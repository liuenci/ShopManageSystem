package cn.view.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import cn.purchase.EmployeeManage;
import cn.purchase.PurchaseSupply;


public class SystemManageUI extends JPanel {
	// ������ť
	JButton planBtn = new JButton("��Ӧ�̹���");
	JButton checkBtn = new JButton("Ա������");

	public SystemManageUI() {
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
		planBtn.setBounds(120, 200, 120, 40);
		this.add(planBtn);
		checkBtn.setBounds(480, 200, 120, 40);
		this.add(checkBtn);
		
	}

	// ����ص��������ʱ�������
	public void elementListener() {
		//�ɹ��ƻ�
		planBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				new PurchaseSupply();
			}
		});
		//��˲ɹ�����
		checkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EmployeeManage();
			}
		});
	}
}