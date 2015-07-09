package jp.recruit.sayasui.form;

import java.io.Serializable;


//commons-langをダウンロードして追加
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
public class RemoveItemForm implements Serializable {
	private static final long serialVersionUID = -7096254930256144432L;
	//個数の分からない同一名のフォームは配列でいったん受ける
	//チェックボックスの値の配列
	public String [] targetItemList;

	public String goahead;//進む系のボタン

	public String goback;//戻るボタン


	public void reset(){
		targetItemList=new String[0];
	}
	public ActionMessages validate() {
		ActionMessages errors = new ActionMessages();
		boolean flag=true;
		for(int i=0;i<targetItemList.length;i++){
			if(!this.isNumber(targetItemList[i])){
				errors.add("targetItemList",
						new ActionMessage("商品ID:["+targetItemList[i]+"]が不正です。整数で指定してください"
								,false));
				reset();
			}else if(targetItemList[i].length()!=5){
				errors.add("targetItemList",
						new ActionMessage("商品ID:["+targetItemList[i]+"]が不正です。5桁で指定してください"
								,false));
				reset();
			}else{
				flag=false;
			}
		}
		if(flag){
			errors.add("targetItemList",
					new ActionMessage("最低1つ以上の商品を選択してください",false));
		}
		return errors;
	}
	public boolean isNumber(String val) {
		return NumberUtils.isNumber(val);
	}


}
