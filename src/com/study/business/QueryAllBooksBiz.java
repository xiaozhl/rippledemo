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
	// 最终返回的集合
	private BooksResult booksresult = new BooksResult();

	// 从第几行还是，找几个 ，输入参数
	private int startrow = 0;
	private int maxrows = 20;
	//booktype
	private BookType booktype= new BookType(BookstoreConstant.BOOKTYPE_NOTSET,"all");
	//bookid 如果传入值，则按照bookid返回书籍
	private Long bookid;
	//如果是根据bookid查询，则返回一本书
	private Book book;
	
	// 调用dao
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
		//获取所有行
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
			booktype = (BookType) params.get(2);// 可能会报数组下标越界
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
