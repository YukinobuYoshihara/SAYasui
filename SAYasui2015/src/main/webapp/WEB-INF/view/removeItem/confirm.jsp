<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="【管理】商品削除の確認 | Super Agile YASUI家具オンラインショップ "/>
<tiles:put name="content" type="string">
	<html:errors/>
	<c:choose>
	<c:when test="${userDto.role != 'administrator'}">
		<h2>管理者以外はこの画面にアクセスすることはできません。</h2>
		<s:form method="POST" action="/purchase">
			<input type="submit" name="return" value="商品一覧に戻る" />
		</s:form>
		<!-- 管理者以外のアクセスを防ぐ機能 -->
	</c:when>
	<c:when test="${userDto.role == 'administrator'}">
		<c:if test="${canRemove == false}">
			<h2>削除対象がありません</h2>
			<p>戻るボタンを押して、商品一覧から選択しなおしてください</p>
			<s:form method="POST" action="/removeItem">
				<input type="submit" name="goindex" value="戻る" />
			</s:form>
		</c:if>
		<c:if test="${canRemove == true}">
			<h2>下記の商品を削除しますか？</h2>
			<s:form method="POST" action="/removeItem/complete">
				<c:if test="${canRemove == true}">
					<table class="itemlist">
						<tr bgcolor="#00ccff">
							<th>商品番号</th>
							<th>商品名</th>
							<th>商品画像</th>
							<th>サイズ</th>
							<th>価格</th>
							<th>在庫</th>
						</tr>
						<c:forEach var="targetItem" items="${targetItemList}"
							varStatus="status">
							<c:choose>
								<c:when test="${status.count%2==0}">
									<c:set var="bgcol" value="#FFffCC" />
								</c:when>
								<c:otherwise>
									<c:set var="bgcol" value="#EEeeAA" />
								</c:otherwise>
							</c:choose>
							<tr bgcolor="${bgcol}">
								<td><c:out value="${targetItem.itemId}" /></td>
								<td><c:out value="${targetItem.itemName}" /></td>
								<td><a href=<c:out value="${targetItem.imgurl}"/>>商品画像</a></td>
								<td><c:out value="${targetItem.itemSize}" /></td>
								<td><c:out value="${targetItem.price}" /></td>
								<td><c:out value="${targetItem.stock.stockNum}" /></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<input type="submit" name="goback" value="戻る" />
				<c:if test="${canRemove == true}">
					<input type="submit" name="goahead" value="削除する" />
				</c:if>
			</s:form>
		</c:if>
	</c:when>
	</c:choose>
</tiles:put>
</tiles:insert>