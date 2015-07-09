/**
 *
 */
package jp.recruit.sayasui.service;

import java.util.ArrayList;
import java.util.List;

import jp.recruit.sayasui.dto.ItemDto;

import org.junit.Before;
import org.junit.Test;
import org.seasar.extension.unit.S2TestCase;



/**
 * @author yuki
 *
 */
public class OrdersServiceTest extends S2TestCase {
  private OrdersService ordersService;

	/* (非 Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		include("app.dicon");
	}

	/**
	 * {@link jp.recruit.sayasui.service.OrdersService#insertOrder(java.lang.String, java.util.List)} のためのテスト・メソッド。
	 */
	@Test
	public void testInsertOrder() {
		//fail("まだ実装されていません");
		List<ItemDto> itemDtoList=new ArrayList<ItemDto>();
		ItemDto itemDto=new ItemDto();
		itemDto.setItemId("00001");
		itemDto.setOrder(1);
		itemDtoList.add(itemDto);
		int actual = ordersService.insertOrder("admin", itemDtoList);
		System.out.println("結果："+actual);
		assertEquals(actual,1);
	}

}
