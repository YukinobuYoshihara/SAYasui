package jp.recruit.sayasui.dto;

import java.io.Serializable;

import jp.recruit.sayasui.entity.Stock;

public class ChangeStockDto implements Serializable {
	private static final long serialVersionUID = -4221975669078378694L;
  public String id;
  public String itemName;
  public String imgurl;
  public String itemSize;
  public Integer price;
  public Short isDelete;
  public Integer order;
  public Stock stock;
  public Integer newStock;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id セットする id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName セットする itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return imgurl
	 */
	public String getImgurl() {
		return imgurl;
	}
	/**
	 * @param imgurl セットする imgurl
	 */
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	/**
	 * @return itemSize
	 */
	public String getItemSize() {
		return itemSize;
	}
	/**
	 * @param itemSize セットする itemSize
	 */
	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}
	/**
	 * @return price
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * @param price セットする price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * @return isDelete
	 */
	public Short getIsDelete() {
		return isDelete;
	}
	/**
	 * @param isDelete セットする isDelete
	 */
	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * @return order
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param order セットする order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	/**
	 * @return stock
	 */
	public Stock getStock() {
		return stock;
	}
	/**
	 * @param stock セットする stock
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	/**
	 * @return newStock
	 */
	public Integer getNewStock() {
		return newStock;
	}
	/**
	 * @param newStock セットする newStock
	 */
	public void setNewStock(Integer newStock) {
		this.newStock = newStock;
	}
	public ChangeStockDto() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
