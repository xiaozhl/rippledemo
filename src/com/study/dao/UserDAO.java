package com.study.dao;
import java.util.List;

import com.study.pojo.User;

public interface UserDAO {
	
	public void saveOrUpdateUser(User user);
	public User queryUser(User user);
	public void delUser(String username);
	public List<User> queryAllUsers(int startrow,int maxrow);
	
}
