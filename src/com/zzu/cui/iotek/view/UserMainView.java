package com.zzu.cui.iotek.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.zzu.cui.entity.User;

public class UserMainView extends JFrame {

	private static final long serialVersionUID = 980334248622792172L;
	
	private JPanel main_panle=null;//主面板
	private JPanel wel_panle=null;//欢迎面板
	private JPanel btn_panle=null;//按钮主面板
	private JDesktopPane funcDesktop=null;//桌面面板
	
	private JButton btn_query_rent_dvd=null;//查询租赁按钮
	private JButton btn_dvd_record=null;//查看租赁记录按钮
    private JButton btn_exit=null;//退出按钮
    private JLabel deskLable=null;//存放图片的lable
    private JLabel lb_welcome=null;//欢迎标题
    
    private User user=null;
    
    public UserMainView(User user) {
    	this.user=user;
		init();
		registerListener();
	}
    
    private void init(){
    	main_panle=new JPanel(new BorderLayout());
    	btn_panle=new JPanel(new GridLayout(7, 1,0,35));
    	btn_query_rent_dvd=new JButton("DVD查询租赁操作");
    	btn_dvd_record=new JButton("DVD租赁记录查询");
    	btn_exit=new JButton("退出窗口");
    	
    	btn_panle.add(new JLabel());//用来填充的标签控件
    	btn_panle.add(new JLabel());
    	btn_panle.add(btn_query_rent_dvd);
    	btn_panle.add(btn_dvd_record);
    	btn_panle.add(btn_exit);
    	btn_panle.add(new JLabel());
    	btn_panle.add(new JLabel());
    	//设置面板边框外观
    	btn_panle.setBorder(BorderFactory.createTitledBorder(
    			BorderFactory.createRaisedBevelBorder(),"快捷功能区"));
    	//初始化欢迎面板
    	wel_panle=new JPanel();
    	lb_welcome=new JLabel("欢  迎  "+user.getUname()+"  使  用  影  碟  租  赁  管  理  系  统");
    	lb_welcome.setFont(new Font("宋体", Font.BOLD, 23));
    	lb_welcome.setBackground(Color.green);
    	wel_panle.add(lb_welcome);
    	
    	//初始化桌面面板
    	funcDesktop =new JDesktopPane();
    	ImageIcon icon=new ImageIcon("src/img/2.jpg");
    	deskLable =new JLabel(icon);
    	deskLable.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
    	funcDesktop.add(deskLable,new Integer(Integer.MIN_VALUE));
    	
    	main_panle.add(btn_panle,BorderLayout.EAST);
    	main_panle.add(wel_panle,BorderLayout.NORTH);
    	main_panle.add(funcDesktop,BorderLayout.CENTER);
    	
    	EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Thread(new DynaminThread()).start();
			}
		});
    	
    	
    	this.setTitle("影碟租赁管理系统");
    	this.getContentPane().add(main_panle);
    	this.setSize(1000, 650);
    	this.setResizable(false);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    	
    }
    
    private void registerListener(){
    	btn_query_rent_dvd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserQueryRentDVDView QdvdView=new UserQueryRentDVDView();
				funcDesktop.add(QdvdView);//把指定的视图添加到指定的容器中
				QdvdView.toFront();//视图显示在上面
				
			}
		});
       btn_dvd_record.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserQueryDVDrecordView QdvdView=new UserQueryDVDrecordView();
				funcDesktop.add(QdvdView);//把指定的视图添加到指定的容器中
				QdvdView.toFront();//视图显示在上面
				
			}
		});
    }
    
    //这是一个线程类，专门用于使得欢迎标签移动
    private class DynaminThread implements Runnable{

		@Override
		public void run() {
			while(true){
				for(int i=1000;i>-750;i--){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					lb_welcome.setLocation(i,5);
				}
			}
			
		}
    	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}







