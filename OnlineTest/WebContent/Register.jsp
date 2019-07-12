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
<script type="text/javascript">
	function checkForm() {
		var name = $("#name").val();
		var sex = $("#sex").val();
		var cardNo = $("#cardNo").val();
		var prefession = $("#prefession").val();
		var password = $("#password").val();
		if (name == null || name == "") {
			$("#error").html("姓名不能为空！");
			return false;
		}
		if (sex == null || sex == "") {
			$("#error").html("请选择性别！");
			return false;
		}
		if (cardNo == null || cardNo == "") {
			$("#error").html("身份证不能为空！");
			return false;
		}
		if (prefession == null || prefession == "") {
			$("#error").html("专业不能为空！");
			return false;
		}
		if (password == null || password == "") {
			$("#error").html("密码不能为空！");
			return false;
		}
		return true;
	}
</script>
</head>
<body
	style="background:url('${pageContext.request.contextPath}/bootstrap/img/login-bg.jpg') no-repeat;background-size:100%">
	<div>
		<h1 style="color: #A67D3D">在线考试系统（OnLineTest）</h1>
	</div>

	<form action="user!register" method="post"
		onsubmit="return checkForm()">
		<fieldset>
			<legend style="font-size: 30px; color: #42426F;">考生注册</legend>
		</fieldset>
		<table width="80%" align="center">
			<tr>
				<td><label>姓名：</label></td>
				<td><input type="text" id="name" name="user.name"
					value="${user.name }" /></td>
				<td>&nbsp;</td>

			</tr>
			<tr>
				<td><label>身份证：</label></td>
				<td colspan="4"><input type="text" id="cardNo"
					name="user.cardNo" value="${user.cardNo }" class="input-xlarge" /></td>
			</tr>
			<tr>
				<td><label>性别：</label></td>
				<td><select id="sex" name="user.sex">
						<option value="">请选择性别...</option>
						<option value="男" ${user.sex=='男'?'selected':'' }>男</option>
						<option value="女" ${user.sex=='女'?'selected':'' }>女</option>
				</select></td>
			</tr>
			<tr>
				<td><label>专业：</label></td>
				<td><input type="text" id="prefession" name="user.prefession"
					value="${user.prefession }" /></td>
				<td>&nbsp;</td>

			</tr>
			<tr>
				<td><label>密码：</label></td>
				<td><input type="text" id="password" name="user.pwd"
					value="${user.password }" /></td>
			</tr>
			<tr>
				<td><input type="hidden" id="id" name="user.id"
					value="${user.id }" /><input type="submit" class="btn btn-primary"
					value="注册" /></td>
				<td colspan="4">
					<a class="button red"
						style="margin-left: 20px; margin-top: 20px; font-size: 20px;"
						href="UserLogin.jsp">已有账号？返回登录</a><font
					id="error" color="red">${error }</font>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>