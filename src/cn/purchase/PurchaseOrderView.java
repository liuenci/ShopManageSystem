package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.EmployeeDao1;
import cn.dao.purchase.PurchaseDao1;
import cn.dao.purchase.PurchaseOrderDao1;
import cn.dao.purchase.StoTransDao1;
import cn.dao.purchase.SupplyDao1;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.model.common.PurDetail;
import cn.model.common.PurchaseOrder;
import cn.model.common.Supply;
import cn.model.purchase.PurchaseEmpSup;
import cn.view.purchase.MTable;


/**
 * 1.���ڣ�2017-8-03 
 * 2.��Ҫ���� 
 * 	a.�ڲ���ʵ�ֶ�̬������ѡ�� 
 * 	b.����Ʒ���ҳ����ת 
 * 	c.����Ʒ���ҳ����ת 
 * 	d.�ƻ���Ʒ���ҳ����ת
 * 	e.�޸���Ʒ���Ա���е���Ʒ���������޸� 
 * 	f.ɾ����Ʒ���Ա���е���Ʒ����ɾ��
 * 	g.��̬����ѡ����Ʒ���������ۼ���Ӧ�����
 * 	h.��дʵ������ʵ���������쳣���� 
 * 	i.��̬��ȡ���ݿ�ɹ�Ա������
 * 	j.�����Ĳɹ�����Ĭ��Ϊδ���״̬ 
 * 	k.����д��ע����
 * 	l.���ȷ�����Ĳɹ������������ɹ���������� 
 * 	m.���ȡ��ɾ���ɹ����� 
 * 	n.�쳣���񣬶��û��ɸ��ĵĲ��ֽ��д��������ʾ������
 * 
 * @author �ܳ���
 */
public class PurchaseOrderView extends JFrame {
	CommondMethods commondMethods = new CommondMethods();
	DefaultTableModel tablemodel;// ������ģ��
	private JPanel jpbtn;// ��ť���
	private JScrollPane spmains;// �������
	private DefaultTableModel tms;// ������ģ��
	private JTable tables;// ������
	private JButton btnOgoods;// ����Ʒ��Ӱ�ť
	private JButton btnNgoods;// ����Ʒ��Ӱ�ť
	private JButton btnPgoods;// �ƻ���Ʒ��Ӱ�ť
	private JButton btnDet;// ɾ����ť
	private JButton btnUpdate;// �޸���Ʒ
	private JButton btnSure;// ȷ����ť
	private JButton btnDel;// ȡ����ť
	private JButton btnsup;// ��Ӧ�̲���
	private JButton btnemp;// Ա������
	JTextField tfsupply = null;
	String supplyName = "";
	static double purAllPrice = 0;

	int goods_id;// ��Ʒ���
	String goods_name;// ��Ʒ����
	String goods_units;// ��Ʒ��λ
	String goods_size;// ��Ʒ����С
	double goods_purPrise;// ��Ʒ����
	double goods_sellPrice;// ��Ʒ�ۼ�
	int goods_number;// ��Ʒ����
	int goods_stoId;// �ֿ���
	int goods_keepDays;// ������
	int goods_minNumber;// ��Ϳ��
	String goods_mark;// ��ע

	int row = 0;

	int pur_id;// �������
	String pur_supplyName;// �����̱�ţ����
	Date pur_date;// �ɹ�����
	double pur_pay;// ֧���ܽ��
	String pur_empName;// Ա����ţ����
	int pur_status;// �Ƿ���ˣ�0��δ���1�������ͨ�� 2�����δͨ���˻زɹ�Ա��
	String pur_mark;// ��ע
	String stata;
	JLabel lapurid;// ����id
	String newday;// ��������
	static JTextField tfallmoney;// �ܽ��
	JTextField tfRealMoney;// ʵ�����
	JComboBox empList;// ������
	JLabel lbstatus;// ״̬
	JTextField ftmark;// ��ע
	CommondMethods comMenth = null;

	int pDet_id;// �ɹ����������
	int pDet_purId;// �ɹ��������ţ����
	int pDet_goodId;// ��Ʒ���
	int pDet_number;// �ɹ�����
	double pDet_goodPrice;// ÿ����Ʒ�Ľ����ܼ۸�
	int pDet_status;// �ɹ�״̬��0�����1��δ���2��
	String pDet_mark;// ��ע

	/**
	 * ���췽��
	 * 
	 * @param tmright
	 * @param tableright
	 */
	public PurchaseOrderView() {
		comMenth = new CommondMethods();
		this.purinit();
		this.init();
		this.addpanel();
		this.btn();
		// ���ڿ��ӻ�
		this.setVisible(true);
	}

