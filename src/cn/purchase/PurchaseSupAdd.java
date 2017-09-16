package cn.purchase;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.SupplyDao1;
import cn.method.CommondMethods;
import cn.model.common.Supply;

public class PurchaseSupAdd extends JFrame {
	private CommondMethods commondMethods = new CommondMethods();// ͨ�÷���
	DefaultTableModel tm;
	JTable table;
	// ���湩Ӧ����Ϣ
	int sup_id = 0;
	String sup_name="";// ��Ӧ������
	String sup_address="";// ��Ӧ�̵�ַ
	String sup_linkMan="";// ��Ӧ����ϵ��
	String sup_phone;// ��Ӧ����ϵ�绰
	int sup_status = 0;// ����״̬��0:���ֺ���1�����������
	String sup_mark;// ��

	public PurchaseSupAdd(DefaultTableModel tm, JTable table) {
		this.tm = tm;
		this.table = table;
		this.inist();
		this.addpanel();
		// ���ڿ��ӻ�
		this.setVisible(true);
	}

	/**
	 * ��ʼ��ҳ��
	 */
	public void inist() {
		// ���ô��ڴ�С
		this.setSize(400, 400);
		// ���ô�����ʾλ��
		this.setLocation(450, 150);
		// �����޸Ĵ��ڵĴ�С
		this.setResizable(false);
		this.setTitle("������Ӧ����Ϣ");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * �������
	 */
	public void addpanel() {
		// ���������
		JPanel jp = new JPanel();
		// �޲�������
		jp.setLayout(null);
		// ����С
		jp.setSize(1200, 600);

		// ����lable
		JLabel lbname = new JLabel("��˾���ƣ�");
		lbname.setBounds(30, 10, 80, 30);
		JLabel lbaddress = new JLabel("��ַ��");
		lbaddress.setBounds(30, 55, 80, 30);
		JLabel lblinkMan = new JLabel("��ϵ�ˣ�");
		lblinkMan.setBounds(30, 100, 80, 30);
		JLabel lbphone = new JLabel("�绰��");
		lbphone.setBounds(30, 145, 80, 30);
		JLabel lbstatus = new JLabel("����״̬��");
		lbstatus.setBounds(30, 190, 80, 30);
		//���ֺ���
		JLabel company = commondMethods.createLabel("���ֺ���", 130, 190, 80, 30, "����", 14);
		jp.add(company);
		JLabel lbmark = new JLabel("��ע��");
		lbmark.setBounds(30, 240, 80, 30);
		

		// �����ı���
		JTextField tfname = new JTextField();
		tfname.setBounds(130, 10, 180, 30);
		JTextField tfaddress = new JTextField();
		tfaddress.setBounds(130, 55, 180, 30);
		JTextField tflinkMan = new JTextField();
		tflinkMan.setBounds(130, 100, 180, 30);
		JTextField tfphone = new JTextField();
		tfphone.setBounds(130, 145, 180, 30);
		JTextArea tfmark = new JTextArea();
		tfmark.setBounds(130, 240, 180, 60);
		MatteBorder borders = new MatteBorder(1, 1, 1, 1, new Color(122,138,153));
		tfmark.setBorder(borders);

		// ȷ����ť
		JButton btnSure = new JButton("����");
		btnSure.setBounds(130, 320, 60, 30);
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sup_name = tfname.getText().trim();
				sup_address = tfaddress.getText();
				sup_linkMan = tflinkMan.getText();
				sup_phone = tfphone.getText().trim();
				sup_mark = tfmark.getText();
				if("".equals(sup_name)) {
					JOptionPane.showMessageDialog(null, "�����빩Ӧ������");
				}else if("".equals(sup_address)) {
					JOptionPane.showMessageDialog(null, "�����빩Ӧ�̵�ַ");
				}else if("".equals(sup_linkMan)) {
					JOptionPane.showMessageDialog(null, "�����빩Ӧ����ϵ��");
				}else if("".equals(sup_phone)) {
					JOptionPane.showMessageDialog(null, "�����빩Ӧ���ֻ���");
				}else {
					if(!sup_name.matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "��Ӧ�̹�˾����ֻ��Ϊ����");
					}else if(!sup_address.matches("[\\u4e00-\\u9fa5]*")){
						JOptionPane.showMessageDialog(null, "��Ӧ�̵�ַֻ��Ϊ����");
					}else if(!sup_linkMan.matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "��ϵ����Ϣֻ��Ϊ����");
					}else if(!sup_phone.matches("0?(13|14|15|18)[0-9]{9}")) {
						JOptionPane.showMessageDialog(null, "��Ӧ���ֻ����������");
					}else {
						try{
							// ��ȡ���ݿ�����
							SupplyDao1 supd = new SupplyDao1();
							Supply sup = new Supply(sup_name, sup_address, sup_linkMan, sup_phone, 0, sup_mark );
							sup.setSup_id(sup_id);
							int afrow = 0;
							afrow = supd.addSupply(sup);
							String status = sup.getSup_status() == 0 ? "���ֺ���" : "�������";
							if (afrow > 0) {
								Object[] rowData = {afrow, sup_name, sup_address, sup_linkMan, sup_phone, status, sup_mark };
								// �ڱ��������һ������
								tm.addRow(rowData);
								// ����ɹ���ʾ
								JOptionPane.showMessageDialog(null, "����ɹ���");
								// �رյ�ǰ����
								PurchaseSupAdd.this.dispose();
							} else {
								// ����ʧ����ʾ
								JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
							}
						}catch(NumberFormatException meassagenull){
							// ��������������
							JOptionPane.showMessageDialog(null, "������Ϣ����������С����ʵ�������Ŷ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
				
			}
		});

		// ȡ����ť
		JButton btnDel = new JButton("ȡ��");
		btnDel.setBounds(220, 320, 60, 30);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ȡ����ť�¼� �رյ�ǰ����
				PurchaseSupAdd.this.dispose();
			}
		});
		// ��ӽ����
		jp.add(lbname);
		jp.add(lbaddress);
		jp.add(lblinkMan);
		jp.add(lbphone);
		jp.add(lbstatus);
		jp.add(lbmark);

		jp.add(tfname);
		jp.add(tfaddress);
		jp.add(tflinkMan);
		jp.add(tfphone);
		jp.add(tfmark);

		jp.add(btnSure);
		jp.add(btnDel);
		// ��ӽ�����
		this.add(jp);
	}

}
