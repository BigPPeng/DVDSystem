package com.zzu.cui.iotek.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.zzu.cui.entity.DVD;
import com.zzu.cui.iotek.biz.DVDBiz;
import com.zzu.cui.iotek.biz.impl.DVDBizImpl;
import com.zzu.cui.util.DVDUtil;

public class ManagerDVDAvctionView extends JInternalFrame {
	private static final long serialVersionUID = 294200964901550886L;
	
	//����ʹ�õĿؼ�
	
	//DVD��ѯ��¼���
	private List<DVD> dvdList=null;
	private JPanel leftpanel=null;//�����
	private JPanel TablePanel=null;//������
	private JTable table=null;//���ؼ�
	//��ѯ�������
	private JPanel ButtonPanel=null;//��ť���
	private JLabel lb_type=null;//��ѯ���ͱ�ǩ
	private JComboBox<String> cb_type=null;//��ѯѡ�������˵�
	private JTextField search_type=null;//DVD��ѯ�ı���
	private JButton btn_Query=null;//��ѯ��ť
	private JButton btn_add=null;//��Ӱ�ť
	private JButton btn_update=null;//���°�ť 
	private JButton btn_del=null;//ɾ����ť
	private JButton btn_exit=null;//�˳���ť
	//ѡ�����
	private JPanel TypePanel=null;//ѡ�����
	private JLabel cb_name=null;//���ֱ�ǩ
	private JTextField cx_name=null;//DVD��������ո�
	private JLabel cb_time=null;//�������
	private JTextField cx_time=null;//DVD�����������ո�
	private JLabel cb_status=null;//���״̬
	private JComboBox<String> cx_type=null;//DVD״̬ѡ��
	
	//ʱ����Ӧ���������ʵ������
	private DVDBiz dvdBiz=null;
	
