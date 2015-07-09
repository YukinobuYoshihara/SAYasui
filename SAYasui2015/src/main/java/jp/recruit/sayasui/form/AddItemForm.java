package jp.recruit.sayasui.form;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.seasar.struts.annotation.IntegerType;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Required;

public class AddItemForm implements Serializable {
	private static final long serialVersionUID = 2044051461347683723L;

	@Required(target="confirm")
	@Mask(mask = "^[0-9]{1,5}$")
	@Maxlength(maxlength = 5)
	public String id = "";
	@Required(target="confirm")
	@Maxlength(maxlength = 50)
	public String itemName = "";
	@Required(target="confirm")
	@Maxlength(maxlength = 50)
	public String imgurl = "";
	@Required(target="confirm")
	@Mask(mask = "^[0-9]+x[0-9]+x[0-9]+$")
	public String itemSize = "";
	@Required(target="confirm")
	@IntegerType
	@Maxlength(maxlength = 10)
	public String price = "";
	@Required(target="confirm")
	@IntegerType
	@Maxlength(maxlength = 8)
	public String newStock = "";

	public String goahead;//進む系のボタン

	public String goback;//戻るボタン

	public void reset() {
		id = "";
		itemName = "";
		imgurl = "";
		itemSize = "";
		price = "";
		newStock = "";
	}
	public ActionMessages validate() {
		ActionMessages errors = new ActionMessages();

		if(this.imgurl!=null&&!this.imgurl.isEmpty()){
			String ptn="\\bhttps?:\\/\\/[\\w\\d-_\\.!~*\\'()\\/\\?:\\@&=+\\$,%#]+\\.(jpg|png|gif|jpeg)\\b";
			Pattern optn=Pattern.compile(ptn,Pattern.CASE_INSENSITIVE);
			Matcher mch=optn.matcher(this.imgurl);
			if(!mch.find()){
				errors.add(ActionMessages.GLOBAL_MESSAGE,new ActionMessage("画像URL:" + this.id
					+ "の形式が不正です。http://ホスト名/画像ファイルパス で指定してください", false));
			}
		}
		return errors;
	}

	public boolean isNumber(String val) {
		return NumberUtils.isNumber(val);
	}

}
