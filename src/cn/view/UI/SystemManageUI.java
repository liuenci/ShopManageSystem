package cn.view.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import cn.purchase.EmployeeManage;
import cn.purchase.PurchaseSupply;


public class SystemManageUI extends JPanel {
	// 创建按钮
	JButton planBtn = new JButton("供应商管理");
	JButton checkBtn = new JButton("员工管理");

	public SystemManageUI() {
		Color color1 = new Color(184,222,223);
		setBackground(color1);
		mainButton();
		elementListener();
		this.setVisible(true);
	}

	// 添加主功能面板
	public void mainButton() {
		// 设置布局为空
		this.setLayout(null);
		planBtn.setBounds(120, 200, 120, 40);
		this.add(planBtn);
		checkBtn.setBounds(480, 200, 120, 40);
		this.add(checkBtn);
		
	}

	// 给相关的组件设置时间监听器
	public void elementListener() {
		//采购计划
		planBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				new PurchaseSupply();
			}
		});
		//审核采购订单
		checkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EmployeeManage();
			}
		});
	}
}