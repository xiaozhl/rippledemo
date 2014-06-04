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

		<title>付款</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.qrcode.min.js"></script>
	<script type="text/javascript" src="js/fun.js"></script>
	<script type="text/javascript">
	
	var sellerwallet="<%out.print(request.getParameter("sellerwallet")); %>";
	var userwallet="${sessionScope.user.wallet }";
	var money=<%out.print(request.getParameter("money")); %>;
	//已经支付了多少
	var paid=0;
	//记录tx id，防止重复计算
	var tx_arr=[];
	//默认下标
	var arr_index=0;
	//ripple支付完成后可能要等一会儿才能查询到正确的结果，如果失败，可以过一会儿再查，查询三次
	var currentquery=3;
	var orderid= <%out.print(request.getParameter("orderid")); %>;
	
	//倒计时，如果10分钟还没有付款完成，关闭
	var taskid;
	var seconds=600;
	function timetopay()
	{
	 taskid=setInterval(function(){
	    seconds--;
	    if(seconds == 0 )
	    {
	     window.opener=null;
	     window.open('','_self');
	     window.close();
	    }
	 	$("#timetopay").html("请在"+seconds+"内完成支付");
	 	},1000);
	}
	function clearTask()
	{
	 clearInterval(taskid);
	}
	
	function monitorPayment()
	{
		var data ={"command":"subscribe",
				"streams":["transactions"],
				"accounts":[sellerwallet]};
			data = JSON.stringify(data);
			websocket = new WebSocket("ws://s1.ripple.com:443");
			websocket.onopen= function(event){
			        websocket.send(data);
			        websocket.onmessage= function(event){
						
			           var result = JSON.parse(event.data);
			           if(result.engine_result == 'tesSUCCESS' 
			           		&& result.engine_result_message == 'The transaction was applied.'
			           		&& result.transaction.Account == userwallet
			           		&& result.transaction.Destination == sellerwallet)
			           {
			            var actmoney=result.transaction.Amount.value;
			            var currency=result.transaction.Amount.currency;
			            var txid = result.transaction.hash;
			            paid = paid + actmoney;
			            if(paid < money)
			            {
			             //只付了一部分，提示还需要付多少钱
			             var shouldpay = money - actmoney;
			             alert("您只支付了"+actmoney+currency+",还需要支付"+shouldpay+currency+"才能完成本订单交易！");
			             tx_arr[arr_index]=txid;
			             arr_index++;
			            }
			            else
			            {
			            //校验一下数组里面是否已经出现过本次tx
			            for(var i=0;i<tx_arr.length;i++)
			            {
			             if(tx_arr[i] == txid)
			             {
			              return false;
			             }
			            }
			            tx_arr[arr_index]=txid;
			            alert("您已经成功向卖家支付"+actmoney+currency+",订单完成，谢谢您的支持！");
			            this.close();
			            
			            //发送订单更新信息到服务端
			            checkOrder();
			            clearTask();
			            }
			            
			           }
			           
			        };
			  websocket.onclose= function(event){
			        //socket关闭
			        alert("监控closed");
			     };
			  };
	 
	}
	
	function checkOrder()
	{
	
	 $.post("order_updateOrder.action",{rippletxid:tx_arr[0],orderid:orderid},function(data){
          if(data)
          {
           $("#txt").empty();
           if(data.success)
           {
           $("#txt").append("<span>支付成功</span>");
           }
           else
           {
           $("#txt").append("<p>"+data.errormsg+"</p><input type=\"button\" value=\"重新检测\" onclick=\"checkOrder()\"/>");
           }
           $("#timetopay").empty();
          }
        });
	}
	
	function init()
	{
	var rippleto="http://ripple.com//send?to="+sellerwallet+"&dt="+orderid+"&amount="+money+"/CNY";
	$('#qrcode_show').qrcode(
	 	{width: 120,
	 	 height: 120,
	 	 text: rippleto});
	
	$("#qrcode_show canvas").wrap("<a href='"+rippleto+"' target=\"_blank\"></a>");
	monitorPayment();
	timetopay();
	
	}
	 $(document).ready(init);
	</script>
	</head>

	<body>
		<div id="main">

			<%@include file="head.jsp"%>
   
			<div id="content">
			 
				<h1 class="txt2">
					付款界面： <span id="timetopay"></span>
				</h1>
				   <div id="txt">
					<form action="pay.action" method="post" enctype="multipart/form-data">
					<table class="form">
					    <tr style="color:green;"><td colspan="2">建议使用您的本地钱包支付，如果您信任本网站，可以使用在线支付方式</td></tr>
						<tr>
							<td>
								您的钱包：
							</td>
							<td>
								${sessionScope.user.wallet }
								<input type ="hidden" name="userwallet" value="${sessionScope.user.wallet }"/>
							</td>
						</tr>
						<tr>
							<td>
								您的密钥：
							</td>
							<td>
								<input type="file" name="walletsecret" />
							</td>
						</tr>
					    <tr>
							<td>
								您要支付：
							</td>
							<td>
								￥<%out.print(request.getParameter("money")); %>
								<input type="hidden" name="money" value="<%out.print(request.getParameter("money")); %>"/>
								
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<input type="hidden" name="orderid" value="<%out.print(request.getParameter("orderid")); %>"/>
								<input type="submit" value="付款" />
							</td>
						</tr>
						</table>
					</form>
				 </div>
			</div>
			<div id="qrcode_show">
			 
			</div>
			
			
		</div>



	</body>
</html>
