package com.zzu.cui.dao;

import java.util.List;

import com.zzu.cui.entity.DVD;
//����һ���ӿڣ����������йص�DVD����
public interface DVDDao {
	public boolean saveDVD(DVD dvd);//���DVD
	public boolean updataDVD(DVD dvd);//����DVD
	public boolean delDVD(int did);//ɾ��ָ��DVD
	public List<DVD> queryDVDS();//��ѯ����DVD
	public List<DVD> queryDVDByName(String dname);//����ָ�����ֵ�DVD
	public List<DVD> querySortDVDByLimit(int index,int number);//��ѯָ����ʼλ�ã�ָ������DVD
	public DVD queryDVDById(int did);//����DVD��Ų�ѯDVD
	public List<DVD> queryDVDBYsStatus(int status);//����״̬��ѯDVD
}
