package com.zzu.cui.iotek.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import com.zzu.cui.entity.DVD;
import com.zzu.cui.iotek.biz.DVDBiz;
import com.zzu.cui.iotek.biz.impl.DVDBizImpl;
import com.zzu.cui.util.DVDUtil;

public class ManagerDVDAvctionView extends JInternalFrame {
	private static final long serialVersionUID = 294200964901550886L;
	
	//声明使用的控件
	
	//DVD查询记录面板
	private List<DVD> dvdList=null;
	private JPanel leftpanel=null;//主面板
	private JPanel TablePanel=null;//表格面板
	private JTable table=null;//表格控件
	//查询条件面板
	private JPanel ButtonPanel=null;//按钮面板
	private JLabel lb_type=null;//查询类型标签
	private JComboBox<String> cb_type=null;//查询选项下拉菜单
	private JTextField search_type=null;//DVD查询文本框
	private JButton btn_Query=null;//查询按钮
	private JButton btn_add=null;//添加按钮
	private JButton btn_update=null;//更新按钮 
	private JButton btn_del=null;//删除按钮
	private JButton btn_exit=null;//退出按钮
	//选项面板
	private JPanel TypePanel=null;//选项面板
	private JLabel cb_name=null;//名字标签
	private JTextField cx_name=null;//DVD名字输入空格
	private JLabel cb_time=null;//借出次数
	private JTextField cx_time=null;//DVD借出次数输入空格
	private JLabel cb_status=null;//借出状态
	private JComboBox<String> cx_type=null;//DVD状态选项
	
	//时间相应声明的相关实例对象
	private DVDBiz dvdBiz=null;
	
