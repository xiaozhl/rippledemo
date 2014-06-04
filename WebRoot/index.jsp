<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>南京艾瑞职业培训学校</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/fun.js"></script>
	<script type="text/javascript">
	var page=1;
	var currenttype = '';
	var maxpage=0;
	 function init()
	 {
	 //在初始化的时候，去服务端查询到底有多少条记录
	  initcategory();
	 //可以计算出有多少页
	  queryBooks(1,0,'');
	 }
	 
	 function initcategory()
	 {
	  var url ="booktype_queryAllBookTypes.action";
	  var param={};
	  var catgory = $("#menu");
	  $.getJSON(url,param,function(data){
	    for(var i=0;i<data.length;i++)
	    {
	     var typeid = data[i].typeid;
	     var typename = data[i].typename;
	     catgory.append("<li class=''><a onclick=\"queryBooks(1,"+typeid+");\">"+typename+"</a></li>");
	    }
	  });
	 }
	 
	 function queryBooks(page,typeid)
	 {
	 currenttype = typeid;
	  var url="book_queryBooks.action?booktype.typeid="+typeid;
	  var param={page:page};
	  $.getJSON(url,param,function(data){
	  maxpage = Math.ceil(data.total/20);
	  //$("#books").fadeOut();
	  $("#books").empty();
	  
	    for(var i=0;i<data.rows.length;i++)
	    {
	      var bookid = data.rows[i].bookid;
	      var title = data.rows[i].title;
	      var newtitle="";
	      if(title.length>4)
	      {
	       newtitle = title.substring(0,4)+"...";
	      }
	      else
	      {
	       newtitle = title;
	      }
	      var pic = data.rows[i].pic;
	      var row = packRow(bookid,newtitle,pic,title);
	      $("#books").append(row);
	    }
	    
	    if(data.length==0 && page>1)
	    {
	     queryNextPage('-');
	    }
	    else
	    {
	    $("#books").fadeIn("slow");
	    }
	  	
	  	
	  });
	  
	 }
	 //构建每一本书对应的div
	 function packRow(bookid,title,pic,ft)
	 {
	var str1 =" <a href=\"book_getBookById.action?book.bookid="+bookid+"\">";
	var str2 ="<div class=\"lstBook\"><span class=\"txt1\">☆"+title+"</span><hr/>";
    var str3 ="<img class=\"lstpic\" src=\"img/"+pic+"\" alt=\""+ft+"\" title=\""+ft+"\"/></div></a>"
     return str1+str2+str3;
	 }
	 
	 function queryNextPage(oper)
	 {
	 if(oper=='+')
	 {
	 page++;
	 }
	 else if(oper=='-')
	 {
	  page--;
	 }
	 if(page<1)
	 {
	  page = 1;
	  alert("已经是第一页了");
	  return false;
	 }
	 if(page > maxpage)
	 {
	  alert("已经是最后一页");
	  page--;
	  return false;
	 }
	 $("#pagecontent").text("当前第"+page+"页，共"+maxpage+"页");
	 queryBooks(page,currenttype,'');
	 }
	 
	 //页面加载完毕后执行init方法
	 $(document).ready(init);
	</script>
  </head>
  
  <body>
  <div id="main">
    
    <%@include file="head.jsp" %>
   <div id="cat">
    <ul id="menu">
    </ul>
   </div>

   <div id="content">
    <h1 class="txt2">图书列表： <span style="cursor: pointer;" onclick="queryNextPage('+');">下一页</span>
    <span style="cursor: pointer;" onclick="queryNextPage('-');">上一页</span><span id="pagecontent">当前第1页</span></h1>
    
     <div id="books">
     </div>
    </div>
     
   </div>
  </body>
</html>
