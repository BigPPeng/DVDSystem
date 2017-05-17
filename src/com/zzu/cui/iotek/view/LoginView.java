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


//��¼������ͼʵ��
public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4267875764419679463L;
    private JPanel panel_main=null;  //��¼���������
    private JPanel panel_left=null;  //��¼���������
    private JPanel panel_right=null; //��¼���������
    
    private JLabel lb_uname=null;//�û���ǩ
    private JLabel lb_upass=null;//�����ǩ
    private JLabel lb_type=null; //�û����� ��ǩ
    
    private JTextField tf_uname=null;//�˻���
    private JPasswordField tf_pass=null;//�����
    
    private JLabel lb_img=null;//������ʾͼƬ
    
    private JButton btn_login=null;//��¼��ť
    private JButton btn_register=null;//ע�ᰴť
    
    private JComboBox<String>  cb_type=null;//��¼���������˵���
    
    //ҵ��ʵ��
    private UserBiz  userBiz=null;
    
    public LoginView() {
    	userBiz=new UserBiaImpl();
		init();
		regeisterListener();
	}
    
    //��ʼ���ؼ��ķ���
    private void init(){
    	this.setSize(320,220);//���ô����С
    	this.setLocationRelativeTo(null);//���ô����������Ļ������ʾ
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�˳�ʱ�رմ��幦��
    	this.setTitle("��¼����");//���õ�¼��������
    	this.setResizable(false);//�����϶����壬���ɸı��С
    	//��ʼ�����
    	panel_main=new JPanel(new GridLayout(1, 2));//Ĭ����ʽ���ֹ������������ֶ�����
    	panel_left=new JPanel();
    	panel_right=new JPanel(new GridLayout(4, 2, 5, 40));
        //��ʼ���ؼ�
    	tf_uname=new JTextField(8);
    	tf_pass=new JPasswordField(8);
    	cb_type=new JComboBox<String>(new String[]{"��ͨ�û�","����Ա"});
    	btn_login= new JButton("��¼");
    	btn_register=new JButton("ע��");
    	
    	lb_uname=new JLabel("�˻���",JLabel.CENTER);
    	lb_upass=new JLabel("���룺",JLabel.CENTER);
    	lb_type=new JLabel("���ͣ�",JLabel.CENTER);
    	lb_img=new JLabel(new ImageIcon(
    			ClassLoader.getSystemResource("img/1.png")));
    	//��ͼƬ�ŵ���Ӧ�������ȥ
    	panel_left.add(lb_img);
    	
    	panel_right.add(lb_uname);
    	panel_right.add(tf_uname);
    	panel_right.add(lb_upass);
    	panel_right.add(tf_pass);
    	panel_right.add(lb_type);
    	panel_right.add(cb_type);
        panel_right.add(btn_login);
        panel_right.add(btn_register);
        
        //�����������������  �����������
        panel_main.add(panel_left);
        panel_main.add(panel_right);
        //�������봰�����
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
				//��ȡ�û���������
			    String uname=tf_uname.getText().trim();
				String upass=new String(tf_pass.getPassword());
				int type=cb_type.getSelectedIndex();//���ѡ�����򷵻�ѡ�����������һ�з���0�����ơ�δѡ���򷵻�0��
				if(uname.equals("")){
					JOptionPane.showConfirmDialog(LoginView.this,"�û�������Ϊ��" );
					return ;
				}else if(upass.equals("")){
					JOptionPane.showConfirmDialog(LoginView.this,"���벻��Ϊ��" );
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
						JOptionPane.showConfirmDialog(LoginView.this,"�û��������������" );
					}
				}
			}
		});
    }
    
}
