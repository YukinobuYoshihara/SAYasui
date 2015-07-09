<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="ログアウト | Super Agile YASUI家具オンラインショップ "/>
<tiles:put name="content" type="string">
<html:errors/>
<h2>ログアウトしました</h2>
<s:form method="GET" action="/index">
<input type="submit" name="return" value="ログイン画面に戻る" />
</s:form>
</tiles:put>
</tiles:insert>