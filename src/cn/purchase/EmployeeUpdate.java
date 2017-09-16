package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

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

import cn.dao.purchase.EmployeeDao1;
import cn.dao.purchase.SupplyDao1;
import cn.model.common.Supply;
import cn.model.purchase.EmployeePosition;

/**
 * 1.����2017-8-17 
 * 2.��Ҫ���� 
 *  a.�޸�Ա����Ϣ�����룬ְ�񣬵绰�����ʣ�
 * @author �ܳ���
 *
 */
public class EmployeeUpdate extends JFrame {
	DefaultTableModel tm;
	JTable table;
	// ���湩Ӧ����Ϣ
	int row = -1;
	// ����Ա����Ϣ
	int emp_id;// Ա����ţ���¼�˺�
	String emp_password;// ��¼����
	String emp_name;// Ա������
	int emp_sex;// �Ա� ��0��Ů 1���У�
	int emp_position_id;// Ա��ְ����,���
	String emp_phone;// Ա����ϵ�绰
	Date emp_birthday;// Ա����������
	int emp_salary;// Ա������
	int emp_status;// Ա��״̬��0:���� 1����ְ��
	String emp_mark;// ��ע
	String posi_name;

	public EmployeeUpdate(DefaultTableModel tm, JTable table) {
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
		// ���ô��ھ���
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
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
		emp_id = Integer.parseInt(tm.getValueAt(row, 0).toString());
		emp_password = tm.getValueAt(row, 1).toString();
		posi_name = tm.getValueAt(row, 4).toString();
		emp_phone = tm.getValueAt(row, 5).toString();
		emp_salary = Integer.parseInt(tm.getValueAt(row, 7).toString());
		// ���������
		JPanel jp = new JPanel();
		// �޲�������
		jp.setLayout(null);
		// ����С
		jp.setSize(1200, 600);

		// ����lable
		JLabel lbpassword = new JLabel("���룺");
		lbpassword.setBounds(30, 10, 80, 30);
		JLabel lbposition = new JLabel("ְ��");
		lbposition.setBounds(30, 55, 80, 30);

		JLabel lbphone = new JLabel("�绰��");
		lbphone.setBounds(30, 100, 80, 30);
		JLabel lbsalary = new JLabel("���ʣ�");
		lbsalary.setBounds(30, 145, 80, 30);

		JTextField tfpassword = new JTextField(emp_password);
		tfpassword.setBounds(110, 10, 180, 30);
		JComboBox tfposition = new JComboBox();
		// ���б����ѡ��
		tfposition.addItem("����Ա");
		tfposition.addItem("�ɹ�Ա");
		tfposition.addItem("����Ա");
		tfposition.addItem("�ֹ�Ա");
		tfposition.setBounds(110, 55, 70, 30);
		JTextField tfphone = new JTextField(emp_phone);
		tfphone.setBounds(110, 100, 180, 30);
		JTextField tfsalary = new JTextField("" + emp_salary);
		tfsalary.setBounds(110, 145, 180, 30);

		// ȷ����ť
		JButton btnSure = new JButton("ȷ��");
		btnSure.setBounds(120, 220, 60, 30);
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// // ȷ����ť�¼�,�޸ĵ�ѧ����Ϣ

				if (tfposition.getSelectedItem().equals("����Ա")) {
					emp_position_id = 2;
				} else if (tfposition.getSelectedItem().equals("�ɹ�Ա")) {
					emp_position_id = 1;
				} else if (tfposition.getSelectedItem().equals("����Ա")) {
					emp_position_id = 4;
				} else {
					emp_position_id = 3;
				}

				if ("".equals(tfpassword.getText().trim())) {
					JOptionPane.showMessageDialog(null, "Ա�����벻��Ϊ��");
				} else if ("".equals(tfsalary.getText().trim())) {
					JOptionPane.showMessageDialog(null, "Ա�����ʲ���Ϊ��");
				} else if ("".equals(tfphone.getText())) {
					JOptionPane.showMessageDialog(null, "Ա���ֻ��Ų���Ϊ��");
				} else {
					if (!tfpassword.getText().matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "����ֻ��Ϊ����");
					} else if (!tfsalary.getText().matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "Ա������ֻ��Ϊ������");
					} else if (!tfphone.getText().matches("0?(13|14|15|18)[0-9]{9}")) {
						JOptionPane.showMessageDialog(null, "Ա���ֻ����������");
					} else {
						emp_password = tfpassword.getText();
						posi_name = tfposition.getSelectedItem().toString();
						emp_phone = tfphone.getText();
						emp_salary = Integer.parseInt(tfsalary.getText());
						EmployeeDao1 empd = new EmployeeDao1();
						// �½���Ӧ�̶���
						EmployeePosition emp = new EmployeePosition(emp_password, emp_name, emp_sex, emp_position_id,
								emp_phone, emp_birthday, emp_salary, emp_status, emp_mark);
						emp.setEmp_id(emp_id);
						int afrow = 0;
						// �����޸ķ���
						afrow = empd.updateEmployee(emp);
						if (afrow > 0) {
							JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
							// �޸�table����Ϣ
							tm.setValueAt(emp_password, row, 1);
							tm.setValueAt(posi_name, row, 4);
							tm.setValueAt(emp_phone, row, 5);
							tm.setValueAt(emp_salary, row, 7);

							// �رյ�ǰ����
							EmployeeUpdate.this.dispose();
						} else {
							// ����ʧ����ʾ
							JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
						}
					}

				}

			}
		});
		// ȡ����ť
		JButton btnDel = new JButton("ȡ��");
		btnDel.setBounds(200, 220, 60, 30);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ȡ����ť�¼� �رյ�ǰ����
				EmployeeUpdate.this.dispose();
			}
		});
		// ��ӽ����
		// ��ӽ����
		jp.add(lbpassword);
		jp.add(lbposition);
		jp.add(lbphone);
		jp.add(lbsalary);

		jp.add(tfpassword);
		jp.add(tfposition);
		jp.add(tfphone);
		jp.add(tfsalary);

		jp.add(btnSure);
		jp.add(btnDel);
		// ��ӽ�����
		this.add(jp);
	}

}
