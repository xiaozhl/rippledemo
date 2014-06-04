package com.study.dao;

import java.util.List;

import com.study.pojo.BookType;

public interface BookTypeDAO {
	
	public void saveOrUpdateBookType(BookType type);
	public BookType queryBookTypeById(int typeid);
	public List<BookType> queryBookTypes();
	public void removeBookType(int typeid);
}