	/**
	 * ��ʼ��ҳ��
	 */
	public void init() {
		// ���ô��ڴ�С
		this.setSize(815, 530);
		// ���ô��ھ���
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		// �����޸Ĵ��ڵĴ�С
		this.setResizable(false);
		this.setTitle("�ɹ��������");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * ��ȡ�½���������Ϣ
	 */
	public void purinit() {
		// �������
		String[] strpur = { "�ɹ�������", "�����̱��", "�ɹ�����", "֧���ܽ��", "Ա�����", "�Ƿ����", "��ע" };
		// ��ȡ���ݿ�����
		PurchaseOrderDao1 purd = new PurchaseOrderDao1();
		List<PurchaseEmpSup> lsup = purd.getPurchaseOrderEmpSup();
		Object[][] rows = new Object[lsup.size()][];
		for (int i = 0; i < rows.length; i++) {
			// ��ȡ��Ӧ�̼��϶���
			PurchaseEmpSup pur = lsup.get(i);
			stata = pur.getSup_status() == 0 ? "δ���" : (pur.getSup_status() == 1 ? "�����ͨ��" : "���δͨ��");

			// ������תΪ����洢
			Object[] obj = { pur.getPur_id(), pur.getSup_name(), pur.getPur_date(), pur.getPur_pay(), pur.getEmp_name(),
					stata, pur.getPur_mark() };
			// ����ά���鸳ֵ
			rows[i] = obj;
		}
		tablemodel = new DefaultTableModel(rows, strpur);

		// ��ȡ�ɹ�������ʼֵ
		pur_id = Integer.parseInt(tablemodel.getValueAt(row, 0).toString());
		pur_supplyName = tablemodel.getValueAt(row, 1).toString();
		pur_date = Date.valueOf(tablemodel.getValueAt(row, 2).toString());
		pur_pay = Double.parseDouble(tablemodel.getValueAt(row, 3).toString());
		pur_empName = tablemodel.getValueAt(row, 4).toString();
		stata = tablemodel.getValueAt(row, 5).toString();
		pur_mark = tablemodel.getValueAt(row, 6).toString();
	}

	/**
	 * �������
	 */
	public void addpanel() {
		// �������ݿ�
		StoTransDao1 std = new StoTransDao1();
		// ��ȡ�����Ʒid
		final int maxid = std.getMaxId();

		// ���������
		jpbtn = comMenth.createPanel(280, 85, 815, 530);
		// ���������
		JPanel jptop = comMenth.createPanel(20, 50, 770, 40);
		JPanel jpmodel = comMenth.createPanel(20, 100, 770, 45);
		JPanel jpbottom = comMenth.createPanel(20, 400, 770, 80);
		// �޲�������
		jpbtn.setLayout(null);
		jptop.setLayout(null);
		jpmodel.setLayout(null);
		jpbottom.setLayout(null);
		jpbtn.add(jptop);
		jpbtn.add(jpmodel);
		jpbtn.add(jpbottom);

		JLabel latit = comMenth.createLabel("�ɹ�����", 340, 10, 180, 40, "����", 30);
		latit.setFont(new Font("����", Font.BOLD, 30));
		JLabel laorder = comMenth.createLabel("�����ţ�", 540, 30, 80, 20, "����", 14);
		laorder.setFont(new Font("����", Font.BOLD, 14));
		laorder.setForeground(Color.RED);
		lapurid = comMenth.createLabel("" + pur_id, 600, 30, 80, 20, "����", 14);
		lapurid.setFont(new Font("����", Font.BOLD, 14));
		lapurid.setForeground(Color.RED);

		// ���������̱�ǩ
		JLabel lbsupply = comMenth.createLabel("������:", 40, 10, 80, 20, "����", 14);
		lbsupply.setFont(new Font("����", Font.PLAIN, 14));
		
		// �����ı���
		tfsupply = comMenth.createTextField(pur_supplyName, 100, 10, 120, 20, "����", 14, new Color(237, 242, 248));
		MatteBorder border = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfsupply.setBorder(border);
		tfsupply.setEditable(false);

		btnsup = comMenth.createButton("����", 230, 7, 60, 25, "����", 14);

		jptop.add(lbsupply);
		jptop.add(tfsupply);
		jptop.add(btnsup);

		// ������ֿ�ʱ���ǩ
		JLabel lbtime = comMenth.createLabel("��������:", 520, 10, 80, 25, "����", 14);
		lbtime.setFont(new Font("����", Font.PLAIN, 14));
		
		// �����ı���
		DateFormat dfTime = new SimpleDateFormat("yyyy-MM-dd");
		newday = dfTime.format(new java.util.Date());
		JLabel lbTime = comMenth.createLabel(newday, 590, 10, 100, 25, "����", 14);

		jptop.add(lbtime);
		jptop.add(lbTime);

		// ����Ʒ��ť
		btnOgoods = comMenth.createButton("����Ʒ���", 40, 7, 100, 25, "����", 14);
		// ��������Ʒ��ť
		btnNgoods = comMenth.createButton("����Ʒ���", 160, 7, 100, 25, "����", 14);
		// �ƻ���Ʒ��ť
		btnPgoods = comMenth.createButton("�ƻ���Ʒ���", 280, 7, 115, 25, "����", 14);
		// ��Ʒɾ��
		btnDet = comMenth.createButton("ɾ����Ʒ", 520, 7, 90, 25, "����", 14);
		// ��Ʒ�޸�
		btnUpdate = comMenth.createButton("�޸���Ʒ", 640, 7, 90, 25, "����", 14);
		// ȷ����ť
		btnSure = comMenth.createButton("ȷ��", 520, 40, 60, 25, "����", 14);
		// ȡ����ť
		btnDel = comMenth.createButton("ȡ��", 640, 40, 60, 25, "����", 14);

		jpmodel.add(btnOgoods);
		jpmodel.add(btnNgoods);
		jpmodel.add(btnPgoods);
		jpmodel.add(btnDet);
		jpmodel.add(btnUpdate);

		// �������
		String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��", "��ע" };
		tms = new DefaultTableModel(null, str);
		tables = new JTable(tms);
		tables = new MTable(tms);

		spmains = new JScrollPane(tables);
		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(155,193,242);
			}

		}
		commondMethods.setColor(tables, color);
		spmains.setBounds(20, 150, 770, 220);

