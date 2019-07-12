<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	function checkForm() {
		var text = $("input[type='text']");
		var size1 = '${fquestionList.size() }';
		var size2 = '${rquestionList.size() }';
		for (var i = 1; i < size1 * 2; i += 2) {
			if (Number(text.eq(i).val()) > 5 || Number(text.eq(i).val()) < 0) {
				alert('填空题得分越界');
				return false;
			}
		}
		var size3 = Number(i) + Number(size2);
		for (var j = i; j < size3; j++) {
			if (Number(text.eq(j).val()) > 10 || Number(text.eq(j).val()) < 0) {
				alert('简答题得分越界');
				return false;
			}
		}
		return ture;
	}
</script>

<hr />
<h1 style="text-align: center;">${paper.paperName}&nbsp;&nbsp;考试卷(满分${fullScore })</h1>
<strong>一，单选题(满分${paper.sScore}，共${squestionList.size() }题)<font
	color="red">&nbsp;&nbsp;得分：${test.singleScore }</font></strong>
<br />
<br />
<form id="myForm" action="test!readOver" method="post"
	onsubmit="return checkForm()">
	<input type="hidden" name="testId" value="${testId}" />
	<c:forEach var="s" items="${squestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${s.subject }&nbsp;&nbsp;&nbsp;(${s.userAnswer })&nbsp;&nbsp;&nbsp;<font
			color="red">${s.answer }</font></strong>
		<br />
		<br />
		<label class="radio">A: ${s.optionA } </label>
		<label class="radio">B: ${s.optionB } </label>
		<label class="radio">C: ${s.optionC } </label>
		<label class="radio">D: ${s.optionD } </label>
		<br />
	</c:forEach>
	<strong>二，多选题(满分${paper.mScore}，共${mquestionList.size() }题)<font
		color="red">&nbsp;&nbsp;得分：${test.moreScore }</font></strong><br /> <br />
	<c:forEach var="m" items="${mquestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${m.subject }&nbsp;&nbsp;&nbsp;(${m.userAnswer })&nbsp;&nbsp;&nbsp;<font
			color="red">${m.answer }</font></strong>
		<br />
		<br />
		<label class="checkbox">A: ${m.optionA } </label>
		<label class="checkbox">B: ${m.optionB } </label>
		<label class="checkbox">C: ${m.optionC } </label>
		<label class="checkbox">D: ${m.optionD } </label>
		<br />
	</c:forEach>
	<strong>三，填空题(每题5分，满分${paper.fScore}，共${fquestionList.size() }题)</strong><br />
	<br />
	<c:forEach var="f" items="${fquestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${f.subject }</strong>&nbsp;&nbsp;
		<input type="text" style="width: 100px;" readonly="readonly"
			value=${f.userAnswer } />
		<strong style="margin-left: 100px; ="><font color="red">得分：</font><input
			type="text" style="width: 30px; color: red;" name="f-${f.id }" /></strong>
		<br />
		<br />
		<br />
	</c:forEach>
	<strong>四，简答题(每题10分，满分${paper.tScore}，共${rquestionList.size() }题)</strong><br />
	<br />
	<c:forEach var="r" items="${rquestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${r.subject }</strong>
		<br />
		<br />
		<textarea id="txtCode" rows="8" cols="50" style="width: 75%"
			readonly="readonly">${r.userAnswer }</textarea>
		<strong style="margin-left: 100px; ="><font color="red">得分：</font><input
			type="text" style="color: red; width: 30px;" name="t-${r.id }" /></strong>
		<br />
		<br />
	</c:forEach>
	<button class="btn btn-primary" type="submit">提交</button>
</form>