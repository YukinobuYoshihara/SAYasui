/**
 *
 */
package jp.recruit.sayasui.service;

import java.util.List;

import jp.recruit.sayasui.entity.Item;

import org.junit.Before;
import org.junit.Test;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author yuki
 *
 */
public class ItemServiceTest extends S2TestCase {

	/* (非 Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		include("app.dicon");
	}
	private ItemService itemService;
	/**
	 * {@link jp.recruit.sayasui.service.ItemService#getCountByName(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testGetCountByName() {
		//fail("まだ実装されていません");
		String name="いす（青）";
		long actual=itemService.getCountByName(name);
		System.out.println(name+"の数："+actual);
		assertEquals(1L, actual);

	}
	@Test
	public void testJoinTest(){
		List<Item> result = itemService.testJoin();
		assertNotNull(result);
	}
}
