package com.zzu.cui.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
	private static final String  DRIVER="org.sqlite.JDBC";
	private static final String  URL="jdbc:sqlite:d:\\data.db";     //D:\SQLite
	
	/*
	 * �����ݿ⽨�����ӣ��������ݿ����Ӷ���
	 * 
	 * @return ���ݿ����Ӷ���
	 * */
	public Connection getConn(){
		Connection conn=null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/*
	 * �ͷ���Ӧ����Դ
	 * @param rs
	 * @param pstmt
	 * @param conn
	 * */
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn){
		try {
		    if(rs!=null){
			   rs.close();
			}
		    if(pstmt!=null){
		    	pstmt.close();
				}
		    if(conn!=null){
		    	conn.close();
			}
		}catch (SQLException e) {
				e.printStackTrace();
	    }		
	}
	
	//�˷������������ɾ�����в���������true or false
	public boolean operUpdate(String sql, List<Object> params){
		int res=0;//Ӱ�������
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;		
		try {
			conn=getConn();//�������ݿ�����
			pstmt=conn.prepareStatement(sql);//װ��sql���
			if(pstmt!=null){
				//�����У�����ִ��֮ǰ����ռλ���滻��
				for(int i=0;i<params.size();i++){
					pstmt.setObject(i+1,params.get(i));//ռλ����1��ʼ����
				}
			}
			res=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			closeAll(rs,pstmt,conn);
		}
		return res>0? true:false;
	}
	
	
	/*
	*ʹ�÷��ͷ����뷴�����ʵ�����в�ѯ
	 * 
	 * 
	 * */
	public <T> List<T> operQuery(String sql,List<Object> params,Class<T> cls) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<T> date=new ArrayList<T>();
		
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);//װ��sql���
			if(params!=null){
				//�����У�����ִ��֮ǰ����ռλ���滻��
				for(int i=0;i<params.size();i++){
					pstmt.setObject(i+1,params.get(i));
				}
			}
			rs=pstmt.executeQuery();//�Ѳ�ѯ�����ļ�¼��װ�ɶ�Ӧ��ʵ�������
			
			ResultSetMetaData rsd=rs.getMetaData();//�õ���¼��Ԫ���ݶ���
			//ͨ���˶�����Եõ���Ľṹ�������������еĸ������е���������
			while(rs.next()){
				T m = cls.newInstance();
				for(int i=0;i<rsd.getColumnCount();i++){
					String col_name=rsd.getColumnName(i+1);//�������
					Object value=rs.getObject(col_name);//���������Ӧ��ֵ
					Field field=cls.getDeclaredField(col_name);
					field.setAccessible(true);//��˽���������ÿɷ���Ȩ
					field.set(m,value);//�������˽�����Ը�ֵ
				}
				date.add(m);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			closeAll(rs,pstmt,conn);
		}
		return date;
	}
	
	
	
	
	
	
	

}
