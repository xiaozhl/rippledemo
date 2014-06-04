package com.study.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.study.business.Business;
import com.study.pojo.Book;
import com.study.pojo.CartItem;

public class CartAction implements java.io.Serializable{
	private  Business querybook;
	private long bookid;
	
	public long getBookid() {
		return bookid;
	}

	public void setBookid(long bookid) {
		this.bookid = bookid;
	}

	public void setQuerybook(Business querybook) {
		this.querybook = querybook;
	}

	public String addcart()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//Ҫ�õ�������һ���飿��
		querybook.setBizData(bookid);
		querybook.doit();
		Book book =(Book) querybook.getResult();
		
		//������ﳵΪ��
		List<CartItem> cart =(List<CartItem>) session.getAttribute("cart");
		if(cart== null)
		{
			//����һ�����ﳵ������
			cart = new ArrayList<CartItem>();
			//����CartItem
			CartItem item = new CartItem(book.getBookid(),book,1);
			cart.add(item);
		}
		else
		{
		    //���ﳵ�����Ѿ���һ����ͬ����
			//true��ʾû����ͬ��
			boolean flag = true; 
		    for(CartItem item : cart)
		    {
		    	if(item.getId() == bookid) 
		    	{
		    		flag =false;
		    		item.setCount(item.getCount()+1);
		    		break;
		    	}
		    }
		    
		    if(flag)
		    {
		    	//û����ͬ����
		    	CartItem item = new CartItem(book.getBookid(),book,1);
				cart.add(item);
		    }
		    
		}
		
		//���浽session
		session.setAttribute("cart",cart);
		
		return "SHOWCART";
	}
}
