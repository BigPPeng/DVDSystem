package com.zzu.cui.iotek.biz;

import com.zzu.cui.entity.User;

public interface UserBiz {
	
	//用户登录，返回登录进去的信息
	public User login(User user);
	//注册用户
	public int registerUser(User user);

}
