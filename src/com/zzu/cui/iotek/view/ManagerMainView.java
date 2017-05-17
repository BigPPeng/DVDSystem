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
	private JPanel main_panle=null;//�����
	private JPanel wel_panle=null;//��ӭ���
	private JPanel btn_panle=null;//��ť�����
	private JDesktopPane funcDesktop=null;//�������	
	private JButton btn_DVD_Rent_Query=null;//DVD���޼�¼��ѯ
	private JButton btn_manager_DVDAction=null;//����ԱDVD����
	private JButton btn_exit=null;//�˳���ť
    private JLabel deskLable=null;//���ͼƬ��lable
    private JLabel lb_welcome=null;//��ӭ����
    
    private User user=null;
    
    public ManagerMainView(User user) {
    	 this.user=user;
		init();
		registerListener();
	}
    
    private void init(){

    	
    	//������Ҫ��������
    	main_panle=new JPanel(new BorderLayout());//����ʵ������壬�����ò��ֹ�����
    	wel_panle=new JPanel(new BorderLayout());
    	btn_panle=new JPanel(new GridLayout(7, 1,0,35));
    	//�������߿����
    	btn_panle.setBorder(BorderFactory.createTitledBorder(
    			BorderFactory.createRaisedBevelBorder(),"��ݹ�����"));
    	//���û�ӭ����
    	lb_welcome=new JLabel("��  ӭ  "+user.getUname()+"  ʹ  ��  DVD  ��  ��  ��  ѯ  ϵ  ͳ");
    	lb_welcome.setFont(new Font("����", Font.BOLD, 23));
    	lb_welcome.setBackground(Color.green);

    	//������ʾ��
    	funcDesktop=new JDesktopPane();
    	ImageIcon icon=new ImageIcon("src//img//2.jpg");//����ʵ��
    	deskLable =new JLabel(icon);
    	deskLable.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());//����λ�ã���������湦����
    	funcDesktop.add(deskLable,new Integer(Integer.MIN_VALUE));//��ʾͼ����������
    	wel_panle.add(funcDesktop);
    	
    	
    	//���ð�ť����
    	btn_DVD_Rent_Query=new JButton("DVD���޼�¼��ѯ");
    	btn_manager_DVDAction=new JButton("����ԱDVD����");
    	btn_exit=new JButton("�˳�����");
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
    	//���ڳ�������
    	this.setTitle("Ӱ�����޹���ϵͳ");
    	this.setSize(1000, 650);
    	this.setResizable(false);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�˳�ʱ�ر�
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    	
    }

    private void registerListener(){
    	btn_manager_DVDAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManagerDVDAvctionView QdvdView=new ManagerDVDAvctionView();
				funcDesktop.add(QdvdView);//��ָ������ͼ��ӵ�ָ����������
				QdvdView.toFront();//��ͼ��ʾ������
				
			}
		});
        btn_DVD_Rent_Query.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ManagerRentQueryView QdvdView=new ManagerRentQueryView();
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