<%@ page pageEncoding="GBK" contentType="text/html; charset=UTF-8" import="java.util.*,java.text.*" %>

<%@ include file="/static/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>POI Excel导入、导出</title>
</head>
<body>
	<form action="" class="layui-form" id="info_list_form" onsubmit="return false;">
		<button class="layui-btn  layui-btn-sm" data-type=""  onclick="testDate()">模版下载</button>
		<a href="javascript:void(0);" class="file">选择文件
			<input type="file" name="file" id="LoanReport" class="layui-input" />
		</a>
		<button type="button" class="layui-btn"  onclick="importExcel()">点我你试试</button>
	</form>
<script type="text/javascript">
	layui.use(['form','layer','element','upload'], function(){
		var form = layui.form;
		var layer = layui.layer;
		var element = layui.element;
		var upload = layui.upload;
		
	})
	function testDate(){
		window.location.href="../vat/exportModel";
	}
	function importExcel(){
		var fileName = $("#LoanReport").val();
		if(fileName == ""){
			layer.alert("未选择文件");
			return ;
		}
		$.ajaxFileUpload({
			url : "../vat/importEx",
			secureuri:false,
			fileElementId : 'LoanReport',
			dataType : 'json',
			data : {} ,
			type : 'post',
			enctype:'multipart/form-data',
			success : function(result){
				if(result.flag=="true"){
					layer.alert("导入成功！");
				}else{
					layer.alert("导入失败！");
				}
			},
			error : function(){
				
			}
			
		})
	}
</script>
</body>
</html>