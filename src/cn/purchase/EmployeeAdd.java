package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.EmployeeDao1;
import cn.liuenci.swing.DateChooser;
import cn.model.purchase.EmployeePosition;

/**
 * 1.���ڣ�2017-8-18 
 * 2.��Ҫ���� 
 *  a.�������ݣ�������Ʒ
 *  b.����ƶ������֤
 *  c.������ȷ����֤
 * 
 * @author �ܳ���
 *
 */
public class EmployeeAdd extends JFrame {
	DefaultTableModel tm;
	JTable table;
	// ����Ա����Ϣ
	int emp_id;// Ա����ţ���¼�˺�
	String emp_password;// ��¼����
	JLabel notePwd;// ������ʾ
	String emp_name;// Ա������
	JLabel noteName;// ������ʾ��
	int emp_sex;// �Ա� ��0��Ů 1���У�
	int emp_position_id;// Ա��ְ����,���
	String emp_phone;// Ա����ϵ�绰
	JLabel notePhone;// �绰��ʾ
	Date emp_birthday;// Ա����������
	JLabel noteBirthday;// ������ʾ���
	int emp_salary;// Ա������
	JLabel noteSalary;// ��������
	int emp_status;// Ա��״̬��0:���� 1����ְ��
	String emp_mark;// ��ע
	JButton btnSure;

	public EmployeeAdd(DefaultTableModel tm, JTable table) {
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
		this.setSize(450, 300);
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
		this.setTitle("����Ա����Ϣ");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// ������巽��
	public JPanel createPanel(int x, int y, int width, int height) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		Color color = new Color(237, 242, 248);
		jPanel.setBackground(color);
		jPanel.setBounds(x, y, width, height);
		jPanel.setBorder(BorderFactory.createEtchedBorder());
		return jPanel;
	}

	// ������ǩ����
	public JLabel createLabel(String name, int x, int y, int width, int height, String fontName, int fontSize) {
		JLabel jLabel = new JLabel(name);
		jLabel.setBounds(x, y, width, height);
		jLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
		return jLabel;
	}

	// ������ť����
	public JButton createButton(String name, int x, int y, int width, int height, String fontName, int fontSize) {
		JButton jButton = new JButton(name);
		jButton.setBounds(x, y, width, height);
		jButton.setBorder(new MetalBorders.ButtonBorder());
		Color color = new Color(129, 194, 214);
		jButton.setBackground(color);
		return jButton;
	}

