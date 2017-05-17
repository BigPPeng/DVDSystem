package com.zzu.cui.dao;

import java.util.List;

import com.zzu.cui.entity.DVD;
//定义一个接口，定义所有有关的DVD操作
public interface DVDDao {
	public boolean saveDVD(DVD dvd);//添加DVD
	public boolean updataDVD(DVD dvd);//更新DVD
	public boolean delDVD(int did);//删除指定DVD
	public List<DVD> queryDVDS();//查询所有DVD
	public List<DVD> queryDVDByName(String dname);//查找指定名字的DVD
	public List<DVD> querySortDVDByLimit(int index,int number);//查询指定起始位置，指定个数DVD
	public DVD queryDVDById(int did);//根据DVD编号查询DVD
	public List<DVD> queryDVDBYsStatus(int status);//根据状态查询DVD
}
