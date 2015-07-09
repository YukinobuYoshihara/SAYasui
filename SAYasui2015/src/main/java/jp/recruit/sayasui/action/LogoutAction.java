package jp.recruit.sayasui.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.Execute;

public class LogoutAction {

	@Resource
	protected HttpSession session;

	@Execute(input = "/index.jsp")
	@RemoveSession(name = "userDto")
	public String index() {
		if(session.getAttribute("items")!=null){
			session.removeAttribute("items");
		}
		if(session.getAttribute("orderitems")!=null){
			session.removeAttribute("orderitems");
		}
		if(session.getAttribute("errormessage")!=null){
			session.removeAttribute("errormessage");
		}
		if(session.getAttribute("canOrder")!=null){
			session.removeAttribute("canOrder");
		}
		if(session.getAttribute("canAdd")!=null){
			session.removeAttribute("canAdd");
		}
		if(session.getAttribute("canChange")!=null){
			session.removeAttribute("canChange");
		}
		if(session.getAttribute("canRemove")!=null){
			session.removeAttribute("canRemove");
		}
		if(session.getAttribute("nextId")!=null){
			session.removeAttribute("nextId");
		}
		if(session.getAttribute("itemDto")!=null){
			session.removeAttribute("itemDto");
		}
		if(session.getAttribute("itemDtoList")!=null){
			session.removeAttribute("itemDtoList");
		}
		if(session.getAttribute("newStockList")!=null){
			session.removeAttribute("newStockList");
		}
		//
		//セッションの中身を取得できたらすべて削除
		session.removeAttribute("isLogin");
		session.removeAttribute("newItem");
		session.removeAttribute("stockitems");

		session.removeAttribute("errormessage");
		session.removeAttribute("exception");
		//セッション廃棄
		session.invalidate();
		return "logout.jsp";
	}
}
