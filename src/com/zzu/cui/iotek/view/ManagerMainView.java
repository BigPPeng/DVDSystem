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


public class ManagerMainView extends JFrame {
	private static final long serialVersionUID = 383714449787815633L;
	private JPanel main_panle=null;//主面板
	private JPanel wel_panle=null;//欢迎面板
	private JPanel btn_panle=null;//按钮主面板
	private JDesktopPane funcDesktop=null;//桌面面板	
	private JButton btn_DVD_Rent_Query=null;//DVD租赁记录查询
	private JButton btn_manager_DVDAction=null;//管理员DVD操作
	private JButton btn_exit=null;//退出按钮
    private JLabel deskLable=null;//存放图片的lable
    private JLabel lb_welcome=null;//欢迎标题
    
    private User user=null;
    
    public ManagerMainView(User user) {
    	 this.user=user;
		init();
		registerListener();
	}
    
    private void init(){

    	
    	//声明主要面板标语区
    	main_panle=new JPanel(new BorderLayout());//声明实现主面板，并设置布局管理器
    	wel_panle=new JPanel(new BorderLayout());
    	btn_panle=new JPanel(new GridLayout(7, 1,0,35));
    	//设置面板边框外观
    	btn_panle.setBorder(BorderFactory.createTitledBorder(
    			BorderFactory.createRaisedBevelBorder(),"快捷功能区"));
    	//设置欢迎标语
    	lb_welcome=new JLabel("欢  迎  "+user.getUname()+"  使  用  DVD  租  赁  查  询  系  统");
    	lb_welcome.setFont(new Font("宋体", Font.BOLD, 23));
    	lb_welcome.setBackground(Color.green);

    	//设置显示区
    	funcDesktop=new JDesktopPane();
    	ImageIcon icon=new ImageIcon("src//img//2.jpg");//背景实现
    	deskLable =new JLabel(icon);
    	deskLable.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());//设置位置，相对于桌面功能区
    	funcDesktop.add(deskLable,new Integer(Integer.MIN_VALUE));//显示图层在最下面
    	wel_panle.add(funcDesktop);
    	
    	
    	//设置按钮区域
    	btn_DVD_Rent_Query=new JButton("DVD租赁记录查询");
    	btn_manager_DVDAction=new JButton("管理员DVD操作");
    	btn_exit=new JButton("退出窗口");
    	btn_panle.add(new JLabel());
    	btn_panle.add(new JLabel());
    	btn_panle.add(btn_manager_DVDAction);
    	btn_panle.add(btn_DVD_Rent_Query);
    	btn_panle.add(btn_exit);
    	btn_panle.add(new JLabel());
    	btn_panle.add(new JLabel());
    	
    	main_panle.add(lb_welcome,BorderLayout.NORTH);
    	main_panle.add(btn_panle,BorderLayout.EAST);
    	main_panle.add(wel_panle,BorderLayout.CENTER);
    	
        EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Thread(new DynaminThread()).start();
			}
		});
    	
    	this.getContentPane().add(main_panle);
    	//窗口常规设置
    	this.setTitle("影碟租赁管理系统");
    	this.setSize(1000, 650);
    	this.setResizable(false);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出时关闭
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    	
    }

    private void registerListener(){
    	btn_manager_DVDAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManagerDVDAvctionView QdvdView=new ManagerDVDAvctionView();
				funcDesktop.add(QdvdView);//把指定的视图添加到指定的容器中
				QdvdView.toFront();//视图显示在上面
				
			}
		});
        btn_DVD_Rent_Query.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManagerRentQueryView QdvdView=new ManagerRentQueryView();
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