package jp.recruit.sayasui.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Stockエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2015/07/03 9:20:05")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    /** itemIdプロパティ */
    @Id
    @Column(length = 5, nullable = false, unique = true)
    public String itemId;

    /** stockNumプロパティ */
    @Column(precision = 8, nullable = false, unique = false)
    public Integer stockNum;

    /** isDeleteプロパティ */
    @Column(precision = 1, nullable = true, unique = false)
    public Short isDelete;
    
    public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@OneToOne
    @JoinColumn(name="item_id",referencedColumnName="item_id")
    public Item item;
}