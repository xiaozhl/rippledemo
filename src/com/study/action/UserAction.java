package com.study.action;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.study.bookstore.tools.Tools;
import com.study.business.Business;
import com.study.pojo.User;

public class UserAction {
	private Business login;
	private Business reg;
	private User user;
	private File upload;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Business getLogin() {
		return login;
	}

	public void setLogin(Business login) {
		this.login = login;
	}

	public Business getReg() {
		return reg;
	}

	public void setReg(Business reg) {
		this.reg = reg;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String login()
	{
		if(user == null)
		{
		 throw new RuntimeException("The user object is null...");	
		}
		else
		{
			login.setBizData(user);
			login.doit();
			user = (User) login.getResult();
			if(user !=null)
			{
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("user",user);
			}
			else
			{
				return "LOGIN";
			}
		}
		return "SUCCESS";
	}
	
	public String reg() throws IOException
	{
		if(user == null)
		{
		 throw new RuntimeException("The user object is null...");	
		}
		else
		{
			if(upload == null)
			{
				user.setPic("default.jpg");
			}
			else
			{
				//获得upload的绝对路径
				String path= ServletActionContext.getServletContext().getRealPath("upload");
				String fname = Tools.getRandFileName()+".jpg";
				path = path + "/"+ fname;
				//1、上传文件
				FileUtils.copyFile(upload, new File(path));
				user.setPic(fname);
			}
			reg.setBizData(user);
			reg.doit();
		}
		return "SUCCESS";
	}
	
	
}
