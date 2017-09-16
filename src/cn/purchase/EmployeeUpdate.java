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
 * 1.日期2017-8-17 
 * 2.主要内容 
 *  a.修改员工信息（密码，职务，电话，工资）
 * @author 熊晨晨
 *
 */
public class EmployeeUpdate extends JFrame {
	DefaultTableModel tm;
	JTable table;
	// 保存供应商信息
	int row = -1;
	// 保存员工信息
	int emp_id;// 员工编号，登录账号
	String emp_password;// 登录密码
	String emp_name;// 员工姓名
	int emp_sex;// 性别 （0：女 1：男）
	int emp_position_id;// 员工职务编号,外键
	String emp_phone;// 员工联系电话
	Date emp_birthday;// 员工出生日期
	int emp_salary;// 员工工资
	int emp_status;// 员工状态（0:开出 1：在职）
	String emp_mark;// 备注
	String posi_name;

	public EmployeeUpdate(DefaultTableModel tm, JTable table) {
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
		this.setSize(350, 300);
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
		this.setTitle("供应商信息修改");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * 创建面板
	 */
	public void addpanel() {
		row = table.getSelectedRow();
		emp_id = Integer.parseInt(tm.getValueAt(row, 0).toString());
		emp_password = tm.getValueAt(row, 1).toString();
		posi_name = tm.getValueAt(row, 4).toString();
		emp_phone = tm.getValueAt(row, 5).toString();
		emp_salary = Integer.parseInt(tm.getValueAt(row, 7).toString());
		// 添加面板对象
		JPanel jp = new JPanel();
		// 无布局设置
		jp.setLayout(null);
		// 面板大小
		jp.setSize(1200, 600);

		// 设置lable
		JLabel lbpassword = new JLabel("密码：");
		lbpassword.setBounds(30, 10, 80, 30);
		JLabel lbposition = new JLabel("职务：");
		lbposition.setBounds(30, 55, 80, 30);

		JLabel lbphone = new JLabel("电话：");
		lbphone.setBounds(30, 100, 80, 30);
		JLabel lbsalary = new JLabel("工资：");
		lbsalary.setBounds(30, 145, 80, 30);

		JTextField tfpassword = new JTextField(emp_password);
		tfpassword.setBounds(110, 10, 180, 30);
		JComboBox tfposition = new JComboBox();
		// 给列表添加选项
		tfposition.addItem("销售员");
		tfposition.addItem("采购员");
		tfposition.addItem("管理员");
		tfposition.addItem("仓管员");
		tfposition.setBounds(110, 55, 70, 30);
		JTextField tfphone = new JTextField(emp_phone);
		tfphone.setBounds(110, 100, 180, 30);
		JTextField tfsalary = new JTextField("" + emp_salary);
		tfsalary.setBounds(110, 145, 180, 30);

		// 确定按钮
		JButton btnSure = new JButton("确定");
		btnSure.setBounds(120, 220, 60, 30);
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// // 确定按钮事件,修改的学生信息

				if (tfposition.getSelectedItem().equals("销售员")) {
					emp_position_id = 2;
				} else if (tfposition.getSelectedItem().equals("采购员")) {
					emp_position_id = 1;
				} else if (tfposition.getSelectedItem().equals("管理员")) {
					emp_position_id = 4;
				} else {
					emp_position_id = 3;
				}

				if ("".equals(tfpassword.getText().trim())) {
					JOptionPane.showMessageDialog(null, "员工密码不能为空");
				} else if ("".equals(tfsalary.getText().trim())) {
					JOptionPane.showMessageDialog(null, "员工工资不能为空");
				} else if ("".equals(tfphone.getText())) {
					JOptionPane.showMessageDialog(null, "员工手机号不能为空");
				} else {
					if (!tfpassword.getText().matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "密码只能为数字");
					} else if (!tfsalary.getText().matches("[0-9]*")) {
						JOptionPane.showMessageDialog(null, "员工工资只能为正整数");
					} else if (!tfphone.getText().matches("0?(13|14|15|18)[0-9]{9}")) {
						JOptionPane.showMessageDialog(null, "员工手机号输入错误");
					} else {
						emp_password = tfpassword.getText();
						posi_name = tfposition.getSelectedItem().toString();
						emp_phone = tfphone.getText();
						emp_salary = Integer.parseInt(tfsalary.getText());
						EmployeeDao1 empd = new EmployeeDao1();
						// 新建供应商对象
						EmployeePosition emp = new EmployeePosition(emp_password, emp_name, emp_sex, emp_position_id,
								emp_phone, emp_birthday, emp_salary, emp_status, emp_mark);
						emp.setEmp_id(emp_id);
						int afrow = 0;
						// 调用修改方法
						afrow = empd.updateEmployee(emp);
						if (afrow > 0) {
							JOptionPane.showMessageDialog(null, "修改成功！");
							// 修改table的信息
							tm.setValueAt(emp_password, row, 1);
							tm.setValueAt(posi_name, row, 4);
							tm.setValueAt(emp_phone, row, 5);
							tm.setValueAt(emp_salary, row, 7);

							// 关闭当前窗口
							EmployeeUpdate.this.dispose();
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
		btnDel.setBounds(200, 220, 60, 30);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 取消按钮事件 关闭当前窗口
				EmployeeUpdate.this.dispose();
			}
		});
		// 添加进面板
		// 添加进面板
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
		// 添加进窗口
		this.add(jp);
	}

}
