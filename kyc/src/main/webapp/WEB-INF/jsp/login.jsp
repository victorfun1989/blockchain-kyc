<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>区块链钥匙扫码</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://v3.bootcss.com/examples/signin/signin.css" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>  
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>  
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>  
    <![endif]-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script
	src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
	<div  align="center">
		<img src="qr_code.img?code_url= <%=com.cib.fintech.kyc.util.GenerateQrCodeUtil.getRandomString(1000).toString() %>" style="width:300px;height:300px;"/>
		<h2 class="form-signin-heading">区块链钥匙扫码登陆</h2>
	</div>
</body>
</html>
