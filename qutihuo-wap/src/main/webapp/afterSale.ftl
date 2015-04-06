<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <title>骏海水产大闸蟹预定系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    
    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
  </head>
  
  <body style="padding-top: 70px;">
	<div class="container">
		<div class="navbar navbar-default navbar-fixed-top">
			<a href="${BasePath}/dispatchCenter.jhtml" class="navbar-text col-xs-2" title="返回"><span class="glyphicon glyphicon-arrow-left"></span></a>
			<span class="navbar-text col-xs-8" style="text-align:center;"><strong>死蟹申请</strong></span>
			<a href="${BasePath}/logout.jhtml" class="navbar-text col-xs-2" title="退出"><span class="glyphicon glyphicon-log-out"></span></a>
      	</div>
      	
      	<#if afterSaleRequ??>
	      	<p><small><strong  class="label label-info">申请时间</strong> ${afterSaleRequ.createTime?string('yyyy-MM-dd HH:mm')}</small></p>
	      	<p><small><strong  class="label label-info">死蟹说明</strong> ${afterSaleRequ.description!''}</small></p>
	      	<p><small><strong  class="label label-info">处理状态</strong> <#if afterSaleRequ.state==1>待处理中<#elseif afterSaleRequ.state==2>已下补货单<#elseif afterSaleRequ.state==3>已拒绝</#if></small></p>
	      	<#if afterSaleRequ.state!=1>
	      	<p><small><strong  class="label label-info">处理时间</strong> ${afterSaleRequ.dealTime?string('yyyy-MM-dd HH:mm')}</small></p>
	      	<p><small><strong  class="label label-info">处理说明</strong> ${afterSaleRequ.dealInfo!''}</small></p>
			</#if>
			<p><small><strong  class="label label-info">死蟹遗照</strong></small></p>
			<div class="clearfix">
				<#if afterSaleRequ.pic1Url??>
				<div class="col-xs-6"><img src="${afterSaleRequ.pic1Url!''}" class="img-thumbnail" width="140px"//></div>
				</#if>
				<#if afterSaleRequ.pic2Url??>
				<div class="col-xs-6"><img src="${afterSaleRequ.pic2Url!''}" class="img-thumbnail" width="140px"//> </div>
			    </#if>
				<#if afterSaleRequ.pic3Url??>
				<div class="col-xs-6"><img src="${afterSaleRequ.pic3Url!''}" class="img-thumbnail" width="140px"/></div>
				</#if>
			</div>
			<br>
      	<#else>
	      	<form id="afterSaleRequForm" action="${BasePath}/savaAfterSaleRequ.jhtml" method="POST" enctype="multipart/form-data">
	      	  <input type="hidden" name="orderMainNo" id="orderMainNo" value="${orderMain.orderMainNo!""}">
			  <div class="form-group">
			    <textarea name="description" class="form-control" rows="4" placeholder="请至少输入20个说明文字"></textarea>
			  </div>
			  <p><small>至少上传2张照片,每张照片最大不超过2M</small></p>
			  <div class="form-group">
			    <input type="file"  name="pic1File" class="form-control" title="上传死蟹照片1"  data-filename-placement="inside">
			  </div>
			  <div class="form-group">
			  	<input type="file"  name="pic2File" class="form-control" title="上传死蟹照片2"  data-filename-placement="inside">
			  </div>
			  <div class="form-group">
			  	<input type="file"  name="pic3File" class="form-control" title="上传死蟹照片3"  data-filename-placement="inside">
			  </div>
			  <div class="form-group">
			      <button type="submit" class="btn btn-info btn-lg btn-block"><strong>提交申请</strong></button>
			  </div>
			</form>
      	</#if>
      	
      	<#include "/common/footer.ftl" >
	</div>
	
    <script src="${BasePath}/js/jquery-1.10.2.min.js"></script>
    <script src="${BasePath}/js/bootstrap.min.js"></script>
    <script src="${BasePath}/js/bootstrap-file-input.js"></script>
    <script src="${BasePath}/js/bootstrap-validator.min.js"></script>
    <script>
		$(document).ready(function() {
			$('input[type=file]').bootstrapFileInput();
			
			$('#afterSaleRequForm').bootstrapValidator({
				live: 'disabled',
		        fields: {
		        	description: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入死蟹描述信息'
		                    },
		                    stringLength:{
		                    	min: 10,
		                    	max: 250,
                        		message: '描述字数必须在10到250个字符之间'
		                    }
		                }
		            },
		            pic1File:{
		            	validators:{
		            		notEmpty: {
		                        message: '请上传图片1'
		                    },
		            		file:{
		            			extension: 'gif,jpg,jpeg,png',
                        		type: 'image/gif,image/jpg,image/jpeg,image/png',
                       	 		maxSize: 2028 * 1024,
                        		message: '图片格式必须为gif,jpg,jpeg,png'
		            		}
		            	}
		            },
		            pic2File:{
		            	validators:{
		            		notEmpty: {
		                        message: '请上传图片2'
		                    },
		            		file:{
		            			extension: 'gif,jpg,jpeg,png',
                        		type: 'image/gif,image/jpg,image/jpeg,image/png',
                       	 		maxSize: 2028 * 1024,
                        		message: '图片格式必须为gif,jpg,jpeg,png'
		            		}
		            	}
		            },
		            pic3File:{
		            	validators:{
		            		file:{
		            			extension: 'gif,jpg,jpeg,png',
                        		type: 'image/gif,image/jpg,image/jpeg,image/png',
                       	 		maxSize: 2028 * 1024,
                        		message: '图片格式必须为gif,jpg,jpeg,png'
		            		}
		            	}
		            }
		        }
		    });
		});
	</script>
  </body>
</html>