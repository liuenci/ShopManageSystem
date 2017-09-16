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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.PurchasePlanDao1;
import cn.dao.purchase.StoTransDao1;
import cn.liuenci.swing.DateChooser;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.model.purchase.PurchasePlanDetGood;
import cn.view.purchase.MTable;

/**
 * 1.����2017-8-10 
 * 2.��Ҫ���� 
 *  a.ͨ�����ڲ�ѯ�ƻ�����
 *  b.ͨ��������ģ����ѯ�ƻ�����
 *   c.������Ӱ�ť�ɽ���Ʒ�������
 *  d.������Ӱ�ť��ѡ����Ʒ�������
 * 
 * @author �ܳ���
 *
 */
public class PurchasePlan extends JFrame {
	JSplitPane splittopbottom;// �������·ָ����
	private JTable tabletop;// �ϱ߱�����
	private DefaultTableModel tmtop;// �ϱߴ�����ģ��
	private JTable tablebottom;// �±߱�����
	private DefaultTableModel tmbottom;// �±ߴ�����ģ��
	DefaultTableModel tm;

	private JScrollPane spbottom;// �����±߹������
	private JScrollPane sptop;// �����ϱ߹������

	private JPanel ptop; // �ϱ����
	private JPanel pbottom; // �±����

	JTextField tfgoodid;// ��������
	JTextField tfgoodTime;// ʱ��
	JButton btnSelid;// ��Ų�ѯ��ť
	JButton btnSelTime;// ʱ���ѯ
	int plan_id;
	int purid;
	int goodid;
	int status;
	Date plan_date;

	JButton btnAll;
	JButton btnLittle;
	CommondMethods comMenth = null;

	public PurchasePlan(DefaultTableModel tms, JTable tables) {
		this.tablebottom = tables;
		this.tm = tms;
		comMenth = new CommondMethods();
		this.inist();
		this.setSplit();
		this.btn();
		// ���ڿ��ӻ�
		this.setVisible(true);
		splittopbottom.setDividerLocation(0.5);
		splittopbottom.setEnabled(false);
	}

