package com.zzu.cui.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.zzu.cui.dao.DVDDao;
import com.zzu.cui.entity.DVD;

public class DVDDaoImpl extends BaseDao implements DVDDao {

	@Override
	public boolean saveDVD(DVD dvd) {
		String sql="insert into dvds(dname,dcount,status)values(?,?,?)";	
		List<Object> params= new ArrayList<Object>();
		params.add(dvd.getDname());
		params.add(dvd.getDcount());
		params.add(dvd.getStatus());
		return this.operUpdate(sql, params);

	}

	@Override
	public boolean updataDVD(DVD dvd) {
		String sql="update dvds set dname=?,dcount=?,status=? where id=?";
		List<Object> params= new ArrayList<Object>();
		params.add(dvd.getDname());
		params.add(dvd.getDcount());
		params.add(dvd.getStatus());
		params.add(dvd.getId());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean delDVD(int did) {
		String sql="delete from dvds where id=?";
		List<Object> params= new ArrayList<Object>();
		params.add(did);
		return this.operUpdate(sql, params);
	}

	@Override
	//查询所有的ＤＶＤ
	public List<DVD> queryDVDS() {
		String sql="select id,dname,dcount,status from dvds";
		List<DVD> dList= null;
		try {
			dList=this.operQuery(sql, null,DVD.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	//查询指定名字的DVD
	public List<DVD> queryDVDByName(String dname) {
		String sql="select id,dname,dcount,status from dvds where dname=?";
		List<DVD> dList= null;
		List<Object> params = new ArrayList<Object>();
		params.add(dname);
		try {
			dList=this.operQuery(sql, params, DVD.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	//此方法用于查询热门DVD,就是借出次数前五(number)位的DVD 
	public List<DVD> querySortDVDByLimit(int index, int number) {
		//查询语句，根据借阅次数降序排序
		String sql="select id,dname,dcount,status from dvds order by dcount desc limit "+index+","+number;
		List<DVD> dList= null;
		try {
			dList=this.operQuery(sql, null, DVD.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

	@Override
	public DVD queryDVDById(int did) {
		String sql="select id,dname,dcount,status from dvds where id=?";
		List<DVD> dList= null;
		List<Object> params = new ArrayList<Object>();
		params.add(did);
		try {
			dList=this.operQuery(sql, params, DVD.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(dList.size()>0){
			return dList.get(0);
		}
		return null;
	}

	@Override
	//根据DVD状态查询DVD状态集合
	public List<DVD> queryDVDBYsStatus(int status) {
		String sql="select id,dcount,status from dvds where status=?";
		List<DVD> dList= null;
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		try {
			dList=this.operQuery(sql, params, DVD.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dList;
	}

}
