package cn.purchase;

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

import cn.dao.purchase.SupplyDao1;
import cn.model.common.Supply;
import cn.view.purchase.MTable;
/**
 * 1.日期：2017-8-20
 * 2.主要内容
 *  a.新增供应商
 *  b.修改供应商信息
 *  c.删除供应商，修改字段
 *  d.条件查询，根据姓名查询
 *  e.全部查询
 *  f.退出该界面
 * @author 熊晨晨
 *
 */
public class PurchaseSupply extends JFrame {
	private JPanel jp;// 面板对象
	private DefaultTableModel tm;// 创建表模型
	private JScrollPane sp;// 滚动面板
	private JTable table;// 表格对象

	public PurchaseSupply() {
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
		// 设置窗口显示位置
		this.setLocation(280, 85);
		// 不许修改窗口的大小
		this.setResizable(false);
		this.setTitle("供应商设置");
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
		String[] str = { "供应商编号", "公司名称", "地址", "联系人", "联系电话", "合作状态", "备注" };
		// 获取数据库数据
		SupplyDao1 supd = new SupplyDao1();
		List<Supply> lsup = supd.getSupply();
		Object[][] rows = new Object[lsup.size()][];
		for (int i = 0; i < rows.length; i++) {
			// 获取供应商集合对象
			Supply sup = lsup.get(i);
			String status = sup.getSup_status() == 0 ? "保持合作" : "解除合作";
			// 将对象转为数组存储
			Object[] obj = { sup.getSup_id(), sup.getSup_name(), sup.getSup_address(), sup.getSup_linkMan(),
					sup.getSup_phone(), status, sup.getSup_mark() };
			// 给二维数组赋值
			rows[i] = obj;
		}
		tm = new DefaultTableModel(rows, str);
		table = new JTable(tm);
		table = new MTable(tm);

		sp = new JScrollPane(table);
		sp.setBounds(0, 100, 800, 400);

		// 增加供应商按钮
		JButton btnAdd = new JButton();
		btnAdd.setBounds(50, 20, 50, 50);
		// 创建图片对象
		Icon iconAdd = new ImageIcon("images\\addSupply.png");
		btnAdd.setIcon(iconAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 增加供应商信息
				// int row = table.getSelectedRow();
				new PurchaseSupAdd(tm, table);

			}
		});
		// 修改供应商信息按钮
		JButton btnUpdate = new JButton();
		btnUpdate.setBounds(130, 20, 50, 50);
		// 创建图片对象
		Icon iconUpdae = new ImageIcon("images\\updateSupply.png");
		btnUpdate.setIcon(iconUpdae);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 修改供应商信息
				int row = table.getSelectedRow();
				if (row != -1) {
					new PurchaseSupUpdate(tm, table);
				} else {
					// 提示语句
					JOptionPane.showMessageDialog(null, "请选择要修改的供应商！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		// 删除供应商按钮
		JButton btnDel = new JButton();
		btnDel.setBounds(200, 20, 50, 50);
		// 创建图片对象
		Icon iconDel = new ImageIcon("images\\deleteSupply.png");
		btnDel.setIcon(iconDel);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 删除供应商信息
				int row = table.getSelectedRow();

				if (row != -1) {
					String status = tm.getValueAt(row, 5).toString();
					if (status.equals("解除合作")) {
						// 错误操作警告语句
						JOptionPane.showMessageDialog(null, "该供应商已经删除！", "操作提示", JOptionPane.ERROR_MESSAGE);
					} else {
						int sup_id = Integer.parseInt(tm.getValueAt(row, 0).toString());
						SupplyDao1 supd = new SupplyDao1();
						// 新建供应商对象
						Supply sup = new Supply();
						sup.setSup_id(sup_id);
						int afrow = 0;
						Object[] obj = { "任性删除", "手下留情" };
						// 选择提示语句
						int res = JOptionPane.showOptionDialog(null, "是否确定删除！", "删除操作提示",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
						if (res == JOptionPane.YES_OPTION) {
							// 调用修改方法
							afrow = supd.delSupply(sup);
						} else {

						}
						if (afrow > 0) {
							// 删除成功提示语句
							JOptionPane.showMessageDialog(null, "删除成功！");
							tm.setValueAt("解除合作", row, 5);
							// tm.removeRow(row);
						} else {
							// 保存失败警告
							JOptionPane.showMessageDialog(null, "删除失败！", "删除操作提示", JOptionPane.WARNING_MESSAGE);
						}
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
				// 查询供应商信息
				// 创建表格
				String[] str = { "供应商编号", "公司名称", "地址", "联系人", "联系电话", "合作状态", "备注" };
				// 获取数据库数据
				SupplyDao1 supd = new SupplyDao1();
				List<Supply> lsup = supd.getSupply();
				Object[][] rows = new Object[lsup.size()][];
				for (int i = 0; i < rows.length; i++) {
					// 获取供应商集合对象
					Supply sup = lsup.get(i);
					String status = sup.getSup_status() == 0 ? "保持合作" : "解除合作";
					// 将对象转为数组存储
					Object[] obj = { sup.getSup_id(), sup.getSup_name(), sup.getSup_address(), sup.getSup_linkMan(),
							sup.getSup_phone(), status, sup.getSup_mark() };
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
				// 查询供应商信息
				// String sup_name = "";
				String search = "" + flserach.getText();
				search.trim();

				if (!search.equals("")) {
					// 创建表格
					String[] str = { "供应商编号", "公司名称", "地址", "联系人", "联系电话", "合作状态", "备注" };
					// 获取数据库数据
					SupplyDao1 supd = new SupplyDao1();
					List<Supply> sup = supd.getSupplyName(search);
					Object[][] rows = new Object[sup.size()][];
					for (int i = 0; i < rows.length; i++) {
						// 获取供应商集合对象
						Supply supply = sup.get(i);
						String status = supply.getSup_status() == 0 ? "保持合作" : "解除合作";
						// 获取相应的编号
						// 将对象转为数组存储
						Object[] obj = { supply.getSup_id(), supply.getSup_name(), supply.getSup_address(),
								supply.getSup_linkMan(), supply.getSup_phone(), status, supply.getSup_mark() };
						// 给二维数组赋值
						rows[i] = obj;
					}
					tm.setDataVector(rows, str);
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(PurchaseSupply.this, "查询框不能为空！", "操作提示", JOptionPane.ERROR_MESSAGE);
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
				PurchaseSupply.this.dispose();
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
		new PurchaseSupply();
	}
}
