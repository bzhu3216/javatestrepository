<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>题目信息管理</h2>

<table>
	<tr>
		<td colspan="2"><button type="button"
				style="width: 350px; font-size: 20px; text-decoration: none; margin-top: 5px; text-align: center; border-radius: 3px; border: 1px solid #959595;"
				onclick="javascript:window.location='question!getAllQuestions'">客观题管理（单选题，多选题）</button>&nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td><button type="button"
				style="width: 350px; font-size: 20px; text-decoration: none; margin-top: 5px; text-align: center; border-radius: 3px; border: 1px solid #959595;"
				onclick="javascript:window.location='subquestion!getAllSubQuestions'">主观题管理（包括填空題，简答题）</button>&nbsp;&nbsp;</td>
	</tr>

</table>