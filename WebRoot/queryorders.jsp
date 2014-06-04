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

		<title>My JSP 'displaybook.jsp' starting page</title>

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
					订单详情：
				</h1>
				<c:choose>
					<c:when test="${empty sessionScope.user.orders}">
						<h1>
							订单为空
						</h1>
					</c:when>
					<c:otherwise>
						<c:set var="sum" value="0" scope="page"></c:set>
						<table class="cartlst">
							<tr class="menu">
								<td>
									订单ID
								</td>
								<td>
									书籍名称
								</td>
								<td>
									单价
								</td>
								<td>
									数量
								</td>
								<td>
									总价
								</td>
								<td>
									操作
								</td>								
							</tr>
							<c:forEach items="${user.orders}" var="order">
								<tr>
									<td>
										${order.orderid }
									</td>
									<td>
										${order.book.title }
									</td>
									<td>
										￥${order.book.price }
									</td>
									<td>
										${order.count }
									</td>
									<td>
										￥${order.price }
									</td>
									<td>
									 <c:choose>
									  <c:when test="${order.status eq 0}">
									  <form action="pay.jsp">
									   <input type="hidden" name="orderid" value="${order.orderid }"/>
									   <input type="hidden" name="money" value="${order.price }"/>
									   <input type="hidden" name="sellerwallet" value="${order.seller.wallet }"/>
									   <input type="submit" value="付款"/>
									   </form>
									  </c:when>
									  <c:when test="${order.status eq 1}">
									   已付款
									  </c:when>									  
									  <c:when test="${order.status eq 2}">
									   <input type="button" value="确认交易"/>
									  </c:when>								  
									  <c:when test="${order.status eq 3}">
									   完成交易
									  </c:when>	
									 								  

									 </c:choose>
									</td>
								</tr>
							</c:forEach>

						</table>
						
						
					</c:otherwise>
				</c:choose>

			</div>
		</div>



	</body>
</html>
