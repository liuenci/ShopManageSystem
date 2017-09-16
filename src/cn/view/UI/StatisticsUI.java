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

	// ������ť
	JButton purStaBtn = new JButton("�ɹ�ͳ��");
	JButton sellStaBtn = new JButton("����ͳ��");

	public StatisticsUI() {
		setBackground(new Color(184,222,223));
		mainButton();
		elementListener();
		this.setVisible(true);
	}

	// ������������
	public void mainButton() {
		this.setLayout(null);
		purStaBtn.setBounds(120, 200, 120, 40);
		this.add(purStaBtn);
		sellStaBtn.setBounds(480, 200, 120, 40);
		this.add(sellStaBtn);
	}

	// ����ص��������ʱ�������
	public void elementListener() {
		// �ɹ��ƻ�
		purStaBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PurStatistics();
			}
		});
		// ��˲ɹ�����
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
