package com.study.business;

import java.util.ArrayList;
import java.util.List;
import com.study.dao.BookDAO;
import com.study.dao.OrderDAO;
import com.study.pojo.Book;
import com.study.pojo.Order;
import com.study.pojo.Seller;

public class AddOrderBiz extends AbstractBusiness {
	//输入参数
	private List<Order> orders;
	//输出参数， 库存为0的异常订单
	public List<Order> orders_0 = new ArrayList<Order>();
	//输出参数， 库存不足的异常订单
	public List<Order> orders_1 = new ArrayList<Order>();
	
	private OrderDAO orderdao;
	private BookDAO bookdao;
	
	public void doBusiness() {
		/**
		 *  
 2、查找库存
    比较库存和购物车里面的数量，库存为零，把这一项单独放到一个list里面
 3、正常处理其他订单
 4、返回一个非正常处理的订单列表，告诉用户“库存为零”
另外一种处理方式：
 针对库存不足的考虑？
 比较库存和购物车里面的数量，库存不足，根据现有库存的数量生成指定订单。把这一项修改count属性，单独放到一个list里面
		 */
		List<Order> os = new ArrayList<Order>();
		for(Order order : orders)
		{
			//1、正常情况
			
			//int kucun = order.getBook().getCount();//应该要实时去取数据库里面的数据
			Book book = bookdao.queryBookById(order.getBook().getBookid());
			int kucun = book.getCount();
			//取得这本书属于哪个卖家
			Seller seller = book.getSeller();
			int c = order.getCount();//100
			order.setSeller(seller);
			//2、库存不足
			if(c > kucun && kucun != 0)
			{
				//如果买了200本，但是库存只有100，生成一条100本的订单，另外剩余放到库存不足的异常订单列表
				order.setCount(kucun);
				os.add(order);
				
				//
				try {
					order = (Order) order.clone();
					order.setCount(c - kucun);
					orders_1.add(order);
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			//3、库存为0
			else if(kucun == 0)
			{
				orders_0.add(order);
			}
			else
			{
				os.add(order);
			}
			
		}
		
		//执行批量保存
		orderdao.saveOrders(os);
		
		
		
	}

	@Override
	public Object getResult() {
		List rs = new ArrayList();
		rs.add(orders_0);
		rs.add(orders_1);
		return rs;
	}

	@Override
	public void setBizData(Object obj) {
		this.orders = (List<Order>) obj;
		
	}

	public void setOrderdao(OrderDAO orderdao) {
		this.orderdao = orderdao;
	}

	public void setBookdao(BookDAO bookdao) {
		this.bookdao = bookdao;
	}
	
	

}
