<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function testDelete(testId){
		if(confirm("确定要删除这条记录吗?")){
			$.post("test!delTest",{testId:testId},
				function(result){
					var result = eval('('+result+')');
					if(result.error){
						alert(result.error);
					}else{
						alert("删除成功！");
						window.location.href="${pageContext.request.contextPath}/test!getAllTests";
					}
				}
			);
		}
	}
</script>
<h2 style="text-align: center;">考生成绩列表</h2>
<form action="${pageContext.request.contextPath}/test!getAllTests"
	method="post">
	<table align="center">
		<tr>
			<td><label>准考证号：</label></td>
			<td><input type="text" id="s_id" name="s_test.user.id"
				value="${s_test.user.id }" /></td>
			<td>&nbsp;</td>
			<td><label>姓名：</label></td>
			<td><input type="text" id="s_name" name="s_test.user.name"
				value="${s_test.user.name }" /></td>
			<td>&nbsp;</td>
			<td><button class="btn btn-primary" style="margin-bottom: 8px;"
					type="submit">查询</button></td>
			<td><button class="btn btn-primary"
					style="margin-bottom: 8px; margin-left: 20px;" type="button"
					onclick="javascript:window.location='test!getAllGrades'">成绩统计</button></td>
		</tr>
	</table>
</form>
<table class="table table-bordered table-hover">
	<tr>
		<th>序号</th>
		<th>准考证号</th>
		<th>考生姓名</th>
		<th>试卷名称</th>
		<th>考试日期</th>
		<th>单选题得分</th>
		<th>多选题得分</th>
		<th>客观题总分</th>
		<th>填空题得分</th>
		<th>简答题得分</th>
		<!-- 		<th>主观题总分</th> -->
		<th>总分</th>
		<th align="center">操作</th>
	</tr>
	<c:forEach var="test" items="${testList}" varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td>${test.user.id }</td>
			<td>${test.user.name }</td>
			<td>${test.paper.paperName }</td>
			<td><fmt:formatDate value="${test.testDate }" type="date"
					pattern="yyyy-MM-dd" /></td>
			<td>${test.singleScore }</td>
			<td>${test.moreScore }</td>
			<td>${test.score }</td>
			<td>${test.fScore==-1?"待批阅":test.fScore }</td>
			<td>${test.sScore==-1?"待批阅":test.sScore }</td>
			<%-- 			<td>${test.tScore }</td> --%>
			<td>${test.totalScore==-1?"待批阅":test.totalScore }</td>
			<td><button class="btn-mini btn-info" type="button"
					onclick="javascript:window.location='test!getDetailTestById?testId=${test.id}'">批改试卷</button>&nbsp;&nbsp;
				<button class="btn-mini btn-info" type="button"
					onclick="testDelete(${test.id})">删除</button>&nbsp;&nbsp;</td>
		</tr>
	</c:forEach>
</table>
<div class="pagination pagination-centered">
	<ul>${pageCode }
	</ul>
</div>