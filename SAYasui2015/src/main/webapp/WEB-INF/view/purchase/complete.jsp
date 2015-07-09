<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="商品発注の完了 | Super Agile YASUI家具オンラインショップ" />
	<tiles:put name="content" type="string">
	<html:errors />
	<h2>注文を完了しました</h2>
	<s:form method="POST">
		<input type="submit" name="index" value="商品一覧に戻る" />
	</s:form>
	<c:remove var="canOrder"/>
	</tiles:put>
</tiles:insert>