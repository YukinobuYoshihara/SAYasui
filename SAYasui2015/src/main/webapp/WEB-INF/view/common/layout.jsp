<!doctype html>
<html>
<head>
<title><tiles:getAsString name="title" /></title>
<script type="text/javascript"
	src="${f:url('/js/jquery-1.8.2.min.js')}"></script>
<script type="text/javascript"
	src="${f:url('/js/jquery-ui-1.8.18.custom.min.js')}"></script>
<link rel="stylesheet" type="text/css" href="${f:url('/css/site.css')}" />
</head>
<body>
<tiles:insert page="/WEB-INF/view/common/header.jsp" />
<tiles:insert page="/WEB-INF/view/common/menu.jsp" />
<tiles:insert attribute="content" />
<tiles:insert page="/WEB-INF/view/common/footer.jsp" />
</body>
</html>