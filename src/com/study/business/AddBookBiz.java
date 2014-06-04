package com.study.business;

import com.study.dao.BookDAO;
import com.study.pojo.Book;

public class AddBookBiz extends AbstractBusiness {
	private Book book;
	private BookDAO bookdao;
	
	public void doBusiness() {
		bookdao.saveOrUpdateBook(book);

	}

	@Override
	public void setBizData(Object obj) {
		this.book = (Book) obj;
	}

	public void setBookdao(BookDAO bookdao) {
		this.bookdao = bookdao;
	}

}
