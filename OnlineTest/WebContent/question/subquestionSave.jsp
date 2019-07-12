<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm() {
		var subject = $("#subject").val();
		var type = $("#type").val();
		var paperName = $("#paperName").val();
		var joinTime = $("#joinTime").val();
		var answer = $("#answer").val();
		if (subject == null || subject == "") {
			$("#error").html("考试题目不能为空！");
			return false;
		}
		if (type == null || type == "") {
			$("#error").html("请选择题目类型！");
			return false;
		}
		if (paperName == null || paperName == "") {
			$("#error").html("请选择试卷！");
			return false;
		}
		if (answer == null || answer == "") {
			$("#error").html("答案不能为空！");
			return false;
		}
		return true;
	}
</script>

<p>${title }</p>


<form action="subquestion!saveSubQuestion" method="post"
	onsubmit="return checkForm()">
	<table width="90%" align="center">
		<tr>
			<td><label>考试题目：</label></td>
			<td><input type="text" id="subject" name="subquestion.subject"
				value="${subquestion.subject }" class="input-xxlarge" /></td>
		</tr>
		<tr>
			<td><label>题目类型：</label></td>
			<td><select id="type" name="subquestion.type">
					<option value="">请选择题目类型...</option>
					<option value="3" ${subquestion.type==3?'selected':'' }>填空题</option>
					<option value="4" ${subquestion.type==4?'selected':'' }>简答题</option>
			</select></td>
		</tr>
		<tr>
			<td><label>所属试卷：</label></td>
			<td><select id="paperName" name="subquestion.paper.id">
					<option value="">请选择试卷...</option>
					<c:forEach var="paper" items="${paperList }">
						<option value="${paper.id }"
							${paper.id==subquestion.paper.id?'selected':'' }>${paper.paperName }</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td><input type="hidden" id="joinTime" name="subquestion.joinTime"
				class="Wdate" onClick="WdatePicker()"
				value="<fmt:formatDate value="${subquestion.joinTime }" type="date" pattern="yyyy-MM-dd"/>" /></td>
		</tr>
		<tr>
			<td><label>题目答案：</label></td>
			<td><input type="text" id="answer" name="subquestion.answer"
				value="${subquestion.answer }" /></td>
		</tr>
		<tr>
			<td><input type="hidden" id="subquestion" name="subquestionId"
				value="${subquestion.id }" /><input type="submit"
				class="btn btn-primary" value="保存" /></td>
			<td>

				<button class="btn btn-primary" type="button"
					onclick="javascript:history.back()">返回</button>&nbsp;&nbsp;<font
				id="error" color="red">${error }</font>
			</td>
		</tr>
	</table>
</form>
