package com.study.dao;

import java.util.List;

import com.study.pojo.Book;
import com.study.pojo.BookType;
import com.study.pojo.Seller;

public interface BookDAO {
	
	public void saveOrUpdateBook(Book book);
	public List<Book> queryAllBooks(final int startrow,final int maxrow);
	public int getAllBooksLines(Seller seller,BookType type);
	public List<Book> queryBooksBySeller(Seller seller,final int startrow, final int maxrow);
	public List<Book> queryBooksBySellerAndType(Seller seller,BookType type,final int startrow, final int maxrow);
	public List<Book> queryAllBooksByType(BookType type);
	public Book queryBookById(long bookid);
	public List<Book> queryBooksByIds(List<Long> ids);
	
	public void removeBooks(List<Long> ids);
	
}
