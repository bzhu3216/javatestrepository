<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function paperDelete(paperId){
		if(confirm("确定要删除这个试卷吗?")){
			$.post("paper!deletePaper",{paperId:paperId},
				function(result){
					var result = eval('('+result+')');
					if(result.error){
						alert(result.error);
					}else{
						alert("删除成功！");
						window.location.href="${pageContext.request.contextPath}/paper!getAllPapers";
					}
				}
			);
		}
	}
</script>

<h2>试卷管理</h2>

<button class="btn-mini btn-primary"
	style="float: right; margin-bottom: 5px;" type="button"
	onclick="javascript:window.location='paper!preSave'">添加试卷</button>


<table class="table table-bordered table-hover">
	<tr>
		<th>序号</th>
		<th>试卷名称</th>
		<th>添加日期</th>
		<th>操作</th>
	</tr>
	<c:forEach var="paper" items="${paperList}" varStatus="status">
		<tr>
			<td>${status.index+1 }</td>
			<td>${paper.paperName }</td>
			<td><fmt:formatDate value="${paper.joinDate }" type="date"
					pattern="yyyy-MM-dd" /></td>
			<td><button class="btn-mini btn-info" type="button"
					onclick="javascript:window.location='paper!getDetailPaperById?paperId=${paper.id}'">查看</button>&nbsp;&nbsp;
				<button class="btn-mini btn-info" type="button"
					onclick="javascript:window.location='paper!preSave?paperId=${paper.id}'">修改</button>&nbsp;&nbsp;
				<button class="btn-mini btn-danger" type="button"
					onclick="paperDelete(${paper.id })">删除</button></td>
		</tr>
	</c:forEach>
</table>
