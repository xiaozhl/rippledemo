package com.study.action;

import java.util.List;

import com.study.business.Business;
import com.study.pojo.BookType;

public class BookTypeAction {
	private List<BookType> booktypes;
	private Business querybooktype;
	
	public List<BookType> getBooktypes() {
		return booktypes;
	}

	public void setBooktypes(List<BookType> booktypes) {
		this.booktypes = booktypes;
	}

	public void setQuerybooktype(Business querybooktype) {
		this.querybooktype = querybooktype;
	}

	public String queryAllBookTypes()
	{
		querybooktype.doit();
		this.booktypes = (List<BookType>) querybooktype.getResult();
		
		return "JSON";
	}
	
}
