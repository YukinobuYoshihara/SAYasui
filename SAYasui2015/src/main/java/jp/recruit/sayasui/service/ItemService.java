package jp.recruit.sayasui.service;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Generated;
import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.struts.exception.ActionMessagesException;

import jp.recruit.sayasui.dto.ItemDto;
import jp.recruit.sayasui.dto.StockDto;
import jp.recruit.sayasui.entity.Item;
import jp.recruit.sayasui.entity.Stock;

import static org.seasar.extension.jdbc.operation.Operations.*;
import static jp.recruit.sayasui.entity.Names.*;
import static jp.recruit.sayasui.entity.ItemNames.*;
/**
 * {@link Item}のサービスクラスです。
 *
 */
@SuppressWarnings("unused")
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/06/06 23:53:51")
public class ItemService extends AbstractService<Item> {
	@Resource
	protected JdbcManager jdbcManager;

	@Resource
	protected StockService stockService;

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param id
	 *            識別子
	 * @return エンティティ
	 */
	public Item findById(String id) {
		return jdbcManager.from(Item.class).id(id).getSingleResult();
	}

	/**
	 * 名前でエンティティの数を調べます。
	 *
	 * @param itemName
	 *            商品名
	 * @return 同じ商品名の数
	 */
	public long getCountByName(String itemName) {
		System.out.println("#getCountByName:"+itemName);
		return jdbcManager.selectBySql(Long.class,"select count(*) from item where item_name =?", itemName).getSingleResult();
	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<Item> findAllOrderById() {
		return jdbcManager.from(Item.class).orderBy("item_id asc").getResultList();
	}
	
	/**
	 * 在庫テーブルとinnnerjoinした結果を取得
	 * @return 在庫付きで商品の一覧のList
	 */

	public List<Item> findAllOrderByIdWithStock(){
		List<Item> result = jdbcManager.from(Item.class)
			.innerJoin("stock")
			.where(new SimpleWhere().eq(isDelete(), 0))
			.orderBy(
					asc(item().itemId())
			)
			.getResultList();
		System.out.println(result.get(0).stock.stockNum);
		return result;
	}
	
	/**
	 * 在庫テーブルとinnnerjoinした結果をロックして取得
	 * @return 在庫付きで商品の一覧のList
	 */

	public List<Item> findAllOrderByIdWithStockForUpdate(){
		List<Item> result = jdbcManager.from(Item.class)
			.innerJoin("stock")
			.where(new SimpleWhere().eq(isDelete(), 0))
			.orderBy(
					asc(item().itemId())
			).forUpdate()
			.getResultList();
		System.out.println(result.get(0).stock.stockNum);
		return result;
	}

	//追加すべき商品のIDを取得する　
	public String getNextItemId(){
		String sql = "select max(item_id) from item";
		String nextId=null;
		String temp = jdbcManager.selectBySql(String.class, sql).getSingleResult();
		int currentMax=-1;//ダミー
		if(NumberUtils.isNumber(temp)){
			int current = NumberUtils.toInt(temp);
			currentMax=current+1;
		}
		DecimalFormat df = new DecimalFormat("00000");
		nextId = df.format(currentMax);
		return nextId;
	}

	public int insertItem(ItemDto itemDto){
		System.out.println("insertItemにきた");
		int result=0;
		Item item = new Item();
		Stock stock = new Stock();
		item.itemId = itemDto.itemId;
		item.itemName = itemDto.itemName;
		item.imgurl = itemDto.imgurl;
		item.itemSize = itemDto.itemSize;
		item.price = itemDto.price;
		item.isDelete = 0;
		//item.stock = itemDto.stock;
		result=jdbcManager.insert(item).execute();

		int stockresult = stockService.insertStock(itemDto.stock.itemId,itemDto.stock.stockNum);
		if(result!=stockresult){
			throw new ActionMessagesException(
					"更新しようとする商品情報と在庫情報が不正です。もう一度やり直してください",false);
		}
		return result;
	}
	/**
	 * エンティティのListに、在庫変更情報のDTOのListを突き合わせ、商品情報の削除フラグを立てる
	 * @param List<Item> oldItemList update対象のエンティティ
	 * @param List<ItemDto> itemDtoList
	 * @return 更新された行数
	 * @throws Exception
	 */
	public int removeItemByList(List<Item> oldItemList,List<ItemDto> itemDtoList){
		int result=0;
		int stockResult=0;
		int error=0;
		if(oldItemList==null||itemDtoList==null||oldItemList.isEmpty()||itemDtoList.isEmpty()){
			throw new ActionMessagesException("削除する商品リストが取得できませんでした",false);
		}
		StringBuilder sb = new StringBuilder();
		for(ItemDto itemDto:itemDtoList){
			for(Item item:oldItemList){
				if(itemDto.getItemId().equals(item.itemId)){
					item.isDelete=1;
					int temp =jdbcManager.update(item).execute();
					if(temp==0){
						++error;
						System.err.println(itemDto.getItemId()+"の削除に失敗しました。");
						sb.append(itemDto.getItemId()+"/"+itemDto.getItemName()+"の削除に失敗しました。");
					}
					result+=temp;
					Stock stock = item.stock;
					int stockTemp=stockService.removeStock(stock);
					if(stockTemp==0){
						++error;
						System.err.println(itemDto.stock.itemId+"の削除に失敗しました。");
						sb.append(itemDto.stock.itemId+"の削除に失敗しました。");
					}
				}
			}
		}
		if(error!=0){
			throw new ActionMessagesException(sb.toString(),false);
		}
		if(result!=itemDtoList.size()){
			throw new ActionMessagesException(
					"削除しようとする商品情報と更新結果が一致しません。処理をやり直してください。",false);
		}
		return result;
	}
	/**
	 * タイプセーフAPIを使ったジョイン記述の作り方のサンプル
	 * @return List<Item>
	 */
	public List<Item> testJoin(){
		return jdbcManager.from(Item.class).innerJoin("stock")
		.where(eq(stock().isDelete(),(short)0)).getResultList();
	}

}