	/**
	 * ��ʼ��ҳ��
	 */
	public void inist() {
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
		this.setTitle("�ɹ��ƻ�������ѯ");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * ���÷ָ����
	 */
	public void setSplit() {
		// �����ָ�������
		splittopbottom = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		// ����������巽��
		this.setTopPanel();
		this.setBottonPanel();
		splittopbottom.add(ptop, JSplitPane.TOP);
		splittopbottom.add(pbottom, JSplitPane.BOTTOM);
		// �ѷָ������ӵ�����
		this.add(splittopbottom);
	}

	// �����������
	public void setTopPanel() {
		// ����������
		ptop = comMenth.createPanel(0, 0, 815, 530);
		;
		// ����Ϊnull
		ptop.setLayout(null);

		JLabel lbtime = comMenth.createLabel("��ѯʱ��:", 25, 20, 100, 25, "����", 14);
		tfgoodTime = comMenth.createTextField("����ѡ��ʱ��", 100, 20, 160, 25, "����", 14, Color.white);
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(tfgoodTime);
		// �����Ų�ѯ��ť
		btnSelTime = comMenth.createButton("��ѯ", 280, 20, 60, 25, "����", 14);

		JLabel lbtopid = comMenth.createLabel("�����붩����:", 450, 20, 100, 25, "����", 14);
		tfgoodid = comMenth.createTextField("", 540, 20, 160, 25, "����", 14, Color.white);
		// �����Ų�ѯ��ť
		btnSelid = comMenth.createButton("��ѯ", 720, 20, 60, 25, "����", 14);
		// ������Ӱ�ť
		btnAll = comMenth.createButton("�������", 40, 60, 100, 25, "����", 14);
		// ������Ӱ�ť
		btnLittle = comMenth.createButton("�������", 210, 60, 100, 25, "����", 14);
		// ��ʾ����
		JLabel lbtabletop = comMenth.createLabel("�ƻ�������", 10, 85, 100, 25, "����", 14);
		lbtabletop.setFont(new Font("΢���ź�", Font.BOLD, 14));
		lbtabletop.setForeground(Color.RED);
		JLabel lbtopmainone = comMenth.createLabel("�ƻ���Ʒ������ӣ��ɵ��������Ӱ�ť��˫�������ƻ���", 400, 55, 400, 25, "����", 14);
		lbtopmainone.setFont(new Font("΢���ź�", Font.BOLD, 14));
		lbtopmainone.setForeground(Color.RED);
		JLabel lbtopmaintwo = comMenth.createLabel("�ƻ���Ʒ������ӣ��ɵ��������Ӱ�ť��˫�������ƻ������", 400, 85, 400, 25, "����", 14);
		lbtopmaintwo.setFont(new Font("΢���ź�", Font.BOLD, 14));
		lbtopmaintwo.setForeground(Color.RED);

		// �������
		String[] strtop = { "���ݺ�", "��������", "������", "��ע" };
		Object[][] rowtop = new Object[0][];

		tmtop = new DefaultTableModel(rowtop, strtop);
		tabletop = new JTable(tmtop);
		tabletop = new MTable(tmtop);
		sptop = new JScrollPane(tabletop);
		sptop.setBounds(10, 110, 780, 120);
		// ˫���������ѡ����Ʒ
		tabletop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int getrow = tabletop.getSelectedRow();
					if (getrow > -1) {
						Object[] objmessage = { "�����Ʒ", "����һ��" };
						// ѡ����ʾ���
						int res = JOptionPane.showOptionDialog(null, "�Ƿ����������Ʒ��", "������ʾ",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, objmessage,
								objmessage[0]);
						if (res == JOptionPane.YES_OPTION) {
							int col = tablebottom.getColumnCount();// ��ȡ��
							int row = tablebottom.getRowCount();// ��ȡ��
							Object[][] rowcol = new Object[row][col];
							for (int i = 0; i < row; i++) {
								// ��ȡ��Ʒ���
								int goodid = (int) tablebottom.getValueAt(i, 0);
								// ��ȡ��Ʒ����
								int goodnumber = (int) tablebottom.getValueAt(i, 5);
								// ��ȡ��Ʒ����
								double goodPrice = (double) tablebottom.getValueAt(i, 4);
								// ��̬�����洢��Ʒ�ܼ�
								PurchaseOrderView.purAllPrice += goodnumber * goodPrice;
								// ������ƷID��ȡ��Ʒ��Ϣ
								StoTransDao1 stod = new StoTransDao1();
								// ������Ʒ��Ϣ
								Good good = null;
								try {
									good = stod.get(goodid);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
										good.getGoods_size(), good.getGoods_purPrise(), goodnumber,
										good.getGoods_purPrise() * goodnumber, good.getGoods_sellPrice(),
										good.getGoods_stoId(), good.getGoods_keepDays(), good.getGoods_minNumber(),
										good.getGoods_mark() };
								rowcol[i] = obj;
								tm.addRow(obj);
							}
							PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
							PurchasePlan.this.dispose();
						} else {
							// ��������������
							JOptionPane.showMessageDialog(null, "��Ӳ�����ȡ����", "������ʾ", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						// ��������������
						JOptionPane.showMessageDialog(null, "��ѡ��Ҫ��ӵ���Ʒ��Ϣ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
					}
				} else if (e.getClickCount() == 1) {
					int row = tabletop.getSelectedRow();
					plan_id = (int) tabletop.getValueAt(row, 0);
					// �������
					String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��" };
					// ��ȡ���ݿ�����
					PurchasePlanDao1 goodpurd = new PurchasePlanDao1();
					// ��Ʒ��Ϣ
					List<PurchasePlanDetGood> lstugood = goodpurd.queryId(plan_id);
					// ��Ʒ��Ϣ��ά����
					Object[][] rows = new Object[lstugood.size()][];
					for (int i = 0; i < rows.length; i++) {
						// ��ȡ���϶���
						PurchasePlanDetGood goodpur = lstugood.get(i);
						// ����������תΪ����洢
						Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
								goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getPlanDet_number(),
								goodpur.getGoods_purPrise() * goodpur.getPlanDet_number(), goodpur.getGoods_sellPrice(),
								goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(), goodpur.getGoods_minNumber() };
						// ����λ���鸳ֵ
						rows[i] = obj;
					}
					tmbottom.setDataVector(rows, str);
				}
			}
		});