	// �����ı��򷽷�
	public JTextField createTextField(String text, int x, int y, int width, int height, String fontName, int fontSize,
			Color color) {
		JTextField jTextField = new JTextField();
		jTextField.setBounds(x, y, width, height);
		jTextField.setText(text);
		jTextField.setFont(new Font(text, Font.PLAIN, fontSize));
		jTextField.setBackground(color);
		return jTextField;
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
		JLabel lbpassword = createLabel("���룺", 30, 10, 40, 30, "����", 12);
		JLabel lbname = createLabel("������", 30, 55, 40, 30, "����", 12);
		JLabel lbphone = createLabel("�绰��", 30, 100, 40, 30, "����", 12);
		JLabel lbsalary = createLabel("���ʣ�", 30, 145, 40, 30, "����", 12);
		JLabel lbsex = createLabel("�Ա�", 250, 20, 40, 30, "����", 12);
		JLabel lbposition = createLabel("ְλ��", 250, 55, 40, 30, "����", 12);
		JLabel lbstatus = createLabel("����״̬��", 250, 100, 60, 30, "����", 12);
		JLabel lbbirthday = createLabel("�������ڣ�", 250, 145, 60, 30, "����", 12);

		// ������ʾ���
		JPasswordField tfpassword = new JPasswordField();
		tfpassword.setBounds(80, 10, 100, 25);
		tfpassword.setBackground(new Color(238, 238, 238));
		MatteBorder borderpassword = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfpassword.setBorder(borderpassword);
		tfpassword.setName("����");
		notePwd = createLabel("", 80, 35, 160, 25, "����", 12);
		notePwd.setForeground(Color.red);
		jp.add(notePwd);
		tfpassword.addFocusListener(new Note(notePwd, tfpassword));

		// ������ʾ���
		JTextField tfname = createTextField("", 80, 55, 100, 25, "����", 12, Color.WHITE);
		tfname.setBackground(new Color(238, 238, 238));
		MatteBorder bordername = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfname.setBorder(bordername);
		tfname.setName("����");
		noteName = createLabel("", 80, 75, 160, 25, "����", 12);
		noteName.setForeground(Color.red);
		jp.add(noteName);
		tfname.addFocusListener(new Note(noteName, tfname));

		JTextField tfphone = createTextField("", 80, 100, 100, 25, "����", 12, Color.WHITE);
		tfphone.setBackground(new Color(238, 238, 238));
		MatteBorder borderphone = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfphone.setBorder(borderphone);
		tfphone.setName("�绰");
		notePhone = createLabel("", 80, 125, 160, 25, "����", 12);
		notePhone.setForeground(Color.red);
		jp.add(notePhone);
		tfphone.addFocusListener(new Note(notePhone, tfphone));

		// ������ʾ���
		JTextField tfsalary = createTextField("", 80, 145, 100, 25, "����", 12, Color.WHITE);
		tfsalary.setBackground(new Color(238, 238, 238));
		MatteBorder bordersalary = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfsalary.setBorder(bordersalary);
		tfsalary.setName("����");
		noteSalary = createLabel("", 80, 165, 160, 25, "����", 12);
		noteSalary.setForeground(Color.red);
		jp.add(noteSalary);
		tfsalary.addFocusListener(new Note(noteSalary, tfsalary));

		JComboBox tfsex = new JComboBox();
		// ���б����ѡ��
		tfsex.addItem("��");
		tfsex.addItem("Ů");
		tfsex.setBounds(310, 20, 50, 20);
		JComboBox tfposition = new JComboBox();
		// ���б����ѡ��
		tfposition.addItem("����Ա");
		tfposition.addItem("�ɹ�Ա");
		tfposition.addItem("����Ա");
		tfposition.addItem("�ֹ�Ա");
		tfposition.setBounds(310, 60, 65, 20);

		// ����״̬��ʾ���
		JLabel tfstatus = createLabel("��ְ", 310, 100, 180, 25, "����", 12);
		JTextField tfbirthday = createTextField("", 310, 145, 100, 25, "����", 12, Color.WHITE);
		tfbirthday.setBackground(new Color(238, 238, 238));
		MatteBorder border = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfbirthday.setBorder(border);
		DateChooser d = DateChooser.getInstance("yyyy-MM-dd");
		d.register(tfbirthday);
		tfbirthday.setName("����");
		noteBirthday = createLabel("", 310, 165, 160, 25, "����", 12);
		noteBirthday.setForeground(Color.red);
		jp.add(noteBirthday);
		tfbirthday.addFocusListener(new Note(noteBirthday, tfbirthday));

		// ȷ����ť
		btnSure = new JButton("����");
		btnSure.setBounds(120, 200, 60, 30);
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// ȷ����ť�¼�
				// ������Ӧ����Ϣ
				if (tfsex.getSelectedItem().equals("��")) {
					emp_sex = 0;
				} else if (tfsex.getSelectedItem().equals("Ů")) {
					emp_sex = 1;
				}
				if (tfposition.getSelectedItem().equals("����Ա")) {
					emp_position_id = 2;
				} else if (tfposition.getSelectedItem().equals("�ɹ�Ա")) {
					emp_position_id = 1;
				} else if (tfposition.getSelectedItem().equals("����Ա")) {
					emp_position_id = 4;
				} else if (tfposition.getSelectedItem().equals("�ֹ�Ա")) {
					emp_position_id = 3;
				}

				emp_status = 1;
				if ("".equals(tfpassword.getText())) {
					JOptionPane.showMessageDialog(null, "Ա�����벻��Ϊ��");
				} else if ("".equals(tfname.getText())) {
					JOptionPane.showMessageDialog(null, "Ա����������Ϊ��");
				} else if ("".equals(tfbirthday.getText())) {
					JOptionPane.showMessageDialog(null, "Ա���������ڲ���Ϊ��");
				} else if ("".equals(tfsalary.getText())) {
					JOptionPane.showMessageDialog(null, "Ա�����ʲ���Ϊ��");
				} else if ("".equals(tfphone.getText())) {
					JOptionPane.showMessageDialog(null, "Ա���ֻ��Ų���Ϊ��");
				} else {

					// emp_salary = Integer.parseInt(tfsalary.getText());
					if (!tfpassword.getText().matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "����ֻ��Ϊ����");
					} else if (!tfname.getText().matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "Ա������ֻ��Ϊ����");
					} else if (!tfphone.getText().matches("0?(13|14|15|18)[0-9]{9}")) {
						JOptionPane.showMessageDialog(null, "Ա���ֻ����������");
					} else if (!("" + tfsalary.getText()).matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "����ֻ��Ϊ����");
					} else {
						emp_password = tfpassword.getText();
						emp_name = tfname.getText();
						emp_phone = tfphone.getText();
						emp_salary = Integer.parseInt(tfsalary.getText());
						emp_birthday = Date.valueOf(tfbirthday.getText());
						// ��ȡ���ݿ�����
						EmployeeDao1 empd = new EmployeeDao1();
						EmployeePosition emp = new EmployeePosition(emp_password, emp_name, emp_sex, emp_position_id,
								emp_phone, emp_birthday, emp_salary, emp_status, emp_mark);
						emp.setEmp_id(emp_id);
						int afrow = 0;
						afrow = empd.addEmployee(emp);
						// ��Ϣ���ӻ�
						String status = emp.getEmp_status() == 1 ? "��ְ" : "��ְ";
						String empsex = emp.getEmp_sex() == 1 ? "Ů " : "��";
						String empposi = emp.getEmp_position_id() == 1 ? "�ɹ�Ա "
								: (emp.getEmp_position_id() == 2 ? "����Ա "
										: (emp.getEmp_position_id() == 3 ? "�ֹ�Ա " : "����Ա"));
						if (afrow > 0) {
							Object[] rowData = { afrow, emp_password, emp_name, empsex, empposi, emp_phone,
									emp_birthday, emp_salary, status, emp_mark };
							// �ڱ��������һ������
							tm.addRow(rowData);
							// ����ɹ���ʾ
							JOptionPane.showMessageDialog(null, "����ɹ���");
							// �رյ�ǰ����
							EmployeeAdd.this.dispose();
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
		btnDel.setBounds(300, 200, 60, 30);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ȡ����ť�¼� �رյ�ǰ����
				EmployeeAdd.this.dispose();
			}
		});
		// ��ӽ����
		jp.add(lbpassword);
		jp.add(lbname);
		jp.add(lbsex);
		jp.add(lbposition);
		jp.add(lbphone);
		jp.add(lbbirthday);
		jp.add(lbsalary);
		jp.add(lbstatus);

		jp.add(tfpassword);
		jp.add(tfname);
		jp.add(tfsex);
		jp.add(tfposition);
		jp.add(tfphone);
		jp.add(tfbirthday);
		jp.add(tfsalary);
		jp.add(tfstatus);
		// jp.add(tfmark);

		jp.add(btnSure);
		jp.add(btnDel);
		// ��ӽ�����
		this.add(jp);
	}
}

class Note implements FocusListener {
	JLabel notePwd;
	JTextField tfpassword;

	public Note(JLabel notePwd, JTextField tfpassword) {
		this.notePwd = notePwd;
		this.tfpassword = tfpassword;
	}

	@Override
	public void focusGained(FocusEvent e) {
		tfpassword.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (tfpassword.getText().trim().equals("")) {
			notePwd.setText(tfpassword.getName() + "����Ϊ��!");
		} else {
			notePwd.setText("");
		}
	}

}
