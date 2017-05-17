package com.zzu.cui.iotek.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


import com.zzu.cui.entity.Record2;
import com.zzu.cui.iotek.biz.RecordBiz;
import com.zzu.cui.iotek.biz.impl.RecordBiaImpl;

public class ManagerRentQueryView extends JInternalFrame {
	private static final long serialVersionUID = 4698771862384522078L;

	    //DVD��ѯ��¼���
		private JPanel TablePanel=null;//������
		private JTable table=null;//���ؼ�
		//��ѯ�������
		private JPanel ButtonPanel=null;//��ť���
		private JLabel lb_type=null;//��ѯ���ͱ�ǩ
		private JComboBox<String> cb_type=null;//��ѯѡ�������˵�
		private JTextField cb_enter=null;//�����
		private JButton btn_Query=null;//��ѯ��ť
		private JButton btn_exit=null;//�˳���ť
		
		private RecordBiz recordBiz=null;
		private List<Record2> recordList=null;
		private Record2InfoTableModel infoTableModel=null;
		
		
		public ManagerRentQueryView() {
			recordBiz=new RecordBiaImpl();
			init();
			regeisterListener();
		}
		
        private void init(){
    		this.setTitle("DVD��Ϣ��ѯ");
        	this.setSize(800,500);
        	this.setIconifiable(true);//���������С��
        	this.setClosable(true);
        	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        	this.setLayout(new BorderLayout());   
        	recordList=new ArrayList<Record2>();
        	
        	TablePanel=new JPanel(new BorderLayout());
        	table =new JTable();
        	//�״ΰ�List
        	refreshTable(recordList);
        	
        	TablePanel.add(table);
        	TablePanel.setBorder(BorderFactory.createTitledBorder
        			(BorderFactory.createEtchedBorder(null, null), "DVD���޼�¼��ѯ"));
        	this.add(TablePanel,BorderLayout.CENTER);
        	ButtonPanel=new JPanel(new GridLayout(9, 1, 10, 20));
        	lb_type=new JLabel("��ѯ����");
        	cb_type=new JComboBox<String>(new String []{"ָ���û�","ָ��DVD"});
        	cb_enter=new JTextField(10);
        	btn_Query=new JButton("��ѯ");
        	btn_exit=new JButton("�˳�����");
        	ButtonPanel.add(lb_type);
        	ButtonPanel.add(cb_type);
        	ButtonPanel.add(cb_enter);
        	ButtonPanel.add(btn_Query);
        	ButtonPanel.add(new JLabel());
        	ButtonPanel.add(new JLabel());
        	ButtonPanel.add(new JLabel());
        	ButtonPanel.add(new JLabel());
        	ButtonPanel.add(btn_exit);
        	this.add(ButtonPanel, BorderLayout.EAST);

        	this.setVisible(true);

        } 

        private void regeisterListener(){
        	btn_exit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int flag=JOptionPane.showInternalConfirmDialog(ManagerRentQueryView.this, 
							"�Ƿ�ȷ���Ƴ�", "ȷ����Ϣ",JOptionPane.YES_NO_OPTION);
					if(flag==JOptionPane.YES_OPTION){
						ManagerRentQueryView.this.dispose();
					}
				}
			});

        	btn_Query.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int  index=cb_type.getSelectedIndex();
					String content=cb_enter.getText().trim();
					if(content.equals("")){
						JOptionPane.showInternalMessageDialog(ManagerRentQueryView.this, "��ѯ���ݲ���Ϊ��");
						return ;
					}
					//������ݷ�ֹ�����ۼ�
					if(recordList!=null){
						recordList.clear();
					}
					if(index==0){//index==0˵��ѡ�е�һ�У���ָ���û���DVD���޼�¼
						recordList=recordBiz.queryUserRecords(content);
						
					}else if(index==1){//index==1˵��ѡ�еڶ��У���ָ��DVD���޼�¼
						recordList=recordBiz.queryDVDRecords(content);
					}
					refreshTable(recordList);					
				}
			});
          
      
        } 
        
    	private class Record2InfoTableModel implements TableModel{
    		
            private List<Record2> recordList=null;
            
            //���췽��
            public Record2InfoTableModel(List<Record2> recordList) {
    			this.recordList=recordList;
    		}
            
            //�ò�����ʵ��
    		@Override
    		public void addTableModelListener(TableModelListener l) {
    			
    		}
            
    		//JTable�����е���������
    		@Override
    		public Class<?> getColumnClass(int columnIndex) {
    			return String.class;
    		}
    		
            //JTable���ܹ��м�������
    		@Override
    		public int getColumnCount() {
    			return 6;
    		}
//    		private int id;//��¼id
//    		private int did;//Ӱ��id
//    		private String uname;//�û���
//    		private String dname;//Ӱ������
//    		private String lendTime;//���ʱ��
//    		private String returnTime;//�黹ʱ��
    		//����JTable��ʾ������
    		@Override
    		public String getColumnName(int columnIndex) {
    			if(columnIndex==0){//��һ�з���Ӱ�����֣�һ������
    				return "ID";
    			}else if(columnIndex==1){
    				return "Ӱ��ID";
    			}else if(columnIndex==2){
    				return "Ӱ������";
    			}else if(columnIndex==3){
    				return "�û���";
    			}else if(columnIndex==4){
    				return "���ʱ��";
    			}else if(columnIndex==5){
    				return "�黹ʱ��";
    			}else{
    				return  "����";
    			}
    		}
    		
            //JTable��ʾ���ݵ�����
    		@Override
    		public int getRowCount() {
    			return recordList.size();
    		}

    		//����JTbleĳ��ĳ�е�ֵ�������ֱ�Ϊ����������
    		@Override
    		public Object getValueAt(int rowIndex, int columnIndex) {
                return null;
    		}
          
    		 //���õ�Ԫ���Ƿ���Ա༭
    		@Override
    		public boolean isCellEditable(int rowIndex, int columnIndex) {
    			return false;//����false���ɱ༭
    		}

    		//�ò���
    		@Override
    		public void removeTableModelListener(TableModelListener l) {
    			// TODO Auto-generated method stub
    			
    		}
            
    		//������õ�Ԫ��ɱ༭������Ҫд�ʷ������������ò��ɱ༭�����Բ��༭��
    		@Override
    		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    			
    		}
    		
    	}

        //ˢ��JTble����ʾ����
        private void refreshTable(List<Record2> recordList){
        	infoTableModel = new Record2InfoTableModel(recordList);
        	table.setModel(infoTableModel);
        }
 
}
