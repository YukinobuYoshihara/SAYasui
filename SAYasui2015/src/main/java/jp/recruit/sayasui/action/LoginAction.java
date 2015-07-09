package jp.recruit.sayasui.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import jp.recruit.sayasui.entity.Item;
import jp.recruit.sayasui.form.LoginForm;
import jp.recruit.sayasui.service.ItemService;
import jp.recruit.sayasui.service.YasuiUserService;
import jp.recruit.sayasui.dto.UserDto;

import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;


public class LoginAction {
	@Resource
	@ActionForm
	protected LoginForm loginForm;

	@Resource
	protected YasuiUserService yasuiUserService;

	@Resource
	protected ItemService itemService;

	@Resource
	protected HttpSession session;

	@Resource
	public UserDto userDto;

	public List<Item> items;

	@Execute(input = "/loginError.jsp")
	public String login() {
		System.out.println("旧セッションID"+session.getId());
		session.invalidate();
		loginForm.yasuiUser = yasuiUserService.findByName(loginForm.username,loginForm.password);
		if (loginForm.yasuiUser == null) {	//ユーザーが入力された条件で見つからなかった
			loginForm.reset();
			return "/loginError.jsp";
		}else{
			//ログイン成功したら、これまでのセッションを破棄して、新規IDを取得
			session = (HttpSession) SingletonS2ContainerFactory
		                .getContainer()
		                .getExternalContext()
		                .getSession();
			userDto.name=loginForm.yasuiUser.name;
			userDto.descript=loginForm.yasuiUser.descript;
			userDto.role=loginForm.yasuiUser.role;
			//セッションを破棄しているので、明示的にuserDtoをセッションスコープで保存
			session.setAttribute("userDto", userDto);
		}
		if(userDto.role.equals("user")){
			return "/purchase";
		}else{
			return "/addItem";
		}

	}

}
