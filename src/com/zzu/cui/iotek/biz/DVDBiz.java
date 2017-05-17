package com.zzu.cui.iotek.biz;

import java.util.List;

import com.zzu.cui.entity.DVD;

public interface DVDBiz {
	public boolean addDVD(DVD dvd);//���DVD
	public boolean delDVD(int did);//ɾ��DVD
	public boolean modifyDVD(DVD dvd);//�޸�DVD
	public List<DVD> queryAllDVDs();//��ѯ���е�DVD
	public List<DVD> ranking_top_five();//��ѯǰ��������ϲ����dvd
	public List<DVD> queryDVDByName(String dname);//�������ֲ�ѯDVD
	public DVD queryDVDById(int did);//����id��ѯDVD
	public int lendDVD(int did,int uid);//����dvd��ź��û����������DVD
	public int returnDVD(int rid);//��DVD�Ĺ���
	public List<DVD> canLendDVD();//��ѯ���Խ��DVD
	public List<DVD> haslendDVD();//��ѯ���ɽ��DVD
}
