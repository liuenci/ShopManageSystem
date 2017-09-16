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
 * 1.日期：2017-8-16
 * 2.主要内容
 *  a.新增员工信息
 *  b.修改员工信息
 *  c.删除员工信息
 *  d.根据名称查询员工信息
 *  e.全部查询
 *  f.退出界面
 * @author 熊晨晨
 *
 */
public class EmployeeManage extends JFrame {
	private JPanel jp;// 面板对象
	private DefaultTableModel tm;// 创建表模型
	private JScrollPane sp;// 滚动面板
	private JTable table;// 表格对象

	public EmployeeManage() {
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
		this.setSize(815, 530);
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
		this.setTitle("员工设置");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * 创建面板
	 */
	public void addpanel() {
		// 添加面板对象
		jp = new JPanel();
		// 无布局设置
		jp.setLayout(null);
		// 面板大小
		jp.setSize(1200, 600);

		// 创建表格
		String[] str = { "员工编号", "账号密码", "员工姓名", "性别", "职务", "联系电话", "出生日期", "工资", "状态" };
		// 获取数据库数据
		EmployeeDao1 empd = new EmployeeDao1();
		List<EmployeePosition> lemp = empd.getEmployee();
		Object[][] rows = new Object[lemp.size()][];
		for (int i = 0; i < rows.length; i++) {
			// 获取供应商集合对象
			EmployeePosition emp = lemp.get(i);
			String status = emp.getEmp_status() == 1 ? "在职" : "离职";
			String empsex = emp.getEmp_sex() == 1 ? "女 " : "男";
			// 将对象转为数组存储
			Object[] obj = { emp.getEmp_id(), emp.getEmp_password(), emp.getEmp_name(), empsex, emp.getPosi_name(),
					emp.getEmp_phone(), emp.getEmp_birthday(), emp.getEmp_salary(), status};
			// 给二维数组赋值
			rows[i] = obj;
		}
		tm = new DefaultTableModel(rows, str);
		table = new JTable(tm);
		table = new MTable(tm);

		sp = new JScrollPane(table);
		sp.setBounds(0, 100, 800, 400);

		// 增加员工按钮
		JButton btnAdd = new JButton();
		btnAdd.setBounds(50, 20, 50, 50);
		// 创建图片对象
		Icon iconAdd = new ImageIcon("images\\addSupply.png");
		btnAdd.setIcon(iconAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 增加供应商信息
				new EmployeeAdd(tm, table);
			}
		});
		// 修改员工信息按钮
		JButton btnUpdate = new JButton();
		btnUpdate.setBounds(130, 20, 50, 50);
		// 创建图片对象
		Icon iconUpdae = new ImageIcon("images\\updateSupply.png");
		btnUpdate.setIcon(iconUpdae);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 修改员工信息
				int row = table.getSelectedRow();
				if (row != -1) {
					new EmployeeUpdate(tm, table);
				} else {
					// 提示语句
					JOptionPane.showMessageDialog(null, "请选择要修改的供应商！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// 删除员工按钮
		JButton btnDel = new JButton();
		btnDel.setBounds(200, 20, 50, 50);
		// 创建图片对象
		Icon iconDel = new ImageIcon("images\\deleteSupply.png");
		btnDel.setIcon(iconDel);
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 删除员工信息
				int row = table.getSelectedRow();
				if (row != -1) {
					int emp_id = Integer.parseInt(tm.getValueAt(row, 0).toString());
					EmployeeDao1 empd = new EmployeeDao1();
					// 新建供应商对象
					EmployeePosition emp = new EmployeePosition();
					emp.setEmp_id(emp_id);
					int afrow = 0;
					Object[] obj = { "任性删除", "手下留情" };
					// 选择提示语句
					int res = JOptionPane.showOptionDialog(null, "是否确定删除！", "删除操作提示", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					if (res == JOptionPane.YES_OPTION) {
						// 调用修改方法
						afrow = empd.delEmployee(emp);
					} else {
					}
					if (afrow > 0) {
						// 删除成功提示语句
						JOptionPane.showMessageDialog(null, "删除成功！");
						tm.setValueAt("离职", row, 8);
						// tm.removeRow(row);
					} else {
						// 保存失败警告
						JOptionPane.showMessageDialog(null, "删除失败！", "删除操作提示", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(null, "请选择要删除的供应商！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 全部查询供应商信息按钮
		JButton btnSelAll = new JButton();
		btnSelAll.setBounds(280, 20, 50, 50);
		// 创建图片对象
		Icon iconSelAll = new ImageIcon("images\\queryAll.png");
		btnSelAll.setIcon(iconSelAll);
		btnSelAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 创建表格
				String[] str = { "员工编号", "账号密码", "员工姓名", "性别", "职务", "联系电话", "出生日期", "工资", "状态"};
				// 获取数据库数据
				EmployeeDao1 empd = new EmployeeDao1();
				List<EmployeePosition> lemp = empd.getEmployee();
				Object[][] rows = new Object[lemp.size()][];
				for (int i = 0; i < rows.length; i++) {
					// 获取供应商集合对象
					EmployeePosition emp = lemp.get(i);
					String status = emp.getEmp_status() == 1 ? "在职" : "离职";
					String empsex = emp.getEmp_sex() == 1 ? "女 " : "男";
					// 将对象转为数组存储
					Object[] obj = { emp.getEmp_id(), emp.getEmp_password(), emp.getEmp_name(), empsex,
							emp.getPosi_name(), emp.getEmp_phone(), emp.getEmp_birthday(), emp.getEmp_salary(), status};
					// 给二维数组赋值
					rows[i] = obj;
				}
				tm.setDataVector(rows, str);
			}
		});

		JLabel latit = new JLabel("请输入供应商公司名：");
		latit.setBounds(450, 15, 140, 25);
		// 条件查询输入框
		JTextField flserach = new JTextField();
		flserach.setBounds(450, 35, 180, 25);

		// 条件查询供应商信息按钮
		JButton btnSel = new JButton();
		btnSel.setBounds(360, 20, 50, 50);
		// 创建图片对象
		Icon iconSel = new ImageIcon("images\\querySupply.png");
		btnSel.setIcon(iconSel);
		btnSel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 查询员工信息
				String search = "" + flserach.getText();
				search.trim();
				if (!search.equals("")) {
					// 创建表格
					String[] str = { "员工编号", "账号密码", "员工姓名", "性别", "职务", "联系电话", "出生日期", "工资", "状态" };
					// 获取数据库数据
					EmployeeDao1 empd = new EmployeeDao1();
					List<EmployeePosition> emp = empd.getEmployeeName(search);
					Object[][] rows = new Object[emp.size()][];
					for (int i = 0; i < rows.length; i++) {
						// 获取供应商集合对象
						EmployeePosition emplyee = emp.get(i);
						// 获取相应的编号
						String status = emplyee.getEmp_status() == 1 ? "在职" : "离职";
						String empsex = emplyee.getEmp_sex() == 0 ? "女 " : "男";
						// 将对象转为数组存储
						Object[] obj = { emplyee.getEmp_id(), emplyee.getEmp_password(), emplyee.getEmp_name(), empsex,
								emplyee.getPosi_name(), emplyee.getEmp_phone(), emplyee.getEmp_birthday(),
								emplyee.getEmp_salary(), status};
						// 给二维数组赋值
						rows[i] = obj;
					}
					tm.setDataVector(rows, str);
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(EmployeeManage.this, "查询框不能为空！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// 退出弹出框按钮
		JButton btnOut = new JButton();
		btnOut.setBounds(700, 20, 50, 50);
		// 创建图片对象
		Icon iconOut = new ImageIcon("images\\exit.png");
		btnOut.setIcon(iconOut);
		btnOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeManage.this.dispose();
			}
		});
		// 按钮添加进面板
		jp.add(btnAdd);
		jp.add(btnDel);
		jp.add(btnUpdate);
		jp.add(btnSel);
		jp.add(btnSelAll);
		jp.add(latit);
		jp.add(flserach);
		jp.add(btnOut);
		jp.add(sp);
		// 添加进窗口
		this.add(jp);

	}

	public static void main(String[] args) {
		new EmployeeManage();
	}

}
