package jp.recruit.sayasui.entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * YasuiUserエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/07/03 9:20:05")
public class YasuiUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /** userIdプロパティ */
    @Id
    @Column(length = 5, nullable = false, unique = true)
    public String userId;

    /** nameプロパティ */
    @Column(length = 20, nullable = true, unique = true)
    public String name;
    

    /** passwdプロパティ */
    @Column(length = 128, nullable = true, unique = false)
    public String passwd;

    /** descriptプロパティ */
    @Column(length = 42, nullable = true, unique = false)
    public String descript;

    /** roleプロパティ */
    @Column(length = 30, nullable = false, unique = false)
    public String role;

    /** isDeleteプロパティ */
    @Column(precision = 1, nullable = true, unique = false)
    public Short isDelete;
}