	private DVDInfoTableModel  infoTableModel=null;
	
	
	//构造方法
	public ManagerDVDAvctionView() {
		dvdBiz=new DVDBizImpl();
		init();
		regeisterListener();
	}
	//窗体搭建
	private void init(){
		this.setTitle("DVD管理员操作");
    	this.setSize(800,500);
    	this.setIconifiable(true);//窗体可以最小化
    	this.setClosable(true);
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.setLayout(new BorderLayout());
		dvdList=new ArrayList<DVD>();
    	table=new JTable();
		//为Table绑定数据模型，让其呈现数据
    	refreshTable(dvdList);
    	
    	leftpanel=new JPanel(new BorderLayout());
		//查询面板构建
		TablePanel=new JPanel(new BorderLayout());	
		TablePanel.add(table);
		//面板设置边框
		TablePanel.setBorder(BorderFactory.createTitledBorder
    			(BorderFactory.createEtchedBorder(null, null), "DVD记录查询"));
    	leftpanel.add(TablePanel,BorderLayout.CENTER);
		//选项面板构建
    	TypePanel=new JPanel(new GridLayout(1,6, 10, 20));
    	cb_name=new JLabel("DVD名字");
    	cx_name=new JTextField(10);
    	cb_time=new JLabel("借出次数");
    	cx_time=new JTextField(10);
    	cb_status=new JLabel("DVD状态");
    	cx_type=new JComboBox<>(new String[]{"已借","可借","所有","热门"});
    	TypePanel.add(cb_name);
    	TypePanel.add(cx_name);
    	TypePanel.add(cb_time);
    	TypePanel.add(cx_time);
    	TypePanel.add(cb_status);
    	TypePanel.add(cx_type);
    	leftpanel.add(TypePanel, BorderLayout.SOUTH);
    	this.add(leftpanel, BorderLayout.CENTER);
    	//按钮面板
    	ButtonPanel=new JPanel(new GridLayout(9,1,10,20));
    	ButtonPanel.setBorder(BorderFactory.createTitledBorder
    			(BorderFactory.createEtchedBorder(null, null), "查询条件"));
    	lb_type=new JLabel("查询类型");
    	cb_type=new JComboBox<String>(new String[]{"所有DVD","DVD编号","DVD名字"} );
    	search_type=new JTextField();
    	btn_Query=new JButton("查询");
    	btn_add=new JButton("添加");
    	btn_update=new JButton("更新");
    	btn_del=new JButton("删除");
    	btn_exit=new JButton("退出窗口");
    	btn_update.setEnabled(false);
    	btn_del.setEnabled(false);
    	ButtonPanel.add(lb_type);
    	ButtonPanel.add(cb_type);
    	ButtonPanel.add(search_type);
    	search_type.setEditable(false);
    	ButtonPanel.add(btn_Query);
    	ButtonPanel.add(btn_add);
    	ButtonPanel.add(btn_update);
    	ButtonPanel.add(btn_del);
    	ButtonPanel.add(new JLabel());
    	ButtonPanel.add(btn_exit);
    	this.add(ButtonPanel, BorderLayout.EAST);

    	this.setVisible(true);
		
	}
	
	
	private void regeisterListener(){
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showInternalConfirmDialog(ManagerDVDAvctionView.this, 
						"是否确定推出", "确认信息",JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					ManagerDVDAvctionView.this.dispose();
				}
			}
		});
		
		btn_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				int did=(Integer)table.getValueAt(row, 0);
				int flag=JOptionPane.showInternalConfirmDialog(ManagerDVDAvctionView.this, 
						"是否确定删除", "确认信息",JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					boolean res=dvdBiz.delDVD(did);
					if(res){
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "删除成功");
					}else{
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "删除失败，请联系管理员");
					}
				}
				
			}
		});
		
		btn_update.addActionListener(new ActionListener() {
			//借出次数显示有问题，应该查证
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dname=cx_name.getText().trim();
				String dcount=cx_time.getText().trim();
				int status=cx_type.getSelectedIndex();
				if(dname.equals("")){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "DVD名字不能为空");
					return ;
				}
				if(!DVDUtil.isNumber(dcount)){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "DVD借出次数必须是数字");
					return ;
				}
				int flag=JOptionPane.showInternalConfirmDialog(ManagerDVDAvctionView.this, 
						"是否确定更新DVD", "确认信息",JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					int row=table.getSelectedRow();
				   boolean res=dvdBiz.modifyDVD(new DVD((Integer)table.getValueAt(row, 0),dname,(Integer)row,status));
					if(res){
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "更新成功");
					}else{
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "更新失败，请联系管理员");
					}
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(table.getSelectedRow()!=-1){
					btn_del.setEnabled(true);
					btn_update.setEnabled(true);
				}
				int row=table.getSelectedRow();
				String dname=table.getValueAt(row, 1).toString();
				String dcount=table.getValueAt(row, 2).toString();
				String status=table.getValueAt(row, 3).toString();
				cx_name.setText(dname);
				cx_time.setText(dcount);
                cx_type.setSelectedItem(status);		//此处功能有问题		
			}
		});
		
		cb_type.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String item=e.getItem().toString();//"所有DVD","DVD编号","DVD名字"
				search_type.setText("");
				if(item.equals("所有DVD")){
					search_type.setEditable(false);
				}else{
					search_type.setEditable(true);
				}
				
			}
		});
		
		btn_Query.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=cb_type.getSelectedIndex();                                     //0所有1DVD编号2DVD名字
				String content=search_type.getText().trim();
				if(index!=0&&content.equals("")){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "查询内容不能为空");
					return ;
				}
				//先清除数据，防止数据累加
				if(dvdList!=null){
					dvdList.clear();
				}
				if(index==0){
					dvdList=dvdBiz.queryAllDVDs();
				}else if(index==1){
					if(DVDUtil.isNumber(content)){
						DVD dvd=dvdBiz.queryDVDById(Integer.parseInt(content));
						if(dvd!=null){
							dvdList.add(dvd);
						}
					}else{
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "输入的编号只能是数字");
						return;
					}
				}else{
					dvdList=dvdBiz.queryDVDByName(content);
				}
				refreshTable(dvdList);
				btn_del.setEnabled(false);
				btn_update.setEnabled(false);
				
				if(dvdList.size()==0){
					JOptionPane.showInternalMessageDialog(
							ManagerDVDAvctionView.this, "没有你要查询的内容");
				}
			}
		});

		//添加DVD按钮功能实现
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dname=cx_name.getText().trim();
				String dcount=cx_time.getText().trim();
				int status=cx_type.getSelectedIndex();//0代表已经结出，1代表可以借
				if(dname.equals("")){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "DVD名字不能为空");
					return;
				}
				if(dcount.equals("")){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "借出次数不能为空");
					return;
				}
				if(!DVDUtil.isNumber(dcount)){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "DVD借出次数只能是数字");
					return;
				}
				int flag=JOptionPane.showInternalConfirmDialog(ManagerDVDAvctionView.this, 
						          "是否添加DVD?","确认信息",JOptionPane.YES_NO_CANCEL_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					boolean res=dvdBiz.addDVD(new DVD(dname,new Integer(dcount),status));
					if(res){
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "添加成功！");
					}else{
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "添加失败，请联系管理员!");
					}
				}
			}
		});
	}
	

	private class DVDInfoTableModel implements TableModel{
		
        private List<DVD> dvdList=null;
        
        //构造方法
        public DVDInfoTableModel(List<DVD> dvdList) {
			this.dvdList=dvdList;
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
			return 4;
		}
        
		//设置JTable显示的列名
		@Override
		public String getColumnName(int columnIndex) {
			if(columnIndex==0){//第一列返回影碟名字，一次类推
				return "影碟ID";
			}else if(columnIndex==1){
				return "影碟名字";
			}else if(columnIndex==2){
				return "影碟借出次数";
			}else if(columnIndex==3){
				return "影碟状态";
			}else{
				return  "出错";
			}
		}
		
        //JTable显示数据的行数
		@Override
		public int getRowCount() {
			return dvdList.size();
		}

		//返回JTble某行某列的值，参数分别为列数与行数
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
            DVD dvd=dvdList.get(rowIndex);
            if(columnIndex==0){
            	return dvd.getId();
            }else if(columnIndex==1){
            	return dvd.getDname();
            }
            else if(columnIndex==2){
            	return dvd.getDcount();
            }else if(columnIndex==3){
            	return " "+(dvd.getStatus()==1?"可借":"已借");
            }else{
            	return "出错";
            }
			
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
    private void refreshTable(List<DVD> dvdList){
    	infoTableModel = new DVDInfoTableModel(dvdList);
    	table.setModel(infoTableModel);
    }


}


