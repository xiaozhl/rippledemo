package com.study.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.study.business.Business;
import com.study.pojo.Seller;

public class SellerAction {
	private Seller seller;
	private Business sellerlogin;
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public void setSellerlogin(Business sellerlogin) {
		this.sellerlogin = sellerlogin;
	}
	
	public String login()
	{
		sellerlogin.setBizData(seller);
		sellerlogin.doit();
		seller = (Seller) sellerlogin.getResult();
		if(seller == null)
		{
			//µÇÂ¼Ê§°Ü
			return "SELLERLOGIN";
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("seller", seller);
		return "SUCCESS";
	}
}
