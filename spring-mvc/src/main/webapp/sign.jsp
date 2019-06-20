<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>商户签名测试</title>

<style type="text/css">
textarea {
	width: 100%;
	margin-bottom: 5px;
	margin-top: 5px;
}

input {
	width: 100%;
	height: 100%;
	margin-bottom: 5px;
	margin-top: 5px;
}
</style>
</head>
<body>
	<form id="appForm" name="appForm">
	<label>客户端上送参数:</label></br>
		<label>payment_token_data:</label><textarea id="payment_token_data" rows="5">xxxxxxxxxxxxxxx</textarea>	
		<button type="button" class="btn btn-default" id="button1">
			<span>&nbsp;接收报文并向API开发平台发起请求</span>
		</button>
	</br><label>返回报文:</label></br>
		<textarea id="appText" rows="50"></textarea>
	</form>
	<script src="js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript">
	
		$(function() {
			$('#button1').on('click', function() {
				var str = "";
				$.ajax({
					type : "POST",
					data : {
						payment_token_data : $('#payment_token_data').val()
						},
					dataType : 'json',
					url : "/sdk-test-demo/copSign",
					success : function(data) {
						str = "成功接收商户报文，开始向API开放平台发起请求!\n";
						$('#appText').val(str);
						
						var inputData = "{\"app_id\":\"" + data.app_id+"\",";
						inputData += "\"biz_content\":\"" + data.biz_content+"\",";
						inputData += "\"ca\":\"" + data.ca+"\",";
						inputData += "\"charset\":\"" + data.charset+"\",";
						inputData += "\"format\":\"" + data.format+"\",";
						inputData += "\"payment_token_data\":\"" + data.payment_token_data+"\",";
						inputData += "\"msg_id\":\"" + data.msg_id+"\",";
						inputData += "\"notify_url\":\"" + data.notify_url+"\",";
						inputData += "\"sign_type\":\"" + data.sign_type+"\",";
						inputData += "\"timestamp\":\"" + data.timestamp+"\",";
						inputData += "\"sign\":\"" + data.sign+"\"}";
						str += "请求的URL：\n"+data.service_url+"\n请求报文：\n"+inputData;						
						$('#appText').val(str);
						
						var inputObj = {
								app_id:data.app_id,
								biz_content:data.biz_content,
								ca:data.ca,
								charset:data.charset,
								format:data.format,
								payment_token_data:data.payment_token_data,
								msg_id:data.msg_id,
								notify_url:data.notify_url,
								sign_type:data.sign_type,
								timestamp:data.timestamp,
								sign:data.sign
						};
						
						$.ajax({
							type : "POST",
							dataType : 'text',
							data : inputObj,
							url : data.service_url,
							success : function(data2) {
								str+="\nAPIP返回报文："+data2;
								$('#appText').val(str);					
							}
						});
					}
				});
			});
		});
	</script>
</body>
</html>