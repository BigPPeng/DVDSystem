package com.zzu.cui.iotek.biz.impl;

import com.zzu.cui.dao.UserDao;
import com.zzu.cui.dao.impl.UserDaoImpl;
import com.zzu.cui.entity.User;
import com.zzu.cui.iotek.biz.UserBiz;

public class UserBiaImpl implements UserBiz {
	private UserDao userDao=null;//用户数据层访问对象
	
	//构造方法
	public UserBiaImpl() {
			userDao=new UserDaoImpl();
    }
	@Override    
	public User login(User user) {
		return userDao.queryUser(user);
	}

	@Override
	public int registerUser(User user) {
		if(userDao.queryUser(user)!=null)     
		      { return 1;}   //注册用户已经存在
		else{   boolean res=userDao.saveUser(user);
			if(res)   return 2; //注册成功
			else  	  return 3;//注册失败
            }	
	}

}
