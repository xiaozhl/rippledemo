package com.study.business;

import java.util.ArrayList;
import java.util.List;

import com.study.dao.BookDAO;
import com.study.pojo.Book;
import com.study.pojo.BookType;
import com.study.pojo.BooksResult;
import com.study.pojo.BookstoreConstant;
import com.study.pojo.Seller;

public class QueryAllBooksBiz extends AbstractBusiness {
	// ���շ��صļ���
	private BooksResult booksresult = new BooksResult();

	// �ӵڼ��л��ǣ��Ҽ��� ���������
	private int startrow = 0;
	private int maxrows = 20;
	//booktype
	private BookType booktype= new BookType(BookstoreConstant.BOOKTYPE_NOTSET,"all");
	//bookid �������ֵ������bookid�����鼮
	private Long bookid;
	//����Ǹ���bookid��ѯ���򷵻�һ����
	private Book book;
	
	// ����dao
	private BookDAO bookdao;
	
	private Seller seller;

	public void doBusiness() {
		List<Book> books = null;
		int lines = 0;
	 if(bookid ==null)	
	 {
		 
	  if(seller == null)
	  {
		if(booktype.getTypeid() == BookstoreConstant.BOOKTYPE_NOTSET)
		{
		//��ȡ������
		lines = bookdao.getAllBooksLines(null,null);
		 books = bookdao.queryAllBooks(startrow, maxrows);
		}
		else
		{
		  lines = bookdao.getAllBooksLines(null,booktype);
		  books = bookdao.queryAllBooksByType(booktype);
		}
	  }
	  else
	  {
		  if(booktype.getTypeid() == BookstoreConstant.BOOKTYPE_NOTSET)
			{
			 lines=bookdao.getAllBooksLines(seller, null);
			 books = bookdao.queryBooksBySeller(seller,startrow, maxrows);
			}
			else
			{
			 lines=bookdao.getAllBooksLines(seller, booktype);
			  books = bookdao.queryBooksBySellerAndType(seller, booktype, startrow, maxrows);
			}
	  }
	  booksresult.setTotal(lines);
	  booksresult.setRows(books);
	  
	 }
	 else
	 {
		book = bookdao.queryBookById(bookid);
	 }
		
	}

	@Override
	public Object getResult() {
		if(bookid == null)
		{
		  return booksresult;
		}
		else
		{
			return book;
		}
	}

	@Override
	public void setBizData(Object obj) {
		if(obj instanceof List)
		{
			List params = (List) obj;
			startrow = (Integer) params.get(0);
			maxrows = (Integer) params.get(1);
			if(params.size() == 3 )
			{
			booktype = (BookType) params.get(2);// ���ܻᱨ�����±�Խ��
			}
			else if(params.size() == 4)
			{
			seller = (Seller) params.get(3);
			}
			
		}
		else if(obj.getClass() == Long.class || obj.getClass() == long.class)
		{
			bookid = (Long) obj;
		}
		
	}

	public void setBookdao(BookDAO bookdao) {
		this.bookdao = bookdao;
	}

}
