package com.study.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.study.pojo.Order;
import com.study.pojo.User;

public class OrderDAOImpl implements OrderDAO {
	private HibernateTemplate hbnTemplate;
	public void setSessionFactory(SessionFactory sf)
	{
		this.hbnTemplate = new HibernateTemplate(sf);
	}
	public List<Order> queryAllOrders() {
		String hql = "from Order";
		List<Order> orders = hbnTemplate.find(hql);
		return orders;
	}

	public Order queryOrderById(long orderid) {
		
		
		return (Order) hbnTemplate.get(Order.class, orderid);
	}

	public List<Order> queryOrdersByUser(User user) {
		String hql ="from Order where user = :user";
		List<Order> orders = hbnTemplate.findByNamedParam(hql, new String[]{"user"}, new Object[]{user});
		return orders;
	}

	public void saveOrders(List<Order> list) {
		int index = 0;
		for(Order order : list)
		{
			hbnTemplate.save(order);
			if(index % 30 == 0)
			{
				hbnTemplate.flush();
			}
			index ++;
		}

	}

	public void updateOrder(Order order) {
		hbnTemplate.saveOrUpdate(order);
	}

}
