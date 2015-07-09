<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="【管理】削除可能な商品一覧 | Super Agile YASUI家具オンラインショップ" />
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
		<h2>商品一覧</h2>
		<p>削除したい商品のチェックボックスをチェックしてください</p>
		<s:form method="POST" action="/removeItem/confirm">
			<table class="itemlist">
				<tr bgcolor="#00ccff">
					<th>削除対象</th>
					<th>商品番号</th>
					<th>商品名</th>
					<th>商品画像</th>
					<th>サイズ</th>
					<th>価格</th>
					<th>在庫</th>
				</tr>
				<c:forEach var="item" items="${itemDtoList}" varStatus="status">
					<c:choose>
						<c:when test="${status.count%2==0}">
							<c:set var="bgcol" value="#FFffCC" />
						</c:when>
						<c:otherwise>
							<c:set var="bgcol" value="#EEeeAA" />
						</c:otherwise>
					</c:choose>
					<tr bgcolor="${bgcol}">
						<td><html:multibox property="targetItemList" value="${item.itemId}"/></td>
						<td><c:out value="${item.itemId}" /></td>
						<td><c:out value="${item.itemName}" /></td>
						<td><a href=<c:out value="${item.imgurl}" />>商品画像</a></td>
						<td><c:out value="${item.itemSize}" /></td>
						<td><c:out value="${item.price}" /></td>
						<td><c:out value="${item.stock.stockNum}" /></td>
					</tr>
				</c:forEach>
			</table>
			<input type="submit" value="確認" />
		</s:form>
	</c:when>
	</c:choose>
	</tiles:put>
</tiles:insert>