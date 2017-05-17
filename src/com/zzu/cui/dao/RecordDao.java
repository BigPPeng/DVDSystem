package com.zzu.cui.dao;

import java.util.List;
import com.zzu.cui.entity.Record;
import com.zzu.cui.entity.Record2;

public interface RecordDao {
	//��ѯָ��DVD�Ľ軹��¼
	public Record queryRecordById(int rid);
	//����record��¼
	public boolean saveRecord(Record record);
	//����REcord��¼
	public boolean updateRecored(Record record);
	//��ѯ���е�DVD��¼
	public List<Record2> queryAllRcoreds();
	//��ѯָ���û���DVD�軹��¼
	public List<Record2> queryRcoredsByNames(String uname);
	//�鿴ָ��DVD�軹Ŀ¼
	public List<Record2> queryRcordByDname(String dname);
	//�鿴�û��Ĺ黹��¼(�Ѿ��黹��û�й黹)
	public List<Record2> queryUserRcordByReturnTime(boolean flag,String uname); 
}
