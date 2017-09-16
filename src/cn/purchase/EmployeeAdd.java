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
 * 1.日期：2017-8-18 
 * 2.主要内容 
 *  a.完善数据，新增商品
 *  b.鼠标移动完成验证
 *  c.正则表达确定验证
 * 
 * @author 熊晨晨
 *
 */
public class EmployeeAdd extends JFrame {
	DefaultTableModel tm;
	JTable table;
	// 保存员工信息
	int emp_id;// 员工编号，登录账号
	String emp_password;// 登录密码
	JLabel notePwd;// 密码提示
	String emp_name;// 员工姓名
	JLabel noteName;// 名称提示语
	int emp_sex;// 性别 （0：女 1：男）
	int emp_position_id;// 员工职务编号,外键
	String emp_phone;// 员工联系电话
	JLabel notePhone;// 电话提示
	Date emp_birthday;// 员工出生日期
	JLabel noteBirthday;// 生日提示语句
	int emp_salary;// 员工工资
	JLabel noteSalary;// 工资提醒
	int emp_status;// 员工状态（0:开出 1：在职）
	String emp_mark;// 备注
	JButton btnSure;

	public EmployeeAdd(DefaultTableModel tm, JTable table) {
		this.tm = tm;
		this.table = table;
		this.inist();
		this.addpanel();
		// 窗口可视化
		this.setVisible(true);
	}

	/**
	 * 初始化页面
	 */
	public void inist() {
		// 设置窗口大小
		this.setSize(450, 300);
		// 设置窗口居中
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		// 不许修改窗口的大小
		this.setResizable(false);
		this.setTitle("新增员工信息");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// 创建面板方法
	public JPanel createPanel(int x, int y, int width, int height) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		Color color = new Color(237, 242, 248);
		jPanel.setBackground(color);
		jPanel.setBounds(x, y, width, height);
		jPanel.setBorder(BorderFactory.createEtchedBorder());
		return jPanel;
	}

	// 创建标签方法
	public JLabel createLabel(String name, int x, int y, int width, int height, String fontName, int fontSize) {
		JLabel jLabel = new JLabel(name);
		jLabel.setBounds(x, y, width, height);
		jLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
		return jLabel;
	}

	// 创建按钮方法
	public JButton createButton(String name, int x, int y, int width, int height, String fontName, int fontSize) {
		JButton jButton = new JButton(name);
		jButton.setBounds(x, y, width, height);
		jButton.setBorder(new MetalBorders.ButtonBorder());
		Color color = new Color(129, 194, 214);
		jButton.setBackground(color);
		return jButton;
	}

