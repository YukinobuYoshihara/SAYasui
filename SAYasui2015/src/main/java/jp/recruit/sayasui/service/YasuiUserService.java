package jp.recruit.sayasui.service;

import java.util.List;

import javax.annotation.Generated;
import javax.annotation.Resource;

import org.seasar.extension.jdbc.JdbcManager;

import jp.recruit.sayasui.entity.YasuiUser;
import static jp.recruit.sayasui.entity.YasuiUserNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link YasuiUser}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/06/06 23:53:51")
public class YasuiUserService extends AbstractService<YasuiUser> {
	@Resource
	protected JdbcManager jdbcManager;

    /**
     * 識別子でエンティティを検索します。
     *
     * @param id
     *            識別子
     * @return エンティティ
     */
    public YasuiUser findById(String id) {
        return jdbcManager.from(YasuiUser.class).id("userId").getSingleResult();
    }
/**
 * nameでエンティティを検索します
 * @param name
 * @return エンティティ
 */
    public YasuiUser findByName(String name,String password){
     		Digest digest = new Digest("SHA-512");
			return jdbcManager.from(YasuiUser.class).where("name = ? and passwd = ? and is_delete < 1",name,digest.hex(password)).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<YasuiUser> findAllOrderByuserId() {
        return select().orderBy(asc(userId())).getResultList();
    }
    /**
     * nameでエンティティを検索します
     * @param name
     * @return エンティティ
     */
        public YasuiUser getYasuiUserByName(String name){
    			return jdbcManager.from(YasuiUser.class).where("name = ? and is_delete < 1",name).getSingleResult();
        }


}