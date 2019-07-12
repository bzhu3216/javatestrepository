<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2 style="text-align: center;">考生成绩统计</h2>

<table class="table table-bordered table-hover">
	<tr>
		<th>序号</th>
		<th>试卷名称</th>
		<th>总考试次数</th>
		<th>及格数</th>
		<th>优秀数</th>
		<th>及格率</th>
		<th>优秀率</th>
		<th>平均分</th>
		<th>最高分</th>
		<th>最低分</th>
	</tr>
	<c:forEach var="grade" items="${grades}" varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td>${grade.paper.paperName }</td>
			<td>${grade.totalCount }</td>
			<td>${grade.passCount }</td>
			<td>${grade.excellentCount }</td>
			<td>${grade.passPercent }</td>
			<td>${grade.excellentPercent }</td>
			<td>${grade.averageScore }</td>
			<td>${grade.maxScore }</td>
			<td>${grade.minScore }</td>
		</tr>
	</c:forEach>
</table>
<div class="pagination pagination-centered">
	<ul>${pageCode }
	</ul>
</div>