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

		<title>订单操作结果</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="css/main.css">

	</head>

	<body>
		<div id="main">

			<%@include file="head.jsp"%>
 
			<div id="content">
			  ${errormsg }
				<h1 class="txt2">
					订单操作结果：
				</h1>
				 <c:if test="${not empty orders_0}">
				  下面的订单库存为0 ，所以没有保存成功，请下次再来！！！<br/>
				  <table class="cartlst">
				   <tr><td>书籍ID</td><td>书籍名称</td><td>库存</td></tr>
				   <c:forEach items="${orders_0}" var="order">
				    <tr><td>${order.book.bookid }</td><td>${order.book.title }</td><td>${order.book.count }</td></tr>
				   </c:forEach>
				   </table>
				 </c:if>
				 <c:if test="${not empty orders_1}">
				  下面的订单库存不足，所以只生成了部分订单，已经没货了，欢迎下次再来！！
				   <table class="cartlst">
				   <tr><td>书籍ID</td><td>书籍名称</td><td>库存</td></tr>
				   <c:forEach items="${orders_1}" var="order">
				    <tr><td>${order.book.bookid }</td><td>${order.book.title }</td><td>${order.book.count }</td></tr>
				   </c:forEach>
				   </table>
				 </c:if>
				 <c:if test="${empty orders_0 && empty orders_1}">
				  订单生成成功
				 </c:if>
					
				
			</div>
		</div>



	</body>
</html>
