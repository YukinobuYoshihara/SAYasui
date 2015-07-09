package jp.recruit.sayasui.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.http.HttpSession;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import jp.recruit.sayasui.dto.ItemDto;
import jp.recruit.sayasui.entity.Item;
import jp.recruit.sayasui.form.RemoveItemForm;
import jp.recruit.sayasui.service.ItemService;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.BeanUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.enums.SaveType;
//import org.seasar.struts.exception.ActionMessagesException;

public class RemoveItemAction {
	@Resource
	ItemService itemService;

	@Resource
	@ActionForm
	RemoveItemForm removeItemForm;

	@Resource
	protected HttpSession session;

	public ArrayList<ItemDto> itemDtoList;
	public ArrayList<ItemDto> targetItemList;

	@Execute(validator = false)
	public String index() {
		removeItemForm.reset();
		itemDtoList=new ArrayList<ItemDto>();
		List<Item> items = itemService.findAllOrderByIdWithStock();
		System.out.println("■■サービスからの戻り値："+items.size());
		for(Item item: items){
			ItemDto itemDto = new ItemDto();
			//EntityからDtoにコピー、Null許容
			BeanUtil.copyProperties(item, itemDto,true);
			System.out.println("itemDtoの名前："+itemDto.getItemName());
			itemDtoList.add(itemDto);
		}
		session.setAttribute("itemDtoList", itemDtoList);

		return "list.jsp";
	}
	@SuppressWarnings("unchecked")
	@Execute(validate="validate",input = "list.jsp",saveErrors=SaveType.SESSION,stopOnValidationError=false,removeActionForm=true)
	public String confirm() {
		//エラー処理用ActionMessagesの作成
		ActionMessages messages = new ActionMessages();
		targetItemList = new ArrayList<ItemDto>();
		itemDtoList=(ArrayList<ItemDto>)session.getAttribute("itemDtoList");
		if(itemDtoList==null){
			messages.add("itemDtoList", new ActionMessage("ログインされていません。ログイン画面に転送します",false));
			return "/index";
		}else if(itemDtoList.size()<1){
			session.setAttribute("canRemove", Boolean.valueOf(false));
			messages.add("itemDtoList", new ActionMessage("注文可能な商品が見つかりません",false));
		}else{
			for(ItemDto itemDto:itemDtoList){
				for(int i=0;i<removeItemForm.targetItemList.length;i++){
					if(itemDto.getItemId().equals(removeItemForm.targetItemList[i])){
						targetItemList.add(itemDto);
						session.setAttribute("canRemove", Boolean.valueOf(true));
					}
				}
			}
		}
		removeItemForm.reset();
		session.removeAttribute("itemDtoList");
		session.setAttribute("targetItemList", targetItemList);
		return "confirm.jsp";
	}

	@Resource
	protected UserTransaction userTransaction;	//本来はこのレベルの操作であれば、s2jdbcに任せて良いが、サンプルとしてユーザートランザクション制御を指定する

	@SuppressWarnings("unchecked")
	@Execute(validator = false)
	@TransactionAttribute(TransactionAttributeType.NEVER) //S2JDBCのトランザクション自動制御の対象外にする指定
	public String complete() {
		//エラー処理用ActionMessagesの作成
		ActionMessages messages = new ActionMessages();

		targetItemList = (ArrayList<ItemDto> )session.getAttribute("targetItemList");
		if(targetItemList==null){
			messages.add("targetItemList", new ActionMessage("削除対象の商品情報が取得できませんでし",false));
		}
		try{
			userTransaction.begin();//トランザクション開始
			List<Item> itemList = itemService.findAllOrderByIdWithStock();
			int result=itemService.removeItemByList(itemList, targetItemList);
			System.out.println("削除されたエンティティ数："+result);
		}catch(Exception e){
			try {
				//ロールバックするフラグを立てる
				messages.add("targetItemList", new ActionMessage("商品の削除に失敗しました。ロールバックします",false));
				userTransaction.setRollbackOnly();
			} catch (Exception e1) {
				messages.add("targetItemList", new ActionMessage("エラーの際のロールバックに失敗しました",false));
			}
		}finally{
			try {
				//トランザクションが正常に進行していたら、コミット
				if(userTransaction.getStatus() == Status.STATUS_ACTIVE){
					userTransaction.commit();
					return "complete.jsp";
				}else{
					userTransaction.rollback();
				}
			} catch(Exception e){

			} finally{
				removeItemForm.reset();
				session.removeAttribute("itemDtoList");
				session.removeAttribute("targetItemList");
				session.removeAttribute("canRemove");
			}
		}
		return "/removeItem?redirect=true";
	}


}
