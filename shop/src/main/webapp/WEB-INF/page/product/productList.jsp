
<%@ page pageEncoding="GBK" contentType="text/html; charset=UTF-8" import="java.util.*,java.text.*" %>
<%@ include file="/static/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>导出下载导出选数据</title>

</head>
<body>

	<div class="padding_div" >
   		<div class="nrq">
   			<div class="padding10_div search_form">
				<form class="layui-form" id="info_list_form" onsubmit="return false;">
					<div class="layui-form-item">
						
						<div class="layui-inline">
			      			<label class="layui-form-label layui-form-label70" >名称：</label>
				      		<div class="layui-input-inline">
					        	<input type="text" name="proName" id="proName" class="layui-input" />
				      		</div>
				      		
				      		
							<div class="layui-input-inline">
					    		<button class="layui-btn  layui-btn-sm" data-type="reload">查询</button>
							</div>
							
				    		<div class="layui-input-inline">
					    		<button class="layui-btn  layui-btn-sm" data-type="excelPro" >导出选中行</button>
							</div>
							<div class="layui-input-inline">
					    		<button class="layui-btn  layui-btn-sm" data-type=""  onclick="exportData1()">导出所有数据</button>
							</div>
							<div class="layui-input-inline">
					    		<button class="layui-btn  layui-btn-sm" data-type="isAll" >是否选中</button>
							</div>
							<div class="layui-input-inline">
								<a href="javascript:void(0);" class="file">选择文件
							   		<input type="file" name="postLoanReport" id="postLoanReport" class="layui-input"/>
								</a>  
					    		<button type="button" class="layui-btn" id="" name="" onclick="postLoanReportSubmit();">
									<i class="layui-icon">&#xe67c;</i>点我你试试
								</button>
							</div>
				    	</div>
						 
				  	</div>
	 							
				  	<div class="layui-form-item">
						<!-- 
						<div class="layui-inline">
							<div class="btn_group btn_group234">
					    		<button class="layui-btn layui-btn-normal layui-btn-mini" data-type="reload">查询</button>
							</div>
				    	</div>
				    	  -->
				  	</div>
				</form>
			</div>
			<div class="sperator_div" ></div>	
			<table class="layui-table" id="LAY_info_table" ></table>
		</div>
	</div>
	
	<script type="text/html" id="memberLongnameTpl">
		<a href="javascript:void(0);" onclick="info({{d.crmId}})">{{ d.memberLongname }}</a>
	</script>
	<script>
	layui.use('table', function() {
		var laytpl = layui.laytpl;
		var searchFormHeight = layui.jquery(".search_form").height();
		searchFormHeight = 20 + searchFormHeight + 10 + 20 + 2 + 1;

		var table = layui.table;
		// 方法级渲染
		table.render({
			elem : '#LAY_info_table',
			
			url : '../produ/getProductList',		
			cols : [ [ {
				type  : 'checkbox',
				fixed : 'left'
			},{
				field : 'id',
				title : 'id',
				fixed : true,
				width : 80,
				align : 'center',
				sort  : true
			}, {
				field : 'proName',
				title : '名称',
				width : 120
				
			}, {
				field : 'proQty',
				title : '数量',
				width : 80
			}, {
				field : 'proType',
				title : '物品类型',
				width : 100,
				align : 'center'
			}, {
				field : 'proState',
				title : '状态',
				width : 100
			}, {
				field : 'price',
				title : '价格',
				width : 80
			}, {
				field : 'crDate',
				title : '上线时间',
				width : 85,
				align : 'center'
			}, {
				field : 'upDate',
				title : '修改时间',
				width : 120,
				align : 'center'
			}, {
				field : 'disCount',
				title : '折扣',
				width : 80,
				align : 'center'
			}, {
				field : 'descs',
				title : '描述',
				width : 380,
				align : 'center'
			} ] ],
			id : 'infoTableReload',
			page : true,
			even : true,
			response : {
				// 成功的状态码，默认：0
				statusCode : 200
			}
		});
		
		table.on('checkbox(LAY_info_table)',function(obj){
			console.log(obj);
		});
		
		var $ = layui.$;
		var active = {
			reload : function() {
				var demoReload = $('#info_list_form').serialize();
				// 执行重载
				table.reload('infoTableReload', {
					page : {
						curr : 1
					// 重新从第 1 页开始
					},
					where : DataDeal.fromJsonStrToJsonObj(DataDeal
							.formStrToJsonStr(demoReload))
				});
			},
			//获取选中行
			excelPro : function(){
				var checkStatus = table.checkStatus('infoTableReload');
				var check = checkStatus.data;
				//var json1 = {"id":"1","proName":"test1"}
			
				var json = JSON.stringify(check);
				$.ajax({
					type : 'POST',
					url	: '../produ/excelPro',
					dataType : 'json',
					data : json,
					contentType : 'application/json',
					success : function(result){
						if(result.flag=="flag"){
							layer.msg(true);
						}else{
							layer.msg("false");
						}
					}	
				})
			
				
					
			},
			//验证是否全选
			isAll : function(){
				var checkStatus = table.checkStatus('infoTableReload');
				
				layer.msg(checkStatus.isAll ? 'true' : 'false')
			}
		};

		$('.search_form .layui-btn').on('click', function() {
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});

	});
	
	function postLoanReportSubmit(){
		var fileName = $("#postLoanReport").val();
		if(fileName == ""){
			layer.alert("请选择文件");
			return;
		}
		$.ajaxFileUpload({
			url : "../produ/importProduct",
			secureuri:false,
			fileElementId : 'postLoanReport',
			dataType : 'json',
			data : {} ,
			type : 'post',
			success : function(){
				if ("true" == data.result) {
					layer.alert("上传成功！",{
						offset: '200px',closeBtn:0
					});
				} else if ("false" == data.result) {
					layer.alert("上传失败！",{
						offset: '200px',closeBtn:0
					});
				}
			},
			error:function(data, status, e){
				
			}
		})
	}
	
	function exportData1(){
		var url = "../produ/exportData1";
		window.location.href=url;
	}

	</script>
</body>
</html>