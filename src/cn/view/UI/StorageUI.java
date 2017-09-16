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
	// ������ť
	JButton planBtn = new JButton("�ɹ��ƻ�");
	JButton checkBtn = new JButton("��˲ɹ�����");
	JButton countBtn = new JButton("����ѯ");
	JButton transportBtn = new JButton("������");
	JButton alarmBtn = new JButton("��澯��");

	public StorageUI() {
		Color color1 = new Color(184,222,223);
		setBackground(color1);
		mainButton();
		elementListener();
		this.setVisible(true);
	}

	// ������������
	public void mainButton() {
		// ���ò���Ϊ��
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

	// ����ص��������ʱ�������
	public void elementListener() {
		//�ɹ��ƻ�
		planBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StorageDao3 storage = new StorageDao3();
				Date date = Date.valueOf("2017-08-14");
				storage.addPurPlan(date, 154083007);
				new AddPurPlan();
			}
		});
		//��˲ɹ�����
		checkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CheckPurOrder();
			}
		});
		//����̵�
		countBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StorageQuery();
			}
		});
		//������
		transportBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StorageTransport();
			}
		});
		//��澯��
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
