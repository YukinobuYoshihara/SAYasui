package jp.recruit.sayasui.action;

import java.util.ArrayList;
import java.util.List;
//commons-langをダウンロードして追加
import org.apache.commons.lang3.math.NumberUtils;
import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.http.HttpSession;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import jp.recruit.sayasui.dto.ItemDto;

import jp.recruit.sayasui.entity.Item;
import jp.recruit.sayasui.entity.Stock;
import jp.recruit.sayasui.form.ChangeStockForm;
import jp.recruit.sayasui.service.ItemService;
import jp.recruit.sayasui.service.StockService;

import org.seasar.framework.beans.util.BeanUtil;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import org.seasar.struts.exception.ActionMessagesException;

public class ChangeStockAction {
	@Resource
	@ActionForm
	protected ChangeStockForm changeStockForm;

	@Resource
	protected ItemService itemService;

	@Resource
	protected StockService stockService;

	@Resource
	protected HttpSession session;

	public List<ItemDto> itemDtoList;
	public List<ItemDto> changeStokItems;

	@Execute(validator=false)
	public String index() {
		itemDtoList = new ArrayList<ItemDto>();
		List<Item> items = itemService.findAllOrderByIdWithStock();
		System.out.println("■■サービスからの戻り値："+items.size());
		for(Item item: items){
			ItemDto itemDto = new ItemDto();
			//EntityからDtoにコピー、Null許容
			BeanUtil.copyProperties(item, itemDto,true);
			System.out.println("ItemDtoの名前："+itemDto.getItemName());
			itemDtoList.add(itemDto);
		}
		if(itemDtoList.size()==0){

		}
		session.setAttribute("itemDtoList", itemDtoList);
		return "list.jsp";
	}

	@SuppressWarnings("unchecked")
	@Execute(validate="validate",input = "list.jsp",stopOnValidationError=false,removeActionForm=true)
	public String confirm() {
		itemDtoList=(List<ItemDto>)session.getAttribute("itemDtoList");
		if(itemDtoList==null){
			System.out.println("itemDtoListがnullだった");
			return "/index"; //セッションの時間切れなどによって、ItemDtoListがNullなときは/indexに転送してログインさせる
		}else	if(itemDtoList.size()<1){
			session.setAttribute("canChange", Boolean.valueOf(false));
			throw new ActionMessagesException("在庫を変更する商品一覧が取得できませんでした。ログインしなおしてください",false);
		}
		//changeStokItems = new ArrayList<ItemDto>();
		//商品一覧から商品ItemDtoクラスのオブジェクトを取り出して、新在庫数を更新
		List<ItemDto> newStockList = new ArrayList<ItemDto>();
		for(int j=0;j<itemDtoList.size();j++){
			ItemDto itemDto = itemDtoList.get(j);
			//ActionFormに含まれる、商品IDの配列から、1つずつ商品IDを取り出し
			//該当する商品IDを持つItemDtoを取り出す
			for(int i=0;i<changeStockForm.changeStockItems.length;i++){
				if(itemDto.itemId.equals(changeStockForm.changeStockItems[i])){
					Integer temp=0;
					if(NumberUtils.isNumber(changeStockForm.newStock[i]))
						temp=Integer.valueOf(changeStockForm.newStock[i]);
					//新在庫が0以上の時のみ
					if(temp>=0){
						itemDto.newStock=Integer.valueOf(changeStockForm.newStock[i]);
						itemDto.stock.stockNum = Integer.valueOf(changeStockForm.oldStock[i]);
						System.out.println("商品"+itemDto.itemId+"の新在庫数："+Integer.valueOf(changeStockForm.newStock[i]));
						newStockList.add(itemDto);
					}
				}
			}
		}
		if(newStockList.size()<1){
			session.setAttribute("canChange", Boolean.valueOf(false));
			changeStockForm.reset();
			return "confirm.jsp";
		}
		//session.removeAttribute(Globals.ERROR_KEY);
		session.setAttribute("newStockList", newStockList);
		session.setAttribute("canChange", Boolean.valueOf(true));
		changeStockForm.reset();
		return "confirm.jsp";

	}
	@Resource
	protected UserTransaction userTransaction;	//本来はこのレベルの操作であれば、s2jdbcに任せて良いが、サンプルとしてユーザートランザクション制御を指定する

	@SuppressWarnings("unchecked")
	@Execute(input="/ChangeStock")
	@TransactionAttribute(TransactionAttributeType.NEVER) //S2JDBCのトランザクション自動制御の対象外にする指定
	public String complete() {
		itemDtoList = (ArrayList<ItemDto>)session.getAttribute("itemDtoList");

		if(itemDtoList!=null && itemDtoList.size()!=0){
			try{
				userTransaction.begin();//トランザクション開始
				List<Stock> oldStocks = stockService.findAllOrderByItemId();
				stockService.changeStockByList(oldStocks,itemDtoList);
			}catch(Exception e){
				try {
					//ロールバックするフラグを立てる
					userTransaction.setRollbackOnly();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}finally{
				try {
					changeStockForm.reset();
					//トランザクションが正常に進行していたら、コミット
					if(userTransaction.getStatus() == Status.STATUS_ACTIVE){
						session.removeAttribute("itemDtoList");
						session.removeAttribute("changeStokItems");
						session.removeAttribute("canChange");
						userTransaction.commit();
						//成功しているので完了ページに転送
						return "complete.jsp";
					}
					userTransaction.rollback();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//ここまでくるということは失敗している
		return "/ChangeStock";
	}


}
