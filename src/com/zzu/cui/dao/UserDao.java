package com.zzu.cui.dao;

import com.zzu.cui.entity.*;

public interface UserDao {
	//����û�
	public boolean saveUser(User user);
	//ɾ���û�
	public boolean deUser(int id);
    //�����û�
	public boolean updataUser(User user);
	//��ѯ�û�
	public User queryUser(User user);
	
}
