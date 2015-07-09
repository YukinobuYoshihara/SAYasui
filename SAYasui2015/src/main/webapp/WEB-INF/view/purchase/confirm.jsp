<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="商品発注の確認 | Super Agile YASUI家具オンラインショップ" />
	<tiles:put name="content" type="string">
		<html:errors />
		<c:choose>
			<c:when test="${canOrder == false}">
				<h2>注文情報が正しくありません</h2>
				<p>戻るボタンを押して、商品一覧から発注しなおしてください</p>
				<s:form method="POST" action="/purchase">
					<input type="submit" name="goindex" value="戻る" />
				</s:form>
			</c:when>
			<c:otherwise>
				<h2>下記の内容で発注しますか？</h2>
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
							<th>小計</th>
						</tr>
						<c:set var="sum" value='<%=0%>' />
						<c:forEach var="itemDto" items="${orderItems}" varStatus="status" >
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
								<td><a href=<c:out value="${itemDto.imgurl}" />>商品画像</a></td>
								<td><c:out value="${itemDto.itemSize}" /></td>
								<td><fmt:formatNumber value="${itemDto.price}" type="CURRENCY" groupingUsed="true" currencySymbol="\\" maxFractionDigits="0" minFractionDigits="0" /></td>
								<td><c:out value="${itemDto.stock.stockNum}" /></td>
								<td><c:out value="${itemDto.order}" /></td>
								<td><fmt:formatNumber value="${itemDto.order * itemDto.price}" type="CURRENCY" groupingUsed="true" currencySymbol="\\" maxFractionDigits="0" minFractionDigits="0" />
								<c:set var="sum" value="${sum+(itemDto.order * itemDto.price)}" />
							</tr>
						</c:forEach>
						<tr>
							<td colspan="7">合計</td>
							<td><fmt:formatNumber value="${sum}" type="CURRENCY" groupingUsed="true" currencySymbol="\\" maxFractionDigits="0" minFractionDigits="0" />
						</tr>
					</table>
					<input type="submit" name="goback" value="戻る" />
					<input type="submit" name="complete" value="発注する" />
				</s:form>
			</c:otherwise>
		</c:choose>
	</tiles:put>
</tiles:insert>