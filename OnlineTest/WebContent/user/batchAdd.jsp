<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">
	<div class="data_info">
		<p>${title }</p>
	</div>
	<div class="data_content">
		<form action="user!batchAdd" method="post">
			<input type="file" name="uploadFile" id="fileField"/>
				<!-- onchange="document.getElementById('fileField').value=this.value" /-->
			<input type="submit" value="上传" />
		</form>
	</div>
</div>
<script type="text/javascript">
	if ('${error}' != '') {
		alert('${error}');
	}
</script>
