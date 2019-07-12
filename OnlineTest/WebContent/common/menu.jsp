<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function userLogout() {
		if (confirm('您确定要退出系统吗？')) {
			window.location.href = 'user!logout';
		}
	}

	function adminLogout() {
		if (confirm('您确定要退出系统吗？')) {
			window.location.href = 'admin!logout';
		}
	}
</script>
<div class="navbar">
	<div class="navbar-inner">
		<a class="brand" href="Main.jsp">首页</a>
		<ul class="nav">
			<c:choose>
				<c:when test="${currentUser.flag==1}">
					<li><a
						href="${pageContext.request.contextPath}/user!getAllUsers">考生信息管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/test!getAllTests">考生成绩查询</a></li>
					<li><a
						href="${pageContext.request.contextPath}/paper!getAllPapers">试卷管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/question!questionManage">题目管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/test!getStatisticsErrors">错题统计</a></li>
					<li><a href="javascript:adminLogout()">退出系统</a></li>
				</c:when>
				<c:otherwise>
					<li><a
						href="${pageContext.request.contextPath}/paper!getPapers">在线考试</a></li>
					<li><a
						href="${pageContext.request.contextPath}/test!getTests?s_test.user.id=${currentUser.id}">成绩查询</a></li>
					<li><a
						href="${pageContext.request.contextPath}/user!preUpdatePassword">修改密码</a></li>
					<li><a href="javascript:userLogout()">退出系统</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>