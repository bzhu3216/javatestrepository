<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	var examTime = 120 * 60;
	var useTime = 0, remainTime = examTime;

	// 显示使用时间和剩余时间
	function showCount() {
		if (remainTime == 0) {
			document.getElementById("myForm").submit();
		}
		useTime += 1;
		remainTime -= 1;
		var hourU = Math.floor(useTime / 3600);
		var minuteU = Math.floor((useTime - hourU * 3600) / 60);
		var secondU = Math.floor(useTime - hourU * 3600 - minuteU * 60);
		document.getElementById("useTime").innerHTML = format(hourU) + ":"
				+ format(minuteU) + ":" + format(secondU);

		var hourR = Math.floor(remainTime / 3600);
		var minuteR = Math.floor((remainTime - hourR * 3600) / 60);
		var secondR = Math.floor(remainTime - hourR * 3600 - minuteR * 60);
		document.getElementById("remainTime").innerHTML = format(hourR) + ":"
				+ format(minuteR) + ":" + format(secondR);
	}

	// 格式化日期数字
	function format(timeNumber) {
		if (timeNumber == 0) {
			return "00";
		} else if (timeNumber < 10) {
			return "0" + timeNumber;
		} else {
			return timeNumber;
		}
	}

	window.setInterval("showCount()", 1000);
</script>
<script type="text/javascript">
	function checkForm() {
		var unradio = $("input[type='radio']");
		var uncheckbox = $("input[type='checkbox']");
		var Flag = true;
		for (var i = 0; i < unradio.size() / 4; i++) {
			Flag=false;
			for (var j = 0 + i * 4; j < 4 + i * 4; j++) {
				if (unradio.eq(j).is(':checked')) {
					Flag=true;
					break;
				}
			}
			if(!Flag){
				alert('单选题未完成，请完成后交卷!');
				return false;
			}		
		}
		for (var i = 0; i < uncheckbox.size() / 4; i++) {
			Flag=false;
			for (var j = 0 + i * 4; j < 4 + i * 4; j++) {
				if (uncheckbox.eq(j).is(':checked')) {
					Flag=true;
					break;
				}
			}
			if(!Flag){
				alert('多选题未完成，请完成后交卷!');
				return false;
			}		
		}
		return Flag;
	}
</script>
<p>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;考试时间：<strong>120分钟</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	计时：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font id="useTime"
		style="font-weight: bold;"></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	剩余时间：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font id="remainTime"
		style="font-weight: bold;"></font>
</p>
<hr />
<h1 style="text-align: center;">${paper.paperName}&nbsp;&nbsp;考试卷(满分${fullScore })</h1>
<strong><big>一，单选题(满分${paper.sScore}，共${squestionList.size() }题)</big></strong>
<br />
<br />
<form id="myForm" action="test!testEnd" method="post"
	onsubmit="return checkForm()">
	<input type="hidden" name="test.user.id" value="${currentUser.id }" />
	<input type="hidden" name="test.paper.id" value="${paper.id }" />
	<c:forEach var="s" items="${squestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${s.subject }</strong>
		<br />
		<br />
		<label class="radio"> <input type="radio"
			name="id-r-${s.id }-${s.level}" value="A"> ${s.optionA }
		</label>
		<label class="radio"> <input type="radio"
			name="id-r-${s.id }-${s.level}" value="B"> ${s.optionB }
		</label>
		<label class="radio"> <input type="radio"
			name="id-r-${s.id }-${s.level}" value="C"> ${s.optionC }
		</label>
		<label class="radio"> <input type="radio"
			name="id-r-${s.id }-${s.level}" value="D"> ${s.optionD }
		</label>
		<br />
	</c:forEach>
	<strong><big>二，多选题(满分${paper.mScore}，共${mquestionList.size() }题)</big></strong><br />
	<br />
	<c:forEach var="m" items="${mquestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${m.subject }</strong>
		<br />
		<br />
		<label class="checkbox"> <input type="checkbox"
			name="id-c-${m.id }-${m.level}" value="A"> ${m.optionA }
		</label>
		<label class="checkbox"> <input type="checkbox"
			name="id-c-${m.id }-${m.level}" value="B"> ${m.optionB }
		</label>
		<label class="checkbox"> <input type="checkbox"
			name="id-c-${m.id }-${m.level}" value="C"> ${m.optionC }
		</label>
		<label class="checkbox"> <input type="checkbox"
			name="id-c-${m.id }-${m.level}" value="D"> ${m.optionD }
		</label>
		<br />
	</c:forEach>
	<strong><big>三，填空题(每题5分，满分${paper.fScore}，共${fquestionList.size() }题)</big></strong><br />
	<br />
	<c:forEach var="f" items="${fquestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${f.subject }</strong>&nbsp;&nbsp;
		<input type="text" style="width: 100px;" name="id-f-${f.id }" />
		<br />
		<br />
		<br />
	</c:forEach>
	<strong><big>四，简答题(每题10分，满分${paper.tScore}，共${rquestionList.size() }题)</big></strong><br />
	<br />
	<c:forEach var="r" items="${rquestionList }" varStatus="status">
		<strong>[&nbsp;${status.index+1}&nbsp;]&nbsp;${r.subject }</strong>
		<br />
		<br />
		<textarea id="txtCode" rows="8" cols="50" style="width: 75%"
			name="id-t-${r.id }"></textarea>
		<br />
		<br />
		<br />
	</c:forEach>
	<button class="btn btn-primary" type="submit">交卷</button>
</form>