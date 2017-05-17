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
	private JButton btn_confirm=null;//ȷ�ϰ�ť
	private JButton btn_back=null;//���ذ�ť
	
	
	public UserRegisterView() {
		init();
		regeisterListener();
	}
	
	private void init(){
		tf_name=new JTextField(15);
		userPassinit=new JPasswordField(15);			
		userPassConfirm=new JPasswordField(15);
		btn_confirm=new JButton("ȷ���ύ");
		btn_back= new JButton("�˳�");
		lb_name=new JLabel(" �û��� ��");
		lb_name.setFont(new Font("����", Font.BOLD, 15));//��������
    	lb_init_pass=new JLabel("��ʼ���룺");
    	lb_init_pass.setFont(new Font("����", Font.BOLD, 15));//��������
		lb_confirm_pass=new JLabel("ȷ�����룺");
		lb_confirm_pass.setFont(new Font("����", Font.BOLD, 15));//��������
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
		
		
		this.setTitle("�û�ע�ᴰ��");
		this.setSize(450, 260);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getRootPane().setDefaultButton(btn_confirm);//����Ĭ�ϻ�ý���İ�ť
		
		this.setVisible(true);
	}
	
	
	private void regeisterListener(){
		btn_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=tf_name.getText().trim();//ȥ���ո�
				String passWord=(new String(userPassinit.getPassword())).trim();
				String passwordConfirm=(new String(userPassConfirm.getPassword())).trim();
				
				if(name.equals("")){//�����û�������Ϊ��
					JOptionPane.showConfirmDialog(UserRegisterView.this,"�û�������Ϊ��" );
					return ;
				}else if(passWord.equals("")){//�������벻����Ϊ��
					JOptionPane.showConfirmDialog(UserRegisterView.this,"���벻��Ϊ��" );//���ı��������������ȥ���ַ�����ʽ��һ����
					return ;
				}else if(passwordConfirm.equals("")){//����ȷ�����벻����Ϊ��
					JOptionPane.showConfirmDialog(UserRegisterView.this,"ȷ�����벻��Ϊ��" );
					return ;
				}
				if(!passWord.equals(passwordConfirm)){
					JOptionPane.showConfirmDialog(UserRegisterView.this,"�������벻һ��������������" );
					//������������ÿմ���
					userPassConfirm.setText("");
			        userPassinit.setText("");
					return ;
				}
				
				User user= new User(name,passWord,0);
			    UserBiaImpl biaImpl=new UserBiaImpl();
			    User userGet=biaImpl.login(user);
			    //��֤������û����Ƿ����
			    if(userGet!=null&&userGet.getUname().equals(name)){
			    		JOptionPane.showConfirmDialog(UserRegisterView.this,"ע���û������ڣ�����������" );
				    	tf_name.setText("");
				    	userPassConfirm.setText("");
				        userPassinit.setText("");
			        return ;
			    }
			    
			    biaImpl.registerUser(user);
			    JOptionPane.showConfirmDialog(UserRegisterView.this,"ע��ɹ������¼" );
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
