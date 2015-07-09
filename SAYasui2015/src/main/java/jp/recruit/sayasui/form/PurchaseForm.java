package jp.recruit.sayasui.form;

import java.io.Serializable;

import java.util.ArrayList;
//commons-langをダウンロードして追加
import org.apache.commons.lang3.math.NumberUtils;
//import org.apache.struts.action.ActionMessage;
//import org.apache.struts.action.ActionMessages;

import jp.recruit.sayasui.dto.ItemDto;
public class PurchaseForm implements Serializable {
	private static final long serialVersionUID = -6981947489294903559L;

	public String [] availableitem;

	//@IntegerType は、配列なので使用できない
	public String [] quantity;


	public ArrayList<ItemDto> itemDtoList;

	public ArrayList<ItemDto> orderItems;

/*	validateメソッドをFormに書いた場合の例
  	public ActionMessages validate() {
		ActionMessages errors = new ActionMessages();
		boolean flag=true;
		for(int i=0;i<quantity.length;i++){
			if(!this.isNumber(quantity[i])){
				errors.add("quantity",
						new ActionMessage("商品ID:"+availableitem[i]+"の注文数["+quantity[i]+"]が不正です。整数で指定してください"
								,false));
			}else{
				int temp = NumberUtils.toInt(quantity[i]);
				if(temp!=0){
					flag=false;
				}else if(temp<0){
					errors.add("quantity",
							new ActionMessage("商品ID:"+availableitem[i]+"の注文数["+quantity[i]+"]が不正です。正の整数で指定してください"
									,false));
				}
			}
		}
		if(flag){
			errors.add("quantity",
					new ActionMessage("最低1つ以上の商品に注文数を設定してください",false));
		}
		return errors;
	}
	*/
	public boolean isNumber(String val) {
		return NumberUtils.isNumber(val);
	}

	public void reset(){
		availableitem=new String[0];
		quantity=new String[0];
	}


}
