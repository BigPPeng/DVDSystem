package com.zzu.cui.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.zzu.cui.dao.UserDao;
import com.zzu.cui.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	//�����û�����
	public boolean saveUser(User user) {
		String sql="insert into users(uname,upass,type)values(?,?,?)";
		List<Object> params= new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		return this.operUpdate(sql, params);

	}

	@Override
	//ɾ���û����
	public boolean deUser(int id) {
		String sql="delete from users where id=?";
		List<Object> params= new ArrayList<Object>();
		params.add(id);
		return this.operUpdate(sql, params);
	}

	@Override
	//�����û����
	public boolean updataUser(User user) {
		String sql="update users set uname=?,upass=?,type=? where id=?";
		//���ڱ�����Ҫ�滻�����ݣ��滻����sql����е�ռλ����
		List<Object> params= new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		params.add(user.getId());
		return this.operUpdate(sql, params);
	}

	@Override
	//��ѯ�û��û�ʵ��
	public User queryUser(User user) {
		List<User> uList=null;
		String sql="select id,uname,upass,type from users where  uname=? and upass=? and type=?";
		List<Object> params= new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		try {
			uList=this.operQuery(sql, params, User.class);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if(uList.size()>0){
			return uList.get(0);
		}
		return null;
	}


}
