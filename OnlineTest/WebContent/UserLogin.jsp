<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考生登录</title>
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/bootstrap/css/login.css">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	function checkForm() {
		var id = document.getElementById("id").value;
		var password = document.getElementById("password").value;
		if (id == null || id == "") {
			alert("准考证号不能为空！");
			return false;
		}
		if (password == null || password == "") {
			alert("登录密码不能为空！");
			return false;
		}
		return true;
	}

	function resetValue() {
		document.getElementById("id").value = "";
		document.getElementById("password").value = "";
	}
</script>
</head>
<body
	style="background:url('${pageContext.request.contextPath}/bootstrap/img/login-bg.jpg') no-repeat;background-size:100%">
	<div>
		<h1 style="color: #A67D3D">在线考试系统（OnLineTest）</h1>
	</div>
	<div class="main-container">
		<div class="form">
			<form action="user!login" method="post" onsubmit="return checkForm()"
				style="margin-left: 10%;">
				<fieldset>
					<legend class="logo">
						<span>考生登录</span> <a style="margin-left: 10px;"
							href="AdminLogin.jsp">管理员登录</a>
					</legend>
					<input type="text" id="id" name="user.id" value="${user.id }"
						placeholder="请输入准考证号" /> <br /> <input type="password"
						id="password" name="user.pwd" value="${user.pwd }"
						placeholder="请输入密码" /><br /> <input type="submit"
						class="button red" value="登录"></input>
					<button class="button" type="button" onclick="resetValue()"
						style="margin-left: 20px;">重置</button>
					<a class="button red"
						style="margin-left: 20px; margin-top: 20px; font-size: 20px;"
						href="Register.jsp">考生注册</a>
				</fieldset>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		if ('${error}' != '') {
			alert('${error}');
		}
	</script>
</body>
</html>