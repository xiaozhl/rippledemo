package com.study.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Cloneable,Serializable{
	private long orderid;
	private User user;
	private Book book;
	private double price;
	private int count;
	private Date createTime;
	private Seller seller;
	private int status=BookstoreConstant.ORDER_NOT_PAY;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(User user, Book book,int count, double price, int status,Date createTime) {
		super();
		this.user = user;
		this.book = book;
		this.price = price;
		this.status = status;
		this.createTime = createTime;
		this.count = count;
	}
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
	
}
