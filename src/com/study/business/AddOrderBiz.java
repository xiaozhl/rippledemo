package com.study.business;

import java.util.ArrayList;
import java.util.List;
import com.study.dao.BookDAO;
import com.study.dao.OrderDAO;
import com.study.pojo.Book;
import com.study.pojo.Order;
import com.study.pojo.Seller;

public class AddOrderBiz extends AbstractBusiness {
	//�������
	private List<Order> orders;
	//��������� ���Ϊ0���쳣����
	public List<Order> orders_0 = new ArrayList<Order>();
	//��������� ��治����쳣����
	public List<Order> orders_1 = new ArrayList<Order>();
	
	private OrderDAO orderdao;
	private BookDAO bookdao;
	
	public void doBusiness() {
		/**
		 *  
 2�����ҿ��
    �ȽϿ��͹��ﳵ��������������Ϊ�㣬����һ����ŵ�һ��list����
 3������������������
 4������һ������������Ķ����б������û������Ϊ�㡱
����һ�ִ���ʽ��
 ��Կ�治��Ŀ��ǣ�
 �ȽϿ��͹��ﳵ�������������治�㣬�������п�����������ָ������������һ���޸�count���ԣ������ŵ�һ��list����
		 */
		List<Order> os = new ArrayList<Order>();
		for(Order order : orders)
		{
			//1���������
			
			//int kucun = order.getBook().getCount();//Ӧ��Ҫʵʱȥȡ���ݿ����������
			Book book = bookdao.queryBookById(order.getBook().getBookid());
			int kucun = book.getCount();
			//ȡ���Ȿ�������ĸ�����
			Seller seller = book.getSeller();
			int c = order.getCount();//100
			order.setSeller(seller);
			//2����治��
			if(c > kucun && kucun != 0)
			{
				//�������200�������ǿ��ֻ��100������һ��100���Ķ���������ʣ��ŵ���治����쳣�����б�
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
			//3�����Ϊ0
			else if(kucun == 0)
			{
				orders_0.add(order);
			}
			else
			{
				os.add(order);
			}
			
		}
		
		//ִ����������
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
