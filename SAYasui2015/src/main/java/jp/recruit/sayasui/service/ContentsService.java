package jp.recruit.sayasui.service;

import java.util.List;
import javax.annotation.Generated;
import jp.recruit.sayasui.entity.Contents;

import static jp.recruit.sayasui.entity.ContentsNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * {@link Contents}のサービスクラスです。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/06/06 23:53:51")
public class ContentsService extends AbstractService<Contents> {

    /**
     * 識別子でエンティティを検索します。
     * 
     * @param mid
     *            識別子
     * @return エンティティ
     */
    public Contents findById(String mid) {
        return select().id(mid).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     * 
     * @return エンティティのリスト
     */
    public List<Contents> findAllOrderById() {
        return select().orderBy(asc(mid())).getResultList();
    }
}