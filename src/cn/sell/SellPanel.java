package cn.sell;
/**
 * �����
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.dao.sell.SalesDao2;
import cn.model.common.SellOrder;

public class SellPanel extends JFrame {
	// �����
	private JPanel mainPanel;
	// ��Ʒ�˻���ť
	private JButton mercharetu;
	// ��������ť
	private JButton ordManag;
	// ��ǰ����ѯ��ť
	private JButton currInvenQuery;
	// ���۶������
	int saleOrderNumber;

	Format gs=new Format();
	// ��ʼ��
	public SellPanel() {
		this.init();
		addsell();
		clickEvent();
		// ���ô��ڿɼ�
		this.setVisible(true);
	}

	public void init() {
		// ���ô��ڱ���
		this.setTitle("���۹���");
		// ���ô��ڴ�С
		this.setSize(1366, 768);
		// ������ʾ���ڵ�λ��
		this.setResizable(false);
		;
		// �����޸Ĵ��ڵĴ�С
		this.setLocationRelativeTo(getOwner());
		// ���ùر�״̬
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * ������ �ӹ����У���ת �����۶��� �����˻� ����ѯ��
	 */
	public void addsell() {
		// �������
		mainPanel = gs.createPanel(5, 5, 775, 450);
		ordManag = gs.createButton("���۶���", 500, 300, 130, 30, "����", 24);
		mercharetu = gs.createButton("�����˻�", 500, 410, 130, 30, "����", 24);
		currInvenQuery = gs.createButton("����ѯ", 850, 345, 130, 30, "����", 24);
		mainPanel.add(ordManag);
		mainPanel.add(mercharetu);
		mainPanel.add(currInvenQuery);
		this.add(mainPanel);
	}

	/**
	 * ����¼�
	 */
	public void clickEvent() {
		/**
		 * ��ת��Ʒ�˻�����
		 */
		mercharetu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��Ʒ�˻�
				new Merchandise_returns();

			}
		});

		/**
		 * ��ת����������
		 */
		ordManag.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ʱʱ��
				String date = "2017-8-13";
				Date sell_date = Date.valueOf(date);
				// ��ʱԱ���������
				int sell_empId = 2;
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
		/**
		 * ��ת����ѯ����
		 */
		currInvenQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Inventory_query();

			}
		});
	}

	public static void main(String[] args) {
		new SellPanel();
	}
}
