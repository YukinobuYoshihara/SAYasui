package jp.recruit.sayasui.action;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import jp.recruit.sayasui.dto.ItemDto;
import jp.recruit.sayasui.entity.Stock;
import jp.recruit.sayasui.form.AddItemForm;
import jp.recruit.sayasui.service.ItemService;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;

public class AddItemAction {
	@Resource
	@ActionForm
	protected AddItemForm addItemForm;

	@Resource
	protected ItemService itemService;

	@Resource
	protected HttpSession session;

	@Resource
	public ItemDto itemDto;

	public String nextId;//publicフィールドは自動的にrequest.setAttributeされる

	@Execute(validator=false)
	public String index() {
		System.out.println("indexにきた");
		nextId = itemService.getNextItemId();
		//session.setAttribute("nextId", nextId);//publicフィールドは自動的にrequest.setAttributeされるので不要
		return "add.jsp";
	}

	@Execute(validate="validate",input = "/addItem/index",stopOnValidationError=false,removeActionForm=true)
	public String confirm() {
		System.out.println("追加するボタン押されて、confirmにきたよ");
		ActionMessages messages = new ActionMessages();
		//ここに来ているということは基本的にバリデーションを抜けている
		itemDto.itemId = addItemForm.id;
		itemDto.itemName = addItemForm.itemName;
		itemDto.imgurl = addItemForm.imgurl;
		itemDto.itemSize = addItemForm.itemSize;
		try{
			//商品名の重複チェック
			Long count = itemService.getCountByName(addItemForm.itemName);
			System.out.println("AddItemAction#confirm:"+count);
			if(count!=null&&count!=0){
				throw new Exception("商品名："+addItemForm.itemName+"が重複しています。商品名を再確認してください");
			}
			itemDto.setPrice(Integer.valueOf(addItemForm.price));
			Stock stock = new Stock();
			stock.itemId = addItemForm.id;
			stock.stockNum = Integer.valueOf(addItemForm.newStock);
			stock.isDelete=0;
			itemDto.setStock(stock);
			itemDto.setNewStock(Integer.valueOf(addItemForm.newStock));
		}catch(Exception e){
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("商品の追加の際に、例外が発生しました。商品情報の確認に失敗しました。原因："+e.getMessage(),false));
			ActionMessagesUtil.addErrors(RequestUtil.getRequest(), messages);
			System.err.println("confirmでExceptionおこったよ");
			e.printStackTrace();
			return "/addItem";
		}

		session.setAttribute("itemDto", itemDto);
		session.setAttribute("canAdd", Boolean.valueOf(true));
		return "confirm.jsp";
	}


	//追加系処理は更新系と異なり、比較的問題が起きづらいので、ロールバック/コミットの処理はS2JDBCに任せる
	@Execute(validator=false)
	public String complete() {
		System.out.println("completeにきた");
		//既にあるグローバルなエラーを削除
		session.removeAttribute(Globals.ERROR_KEY);
		System.out.println("completeにきたよ");

		//エラー処理用ActionMessagesの作成
		ActionMessages messages = new ActionMessages();
		//セッションから追加する商品情報を取得
		itemDto = (ItemDto)session.getAttribute("itemDto");
		if(addItemForm.goahead!=null){
			System.out.println("itemDtoを取得した");
			if(itemDto!=null){
				System.out.println("itemDtoがnullではない");
				try{
					//商品の追加処理
					itemService.insertItem(itemDto);
					System.out.println("insertItem成功した");
				}catch(Exception e){
					System.out.println("insertItem失敗した");
					session.removeAttribute("canAdd");
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("商品の追加の際に、例外が発生しました。回復処理に失敗しました。原因："+e.getMessage(),false));
					ActionMessagesUtil.addErrors(RequestUtil.getRequest(), messages);
					return "index";
				}
			}else{
				System.out.println("itemDtoがnullだった");
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("商品追加のための情報が正常に渡されませんでした",false));
				ActionMessagesUtil.addErrors(RequestUtil.getRequest(), messages);
				return "index";
			}
		}else if(addItemForm.goback!=null){
			System.out.println("goback押された");
			return "index";
		}
		//不要なセッション変数を削除
		session.removeAttribute("itemDto");
		session.removeAttribute("canAdd");

		//成功しているので完了ページに転送
		return "complete.jsp";
	}



}
