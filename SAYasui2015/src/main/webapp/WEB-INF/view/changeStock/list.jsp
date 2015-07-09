<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title"
		value="【管理】在庫の変更 | Super Agile YASUI家具オンラインショップ" />
	<tiles:put name="content" type="string">
		<html:errors />
		<c:if test="${userDto.role != 'administrator'}">
			<h2>管理者以外はこの画面にアクセスすることはできません。</h2>
			<s:form method="POST" action="/purchase">
				<input type="submit" name="return" value="商品一覧に戻る" />
			</s:form>
		</c:if>
		<c:if test="${userDto.role == 'administrator'}">
			<h2>変更したい在庫数量を指定してください</h2>
			<s:form method="POST" action="/changeStock/confirm">
				<table class="itemlist">
					<tr bgcolor="#00ccff">
						<th>商品番号</th>
						<th>商品名</th>
						<th>商品画像</th>
						<th>サイズ</th>
						<th>価格</th>
						<th>現在の在庫</th>
						<th>変更後の在庫</th>
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
							<td><c:out value="${item.itemId}" /></td>
							<td><c:out value="${item.itemName}" /></td>
							<td><a href=<c:out value="${item.imgurl}" />>商品画像</a></td>
							<td><c:out value="${item.itemSize}" /></td>
							<td><c:out value="${item.price}" /></td>
							<td><c:out value="${item.stock.stockNum}" /> 
								<input type="hidden" name="oldStock" value="${item.stock.stockNum}" />
								<input type="hidden" name="changeStockItems" value="${item.itemId}" />
							</td>
							<c:choose>
								<c:when test="${not empty item.newStock}">
									<td><input value=<c:out value="${item.newStock}"/>
										type="text" name="newStock" size="20" maxlength="8"
										style="text-align: right" /></td>
								</c:when>
								<c:when test="${not empty item.stock.stockNum}">
									<td><input value=<c:out value="${item.stock.stockNum}"/>
										type="text" name="newStock" size="20" maxlength="8"
										style="text-align: right" /></td>
								</c:when>
								<c:otherwise>
									<td><input value="0" type="text" name="newStock" size="20"
										maxlength="8" style="text-align: right" /></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" value="確認" />
			</s:form>
		</c:if>
	</tiles:put>
</tiles:insert>