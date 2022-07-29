<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>测试页面</title>
</head>
<body>
	<h1>1. 把功能抽象出去,在service方法当中调用</h1>
	<a href="${pageContext.request.contextPath}/user?method=login">点击访问login方法</a>
	<hr>
	<a href="${pageContext.request.contextPath}/user?method=logout">点击访问logout方法</a>
	<hr>
	<a href="${pageContext.request.contextPath}/user?method=hehe">点击访问hehe「不存在的方法」方法</a>
	<hr>
	<a href="${pageContext.request.contextPath}/user?">点击访问啥也不传</a>
	<hr>
	<a href="${pageContext.request.contextPath}/goods?">goodsController没有传递方法名称</a>
</body>
</html>