		JLabel lballmoney = comMenth.createLabel("Ӧ�����:", 20, 10, 80, 30, "����", 14);
		lballmoney.setFont(new Font("����", Font.PLAIN, 14));
		tfallmoney = comMenth.createTextField("" + pur_pay, 85, 10, 60, 25, "����", 14, new Color(237, 242, 248));
		tfallmoney.setEditable(false);
		MatteBorder border2 = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfallmoney.setBorder(border2);

		JLabel lbRealMoney = comMenth.createLabel("ʵ�����:", 160, 10, 80, 30, "����", 14);
		lbRealMoney.setFont(new Font("����", Font.PLAIN, 14));
		tfRealMoney = comMenth.createTextField("", 230, 10, 60, 25, "����", 14, new Color(237, 242, 248));
		MatteBorder borderReal = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfRealMoney.setBorder(borderReal);

		JLabel lbpeople = comMenth.createLabel("�����ˣ�", 300, 10, 80, 25, "����", 14);
		lbpeople.setFont(new Font("����", Font.PLAIN, 14));
		// �����������б��
		empList = comMenth.createJComboBox(350, 10, 80, 25, "����", 14);
		// �����ݿ��ȡԱ����
		EmployeeDao1 empDao = new EmployeeDao1();
		List<cn.model.common.Employee> employeeList = empDao.query(1);
		for (int i = 0; i < employeeList.size(); i++) {
			empList.addItem(employeeList.get(i).getEmp_name());
		}
		jpbottom.add(empList);

		JLabel lbsure = comMenth.createLabel("���:", 520, 10, 60, 25, "����", 14);
		lbsure.setFont(new Font("����", Font.PLAIN, 14));
		lbstatus = comMenth.createLabel(stata, 570, 10, 80, 25, "����", 14);
		JLabel lbmark = comMenth.createLabel("��ע��", 20, 40, 60, 25, "����", 14);
		lbmark.setFont(new Font("����", Font.PLAIN, 14));
		ftmark = comMenth.createTextField(pur_mark, 60, 40, 370, 25, "����", 14, new Color(237, 242, 248));
		MatteBorder border5 = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		ftmark.setBorder(border5);

		// ��ӵ����
		jpbtn.add(spmains);
		jpbtn.add(laorder);
		jpbtn.add(latit);
		jpbtn.add(lapurid);

		jpbottom.add(lbsure);
		jpbottom.add(lbstatus);
		jpbottom.add(lbmark);
		jpbottom.add(ftmark);
		jpbottom.add(lbpeople);
		jpbottom.add(lbRealMoney);
		jpbottom.add(tfRealMoney);
		jpbottom.add(lballmoney);
		jpbottom.add(tfallmoney);
		jpbottom.add(btnSure);
		jpbottom.add(btnDel);