	private DVDInfoTableModel  infoTableModel=null;
	
	
	//���췽��
	public ManagerDVDAvctionView() {
		dvdBiz=new DVDBizImpl();
		init();
		regeisterListener();
	}
	//����
	private void init(){
		this.setTitle("DVD����Ա����");
    	this.setSize(800,500);
    	this.setIconifiable(true);//���������С��
    	this.setClosable(true);
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.setLayout(new BorderLayout());
		dvdList=new ArrayList<DVD>();
    	table=new JTable();
		//ΪTable������ģ�ͣ������������
    	refreshTable(dvdList);
    	
    	leftpanel=new JPanel(new BorderLayout());
		//��ѯ��幹��
		TablePanel=new JPanel(new BorderLayout());	
		TablePanel.add(table);
		//������ñ߿�
		TablePanel.setBorder(BorderFactory.createTitledBorder
    			(BorderFactory.createEtchedBorder(null, null), "DVD��¼��ѯ"));
    	leftpanel.add(TablePanel,BorderLayout.CENTER);
		//ѡ����幹��
    	TypePanel=new JPanel(new GridLayout(1,6, 10, 20));
    	cb_name=new JLabel("DVD����");
    	cx_name=new JTextField(10);
    	cb_time=new JLabel("�������");
    	cx_time=new JTextField(10);
    	cb_status=new JLabel("DVD״̬");
    	cx_type=new JComboBox<>(new String[]{"�ѽ�","�ɽ�","����","����"});
    	TypePanel.add(cb_name);
    	TypePanel.add(cx_name);
    	TypePanel.add(cb_time);
    	TypePanel.add(cx_time);
    	TypePanel.add(cb_status);
    	TypePanel.add(cx_type);
    	leftpanel.add(TypePanel, BorderLayout.SOUTH);
    	this.add(leftpanel, BorderLayout.CENTER);
    	//��ť���
    	ButtonPanel=new JPanel(new GridLayout(9,1,10,20));
    	ButtonPanel.setBorder(BorderFactory.createTitledBorder
    			(BorderFactory.createEtchedBorder(null, null), "��ѯ����"));
    	lb_type=new JLabel("��ѯ����");
    	cb_type=new JComboBox<String>(new String[]{"����DVD","DVD���","DVD����"} );
    	search_type=new JTextField();
    	btn_Query=new JButton("��ѯ");
    	btn_add=new JButton("���");
    	btn_update=new JButton("����");
    	btn_del=new JButton("ɾ��");
    	btn_exit=new JButton("�˳�����");
    	btn_update.setEnabled(false);
    	btn_del.setEnabled(false);
    	ButtonPanel.add(lb_type);
    	ButtonPanel.add(cb_type);
    	ButtonPanel.add(search_type);
    	search_type.setEditable(false);
    	ButtonPanel.add(btn_Query);
    	ButtonPanel.add(btn_add);
    	ButtonPanel.add(btn_update);
    	ButtonPanel.add(btn_del);
    	ButtonPanel.add(new JLabel());
    	ButtonPanel.add(btn_exit);
    	this.add(ButtonPanel, BorderLayout.EAST);

    	this.setVisible(true);
		
	}
	
	
	private void regeisterListener(){
		btn_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int flag=JOptionPane.showInternalConfirmDialog(ManagerDVDAvctionView.this, 
						"�Ƿ�ȷ���Ƴ�", "ȷ����Ϣ",JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					ManagerDVDAvctionView.this.dispose();
				}
			}
		});
		
		btn_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row=table.getSelectedRow();
				int did=(Integer)table.getValueAt(row, 0);
				int flag=JOptionPane.showInternalConfirmDialog(ManagerDVDAvctionView.this, 
						"�Ƿ�ȷ��ɾ��", "ȷ����Ϣ",JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					boolean res=dvdBiz.delDVD(did);
					if(res){
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "ɾ���ɹ�");
					}else{
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "ɾ��ʧ�ܣ�����ϵ����Ա");
					}
				}
				
			}
		});
		
		btn_update.addActionListener(new ActionListener() {
			//���������ʾ�����⣬Ӧ�ò�֤
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String dname=cx_name.getText().trim();
				String dcount=cx_time.getText().trim();
				int status=cx_type.getSelectedIndex();
				if(dname.equals("")){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "DVD���ֲ���Ϊ��");
					return ;
				}
				if(!DVDUtil.isNumber(dcount)){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "DVD�����������������");
					return ;
				}
				int flag=JOptionPane.showInternalConfirmDialog(ManagerDVDAvctionView.this, 
						"�Ƿ�ȷ������DVD", "ȷ����Ϣ",JOptionPane.YES_NO_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					int row=table.getSelectedRow();
				   boolean res=dvdBiz.modifyDVD(new DVD((Integer)table.getValueAt(row, 0),dname,(Integer)row,status));
					if(res){
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "���³ɹ�");
					}else{
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "����ʧ�ܣ�����ϵ����Ա");
					}
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(table.getSelectedRow()!=-1){
					btn_del.setEnabled(true);
					btn_update.setEnabled(true);
				}
				int row=table.getSelectedRow();
				String dname=table.getValueAt(row, 1).toString();
				String dcount=table.getValueAt(row, 2).toString();
				String status=table.getValueAt(row, 3).toString();
				cx_name.setText(dname);
				cx_time.setText(dcount);
                cx_type.setSelectedItem(status);		//�˴�����������		
			}
		});
		
		cb_type.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String item=e.getItem().toString();//"����DVD","DVD���","DVD����"
				search_type.setText("");
				if(item.equals("����DVD")){
					search_type.setEditable(false);
				}else{
					search_type.setEditable(true);
				}
				
			}
		});
		
		btn_Query.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index=cb_type.getSelectedIndex();                                     //0����1DVD���2DVD����
				String content=search_type.getText().trim();
				if(index!=0&&content.equals("")){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "��ѯ���ݲ���Ϊ��");
					return ;
				}
				//��������ݣ���ֹ�����ۼ�
				if(dvdList!=null){
					dvdList.clear();
				}
				if(index==0){
					dvdList=dvdBiz.queryAllDVDs();
				}else if(index==1){
					if(DVDUtil.isNumber(content)){
						DVD dvd=dvdBiz.queryDVDById(Integer.parseInt(content));
						if(dvd!=null){
							dvdList.add(dvd);
						}
					}else{
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "����ı��ֻ��������");
						return;
					}
				}else{
					dvdList=dvdBiz.queryDVDByName(content);
				}
				refreshTable(dvdList);
				btn_del.setEnabled(false);
				btn_update.setEnabled(false);
				
				if(dvdList.size()==0){
					JOptionPane.showInternalMessageDialog(
							ManagerDVDAvctionView.this, "û����Ҫ��ѯ������");
				}
			}
		});

		//���DVD��ť����ʵ��
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String dname=cx_name.getText().trim();
				String dcount=cx_time.getText().trim();
				int status=cx_type.getSelectedIndex();//0�����Ѿ������1������Խ�
				if(dname.equals("")){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "DVD���ֲ���Ϊ��");
					return;
				}
				if(dcount.equals("")){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "�����������Ϊ��");
					return;
				}
				if(!DVDUtil.isNumber(dcount)){
					JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "DVD�������ֻ��������");
					return;
				}
				int flag=JOptionPane.showInternalConfirmDialog(ManagerDVDAvctionView.this, 
						          "�Ƿ����DVD?","ȷ����Ϣ",JOptionPane.YES_NO_CANCEL_OPTION);
				if(flag==JOptionPane.YES_OPTION){
					boolean res=dvdBiz.addDVD(new DVD(dname,new Integer(dcount),status));
					if(res){
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "��ӳɹ���");
					}else{
						JOptionPane.showInternalMessageDialog(ManagerDVDAvctionView.this, "���ʧ�ܣ�����ϵ����Ա!");
					}
				}
			}
		});
	}
	

	private class DVDInfoTableModel implements TableModel{
		
        private List<DVD> dvdList=null;
        
        //���췽��
        public DVDInfoTableModel(List<DVD> dvdList) {
			this.dvdList=dvdList;
		}
        
        //�ò�����ʵ��
		@Override
		public void addTableModelListener(TableModelListener l) {
			
		}
        
		//JTable�����е���������
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return String.class;
		}
		
        //JTable���ܹ��м�������
		@Override
		public int getColumnCount() {
			return 4;
		}
        
		//����JTable��ʾ������
		@Override
		public String getColumnName(int columnIndex) {
			if(columnIndex==0){//��һ�з���Ӱ�����֣�һ������
				return "Ӱ��ID";
			}else if(columnIndex==1){
				return "Ӱ������";
			}else if(columnIndex==2){
				return "Ӱ���������";
			}else if(columnIndex==3){
				return "Ӱ��״̬";
			}else{
				return  "����";
			}
		}
		
        //JTable��ʾ���ݵ�����
		@Override
		public int getRowCount() {
			return dvdList.size();
		}

		//����JTbleĳ��ĳ�е�ֵ�������ֱ�Ϊ����������
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
            DVD dvd=dvdList.get(rowIndex);
            if(columnIndex==0){
            	return dvd.getId();
            }else if(columnIndex==1){
            	return dvd.getDname();
            }
            else if(columnIndex==2){
            	return dvd.getDcount();
            }else if(columnIndex==3){
            	return " "+(dvd.getStatus()==1?"�ɽ�":"�ѽ�");
            }else{
            	return "����";
            }
			
		}
      
		 //���õ�Ԫ���Ƿ���Ա༭
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;//����false���ɱ༭
		}

		//�ò���
		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
        
		//������õ�Ԫ��ɱ༭������Ҫд�ʷ������������ò��ɱ༭�����Բ��༭��
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			
		}
		
	}

    //ˢ��JTble����ʾ����
    private void refreshTable(List<DVD> dvdList){
    	infoTableModel = new DVDInfoTableModel(dvdList);
    	table.setModel(infoTableModel);
    }


}


