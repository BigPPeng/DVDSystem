package com.zzu.cui.iotek.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class UserQueryDVDrecordView extends JInternalFrame {
	private static final long serialVersionUID = -6375485804161772482L;
	//声明一个存放表格的面板以及一个表格
	private JPanel panelTable=null;//用于保存Table
	private JTable table=null;//查询信息的table
	//声明右侧面板以及相关按钮
	private JPanel panelbutton=null;//存放功能按钮的面板
	private JLabel lb_type=null;//存放查询类型语句的标签
	private JButton btn_Query=null;//查询按钮
	private JButton btn_rent=null;//还DVD的按钮
	private JButton btn_exit=null;//退出按钮
	private JComboBox<String> cb_type=null;//选择查询类型的下来列表框
	
	public UserQueryDVDrecordView() {
		init();
	}
	//初始化并实现按钮面板
	private void init(){
		this.setTitle("DVD租赁查询记录");
    	this.setSize(800,500);
    	this.setIconifiable(true);//窗体可以最小化
    	this.setClosable(true);//窗体可以关闭
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.setLayout(new BorderLayout());
		//实现左侧显示面板
	    panelTable=new JPanel(new BorderLayout());//初始化左侧面板，并设置布局方式
	    table=new JTable();
	    panelTable.setBorder(BorderFactory.createTitledBorder
    			(BorderFactory.createEtchedBorder(null, null), "本人租赁记录查询"));
	    panelTable.add(table);
	    //实现右侧显示面板
	    panelbutton=new JPanel(new GridLayout(8,1,10,30));
	    panelbutton.setBorder(BorderFactory.createTitledBorder
    			(BorderFactory.createEtchedBorder(null, null), "查询条件"));
	    lb_type=new JLabel("查询类型");
	    cb_type=new JComboBox<String>(new String[]{"全部租赁记录","归还记录","未归还记录"});//这个内容不确定是否正确
	    btn_Query=new JButton("查询");
	    btn_rent=new JButton("还DVD");
	    btn_exit=new JButton("退出");
	    panelbutton.add(lb_type);
	    panelbutton.add(cb_type);
	    panelbutton.add(btn_Query);
	    panelbutton.add(btn_rent);
	    panelbutton.add(new JLabel());
	    panelbutton.add(new JLabel());
	    panelbutton.add(new JLabel());
	    panelbutton.add(btn_exit);
	    //将左右两侧加入到主窗体中
	    this.add(panelTable,BorderLayout.CENTER);
	    this.add(panelbutton, BorderLayout.EAST);
	    //设置窗体可见
	    this.setVisible(true);
	    
		
	}

}
