package com.study.pojo;

import java.io.Serializable;
import java.util.List;

public class Seller implements Serializable {
	
	private String selleruser;
	private String sellerpass;
	private String wallet;
	private List<Book> books;
	private List<Order> orders;
	
	public String getSelleruser() {
		return selleruser;
	}
	public void setSelleruser(String selleruser) {
		this.selleruser = selleruser;
	}
	public String getSellerpass() {
		return sellerpass;
	}
	public void setSellerpass(String sellerpass) {
		this.sellerpass = sellerpass;
	}
	public String getWallet() {
		return wallet;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
