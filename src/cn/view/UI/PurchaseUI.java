package cn.view.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.PurchaseOrderDao1;
import cn.model.common.PurchaseOrder;
import cn.purchase.PurchaseDel;
import cn.purchase.PurchaseOrderView;
import cn.purchase.PurchaseSel;
import cn.purchase.PurchaseSupply;
/**
 * ���ڣ�2017-08-05
 * ���ܣ��ṩ�ɹ���������
 * 	1.�ɹ�����
 * 	2.�ɹ��˻�
 * 	3.�ɹ�����
 * 	4.��Ӧ������
 * @author LuckyJavaCi
 *
 */
public class PurchaseUI extends JPanel {
	JButton purBtn = new JButton("�ɹ�����");//�ɹ�������ť
	JButton returnBtn = new JButton("�ɹ��˻�");//�ɹ��˻���ť
	JButton purOrderBtn = new JButton("�ɹ�����");//�ɹ�������ť

	public PurchaseUI() {
		this.setBackground(new Color(184,222,223));
		this.mainButton();
		this.elementListener();
		this.setVisible(true);
	}

	/*
	 *  ������������
	 */
	public void mainButton() {
		this.setLayout(null);
		purBtn.setBounds(120, 80, 120, 40);
		this.add(purBtn);
		returnBtn.setBounds(480, 80, 120, 40);
		this.add(returnBtn);
		purOrderBtn.setBounds(300, 300, 120, 40);
		this.add(purOrderBtn);
	}

	/*
	 *  ����ص��������ʱ�������
	 */
	public void elementListener() {
		// �ɹ�����
		purBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int pur_id = 20171001;// �������
				int pur_supplyId = 1;// �����̱�ţ����
				String date = "2017-8-26";// �ɹ�����
				Date pur_date = Date.valueOf(date);
				double pur_pay=0.0;// ֧���ܽ��
				int pur_empId=154083005;// Ա����ţ����
				int pur_status=0;// �Ƿ���ˣ�0��δ���1�������ͨ�� 2�����δͨ���˻زɹ�Ա��
				String pur_mark="";// ��ע
				// �������
				// ��ȡ���ݿ�����
				PurchaseOrderDao1 purd = new PurchaseOrderDao1();
				PurchaseOrder pur = new PurchaseOrder(pur_supplyId,pur_date,pur_pay,pur_empId,pur_status,pur_mark);
				pur.setPur_id(pur_id); 
				int afrow = 0;
				afrow = purd.addPurchaseOrder(pur);
				if (afrow > 0) {
					new PurchaseOrderView();
				} 
			}
		});
		// �ɹ�����
		returnBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PurchaseDel();
			}
		});
		// �ɹ��˻�
		purOrderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PurchaseSel();
			}
		});
	}

	public static void main(String[] args) {
		new PurchaseUI();
	}

}
