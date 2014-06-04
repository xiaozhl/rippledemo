<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>卖家管理页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/themes/demo.css">
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery.upload-1.0.js"></script>
	<script type="text/javascript" src="js/fun.js"></script>
	<style type="text/css">
	 body{
	  background:url('img/win.jpg') no-repeat;
	  background-position: -300px -300px;
	 }
	</style>
	<script type="text/javascript">
	 function showwin(win)
	 {
	  $("#"+win).window('open');
	 }
	 
	 function closewin(win)
	 {
	 $("#"+win).window('close');
	 }
	 
	 	 
	 $(function(){
			$('#booklist').datagrid({
				title:'书籍管理',
				iconCls:'icon-save',
				width:800,
				height:350,
				nowrap: true,
				autoRowHeight: true,
				striped: false,
				collapsible:false,
				url:'book_queryBooks.action?booktype.typeid=0&seller.selleruser=${sessionScope.seller.selleruser}',
				sortName: 'bookid',
				sortOrder: 'asc',
				remoteSort: false,
				idField:'bookid',
				frozenColumns:[[
	               
	                {title:'bookid',field:'bookid',width:50,sortable:true}
				]],
				columns:[[
			        {title:'基本信息',colspan:4}
				],[
					{field:'title',title:'书名',width:120},
					{field:'author',title:'作者',width:220,rowspan:2},
					{field:'price',title:'价格',width:150,rowspan:2},
					{field:'count',title:'库存',width:150,rowspan:2}
				]],
				pagination:true,
				rownumbers:false,
				toolbar:[{
					id:'btnadd',
					text:'新增',
					iconCls:'icon-add',
					handler:function(){
						showwin('addBook');
					}
				},'-',{
					id:'btnmod',
					text:'更新',
					disabled:false,
					iconCls:'icon-edit',
					handler:function(){
						$('#btnmod').linkbutton('enable');
						alert('modify');
						//取得用户选中的行,注意，如果是更新，只能选择一行
						var row = getSelections();
						if(row.length <1 )
						{
						 $.messager.alert('无法更新记录','您必须得选择要更新的记录','error');
						}
						else if(row.length > 1)
						{
						 $.messager.alert('无法更新记录','更新记录一次只能选择一个','error');
						}
						else if(row.length == 1)
						{
						 row = getSelected();
						 //显示表格
						 showwin('modBook');
						 //获得所有的input元素，用于填充值
						 var eles = $("#bookform2 input");
						 
						 //获得对象的所有键值对
						 var kv = [];
						 
						 $.each( row, function(i, n){
							  kv[i] = n;
							});
						 
						 $.each( eles, function(i, n){
							var e_name = n.name;
							if(e_name.split(".").length == 2)
							{
								var key = e_name.substring(e_name.lastIndexOf(".")+1,e_name.length);
								var value = kv[key];
								n.value=value;
								
							 }
							 else if(e_name.split(".").length > 2)
							 {
							  var ind = e_name.indexOf(".");
							   var key = e_name.substring(ind+1,e_name.indexOf(".",ind+1));
								var value = kv[key];
								
								//$("#bookform2 input[name='book.type.typeid']").datagrid('beginEdit', value.typeid);
								
								
							 }
							 
							});
						}
					}
				},'-',{
					id:'btndel',
					text:'删除',
					disabled:false,
					iconCls:'icon-remove',
					handler:function(){
						//获得选择的项目，执行删除
						var ids = getSelections();
						$.messager.confirm("确定要删除么？", "确定要删除这"+ids.length+"项么？", function(r){
							if (r){
							  var url = "book_removeBooks.action";
							  var params={"ids":JSON.stringify(ids)};
							  $.post(url,params,function(data){
							    if(data.success)
							    {
							     $.messager.alert('删除成功','删除记录成功了！！','info');
							     $('#booklist').datagrid('reload');
							    }
							    else
							    {
							     $.messager.alert('删除失败',data.errormsg,'error');
							    }
							  });								
							}
						});
						
						
					}
				}
				
				]
			});
			
			var p = $('#booklist').datagrid('getPager');
			$(p).pagination({
				onBeforeRefresh:function(d){
					//alert('before refresh');
				}
			});
		});
		
		function getSelected(){
			var selected = $('#booklist').datagrid('getSelected');
			if (selected){
				return selected;
			}
			return null;
		}
		function getSelections(){
			var ids = [];
			var rows = $('#booklist').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].bookid);
			}
			return ids;
		}
		
	    
	    function postForm(fm)
	    {
	     $.messager.show({
				title:'开始上传书籍...',
				msg:'书籍上传处理中，耐心等待',
				timeout:3000,
				showType:'show'
			}); 
		var book = $("#"+fm).serializeObject();
	     // 上传方法
        $.upload({
                // 上传地址
                url: 'book_addBook.action', 
                // 文件域名字
                fileName: 'uploadPic', 
                // 其他表单数据
                params: book,
                // 上传完成后, 返回json, text
                dataType: 'json',
                // 上传之前回调,return true表示可继续上传
                onSend: function() {
                        return true;
                },
                // 上传之后回调
                onComplate: function(data) {
                       if(data.success)
                       {
                        $.messager.alert('新增书籍成功','书籍新增成功','info');
                        closewin('addBook');
                       }
                       else
                       {
	                     $.messager.alert('新增书籍成功','书籍新增成功','error');
                       }
                }
        });
        
	     
	    }
	  $(document).ready(function(){
			var win = $.messager.progress({
				title:'Please waiting',
				msg:'Loading data...'
			});
			setTimeout(function(){
				$.messager.progress('close');
			},3000);
			$.messager.show({
				title:'欢迎光临',
				msg:'欢迎光临,${sessionScope.seller.selleruser}<br/>如果需要帮助，请联系QQ:45491622',
				timeout:5000,
				showType:'slide'
			}); 
			
	  });
	</script>

  </head>
  
  <body style="height:100%;width:100%;overflow:hidden;border:none;padding:20px">
    <h2>欢迎登陆卖家管理页面,${sessionScope.seller.selleruser }&nbsp;&nbsp; <a href="logout.jsp">注销</a></h2>
    <div style="padding:5px;width:200px;border:1px solid white">
        <a href="#" class="easyui-menubutton" menu="#mm1" iconCls="icon-tip">管理</a>
        <a href="#" class="easyui-menubutton" menu="#mm2" iconCls="icon-help">Help</a>
    </div>
    <div id="mm1" style="width:150px;">
        <div onclick="showwin('bookmanager');">书籍管理</div>
        <div onclick="">用户管理</div>
        <div onclick="showwin('ordermgr');">订单管理</div>
    </div>
    <div id="mm2" style="width:100px;">
        <div>Help</div>
        <div>About</div>
        <div>News</div>
    </div>
    
    <!-- 书籍管理窗口 -->
    <div id="bookmanager" 
    	class="easyui-window" 
    	data-options="closed:true,modal:true,maximizable:false,resizable:false,title:'书籍管理窗口'" 
    	style="width:820px;height:400px;">
      
      <table id="booklist"></table>	
    </div>
    
    <!-- 新增书籍窗口 -->
    <div id="addBook" 
        class="easyui-window" 
    	data-options="closed:true,modal:true,maximizable:false,resizable:false,title:'新增书籍'" 
    	style="width:300px;height:200px;">
      
       <form id="bookform" method="post" novalidate>
       	<table>
	        <tr>
	            <td><label for="name">书籍名称:</label></td>
	            <td><input class="easyui-validatebox" type="text" name="book.title" data-options="required:true" /></td>
	        </tr>
	        <tr>
	            <td><label for="author">作者:</label></td>
	            <td><input class="easyui-validatebox" type="text" name="book.author" data-options="required:true" /></td>
	        </tr>
	        <tr>
	            <td><label for="price">价格:</label></td>
	            <td><input name="book.price" class="easyui-numberspinner" style="width:80px;"
        			required="required" data-options="editable:true" /></td>
	        </tr>
	       
	        <tr>
	            <td><label for="booktype">分类:</label></td>
				<td><input class="easyui-combobox" name="book.type.typeid"
						data-options="
								url:'booktype_queryAllBookTypes.action',
								valueField:'typeid',
								textField:'typename',
								panelHeight:'auto'" />
