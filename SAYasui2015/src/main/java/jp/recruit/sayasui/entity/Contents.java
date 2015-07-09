package jp.recruit.sayasui.entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Contentsエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/07/03 9:20:05")
public class Contents implements Serializable {

    private static final long serialVersionUID = 1L;

    /** midプロパティ */
    @Id
    @Column(length = 20, nullable = false, unique = true)
    public String mid;

    /** titleプロパティ */
    @Column(length = 100, nullable = true, unique = false)
    public String title;

    /** keywdプロパティ */
    @Column(length = 50, nullable = true, unique = false)
    public String keywd;

    /** descriptプロパティ */
    @Column(length = 100, nullable = true, unique = false)
    public String descript;

    /** roleプロパティ */
    @Column(length = 100, nullable = true, unique = false)
    public String role;

    /** skipプロパティ */
    @Column(precision = 1, nullable = true, unique = false)
    public Short skip;
}