		ptop.add(btnAll);
		ptop.add(btnLittle);
		ptop.add(lbtopid);
		ptop.add(lbtime);
		ptop.add(lbtopmainone);
		ptop.add(lbtopmaintwo);
		ptop.add(lbtabletop);
		ptop.add(tfgoodTime);
		ptop.add(tfgoodid);
		ptop.add(btnSelid);
		ptop.add(btnSelTime);
		ptop.add(sptop);
		this.add(ptop);
	}

	// �����������
	public void setBottonPanel() {
		// ����������
		pbottom = new JPanel();
		// ����Ϊnull
		pbottom.setLayout(null);
		// ��ʾ����
		JLabel lbmain = new JLabel("����������ϸ��Ϣ��");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("΢���ź�", Font.BOLD, 12));
		lbmain.setForeground(Color.RED);

		// �������
		String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��" };
		Object[][] rows = new Object[0][];

		tmbottom = new DefaultTableModel(rows, str);
		tablebottom = new JTable(tmbottom);
		tablebottom = new MTable(tmbottom);
		spbottom = new JScrollPane(tablebottom);
		spbottom.setBounds(10, 30, 780, 200);
		// ˫���������ѡ����Ʒ
		tablebottom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					int getrow = tablebottom.getSelectedRow();
					if (getrow > -1) {
						Object[] objmessage = { "�����Ʒ", "����һ��" };
						// ѡ����ʾ���
						int res = JOptionPane.showOptionDialog(null, "�Ƿ���Ӹ���Ʒ��", "������ʾ",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, objmessage,
								objmessage[0]);
						if (res == JOptionPane.YES_OPTION) {
							// ��ȡ��Ʒ���
							int goodid = (int) tablebottom.getValueAt(getrow, 0);
							// ��ȡ��Ʒ����
							int goodnumber = (int) tablebottom.getValueAt(getrow, 5);
							// ��ȡ��Ʒ����
							double goodPrice = (double) tablebottom.getValueAt(getrow, 4);
							// ��̬�����洢��Ʒ�ܼ�
							PurchaseOrderView.purAllPrice += goodnumber * goodPrice;
							// ������ƷID��ȡ��Ʒ��Ϣ
							StoTransDao1 stod = new StoTransDao1();
							// ������Ʒ��Ϣ
							Good good = null;
							try {
								good = stod.get(goodid);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
									good.getGoods_size(), good.getGoods_purPrise(), goodnumber,
									good.getGoods_purPrise() * goodnumber, good.getGoods_sellPrice(),
									good.getGoods_stoId(), good.getGoods_keepDays(), good.getGoods_minNumber(),
									good.getGoods_mark() };
							tm.addRow(obj);
							PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
							PurchasePlan.this.dispose();
							tmbottom.removeRow(getrow);
						} else {
							// ��������������
							JOptionPane.showMessageDialog(null, "��Ӳ�����ȡ����", "������ʾ", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						// ��������������
						JOptionPane.showMessageDialog(null, "��ѡ��Ҫ��ӵ���Ʒ��Ϣ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		pbottom.add(lbmain);
		pbottom.add(spbottom);
		this.add(pbottom);
	}

	/**
	 * ��ť�¼�
	 */
	public void btn() {
		// ����ʱ���ѯ
		btnSelTime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ѯ�¼�
				// �������
				String[] strtop = { "���ݺ�", "��������", "������", "��ע" };
				// �������
				String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��" };
				if (!tfgoodTime.getText().equals("")) {
					try {
						plan_date = Date.valueOf(tfgoodTime.getText());
						// ��ȡ���ݿ�����
						PurchasePlanDao1 goodpurd = new PurchasePlanDao1();
						// ������Ϣ
						List<PurchasePlanDetGood> lstu = goodpurd.queryOrdTime(plan_date);
						// ������Ϣ��ά����
						Object[][] rowtop = new Object[lstu.size()][];
						for (int i = 0; i < rowtop.length; i++) {
							// ��ȡ���϶���
							PurchasePlanDetGood purplan = lstu.get(i);
							// ����������תΪ����洢
							Object[] obj = { purplan.getPlan_id(), purplan.getPlan_date(), purplan.getEmp_name(),
									purplan.getPlan_mark() };
							// ����λ���鸳ֵ
							rowtop[i] = obj;
						}
						tmtop.setDataVector(rowtop, strtop);
						// ��Ʒ��Ϣ
						List<PurchasePlanDetGood> lstugood = goodpurd.queryTime(plan_date);
						// ��Ʒ��Ϣ��ά����
						Object[][] rows = new Object[lstugood.size()][];
						for (int j = 0; j < rows.length; j++) {
							// ��ȡ���϶���
							PurchasePlanDetGood goodpur = lstugood.get(j);
							// ����������תΪ����洢
							Object[] objgood = { goodpur.getGoods_id(), goodpur.getGoods_name(),
									goodpur.getGoods_units(), goodpur.getGoods_size(), goodpur.getGoods_purPrise(),
									goodpur.getPlanDet_number(),
									goodpur.getGoods_purPrise() * goodpur.getPlanDet_number(),
									goodpur.getGoods_sellPrice(), goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(),
									goodpur.getGoods_minNumber() };
							// ����λ���鸳ֵ
							rows[j] = objgood;
						}
						tmbottom.setDataVector(rows, str);

					} catch (IllegalArgumentException timeerror) {
						JOptionPane.showMessageDialog(null, "��������ȷ��ʱ��", "������ʾ", JOptionPane.WARNING_MESSAGE);
						tfgoodTime.setText("");
					}
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ���ѯ��ʱ�䣡", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ��Ų�ѯ��ť
		btnSelid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ѯ�¼�
				// �������
				String[] strtop = { "���ݺ�", "��������", "������", "��ע" };
				// �������
				String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��" };
				if (!tfgoodid.getText().equals("")) {
					try {
						plan_id = Integer.parseInt(tfgoodid.getText());
						// ��ȡ���ݿ�����
						PurchasePlanDao1 goodpurd = new PurchasePlanDao1();
						// ������Ϣ,ģ����ѯ����
						List<PurchasePlanDetGood> lstu = goodpurd.queryOrdId(plan_id);
						// ������Ϣ��ά����
						Object[][] rowtop = new Object[lstu.size()][];
						for (int i = 0; i < rowtop.length; i++) {
							// ��ȡ���϶���
							PurchasePlanDetGood purplan = lstu.get(i);
							// ����������תΪ����洢
							Object[] obj = { purplan.getPlan_id(), purplan.getPlan_date(), purplan.getEmp_name(),
									purplan.getPlan_mark() };
							// ����λ���鸳ֵ
							rowtop[i] = obj;
						}
						tmtop.setDataVector(rowtop, strtop);
						// ��Ʒ��Ϣ
						List<PurchasePlanDetGood> lstugood = goodpurd.queryId(plan_id);
						// ��Ʒ��Ϣ��ά����
						Object[][] rows = new Object[lstugood.size()][];
						for (int i = 0; i < rows.length; i++) {
							// ��ȡ���϶���
							PurchasePlanDetGood goodpur = lstugood.get(i);
							// ����������תΪ����洢
							Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
									goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getPlanDet_number(),
									goodpur.getGoods_purPrise() * goodpur.getPlanDet_number(),
									goodpur.getGoods_sellPrice(), goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(),
									goodpur.getGoods_minNumber() };
							// ����λ���鸳ֵ
							rows[i] = obj;
						}
						tmbottom.setDataVector(rows, str);
					} catch (NumberFormatException iderror) {
						JOptionPane.showMessageDialog(null, "��������ȷ�ı��", "������ʾ", JOptionPane.WARNING_MESSAGE);
						tfgoodid.setText("");
					}
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ���ѯ�ı�ţ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ������Ӱ�ť
		btnAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int getrow = tabletop.getSelectedRow();
				if (getrow > -1) {
					int col = tablebottom.getColumnCount();// ��ȡ��
					int row = tablebottom.getRowCount();// ��ȡ��
					Object[][] rowcol = new Object[row][col];
					for (int i = 0; i < row; i++) {
						// ��ȡ��Ʒ���
						int goodid = (int) tablebottom.getValueAt(i, 0);
						// ��ȡ��Ʒ����
						int goodnumber = (int) tablebottom.getValueAt(i, 5);
						// ��ȡ��Ʒ����
						double goodPrice = (double) tablebottom.getValueAt(i, 4);
						// ��̬�����洢��Ʒ�ܼ�
						PurchaseOrderView.purAllPrice += goodnumber * goodPrice;
						// ������ƷID��ȡ��Ʒ��Ϣ
						StoTransDao1 stod = new StoTransDao1();
						// ������Ʒ��Ϣ
						Good good = null;
						try {
							good = stod.get(goodid);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
								good.getGoods_size(), good.getGoods_purPrise(), goodnumber,
								good.getGoods_purPrise() * goodnumber, good.getGoods_sellPrice(), good.getGoods_stoId(),
								good.getGoods_keepDays(), good.getGoods_minNumber(), good.getGoods_mark() };
						rowcol[i] = obj;
						tm.addRow(obj);
					}
					PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
					PurchasePlan.this.dispose();
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ��ӵ���Ʒ��Ϣ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// �������
		btnLittle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int getrow = tablebottom.getSelectedRow();
				if (getrow > -1) {
					// ��ȡ��Ʒ���
					int goodid = (int) tablebottom.getValueAt(getrow, 0);
					// ��ȡ��Ʒ����
					int goodnumber = (int) tablebottom.getValueAt(getrow, 5);
					// ��ȡ��Ʒ����
					double goodPrice = (double) tablebottom.getValueAt(getrow, 4);
					// ��̬�����洢��Ʒ�ܼ�
					PurchaseOrderView.purAllPrice += goodnumber * goodPrice;
					// ������ƷID��ȡ��Ʒ��Ϣ
					StoTransDao1 stod = new StoTransDao1();
					// ������Ʒ��Ϣ
					Good good = null;
					try {
						good = stod.get(goodid);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
							good.getGoods_size(), good.getGoods_purPrise(), goodnumber,
							good.getGoods_purPrise() * goodnumber, good.getGoods_sellPrice(), good.getGoods_stoId(),
							good.getGoods_keepDays(), good.getGoods_minNumber(), good.getGoods_mark() };
					tm.addRow(obj);
					PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
					PurchasePlan.this.dispose();
					tmbottom.removeRow(getrow);
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ��ӵ���Ʒ��Ϣ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public static void main(String[] args) {
		// new PurchasePlan();
	}
}
