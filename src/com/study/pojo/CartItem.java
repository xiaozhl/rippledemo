package com.study.pojo;

public class CartItem implements java.io.Serializable{
	private long id;
	private Book book;
	private int count;
	
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItem(long id, Book book, int count) {
		super();
		this.id = id;
		this.book = book;
		this.count = count;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
