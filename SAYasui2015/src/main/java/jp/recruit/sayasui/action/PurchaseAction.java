package jp.recruit.sayasui.action;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import jp.recruit.sayasui.dto.ItemDto;
import jp.recruit.sayasui.dto.UserDto;
import jp.recruit.sayasui.entity.Item;
import jp.recruit.sayasui.entity.Stock;
import jp.recruit.sayasui.form.PurchaseForm;
import jp.recruit.sayasui.service.ItemService;
import jp.recruit.sayasui.service.OrdersService;
import jp.recruit.sayasui.service.StockService;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.framework.beans.util.BeanUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.enums.SaveType;
import org.seasar.struts.exception.ActionMessagesException;
import org.seasar.struts.util.ActionMessagesUtil;
import org.seasar.struts.util.RequestUtil;

public class PurchaseAction {
	@Resource
	@ActionForm
	protected PurchaseForm purchaseForm;

	@Resource
	protected ItemService itemService;

	@Resource
	protected StockService stockService;

	@Resource
	protected OrdersService ordersService;

	@Resource
	protected HttpSession session;

	public ArrayList<ItemDto> itemDtoList;

	/**
	 * 文字列が数値に変換できるかどうかをチェック
	 * @return 数値変換可能ならtrue 不可だったらfalse
	 */
	public boolean isNumber(String val) {
		return NumberUtils.isNumber(val);
	}

	@Execute(input = "/index.jsp")
	public String index() {
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		System.err.println("#purchaseAction:role"+userDto.role);
		itemDtoList = new ArrayList<ItemDto>();
		List<Item> items = itemService.findAllOrderByIdWithStock();
		System.err.println("■■サービスからの戻り値："+items.size());
		for(Item item: items){
			ItemDto itemDto = new ItemDto();
			//EntityからDtoにコピー、Null許容
			BeanUtil.copyProperties(item, itemDto,true);
			System.err.println("itemDtoの名前："+itemDto.getItemName());
			itemDtoList.add(itemDto);
		}
		
		return "list.jsp";
	}

	@Execute(input = "/index.jsp")
	public String goback() {
		UserDto userDto = (UserDto)session.getAttribute("userDto");
		System.err.println("#purchaseAction:role"+userDto.role);
		itemDtoList = new ArrayList<ItemDto>();
		List<Item> items = itemService.findAllOrderByIdWithStock();
		System.err.println("■■サービスからの戻り値："+items.size());
		for(Item item: items){
			ItemDto itemDto = new ItemDto();
			//EntityからDtoにコピー、Null許容
			BeanUtil.copyProperties(item, itemDto,true);
			System.err.println("itemDtoの名前："+itemDto.getItemName());
			itemDtoList.add(itemDto);
		}
		return "list.jsp";
	}


	@Execute(input = "list.jsp",saveErrors=SaveType.SESSION,stopOnValidationError=false)
	public String confirm() {
		//既にあるグローバルなエラーを削除
		session.removeAttribute(Globals.ERROR_KEY);
		//エラー処理用ActionMessagesの作成
		ActionMessages messages = new ActionMessages();

		//エラー数カウンター
		int error=0;

		//正常処理チェック
		boolean flag = true;

		UserDto userDto=(UserDto)session.getAttribute("userDto");
		if(userDto==null){
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("ログインされていません。ログイン画面に転送します",false));
			return "/index"; //セッションの時間切れなどによって、userDtoがNullなときは/indexに転送してログインさせる
		}

