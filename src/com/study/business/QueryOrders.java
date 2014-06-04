package com.study.business;

import java.util.List;

import com.study.dao.OrderDAO;
import com.study.pojo.Order;
import com.study.pojo.User;

public class QueryOrders extends AbstractBusiness {
	//从action传入的
	private User user;
	//返回结果
	private List<Order> orders;
	
	private OrderDAO orderdao;
	
	public void doBusiness() {
	 orders = orderdao.queryOrdersByUser(user);

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return orders;
	}

	@Override
	public void setBizData(Object obj) {
		this.user = (User) obj;
	}

	public void setOrderdao(OrderDAO orderdao) {
		this.orderdao = orderdao;
	}
	
	

}
