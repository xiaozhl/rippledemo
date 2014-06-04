package com.study.business;

import com.study.dao.SellerDAO;
import com.study.pojo.Seller;

public class SellerLoginBiz extends AbstractBusiness {
	//卖家的对象，由action传入,最后通过数据库比对返回
	private Seller seller;
	//调用指定的DAO
	private SellerDAO sellerDAO;
	
	public void doBusiness() {
		seller = sellerDAO.querySellerByUserAndPass(seller);

	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return this.seller;
	}

	@Override
	public void setBizData(Object obj) {
		this.seller = (Seller) obj;
	}

	public void setSellerDAO(SellerDAO sellerDAO) {
		this.sellerDAO = sellerDAO;
	}

}
