<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="head">
	<div class="logo">
	<a href="index.jsp" target="_self">
	<img src="img/logo.jpg" />
	</a>
	</div>
	<div class="search">
	<form action="search.action" method="post">
		<input type="text" class="search_box" name="keywords" value="点击这里搜索"
			 onclick="this.value=''"
			 onblur="if(this.value==''){this.value='点击这里搜索'}"/>
		<input type="submit" class="btn1" value="搜索">
	</form>
	</div>
	<div class="loginstatus">
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<span><a href="login.jsp">登录</a>
				</span>
				<span><a href="reg.jsp">注册</a>
				</span>
			</c:when>
			<c:otherwise>
			   <div class="pic_left">
			    <img src="upload/${user.pic }" width="30px" height="35px"/>
			   </div>
			   <div class="pic_left">
				<p>
					<span>用户名：${user.username }</span>
					<span><a href="logout.jsp">注销</a>
					</span>
				</p>
				<p>
				  <span><a href="showcart.jsp">查看购物车</a></span>
				  <span><a href="order_queryOrder.action">查看订单</a></span>
				</p>
				</div>
			</c:otherwise>
		</c:choose>
		<div id="qr" class="qrcode" >
				 扫我<h1 onclick="closeAdv('qr')">X</h1>
				 <img src="img/qrcode.png" />
		</div>
	</div>
	
</div>
<div class="adv_left" id="adv1">
<h1 onclick="closeAdv('adv1')">X</h1>
<a target="_blank" 
	href="http://shang.qq.com/wpa/qunwpa?idkey=7c718f481efb81815f8ccb8f6be8e6bb3cbfeeaa637bd3d48866d4ebf8c9c3d6">
	<img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="JAVA一起学" title="JAVA一起学">
 </a>
</div>
<div class="adv_right" id="adv2">
<h1 onclick="closeAdv('adv2')">X</h1>
我们一起Java QQ群：370930699
</div>
<div id="adv">
<div class="adv_l_c">
	<div class="adv_btn_l" onclick="changeAdv('-');">
	</div>
</div>
<div class="adv_r_c">
	<div class="adv_btn_r" onclick="changeAdv('+');">
    </div>
</div>

</div>