		// ��ӽ�����
		this.add(jpbtn);
	}

	/**
	 * ��ť����¼�
	 */
	public void btn() {
		// �������ݿ�
		StoTransDao1 std = new StoTransDao1();
		// ��ȡ�����Ʒid
		final int maxid = std.getMaxId();
		// ����Ʒ����¼�
		btnOgoods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����Ʒ����¼�
				new PurchaseOldAdd(tables, tms);
			}
		});
		// ����Ʒ����¼�
		btnNgoods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����Ʒ����¼�
				new PurchaseOrdNew(tms, tables);
			}
		});
		// �ƻ���Ʒ�����Ʒ
		btnPgoods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �ƻ�����Ʒ����¼�
				new PurchasePlan(tms, tables);
			}
		});
		// ��Ʒɾ����ť
		btnDet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��Ʒɾ���¼�
				// ɾ����Ӧ����Ϣ
				int rows = tables.getSelectedRow();
				if (rows != -1) {
					int goodId = Integer.parseInt(tms.getValueAt(rows, 0).toString());
					Object[] obj = { "����ɾ��", "��������" };
					// ѡ����ʾ���
					int res = JOptionPane.showOptionDialog(null, "�Ƿ�ȷ��ɾ����", "ɾ��������ʾ", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					if (res == JOptionPane.YES_OPTION) {
						if (goodId > maxid) {
							StoTransDao1 std = new StoTransDao1();
							Good good = new Good();
							good.setGoods_id(goodId);
							std.delGood(good);
							// ɾ���ɹ���ʾ���
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
							tms.removeRow(rows);
						} else {
							// ɾ���ɹ���ʾ���
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
							tms.removeRow(rows);
						}
					} else {
						// ����ʧ�ܾ���
						JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�", "ɾ��������ʾ", JOptionPane.WARNING_MESSAGE);
					}
					// ��ȡ�������
					int maxRows = tables.getRowCount();
					// ��Ʒ���ܼ�����Ϊ0
					purAllPrice = 0;
					// ����
					for (int i = 0; i < maxRows; i++) {
						// �õ�ÿһ�е��ܽ��
						double perGoodPrice = (double) tables.getValueAt(i, 6);
						purAllPrice += perGoodPrice;
					}
					tfallmoney.setText("" + purAllPrice);
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������Ʒ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ��Ʒ�޸İ�ť
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rows = tables.getSelectedRow();
				if (rows != -1) {
					// ��Ʒ�޸��¼�
					new PurchaseOrUpdate(tms, tables);
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���Ʒ��Ϣ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ȷ����ť
		btnSure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���²ɹ�������,���ɲɹ��굥��
				// ��ȡ�ܹ�������
				int allrow = tables.getRowCount();
				if (allrow == 0) {
					// ����ʧ�ܾ���
					JOptionPane.showMessageDialog(null, "��ѡ����Ʒ����ɹ�������", "������ʾ", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						pur_id = Integer.parseInt(lapurid.getText());
						if (tfsupply.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "��Ӧ�̲���Ϊ��", "������ʾ", JOptionPane.WARNING_MESSAGE);
						} else {
								pur_supplyName = tfsupply.getText();
						}
						pur_date = Date.valueOf(newday);
						pur_pay = Double.parseDouble(tfallmoney.getText());
						double pur_RealMoney = Double.parseDouble(tfRealMoney.getText());
						// �ж�ʵ�����
						if (pur_pay > pur_RealMoney) {
							JOptionPane.showMessageDialog(null, "ʵ������С��Ӧ�����", "������ʾ", JOptionPane.WARNING_MESSAGE);
							tfRealMoney.setText("");
						} else if (pur_RealMoney <= 0) {
							JOptionPane.showMessageDialog(null, "ʵ������С����", "������ʾ", JOptionPane.WARNING_MESSAGE);
							tfRealMoney.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "����" + (pur_RealMoney - pur_pay) + "Ԫ");
							pur_empName = empList.getSelectedItem().toString();
							int statas = 0;
							pur_mark = ftmark.getText();
							SupplyDao1 sud = new SupplyDao1();
							int sup_id = sud.getSupplyname(pur_supplyName);
							EmployeeDao1 empd = new EmployeeDao1();
							int emp_id = empd.getEmployeename(pur_empName);
							// �������ݿ�
							PurchaseOrderDao1 purd = new PurchaseOrderDao1();
							// �½���Ӧ�̶���
							PurchaseOrder pur = new PurchaseOrder(sup_id, pur_date, pur_pay, emp_id, statas, pur_mark);
							pur.setPur_id(pur_id);
							purd.updatePurchaseOrder(pur);
							// �����굥��
							for (int i = 0; i < allrow; i++) {
								pDet_purId = Integer.parseInt(lapurid.getText());// �ɹ��������
								pDet_goodId = Integer.parseInt(tms.getValueAt(i, 0).toString());// ��Ʒ���
								pDet_number = Integer.parseInt(tms.getValueAt(i, 5).toString());// �ɹ�����
								pDet_goodPrice = Double.parseDouble(tms.getValueAt(i, 6).toString());// �ɹ���Ʒ�ܽ��
								pDet_status = 1;// �ɹ�״̬���Ƿ����
								pDet_mark = tms.getValueAt(i, 11).toString();
								PurDetail purdet = new PurDetail(pDet_purId, pDet_goodId, pDet_number, pDet_goodPrice,
										pDet_status, pDet_mark);
								purdet.setpDet_id(pDet_id);
								purd.addPurDetail(purdet);
							}
							JOptionPane.showMessageDialog(null, "�ɹ��ɹ�");
							PurchaseOrderView.this.dispose();
							purAllPrice = 0;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ��ʵ�����", "������ʾ", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		// ȡ����ť�¼�
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ȡ����ť�¼�
				int statas = 0;
				SupplyDao1 sud = new SupplyDao1();
				int sup_id = sud.getSupplyname(pur_supplyName);
				EmployeeDao1 empd = new EmployeeDao1();
				int emp_id = empd.getEmployeename(pur_empName);
				// �������ݿ�
				PurchaseOrderDao1 purd = new PurchaseOrderDao1();
				// �½���Ӧ�̶���
				PurchaseOrder pur = new PurchaseOrder(sup_id, pur_date, pur_pay, emp_id, statas, pur_mark);
				pur.setPur_id(pur_id);
				purd.delPurchaseOrder(pur);
				PurchaseOrderView.this.dispose();
			}
		});
		// ��Ӧ��ȫ����ѯ
		btnsup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SupplyFrame();
			}
		});
	}

	/**
	 * ��Ӧ����ʾ��ѡ��� �ڲ���
	 * 
	 * @author �ܳ���
	 *
	 */
	class SupplyFrame extends JFrame {
		private JScrollPane spmain;// �������
		private DefaultTableModel tm;// ������ģ��
		private JTable table;// ������

		/**
		 * ���췽��
		 */
		public SupplyFrame() {
			this.init();
			this.addpanel();
			setUndecorated(true);
			// ���ڿ��ӻ�
			this.setVisible(true);
		}

		/**
		 * ��ʼ��ҳ��
		 */
		public void init() {
			// ���ô��ڴ�С
			this.setSize(400, 120);
			// ���ô�����ʾλ��
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenWidth = screenSize.width;
			int screenHeight = screenSize.height;
			int windowWidth = this.getWidth();
			int windowHeight = this.getHeight();
			int x = (screenWidth - windowWidth) / 2;
			int y = (screenHeight - windowHeight) / 2;
			setLocation(x, y);
			// �����޸Ĵ��ڵĴ�С
			this.setResizable(false);
			this.setTitle("�����̲�ѯ");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		/**
		 * �������
		 */
		public void addpanel() {
			// �������
			String[] str = { "��˾����", "��ϵ��", "��ϵ�绰", "����״̬" };
			// ��ȡ���ݿ�����
			PurchaseDao1 supd = new PurchaseDao1();
			List<Supply> lsup = supd.getSupply();
			Object[][] rows = new Object[lsup.size()][];
			for (int i = 0; i < rows.length; i++) {
				// ��ȡ��Ӧ�̼��϶���
				Supply sup = lsup.get(i);
				String status = sup.getSup_status() == 0 ? "���ֺ���" : "�����Լ";
				// ������תΪ����洢
				Object[] obj = { sup.getSup_name(), sup.getSup_linkMan(), sup.getSup_phone(), status };
				// ����ά���鸳ֵ
				rows[i] = obj;
			}
			tm = new DefaultTableModel(rows, str);
			table = new JTable(tm);
			table = new MTable(rows, str);
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						int row = table.getSelectedRow();
						int column = table.getAutoResizeMode();
						table.getValueAt(row, column);
						supplyName = tm.getValueAt(row, 0).toString();
						tfsupply.setText(supplyName);
						SupplyFrame.this.dispose();
					}
				}
			});
			spmain = new JScrollPane(table);
			spmain.setBounds(0, 0, 400, 330);
			// ��ӽ�����
			this.add(spmain);
		}
	}

	public static void main(String[] args) {
		new PurchaseOrderView();
	}
}
