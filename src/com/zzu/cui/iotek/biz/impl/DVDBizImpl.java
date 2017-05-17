package com.zzu.cui.iotek.biz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zzu.cui.dao.DVDDao;
import com.zzu.cui.dao.RecordDao;
import com.zzu.cui.dao.impl.DVDDaoImpl;
import com.zzu.cui.dao.impl.RecordDaoimpl;
import com.zzu.cui.entity.DVD;
import com.zzu.cui.entity.Record;
import com.zzu.cui.iotek.biz.DVDBiz;

public class DVDBizImpl implements DVDBiz {
    private DVDDao dvdDao=null;
    private RecordDao recordDao=null;
    
    public DVDBizImpl() {
		dvdDao = new DVDDaoImpl();
		recordDao= new RecordDaoimpl();
	}
	
	@Override
	public boolean addDVD(DVD dvd) {
		return dvdDao.saveDVD(dvd);
	}

	@Override
	public boolean delDVD(int did) {
		return dvdDao.delDVD(did);
	}

	@Override
	public boolean modifyDVD(DVD dvd) {

		return dvdDao.updataDVD(dvd);
	}

	@Override
	public List<DVD> queryAllDVDs() {
		return dvdDao.queryDVDS();
	}

	@Override
	public List<DVD> ranking_top_five() {
		return dvdDao.querySortDVDByLimit(0, 5);
	}

	@Override
	public List<DVD> queryDVDByName(String dname) {
		
		return dvdDao.queryDVDByName(dname);
	}

	@Override
	public DVD queryDVDById(int did) {
		return dvdDao.queryDVDById(did);
	}

	@Override
	public int lendDVD(int did, int uid) {
		DVD dvd=dvdDao.queryDVDById(did);
		if(dvd==null){
			return 0;//代表没有找到对应的DVD
		}else{
			if(dvd.getStatus()==0){
				return 1;//代表查询要借的DVD不可借
			}else{
				dvd.setStatus(0);//更新状态，代表借出
				dvd.setDcount(dvd.getDcount()+1);//借出次数加1
				boolean flag1=dvdDao.updataDVD(dvd);
				Record record= new Record(uid,dvd.getId(),
						new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null);
				boolean flag2=recordDao.saveRecord(record);
				if(flag1&&flag2){
					return 2;//代表借出成功
				}else{
					return 3;//代表借出失败
				}
			}
		}
	}

	@Override
	public int returnDVD(int rid) {
	    Record record=recordDao.queryRecordById(rid);
	    if(record==null){
	    	return 1;//代表输入不正确
	    }else if(record.getReturnTime()!=null){//如果归还时间不为空，则其已经归还了。
	    	return 2;//dvd代表已经归还
	    }else{
	    	record.setReturnTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	    	boolean flag1=recordDao.updateRecored(record);
	    	DVD dvd=dvdDao.queryDVDById(record.getId());//找到对应的dvd
	    	dvd.setStatus(1);//代表可借
	    	boolean flag2=dvdDao.updataDVD(dvd);
	    	if(flag1&&flag2){
	    		return 3;//归还成功
	    	}else{
	    		return 4;//归还失败
	    	}
	    }
	}

	@Override
	public List<DVD> canLendDVD() {
		return dvdDao.queryDVDBYsStatus(1);
	}

	@Override
	public List<DVD> haslendDVD() {
		return dvdDao.queryDVDBYsStatus(0);
	}

}
