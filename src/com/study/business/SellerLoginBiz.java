package com.study.business;

import com.study.dao.SellerDAO;
import com.study.pojo.Seller;

public class SellerLoginBiz extends AbstractBusiness {
	//���ҵĶ�����action����,���ͨ�����ݿ�ȶԷ���
	private Seller seller;
	//����ָ����DAO
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
