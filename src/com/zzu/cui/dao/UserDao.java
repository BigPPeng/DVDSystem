package com.zzu.cui.dao;

import com.zzu.cui.entity.*;

public interface UserDao {
	//添加用户
	public boolean saveUser(User user);
	//删除用户
	public boolean deUser(int id);
    //更新用户
	public boolean updataUser(User user);
	//查询用户
	public User queryUser(User user);
	
}
