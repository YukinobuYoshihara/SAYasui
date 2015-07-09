<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="【管理】商品追加の完了 | Super Agile YASUI家具オンラインショップ" />
	<tiles:put name="content" type="string">
		<html:errors />
		<c:if test="${userDto.role != 'administrator'}">
			<h2>管理者以外はこの画面にアクセスすることはできません。</h2>
			<s:form method="POST" action="/index">
				<input type="submit" name="return" value="商品一覧に戻る" />
			</s:form>
		</c:if>
		<c:if test="${userDto.role == 'administrator'}">
			<h2>商品の追加を完了しました</h2>
			<s:form method="POST" action="/addItem">
				<input type="submit" name="return" value="商品追加画面に戻る" />
			</s:form>
			<s:form method="POST" action="/purchase">
				<input type="submit" name="return" value="商品一覧に戻る" />
			</s:form>
		</c:if>
		<c:remove var="canAdd" />
		<c:remove var="itemDto" />
	</tiles:put>
</tiles:insert>