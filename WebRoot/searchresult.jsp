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
	<script type="text/javascript">
	 function showBookPic(pic)
	 {
	 var detail = $("#book_detail");
	 
	 	if(pic!='')
	 	{
		  detail.attr({ src: "img/"+pic });
		  detail.fadeIn();
		 }
		 else
		 {
		 detail.attr({"src":""});
		 
		 }
		 
	 }
	</script>
	</head>

	<body>
		<div id="main">

			<%@include file="head.jsp"%>
  <div id="cat">
    <img id="book_detail" src="" width="200px" height="250px" border="0"/>
  </div>
			<div id="content">
				<h1 class="txt2">
					搜索结果：
				</h1>
				<c:choose>
					<c:when test="${empty books}">
						<h1>
							没有找到结果
						</h1>
					</c:when>
					<c:otherwise>
						<table class="cartlst">
							<tr class="menu">
								<td>
									书籍ID
								</td>
								<td>
									书籍名称
								</td>
								<td>
									作者
								</td>
								<td>
									价格
								</td>
							</tr>
							<c:forEach items="${books}" var="book">
							
								<tr class="tr_select" onmouseover="showBookPic('${book.pic }')"
									onmouseout="showBookPic('')">
									<td>
										<a href="book_getBookById.action?book.bookid=${book.bookid }">${book.bookid }</a>
									</td>
									<td>
										<a href="book_getBookById.action?book.bookid=${book.bookid }">${book.title }</a>
									</td>
									<td>
										${book.author }
									</td>
									<td>
										￥${book.price }
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
