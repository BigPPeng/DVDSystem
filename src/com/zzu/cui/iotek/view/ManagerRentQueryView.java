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

	    //DVD查询记录面板
		private JPanel TablePanel=null;//表格面板
		private JTable table=null;//表格控件
		//查询条件面板
		private JPanel ButtonPanel=null;//按钮面板
		private JLabel lb_type=null;//查询类型标签
		private JComboBox<String> cb_type=null;//查询选项下拉菜单
		private JTextField cb_enter=null;//输入框
		private JButton btn_Query=null;//查询按钮
		private JButton btn_exit=null;//退出按钮
		
		private RecordBiz recordBiz=null;
		private List<Record2> recordList=null;
		private Record2InfoTableModel infoTableModel=null;
		
		
		public ManagerRentQueryView() {
			recordBiz=new RecordBiaImpl();
			init();
			regeisterListener();
		}
		
        private void init(){
    		this.setTitle("DVD信息查询");
        	this.setSize(800,500);
        	this.setIconifiable(true);//窗体可以最小化
        	this.setClosable(true);
        	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        	this.setLayout(new BorderLayout());   
        	recordList=new ArrayList<Record2>();
        	
        	TablePanel=new JPanel(new BorderLayout());
        	table =new JTable();
        	//首次绑定List
        	refreshTable(recordList);
        	
        	TablePanel.add(table);
        	TablePanel.setBorder(BorderFactory.createTitledBorder
        			(BorderFactory.createEtchedBorder(null, null), "DVD租赁记录查询"));
        	this.add(TablePanel,BorderLayout.CENTER);
        	ButtonPanel=new JPanel(new GridLayout(9, 1, 10, 20));
        	lb_type=new JLabel("查询类型");
        	cb_type=new JComboBox<String>(new String []{"指定用户","指定DVD"});
        	cb_enter=new JTextField(10);
        	btn_Query=new JButton("查询");
        	btn_exit=new JButton("退出窗口");
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
							"是否确定推出", "确认信息",JOptionPane.YES_NO_OPTION);
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
						JOptionPane.showInternalMessageDialog(ManagerRentQueryView.this, "查询内容不能为空");
						return ;
					}
					//清除数据防止数据累加
					if(recordList!=null){
						recordList.clear();
					}
					if(index==0){//index==0说明选中第一行，则指定用户的DVD租赁记录
						recordList=recordBiz.queryUserRecords(content);
						
					}else if(index==1){//index==1说明选中第二行，则指定DVD租赁记录
						recordList=recordBiz.queryDVDRecords(content);
					}
					refreshTable(recordList);					
				}
			});
          
      
        } 
        
    	private class Record2InfoTableModel implements TableModel{
    		
            private List<Record2> recordList=null;
            
            //构造方法
            public Record2InfoTableModel(List<Record2> recordList) {
    			this.recordList=recordList;
    		}
            
            //用不到不实现
    		@Override
    		public void addTableModelListener(TableModelListener l) {
    			
    		}
            
    		//JTable返回列的数据类型
    		@Override
    		public Class<?> getColumnClass(int columnIndex) {
    			return String.class;
    		}
    		
            //JTable中总共有几列数据
    		@Override
    		public int getColumnCount() {
    			return 6;
    		}
//    		private int id;//记录id
//    		private int did;//影碟id
//    		private String uname;//用户名
//    		private String dname;//影碟名字
//    		private String lendTime;//借出时间
//    		private String returnTime;//归还时间
    		//设置JTable显示的列名
    		@Override
    		public String getColumnName(int columnIndex) {
    			if(columnIndex==0){//第一列返回影碟名字，一次类推
    				return "ID";
    			}else if(columnIndex==1){
    				return "影碟ID";
    			}else if(columnIndex==2){
    				return "影碟名字";
    			}else if(columnIndex==3){
    				return "用户名";
    			}else if(columnIndex==4){
    				return "借出时间";
    			}else if(columnIndex==5){
    				return "归还时间";
    			}else{
    				return  "出错";
    			}
    		}
    		
            //JTable显示数据的行数
    		@Override
    		public int getRowCount() {
    			return recordList.size();
    		}

    		//返回JTble某行某列的值，参数分别为列数与行数
    		@Override
    		public Object getValueAt(int rowIndex, int columnIndex) {
                return null;
    		}
          
    		 //设置单元格是否可以编辑
    		@Override
    		public boolean isCellEditable(int rowIndex, int columnIndex) {
    			return false;//返回false不可编辑
    		}

    		//用不到
    		@Override
    		public void removeTableModelListener(TableModelListener l) {
    			// TODO Auto-generated method stub
    			
    		}
            
    		//如果设置单元格可编辑，则需要写词方法，本处设置不可编辑，所以不编辑。
    		@Override
    		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    			
    		}
    		
    	}

        //刷新JTble并显示数据
        private void refreshTable(List<Record2> recordList){
        	infoTableModel = new Record2InfoTableModel(recordList);
        	table.setModel(infoTableModel);
        }
 
}
