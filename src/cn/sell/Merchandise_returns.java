package cn.sell;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;

/**
 * ɾ ��Ʒ�˻�
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import cn.dao.sell.SalesDao2;
import cn.liuenci.swing.DateChooser;
import cn.model.sell.RefunddetailsOrder;
import cn.view.purchase.MTable;

/**
 * 1.����:2017-08-18
 * 2.���๦��:
 * a.��Ʒ�˻�����ϱ�˫��ȫ�˹���
 * ������ʱ������˻���
 * �����ݶ�����ģ����ѯѡ���˻���
 * b.��Ʒ�˻�����±�˫��ѡ�����˻�����
 * c.�˻���ѯ����ϲ�ѯ�������Ļ�����Ϣ
 * d.�˻���ѯ����²�ѯ����������ϸ��Ϣ
 * ������ʱ����ѯ��
 * �����ݶ�����ģ����ѯ��
 * @author ���һ�
 *
 */
public class Merchandise_returns extends JFrame {
	JSplitPane reSplitupanddown;// �˻�ѡ��������·ָ����
	private JTable reupperform;// �˻�ѡ��ϱ�����
	private DefaultTableModel reupperdm;// �˻�ѡ��ϱߴ�����ģ��
	private JTable redownform;// �˻�ѡ��±�����
	private DefaultTableModel redowndm;// �˻�ѡ��±ߴ�����ģ��
	private JScrollPane redownrollingpanel;;// �˻�ѡ� �����±߹������
	private JScrollPane reupperrollingpanel;// �˻�ѡ������ϱ߹������
	private JPanel reupperrolling;// �˻�ѡ������
	private JPanel redownrollin;// �˻�ѡ������
	private JLabel requerytime;// �˻�ѡ���ѯʱ��
	private JTextField reChoicetime;// �˻�ѡ�ѡ��ʱ��
	private JButton requeryButton;// �˻�ѡ� ʱ���ѯ��ť
	private JLabel reinputOrder;// �˻�ѡ����붩��
	private JTextField reOrderinputframe;// �˻�ѡ����������
	private JButton reOrderqueryButton;// �˻�ѡ������Ų�ѯ��ť
	private JTextField reNumbers;// �˻�ѡ���������ʾ��
	int reid;// �˻�ѡ����ݺ�
	String reCustomer;// �˻�ѡ��ͻ�

	private JPanel retitlePanel;// �˻�ѡ��������
	private JPanel rebuttonpanel;// �˻�ѡ���ť���
	private JLabel reupTotal;// ��Ʒ�˻��ϱ�����
	private JLabel retotalColle;// ��Ʒ�˻��ϱ��������
	private JPanel reUpsummary;// �ϻ������
	private JPanel redowummary;// �»���
	private JLabel redowTotal;// �±�����
	private JLabel redowtalColle;// �±��������
	// ------------------------------------------------------------
	private JSplitPane quSplitupanddown;// �˻�ѡ��������·ָ����
	private JTable quupperform;// �˻�ѡ��ϱ�����
	private DefaultTableModel quupperdm;// �˻�ѡ��ϱߴ�����ģ��
	private JTable qudownform;// �˻�ѡ��±�����
	private DefaultTableModel qudowndm;// �˻�ѡ��±ߴ�����ģ��
	private JScrollPane qudownrollingpanel;;// �˻�ѡ� �����±߹������
	private JScrollPane quupperrollingpanel;// �˻�ѡ������ϱ߹������
	private JPanel quupperrolling;// �˻�ѡ������
	private JPanel qudownrollin;// �˻�ѡ������
	private JLabel ququerytime;// �˻�ѡ���ѯʱ��
	private JTextField quChoicetime;// �˻�ѡ�ѡ��ʱ��
	private JButton ququeryButton;// �˻�ѡ� ʱ���ѯ��ť
	private JLabel quinputOrder;// �˻�ѡ����붩��
	private JTextField quOrderinputframe;// �˻�ѡ����������
	private JButton quOrderqueryButton;// �˻�ѡ������Ų�ѯ��ť
	private JTextField quNumbers;// �˻�ѡ���������ʾ��
	int quid;// �˻�ѡ����ݺ�
	String quCustomer;// �˻�ѡ��ͻ�
	// int qugoodsid;// �˻�ѡ���Ʒ���
	private JPanel qutitlePanel;// �˻�ѡ��������
	private JPanel qubuttonpanel;// �˻�ѡ���ť���
	private JLabel quupTotal;// ��Ʒ�˻��ϱ�����
	private JLabel qutotalColle;// ��Ʒ�˻��ϱ��������
	private JPanel quUpsummary;// �ϻ������
	private JPanel qudowummary;// �»���
	private JLabel qudowTotal;// ��Ʒ�˻��±�����
	private JLabel qudowtalColle;// ��Ʒ�˻��±��������
	// ------------------------------------------------------------
	SalesDao2 dao = new SalesDao2();
	Format gs = new Format();// ��ʽ
	JSplitPane queryreturn;// ��ѯ�˻��ָ����
	JTabbedPane tabpanel;// ѡ����
	JPanel mainpanel;// �����
	// ��ʼ��

