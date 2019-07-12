<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2 style="text-align: center;">&nbsp;错题统计</h2>

<table class="table table-bordered table-hover">
	<tr>
		<th>客观题总数</th>
		<th>难题总数</th>
		<th>中等题总数</th>
		<th>简单題总数</th>
		<th>难题错误数</th>
		<th>中等题错误数</th>
		<th>简单題错误数</th>
		<th>难题错误率</th>
		<th>中等题错误率</th>
		<th>简单題错误率</th>
	</tr>
	<tr>
		<td>${statistics_Error.totalCount }</td>
		<td>${statistics_Error.difficultCount }</td>
		<td>${statistics_Error.middleCount }</td>
		<td>${statistics_Error.easyCount }</td>
		<td>${statistics_Error.diffiErrorCount }</td>
		<td>${statistics_Error.middErroCount }</td>
		<td>${statistics_Error.easyErroCount }</td>
		<td>${statistics_Error.difficultPercent==null?0:statistics_Error.difficultPercent }</td>
		<td>${statistics_Error.middlePercent==null?0:statistics_Error.middlePercent }</td>
		<td>${statistics_Error.easyPercent==null?0:statistics_Error.easyPercent }</td>
	</tr>
	<tr>
		<td colspan="5"><button class="btn-mini btn-info" type="button"
				onclick="javascript:window.location='test!getDifficultyDiagram'">题目难度分布扇形图</button>
		</td>
		<td colspan="5"><button class="btn-mini btn-info" type="button"
				onclick="javascript:window.location='test!getErrorDiagram'">错题分布扇形图</button>
		</td>
	</tr>
</table>