package com.study.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.study.pojo.Seller;

public class SellerDAOImpl implements SellerDAO {
	private HibernateTemplate hbnTemplate;
	public void setSessionFactory(SessionFactory sf)
	{
		this.hbnTemplate = new HibernateTemplate(sf);
	}
	
	public Seller querySellerByUserAndPass(Seller seller) {
		String hql = "from Seller where selleruser = :selleruser and sellerpass = :sellerpass";
		List<Seller> list = hbnTemplate.findByNamedParam(hql, 
				new String[]{"selleruser","sellerpass"}, 
				new Object[]{seller.getSelleruser(),seller.getSellerpass()});
		if(list.size()==0)
		{
			return null;
		}
		return list.get(0);
	}

	public void saveOrUpdateSeller(Seller seller) {
		hbnTemplate.saveOrUpdate(seller);

	}

}
