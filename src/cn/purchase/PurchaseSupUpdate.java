package cn.purchase;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
import cn.model.common.Supply;

public class PurchaseSupUpdate extends JFrame {
	DefaultTableModel tm;
	JTable table;
	// ���湩Ӧ����Ϣ
	int row = -1;
	int sup_id = 0;
	String sup_name;// ��Ӧ������
	String sup_address;// ��Ӧ�̵�ַ
	String sup_linkMan;// ��Ӧ����ϵ��
	String sup_phone;// ��Ӧ����ϵ�绰
	int sup_status = 0;// ����״̬��0:���ֺ���1�����������
	String sup_mark;// ��

	public PurchaseSupUpdate(DefaultTableModel tm, JTable table) {
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
		this.setSize(350, 300);
		// ���ô�����ʾλ��
		this.setLocation(450, 150);
		// �����޸Ĵ��ڵĴ�С
		this.setResizable(false);
		this.setTitle("��Ӧ����Ϣ�޸�");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * �������
	 */
	public void addpanel() {
		row = table.getSelectedRow();
		sup_id = Integer.parseInt(tm.getValueAt(row, 0).toString());
		sup_address = tm.getValueAt(row, 2).toString();
		sup_linkMan = tm.getValueAt(row, 3).toString();
		sup_phone = tm.getValueAt(row, 4).toString();
		sup_mark = tm.getValueAt(row, 6).toString();
		// ���������
		JPanel jp = new JPanel();
		// �޲�������
		jp.setLayout(null);
		// ����С
		jp.setSize(1200, 600);

		// ����lable
		JLabel lbaddress = new JLabel("��ַ��");
		lbaddress.setBounds(30, 10, 80, 30);
		JLabel lblinkMan = new JLabel("��ϵ�ˣ�");
		lblinkMan.setBounds(30, 55, 80, 30);
		JLabel lbphone = new JLabel("�绰��");
		lbphone.setBounds(30, 100, 80, 30);
		JLabel lbmark = new JLabel("��ע��");
		lbmark.setBounds(30, 145, 80, 30);

		// �����ı���
		JTextField tfid = new JTextField(sup_id);

		JTextField tfaddress = new JTextField(sup_address);
		tfaddress.setBounds(110, 10, 180, 30);
		JTextField tflinkMan = new JTextField(sup_linkMan);
		tflinkMan.setBounds(110, 55, 180, 30);
		JTextField tfphone = new JTextField(sup_phone);
		tfphone.setBounds(110, 100, 180, 30);
		JTextArea tfmark = new JTextArea(sup_mark);
		tfmark.setBounds(110, 145, 180, 60);
		MatteBorder borders = new MatteBorder(1, 1, 1, 1, new Color(122, 138, 153));
		tfmark.setBorder(borders);

		// ȷ����ť
		JButton btnSure = new JButton("ȷ��");
		btnSure.setBounds(120, 220, 60, 30);
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ȷ����ť�¼�,�޸ĵ�ѧ����Ϣ
				sup_address = tfaddress.getText();
				sup_linkMan = tflinkMan.getText();
				sup_phone = tfphone.getText();
				sup_mark = tfmark.getText();
				if ("".equals(sup_name)) {
					JOptionPane.showMessageDialog(null, "�����빩Ӧ������");
				} else if ("".equals(sup_address)) {
					JOptionPane.showMessageDialog(null, "�����빩Ӧ�̵�ַ");
				} else if ("".equals(sup_linkMan)) {
					JOptionPane.showMessageDialog(null, "�����빩Ӧ����ϵ��");
				} else if ("".equals(sup_phone)) {
					JOptionPane.showMessageDialog(null, "�����빩Ӧ���ֻ���");
				} else {
					if (!sup_address.matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "��Ӧ�̵�ַ��������");
					} else if (!sup_linkMan.matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "��ϵ����Ϣ��������");
					} else if (!sup_phone.matches("0?(13|14|15|18)[0-9]{9}")) {
						JOptionPane.showMessageDialog(null, "��Ӧ���ֻ��Ŵ���");
					} else {
						SupplyDao1 supd = new SupplyDao1();
						// �½���Ӧ�̶���
						Supply sup = new Supply(sup_name, sup_address, sup_linkMan, sup_phone, sup_status, sup_mark);
						sup.setSup_id(sup_id);
						int afrow = 0;
						// �����޸ķ���
						afrow = supd.updateSupply(sup);
						if (afrow > 0) {
							JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
							// �޸�table����Ϣ
							tm.setValueAt(sup_address, row, 2);
							tm.setValueAt(sup_linkMan, row, 3);
							tm.setValueAt(sup_phone, row, 4);
							tm.setValueAt(sup_mark, row, 6);

							// �رյ�ǰ����
							PurchaseSupUpdate.this.dispose();
						} else {
							// ����ʧ����ʾ
							JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		}

		);
		// ȡ����ť
		JButton btnDel = new JButton("ȡ��");
		btnDel.setBounds(200, 220, 60, 30);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ȡ����ť�¼� �رյ�ǰ����
				PurchaseSupUpdate.this.dispose();
			}
		});
		// ��ӽ����
		// ��ӽ����
		jp.add(lbaddress);
		jp.add(lblinkMan);
		jp.add(lbphone);
		jp.add(lbmark);

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
