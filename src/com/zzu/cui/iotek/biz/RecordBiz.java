package com.zzu.cui.iotek.biz;

import java.util.List;
import com.zzu.cui.entity.Record2;

public interface RecordBiz {
	//����Ա�鿴ָ���û��Ľ軹��¼
	public List<Record2> queryUserRecords(String uname);
	//�鿴ָ��DVD�Ľ軹��¼
	public List<Record2> queryDVDRecords(String dname);
	
	//�鿴ָ���û����Ѿ��黹��¼
	public List<Record2> queryHasRrturnedRecords(String uname);
	//�鿴ָ���û���û�й黹��¼
	public List<Record2> querynoRrturnedRecords(String uname);
	//�鿴���еļ�¼
	public List<Record2> querynoAllRecords();
}
