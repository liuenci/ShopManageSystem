package cn.purchase;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.EmployeeDao1;
import cn.dao.purchase.SupplyDao1;
import cn.model.common.Supply;
import cn.model.purchase.EmployeePosition;
import cn.view.purchase.MTable;
/**
 * 1.���ڣ�2017-8-16
 * 2.��Ҫ����
 *  a.����Ա����Ϣ
 *  b.�޸�Ա����Ϣ
 *  c.ɾ��Ա����Ϣ
 *  d.�������Ʋ�ѯԱ����Ϣ
 *  e.ȫ����ѯ
 *  f.�˳�����
 * @author �ܳ���
 *
 */
public class EmployeeManage extends JFrame {
	private JPanel jp;// ������
	private DefaultTableModel tm;// ������ģ��
	private JScrollPane sp;// �������
	private JTable table;// ������

	public EmployeeManage() {
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
		this.setSize(815, 530);
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
		this.setTitle("Ա������");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * �������
	 */
	public void addpanel() {
		// ���������
		jp = new JPanel();
		// �޲�������
		jp.setLayout(null);
		// ����С
		jp.setSize(1200, 600);

		// �������
		String[] str = { "Ա�����", "�˺�����", "Ա������", "�Ա�", "ְ��", "��ϵ�绰", "��������", "����", "״̬" };
		// ��ȡ���ݿ�����
		EmployeeDao1 empd = new EmployeeDao1();
		List<EmployeePosition> lemp = empd.getEmployee();
		Object[][] rows = new Object[lemp.size()][];
		for (int i = 0; i < rows.length; i++) {
			// ��ȡ��Ӧ�̼��϶���
			EmployeePosition emp = lemp.get(i);
			String status = emp.getEmp_status() == 1 ? "��ְ" : "��ְ";
			String empsex = emp.getEmp_sex() == 1 ? "Ů " : "��";
			// ������תΪ����洢
			Object[] obj = { emp.getEmp_id(), emp.getEmp_password(), emp.getEmp_name(), empsex, emp.getPosi_name(),
					emp.getEmp_phone(), emp.getEmp_birthday(), emp.getEmp_salary(), status};
			// ����ά���鸳ֵ
			rows[i] = obj;
		}
		tm = new DefaultTableModel(rows, str);
		table = new JTable(tm);
		table = new MTable(tm);

		sp = new JScrollPane(table);
		sp.setBounds(0, 100, 800, 400);

		// ����Ա����ť
		JButton btnAdd = new JButton();
		btnAdd.setBounds(50, 20, 50, 50);
		// ����ͼƬ����
		Icon iconAdd = new ImageIcon("images\\addSupply.png");
		btnAdd.setIcon(iconAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ���ӹ�Ӧ����Ϣ
				new EmployeeAdd(tm, table);
			}
		});
		// �޸�Ա����Ϣ��ť
		JButton btnUpdate = new JButton();
		btnUpdate.setBounds(130, 20, 50, 50);
		// ����ͼƬ����
		Icon iconUpdae = new ImageIcon("images\\updateSupply.png");
		btnUpdate.setIcon(iconUpdae);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �޸�Ա����Ϣ
				int row = table.getSelectedRow();
				if (row != -1) {
					new EmployeeUpdate(tm, table);
				} else {
					// ��ʾ���
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵĹ�Ӧ�̣�", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ɾ��Ա����ť
		JButton btnDel = new JButton();
		btnDel.setBounds(200, 20, 50, 50);
		// ����ͼƬ����
		Icon iconDel = new ImageIcon("images\\deleteSupply.png");
		btnDel.setIcon(iconDel);
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ɾ��Ա����Ϣ
				int row = table.getSelectedRow();
				if (row != -1) {
					int emp_id = Integer.parseInt(tm.getValueAt(row, 0).toString());
					EmployeeDao1 empd = new EmployeeDao1();
					// �½���Ӧ�̶���
					EmployeePosition emp = new EmployeePosition();
					emp.setEmp_id(emp_id);
					int afrow = 0;
					Object[] obj = { "����ɾ��", "��������" };
					// ѡ����ʾ���
					int res = JOptionPane.showOptionDialog(null, "�Ƿ�ȷ��ɾ����", "ɾ��������ʾ", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					if (res == JOptionPane.YES_OPTION) {
						// �����޸ķ���
						afrow = empd.delEmployee(emp);
					} else {
					}
					if (afrow > 0) {
						// ɾ���ɹ���ʾ���
						JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
						tm.setValueAt("��ְ", row, 8);
						// tm.removeRow(row);
					} else {
						// ����ʧ�ܾ���
						JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�", "ɾ��������ʾ", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���Ĺ�Ӧ�̣�", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// ȫ����ѯ��Ӧ����Ϣ��ť
		JButton btnSelAll = new JButton();
		btnSelAll.setBounds(280, 20, 50, 50);
		// ����ͼƬ����
		Icon iconSelAll = new ImageIcon("images\\queryAll.png");
		btnSelAll.setIcon(iconSelAll);
		btnSelAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �������
				String[] str = { "Ա�����", "�˺�����", "Ա������", "�Ա�", "ְ��", "��ϵ�绰", "��������", "����", "״̬"};
				// ��ȡ���ݿ�����
				EmployeeDao1 empd = new EmployeeDao1();
				List<EmployeePosition> lemp = empd.getEmployee();
				Object[][] rows = new Object[lemp.size()][];
				for (int i = 0; i < rows.length; i++) {
					// ��ȡ��Ӧ�̼��϶���
					EmployeePosition emp = lemp.get(i);
					String status = emp.getEmp_status() == 1 ? "��ְ" : "��ְ";
					String empsex = emp.getEmp_sex() == 1 ? "Ů " : "��";
					// ������תΪ����洢
					Object[] obj = { emp.getEmp_id(), emp.getEmp_password(), emp.getEmp_name(), empsex,
							emp.getPosi_name(), emp.getEmp_phone(), emp.getEmp_birthday(), emp.getEmp_salary(), status};
					// ����ά���鸳ֵ
					rows[i] = obj;
				}
				tm.setDataVector(rows, str);
			}
		});

		JLabel latit = new JLabel("�����빩Ӧ�̹�˾����");
		latit.setBounds(450, 15, 140, 25);
		// ������ѯ�����
		JTextField flserach = new JTextField();
		flserach.setBounds(450, 35, 180, 25);

		// ������ѯ��Ӧ����Ϣ��ť
		JButton btnSel = new JButton();
		btnSel.setBounds(360, 20, 50, 50);
		// ����ͼƬ����
		Icon iconSel = new ImageIcon("images\\querySupply.png");
		btnSel.setIcon(iconSel);
		btnSel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ѯԱ����Ϣ
				String search = "" + flserach.getText();
				search.trim();
				if (!search.equals("")) {
					// �������
					String[] str = { "Ա�����", "�˺�����", "Ա������", "�Ա�", "ְ��", "��ϵ�绰", "��������", "����", "״̬" };
					// ��ȡ���ݿ�����
					EmployeeDao1 empd = new EmployeeDao1();
					List<EmployeePosition> emp = empd.getEmployeeName(search);
					Object[][] rows = new Object[emp.size()][];
					for (int i = 0; i < rows.length; i++) {
						// ��ȡ��Ӧ�̼��϶���
						EmployeePosition emplyee = emp.get(i);
						// ��ȡ��Ӧ�ı��
						String status = emplyee.getEmp_status() == 1 ? "��ְ" : "��ְ";
						String empsex = emplyee.getEmp_sex() == 0 ? "Ů " : "��";
						// ������תΪ����洢
						Object[] obj = { emplyee.getEmp_id(), emplyee.getEmp_password(), emplyee.getEmp_name(), empsex,
								emplyee.getPosi_name(), emplyee.getEmp_phone(), emplyee.getEmp_birthday(),
								emplyee.getEmp_salary(), status};
						// ����ά���鸳ֵ
						rows[i] = obj;
					}
					tm.setDataVector(rows, str);
				} else {
					// ��������������
					JOptionPane.showMessageDialog(EmployeeManage.this, "��ѯ����Ϊ�գ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// �˳�������ť
		JButton btnOut = new JButton();
		btnOut.setBounds(700, 20, 50, 50);
		// ����ͼƬ����
		Icon iconOut = new ImageIcon("images\\exit.png");
		btnOut.setIcon(iconOut);
		btnOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeManage.this.dispose();
			}
		});
		// ��ť��ӽ����
		jp.add(btnAdd);
		jp.add(btnDel);
		jp.add(btnUpdate);
		jp.add(btnSel);
		jp.add(btnSelAll);
		jp.add(latit);
		jp.add(flserach);
		jp.add(btnOut);
		jp.add(sp);
		// ��ӽ�����
		this.add(jp);

	}

	public static void main(String[] args) {
		new EmployeeManage();
	}

}
