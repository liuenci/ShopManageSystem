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
	private CommondMethods commondMethods = new CommondMethods();// 通用方法
	DefaultTableModel tm;
	JTable table;
	// 保存供应商信息
	int sup_id = 0;
	String sup_name="";// 供应商名称
	String sup_address="";// 供应商地址
	String sup_linkMan="";// 供应商联系人
	String sup_phone;// 供应商联系电话
	int sup_status = 0;// 合作状态（0:保持合作1：解除合作）
	String sup_mark;// 备

	public PurchaseSupAdd(DefaultTableModel tm, JTable table) {
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
		this.setSize(400, 400);
		// 设置窗口显示位置
		this.setLocation(450, 150);
		// 不许修改窗口的大小
		this.setResizable(false);
		this.setTitle("新增供应商信息");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		JLabel lbname = new JLabel("公司名称：");
		lbname.setBounds(30, 10, 80, 30);
		JLabel lbaddress = new JLabel("地址：");
		lbaddress.setBounds(30, 55, 80, 30);
		JLabel lblinkMan = new JLabel("联系人：");
		lblinkMan.setBounds(30, 100, 80, 30);
		JLabel lbphone = new JLabel("电话：");
		lbphone.setBounds(30, 145, 80, 30);
		JLabel lbstatus = new JLabel("合作状态：");
		lbstatus.setBounds(30, 190, 80, 30);
		//保持合作
		JLabel company = commondMethods.createLabel("保持合作", 130, 190, 80, 30, "宋体", 14);
		jp.add(company);
		JLabel lbmark = new JLabel("备注：");
		lbmark.setBounds(30, 240, 80, 30);
		

		// 设置文本域
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

		// 确定按钮
		JButton btnSure = new JButton("保存");
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
					JOptionPane.showMessageDialog(null, "请输入供应商名称");
				}else if("".equals(sup_address)) {
					JOptionPane.showMessageDialog(null, "请输入供应商地址");
				}else if("".equals(sup_linkMan)) {
					JOptionPane.showMessageDialog(null, "请输入供应商联系人");
				}else if("".equals(sup_phone)) {
					JOptionPane.showMessageDialog(null, "请输入供应商手机号");
				}else {
					if(!sup_name.matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "供应商公司名称只能为汉字");
					}else if(!sup_address.matches("[\\u4e00-\\u9fa5]*")){
						JOptionPane.showMessageDialog(null, "供应商地址只能为汉字");
					}else if(!sup_linkMan.matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "联系人信息只能为汉字");
					}else if(!sup_phone.matches("0?(13|14|15|18)[0-9]{9}")) {
						JOptionPane.showMessageDialog(null, "供应商手机号输入错误");
					}else {
						try{
							// 获取数据库数据
							SupplyDao1 supd = new SupplyDao1();
							Supply sup = new Supply(sup_name, sup_address, sup_linkMan, sup_phone, 0, sup_mark );
							sup.setSup_id(sup_id);
							int afrow = 0;
							afrow = supd.addSupply(sup);
							String status = sup.getSup_status() == 0 ? "保持合作" : "解除合作";
							if (afrow > 0) {
								Object[] rowData = {afrow, sup_name, sup_address, sup_linkMan, sup_phone, status, sup_mark };
								// 在表格中新增一行数据
								tm.addRow(rowData);
								// 保存成功提示
								JOptionPane.showMessageDialog(null, "保存成功！");
								// 关闭当前窗口
								PurchaseSupAdd.this.dispose();
							} else {
								// 保存失败提示
								JOptionPane.showMessageDialog(null, "保存失败！", "操作提示", JOptionPane.WARNING_MESSAGE);
							}
						}catch(NumberFormatException meassagenull){
							// 错误操作警告语句
							JOptionPane.showMessageDialog(null, "订单信息不完整，请小主核实清楚订单哦！", "操作提示", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
				
			}
		});

		// 取消按钮
		JButton btnDel = new JButton("取消");
		btnDel.setBounds(220, 320, 60, 30);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 取消按钮事件 关闭当前窗口
				PurchaseSupAdd.this.dispose();
			}
		});
		// 添加进面板
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
		// 添加进窗口
		this.add(jp);
	}

}
