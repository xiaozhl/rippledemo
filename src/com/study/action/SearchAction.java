package com.study.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.study.business.Business;
import com.study.pojo.Book;

public class SearchAction {
	private String keywords;
	private Business search;
	private List<Book> books;
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setSearch(Business search) {
		this.search = search;
	}
	
	public String execute() throws UnsupportedEncodingException
	{
		//keywords = new String(keywords.getBytes("ISO8859-1"),"utf-8");
		search.setBizData(keywords);
		search.doit();
		books= (List<Book>) search.getResult();
		return "BOOKS";
	}
}
