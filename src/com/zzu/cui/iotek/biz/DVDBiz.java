package com.zzu.cui.iotek.biz;

import java.util.List;

import com.zzu.cui.entity.DVD;

public interface DVDBiz {
	public boolean addDVD(DVD dvd);//添加DVD
	public boolean delDVD(int did);//删除DVD
	public boolean modifyDVD(DVD dvd);//修改DVD
	public List<DVD> queryAllDVDs();//查询所有的DVD
	public List<DVD> ranking_top_five();//查询前五张最受喜爱的dvd
	public List<DVD> queryDVDByName(String dname);//根据名字查询DVD
	public DVD queryDVDById(int did);//根据id查询DVD
	public int lendDVD(int did,int uid);//按照dvd标号和用户编号来租赁DVD
	public int returnDVD(int rid);//还DVD的功能
	public List<DVD> canLendDVD();//查询可以借的DVD
	public List<DVD> haslendDVD();//查询不可借的DVD
}
