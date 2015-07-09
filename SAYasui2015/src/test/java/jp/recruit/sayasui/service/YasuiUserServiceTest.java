/**
 *
 */
package jp.recruit.sayasui.service;

import jp.recruit.sayasui.entity.YasuiUser;

import org.junit.Before;
import org.junit.Test;
import org.seasar.extension.unit.S2TestCase;

/**
 * @author yuki
 *
 */
public class YasuiUserServiceTest extends S2TestCase {
	private YasuiUserService yasuiUserService;

	/* (非 Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		include("app.dicon");
		super.setUp();
	}

	/**
	 * {@link jp.recruit.sayasui.service.YasuiUserService#getYasuiUserByName(java.lang.String)} のためのテスト・メソッド。
	 */
	@Test
	public void testGetYasuiUserByName() {
		YasuiUser expected = new YasuiUser();
		expected.userId="A0001";
		expected.name="admin";
		expected.passwd="b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86";
		expected.descript="管理者";
		expected.role="administrator";
		expected.isDelete=0;
		YasuiUser actual = yasuiUserService.getYasuiUserByName("admin");
		System.out.println("actual:id:"+actual.userId);
		System.out.println("actual:name:"+actual.name);
		System.out.println("actual:passwd:"+actual.passwd);
		System.out.println("actual:descript:"+actual.descript);
		System.out.println("actual:role:"+actual.role);
		System.out.println("actual:isDelete:"+actual.isDelete);
		assertEquals(expected.userId,actual.userId);
	}

}
