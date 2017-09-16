package cn.view.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JPanel;

import cn.dao.storage.StorageDao3;
import cn.storage.AddPurPlan;
import cn.storage.CheckPurOrder;
import cn.storage.StorageAlarm;
import cn.storage.StorageQuery;
import cn.storage.StorageTransport;

public class StorageUI extends JPanel {
	// 创建按钮
	JButton planBtn = new JButton("采购计划");
	JButton checkBtn = new JButton("审核采购订单");
	JButton countBtn = new JButton("库存查询");
	JButton transportBtn = new JButton("库存调拨");
	JButton alarmBtn = new JButton("库存警报");

	public StorageUI() {
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
		planBtn.setBounds(120, 80, 120, 40);
		this.add(planBtn);
		checkBtn.setBounds(480, 80, 120, 40);
		this.add(checkBtn);
		countBtn.setBounds(120, 300, 120, 40);
		this.add(countBtn);
		transportBtn.setBounds(480, 300, 120, 40);
		this.add(transportBtn);
		alarmBtn.setBounds(300, 190, 120, 40);
		this.add(alarmBtn);
	}

	// 给相关的组件设置时间监听器
	public void elementListener() {
		//采购计划
		planBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StorageDao3 storage = new StorageDao3();
				Date date = Date.valueOf("2017-08-14");
				storage.addPurPlan(date, 154083007);
				new AddPurPlan();
			}
		});
		//审核采购订单
		checkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CheckPurOrder();
			}
		});
		//库存盘点
		countBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StorageQuery();
			}
		});
		//库存调拨
		transportBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StorageTransport();
			}
		});
		//库存警报
		alarmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new StorageAlarm();
			}
		});
	}

	public static void main(String[] args) {
		new StorageUI();
	}

}
