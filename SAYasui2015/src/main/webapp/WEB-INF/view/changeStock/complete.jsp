<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="【管理】在庫変更の完了 | Super Agile YASUI家具オンラインショップ"/>
<tiles:put name="content" type="string">
	<c:if test="${userDto.role != 'administrator'}">
		<h2>管理者以外はこの画面にアクセスすることはできません。</h2>
		<s:form method="POST" action="/purchase">
			<input type="submit" name="return" value="商品一覧に戻る" />
		</s:form>
	</c:if>
	<c:if test="${userDto.role == 'administrator'}">
		<h2>在庫数の変更を完了しました</h2>
		<c:if test="${not empty sessionScope.errormessage}">
			<c:forEach var="message" items="${errormessage}"
				varStatus="statusError">
				<span class="errormsg">(Error)：${message}</span>
				<br />
			</c:forEach>
			<c:remove var="errormessage"/><%--表示が終わったエラーメッセージはセッションから削除する --%>
		</c:if>
		<s:form method="POST" action="/purchase">
			<input type="submit" name="return" value="商品一覧に戻る" />
		</s:form>
	</c:if>
	<c:remove var="changeStock"/>
	<c:remove var="stockitems"/>
	<c:remove var="canChange"/>
</tiles:put>
</tiles:insert>