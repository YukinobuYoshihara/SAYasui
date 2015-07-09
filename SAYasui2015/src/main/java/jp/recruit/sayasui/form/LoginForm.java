package jp.recruit.sayasui.form;

import java.io.Serializable;

import jp.recruit.sayasui.entity.YasuiUser;

import org.seasar.struts.annotation.Required;

public class LoginForm implements Serializable {
	private static final long serialVersionUID = -3324412681514877460L;

	public YasuiUser yasuiUser;

    @Required
    public String username;

    @Required
    public String password;

    public void reset() {
    	username = "";
    	password = "";
    }

}
