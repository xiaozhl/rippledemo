package com.study.interceptor;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.study.pojo.User;

public class AuthInterceptor implements Interceptor {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	public String intercept(ActionInvocation inv) throws Exception {
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user");
		String result;
		if(user == null)
		{
			ServletActionContext.getRequest().setAttribute("errormsg", "��δ��¼");
			return "LOGIN";
		}
		else
		{
			//���������Ķ���
			result = inv.invoke();
		}

		return result;
	}

}
