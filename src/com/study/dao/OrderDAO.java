package com.study.dao;

import java.util.List;

import com.study.pojo.Order;
import com.study.pojo.User;

public interface OrderDAO {
  
	public void saveOrders(List<Order> list);
	public Order queryOrderById(long orderid);
	public void updateOrder(Order order);
	public List<Order> queryOrdersByUser(User user);
	public List<Order> queryAllOrders();
	
}
