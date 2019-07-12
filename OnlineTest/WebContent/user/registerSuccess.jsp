<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考生注册</title>
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/bootstrap/css/login.css">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</head>
<body
	style="background:url('${pageContext.request.contextPath}/bootstrap/img/login-bg.jpg') no-repeat;background-size:100%">
	<div>
		<h1 style="color: #A67D3D">在线考试系统（OnLineTest）</h1>
	</div>
<script>
	alert('注册成功！您的准考证号为'+'${user.id}');
	window.location.href = 'user!logout';
</script>
</body>
</html>
