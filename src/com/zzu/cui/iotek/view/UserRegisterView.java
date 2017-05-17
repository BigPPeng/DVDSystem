package com.zzu.cui.iotek.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.zzu.cui.entity.User;
import com.zzu.cui.iotek.biz.impl.UserBiaImpl;

public class UserRegisterView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5073633983391516300L;
	
	private JPanel Panel_main=null;
	private JPanel panel1=null;
	private JPanel panel2=null;
	private JPanel panel3=null;
	private JPanel panel4=null;
	private JPanel panel5=null;
	
	private JLabel lb_name=null;
	private JLabel lb_init_pass=null;
	private JLabel lb_confirm_pass=null;
	
	private JTextField tf_name=null;
	private JPasswordField userPassinit=null;
	private JPasswordField userPassConfirm=null;
	private JButton btn_confirm=null;//确认按钮
	private JButton btn_back=null;//返回按钮
	
	
	public UserRegisterView() {
		init();
		regeisterListener();
	}
	
	private void init(){
		tf_name=new JTextField(15);
		userPassinit=new JPasswordField(15);			
		userPassConfirm=new JPasswordField(15);
		btn_confirm=new JButton("确认提交");
		btn_back= new JButton("退出");
		lb_name=new JLabel(" 用户名 ：");
		lb_name.setFont(new Font("宋体", Font.BOLD, 15));//设置字体
    	lb_init_pass=new JLabel("初始密码：");
    	lb_init_pass.setFont(new Font("宋体", Font.BOLD, 15));//设置字体
		lb_confirm_pass=new JLabel("确认密码：");
		lb_confirm_pass.setFont(new Font("宋体", Font.BOLD, 15));//设置字体
		Panel_main=new JPanel(new GridLayout(5, 1));
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
		
		panel2.add(lb_name);
		panel2.add(tf_name);
		panel3.add(lb_init_pass);
		panel3.add(userPassinit);
		panel4.add(lb_confirm_pass);
		panel4.add(userPassConfirm);
		panel5.add(btn_confirm);
		panel5.add(btn_back);
		
		Panel_main.add(panel1);
		Panel_main.add(panel2);
		Panel_main.add(panel3);
		Panel_main.add(panel4);
		Panel_main.add(panel5);
		
		this.getContentPane().add(Panel_main);
		
		
		this.setTitle("用户注册窗口");
		this.setSize(450, 260);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getRootPane().setDefaultButton(btn_confirm);//设置默认获得焦点的按钮
		
		this.setVisible(true);
	}
	
	
	private void regeisterListener(){
		btn_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=tf_name.getText().trim();//去除空格
				String passWord=(new String(userPassinit.getPassword())).trim();
				String passwordConfirm=(new String(userPassConfirm.getPassword())).trim();
				
				if(name.equals("")){//输入用户名不能为空
					JOptionPane.showConfirmDialog(UserRegisterView.this,"用户名不能为空" );
					return ;
				}else if(passWord.equals("")){//输入密码不可以为空
					JOptionPane.showConfirmDialog(UserRegisterView.this,"密码不能为空" );//从文本框中与密码框中去除字符串方式不一样。
					return ;
				}else if(passwordConfirm.equals("")){//输入确认密码不可以为空
					JOptionPane.showConfirmDialog(UserRegisterView.this,"确认密码不能为空" );
					return ;
				}
				if(!passWord.equals(passwordConfirm)){
					JOptionPane.showConfirmDialog(UserRegisterView.this,"两次密码不一样，请重新输入" );
					//将两个密码框置空处理
					userPassConfirm.setText("");
			        userPassinit.setText("");
					return ;
				}
				
				User user= new User(name,passWord,0);
			    UserBiaImpl biaImpl=new UserBiaImpl();
			    User userGet=biaImpl.login(user);
			    //验证输入的用户名是否存在
			    if(userGet!=null&&userGet.getUname().equals(name)){
			    		JOptionPane.showConfirmDialog(UserRegisterView.this,"注册用户名存在，请重新输入" );
				    	tf_name.setText("");
				    	userPassConfirm.setText("");
				        userPassinit.setText("");
			        return ;
			    }
			    
			    biaImpl.registerUser(user);
			    JOptionPane.showConfirmDialog(UserRegisterView.this,"注册成功，请登录" );
			    UserRegisterView.this.dispose();
				return ;
				
			}
		});
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserRegisterView.this.dispose();
			}
		});
	}
	
	
	

}
