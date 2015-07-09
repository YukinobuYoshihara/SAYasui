<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="【管理】追加商品の確認 | Super Agile YASUI家具オンラインショップ" />
	<tiles:put name="content" type="string">
		<html:errors />
		<c:if test="${userDto.role != 'administrator'}">
			<h2>管理者以外はこの画面にアクセスすることはできません。</h2>
			<s:form method="POST" action="/index">
				<input type="submit" name="goback" value="商品一覧に戻る" />
			</s:form>
		</c:if>
		<c:if test="${userDto.role == 'administrator'}">
			<c:if test="${canAdd == false}">
				<h2>追加する商品情報が正しくありません</h2>
				<p>戻るボタンを押して、入力しなおしてください</p>
			</c:if>
			<s:form method="POST" action="/addItem/complete">
				<c:if test="${canAdd == true}">
					<table class="itemlist">
						<tr bgcolor="#00ccff">
							<th>商品番号</th>
							<th>商品名</th>
							<th>商品画像</th>
							<th>サイズ</th>
							<th>価格</th>
							<th>在庫</th>
						</tr>
						<c:set var="bgcol" value="#FFffCC" />
						<tr bgcolor="${bgcol}">
							<td><c:out value="${itemDto.itemId}" /></td>
							<td><c:out value="${itemDto.itemName}" /></td>
							<td><a href=<c:out value="${itemDto.imgurl}" />>商品画像</a></td>
							<td><c:out value="${itemDto.itemSize}" /></td>
							<td><c:out value="${itemDto.price}" /></td>
							<td><c:out value="${itemDto.newStock}" /></td>
						</tr>
					</table>
				</c:if>
				<input type="submit" name="goback" value="戻る" />
				<c:if test="${canAdd == true}">
					<input type="submit" name="goahead" value="追加する" />
				</c:if>
			</s:form>
		</c:if>
		<%--roleチェックのc:if --%>
	</tiles:put>
</tiles:insert>