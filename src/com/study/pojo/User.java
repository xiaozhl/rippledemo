package com.study.pojo;

import java.util.List;

public class User implements java.io.Serializable{
	private String username;
	private String password;
	private String pic;
	private String wallet;
	private List<Order> orders;
	
	public User(String username, String password, String pic, List<Order> orders) {
		super();
		this.username = username;
		this.password = password;
		this.pic = pic;
		this.orders = orders;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getWallet() {
		return wallet;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	
	
}
