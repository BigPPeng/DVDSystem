package com.zzu.cui.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.zzu.cui.dao.RecordDao;
import com.zzu.cui.entity.Record;
import com.zzu.cui.entity.Record2;

public class RecordDaoimpl extends BaseDao implements RecordDao {

	@Override
	//根据记录的Id得到对应的记录
	public Record queryRecordById(int rid) {
		String sql="select id,uid,did,lendTime,returnTime from records where id=?";
		List<Record> rList= null;
		List<Object> params = new ArrayList<Object>();
		params.add(rid);
		try {
			rList=this.operQuery(sql, params, Record.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rList.size()>0){
			return rList.get(0);
		}
		return null;
		
	}

	@Override
	public boolean saveRecord(Record record) {
		String sql="insert into records(uid,did,lendTime,returnTime) values (?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(record.getUid());
		params.add(record.getDid());
		params.add(record.getLendTime());
		params.add(record.getReturnTime());
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateRecored(Record record) {
		String sql="update records set uid=?,did=?,lendTime=?,returnTime=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(record.getUid());
		params.add(record.getDid());
		params.add(record.getLendTime());
		params.add(record.getReturnTime());
		params.add(record.getId());
		return this.operUpdate(sql, params);
	}

	@Override
	public List<Record2> queryAllRcoreds() {
		List<Record2> data=null;
		String sql="select r.id,d.id as did,u.uname,d.dname,r.lendTime,r.returnTime from"
				+ "users u, dvds d, records r where u.id=r.uid and d.id=r.did";
		try {
			data=this.operQuery(sql, null, Record2.class);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public List<Record2> queryRcoredsByNames(String uname) {
		List<Record2> data=null;
		String sql="select r.id,d.id as did,u.uname,d.dname,r.lendTime,r.returnTime from"
				+ "users u, dvds d, records r where u.id=r.uid and d.id=r.did and uname=?";
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		try {
			data=this.operQuery(sql, null, Record2.class);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public List<Record2> queryRcordByDname(String dname) {
		List<Record2> data=null;
		String sql="select r.id,d.id as did,u.uname,d.dname,r.lendTime,r.returnTime from"
				+ "users u, dvds d, records r where u.id=r.uid and d.id=r.did and dname=?";
		List<Object> params = new ArrayList<Object>();
		params.add(dname);
		try {
			data=this.operQuery(sql, null, Record2.class);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return data;
	}

	@Override
	//查看用户的借还记录，包括用户的已归还记录或者未归还记录
	public List<Record2> queryUserRcordByReturnTime(boolean flag, String uname) {
		List<Record2> data=null;
		String sql=null;
		if(flag){
			sql="select r.id,d.id as did,u.uname,d.dname,r.lendTime,r.returnTime from"
					+ "users u, dvds d, records r where u.id=r.uid and d.id=r.did and returnTime is not null and uname=?";
		}else{
			sql="select r.id,d.id as did,u.uname,d.dname,r.lendTime,r.returnTime from"
					+ "users u, dvds d, records r where u.id=r.uid and d.id=r.did and returnTime id null and uname=?";
		}
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		try {
			data=this.operQuery(sql, null, Record2.class);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return data;
	}

}
