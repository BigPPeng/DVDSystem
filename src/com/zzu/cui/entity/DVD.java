package com.zzu.cui.entity;


//�������ݿ��е���Ϣ��������ó�����DVD���������
public class DVD {
	private int id;       //DVD���ţ�������
	private String dname; //DVD����
	private int dcount;   //DVD����
	private int status;   //DVD�Ƿ�ɽ衣��ʾDVD״̬
	public DVD() {
	}
	
    //����id�Ĺ��췽��
	public DVD(String dname, int dcount, int status) {
		super();
		this.dname = dname;
		this.dcount = dcount;
		this.status = status;
	}
	//��id�ֶεĹ��췽��
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
