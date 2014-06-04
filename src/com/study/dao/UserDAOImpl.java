package com.study.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.study.pojo.User;

public class UserDAOImpl implements UserDAO {
	private HibernateTemplate hbnTemplate;

	public void setSessionFactory(SessionFactory sf) {
		this.hbnTemplate = new HibernateTemplate(sf);
	}

	public void delUser(String username) {
		User user = (User) hbnTemplate.get(User.class, username);
		hbnTemplate.delete(user);
	}

	public List<User> queryAllUsers(final int startrow, final int maxrow) {
		List<User> users = (List<User>) hbnTemplate
				.execute(new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from User";
						Query query = session.createQuery(hql);
						query.setFirstResult(startrow);
						query.setMaxResults(maxrow);
						return query.list();
					}

				});
		return users;
	}

	public User queryUser(User user) {
		String hql = "from User where username = :user and password = :pass";
		List<User> list = hbnTemplate.findByNamedParam(hql, new String[] { "user", "pass" },
				new Object[] { user.getUsername(), user.getPassword() });
		if(list.size() > 0)
		{
		return list.get(0);
		}
		
		return null;
	}

	public void saveOrUpdateUser(User user) {
		hbnTemplate.saveOrUpdate(user);

	}

}