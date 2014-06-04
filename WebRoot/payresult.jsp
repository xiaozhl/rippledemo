<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>支付结果</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/fun.js"></script>
	</head>

	<body>
		<div id="main">

			<%@include file="head.jsp"%>
   
			<div id="content">
			 
				<h1 class="txt2">
					支付结果：
				</h1>
				<c:choose>
				 <c:when test="${result.success == true }">
				  <span>支付成功</span>
				 </c:when>
				 <c:otherwise>
				  <span>支付失败</span>
				  <p>原因：${result.errormsg }</p>
				 </c:otherwise>
				</c:choose>
					
				
			</div>
		</div>



	</body>
</html>
