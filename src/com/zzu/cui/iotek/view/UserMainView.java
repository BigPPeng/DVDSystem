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
	
	private JPanel main_panle=null;//�����
	private JPanel wel_panle=null;//��ӭ���
	private JPanel btn_panle=null;//��ť�����
	private JDesktopPane funcDesktop=null;//�������
	
	private JButton btn_query_rent_dvd=null;//��ѯ���ް�ť
	private JButton btn_dvd_record=null;//�鿴���޼�¼��ť
    private JButton btn_exit=null;//�˳���ť
    private JLabel deskLable=null;//���ͼƬ��lable
    private JLabel lb_welcome=null;//��ӭ����
    
    private User user=null;
    
    public UserMainView(User user) {
    	this.user=user;
		init();
		registerListener();
	}
    
    private void init(){
    	main_panle=new JPanel(new BorderLayout());
    	btn_panle=new JPanel(new GridLayout(7, 1,0,35));
    	btn_query_rent_dvd=new JButton("DVD��ѯ���޲���");
    	btn_dvd_record=new JButton("DVD���޼�¼��ѯ");
    	btn_exit=new JButton("�˳�����");
    	
    	btn_panle.add(new JLabel());//�������ı�ǩ�ؼ�
    	btn_panle.add(new JLabel());
    	btn_panle.add(btn_query_rent_dvd);
    	btn_panle.add(btn_dvd_record);
    	btn_panle.add(btn_exit);
    	btn_panle.add(new JLabel());
    	btn_panle.add(new JLabel());
    	//�������߿����
    	btn_panle.setBorder(BorderFactory.createTitledBorder(
    			BorderFactory.createRaisedBevelBorder(),"��ݹ�����"));
    	//��ʼ����ӭ���
    	wel_panle=new JPanel();
    	lb_welcome=new JLabel("��  ӭ  "+user.getUname()+"  ʹ  ��  Ӱ  ��  ��  ��  ��  ��  ϵ  ͳ");
    	lb_welcome.setFont(new Font("����", Font.BOLD, 23));
    	lb_welcome.setBackground(Color.green);
    	wel_panle.add(lb_welcome);
    	
    	//��ʼ���������
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
    	
    	
    	this.setTitle("Ӱ�����޹���ϵͳ");
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
				funcDesktop.add(QdvdView);//��ָ������ͼ��ӵ�ָ����������
				QdvdView.toFront();//��ͼ��ʾ������
				
			}
		});
       btn_dvd_record.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserQueryDVDrecordView QdvdView=new UserQueryDVDrecordView();
				funcDesktop.add(QdvdView);//��ָ������ͼ��ӵ�ָ����������
				QdvdView.toFront();//��ͼ��ʾ������
				
			}
		});
    }
    
    //����һ���߳��࣬ר������ʹ�û�ӭ��ǩ�ƶ�
    private class DynaminThread implements Runnable{

		@Override
		public void run() {
			while(true){
				for(int i=1000;i>-750;i--){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
					lb_welcome.setLocation(i,5);
				}
			}
			
		}
    	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}







