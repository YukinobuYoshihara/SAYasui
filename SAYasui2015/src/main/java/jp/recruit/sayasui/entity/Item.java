package jp.recruit.sayasui.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Itemエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/07/03 9:20:05")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /** itemIdプロパティ */
    @Id
    @Column(length = 5, nullable = false, unique = true)
    public String itemId;

    /** itemNameプロパティ */
    @Column(length = 50, nullable = false, unique = false)
    public String itemName;

    /** imgurlプロパティ */
    @Column(length = 50, nullable = true, unique = false)
    public String imgurl;

    /** itemSizeプロパティ */
    @Column(length = 50, nullable = true, unique = false)
    public String itemSize;

    /** priceプロパティ */
    @Column(precision = 8, nullable = false, unique = false)
    public Integer price;

    /** isDeleteプロパティ */
    @Column(precision = 1, nullable = true, unique = false)
    public Short isDelete;
    
    @OneToOne(mappedBy = "item")
    public Stock stock;
}