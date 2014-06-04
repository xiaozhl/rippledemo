package com.study.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.study.pojo.BookType;

public class BookTypeDAOImpl implements BookTypeDAO {
	private HibernateTemplate hbnTemplate;
	public void setSessionFactory(SessionFactory sf)
	{
		this.hbnTemplate = new HibernateTemplate(sf);
	}
	
	public BookType queryBookTypeById(int typeid) {
		
		return (BookType) hbnTemplate.get(BookType.class, typeid);
	}

	public List<BookType> queryBookTypes() {
		String hql = "from BookType";
		List<BookType> list = hbnTemplate.find(hql);
		return list;
	}

	public void removeBookType(int typeid) {
		BookType booktype = this.queryBookTypeById(typeid);
		hbnTemplate.delete(booktype);
	}

	public void saveOrUpdateBookType(BookType type) {
		hbnTemplate.saveOrUpdate(type);

	}

}
