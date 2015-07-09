package jp.recruit.sayasui.entity;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Ordersエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/07/03 9:20:05")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /** oidプロパティ */
    @Id
    @Column(length = 25, nullable = false, unique = false)
    public String oid;

    /** userNameプロパティ */
    @Column(length = 20, nullable = false, unique = false)
    public String userName;

    /** itemIdプロパティ */
    @Id
    @Column(length = 5, nullable = false, unique = false)
    public String itemId;

    /** quantityプロパティ */
    @Column(precision = 8, nullable = true, unique = false)
    public Integer quantity;

    /** isDeliveryプロパティ */
    @Column(precision = 1, nullable = true, unique = false)
    public Short isDelivery;

    /** orderDateプロパティ */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, unique = false)
    public Date orderDate;

    /** deliveryDateプロパティ */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, unique = false)
    public Date deliveryDate;
}