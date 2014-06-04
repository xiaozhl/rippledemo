package com.study.business;

import com.study.dao.UserDAO;
import com.study.pojo.User;

public class RegBiz extends AbstractBusiness {
	private User user;
	private UserDAO userdao;
	
	public void doBusiness() {
		//可以执行一些校验
		 
		userdao.saveOrUpdateUser(user);

	}

	@Override
	public void setBizData(Object obj) {
		this.user = (User) obj;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}
	
	

}
