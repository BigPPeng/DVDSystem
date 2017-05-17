package com.zzu.cui.iotek.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.zzu.cui.entity.User;
import com.zzu.cui.iotek.biz.UserBiz;
import com.zzu.cui.iotek.biz.impl.UserBiaImpl;


//登录界面视图实现
public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4267875764419679463L;
    private JPanel panel_main=null;  //登录界面主面板
    private JPanel panel_left=null;  //登录界面左面板
    private JPanel panel_right=null; //登录界面右面版
    
    private JLabel lb_uname=null;//用户标签
    private JLabel lb_upass=null;//密码标签
    private JLabel lb_type=null; //用户类型 标签
    
    private JTextField tf_uname=null;//账户框
    private JPasswordField tf_pass=null;//密码框
    
    private JLabel lb_img=null;//用以显示图片
    
    private JButton btn_login=null;//登录按钮
    private JButton btn_register=null;//注册按钮
    
    private JComboBox<String>  cb_type=null;//登录类型下拉菜单框
    
    //业务实现
    private UserBiz  userBiz=null;
    
    public LoginView() {
    	userBiz=new UserBiaImpl();
		init();
		regeisterListener();
	}
    
    //初始化控件的方法
    private void init(){
    	this.setSize(320,220);//设置窗体大小
    	this.setLocationRelativeTo(null);//设置窗体相对于屏幕居中显示
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出时关闭窗体功能
    	this.setTitle("登录窗口");//设置登录窗口名称
    	this.setResizable(false);//不可拖动窗体，不可改变大小
    	//初始化面板
    	panel_main=new JPanel(new GridLayout(1, 2));//默认流式布局管理器，可以手动更改
    	panel_left=new JPanel();
    	panel_right=new JPanel(new GridLayout(4, 2, 5, 40));
        //初始化控件
    	tf_uname=new JTextField(8);
    	tf_pass=new JPasswordField(8);
    	cb_type=new JComboBox<String>(new String[]{"普通用户","管理员"});
    	btn_login= new JButton("登录");
    	btn_register=new JButton("注册");
    	
    	lb_uname=new JLabel("账户：",JLabel.CENTER);
    	lb_upass=new JLabel("密码：",JLabel.CENTER);
    	lb_type=new JLabel("类型：",JLabel.CENTER);
    	lb_img=new JLabel(new ImageIcon(
    			ClassLoader.getSystemResource("img/1.png")));
    	//把图片放到相应的面板中去
    	panel_left.add(lb_img);
    	
    	panel_right.add(lb_uname);
    	panel_right.add(tf_uname);
    	panel_right.add(lb_upass);
    	panel_right.add(tf_pass);
    	panel_right.add(lb_type);
    	panel_right.add(cb_type);
        panel_right.add(btn_login);
        panel_right.add(btn_register);
        
        //将两个面板放入总面板  左右两个面板
        panel_main.add(panel_left);
        panel_main.add(panel_right);
        //主面板放入窗体面板
        this.getContentPane().add(panel_main);
        this.pack();
        
    	this.setVisible(true);
    	
    }
    
    
    private void regeisterListener(){
    	btn_register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserRegisterView();
				
			}
		});
    	
    	btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取用户名与密码
			    String uname=tf_uname.getText().trim();
				String upass=new String(tf_pass.getPassword());
				int type=cb_type.getSelectedIndex();//如果选择了则返回选择的行数，第一行返回0，类推。未选择则返回0；
				if(uname.equals("")){
					JOptionPane.showConfirmDialog(LoginView.this,"用户名不能为空" );
					return ;
				}else if(upass.equals("")){
					JOptionPane.showConfirmDialog(LoginView.this,"密码不能为空" );
					return ;
				}
				User user=new User(uname,upass,type);
				user=userBiz.login(user);{
					if(user!=null){
						if(user.getType()==0){
							new UserMainView(user);
						}else{
							new ManagerMainView(user);
						}
						LoginView.this.dispose();
					}else{
						JOptionPane.showConfirmDialog(LoginView.this,"用户名或者密码出错" );
					}
				}
			}
		});
    }
    
}
