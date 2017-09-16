package cn.sell;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import cn.model.sell.PurchaseOrderDetailGood;
import cn.model.sell.PurchaseOrderEmpSup;
import cn.model.sell.RefundOrder;
import cn.model.sell.RefunddetailsOrder;
import cn.view.purchase.MTable;

public class Commodity_management extends JFrame {
	private JPanel Majp;// �����
	private JPanel StoLejp;// ��������
	private JPanel Striupjp;// �������
	private JSplitPane Stuplojp;// ��洴�����·ָ����
	private JPanel Strilowjp;// �������
	private JPanel Purupjp;// �ϲɹ�
	private JPanel PuLowjp;// �²ɹ�
	private JPanel Salupjp;// ������
	private JPanel Salowjp;// ������
	private JTabbedPane tp;// ѡ����
	private JSplitPane StoSpl;// ��洴�����ҷָ����
	private JSplitPane PurSpl;// �ɹ��������·ָ����
	private JSplitPane SaSpl;// ���۴������·ָ����
	// �ɹ�
	private JLabel Purque;// ��ѯʱ��
	private JTextField PurCho;// ѡ��ʱ��
	private JButton Purqueb;// ʱ���ѯ��ť
	private JLabel Purinpu;// ���붩��
	private JTextField PurOrder;// ���������
	private JButton PurOrderqu;// �����Ų�ѯ��ť

	// ------------------------------
	// ����
	private JLabel Saque;// ��ѯʱ��
	private JTextField SaCho;// ѡ��ʱ��
	private JButton Saqueb;// ʱ���ѯ��ť
	private JLabel Sainpu;// ���붩��
	private JTextField SaOrder;// ���������
	private JButton SaOrderqu;// �����Ų�ѯ��ť
	// ------------------------------
	// �ɹ�
	private DefaultTableModel Puupdm;// ������Ʒ��Ϣ�ϱ��
	private JTable Puupjl;// ����
	private JScrollPane Puupsp;// �������
	private DefaultTableModel Pulodm;// ������Ʒ��Ϣ�±��
	private JTable Pulojl;// ����
	private JScrollPane Pulosp;// �������
	// ------------------------------
	// ����
	private DefaultTableModel Saupdm;// ������Ʒ��Ϣ�ϱ��
	private JTable Saupjl;// ����
	private JScrollPane Saupsp;// �������
	private DefaultTableModel Salodm;// ������Ʒ��Ϣ�±��
	private JTable Salojl;// ����
	private JScrollPane Salosp;// �������

	// ------------------------------
	// �ɹ�
	private JLabel Purintat;// ��������ǩ
	private JLabel Purtotal;// �ܼ۱�ǩ
	private JTextField PurNumb;// ��ʾ���ݺ��ı������
	int Purmai;// ��������
	double Purtomo = 0.0;// �ϱ���Ǯ
	int Purid;// �Ž��ı���ĵ��ݺţ�PurNumb��
	// �±������ܼ�
	JLabel PuTosepr;// ���±��ۼ��ܼ�
	JLabel Puloto;// ���±�������
	JLabel PuTopr;// ���±��ܼ��ܼ�Pupr
	JLabel Punum;// ������
	JLabel PuTtpp;// �����ܼ�
	// ----------------------------------
	// ����
	private JLabel Saintat;// ��������ǩ
	private JLabel Satotal;// �ܼ۱�ǩ
	private JTextField SaNumb;// ��ʾ���ݺ��ı������
	int Samai;// ��������
	double Satomo = 0.0;// �ϱ���Ǯ
	int Said;// �Ž��ı���ĵ��ݺţ�PurNumb��
	JLabel Sanum;
	JLabel Saquto;
	JLabel SaTopr;
	JLabel SaTosepr;
	// ------------------------------------------------
	// ����

	// ��ʽ
	Format gs = new Format();
	// dao��
	SalesDao2 dao = new SalesDao2();
	int row;

	public Commodity_management() {
		this.inin();
		Stock();
		Purchase();
		Sale();
		PuClickevent();
		SaClickevent();
		this.setVisible(true);

	}

