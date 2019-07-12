<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function subQuestionDelete(subquestionId){
		if(confirm("确定要删除这条记录吗?")){
			$.post("subquestion!deleteSubQuesion",{subquestionId:subquestionId},
				function(result){
					var result = eval('('+result+')');
					if(result.error){
						alert(result.error);
					}else{
						alert("删除成功！");
						window.location.href="${pageContext.request.contextPath}/subquestion!getAllSubQuestions";
					}
				}
			);
		}
	}
</script>
<h1>主观题信息管理</h1>
<form
	action="${pageContext.request.contextPath}/subquestion!getAllSubQuestions"
	method="post">
	<table align="center">
		<tr>
			<td><label>考试题目：</label></td>
			<td><input type="text" id="subject"
				name="selSubQuestion.subject" value="${selSubQuestion.subject }" /></td>
			<td><button class="btn btn-primary" style="margin-bottom: 8px;"
					type="submit">查询</button></td>
		</tr>
	</table>
</form>
<button class="btn-mini btn-primary"
	style="float: right; margin-bottom: 5px;" type="button"
	onclick="javascript:window.location='subquestion!preSave'">添加主观题</button>

<h2>主观题</h2>
<table class="table table-bordered table-hover">
	<tr>
		<th>序号</th>
		<th>考试题目</th>
		<th>加入时间</th>
		<th>题目类型</th>
		<th>所属试卷</th>
		<th>操作</th>
	</tr>
	<c:forEach var="subquestion" items="${subquestionList}"
		varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td>${subquestion.subject }</td>
			<td><fmt:formatDate value="${subquestion.joinTime }" type="date"
					pattern="yyyy-MM-dd" /></td>
			<c:choose>
				<c:when test="${subquestion.type==3 }">
					<td>填空题</td>
				</c:when>
				<c:otherwise>
					<td>简答题</td>
				</c:otherwise>
			</c:choose>
			<td>${subquestion.paper.paperName }</td>
			<td><button class="btn-mini btn-info" type="button"
					onclick="javascript:window.location='subquestion!getSubQuestionById?subquestionId=${subquestion.id}'">查看试题</button>&nbsp;&nbsp;
				<button class="btn-mini btn-info" type="button"
					onclick="javascript:window.location='subquestion!preSave?subquestionId=${subquestion.id}'">修改</button>&nbsp;&nbsp;
				<button class="btn-mini btn-danger" type="button"
					onclick="subQuestionDelete(${subquestion.id })">删除</button></td>
		</tr>
	</c:forEach>
</table>
<div class="pagination pagination-centered">
	<ul>${pageCode }
	</ul>
</div>