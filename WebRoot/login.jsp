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

		<title>登陆页面</title>

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
			  ${errormsg }
				<h1 class="txt2">
					登录界面：
				</h1>
				
					<form action="user_login.action" method="post">
					<table class="form">
						<tr>
							<td>
								用户名：
							</td>
							<td>
								<input type="text" name="user.username" />
							</td>
						</tr>
						<tr>
							<td>
								密&nbsp;&nbsp;码：
							</td>
							<td>
								<input type="password" name="user.password" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="登录" />
							</td>
						</tr>
						</table>
					</form>
				
			</div>
		</div>



	</body>
</html>