	public void inin() {
		// ���ô��ڱ���
		this.setTitle("��ǰ����ѯ");
		// ���ô��ڴ�С
		this.setSize(815, 530);
		// ������ʾ���ڵ�λ��
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		// ���ô��ڹر�
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void Stock() {
		Majp = new JPanel();
		Majp.setLayout(null);
		Majp.setBounds(5, 5, 807, 520);
		tp = new JTabbedPane(JTabbedPane.TOP);// ��ʾ���Ϸ�
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tp.setBounds(5, 5, 800, 510);
		Majp.add(tp);
		this.add(Majp);

	}

	public void Purchase() {
		PurSpl = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		Purupjp = new JPanel();
		Purupjp.setLayout(null);
		PurSpl.add(Purupjp, JSplitPane.TOP);
		PuLowjp = new JPanel();
		PuLowjp.setLayout(null);
		Purque = gs.createLabel("��ѯʱ��:", 25, 15, 100, 25, "����", 12);

		PurCho = new JTextField("����ѡ��ʱ��");
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(PurCho);
		PurCho.setBounds(100, 15, 160, 25);
		// �����Ų�ѯ��ť
		Purqueb = gs.createButton("��ѯ", 280, 15, 60, 25, "����", 12);

		Purinpu = gs.createLabel("�����붩����:", 450, 15, 100, 25, "����", 12);
		PurOrder = new JTextField();
		PurOrder.setBounds(540, 15, 160, 25);
		// �����Ų�ѯ��ť
		PurOrderqu = gs.createButton("��ѯ", 720, 15, 60, 25, "����", 12);
		// �������
		String[] pugood = { "���ݺ�", "����������", "��������", "֧���ܽ��", "������", "���״̬", "��ע" };
		List<PurchaseOrderEmpSup> lstu = dao.queryOrdTime();
		Object[][] rowtop = new Object[lstu.size()][];
		for (int i = 0; i < rowtop.length; i++) {
			// ��ȡ���϶���
			PurchaseOrderEmpSup purempsup = lstu.get(i);
			String purstatus = purempsup.getPur_status() == 0 ? "δ���"
					: (purempsup.getPur_status() == 1 ? "���ͨ��" : purempsup.getPur_status() == 2 ? "���δͨ��" : "ȡ������");
			// ����������תΪ����洢
			Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
					purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus, purempsup.getPur_mark() };
			// ����λ���鸳ֵ
			rowtop[i] = obj;
		}
		Puupdm = new DefaultTableModel(rowtop, pugood);
		Puupjl = new JTable(Puupdm);
		Puupjl = new MTable(Puupdm);
		Puupsp = new JScrollPane(Puupjl);
		Puupsp.setBounds(10, 65, 780, 100);
		// ������
		Purmai = Puupdm.getRowCount();
		// ͳ��
		Purintat = gs.createLabel("������:" + Purmai, 10, 165, 130, 25, "����", 12);
		for (int j = 0; j < Purmai; j++) {
			Purtomo += Double.parseDouble(Puupdm.getValueAt(j, 3).toString());
		}
		Purtotal = gs.createLabel("��" + Purtomo, 345, 165, 130, 25, "����", 12);
		PurSpl.add(PuLowjp, JSplitPane.BOTTOM);
		PurSpl.setDividerLocation(190);
		PurSpl.setEnabled(false);
		JLabel lbmain = new JLabel("�ɹ�������ϸ��Ϣ��");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("΢���ź�", Font.BOLD, 13));
		lbmain.setForeground(Color.black);
		PurNumb = new JTextField("ID:" + Purid);
		PurNumb.setBounds(130, 0, 200, 25);
		PurNumb.setFont(new Font("΢���ź�", Font.BOLD, 12));
		MatteBorder border1 = new MatteBorder(0, 0, 0, 0, Color.RED);
		PurNumb.setBorder(border1);
		String[] Sagood = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��", "����״̬" };
		Object[][] data = new Object[0][];
		Pulodm = new DefaultTableModel(null, Sagood);
		Pulojl = new JTable(Pulodm);
		Pulojl = new MTable(Pulodm);
		Pulosp = new JScrollPane(Pulojl);
		Pulosp.setBounds(10, 35, 780, 200);
		Punum = gs.createLabel("������:" + 0, 10, 240, 50, 25, "����", 12);
		PuTtpp = gs.createLabel("|" + 0.0, 265, 240, 50, 25, "����", 12);
		Puloto = gs.createLabel("|" + 0.0, 330, 240, 50, 25, "����", 12);
		PuTopr = gs.createLabel("|" + 0.0, 395, 240, 50, 25, "����", 12);
		PuTosepr = gs.createLabel("|" + 0, 460, 240, 50, 25, "����", 12);
		Purupjp.add(Purtotal);
		Purupjp.add(Purintat);
		Purupjp.add(Puupsp);
		Purupjp.add(Purque);
		Purupjp.add(PurCho);
		Purupjp.add(Purqueb);
		Purupjp.add(Purinpu);
		Purupjp.add(PurOrder);
		Purupjp.add(PurOrderqu);
		PuLowjp.add(lbmain);
		PuLowjp.add(PurNumb);
		PuLowjp.add(Pulosp);
		PuLowjp.add(Punum);
		PuLowjp.add(PuTtpp);
		PuLowjp.add(Puloto);
		PuLowjp.add(PuTosepr);
		PuLowjp.add(PuTopr);
		tp.add("������Ʒ��Ϣ", PurSpl);

	}

	public void Sale() {
		SaSpl = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		Salupjp = new JPanel();
		Salupjp.setLayout(null);
		SaSpl.add(Salupjp, JSplitPane.TOP);
		Salowjp = new JPanel();
		Salowjp.setLayout(null);
		Saque = gs.createLabel("��ѯʱ��:", 25, 15, 100, 25, "����", 12);

		SaCho = new JTextField("����ѡ��ʱ��");
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(SaCho);
		SaCho.setBounds(100, 15, 160, 25);
		// �����Ų�ѯ��ť
		Saqueb = gs.createButton("��ѯ", 280, 15, 60, 25, "����", 12);

		Sainpu = gs.createLabel("�����붩����:", 450, 15, 100, 25, "����", 12);
		SaOrder = new JTextField();
		SaOrder.setBounds(540, 15, 160, 25);
		// �����Ų�ѯ��ť
		SaOrderqu = gs.createButton("��ѯ", 720, 15, 60, 25, "����", 12);
		// �������
		String[] upgood = { "���۶������", "��������", "�ͻ�����", "�տ���", "��������", "������", "��ע" };
		List<RefundOrder> lststu = dao.SeeRefundOrderx();
		Object[][] rowsellOrderform = new Object[lststu.size()][];
		for (int i = 0; i < rowsellOrderform.length; i++) {
			RefundOrder sb = lststu.get(i);
			Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getCustomername(), sb.getSell_profit(),
					sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
			rowsellOrderform[i] = obj;
		}
		Saupdm = new DefaultTableModel(rowsellOrderform, upgood);
		Saupjl = new JTable(Saupdm);
		Saupjl = new MTable(Saupdm);
		Saupsp = new JScrollPane(Saupjl);
		Saupsp.setBounds(10, 65, 780, 100);
		// ������
		Samai = Saupdm.getRowCount();
		// ͳ��
		Saintat = gs.createLabel("������:" + Samai, 10, 165, 130, 25, "����", 12);
		for (int j = 0; j < Samai; j++) {
			Satomo += Double.parseDouble(Saupdm.getValueAt(j, 3).toString());
		}
		Satotal = gs.createLabel("��" + Satomo, 350, 165, 130, 25, "����", 12);
		SaSpl.add(Salowjp, JSplitPane.BOTTOM);
		SaSpl.setDividerLocation(190);
		SaSpl.setEnabled(false);
		JLabel lbmain = new JLabel("���۵�����ϸ��Ϣ��");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("΢���ź�", Font.BOLD, 13));
		lbmain.setForeground(Color.black);
		SaNumb = new JTextField("ID:" + Said);
		SaNumb.setBounds(130, 0, 200, 25);
		SaNumb.setFont(new Font("΢���ź�", Font.BOLD, 12));
		MatteBorder border1 = new MatteBorder(0, 0, 0, 0, Color.RED);
		SaNumb.setBorder(border1);
		String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С", "����״̬" };
		Salodm = new DefaultTableModel(null, str);
		Salojl = new JTable(Salodm);
		Salojl = new MTable(Salodm);
		Salosp = new JScrollPane(Salojl);
		Salosp.setBounds(10, 35, 780, 200);
		Sanum = gs.createLabel("������:" + 0, 10, 240, 50, 25, "����", 12);
		// �����ܼ�
		Saquto = gs.createLabel("|" + 0.0, 300, 240, 50, 25, "����", 12);
		// �����ܼ�
		SaTopr = gs.createLabel("|" + 0, 395, 240, 50, 25, "����", 12);
		// �ܽ��
		SaTosepr = gs.createLabel("|" + 0.0, 495, 240, 50, 25, "����", 12);
		Salupjp.add(Saque);
		Salupjp.add(Sainpu);
		Salupjp.add(Saqueb);
		Salupjp.add(SaOrder);
		Salupjp.add(SaOrderqu);
		Salupjp.add(SaCho);
		Salupjp.add(Saupsp);
		Salupjp.add(Saintat);
		Salupjp.add(Satotal);
		Salowjp.add(lbmain);
		Salowjp.add(Sanum);
		Salowjp.add(SaTopr);
		Salowjp.add(SaTosepr);
		Salowjp.add(SaNumb);
		Salowjp.add(Salosp);
		Salowjp.add(SaTosepr);
		Salowjp.add(SaTopr);
		Salowjp.add(Saquto);
		tp.add("������Ʒ��Ϣ", SaSpl);
	}

	public void PuClickevent() {
		/**
		 * �е��
		 */
		Puupjl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 1) {
					row = Puupjl.getSelectedRow();
					((DefaultTableModel) Pulojl.getModel()).getDataVector().clear(); // ����������
					List<PurchaseOrderDetailGood> lstugood = dao.queryTime((int) Puupjl.getValueAt(row, 0));
					Purid = (int) Puupjl.getValueAt(row, 0);
					PurNumb.setText("ID:" + Purid);
					// ��Ʒ��Ϣ��ά����
					Object[][] rows = new Object[lstugood.size()][];
					for (int i = 0; i < rows.length; i++) {
						// ��ȡ���϶���
						PurchaseOrderDetailGood goodpur = lstugood.get(i);
						String status = goodpur.getpDet_status() == 0 ? "�����"
								: (goodpur.getpDet_status() == 1 ? "δ���" : "���˻�");
						// ����������תΪ����洢
						Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
								goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getpDet_number(),
								goodpur.getGoods_purPrise() * goodpur.getpDet_number(), goodpur.getGoods_sellPrice(),
								goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(), goodpur.getGoods_minNumber(),
								status };
						// ����λ���鸳ֵ
						rows[i] = obj;
						Pulodm.addRow(obj);
					}
					int sum = Pulodm.getRowCount();
					// �ܼ�
					double Pupr = 0.0;
					// ����
					int Puto = 0;
					// �����ܼ�
					double Putoppr = 0.0;
					// �±��ۼ��ܼ�
					double Pusepr = 0.0;
					for (int i = 0; i < sum; i++) {
						Puto += (int) Pulodm.getValueAt(i, 5);
						Pupr += (double) Pulodm.getValueAt(i, 6);
						Putoppr += (double) Pulodm.getValueAt(i, 4);
						Pusepr += (double) Pulodm.getValueAt(i, 7);
					}
					PuTosepr.setText("|" + Pusepr);
					PuTtpp.setText("|" + Putoppr);
					Punum.setText("������:" + sum);
					Puloto.setText("|" + Puto);
					PuTopr.setText("|" + Pupr);

				}

			}
		});
		/**
		 * ������ѯ
		 */
		PurOrderqu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!PurOrder.getText().equals("")) {
					int Order_number = Integer.parseInt(PurOrder.getText());
					((DefaultTableModel) Pulojl.getModel()).getDataVector().clear(); // ����������
					((DefaultTableModel) Puupjl.getModel()).getDataVector().clear(); // ����������
					List<PurchaseOrderEmpSup> lstu = dao.queryOrdTime(Order_number);
					Object[][] rowtop = new Object[lstu.size()][];
					for (int i = 0; i < rowtop.length; i++) {
						// ��ȡ���϶���
						PurchaseOrderEmpSup purempsup = lstu.get(i);
						String purstatus = purempsup.getPur_status() == 0 ? "δ���"
								: (purempsup.getPur_status() == 1 ? "���ͨ��"
										: purempsup.getPur_status() == 2 ? "���δͨ��" : "ȡ������");
						// ����������תΪ����洢
						Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
								purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus, purempsup.getPur_mark() };
						// ����λ���鸳ֵ
						rowtop[i] = obj;
						String[] upgood = { "���۶������", "��������", "�ͻ�����", "�տ���", "��������", "������", "��ע" };
						Puupdm.setDataVector(rowtop, upgood);
						// ��ȡ
						Purid = (int) Puupjl.getValueAt(row, 0);

						PurNumb.setText("ID:" + Purid);

					}
					List<PurchaseOrderDetailGood> lstugood = dao.queryTime(Order_number);
					// ��Ʒ��Ϣ��ά����
					Object[][] rows = new Object[lstugood.size()][];
					for (int i = 0; i < rows.length; i++) {
						// ��ȡ���϶���
						PurchaseOrderDetailGood goodpur = lstugood.get(i);
						String status = goodpur.getpDet_status() == 0 ? "�����"
								: (goodpur.getpDet_status() == 1 ? "δ���" : "���˻�");
						// ����������תΪ����洢
						Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
								goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getpDet_number(),
								goodpur.getGoods_purPrise() * goodpur.getpDet_number(), goodpur.getGoods_sellPrice(),
								goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(), goodpur.getGoods_minNumber(),
								status };
						// ����λ���鸳ֵ
						rows[i] = obj;
						Pulodm.addRow(obj);

					}

					int sum = Pulodm.getRowCount();
					// �ܼ�
					double Pupr = 0.0;
					// ����
					int Puto = 0;
					// �����ܼ�
					double Putoppr = 0.0;
					// �±��ۼ��ܼ�
					double Pusepr = 0.0;
					for (int i = 0; i < sum; i++) {
						Puto += (int) Pulodm.getValueAt(i, 5);
						Pupr += (double) Pulodm.getValueAt(i, 6);
						Putoppr += (double) Pulodm.getValueAt(i, 4);
						Pusepr += (double) Pulodm.getValueAt(i, 7);
					}
					PuTosepr.setText("|" + Pusepr);
					PuTtpp.setText("|" + Putoppr);
					Punum.setText("������:" + sum);
					Puloto.setText("|" + Puto);
					PuTopr.setText("|" + Pupr);

				} else if (PurOrder.getText().equals("")) {
					List<PurchaseOrderEmpSup> lstu = dao.queryOrdTime();
					Object[][] rowtop = new Object[lstu.size()][];
					for (int i = 0; i < rowtop.length; i++) {
						// ��ȡ���϶���
						PurchaseOrderEmpSup purempsup = lstu.get(i);
						String purstatus = purempsup.getPur_status() == 0 ? "δ���"
								: (purempsup.getPur_status() == 1 ? "���ͨ��"
										: purempsup.getPur_status() == 2 ? "���δͨ��" : "ȡ������");
						// ����������תΪ����洢
						Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
								purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus, purempsup.getPur_mark() };
						// ����λ���鸳ֵ
						rowtop[i] = obj;
						Puupdm.addRow(obj);
						Pulodm.setRowCount(0);
					}
				}
			}
		});
		/**
		 * ʱ���ѯ
		 */
		Purqueb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!PurCho.getText().equals("")) {
					List<PurchaseOrderEmpSup> lstu = dao.queryOrdTime(Date.valueOf(PurCho.getText()));
					Object[][] rowtop = new Object[lstu.size()][];
					for (int i = 0; i < rowtop.length; i++) {
						// ��ȡ���϶���
						PurchaseOrderEmpSup purempsup = lstu.get(i);
						String purstatus = purempsup.getPur_status() == 0 ? "δ���"
								: (purempsup.getPur_status() == 1 ? "���ͨ��"
										: purempsup.getPur_status() == 2 ? "���δͨ��" : "ȡ������");
						// ����������תΪ����洢
						Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
								purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus, purempsup.getPur_mark() };
						// ����λ���鸳ֵ
						rowtop[i] = obj;
					}
					String[] upgood = { "���۶������", "��������", "�ͻ�����", "�տ���", "��������", "������", "��ע" };
					Puupdm.setDataVector(rowtop, upgood);
				}
			}
		});
	}

	public void SaClickevent() {
		/**
		 * �е��
		 */
		Saupjl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = Saupjl.getSelectedRow();// ��ȡ��
				if (e.getClickCount() == 1) {
					Said = Integer.parseInt(Saupjl.getValueAt(row, 0).toString());
					SaNumb.setText("ID:" + Said);
					List<RefunddetailsOrder> lststu = dao.SeeRefunddetailsOrderx(Said);
					Object[][] rowparticularsform = new Object[lststu.size()][];
					for (int i = 0; i < rowparticularsform.length; i++) {
						RefunddetailsOrder sb = lststu.get(i);
						String status = sb.getsDet_status() == 0 ? "������" : "���˻�";
						Object[] obj = { sb.getsDet_goodId(), sb.getGoods_name(), sb.getGoods_units(),
								sb.getGoods_sellPrice(), sb.getsDet_number(), sb.getsDet_goodPrice(),
								sb.getGoods_size(), status };
						rowparticularsform[i] = obj;
					}
					String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С", "����״̬" };
					Salodm.setDataVector(rowparticularsform, str);
					int Sanumber = Salodm.getRowCount();// �����±��������
					double Sato = 0.0;
					int Sapr = 0;
					double Sasepr = 0.0;
					for (int i = 0; i < Sanumber; i++) {
						Sato += (double) Salodm.getValueAt(i, 3);
						Sapr += (int) Salodm.getValueAt(i, 4);
						Sasepr += (double) Salodm.getValueAt(i, 5);
					}
					Sanum.setText("������:" + Sanumber);
					Saquto.setText("|" + Sato);
					SaTopr.setText("|" + Sapr);
					SaTosepr.setText("|" + Sasepr);
				}
			}
		});
		/**
		 * ʱ���ѯ
		 */
		Saqueb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!SaCho.getText().equals("")) {
					Date sell_date = Date.valueOf(SaCho.getText());
					List<RefundOrder> lstu = dao.stafftimex(sell_date);
					Object[][] rowtop = new Object[lstu.size()][];
					for (int i = 0; i < rowtop.length; i++) {
						// ��ȡ���϶���
						RefundOrder sb = lstu.get(i);
						// ����������תΪ����洢
						Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getCustomername(), sb.getSell_profit(),
								sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
						rowtop[i] = obj;
					}
					String[] upgood = { "���۶������", "��������", "�ͻ�����", "�տ���", "��������", "������", "��ע" };
					Saupdm.setDataVector(rowtop, upgood);
				}
			}
		});
		/**
		 * ������ѯ
		 */
		SaOrderqu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!SaOrder.getText().equals("")) {
					((DefaultTableModel) Salojl.getModel()).getDataVector().clear(); // ����������
					((DefaultTableModel) Saupjl.getModel()).getDataVector().clear(); // ����������
					int idname = Integer.parseInt(SaOrder.getText());
					List<RefundOrder> lstu = dao.To_find_thex(idname);
					Object[][] rowtop = new Object[lstu.size()][];
					for (int i = 0; i < rowtop.length; i++) {
						// ��ȡ���϶���
						RefundOrder sb = lstu.get(i);
						// ����������תΪ����洢
						Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getCustomername(), sb.getSell_profit(),
								sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
						rowtop[i] = obj;
					}
					String[] upgood = { "���۶������", "��������", "�ͻ�����", "�տ���", "��������", "������", "��ע" };
					Saupdm.setDataVector(rowtop, upgood);
				} else if(SaOrder.getText().equals("")){
					List<RefundOrder> lststu = dao.SeeRefundOrderx();
					Object[][] rowsellOrderform = new Object[lststu.size()][];
					for (int i = 0; i < rowsellOrderform.length; i++) {
						RefundOrder sb = lststu.get(i);
						Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getCustomername(), sb.getSell_profit(),
								sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
						rowsellOrderform[i] = obj;
						Saupdm.addRow(obj);
						Salodm.setRowCount(0);
					}
					
				}

			}
		});
	}

	public static void main(String[] args) {
		new Commodity_management();
	}
}
