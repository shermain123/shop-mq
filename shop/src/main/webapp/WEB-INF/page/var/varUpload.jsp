<%@ page pageEncoding="GBK" contentType="text/html; charset=UTF-8" import="java.util.*,java.text.*" %>

<%@ include file="/static/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图片上传</title>
</head>
<body>
	<button type="button" class="layui-btn" id="import" name ="import">
		<i class="layui-icon">&#xe67c</i>上传图片
	</button>
	
<script type="text/javascript">
	layui.use('upload',function(){
		var upload = layui.upload;
		
		//上传图片
		var uploadInst = upload.render({
			elem : '#import',
			url	 : '../vat/uploadImg',
			accept:'images',
			done : function(data){
				if(data.result == 'true'){
					layer.alert("上传成功!");
				}else{
					layer.alert("上传失败!");
				}
			},
			error: function(){
				
			}
		})
	})
</script>
</body>
</html>