<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<hr />
<h1 style="text-align: center;">${paper.paperName}&nbsp;&nbsp;考试卷(满分${fullScore })</h1>
<strong><big>一，单选题(满分${paper.sScore}，共${squestionList.size() }题)</big></strong>
<br />
<br />
	<input type="hidden" name="test.user.id" value="${currentUser.id }" />
	<input type="hidden" name="test.paper.id" value="${paper.id }" />
	<c:forEach var="s" items="${squestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${s.subject }</strong>
		<br />
		<br />
		<label class="radio"> <input type="radio" readonly="readonly" disabled="disabled"
			name="id-r-${s.id }-${s.level}" value="A"> ${s.optionA }
		</label>
		<label class="radio"> <input type="radio" readonly="readonly" disabled="disabled"
			name="id-r-${s.id }-${s.level}" value="B"> ${s.optionB }
		</label>
		<label class="radio"> <input type="radio" readonly="readonly" disabled="disabled"
			name="id-r-${s.id }-${s.level}" value="C"> ${s.optionC }
		</label>
		<label class="radio"> <input type="radio" readonly="readonly" disabled="disabled"
			name="id-r-${s.id }-${s.level}" value="D"> ${s.optionD }
		</label>
		<br />
	</c:forEach>
	<strong><big>二，多选题(满分${paper.mScore}，共${mquestionList.size() }题)</big></strong><br /> <br />
	<c:forEach var="m" items="${mquestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${m.subject }</strong>
		<br />
		<br />
		<label class="checkbox"> <input type="checkbox" readonly="readonly" disabled="disabled"
			name="id-c-${m.id }-${m.level}" value="A"> ${m.optionA }
		</label>
		<label class="checkbox"> <input type="checkbox" readonly="readonly" disabled="disabled"
			name="id-c-${m.id }-${m.level}" value="B"> ${m.optionB }
		</label>
		<label class="checkbox"> <input type="checkbox" readonly="readonly" disabled="disabled" 
			name="id-c-${m.id }-${m.level}" value="C"> ${m.optionC }
		</label>
		<label class="checkbox"> <input type="checkbox" readonly="readonly" disabled="disabled"
			name="id-c-${m.id }-${m.level}" value="D"> ${m.optionD }
		</label>
		<br />
	</c:forEach>
	<strong><big>三，填空题(每题5分，满分${paper.fScore}，共${fquestionList.size() }题)</big></strong><br /> <br />
	<c:forEach var="f" items="${fquestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${f.subject }</strong>&nbsp;&nbsp;
		<input text="text" style="width: 100px;" readonly="readonly"/>
		<br />
		<br />
		<br />
	</c:forEach>
	<strong><big>四，简答题(每题10分，满分${paper.tScore}，共${rquestionList.size() }题)</big></strong><br /> <br />
	<c:forEach var="r" items="${rquestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${r.subject }</strong>
		<br />
		<br />
		<input text="text" style="width: 1000px; height: 200px;"
			readonly="readonly" />
		<br />
		<br />
		<br />
	</c:forEach>
	<button class="btn btn-primary" type="button"
				onclick="javascript:history.back()">返回</button>