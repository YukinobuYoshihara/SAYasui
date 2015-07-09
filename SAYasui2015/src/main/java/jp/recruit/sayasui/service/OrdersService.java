package jp.recruit.sayasui.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import org.seasar.struts.exception.ActionMessagesException;

import jp.recruit.sayasui.dto.ItemDto;
import jp.recruit.sayasui.entity.Orders;
import static jp.recruit.sayasui.entity.OrdersNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Orders}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/06/06 23:53:51")
public class OrdersService extends AbstractService<Orders> {

	/**
	 * 識別子でエンティティを検索します。
	 *
	 * @param oid
	 *            識別子
	 * @param itemId
	 *            識別子
	 * @return エンティティ
	 */
	public Orders findById(String oid, String itemId) {
		return jdbcManager.from(Orders.class).id(oid, itemId).getSingleResult();

	}

	/**
	 * 識別子の昇順ですべてのエンティティを検索します。
	 *
	 * @return エンティティのリスト
	 */
	public List<Orders> findAllOrderById() {
		return jdbcManager.from(Orders.class).orderBy(asc(oid()), asc(itemId())).getResultList();
	}

	public int insertOrder(String userName,List<ItemDto> itemDtoList){
		int result=0;
		int updateResult=0;

		//注文IDに添付するuidを取得（5ケタ）
		if(itemDtoList==null||itemDtoList.size()==0){
			throw new ActionMessagesException("注文情報が取得できませんでした",false);
		}
		for(ItemDto itemDto : itemDtoList){
			//注文ID（oid）の作成
			StringBuilder sb = new StringBuilder();
			//日時情報を添付（17ケタ）
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			sb.append(sdf.format(date));
			//競合対策で、8桁のランダムな数値をoidに添付する（3ケタ）
			DecimalFormat df = new DecimalFormat("00000000");
			sb.append( df.format( (int)( Math.random() * 100000000 )));

			//注文IDを文字列化する（計25ケタ）
			String oid = sb.toString();
			System.out.println("(OrdersService#insertOrder)作成された注文ID:"+oid);

			updateResult = jdbcManager.updateBySql(
					"Insert into orders (oid,user_name,item_id,quantity,is_delivery,order_date) values (?,?,?,?,0,sysdate)",
					String.class,String.class,String.class,Integer.class)
					.params(oid,userName,itemDto.itemId,itemDto.order)
					.execute();

			if(updateResult !=1){
				throw new ActionMessagesException(
						"注文情報の登録に失敗しました",false);
			}
			result+=updateResult;
		}
		return result;
	}
}