package com.study.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.study.business.Business;
import com.study.pojo.Book;
import com.study.pojo.BookstoreConstant;
import com.study.pojo.CartItem;
import com.study.pojo.Order;
import com.study.pojo.ProcessResult;
import com.study.pojo.User;

public class OrderAction {
	private Business addorder;
	private Business queryorder;
	private Business updateOrder;
	private List<Order> orders_0;
	private List<Order> orders_1;
	private long orderid;
	private String rippletxid;
	private ProcessResult result;
	public void setUpdateOrder(Business updateOrder) {
		this.updateOrder = updateOrder;
	}


	public Business getAddorder() {
		return addorder;
	}


	public void setAddorder(Business addorder) {
		this.addorder = addorder;
	}


	public List<Order> getOrders_0() {
		return orders_0;
	}


	public void setOrders_0(List<Order> orders_0) {
		this.orders_0 = orders_0;
	}


	public List<Order> getOrders_1() {
		return orders_1;
	}
 
	public long getOrderid() {
		return orderid;
	}


	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}


	public String getRippletxid() {
		return rippletxid;
	}


	public void setRippletxid(String rippletxid) {
		this.rippletxid = rippletxid;
	}


	public ProcessResult getResult() {
		return result;
	}


	public void setResult(ProcessResult result) {
		this.result = result;
	}


	public void setOrders_1(List<Order> orders_1) {
		this.orders_1 = orders_1;
	}


	public void setQueryorder(Business queryorder) {
		this.queryorder = queryorder;
	}


	public String addOrder()
	{
		//根据购物车，生成订单
		HttpSession session = ServletActionContext.getRequest().getSession();
		List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");
		//开始转换购物车到订单
		List<Order> orders = new ArrayList<Order>();
		Order order = null;
		Book book = null;
		for(CartItem item : cart)
		{
			book = item.getBook();
			int count = item.getCount();
			double price = count * book.getPrice();
			order = new Order(user,book,count,price,BookstoreConstant.ORDER_NOT_PAY,new Date());
			orders.add(order);
		}
		
		addorder.setBizData(orders);
		addorder.doit();
		List list = (List) addorder.getResult();
		orders_0 = (List<Order>) list.get(0);
		orders_1 = (List<Order>) list.get(1);
		//清空购物车
		session.setAttribute("cart", null);
		
		return "SUCCESS";
	}
	
	
	public String queryOrder()
	{
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user");
		queryorder.setBizData(user);
		queryorder.doit();
		List<Order> orders = (List<Order>) queryorder.getResult();
		user.setOrders(orders);
		
		session.setAttribute("user", user);
		
		return "SHOWORDERS";
	}
	
	public String updateOrder()
	{
		List params = new ArrayList();
		params.add(rippletxid);
		params.add(this.orderid);
	    updateOrder.setBizData(params);
	    updateOrder.doit();
	    result = (ProcessResult) updateOrder.getResult();
		if(result.isSuccess())
		{
			result.setErrormsg("订单"+this.orderid+",支付成功");
		}
		return "PAYRESULT";
	}
}
