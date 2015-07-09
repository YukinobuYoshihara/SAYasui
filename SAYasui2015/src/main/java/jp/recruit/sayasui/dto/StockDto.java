/**
 *
 */
package jp.recruit.sayasui.dto;

import java.io.Serializable;

/**
 * @author yuki
 *
 */
public class StockDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 316613189853840827L;
	String itemId="";
	Integer stockNum=0;
	Short isDelete=0;
	/**
	 * @return itemId
	 */
	public String getitemId() {
		return itemId;
	}
	/**
	 * @param itemId セットする itemId
	 */
	public void setitemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return stockNum
	 */
	public Integer getStockNum() {
		return stockNum;
	}
	/**
	 * @param stockNum セットする stockNum
	 */
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
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


}
