package com.study.dao;

import com.study.pojo.Seller;

public interface SellerDAO {
	public void saveOrUpdateSeller(Seller seller);
	public Seller querySellerByUserAndPass(Seller seller);
	
	
}
