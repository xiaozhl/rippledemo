package com.study.business;

import java.util.List;

import com.study.dao.BookTypeDAO;
import com.study.pojo.BookType;

public class QueryAllBookTypesBiz extends AbstractBusiness {
	private List<BookType> booktypes;
	private BookTypeDAO booktypedao;
	public void doBusiness() {
		this.booktypes = booktypedao.queryBookTypes();

	}

	public void setBooktypedao(BookTypeDAO booktypedao) {
		this.booktypedao = booktypedao;
	}


	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return this.booktypes;
	}

}
