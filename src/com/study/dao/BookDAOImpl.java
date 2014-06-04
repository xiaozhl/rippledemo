package com.study.dao;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.study.pojo.Book;
import com.study.pojo.BookType;
import com.study.pojo.Seller;

public class BookDAOImpl implements BookDAO {
	private HibernateTemplate hbnTemplate;

	public void setSessionFactory(SessionFactory sf) {
		this.hbnTemplate = new HibernateTemplate(sf);
	}

	public List<Book> queryAllBooks(final int startrow, final int maxrow) {
		List<Book> books = (List<Book>) hbnTemplate
				.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from Book";
						Query query = session.createQuery(hql);
						query.setFirstResult(startrow);
						query.setMaxResults(maxrow);
						return query.list();
					}
				});

		return books;
	}

	public List<Book> queryAllBooksByType(BookType type) {
		String hql = "from Book where type = :t";

		List<Book> books = hbnTemplate.findByNamedParam(hql,
				new String[] { "t" }, new Object[] { type });
		return books;
	}

	public Book queryBookById(long bookid) {
		// TODO Auto-generated method stub
		return (Book) hbnTemplate.get(Book.class, bookid);
	}

	public void saveOrUpdateBook(Book book) {
		hbnTemplate.saveOrUpdate(book);

	}

	public List<Book> queryBooksByIds(List<Long> ids) {
		if (ids.size() == 0) {
			return null;
		}
		String hql = "from Book where bookid in(";
		for (int i = 0; i < ids.size(); i++) {
			if (i < ids.size() - 1) {
				hql = hql + ids.get(i) + ",";
			} else {
				hql = hql + ids.get(i) + ")";
			}
		}

		return hbnTemplate.find(hql);
	}
	
	
	
	public List<Book> queryBooksBySeller(final Seller seller,final int startrow, final int maxrow) {
		List<Book> books = (List<Book>) hbnTemplate.execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from Book where seller = :seller";
				Query query = session.createQuery(hql);
				query.setEntity("seller",seller);
				query.setFirstResult(startrow);
				query.setMaxResults(maxrow);
				
				return query.list();
			}
			
			
		});
		
		
		return books;
	}

	public List<Book> queryBooksBySellerAndType(final Seller seller, final BookType type,
			final int startrow, final int maxrow) {
		List<Book> books = (List<Book>) hbnTemplate.execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from Book where seller = :seller and  type = :t";
				Query query = session.createQuery(hql);
				query.setEntity("seller",seller);
				query.setEntity("t", type);
				query.setFirstResult(startrow);
				query.setMaxResults(maxrow);
				
				return query.list();
			}
			
			
		});
		
		return books;
	}

	public int getAllBooksLines(Seller seller,BookType type) {
		String sql = null;
		if(seller !=null)
		{
		 if(type == null)
		 {
		 sql= "select count(*) from tb_Book where sellerid = :sellerid";
		 }
		 else
		 {
		  sql= "select count(*) from tb_Book where sellerid = :sellerid and type = :booktype";			 
		 }
		}
		else
		{
		 if(type == null)
		 {
		  sql="select count(*) from tb_Book";
		 }
		 else
		 {
		   sql="select count(*) from tb_Book where type = :booktype"; 
		 }
		}
		
		Query query = hbnTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if(seller!=null)
		{
		query.setString("sellerid", seller.getSelleruser());
		}
		if(type != null)
		{
		 query.setInteger("booktype", type.getTypeid());	
		}
		BigInteger line = (BigInteger) query.list().get(0);
		return line.intValue();
	}

	public void removeBooks(List<Long> ids) {
		if (ids.size() != 0) {
			String hql = "delete from Book where bookid in(";
			for (int i = 0; i < ids.size(); i++) {
				if (i < ids.size() - 1) {
					hql = hql + ids.get(i) + ",";
				} else {
					hql = hql + ids.get(i) + ")";
				}
			}
			hbnTemplate.bulkUpdate(hql);
		}
		
		
	}

}
