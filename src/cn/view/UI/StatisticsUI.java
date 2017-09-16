package cn.view.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JPanel;

import cn.dao.storage.StorageDao3;
import cn.statistics.PurStatistics;
import cn.statistics.SellStatistics;
import cn.storage.AddPurPlan;
import cn.storage.CheckPurOrder;
import cn.storage.StorageQuery;
import cn.storage.StorageTransport;

public class StatisticsUI extends JPanel {

	// 创建按钮
	JButton purStaBtn = new JButton("采购统计");
	JButton sellStaBtn = new JButton("销售统计");

	public StatisticsUI() {
		setBackground(new Color(184,222,223));
		mainButton();
		elementListener();
		this.setVisible(true);
	}

	// 添加主功能面板
	public void mainButton() {
		this.setLayout(null);
		purStaBtn.setBounds(120, 200, 120, 40);
		this.add(purStaBtn);
		sellStaBtn.setBounds(480, 200, 120, 40);
		this.add(sellStaBtn);
	}

	// 给相关的组件设置时间监听器
	public void elementListener() {
		// 采购计划
		purStaBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PurStatistics();
			}
		});
		// 审核采购订单
		sellStaBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SellStatistics();
			}
		});
	}

	public static void main(String[] args) {
		new PurchaseUI();
	}

}
