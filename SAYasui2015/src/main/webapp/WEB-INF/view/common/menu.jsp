<c:if test="${sessionScope.userDto.role == 'administrator' || sessionScope.userDto.role == 'user'}">
	<div id=nav>
		<ul>
			<li>顧客ID［${f:h(sessionScope.userDto.name)}
				（${f:h(sessionScope.userDto.descript)}）］</li>

			<c:if test="${sessionScope.userDto.role == 'administrator'}">
				<li><s:link href="/addItem">商品追加</s:link></li>
				<li><s:link href="/changeStock">在庫数量変更</s:link></li>
				<li><s:link href="/removeItem">商品削除</s:link></li>
			</c:if>
			<li><s:link href="/putchase">商品一覧</s:link></li>
			<li><s:link href="/logout">ログアウト</s:link></li>
		</ul>
	</div>
	<%-- ログイン中の画面はグローバルナビゲーションの下にhrを入れる --%>
	<hr>
</c:if>