package com.study.business;

import com.study.dao.UserDAO;
import com.study.pojo.User;

public class LoginBiz extends AbstractBusiness {
	private User user;
	private UserDAO userdao;
	public void doBusiness() {
	
		user = userdao.queryUser(user);

	}

	@Override
	public Object getResult() {
		return user;
	}

	@Override
	public void setBizData(Object obj) {
		this.user = (User) obj;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}
	
	

}