<input type="hidden" name="book.seller.selleruser" value="${sessionScope.seller.selleruser }"/>								
								</td>
	        </tr>
	        <tr>
	            <td colspan="2"><input type="button" value="上传书籍图片完成新建书籍" onclick="postForm('bookform');"></td>
	        </tr>
    </table>
    </form>
    </div>
    
       <!-- 更新书籍窗口 -->
    <div id="modBook" 
        class="easyui-window" 
    	data-options="closed:true,modal:true,maximizable:false,resizable:false,title:'新增书籍'" 
    	style="width:300px;height:200px;">
      
       <form id="bookform2" method="post" novalidate>
       	<table>
       	     <tr>
	            <td><label for="name">书籍ID:</label></td>
	            <td><input class="easyui-validatebox" type="text" name="book.bookid" data-options="required:true" disabled="disabled"/></td>
	        </tr>
       	    
	        <tr>
	            <td><label for="name">书籍名称:</label></td>
	            <td><input class="easyui-validatebox" type="text" name="book.title" data-options="required:true" /></td>
	        </tr>
	        <tr>
	            <td><label for="author">作者:</label></td>
	            <td><input class="easyui-validatebox" type="text" name="book.author" data-options="required:true" /></td>
	        </tr>
	        <tr>
	            <td><label for="price">价格:</label></td>
	            <td><input name="book.price" class="easyui-numberspinner" style="width:80px;"
        			required="required" data-options="editable:true" /></td>
	        </tr>
	       
	        <tr>
	            <td><label for="booktype">分类:</label></td>
				<td><input class="easyui-combobox" name="book.type.typeid"
						data-options="
								url:'booktype_queryAllBookTypes.action',
								valueField:'typeid',
								textField:'typename',
								panelHeight:'auto'" />
					<input type="hidden" name="book.seller.selleruser" value="${sessionScope.seller.selleruser }"/>	
								</td>
	        </tr>
	        <tr>
	            <td colspan="2"><input type="button" value="上传书籍图片完成更新书籍" onclick="postForm('bookform2');"></td>
	        </tr>
    </table>
    </form>
    </div>
    
    <!-- 订单管理窗口 -->
    <div id="ordermgr" 
    	class="easyui-window" 
    	data-options="closed:true,modal:true,maximizable:false,resizable:false,title:'订单管理窗口'" 
    	style="width:820px;height:400px;">
      
      <table id="orderlist"></table>	
    </div>
  </body>
</html>
