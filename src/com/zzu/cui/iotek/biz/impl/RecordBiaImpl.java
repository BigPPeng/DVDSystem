package com.zzu.cui.iotek.biz.impl;

import java.util.List;

import com.zzu.cui.dao.RecordDao;
import com.zzu.cui.dao.impl.RecordDaoimpl;
import com.zzu.cui.entity.Record2;
import com.zzu.cui.iotek.biz.RecordBiz;

public class RecordBiaImpl implements RecordBiz {
    private RecordDao recordDao=null;
	public RecordBiaImpl() {
		recordDao=new RecordDaoimpl();
	}
	
	@Override
	public List<Record2> queryUserRecords(String uname) {
		
		return recordDao.queryRcoredsByNames(uname);//Uname
	}

	@Override
	public List<Record2> queryDVDRecords(String dname) {
		
		return recordDao.queryRcordByDname(dname);
	}

	@Override
	public List<Record2> queryHasRrturnedRecords(String uname) {
		
		return recordDao.queryUserRcordByReturnTime(true, uname);
	}

	@Override
	public List<Record2> querynoRrturnedRecords(String uname) {
		
		return recordDao.queryUserRcordByReturnTime(false, uname);
	}

	@Override
	public List<Record2> querynoAllRecords() {
	
		return recordDao.queryAllRcoreds();
	}

}
