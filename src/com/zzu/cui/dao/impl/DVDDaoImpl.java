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
	//��ѯ���еģģ֣�
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
	//��ѯָ�����ֵ�DVD
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
	//�˷������ڲ�ѯ����DVD,���ǽ������ǰ��(number)λ��DVD 
	public List<DVD> querySortDVDByLimit(int index, int number) {
		//��ѯ��䣬���ݽ��Ĵ�����������
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
	//����DVD״̬��ѯDVD״̬����
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
