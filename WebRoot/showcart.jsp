<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

		<title>购物车</title>

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
					购物车：
				</h1>
				<c:choose>
					<c:when test="${empty cart}">
						<h1>
							购物车为空
						</h1>
					</c:when>
					<c:otherwise>
						<c:set var="sum" value="0" scope="page"></c:set>
						<table class="cartlst">
							<tr class="menu">
								<td>
									书籍名称
								</td>
								<td>
									数量
								</td>
								<td>
									价格
								</td>
								<td>
									总价
								</td>
							</tr>
							<c:forEach items="${cart}" var="item">
								<tr>
									<td>
										${item.book.title }
									</td>
									<td>
										${item.count }
									</td>
									<td>
										￥${item.book.price }
									</td>
									<td>
										￥${item.count * item.book.price }
									</td>
									<c:set var="sum" value="${sum + item.count * item.book.price}"></c:set>
								</tr>
							</c:forEach>

						</table>
						<p class="btm">
							总价:￥${sum }
							<span><a href="index.jsp">返回购物</a>
							</span>
							<span><a href="order_addOrder.action">生成订单</a>
							</span>
						</p>
						
					</c:otherwise>
				</c:choose>

			</div>
		</div>



	</body>
</html>
