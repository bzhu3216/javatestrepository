<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<p>查看试题</p>
<table width="90%" align="center">
	<tr>
		<td><label>考试题目：</label></td>
		<td><input type="text" id="subject" value="${subquestion.subject }"
			readonly="readonly" class="input-xxlarge" /></td>
	</tr>
	<tr>
		<td><label>题目类型：</label></td>
		<td><c:choose>
				<c:when test="${subquestion.type==3 }">
					<input type="text" id="type" value="填空题" readonly="readonly" />
				</c:when>
				<c:otherwise>
					<input type="text" id="type" value="简答题" readonly="readonly" />
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td><label>所属试卷：</label></td>
		<td><input type="text" id="paperName" readonly="readonly"
			value="${subquestion.paper.paperName }" /></td>
	</tr>
	<tr>
		<td><label>加入时间：</label></td>
		<td><input type="text" id="joinTime" readonly="readonly"
			value="<fmt:formatDate value="${subquestion.joinTime }" type="date" pattern="yyyy-MM-dd"/>" /></td>
	</tr>
	<tr>
		<td><label>题目答案：</label></td>
		<td><input type="text" id="answer" readonly="readonly"
			value="${subquestion.answer }" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<button class="btn btn-primary" type="button"
				onclick="javascript:history.back()">返回</button>
		</td>
	</tr>
</table>

