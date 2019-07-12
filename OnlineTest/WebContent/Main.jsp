<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线考试系统主界面</title>
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/bootstrap/css/login.css">
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script
	src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="http://sandbox.runjs.cn/uploads/rs/146/73fi1rab/amcharts.js"></script>
<style>
	
</style>
</head>
<%
	String mainPage = (String) request.getAttribute("mainPage");
	if (mainPage == null || mainPage.equals("")) {
		mainPage = "common/default.jsp";
	}
%>
<body
	style="background:url('${pageContext.request.contextPath}/bootstrap/img/banner-background.png') no-repeat;background-size:100%">
	<div>
		<h1 style="color: #A67D3D">在线考试系统（OnLineTest）</h1>
	</div>
	<table width="1000px;" align="center">
		<tr>
			<td><jsp:include page="common/head.jsp" /></td>
		</tr>
		<tr>
			<td><jsp:include page="common/menu.jsp" /></td>
		<tr>
			<td><jsp:include page="<%=mainPage%>" /></td>
		</tr>
	</table>
</body>
</html>