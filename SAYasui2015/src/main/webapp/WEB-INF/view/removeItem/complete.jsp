<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="【管理】商品削除の確認 | Super Agile YASUI家具オンラインショップ"/>
<tiles:put name="content" type="string">
<html:errors/>
	<c:choose>
		<c:when test="${userDto.role != 'administrator'}">
			<h2>管理者以外はこの画面にアクセスすることはできません。</h2>
			<s:form method="POST" action="/purchase">
				<input type="submit" name="return" value="商品一覧に戻る" />
			</s:form>
		</c:when>
		<c:when test="${userDto.role == 'administrator'}">
			<h2>商品の削除を完了しました</h2>
			<s:form method="POST" action="/purchase">
				<input type="submit" name="return" value="商品一覧に戻る" />
			</s:form>
		</c:when>
	</c:choose>
	<c:remove var="items"/>
	<c:remove var="targetItems"/>
	<c:remove var="canRemove"/>
</tiles:put>
</tiles:insert>