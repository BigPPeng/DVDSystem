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
			return 0;//����û���ҵ���Ӧ��DVD
		}else{
			if(dvd.getStatus()==0){
				return 1;//�����ѯҪ���DVD���ɽ�
			}else{
				dvd.setStatus(0);//����״̬��������
				dvd.setDcount(dvd.getDcount()+1);//���������1
				boolean flag1=dvdDao.updataDVD(dvd);
				Record record= new Record(uid,dvd.getId(),
						new SimpleDateFormat("yyyy-MM-dd").format(new Date()), null);
				boolean flag2=recordDao.saveRecord(record);
				if(flag1&&flag2){
					return 2;//�������ɹ�
				}else{
					return 3;//������ʧ��
				}
			}
		}
	}

	@Override
	public int returnDVD(int rid) {
	    Record record=recordDao.queryRecordById(rid);
	    if(record==null){
	    	return 1;//�������벻��ȷ
	    }else if(record.getReturnTime()!=null){//����黹ʱ�䲻Ϊ�գ������Ѿ��黹�ˡ�
	    	return 2;//dvd�����Ѿ��黹
	    }else{
	    	record.setReturnTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	    	boolean flag1=recordDao.updateRecored(record);
	    	DVD dvd=dvdDao.queryDVDById(record.getId());//�ҵ���Ӧ��dvd
	    	dvd.setStatus(1);//����ɽ�
	    	boolean flag2=dvdDao.updataDVD(dvd);
	    	if(flag1&&flag2){
	    		return 3;//�黹�ɹ�
	    	}else{
	    		return 4;//�黹ʧ��
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
