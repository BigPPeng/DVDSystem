package com.zzu.cui.entity;


//根据数据库中的信息，来定义该程序中DVD对象的属性
public class DVD {
	private int id;       //DVD表编号，自增长
	private String dname; //DVD名字
	private int dcount;   //DVD数量
	private int status;   //DVD是否可借。表示DVD状态
	public DVD() {
	}
	
    //不带id的构造方法
	public DVD(String dname, int dcount, int status) {
		super();
		this.dname = dname;
		this.dcount = dcount;
		this.status = status;
	}
	//带id字段的构造方法
	public DVD(int id, String dname, int dcount, int status) {
		super();
		this.id = id;
		this.dname = dname;
		this.dcount = dcount;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getDcount() {
		return dcount;
	}
	public void setDcount(int dcount) {
		this.dcount = dcount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

}