	public Merchandise_returns() {
		this.inin();
		Tabpanel();
		clickEvent();
		// ���ô��ڿɼ�
		this.setVisible(true);

	}

	public void inin() {
		// ���ô��ڱ���
		this.setTitle("��Ʒ�˻�");
		// ���ô��ڴ�С
		this.setSize(815, 530);
		// ������ʾ���ڵ�λ��
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		// ���ô��ڹر�
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void Tabpanel() {
		// ����������(��Ʒ�˻���)
		reupperrolling = new JPanel();
		// ����Ϊnull(��Ʒ�˻���)
		reupperrolling.setLayout(null);
		// ����������(��Ʒ�˻���)
		redownrollin = new JPanel();
		// ����Ϊnull(��Ʒ�˻���)
		redownrollin.setLayout(null);
		// ����������(�˻���ѯ��)
		quupperrolling = new JPanel();
		quupperrolling.setLayout(null);
		// ����������(�˻���ѯ��)
		qudownrollin = new JPanel();
		qudownrollin.setLayout(null);
		tabpanel = new JTabbedPane(JTabbedPane.TOP);// ��ʾ���Ϸ�
		tabpanel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabpanel.setBounds(0, 0, 812, 520);
		// �����
		mainpanel = new JPanel();
		mainpanel.setLayout(null);
		mainpanel.add(tabpanel);
		// �����ָ�������
		reSplitupanddown = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		// ����������巽��
		this.classupperrolling();
		this.classdownrollin();
		reSplitupanddown.add(reupperrolling, JSplitPane.TOP);
		reSplitupanddown.add(redownrollin, JSplitPane.BOTTOM);
		reSplitupanddown.setDividerLocation(280);
		reSplitupanddown.setEnabled(false);
		// ���۲�ѯ�˻������
		this.Salesqueryreturnspanel();
		// ���۲�ѯ�˻������
		this.Salesinquiriesunderthepanel();
		quSplitupanddown = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		quSplitupanddown.add(quupperrolling, JSplitPane.TOP);
		quSplitupanddown.add(qudownrollin, JSplitPane.BOTTOM);
		quSplitupanddown.setDividerLocation(280);
		quSplitupanddown.setEnabled(false);
		tabpanel.add("��Ʒ�˻�", reSplitupanddown);
		tabpanel.add("�˻���ѯ", quSplitupanddown);
		this.add(tabpanel);
	}

	// �����˻���
	public void classupperrolling() {
		requerytime = gs.createLabel("��ѯʱ��:", 25, 10, 100, 25, "����", 14);

		reChoicetime = new JTextField("����ѡ��ʱ��");
		DateChooser dateChoosers = DateChooser.getInstance("yyyy-MM-dd");
		dateChoosers.register(reChoicetime);
		reChoicetime.setBounds(100, 10, 160, 25);
		// �����Ų�ѯ��ť
		requeryButton = gs.createButton("��ѯ", 280, 10, 60, 25, "����", 14);

		reinputOrder = gs.createLabel("�����붩����:", 450, 10, 100, 25, "����", 14);
		reOrderinputframe = gs.createTextField(null, 540, 10, 160, 25, "����", 14, Color.white);
		// �����Ų�ѯ��ť
		reOrderqueryButton = gs.createButton("��ѯ", 720, 10, 60, 25, "����", 14);
		// �������
		String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��������", "��ע" };
		List<RefunddetailsOrder> lststu = dao.SeeRefundOrder();
		Object[][] rowsellOrderform = new Object[lststu.size()][];
		for (int i = 0; i < rowsellOrderform.length; i++) {
			RefunddetailsOrder sb = lststu.get(i);
			Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
					sb.getBilltype(), sb.getSell_mark() };
			rowsellOrderform[i] = obj;
		}
		reupperdm = new DefaultTableModel(rowsellOrderform, strsellOrderform);
		reupperform = new JTable(reupperdm);
		reupperform = new MTable(reupperdm);
		reupperrollingpanel = new JScrollPane(reupperform);
		reupperrollingpanel.setBounds(5, 105, 790, 140);
		double money = 0;
		for (int i = 0; i < reupperform.getRowCount(); i++) {
			money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
		}
		// ��Ʒ�˻��ϱ�����
		reupTotal = gs.createLabel("�ܵ���:" + reupperform.getRowCount(), 5, 5, 100, 20, "����", 14);
		// ��Ʒ�˻��ϱ��������
		retotalColle = gs.createLabel("�ܼ�:" + money, 395, 5, 100, 20, "����", 14);
		retitlePanel = gs.createPanel(5, 5, 790, 45);
		JLabel title = gs.createLabel("��Ʒ�˻�", 365, 10, 100, 25, "����", 16);
		// ��ť���
		rebuttonpanel = gs.createPanel(5, 55, 790, 45);
		retitlePanel.add(title);
		// �������
		reUpsummary = gs.createPanel(5, 247, 790, 30);
		// �˿��ϱ�
		reUpsummary.add(retotalColle);
		reUpsummary.add(reupTotal);
		reupperrolling.add(reUpsummary);
		reupperrolling.add(retitlePanel);
		reupperrolling.add(rebuttonpanel);
		rebuttonpanel.add(requerytime);
		rebuttonpanel.add(reChoicetime);
		rebuttonpanel.add(requeryButton);
		rebuttonpanel.add(reinputOrder);
		rebuttonpanel.add(reOrderinputframe);
		rebuttonpanel.add(reOrderqueryButton);
		reupperrolling.add(reupperrollingpanel);
		this.add(reupperrolling);
	}

