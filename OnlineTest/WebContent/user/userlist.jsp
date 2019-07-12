<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function userDelete(userId) {
		if (confirm("确定要删除这条记录吗?")) {
			$
					.post(
							"user!delUser",
							{
								id : userId
							},
							function(result) {
								var result = eval('(' + result + ')');
								if (result.error) {
									alert(result.error);
								} else {
									alert("删除成功！");
									window.location.href = "${pageContext.request.contextPath}/user!getAllUsers";
								}
							});
		}
	}
</script>
<div class="data_list">
	<div class="data_info">
		<p>考生信息管理</p>
	</div>
	<div class="search_content">
		<form
			action="${pageContext.request.contextPath}/user!getUsersByIdOrName"
			method="post">
			<table align="center">
				<tr>
					<td><label>准考证号：</label></td>
					<td><input type="text" id="s_id" name="selUser.id"
						value="${selUser.id }" /></td>
					<td>&nbsp;</td>
					<td><label>姓名：</label></td>
					<td><input type="text" id="s_name" name="selUser.name"
						value="${selUser.name }" /></td>
					<td>&nbsp;</td>
					<td><button class="btn btn-primary"
							style="margin-bottom: 8px;" type="submit">查询</button></td>
				</tr>
			</table>
		</form>
		<button class="btn-mini btn-primary"
			style="float: right; margin-bottom: 5px;" type="button"
			onclick="javascript:window.location='user!preSave'">添加考生信息</button>
		<button class="btn-mini btn-primary"
			style="float: right; margin-right: 5px;" type="button"
			onclick="javascript:window.location='user!preBatchAdd'">批量添加</button>
	</div>
	<div class="data_content">
		<table class="table table-bordered table-hover">
			<tr>
				<th>序号</th>
				<th>准考证号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>身份证</th>
				<th>密码</th>
				<th>专业</th>
				<th>操作</th>
			</tr>
			<c:forEach var="user" items="${userlist }" varStatus="status">
				<tr>
					<td>${status.index+1 }</td>
					<td>${user.id }</td>
					<td>${user.name }</td>
					<td>${user.sex }</td>
					<td>${user.cardNo }</td>
					<td>${user.pwd }</td>
					<td>${user.prefession }</td>
					<td><button class="btn-mini btn-info" type="button"
							onclick="javascript:window.location='user!preSave?id=${user.id}'">修改</button>&nbsp;&nbsp;
						<button class="btn-mini btn-danger" type="button"
							onclick="userDelete('${user.id }')">删除</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagination pagination-centered">
		<ul>${pageCode }</ul>
	</div>
</div>