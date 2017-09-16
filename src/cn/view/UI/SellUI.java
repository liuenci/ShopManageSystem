package cn.view.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JPanel;

import cn.dao.sell.SalesDao2;
import cn.model.common.SellOrder;
import cn.sell.Merchandise_returns;
import cn.sell.Sales_order;

public class SellUI extends JPanel {

	// ������ť
	JButton sellOrderBtn = new JButton("���۶���");
	JButton sellReturnBtn = new JButton("�����˻�");

	public SellUI() {
		this.setBackground(new Color(184, 222, 223));
		this.mainButton();
		this.elementListener();
		this.setVisible(true);
	}

	// ������������
	public void mainButton() {
		// ���ò���Ϊ��
		this.setLayout(null);
		// this.setOpaque(false);
		sellOrderBtn.setBounds(120, 200, 120, 40);
		this.add(sellOrderBtn);
		sellReturnBtn.setBounds(480, 200, 120, 40);
		this.add(sellReturnBtn);

	}

	// ����ص��������ʱ�������
	public void elementListener() {
		// �ɹ��ƻ�
		sellOrderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ʱʱ��
				String date = "2017-8-13";
				Date sell_date = Date.valueOf(date);
				// ��ʱԱ���������
				int sell_empId = 154083006;
				// ��ʱ֧���ܽ��
				double sell_profit = 0.0;
				// ��ʱ��ע
				String sell_mark = "�ޱ�ע";
				// ����dao���������۵���Ϣ
				SalesDao2 sell = new SalesDao2();
				int status = 0;
				SellOrder seoed = new SellOrder(sell_empId, sell_date, sell_profit,status,sell_mark);
				int sel = sell.addSellOrder(seoed);
				// ���۶���
				new Sales_order();
				
			}
		});
		// ����̵�
		sellReturnBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Merchandise_returns();
				
			}
		});
	}

	public static void main(String[] args) {
		new SellUI();
	}

}