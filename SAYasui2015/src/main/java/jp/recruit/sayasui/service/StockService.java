package jp.recruit.sayasui.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.struts.exception.ActionMessagesException;

import jp.recruit.sayasui.dto.ItemDto;
import jp.recruit.sayasui.dto.ItemDto;
import jp.recruit.sayasui.dto.StockDto;
import jp.recruit.sayasui.entity.Stock;
import static jp.recruit.sayasui.entity.StockNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Stock}のサービスクラスです。
 *
 */
@SuppressWarnings("unused")
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/06/06 23:53:51")
public class StockService extends AbstractService<Stock> {
	@Resource
	protected JdbcManager jdbcManager;

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param itemId
	 *            識別子
	 * @return エンティティ
	 */
	public Stock findByitemId(String itemId) {
		Stock stock = jdbcManager.from(Stock.class).where("itemId = ?", itemId).getSingleResult();
		return stock;
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<Stock> findAllOrderByItemId() {
		return jdbcManager.from(Stock.class).orderBy("itemId asc").getResultList();
	}
	/**
	 * 商品IDと注文数量で在庫を更新
	 * @param String itemId 商品ID
	 * @param int order 注文数量
	 * @return 成功したかどうか（boolean)
	 */
	public boolean updateStockByitemId(String itemId,int order){
		Stock stock = findByitemId(itemId);
		System.out.println("updateStockByitemId"+itemId+"の在庫数："+stock.stockNum+"/注文数："+order);
		if(stock.stockNum<order){
			return false;
		}else{
			stock.stockNum-=order;
		}
		int result = jdbcManager.update(stock).execute();
		if(result == 1){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * エンティティのListに、注文情報のDTOのListを突き合わせ、在庫情報を更新する
	 * @param List<Stock> oldStockList update対象のエンティティ
	 * @param List<ItemDto> itemList
	 * @return 更新された行数
	 */
	public int updateStockByList(List<Stock> oldStockList,List<ItemDto> itemList){
		int result=0;
		int error=0;
		if(itemList==null||itemList.size()==0){
			throw new ActionMessagesException("在庫情報が取得できませんでした",false);
		}
		StringBuilder sb = new StringBuilder();
		for(ItemDto item:itemList){
			for(Stock stock:oldStockList){
				if(item.itemId.equals(stock.itemId)){
					//
					if(stock.stockNum>=item.order){
						stock.stockNum=stock.stockNum-item.order;
						result+=jdbcManager.update(stock).execute();
					}else{
						++error;
						System.out.println("error:"+error);
						System.err.println(item.getItemName()+"の発注数："+
								item.getOrder()+"が、在庫数："+stock.stockNum+
								"を超えています。");
						sb.append(item.getItemName()+"の発注数："+
								item.getOrder()+"が、在庫数："+stock.stockNum+
								"を超えています。");
					}
				}
			}
		}
		if(error!=0){
			sb.append("左記の商品の発注数を見直してください");
			throw new ActionMessagesException(sb.toString(),false);
		}
		if(result!=itemList.size()){
			throw new ActionMessagesException(
					"発注数と更新結果が一致しません。処理をやり直してください。",false);
		}
		return result;
	}
	/**
	 * エンティティのListに、在庫変更情報のDTOのListを突き合わせ、在庫情報を更新する
	 * @param List<Stock> oldStockList update対象のエンティティ
	 * @param List<ItemDto> itemDtoList
	 * @return 更新された行数
	 */
	public int changeStockByList(List<Stock> oldStockList,List<ItemDto> itemDtoList){
		int result=0;
		int error=0;
		if(itemDtoList==null||itemDtoList.size()==0){
			throw new ActionMessagesException("新在庫情報が取得できませんでした",false);
		}
		StringBuilder sb = new StringBuilder();
		for(ItemDto changeStockDto:itemDtoList){
			for(Stock stock:oldStockList){
				if(changeStockDto.getItemId().equals(stock.itemId)){
					//在庫変更の場合は0にする場合もあり得るので、0以上の設定
					if(changeStockDto.getNewStock()>=0){
						stock.stockNum=changeStockDto.getNewStock();
						result+=jdbcManager.update(stock).execute();
					}else{
						++error;
						System.err.println(changeStockDto.getItemName()+"の新在庫数："+
								changeStockDto.getNewStock()+"に不正な値が設定されています。");
						sb.append(changeStockDto.getItemName()+"の新在庫数："+
								changeStockDto.getNewStock()+"に不正な値が設定されています。");
					}
				}
			}
		}
		if(error!=0){
			sb.append("左記の商品の新在庫数を見直してください");
			throw new ActionMessagesException(sb.toString(),false);
		}
		if(result!=itemDtoList.size()){
			throw new ActionMessagesException(
					"設定した新在庫数と更新結果が一致しません。処理をやり直してください。",false);
		}
		return result;
	}
	/**
	 * 在庫情報により、在庫情報を追加する
   * @param String itemId
   * @param Integer stockNum
	 * @return 更新された行数
	 */
	public int insertStock(String itemId,Integer stockNum){
		int result=0;
		Stock stock = new Stock();
		stock.itemId=itemId;

		stock.stockNum=stockNum;
		stock.isDelete=0;
		System.out.println("insertStock: itemId="+itemId+":stockNum="+stockNum);
		result=jdbcManager.insert(stock).execute();
		return result;
	}
	/**
	 * エンティティのListに、在庫変更情報のDTOのListを突き合わせ、在庫情報を更新する
	 * @param List<Stock> oldStockList update対象のエンティティ
	 * @param List<ItemDto> itemDtoList
	 * @return 更新された行数
	 */
	public int removeStockByList(List<Stock> oldStockList,List<StockDto> stockDtoList){
		int result=0;
		int error=0;
		if(stockDtoList==null||stockDtoList.size()==0){
			throw new ActionMessagesException("新在庫情報が取得できませんでした",false);
		}
		StringBuilder sb = new StringBuilder();
		for(StockDto stockDto:stockDtoList){
			for(Stock stock:oldStockList){
				if(stockDto.getitemId().equals(stock.itemId)){
					stock.isDelete=1;
					result+=jdbcManager.update(stock).execute();
				}else{
						++error;
						System.err.println(stockDto.getitemId()+"の削除に失敗しました。");
						sb.append(stockDto.getitemId()+"の削除に失敗しました。");

				}
			}
		}
		if(error!=0){
			throw new ActionMessagesException(sb.toString(),false);
		}
		if(result!=stockDtoList.size()){
			throw new ActionMessagesException(
					"削除しようとする在庫情報と更新結果が一致しません。処理をやり直してください。",false);
		}
		return result;
	}
	/**
	 * 削除対象のエンティティをもとに削除を行う
	 * @paramStock targetStock 削除対象のエンティティ
	 * @return 更新された行数
	 */
	public int removeStock(Stock targetStock){
		int result=0;
		int error=0;
		if(targetStock==null){
			throw new ActionMessagesException("削除対象の在庫情報が取得できませんでした",false);
		}
		targetStock.isDelete=1;
		StringBuilder sb = new StringBuilder();

		result=jdbcManager.update(targetStock).execute();
		if(result==0){
			++error;
			System.err.println(targetStock.itemId+"の削除に失敗しました。");
			sb.append(targetStock.itemId+"の削除に失敗しました。");
		}
		if(error!=0){
			throw new ActionMessagesException(sb.toString(),false);
		}
		return result;
	}

}