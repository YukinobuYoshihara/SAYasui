<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="トップページ | YASUI家具オンラインショップ"/>
<tiles:put name="content" type="string">
	<html:errors/>
	<s:form method="POST" action="login">
		<table class="noborder">
			<tr>
				<th>顧客ID：</th>
				<td><input type="text" name="username" size="20" maxlength="30"
					value="${sessionScope.username}" style="ime-mode: disabled" /></td>
			</tr>
			<tr>
				<th>パスワード：</th>
				<td><input type="password" name="password" size="20"
					maxlength="30" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit" value="ログイン">
				</td>
			</tr>
		</table>
	</s:form>
</tiles:put>
</tiles:insert>