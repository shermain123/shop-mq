
<%@ page pageEncoding="GBK" contentType="text/html; charset=UTF-8" import="java.util.*,java.text.*" %>
<%@ include file="/static/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>�������ص���ѡ����</title>

</head>
<body>

	<div class="padding_div" >
   		<div class="nrq">
   			<div class="padding10_div search_form">
				<form class="layui-form" id="info_list_form" onsubmit="return false;">
					<div class="layui-form-item">
						
						<div class="layui-inline">
			      			<label class="layui-form-label layui-form-label70" >���ƣ�</label>
				      		<div class="layui-input-inline">
					        	<input type="text" name="proName" id="proName" class="layui-input" />
				      		</div>
				      		
				      		
							<div class="layui-input-inline">
					    		<button class="layui-btn  layui-btn-sm" data-type="reload">��ѯ</button>
							</div>
							
				    		<div class="layui-input-inline">
					    		<button class="layui-btn  layui-btn-sm" data-type="excelPro" >����ѡ����</button>
							</div>
							<div class="layui-input-inline">
					    		<button class="layui-btn  layui-btn-sm" data-type=""  onclick="exportData1()">������������</button>
							</div>
							<div class="layui-input-inline">
					    		<button class="layui-btn  layui-btn-sm" data-type="isAll" >�Ƿ�ѡ��</button>
							</div>
							<div class="layui-input-inline">
								<a href="javascript:void(0);" class="file">ѡ���ļ�
							   		<input type="file" name="postLoanReport" id="postLoanReport" class="layui-input"/>
								</a>  
					    		<button type="button" class="layui-btn" id="" name="" onclick="postLoanReportSubmit();">
									<i class="layui-icon">&#xe67c;</i>����������
								</button>
							</div>
				    	</div>
						 
				  	</div>
	 							
				  	<div class="layui-form-item">
						<!-- 
						<div class="layui-inline">
							<div class="btn_group btn_group234">
					    		<button class="layui-btn layui-btn-normal layui-btn-mini" data-type="reload">��ѯ</button>
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
		// ��������Ⱦ
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
				title : '����',
				width : 120
				
			}, {
				field : 'proQty',
				title : '����',
				width : 80
			}, {
				field : 'proType',
				title : '��Ʒ����',
				width : 100,
				align : 'center'
			}, {
				field : 'proState',
				title : '״̬',
				width : 100
			}, {
				field : 'price',
				title : '�۸�',
				width : 80
			}, {
				field : 'crDate',
				title : '����ʱ��',
				width : 85,
				align : 'center'
			}, {
				field : 'upDate',
				title : '�޸�ʱ��',
				width : 120,
				align : 'center'
			}, {
				field : 'disCount',
				title : '�ۿ�',
				width : 80,
				align : 'center'
			}, {
				field : 'descs',
				title : '����',
				width : 380,
				align : 'center'
			} ] ],
			id : 'infoTableReload',
			page : true,
			even : true,
			response : {
				// �ɹ���״̬�룬Ĭ�ϣ�0
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
				// ִ������
				table.reload('infoTableReload', {
					page : {
						curr : 1
					// ���´ӵ� 1 ҳ��ʼ
					},
					where : DataDeal.fromJsonStrToJsonObj(DataDeal
							.formStrToJsonStr(demoReload))
				});
			},
			//��ȡѡ����
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
			//��֤�Ƿ�ȫѡ
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
			layer.alert("��ѡ���ļ�");
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
					layer.alert("�ϴ��ɹ���",{
						offset: '200px',closeBtn:0
					});
				} else if ("false" == data.result) {
					layer.alert("�ϴ�ʧ�ܣ�",{
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