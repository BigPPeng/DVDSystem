package com.zzu.cui.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.zzu.cui.dao.UserDao;
import com.zzu.cui.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	//保存用户数据
	public boolean saveUser(User user) {
		String sql="insert into users(uname,upass,type)values(?,?,?)";
		List<Object> params= new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		return this.operUpdate(sql, params);

	}

	@Override
	//删除用户语句
	public boolean deUser(int id) {
		String sql="delete from users where id=?";
		List<Object> params= new ArrayList<Object>();
		params.add(id);
		return this.operUpdate(sql, params);
	}

	@Override
	//更新用户语句
	public boolean updataUser(User user) {
		String sql="update users set uname=?,upass=?,type=? where id=?";
		//用于保存需要替换的数据，替换上述sql语句中的占位符？
		List<Object> params= new ArrayList<Object>();
		params.add(user.getUname());
		params.add(user.getUpass());
		params.add(user.getType());
		params.add(user.getId());
		return this.operUpdate(sql, params);
	}

	@Override
	//查询用户用户实现
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
