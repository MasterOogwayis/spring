<%@ page language="java" contentType="text/html; charset=gbk"
	pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>接口测试</title>

<style type="text/css">
textarea {
	width: 100%;
	height: 600px;
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

	<form action="/sdk-test-demo/cop" method="POST">
	 <input type="hidden" name="charset" value="gbk" />
		<label>APP id:</label> <input name="appid" value="10000000000000002118" />
		<label>网关公钥:</label><textarea style="width:100%;height:20px;" name="pub" >MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwFgHD4kzEVPdOj03ctKM7KV+16bWZ5BMNgvEeuEQwfQYkRVwI9HFOGkwNTMn5hiJXHnlXYCX+zp5r6R52MY0O7BsTCLT7aHaxsANsvI9ABGx3OaTVlPB59M6GPbJh0uXvio0m1r/lTW3Z60RU6Q3oid/rNhP3CiNgg0W6O3AGqwIDAQAB</textarea>
		<label>应用私钥:</label><textarea style="width:100%;height:110px;" name="pri" >MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMetYGodFXpw5dR1n2BA93BWqQ3YnOq3bIAvyF9mpP/opq4Ft9GSP9v2aaC6VawqG/kn4Ljll2+t9TuU9lLhhQWfvZhjjLQiXgKqS2oyhcmwL2wMp9JPefR6zIkAvWugxuGOlSm5/rbCeRozCKXJ9idt+agYvobtH6aQljgqAbqlAgMBAAECgYBVNBEpFoH8vkID97DrRK2RHRmEFANZUK2WwUzyEH2lyWOebEg0wABJ3wrRj9FQ5qMLHU4R97435HX5V6YIVb0JmP7FFmGFediJrheS/hH9OqoYAkJPj48f/0w91GnVlwQOTfJVBg39/A6eKS/oBpf2PmbMaJ7mRS0hdXWx5wa9YQJBAPCIlzaWSo19OSYZVgdsMI1Xur3FOaX9m6qKF0TL8eKqT99rAHzVqjY4s9LqsPQ7gUarSg/DcHuKahgAF/akq/0CQQDUhECrIXUOxHfWAVmD86j3kTUeIFyvLGW3mtZHa9dPYmnYPk0Cp7LKHF3Ybm4NMfMJhIRJfQQBmEBWgOl14cXJAkEAq1109dP/S2xyob0dOG6Q7LIa/gacOrFDDS4Sw9XU/9q8yvytvUJwYO7m1paT+XzHbnSscCyaX7nOOkkeyX6qdQJAFgzi6N6/1phsFrhtIVPW5FkcudOBymtA4pweP6rg3EvJz7YnJ29fDbmDw0EyCgx0uzgUwctG7yNtCYy2rJSQgQJBAMpNfNcHfdV4WJx5KbkbUlGHnKIJ7bV5iXiC+16jLABDTmtZK9Lbuw8y45Yi2INB/5NvXU+l+2fKK1egYToByEE=</textarea>
		<label>请求实现类:</label>
		<input name="request_class_name"
			value="com.icbc.api.request.GoldPurchaseRequest" /> <label>响应实现类:</label>
		<input name="response_class_name"
			value="com.icbc.api.response.GoldPurchaseResponse" /> <label>API请求路径:</label>
		<input name="service_url"
			value="http://122.19.77.226:8081/api/preciousmetal/gold/V1/purchase" /> <label>biz_content:</label>
		<textarea name="biz_content"></textarea>
		<input type="submit" value="submit" />
	</form>

</body>
</html>