	// �����˻���
	public void classdownrollin() {
		// ��ʾ����
		JLabel lbmain = new JLabel("���۵�����ϸ��Ϣ��");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("΢���ź�", Font.BOLD, 13));
		lbmain.setForeground(Color.black);
		reNumbers = new JTextField("ID:" + reid + reCustomer);
		reNumbers.setBounds(130, 0, 200, 25);
		reNumbers.setFont(new Font("΢���ź�", Font.BOLD, 12));
		MatteBorder border1 = new MatteBorder(0, 0, 0, 0, Color.RED);
		reNumbers.setBorder(border1);
		// �������
		String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С", "��Ʒ״̬" };
		redowndm = new DefaultTableModel(null, str);
		redownform = new JTable(redowndm);
		redownform = new MTable(redowndm);
		redownrollingpanel = new JScrollPane(redownform);
		redownrollingpanel.setBounds(5, 30, 790, 130);
		// ��Ʒ�˻��±�����
		redowTotal = gs.createLabel("�ܵ���:" + 0, 5, 0, 100, 20, "����", 12);
		// ��Ʒ�˻��±��������
		redowtalColle = gs.createLabel("С��:" + 0.0, 505, 0, 100, 20, "����", 12);
		// �������
		redowummary = gs.createPanel(5, 160, 790, 20);
		redowummary.add(redowTotal);
		redowummary.add(redowtalColle);
		redownrollin.add(redowummary);
		redownrollin.add(redownrollingpanel);
		redownrollin.add(reNumbers);
		redownrollin.add(lbmain);
		this.add(redownrollin);
	}

	// ���۲�ѯ�˻������
	private void Salesinquiriesunderthepanel() {
		ququerytime = gs.createLabel("��ѯʱ��:", 25, 10, 100, 25, "����", 14);

		quChoicetime = new JTextField("����ѡ��ʱ��");
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(quChoicetime);
		quChoicetime.setBounds(100, 10, 160, 25);
		// �����Ų�ѯ��ť
		ququeryButton = gs.createButton("��ѯ", 280, 10, 60, 25, "����", 14);

		quinputOrder = gs.createLabel("�����붩����:", 450, 10, 100, 25, "����", 14);
		quOrderinputframe = gs.createTextField(null, 540, 10, 160, 25, "����", 14, Color.white);
		// �����Ų�ѯ��ť
		quOrderqueryButton = gs.createButton("��ѯ", 720, 10, 60, 25, "����", 14);
		// �������
		String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��������", "��ע" };
		List<RefunddetailsOrder> lststu = dao.viewSalesOrderStatus();
		Object[][] rowsellOrderform = new Object[lststu.size()][];
		for (int i = 0; i < rowsellOrderform.length; i++) {
			RefunddetailsOrder sb = lststu.get(i);
			Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
					sb.getBilltype(), sb.getSell_mark() };
			rowsellOrderform[i] = obj;
		}
		quupperdm = new DefaultTableModel(rowsellOrderform, strsellOrderform);
		quupperform = new JTable(quupperdm);
		quupperform = new MTable(quupperdm);
		quupperrollingpanel = new JScrollPane(quupperform);
		quupperrollingpanel.setBounds(5, 105, 790, 140);
		double money = 0;
		for (int i = 0; i < quupperform.getRowCount(); i++) {
			money += Double.parseDouble(quupperdm.getValueAt(i, 3).toString());
		}
		// ��Ʒ�˻��ϱ�����
		quupTotal = gs.createLabel("�ܵ���:" + quupperform.getRowCount(), 5, 5, 100, 20, "����", 14);
		// ��Ʒ�˻��ϱ��������
		qutotalColle = gs.createLabel("�ܼ�:" + money, 395, 5, 100, 20, "����", 14);
		// �������
		qutitlePanel = gs.createPanel(5, 5, 790, 45);
		JLabel title = gs.createLabel("�˻���ѯ", 365, 10, 100, 25, "����", 16);
		// ��ť���
		qubuttonpanel = gs.createPanel(5, 55, 790, 45);
		qutitlePanel.add(title);
		// �������
		quUpsummary = gs.createPanel(5, 247, 790, 30);
		// �˿��ϱ�
		quUpsummary.add(qutotalColle);
		quUpsummary.add(quupTotal);
		quupperrolling.add(quUpsummary);
		quupperrolling.add(qutitlePanel);
		quupperrolling.add(qubuttonpanel);
		quupperrolling.add(quupperrollingpanel);
		// ��ť
		qubuttonpanel.add(ququerytime);
		qubuttonpanel.add(quChoicetime);
		qubuttonpanel.add(ququeryButton);
		qubuttonpanel.add(quinputOrder);
		qubuttonpanel.add(quOrderinputframe);
		qubuttonpanel.add(quOrderqueryButton);

		this.add(quupperrolling);

	}

	// ���۲�ѯ�˻������
	private void Salesqueryreturnspanel() {
		// ��ʾ����
		JLabel lbmain = new JLabel("���۵�����ϸ��Ϣ��");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("΢���ź�", Font.BOLD, 13));
		lbmain.setForeground(Color.black);
		quNumbers = new JTextField("ID:" + quid + quCustomer);
		quNumbers.setBounds(130, 0, 200, 25);
		quNumbers.setFont(new Font("΢���ź�", Font.BOLD, 12));
		MatteBorder border1 = new MatteBorder(0, 0, 0, 0, Color.RED);
		quNumbers.setBorder(border1);
		// �������
		String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С", "��Ʒ״̬" };
		qudowndm = new DefaultTableModel(null, str);
		qudownform = new JTable(qudowndm);
		qudownform = new MTable(qudowndm);
		qudownrollingpanel = new JScrollPane(qudownform);
		qudownrollingpanel.setBounds(5, 30, 790, 130);
		double money = 0;
		int number = 0;
		for (int i = 0; i < qudownform.getRowCount(); i++) {
			money += Double.parseDouble(qudowndm.getValueAt(i, 5).toString());
			number += Integer.parseInt(qudowndm.getValueAt(i, 4).toString());
		}
		// ��Ʒ�˻��±�����
		qudowTotal = gs.createLabel("�ܵ���:" + qudownform.getRowCount(), 5, 0, 100, 20, "����", 12);
		// ��Ʒ�˻��±��������
		qudowtalColle = gs.createLabel("С��:" + money, 505, 0, 100, 20, "����", 12);
		// �������
		qudowummary = gs.createPanel(5, 160, 790, 20);
		qudowummary.add(qudowTotal);
		qudowummary.add(qudowtalColle);
		qudownrollin.add(qudowummary);
		qudownrollin.add(qudownrollingpanel);
		qudownrollin.add(quNumbers);
		qudownrollin.add(lbmain);
		this.add(qudownrollin);

	}

	public void clickEvent() {
		/**
		 * �ϱ�����¼�
		 */
		reupperform.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = reupperform.getSelectedRow();// ��ȡ��
				if (e.getClickCount() == 1) {
					reid = Integer.parseInt(reupperform.getValueAt(row, 0).toString());
					reCustomer = reupperform.getValueAt(row, 2).toString();
					reNumbers.setText("ID:" + reid + "	Name:" + reCustomer);
					// �������id��ʾ�ѳ����������Ϣ
					List<RefunddetailsOrder> lststu = dao.SeeRefunddetailsOrder(reid);
					Object[][] rowparticularsform = new Object[lststu.size()][];
					for (int i = 0; i < rowparticularsform.length; i++) {
						RefunddetailsOrder sb = lststu.get(i);
						// "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С"
						String status = sb.getsDet_status() == 0 ? "������" : "���˻�";
						Object[] obj = { sb.getsDet_goodId(), sb.getGoods_name(), sb.getGoods_units(),
								sb.getGoods_sellPrice(), sb.getsDet_number(), sb.getsDet_goodPrice(),
								sb.getGoods_size(), status };
						rowparticularsform[i] = obj;
					}
					String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С", "��Ʒ״̬" };
					redowndm.setDataVector(rowparticularsform, str);
					double money = 0;
					int number = 0;
					for (int i = 0; i < redownform.getRowCount(); i++) {
						money += Double.parseDouble(redowndm.getValueAt(i, 5).toString());
						number += Integer.parseInt(redowndm.getValueAt(i, 4).toString());
					}
					redowTotal.setText("�ܵ���:" + redownform.getRowCount());
					redowtalColle.setText("С��:" + money);
				} else if (e.getClickCount() == 2) {
					Object[] options = { "ȷ��", "ȡ��" };
					int response = JOptionPane.showOptionDialog(null, "��ȷ���Ƿ��˿�", "��ʾ", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					int sup = 0;
					if (response == 0) {
						for (int i = 0; i < redownform.getRowCount(); i++) {
							sup = dao.update((int) (redownform.getValueAt(i, 4)),
									Integer.parseInt(redownform.getValueAt(i, 0).toString()),
									Integer.parseInt(reupperform.getValueAt(row, 0).toString()));
						}
						if (sup > 0) {
							redowndm.setRowCount(0);
							JOptionPane.showMessageDialog(null, "�˻��ɹ���");
						} else {
							JOptionPane.showMessageDialog(null, "�˻�ʧ�ܣ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
						}
						// ���ñ��
						String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��������", "��ע" };
						List<RefunddetailsOrder> lststu = dao.SeeRefundOrder();
						Object[][] rowsellOrderform = new Object[lststu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lststu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						reupTotal.setText("�ܵ���:" + reupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < reupperform.getRowCount(); i++) {
							money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
						}
						retotalColle.setText("�ܼ�:" + money);
						redowTotal.setText("�ܵ���:" + 0);
						redowtalColle.setText("С��:" + 0.0);
					}
				}
			}
		});
		/**
		 * �±�����¼�
		 */
		redownform.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int sup = 0;
				int row = redownform.getSelectedRow();// ��ȡ��
				if (e.getClickCount() == 2) {
					Object[] options = { "ȷ��", "ȡ��" };
					int response = JOptionPane.showOptionDialog(null, "��ȷ���Ƿ��˿�", "��ʾ", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					if (response == 0) {
						if (redowndm.getRowCount() > 1) {
							// ���ݿ����Ϣ
							int goodup = Integer.parseInt(redownform.getValueAt(row, 4).toString());
							int goodid = Integer.parseInt(redownform.getValueAt(row, 0).toString());
							sup = dao.updetails(goodup, goodid, reid);
						} else if (redowndm.getRowCount() == 1) {
							sup = dao.update(Integer.parseInt(redownform.getValueAt(row, 4).toString()),
									Integer.parseInt(redownform.getValueAt(row, 0).toString()), reid);
							String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��������", "��ע" };
							List<RefunddetailsOrder> lststu = dao.SeeRefundOrder();
							Object[][] rowsellOrderform = new Object[lststu.size()][];
							for (int i = 0; i < rowsellOrderform.length; i++) {
								RefunddetailsOrder sb = lststu.get(i);
								Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(),
										sb.getSell_profit(), sb.getBilltype(), sb.getSell_mark() };
								rowsellOrderform[i] = obj;
							}
							reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
							reNumbers.setText("ID:" + 0 + " Name:" + null);
							reupTotal.setText("�ܵ���:" + reupperform.getRowCount());
							double money = 0;
							for (int i = 0; i < reupperform.getRowCount(); i++) {
								money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
							}
							retotalColle.setText("�ܼ�:" + money);
							redowTotal.setText("�ܵ���:" + 0);
							redowtalColle.setText("С��:" + 0.0);
						}
						if (sup > 0) {
							redownform.setValueAt("���˻�", row, 7);
							JOptionPane.showMessageDialog(null, "�˻��ɹ���");
						} else {
							JOptionPane.showMessageDialog(null, "�˻�ʧ�ܣ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
						}
					}
					List<RefunddetailsOrder> lststu = dao.SeeRefunddetailsOrder(reid);
					Object[][] rowparticularsform = new Object[lststu.size()][];
					for (int i = 0; i < rowparticularsform.length; i++) {
						RefunddetailsOrder sb = lststu.get(i);
						// "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С"
						String status = sb.getsDet_status() == 0 ? "������" : "���˻�";
						Object[] obj = { sb.getsDet_goodId(), sb.getGoods_name(), sb.getGoods_units(),
								sb.getGoods_sellPrice(), sb.getsDet_number(), sb.getsDet_goodPrice(),
								sb.getGoods_size(), status };
						rowparticularsform[i] = obj;
					}
					String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С", "��Ʒ״̬" };
					redowndm.setDataVector(rowparticularsform, str);
					double money = 0;
					int number = 0;
					for (int i = 0; i < redownform.getRowCount(); i++) {
						money += Double.parseDouble(redowndm.getValueAt(i, 5).toString());
						number += Integer.parseInt(redowndm.getValueAt(i, 4).toString());
					}
					redowTotal.setText("�ܵ���:" + redownform.getRowCount());
					redowtalColle.setText("С��:" + money);
				}
			}
		});

		/**
		 * ���ݶ����Ų�ѯ
		 */
		reOrderqueryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				redowndm.setRowCount(0);
				reNumbers.setText("ID:" + 0 + "	Name:" + null);
				try {
					if (!reOrderinputframe.getText().equals("")) {
						int idname = Integer.parseInt(reOrderinputframe.getText());
						List<RefunddetailsOrder> lstu = dao.To_find_the(idname);
						Object[][] rowsellOrderform = new Object[lstu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lstu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��������", "��ע" };
						reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						reupTotal.setText("�ܵ���:" + reupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < reupperform.getRowCount(); i++) {
							money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
						}
						retotalColle.setText("�ܼ�:" + money);
						qudowTotal.setText("�ܵ���:" + 0);
						qudowtalColle.setText("С��:" + 0.0);
					} else {
						// �������
						String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��������", "��ע" };
						List<RefunddetailsOrder> lststu = dao.SeeRefundOrder();
						Object[][] rowsellOrderform = new Object[lststu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lststu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						reupTotal.setText("�ܵ���:" + reupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < reupperform.getRowCount(); i++) {
							money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
						}
						retotalColle.setText("�ܼ�:" + money);
						redowTotal.setText("�ܵ���:" + 0);
						redowtalColle.setText("С��:" + 0.0);
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "��������ȷ�Ķ������");
				}

			}
		});
		/**
		 * ����ʱ���ѯ���۵Ķ���
		 */

		requeryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reNumbers.setText("ID:" + 0 + "	Name:" + null);
				redowndm.setRowCount(0);
				try {
					if (reupperdm.getRowCount() == -1) {
						JOptionPane.showMessageDialog(null, "�գ�������ѡ������");
					} else if (reupperdm.getRowCount() >= 0 || !reChoicetime.getText().equals("")) {
						List<RefunddetailsOrder> lstu = dao.stafftime(Date.valueOf(reChoicetime.getText()));
						// ������Ϣ��ά����
						Object[][] rowsellOrderform = new Object[lstu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lstu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��������", "��ע" };
						reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						reupTotal.setText("�ܵ���:" + reupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < reupperform.getRowCount(); i++) {
							money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
						}
						retotalColle.setText("�ܼ�:" + money);
						redowTotal.setText("�ܵ���:" + 0);
						redowtalColle.setText("С��:" + 0.0);
					}
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, "��������ȷ������");
				}

			}
		});
		// ��ѯ�ϱ�����¼�
		quupperform.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = quupperform.getSelectedRow();// ��ȡ��
				if (e.getClickCount() == 1) {
					quid = Integer.parseInt(quupperform.getValueAt(row, 0).toString());
					quCustomer = quupperform.getValueAt(row, 2).toString();
					quNumbers.setText("ID:" + quid + "	Name:" + quCustomer);
					// �������id��ʾ�ѳ����������Ϣ
					List<RefunddetailsOrder> lststu = dao.ordernumberfordetailsof(quid);
					Object[][] rowparticularsform = new Object[lststu.size()][];
					for (int i = 0; i < rowparticularsform.length; i++) {
						RefunddetailsOrder sb = lststu.get(i);
						// "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С"
						String status = sb.getsDet_status() == 1 ? "���˻�" : "������";
						Object[] obj = { sb.getsDet_goodId(), sb.getGoods_name(), sb.getGoods_units(),
								sb.getGoods_sellPrice(), sb.getsDet_number(), sb.getsDet_goodPrice(),
								sb.getGoods_size(), status };
						rowparticularsform[i] = obj;
					}
					String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С", "��Ʒ״̬" };
					qudowndm.setDataVector(rowparticularsform, str);
					double money = 0;
					int number = 0;
					for (int i = 0; i < qudownform.getRowCount(); i++) {
						money += Double.parseDouble(qudowndm.getValueAt(i, 5).toString());
						number += Integer.parseInt(qudowndm.getValueAt(i, 4).toString());
					}
					qudowTotal.setText("�ܵ���:" + qudownform.getRowCount());
					qudowtalColle.setText("С��:" + money);
				}
			}
		});
		/**
		 * ����ʱ���ѯ���۵Ķ���
		 */

		ququeryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quNumbers.setText("ID:" + 0 + "	Name:" + null);
				qudowndm.setRowCount(0);
				try {
					if (quupperdm.getRowCount() == -1) {
						JOptionPane.showMessageDialog(null, "�գ�������ѡ������");
					} else if (quupperdm.getRowCount() >= 0 || !quChoicetime.getText().equals("")) {
						List<RefunddetailsOrder> lstu = dao.checkthedocumentsaccordingtothetime(Date.valueOf(quChoicetime.getText()));
						// ������Ϣ��ά����
						Object[][] rowsellOrderform = new Object[lstu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lstu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��������", "��ע" };
						quupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						quupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						quupTotal.setText("�ܵ���:" + quupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < quupperform.getRowCount(); i++) {
							money += Double.parseDouble(quupperdm.getValueAt(i, 3).toString());
						}
						qutotalColle.setText("�ܼ�:" + money);
						qudowTotal.setText("�ܵ���:" + 0);
						qudowtalColle.setText("С��:" + 0.0);
					}
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, "��������ȷ������");
				}

			}
		});
		//���ݶ�����ѯ
		quOrderqueryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				qudowndm.setRowCount(0);
				quNumbers.setText("ID:" + 0 + "	Name:" + null);
				try {
					if (!quOrderinputframe.getText().equals("")) {
						int idname = Integer.parseInt(quOrderinputframe.getText());
						List<RefunddetailsOrder> lstu = dao.findorderaccordingtoID(idname);
						Object[][] rowsellOrderform = new Object[lstu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lstu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��������", "��ע" };
						quupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						quupTotal.setText("�ܵ���:" + quupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < quupperform.getRowCount(); i++) {
							money += Double.parseDouble(quupperdm.getValueAt(i, 3).toString());
						}
						qutotalColle.setText("�ܼ�:" + money);
						qudowTotal.setText("�ܵ���:" + 0);
						qudowtalColle.setText("С��:" + 0.0);
					} else {
						// �������
						String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��������", "��ע" };
						List<RefunddetailsOrder> lststu = dao.viewSalesOrderStatus();
						Object[][] rowsellOrderform = new Object[lststu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lststu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						quupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						quupTotal.setText("�ܵ���:" + quupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < quupperform.getRowCount(); i++) {
							money += Double.parseDouble(quupperdm.getValueAt(i, 3).toString());
						}
						qutotalColle.setText("�ܼ�:" + money);
						qudowTotal.setText("�ܵ���:" + 0);
						qudowtalColle.setText("С��:" + 0.0);
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "��������ȷ�Ķ������");
				}
			}
		});
	}

	public static void main(String[] args) {
		new Merchandise_returns();
	}

}
