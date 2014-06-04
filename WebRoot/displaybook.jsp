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
					书籍详情：
				</h1>

				<c:choose>
					<c:when test="${empty book}">
						<h1>
							书籍未找到
						</h1>
					</c:when>
					<c:otherwise>
						<div id="bookdetail">
							<div class="detail">
								<img src="img/${book.pic }" width="176px" height="235px"/>
							</div>
							<div class="detail">
								<h1>
									${book.title }
								</h1>
								<table class="book">
									<tr>
										<td>
											书籍ID：
										</td>
										<td>
											${book.bookid }
										</td>
									</tr>
									<tr>
										<td>
											作者：
										</td>
										<td>
											${book.author }
										</td>
									</tr>
									<tr>
										<td>
											价格：
										</td>
										<td>
											￥${book.price }
										</td>
									</tr>
									<tr>
										<td>
											<a href="cart_addcart.action?bookid=${book.bookid }">加入购物车</a>
										</td>
										<td>
											<a href="showcart.jsp">查看购物车</a>
										</td>
									</tr>
								</table>
							</div>

						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>



	</body>
</html>
