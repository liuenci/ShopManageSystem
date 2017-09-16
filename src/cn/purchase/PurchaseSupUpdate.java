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
	// 保存供应商信息
	int row = -1;
	int sup_id = 0;
	String sup_name;// 供应商名称
	String sup_address;// 供应商地址
	String sup_linkMan;// 供应商联系人
	String sup_phone;// 供应商联系电话
	int sup_status = 0;// 合作状态（0:保持合作1：解除合作）
	String sup_mark;// 备

	public PurchaseSupUpdate(DefaultTableModel tm, JTable table) {
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
		// 设置窗口显示位置
		this.setLocation(450, 150);
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
		sup_id = Integer.parseInt(tm.getValueAt(row, 0).toString());
		sup_address = tm.getValueAt(row, 2).toString();
		sup_linkMan = tm.getValueAt(row, 3).toString();
		sup_phone = tm.getValueAt(row, 4).toString();
		sup_mark = tm.getValueAt(row, 6).toString();
		// 添加面板对象
		JPanel jp = new JPanel();
		// 无布局设置
		jp.setLayout(null);
		// 面板大小
		jp.setSize(1200, 600);

		// 设置lable
		JLabel lbaddress = new JLabel("地址：");
		lbaddress.setBounds(30, 10, 80, 30);
		JLabel lblinkMan = new JLabel("联系人：");
		lblinkMan.setBounds(30, 55, 80, 30);
		JLabel lbphone = new JLabel("电话：");
		lbphone.setBounds(30, 100, 80, 30);
		JLabel lbmark = new JLabel("备注：");
		lbmark.setBounds(30, 145, 80, 30);

		// 设置文本域
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

		// 确定按钮
		JButton btnSure = new JButton("确定");
		btnSure.setBounds(120, 220, 60, 30);
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 确定按钮事件,修改的学生信息
				sup_address = tfaddress.getText();
				sup_linkMan = tflinkMan.getText();
				sup_phone = tfphone.getText();
				sup_mark = tfmark.getText();
				if ("".equals(sup_name)) {
					JOptionPane.showMessageDialog(null, "请输入供应商名称");
				} else if ("".equals(sup_address)) {
					JOptionPane.showMessageDialog(null, "请输入供应商地址");
				} else if ("".equals(sup_linkMan)) {
					JOptionPane.showMessageDialog(null, "请输入供应商联系人");
				} else if ("".equals(sup_phone)) {
					JOptionPane.showMessageDialog(null, "请输入供应商手机号");
				} else {
					if (!sup_address.matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "供应商地址输入有误");
					} else if (!sup_linkMan.matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "联系人信息输入有误");
					} else if (!sup_phone.matches("0?(13|14|15|18)[0-9]{9}")) {
						JOptionPane.showMessageDialog(null, "供应商手机号错误");
					} else {
						SupplyDao1 supd = new SupplyDao1();
						// 新建供应商对象
						Supply sup = new Supply(sup_name, sup_address, sup_linkMan, sup_phone, sup_status, sup_mark);
						sup.setSup_id(sup_id);
						int afrow = 0;
						// 调用修改方法
						afrow = supd.updateSupply(sup);
						if (afrow > 0) {
							JOptionPane.showMessageDialog(null, "修改成功！");
							// 修改table的信息
							tm.setValueAt(sup_address, row, 2);
							tm.setValueAt(sup_linkMan, row, 3);
							tm.setValueAt(sup_phone, row, 4);
							tm.setValueAt(sup_mark, row, 6);

							// 关闭当前窗口
							PurchaseSupUpdate.this.dispose();
						} else {
							// 保存失败提示
							JOptionPane.showMessageDialog(null, "保存失败！", "操作提示", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		}

		);
		// 取消按钮
		JButton btnDel = new JButton("取消");
		btnDel.setBounds(200, 220, 60, 30);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 取消按钮事件 关闭当前窗口
				PurchaseSupUpdate.this.dispose();
			}
		});
		// 添加进面板
		// 添加进面板
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
		// 添加进窗口
		this.add(jp);
	}

}
