<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="商品一覧 | Super Agile YASUI家具オンラインショップ"/>
<tiles:put name="content" type="string">
	<html:errors/>
	<h2>商品一覧</h2>
	<s:form method="POST">
		<table class="itemlist">
			<tr bgcolor="#00ccff">
				<th>商品番号</th>
				<th>商品名</th>
				<th>商品画像</th>
				<th>サイズ</th>
				<th>価格</th>
				<th>在庫</th>
				<th>注文数</th>
			</tr>
			<c:forEach var="itemDto" items="${itemDtoList}" varStatus="status">
				<c:choose>
					<c:when test="${status.count%2==0}">
						<c:set var="bgcol" value="#FFffCC" />
					</c:when>
					<c:otherwise>
						<c:set var="bgcol" value="#EEeeAA" />
					</c:otherwise>
				</c:choose>
				<tr bgcolor="${bgcol}">
					<td><c:out value="${itemDto.itemId}" /></td>
					<td><c:out value="${itemDto.itemName}" /></td>
					<td><a href=<c:out value="${itemDto.imgurl}"/>>商品画像</a></td>
					<td><c:out value="${itemDto.itemSize}" /></td>
					<td><fmt:formatNumber value="${itemDto.price}" type="CURRENCY" groupingUsed="true" currencySymbol="\\" maxFractionDigits="0" minFractionDigits="0" /></td>
					<td><c:out value="${itemDto.stock.stockNum}" /></td>
					<td>
						<!-- 注文数量の処理 -->
						<c:choose>
							<%--stockの数が0以下またはstockの数をorderが上回る場合 --%>
							<c:when test="${itemDto.stock.stockNum <=0}">現在注文できません</c:when>
							<c:otherwise>
								<c:choose>
								<c:when test="${not empty itemDto.order}">
									<input value='<c:out value="${itemDto.order}"/>' type="text" name="quantity" size="20" maxlength="8" style="text-align: right" />
								</c:when>
								<c:otherwise>
									<input value="0" type="text" name="quantity" size="20" maxlength="8" style="text-align: right" />
								</c:otherwise>
								</c:choose>
								<input type="hidden" name="availableitem" value="${itemDto.itemId}" />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" name="confirm" value="確認" />
	</s:form>
</tiles:put>
</tiles:insert>