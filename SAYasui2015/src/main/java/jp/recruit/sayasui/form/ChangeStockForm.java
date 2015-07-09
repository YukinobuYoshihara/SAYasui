package jp.recruit.sayasui.form;

import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class ChangeStockForm implements Serializable {
	private static final long serialVersionUID = 7909677907993644846L;
	//個数の分からない同一名のフォームは配列でいったん受ける
	public String [] changeStockItems;

	//@IntegerType は、配列なので使用できない
	public String [] oldStock;

	public String [] newStock;

	public String goahead;//進む系のボタン

	public String goback;//戻るボタン

	public void reset(){
		changeStockItems=new String[0];
		oldStock=new String[0];
		newStock=new String[0];
	}

	public ActionMessages validate() {
		ActionMessages errors = new ActionMessages();
		boolean flag=true;
		for(int i=0;i<newStock.length;i++){
			if(!this.isNumber(newStock[i])){
				flag=false;
				errors.add(ActionMessages.GLOBAL_MESSAGE,
						new ActionMessage("商品ID:"+changeStockItems[i]+"の注文数["+newStock[i]+"]が不正です。整数で指定してください"
								,false));
			}else{
				int temp = NumberUtils.toInt(newStock[i]);
				if(temp<0){
					flag=false;
					errors.add(ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage("商品ID:"+changeStockItems[i]+"の注文数["+newStock[i]+"]が不正です。正の整数で指定してください"
									,false));
				}
			}
		}
		if(!flag){
			this.reset();
			errors.add(ActionMessages.GLOBAL_MESSAGE,
					new ActionMessage("最低1つ以上の在庫設定が不正です。正しい在庫数を設定してください",false));
		}
		return errors;
	}
	public boolean isNumber(String val) {
		return NumberUtils.isNumber(val);
	}
}
