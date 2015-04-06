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
			<span class="navbar-text col-xs-8" style="text-align:center;"><strong>咨询留言</strong></span>
			<a href="${BasePath}/logout.jhtml" class="navbar-text col-xs-2" title="退出"><span class="glyphicon glyphicon-log-out"></span></a>
      	</div>
      	
      	<#list afterSaleConsList as afterSaleCons>
      		<p><small><strong  class="label label-info">客户留言</strong> ${afterSaleCons.consultation!""} ${afterSaleCons.createTime?datetime}</small></p>
      		<#if afterSaleCons?? && afterSaleCons.reply??>
      			<p><small><strong class="label label-primary">客服回复</strong> ${afterSaleCons.reply!""} ${afterSaleCons.replyTime?datetime}</small></p>
      		</#if>
      		<hr>
      	</#list>
      	
      	<form id="consultationForm" method="POST" action="${BasePath}/submitRemark.jhtml">
      	  <input type="hidden" name="orderMainNo" id="orderMainNo" value="${orderMain.orderMainNo!""}">
		  <div class="form-group">
		    <textarea name="consultation" id="consultation" class="form-control" rows="5"></textarea>
		  </div>
		  <div class="form-group">
		      <button type="submit" class="btn btn-info btn-lg btn-block"><strong>提交留言</strong></button>
		  </div>
		</form>
		
		<#include "/common/footer.ftl" >
	</div>
	
	
    <script src="http://ejunhai.qiniudn.com/jquery-1.10.2.min.js"></script>
    <script src="http://ejunhai.qiniudn.com/bootstrap.min.js"></script>
    <script src="http://ejunhai.qiniudn.com/bootstrap-validator.min.js"></script>
    <script>
		$(document).ready(function() {
			$('#consultationForm').bootstrapValidator({
				live: 'disabled',
		        fields: {
		        	consultation: {
		                validators: {
		                    notEmpty: {
		                        message: '请输入留言'
		                    },
		                    stringLength:{
		                    	min: 20,
		                    	max: 250,
                        		message: '留言字数必须在20到250个字符之间'
		                    }
		                }
		            }
		        }
		    });
		});
	</script>
  </body>
</html>