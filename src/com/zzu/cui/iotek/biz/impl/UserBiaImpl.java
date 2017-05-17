package com.zzu.cui.iotek.biz.impl;

import com.zzu.cui.dao.UserDao;
import com.zzu.cui.dao.impl.UserDaoImpl;
import com.zzu.cui.entity.User;
import com.zzu.cui.iotek.biz.UserBiz;

public class UserBiaImpl implements UserBiz {
	private UserDao userDao=null;//�û����ݲ���ʶ���
	
	//���췽��
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
		      { return 1;}   //ע���û��Ѿ�����
		else{   boolean res=userDao.saveUser(user);
			if(res)   return 2; //ע��ɹ�
			else  	  return 3;//ע��ʧ��
            }	
	}

}
