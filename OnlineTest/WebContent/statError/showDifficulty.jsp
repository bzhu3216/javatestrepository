<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	var chart;
	var legend;
	var difficultCount = '${statistics_Error.difficultCount}';
	var middleCount = '${statistics_Error.middleCount}';
	var easyCount = '${statistics_Error.easyCount}';
	var chartData = [ {
		country : "难题",
		value : difficultCount
	}, {
		country : "中等题",
		value : middleCount
	}, {
		country : "简单题",
		value : easyCount
	} ];
	AmCharts.ready(function() {
		// 饼图
		chart = new AmCharts.AmPieChart();
		chart.dataProvider = chartData;
		chart.titleField = "country";
		chart.valueField = "value";
		chart.outlineColor = "";
		// 3D
		chart.depth3D = 20;
		chart.angle = 30;
		// 图形写入
		chart.write("chartdiv");
	});
</script>
<div id="chartdiv"
	style="margin-left: -220px; text-align: center; width: 90%; height: 390px;"></div>
<button class="btn btn-primary" type="button"
	onclick="javascript:history.back()">返回</button>
