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
	//����һ����ű�������Լ�һ�����
	private JPanel panelTable=null;//���ڱ���Table
	private JTable table=null;//��ѯ��Ϣ��table
	//�����Ҳ�����Լ���ذ�ť
	private JPanel panelbutton=null;//��Ź��ܰ�ť�����
	private JLabel lb_type=null;//��Ų�ѯ�������ı�ǩ
	private JButton btn_Query=null;//��ѯ��ť
	private JButton btn_rent=null;//��DVD�İ�ť
	private JButton btn_exit=null;//�˳���ť
	private JComboBox<String> cb_type=null;//ѡ���ѯ���͵������б��
	
	public UserQueryDVDrecordView() {
		init();
	}
	//��ʼ����ʵ�ְ�ť���
	private void init(){
		this.setTitle("DVD���޲�ѯ��¼");
    	this.setSize(800,500);
    	this.setIconifiable(true);//���������С��
    	this.setClosable(true);//������Թر�
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.setLayout(new BorderLayout());
		//ʵ�������ʾ���
	    panelTable=new JPanel(new BorderLayout());//��ʼ�������壬�����ò��ַ�ʽ
	    table=new JTable();
	    panelTable.setBorder(BorderFactory.createTitledBorder
    			(BorderFactory.createEtchedBorder(null, null), "�������޼�¼��ѯ"));
	    panelTable.add(table);
	    //ʵ���Ҳ���ʾ���
	    panelbutton=new JPanel(new GridLayout(8,1,10,30));
	    panelbutton.setBorder(BorderFactory.createTitledBorder
    			(BorderFactory.createEtchedBorder(null, null), "��ѯ����"));
	    lb_type=new JLabel("��ѯ����");
	    cb_type=new JComboBox<String>(new String[]{"ȫ�����޼�¼","�黹��¼","δ�黹��¼"});//������ݲ�ȷ���Ƿ���ȷ
	    btn_Query=new JButton("��ѯ");
	    btn_rent=new JButton("��DVD");
	    btn_exit=new JButton("�˳�");
	    panelbutton.add(lb_type);
	    panelbutton.add(cb_type);
	    panelbutton.add(btn_Query);
	    panelbutton.add(btn_rent);
	    panelbutton.add(new JLabel());
	    panelbutton.add(new JLabel());
	    panelbutton.add(new JLabel());
	    panelbutton.add(btn_exit);
	    //������������뵽��������
	    this.add(panelTable,BorderLayout.CENTER);
	    this.add(panelbutton, BorderLayout.EAST);
	    //���ô���ɼ�
	    this.setVisible(true);
	    
		
	}

}
