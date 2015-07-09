<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="ログイン認証に失敗しました | Super Agile YASUI家具オンラインショップ "/>
<tiles:put name="content" type="string">
<html:errors/>
<h2>認証に失敗しました。</h2>
<s:link href="/index">［ログインページに戻る］</s:link>
</tiles:put>
</tiles:insert>