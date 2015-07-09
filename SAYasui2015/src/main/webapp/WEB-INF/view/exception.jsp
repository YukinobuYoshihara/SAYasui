<%@ page isErrorPage="true" %>
<%@ page contentType="text/html; charset=UTF-8" session="false" %>
<%
  // Java 例外オブジェクト
  Throwable exceptionObject =
    (Throwable)request.getAttribute("javax.servlet.error.exception");

  // エラーの発生したrequest URI
  String requestURI =
    (String)request.getAttribute("javax.servlet.error.request_uri");

  // Java例外タイプ
  String exceptionType="";
  Object exceptionTypeRaw =
    request.getAttribute("javax.servlet.error.exception_type");
  if(exceptionTypeRaw!=null){
  	exceptionType=exceptionTypeRaw.toString();
  }

  // HTTP status メッセージ
  String message =
    (String)request.getAttribute(
        "javax.servlet.error.message");

  // servlet 名
  String servletName =
    (String)request.getAttribute(
        "javax.servlet.error.servlet_name");

  // statusコード
  Integer statusCode =
    (Integer)request.getAttribute(
        "javax.servlet.error.status_code");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="imagetoolbar" content="no">
    <title>
      エラーが発生しました | Super Agile YASUI家具オンラインショップ
    </title>
    <style>
		body {background: #f9fee8;margin: 0; padding: 20px; text-align:center; font-family:Arial, Helvetica, sans-serif; font-size:14px; color:#666666;}
		.error_page {width: 800px; padding: 50px; margin: auto;}
		.error_page h1 {margin: 20px 0 0;}
		.error_page p {margin: 10px 0; padding: 0;}
		a {color: #9caa6d; text-decoration:none;}
		a:hover {color: #9caa6d; text-decoration:underline;}
		table#table01 {
    	width: 750px;
    	margin-left: auto;
 		margin-right: auto;
    	border: 1px #E3E3E3 solid;
    	border-collapse: collapse;
    	border-spacing: 0;
		}

		table#table01 th {
    	padding: 5px;
    	border: #E3E3E3 solid;
    	border-width: 0 0 1px 1px;
    	background: #F5F5F5;
    	font-weight: bold;
    	line-height: 120%;
    	text-align: center;
		}
		table#table01 td {
		text-align: left;
    	padding: 5px;
    	border: 1px #E3E3E3 solid;
    	border-width: 0 0 1px 1px;
    	text-align: center;
		}
    </style>
  </head>
  <body class="login">
    <div class="error_page">
      <img alt="sorry" src="${f:url('/img/sad.gif')}">
      <h1>
        We're sorry...
      </h1>
      <p>
        現在の画面において、致命的なエラーが発生しました。
        <br />
        しばらくたってもエラーが改善されない場合には、本画面のキャプチャを添付の上、
        <a href="mailto:foo@bar.com">
          YASUI家具ヘルプデスク
        </a>
        までご連絡ください。
      </p>
      <hr />
      <p>
        <a href="${f:url('/index')}">
          サイトのトップページに戻る
        </a>
      </p>
    </div>
    <c:if test="${initParam['mode.debug']=='true'}">
      <table id="table01">
        <tr>
          <th valign="top" nowrap>
            ステータス
          </th>
          <td>
             <%=statusCode %>：<%=message %>
            <br />
          </td>
        </tr>
        <tr>
          <th valign="top" nowrap>
            例外発生箇所（URI）
          </th>
          <td>
            <%=requestURI %>
            <br />
          </td>
        </tr>
        <tr>
          <th valign="top" nowrap>
            例外クラス（サーブレット名）
          </th>
          <td>
            <%=exceptionObject.toString() %>（<%=servletName %>）
            <br />
          </td>
        </tr>
      </table>
    </c:if>
  </body>
</html>