		//ActionFormの内容をHashMapに移し替える
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0;i<purchaseForm.availableitem.length;i++){
			//数値じゃなかったらエラー
			if(!this.isNumber(purchaseForm.quantity[i])){
				error++;
				messages.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("商品ID:"+purchaseForm.availableitem[i]+"の注文数["+purchaseForm.quantity[i]+"]が不正です。整数で指定してください"
								,false));
			}else{
				//注文を整数orderに変換する
				int order = NumberUtils.toInt(purchaseForm.quantity[i]);
				//注文が正の整数かどうかチェック
				if(order<0){
					error++;
					messages.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage("商品ID:"+purchaseForm.availableitem[i]+"の注文数["+purchaseForm.quantity[i]+"]が不正です。正の整数で指定してください"
									,false));
				}else if(order == 0){
					//注文数が0なので単純にスキップ
				}else{
					//注文数が正の整数なので、注文のHashMapに追加する
					map.put(purchaseForm.availableitem[i], order);
					System.out.println("注文されたID："+purchaseForm.availableitem[i]+" 数量："+order);
				}
			}
		}

		//最新の在庫情報を持つitemエンティティ
		List<Item> itemEntities = itemService.findAllOrderByIdWithStock();
		if(itemEntities.size()<1){
			session.setAttribute("canOrder", Boolean.valueOf(false));
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("注文可能な商品が見つかりません",false));
		}

		ArrayList<ItemDto> orderedItemList = new ArrayList<ItemDto>();
		session.setAttribute("canOrder", Boolean.valueOf(true));
		//商品一覧から商品ItemDtoクラスのオブジェクトを取り出し
		for(Map.Entry<String, Integer> e:map.entrySet()){
			System.err.println("e.getKey()="+e.getKey()+" e.getValue()="+e.getValue());
			for(Item item:itemEntities){
				if(item.itemId.equalsIgnoreCase(e.getKey())){
					//Itemの内容をItemDtoに変換
					ItemDto orderedItemDto = new ItemDto();
					BeanUtil.copyProperties(item, orderedItemDto,true);
					orderedItemDto.setOrder(e.getValue());
					//注文数量をチェック
					if(orderedItemDto.stock.stockNum>=e.getValue()){
						//在庫の範囲内の注文なのでDTOのリストに追加
						orderedItemList.add(orderedItemDto);
						//正常な注文があったのでフラグをfalse
						flag=false;
					}else{
						error++;
						//エラーメッセージ用StringBuilder
						StringBuilder sb = new StringBuilder();
						System.err.println("エラーの数"+error);
						sb.append(orderedItemDto.getItemName());
						sb.append("の発注数：");
						sb.append(orderedItemDto.getOrder());
						sb.append("が、在庫数：");
						sb.append(orderedItemDto.stock.stockNum);
						sb.append("を超えています。");
						System.err.println(sb.toString());
						messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(sb.toString(),false));
					}
				}
			}
		}
		//注文フラグがfalseになっていなかったら正規の注文がない
		if(flag){
			error++;
			messages.add(ActionMessages.GLOBAL_MESSAGE,
					new ActionMessage("最低1つ以上の商品に注文数を設定してください",false));
		}
		//ActionMessageが空ではない場合
		if(!messages.isEmpty()){
			//セッションにActionMessagesを保管したい場合(リダイレクトなどを使用する場合）
			//ActionMessagesUtil.addMessages(session, messages);

			//requestにActionMessagesを保管したい場合
			ActionMessagesUtil.addErrors(RequestUtil.getRequest(), messages);

			//ActionMessageの処理はActionMessagesExceptionでも代替できる
			//ActionMessagesExceptionをハンドリングするIntercepter定義をapp.diconに定義し、
			//customizer.diconで、インターセプターを有効にすると、ActionMessagesExceptionをインターセプトして処理をしてくれる
			//sb.append("左記の商品の発注数を見直してください");
			//throw new ActionMessagesException(sb.toString(),false);
		}
		//処理に何らかのエラーがあった場合、オーダー不可フラグを立てる
		if(error!=0){
			session.setAttribute("canOrder", Boolean.valueOf(false));
		}
		System.err.println("注文のArrayListのサイズ："+orderedItemList.size());
		session.setAttribute("orderItems", orderedItemList);
		return "confirm.jsp";
	}

	@Resource
	protected UserTransaction userTransaction;	//本来はこのレベルの操作であれば、s2jdbcに任せて良いが、サンプルとしてユーザートランザクション制御を指定する

	@SuppressWarnings("unchecked")
	@Execute(input="/purchase",removeActionForm=true)
	@TransactionAttribute(TransactionAttributeType.NEVER) //S2JDBCのトランザクション自動制御の対象外にする指定 徹底入門 P438～
	public String complete() {
		ArrayList<ItemDto> orderItems = (ArrayList<ItemDto> )session.getAttribute("orderItems");

		if(orderItems==null){
			throw new ActionMessagesException("注文情報が取得できませんでした",false);
		}
		ActionMessages messages = new ActionMessages();
		try{
			userTransaction.begin();//トランザクション開始
			List<Stock> oldStocks = stockService.findAllOrderByItemId();
			stockService.updateStockByList(oldStocks,orderItems);
			UserDto userDto = (UserDto)session.getAttribute("userDto");
			ordersService.insertOrder(userDto.name, orderItems);
		}catch(NotSupportedException|SystemException e){
			try {
				//ロールバックするフラグを立てる
				userTransaction.setRollbackOnly();
			} catch(IllegalStateException | SystemException e1) {
				e1.printStackTrace();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("注文処理が失敗しました。注文の取り消し処理も失敗しています。原因："+e1.getMessage(),false));
			}
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("注文処理が失敗しました。注文は取り消されます。原因："+e.getMessage(),false));
		}finally{
			try {
				//トランザクションが正常に進行していたら、コミット
				if(userTransaction.getStatus() == Status.STATUS_ACTIVE){
					userTransaction.commit();
					return "complete.jsp";
				}
				userTransaction.rollback();
				if(messages.size()!=0){
					ActionMessagesUtil.addErrors(RequestUtil.getRequest(), messages);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				session.removeAttribute("itemDtoList");
				session.removeAttribute("orderItems");
				session.removeAttribute("canOrder");
				purchaseForm.reset();
			}
		}

		return "/purchase";
	}

}
