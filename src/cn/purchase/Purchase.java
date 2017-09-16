package cn.purchase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.PurchaseOrderDao1;
import cn.model.common.PurchaseOrder;

public class Purchase extends JFrame {

	public Purchase() {
		// ��ʼ��
		this.inist();
		// �����巽��
		this.addpanel();
		// ���ô��ڿ��ӻ�
		this.setVisible(true);
	}

	/**
	 * ��ʼ�����
	 */
	public void inist() {
		this.setSize(1370, 700);
		// ���ô�����ʾλ��
		this.setLocation(0, 0);
		// ���ñ���
		this.setTitle("�ɹ����");
		// ���ùر�״̬
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * �������
	 */
	public void addpanel() {
		// ����������
		JPanel jp = new JPanel();
		// ��岼������Ϊ��
		jp.setLayout(null);
		// ��峤��
		jp.setSize(1200, 600);
		// ������ť����,��Ʒ��Ӱ�ť
		JButton btnPur = new JButton("�ɹ�����");
		btnPur.setBounds(300, 200, 100, 40);
		// ��������¼�
		btnPur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ת���ɹ������������
				DefaultTableModel tm;// ������ģ��
				// �������
				int pur_id = 20171001;
				// �����̱�ţ����
				int pur_supplyId = 1;
				// �ɹ�����
				String date = "2017-8-13";
				java.sql.Date pur_date = java.sql.Date.valueOf(date);
				// ֧���ܽ��
				double pur_pay=0.0;
				// Ա����ţ����
				int pur_empId=1;
				// �Ƿ���ˣ�0��δ���1�������ͨ�� 2�����δͨ���˻زɹ�Ա��
				int pur_status=0;
				// ��ע
				String pur_mark="";
				// �������
				String[] str = { "�ɹ�������", "�����̱��", "�ɹ�����", "֧���ܽ��", "Ա�����", "�Ƿ����", "��ע" };
				// ��ȡ���ݿ�����
				PurchaseOrderDao1 purd = new PurchaseOrderDao1();
				PurchaseOrder pur = new PurchaseOrder(pur_supplyId,pur_date,pur_pay,pur_empId,pur_status,pur_mark);
				Object[][] rows = new Object[0][];
				pur.setPur_id(pur_id); 
				int afrow = 0;
				afrow = purd.addPurchaseOrder(pur);
				//String status = pur.getSup_status() == 0 ? "���ֺ���" : "�������";
				if (afrow > 0) {
					Object[] rowData = {afrow,pur_supplyId,pur_date,pur_pay,pur_empId,pur_status,pur_mark};
					// �ڱ��������һ������
					//tm.addRow(rowData);
					// �رյ�ǰ����
					new PurchaseOrderView();
				} 
			}
		});
		// �ɹ�������ѯ��ť
		JButton btnOrd = new JButton("�ɹ�����");
		btnOrd.setBounds(300, 400, 100, 40);
		// ��������¼�
		btnOrd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ת���ɹ�������ѯ���
				new PurchaseSel();
			}
		});
		// �ɹ��˻���ť
		JButton btnSta = new JButton("�ɹ��˻�");
		btnSta.setBounds(700, 200, 100, 40);
		// ��������¼�
		btnSta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �ɹ��˻����
				new PurchaseDel();
			}
		});
		// ��Ӧ�����ð�ť
		JButton btnSup = new JButton("��Ӧ������");
		btnSup.setBounds(700, 400, 100, 40);
		// ��������¼�
		btnSup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��Ӧ�������������
				new PurchaseSupply();
			}
		});
		// ��ť��ӵ����
		jp.add(btnPur);
		jp.add(btnOrd);
		jp.add(btnSta);
		jp.add(btnSup);
		// �����ӵ�����
		this.add(jp);
	}

	public static void main(String[] args) {
		new Purchase();
	}
}
