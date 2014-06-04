package com.study.business;

import java.util.List;

import com.study.dao.OrderDAO;
import com.study.pojo.Order;
import com.study.pojo.User;

public class QueryOrders extends AbstractBusiness {
	//��action�����
	private User user;
	//���ؽ��
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
