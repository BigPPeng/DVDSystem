package com.zzu.cui.dao;

import java.util.List;
import com.zzu.cui.entity.Record;
import com.zzu.cui.entity.Record2;

public interface RecordDao {
	//查询指定DVD的借还记录
	public Record queryRecordById(int rid);
	//保存record记录
	public boolean saveRecord(Record record);
	//更新REcord记录
	public boolean updateRecored(Record record);
	//查询所有的DVD记录
	public List<Record2> queryAllRcoreds();
	//查询指定用户的DVD借还记录
	public List<Record2> queryRcoredsByNames(String uname);
	//查看指定DVD借还目录
	public List<Record2> queryRcordByDname(String dname);
	//查看用户的归还记录(已经归还，没有归还)
	public List<Record2> queryUserRcordByReturnTime(boolean flag,String uname); 
}
