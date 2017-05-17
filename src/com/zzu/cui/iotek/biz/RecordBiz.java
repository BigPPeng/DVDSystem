package com.zzu.cui.iotek.biz;

import java.util.List;
import com.zzu.cui.entity.Record2;

public interface RecordBiz {
	//管理员查看指定用户的借还记录
	public List<Record2> queryUserRecords(String uname);
	//查看指定DVD的借还记录
	public List<Record2> queryDVDRecords(String dname);
	
	//查看指定用户的已经归还记录
	public List<Record2> queryHasRrturnedRecords(String uname);
	//查看指定用户的没有归还记录
	public List<Record2> querynoRrturnedRecords(String uname);
	//查看所有的记录
	public List<Record2> querynoAllRecords();
}
