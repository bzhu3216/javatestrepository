<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2 style="text-align: center;">${currentUser.name }&nbsp;成绩列表</h2>

<table class="table table-bordered table-hover">
	<tr>
		<th>序号</th>
		<th>试卷名称</th>
		<th>考试日期</th>
		<th>单选题得分</th>
		<th>多选题得分</th>
		<th>客观题总分</th>
		<th>填空題题得分</th>
		<th>简答题得分</th>
		<th>总分</th>
		<th align="center">操作</th>
	</tr>
	<c:forEach var="test" items="${testList}" varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td>${test.paper.paperName }</td>
			<td><fmt:formatDate value="${test.testDate }" type="date"
					pattern="yyyy-MM-dd" /></td>
			<td>${test.singleScore }</td>
			<td>${test.moreScore }</td>
			<td>${test.score }</td>
			<td>${test.fScore==-1?"待批阅":test.fScore }</td>
			<td>${test.sScore==-1?"待批阅":test.sScore }</td>
			<td>${test.totalScore==-1?"待批阅":test.totalScore }</td>
			<td><button class="btn-mini btn-info" type="button"
					onclick="javascript:window.location='test!getOwnTestById?testId=${test.id}'">试题分析</button>&nbsp;&nbsp;
			</td>
		</tr>
	</c:forEach>
</table>
<div class="pagination pagination-centered">
	<ul>${pageCode }
	</ul>
</div>