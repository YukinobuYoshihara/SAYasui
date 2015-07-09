<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="【管理】在庫変更の確認 | Super Agile YASUI家具オンラインショップ"/>
<tiles:put name="content" type="string">
	<html:errors/>
	<c:if test="${userDto.role != 'administrator'}">
		<h2>管理者以外はこの画面にアクセスすることはできません。</h2>
		<s:form method="POST" action="/purchase">
			<input type="submit" name="return" value="商品一覧に戻る" />
		</s:form>
	</c:if>
	<c:if test="${userDto.role == 'administrator'}">
		<c:if test="${canChange == false}">
			<h2>在庫変更ができませんでした</h2>
			<p>戻るボタンを押して、商品一覧から変更してください</p>
			<c:set var="destination" value="/changeStock" />
			<s:form method="POST" action="${destination}">
				<input type="submit" name="goback" value="戻る" />
			</s:form>
		</c:if>
		<c:if test="${canChange == true}">
			<h2>下記の内容で在庫を変更しますか？</h2>
			<s:form method="POST" action="/changeStock/complete">
			<table class="itemlist">
				<tr bgcolor="#00ccff">
					<th>商品番号</th>
					<th>商品名</th>
					<th>商品画像</th>
					<th>サイズ</th>
					<th>価格</th>
					<th>旧在庫</th>
					<th>新在庫</th>
				</tr>
				<c:forEach var="item" items="${newStockList}" varStatus="status">
					<c:choose>
						<c:when test="${status.count%2==0}">
							<c:set var="bgcol" value="#FFffCC" />
						</c:when>
						<c:otherwise>
							<c:set var="bgcol" value="#EEeeAA" />
						</c:otherwise>
					</c:choose>
					<tr bgcolor="${bgcol}">
						<td><c:out value="${item.itemId}" /></td>
						<td><c:out value="${item.itemName}" /></td>
						<td><a href=<c:out value="${item.imgurl}" />>商品画像</a></td>
						<td><c:out value="${item.itemSize}" /></td>
						<td><c:out value="${item.price}" /></td>
						<td><c:out value="${item.stock.stockNum}" /></td>
						<td><c:out value="${item.newStock}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
			<input type="submit" name="goback" value="戻る" />
			<input type="submit" name="goahead" value="変更する" />
		</s:form>
		</c:if>
	</c:if>
</tiles:put>
</tiles:insert>