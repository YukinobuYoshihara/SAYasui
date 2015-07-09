<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
	<tiles:put name="title" value="【管理】商品の追加 | Super Agile YASUI家具オンラインショップ" />
	<tiles:put name="content" type="string">
	<html:errors/>
	<c:if test="${userDto.role != 'administrator'}">
		<h2>管理者以外はこの画面にアクセスすることはできません。</h2>
		<s:form method="POST" action="/purchase">
			<input type="submit" name="return" value="商品一覧に戻る" />
		</s:form>
	</c:if>
	<c:if test="${userDto.role == 'administrator'}">
		<s:form method="POST" action="/addItem/confirm">
			<fieldset>
				<legend>商品ID</legend>
				<input size=8 value="${nextId}" type="text" name="id" id="itemid" />
				（整数5桁まで）商品IDの最大値に1加算した値が、初期状態では表示されています
			</fieldset>
			<fieldset>
				<legend>商品名</legend>
				<input size=52 type="text" name="itemName" autofocus placeholder="商品名（色）" />
				（最大50文字まで）商品名（色）の形式で入力してください
			</fieldset>
			<fieldset>
				<legend>商品画像URL</legend>
				<input size=52 value="http://localhost:8080/SAYasui/img/"
					name="imgurl" placeholder="http://localhost:8080/SAYasui/img/nnnnn.jpg" />
				（最大50文字まで）http://ホスト名:ポート番号/SAYasui/img/画像名
			</fieldset>
			<fieldset>
				<legend>商品サイズ</legend>
				<input size=52 type="text" name="itemSize"placeholder="150x75x85" />
				 （最大50文字まで）たてxよこx高さ 単位：cm （例：150x75x85）
			</fieldset>
			<fieldset>
				<legend>商品価格</legend>
				<input size=10
					type="text" name="price" placeholder="99999999" />
				（整数8桁まで）通貨フォーマットで記述する必要はありません
			</fieldset>
			<fieldset>
				<legend>入庫数量</legend>
				<input size=10
					type="text" name="newStock" placeholder="99999999" /> （整数8桁まで）
			</fieldset>
			<input type="submit" name="goahead" value="確認" />
			<input type="reset" name="goback" value="リセット" />
		</s:form>
	</c:if>
	</tiles:put>
</tiles:insert>