	// 创建文本框方法
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
	 * 创建面板
	 */
	public void addpanel() {
		// 添加面板对象
		JPanel jp = new JPanel();
		// 无布局设置
		jp.setLayout(null);
		// 面板大小
		jp.setSize(1200, 600);

		// 设置lable
		JLabel lbpassword = createLabel("密码：", 30, 10, 40, 30, "宋体", 12);
		JLabel lbname = createLabel("姓名：", 30, 55, 40, 30, "宋体", 12);
		JLabel lbphone = createLabel("电话：", 30, 100, 40, 30, "宋体", 12);
		JLabel lbsalary = createLabel("工资：", 30, 145, 40, 30, "宋体", 12);
		JLabel lbsex = createLabel("性别：", 250, 20, 40, 30, "宋体", 12);
		JLabel lbposition = createLabel("职位：", 250, 55, 40, 30, "宋体", 12);
		JLabel lbstatus = createLabel("工作状态：", 250, 100, 60, 30, "宋体", 12);
		JLabel lbbirthday = createLabel("出生日期：", 250, 145, 60, 30, "宋体", 12);

		// 密码提示语句
		JPasswordField tfpassword = new JPasswordField();
		tfpassword.setBounds(80, 10, 100, 25);
		tfpassword.setBackground(new Color(238, 238, 238));
		MatteBorder borderpassword = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfpassword.setBorder(borderpassword);
		tfpassword.setName("密码");
		notePwd = createLabel("", 80, 35, 160, 25, "宋体", 12);
		notePwd.setForeground(Color.red);
		jp.add(notePwd);
		tfpassword.addFocusListener(new Note(notePwd, tfpassword));

		// 姓名提示语句
		JTextField tfname = createTextField("", 80, 55, 100, 25, "宋体", 12, Color.WHITE);
		tfname.setBackground(new Color(238, 238, 238));
		MatteBorder bordername = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfname.setBorder(bordername);
		tfname.setName("姓名");
		noteName = createLabel("", 80, 75, 160, 25, "宋体", 12);
		noteName.setForeground(Color.red);
		jp.add(noteName);
		tfname.addFocusListener(new Note(noteName, tfname));

		JTextField tfphone = createTextField("", 80, 100, 100, 25, "宋体", 12, Color.WHITE);
		tfphone.setBackground(new Color(238, 238, 238));
		MatteBorder borderphone = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfphone.setBorder(borderphone);
		tfphone.setName("电话");
		notePhone = createLabel("", 80, 125, 160, 25, "宋体", 12);
		notePhone.setForeground(Color.red);
		jp.add(notePhone);
		tfphone.addFocusListener(new Note(notePhone, tfphone));

		// 工资提示语句
		JTextField tfsalary = createTextField("", 80, 145, 100, 25, "宋体", 12, Color.WHITE);
		tfsalary.setBackground(new Color(238, 238, 238));
		MatteBorder bordersalary = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfsalary.setBorder(bordersalary);
		tfsalary.setName("工资");
		noteSalary = createLabel("", 80, 165, 160, 25, "宋体", 12);
		noteSalary.setForeground(Color.red);
		jp.add(noteSalary);
		tfsalary.addFocusListener(new Note(noteSalary, tfsalary));

		JComboBox tfsex = new JComboBox();
		// 给列表添加选项
		tfsex.addItem("男");
		tfsex.addItem("女");
		tfsex.setBounds(310, 20, 50, 20);
		JComboBox tfposition = new JComboBox();
		// 给列表添加选项
		tfposition.addItem("销售员");
		tfposition.addItem("采购员");
		tfposition.addItem("管理员");
		tfposition.addItem("仓管员");
		tfposition.setBounds(310, 60, 65, 20);

		// 工作状态提示语句
		JLabel tfstatus = createLabel("在职", 310, 100, 180, 25, "宋体", 12);
		JTextField tfbirthday = createTextField("", 310, 145, 100, 25, "宋体", 12, Color.WHITE);
		tfbirthday.setBackground(new Color(238, 238, 238));
		MatteBorder border = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfbirthday.setBorder(border);
		DateChooser d = DateChooser.getInstance("yyyy-MM-dd");
		d.register(tfbirthday);
		tfbirthday.setName("生日");
		noteBirthday = createLabel("", 310, 165, 160, 25, "宋体", 12);
		noteBirthday.setForeground(Color.red);
		jp.add(noteBirthday);
		tfbirthday.addFocusListener(new Note(noteBirthday, tfbirthday));

		// 确定按钮
		btnSure = new JButton("保存");
		btnSure.setBounds(120, 200, 60, 30);
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 确定按钮事件
				// 新增供应商信息
				if (tfsex.getSelectedItem().equals("男")) {
					emp_sex = 0;
				} else if (tfsex.getSelectedItem().equals("女")) {
					emp_sex = 1;
				}
				if (tfposition.getSelectedItem().equals("销售员")) {
					emp_position_id = 2;
				} else if (tfposition.getSelectedItem().equals("采购员")) {
					emp_position_id = 1;
				} else if (tfposition.getSelectedItem().equals("管理员")) {
					emp_position_id = 4;
				} else if (tfposition.getSelectedItem().equals("仓管员")) {
					emp_position_id = 3;
				}

				emp_status = 1;
				if ("".equals(tfpassword.getText())) {
					JOptionPane.showMessageDialog(null, "员工密码不能为空");
				} else if ("".equals(tfname.getText())) {
					JOptionPane.showMessageDialog(null, "员工姓名不能为空");
				} else if ("".equals(tfbirthday.getText())) {
					JOptionPane.showMessageDialog(null, "员工出生日期不能为空");
				} else if ("".equals(tfsalary.getText())) {
					JOptionPane.showMessageDialog(null, "员工工资不能为空");
				} else if ("".equals(tfphone.getText())) {
					JOptionPane.showMessageDialog(null, "员工手机号不能为空");
				} else {

					// emp_salary = Integer.parseInt(tfsalary.getText());
					if (!tfpassword.getText().matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "密码只能为数字");
					} else if (!tfname.getText().matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "员工姓名只能为汉字");
					} else if (!tfphone.getText().matches("0?(13|14|15|18)[0-9]{9}")) {
						JOptionPane.showMessageDialog(null, "员工手机号输入错误");
					} else if (!("" + tfsalary.getText()).matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "工资只能为数字");
					} else {
						emp_password = tfpassword.getText();
						emp_name = tfname.getText();
						emp_phone = tfphone.getText();
						emp_salary = Integer.parseInt(tfsalary.getText());
						emp_birthday = Date.valueOf(tfbirthday.getText());
						// 获取数据库数据
						EmployeeDao1 empd = new EmployeeDao1();
						EmployeePosition emp = new EmployeePosition(emp_password, emp_name, emp_sex, emp_position_id,
								emp_phone, emp_birthday, emp_salary, emp_status, emp_mark);
						emp.setEmp_id(emp_id);
						int afrow = 0;
						afrow = empd.addEmployee(emp);
						// 信息可视化
						String status = emp.getEmp_status() == 1 ? "在职" : "离职";
						String empsex = emp.getEmp_sex() == 1 ? "女 " : "男";
						String empposi = emp.getEmp_position_id() == 1 ? "采购员 "
								: (emp.getEmp_position_id() == 2 ? "销售员 "
										: (emp.getEmp_position_id() == 3 ? "仓管员 " : "管理员"));
						if (afrow > 0) {
							Object[] rowData = { afrow, emp_password, emp_name, empsex, empposi, emp_phone,
									emp_birthday, emp_salary, status, emp_mark };
							// 在表格中新增一行数据
							tm.addRow(rowData);
							// 保存成功提示
							JOptionPane.showMessageDialog(null, "保存成功！");
							// 关闭当前窗口
							EmployeeAdd.this.dispose();
						} else {
							// 保存失败提示
							JOptionPane.showMessageDialog(null, "保存失败！", "操作提示", JOptionPane.WARNING_MESSAGE);
						}
					}
				}

			}
		});

		// 取消按钮
		JButton btnDel = new JButton("取消");
		btnDel.setBounds(300, 200, 60, 30);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 取消按钮事件 关闭当前窗口
				EmployeeAdd.this.dispose();
			}
		});
		// 添加进面板
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
		// 添加进窗口
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
			notePwd.setText(tfpassword.getName() + "不能为空!");
		} else {
			notePwd.setText("");
		}
	}

}
