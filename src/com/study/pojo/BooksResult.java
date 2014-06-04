package com.study.pojo;

import java.util.List;

public class BooksResult {
	private int total;
	private List<Book> books;
	
	public BooksResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BooksResult(int total, List<Book> books) {
		super();
		this.total = total;
		this.books = books;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Book> getRows() {
		return books;
	}
	public void setRows(List<Book> books) {
		this.books = books;
	}
	
}
