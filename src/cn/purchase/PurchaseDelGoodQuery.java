package cn.purchase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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

import cn.dao.purchase.PurchaseOrderDetailGoodDao1;
import cn.liuenci.swing.DateChooser;
import cn.method.CommondMethods;
import cn.model.purchase.PurchaseOrderDetailGood;
import cn.model.purchase.PurchaseOrderEmpSup;
import cn.view.purchase.MTable;
/**
 * 1.����2017-8-19
 * 2.��Ҫ����
 *  a.�ɸ����������˻���ѯ
 *  b.�ɸ��ݶ����Ŷ����˻���ѯ
 * @author �ܳ���
 *
 */
public class PurchaseDelGoodQuery extends JPanel {
	CommondMethods commondMethods = new CommondMethods();
	JSplitPane splittopbottom;// �������·ָ����
	private JTable tabletop;// �ϱ߱�����
	private DefaultTableModel tmtop;// �ϱߴ�����ģ��
	private JTable tablebottom;// �±߱�����
	private DefaultTableModel tmbottom;// �±ߴ�����ģ��

	private JScrollPane spbottom;// �����±߹������
	private JScrollPane sptop;// �����ϱ߹������

	private JPanel ptop; // �ϱ����
	private JPanel pbottom; // �±����

	JTextField tfgoodid;// ��������
	JTextField tfgoodTime;// ʱ��
	JButton btnSelid;// ��Ų�ѯ��ť
	JButton btnSelTime;// ʱ���ѯ
	int pur_id;
	int purid;
	int goodid;
	int status;
	Date pur_date;
	CommondMethods comMenth = null;

	public PurchaseDelGoodQuery(JSplitPane splittopQuery) {
		this.splittopbottom = splittopQuery;
		this.setLayout(new BorderLayout());
		comMenth = new CommondMethods();
		this.inist();
		this.setSplit();
		this.btn();
		// ���ڿ��ӻ�
		this.setVisible(true);
	}

	/**
	 * ��ʼ��ҳ��
	 */
	public void inist() {
		// ���ô��ڴ�С
		this.setSize(815, 530);
		// ���ô�����ʾλ��
		this.setLocation(280, 85);
	}

	/**
	 * ���÷ָ����
	 */
	public void setSplit() {
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
		ptop = comMenth.createPanel(0, 0, 415, 280);
		// ����Ϊnull
		ptop.setLayout(null);

		JLabel lbTitle = comMenth.createLabel("�˻���ѯ", 340, 10, 180, 40, "����", 30);
		JLabel lbtime = comMenth.createLabel("��ѯʱ��:",25, 90, 100, 25, "����", 14);
		tfgoodTime = comMenth.createTextField("����ѡ��ʱ��", 100, 90, 160, 25, "����", 14, Color.WHITE);
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(tfgoodTime);
		// ʱ���ѯ��ť
		btnSelTime = comMenth.createButton("��ѯ", 280, 90, 60, 25,"����", 14);
		JLabel lbtopid = comMenth.createLabel("�����붩����:", 440, 90, 100, 25,"����", 14);
		tfgoodid = comMenth.createTextField("", 540, 90, 160, 25,  "����", 14, Color.WHITE);
		// �����Ų�ѯ��ť
		btnSelid = comMenth.createButton("��ѯ", 720, 90, 60, 25, "����", 14);
		// �������
		String[] strtop = { "���ݺ�", "����������", "��������", "֧���ܽ��", "������", "�ɹ�״̬", "��ע" };
		Object[][] rowtop = new Object[0][];

		tmtop = new DefaultTableModel(rowtop, strtop);
		tabletop = new JTable(tmtop);
		tabletop = new MTable(tmtop);
		sptop = new JScrollPane(tabletop);
		sptop.setBounds(10, 130, 780, 100);
		// ˫���������ѡ����Ʒ
		tabletop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// ˫��ȫ���˻�
				if (e.getClickCount() == 1) {
					int row = tabletop.getSelectedRow();
					int column = tabletop.getAutoResizeMode();
					tabletop.getValueAt(row, column);
					purid = (int) tabletop.getValueAt(row, 0);
					PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
					if (row != -1) {
						// �������
						String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��",
								"����״̬" };
						// ��ȡ���ݿ�����
						// ��Ʒ��Ϣ
						List<PurchaseOrderDetailGood> lstugood = goodpurd.queryDelGoodId(purid);
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
									goodpur.getGoods_purPrise() * goodpur.getpDet_number(),
									goodpur.getGoods_sellPrice(), goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(),
									goodpur.getGoods_minNumber(), status };
							rows[i] = obj;
						}
						tmbottom.setDataVector(rows, str);
						Color[] color = new Color[100];
						for (int i = 0; i < 100; i++) {
							int num = i % 2;
							if (num == 0) {
								color[i] = Color.WHITE;
							} else {
								color[i] = new Color(155,193,242);
							}
						}
						commondMethods.setColor(tablebottom, color);
					}
				}
			}
		});

		ptop.add(lbTitle);
		ptop.add(lbtopid);
		ptop.add(lbtime);
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
		pbottom = comMenth.createPanel(0, 0, 415, 280);
		// ����Ϊnull
		pbottom.setLayout(null);
		// ��ʾ����
		JLabel lbmain = comMenth.createLabel("����������ϸ��Ϣ��", 5, 0, 160, 25, "����", 14);
		lbmain.setFont(new Font("΢���ź�", Font.BOLD, 14));
		lbmain.setForeground(Color.RED);
		// �������
		String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��", "����״̬" };
		Object[][] rows = new Object[0][];

		tmbottom = new DefaultTableModel(rows, str);
		tablebottom = new JTable(tmbottom);
		tablebottom = new MTable(tmbottom);
		spbottom = new JScrollPane(tablebottom);
		spbottom.setBounds(10, 30, 780, 180);

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
				// �����Ʒ����������ԭ��Ϣ
				tmbottom.setNumRows(0);
				// ��ѯ�¼�
				// �������
				String[] strtop = { "���ݺ�", "����������", "��������", "֧���ܽ��", "������", "����״̬", "��ע" };
				// �������
				String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��", "����״̬" };
				if (!tfgoodTime.getText().equals("") || !tfgoodTime.getText().equals("����ѡ��ʱ��")) {
					try {
						if (tfgoodTime.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "��������Ҫ��ѯ�Ķ������", "������ʾ", JOptionPane.WARNING_MESSAGE);
							tfgoodTime.setText("");
						} else {
							pur_date = Date.valueOf(tfgoodTime.getText());
							// ��ȡ���ݿ�����
							PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
							// ������Ϣ
							List<PurchaseOrderEmpSup> lstu = goodpurd.queryDelPurTime(pur_date);
							// ������Ϣ��ά����
							Object[][] rowtop = new Object[lstu.size()][];
							for (int i = 0; i < rowtop.length; i++) {
								// ��ȡ���϶���
								PurchaseOrderEmpSup purempsup = lstu.get(i);
								String purstatus = purempsup.getPur_status() == 0 ? "δ���"
										: (purempsup.getPur_status() == 1 ? "���ͨ��"
												: (purempsup.getPur_status() == 2 ? "���δͨ��" : "ȡ������"));
								// ����������תΪ����洢
								Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(),
										purempsup.getPur_date(), purempsup.getPur_pay(), purempsup.getEmp_name(),
										purstatus, purempsup.getPur_mark() };
								// ����λ���鸳ֵ
								rowtop[i] = obj;
							}
							tmtop.setDataVector(rowtop, strtop);
							Color[] color = new Color[100];
							for (int i = 0; i < 100; i++) {
								int num = i % 2;
								if (num == 0) {
									color[i] = Color.WHITE;
								} else {
									color[i] = new Color(155,193,242);
								}
							}
							commondMethods.setColor(tabletop, color);
							
							// ��Ʒ��Ϣ
							List<PurchaseOrderDetailGood> lstugood = goodpurd.queryDelGoodTime(pur_date);
							// ��Ʒ��Ϣ��ά����
							Object[][] rows = new Object[lstugood.size()][];
							for (int i = 0; i < rows.length; i++) {
								// ��ȡ���϶���
								PurchaseOrderDetailGood goodpur = lstugood.get(i);
								String status = goodpur.getpDet_status() == 0 ? "�����"
										: (goodpur.getpDet_status() == 1 ? "δ���" : "���˻�");
								// ����������תΪ����洢
								Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(),
										goodpur.getGoods_units(), goodpur.getGoods_size(), goodpur.getGoods_purPrise(),
										goodpur.getpDet_number(),
										goodpur.getGoods_purPrise() * goodpur.getpDet_number(),
										goodpur.getGoods_sellPrice(), goodpur.getGoods_stoId(),
										goodpur.getGoods_keepDays(), goodpur.getGoods_minNumber(), status };
								// ����λ���鸳ֵ
								rows[i] = obj;
							}
							tmbottom.setDataVector(rows, str);
							for (int i = 0; i < 100; i++) {
								int num = i % 2;
								if (num == 0) {
									color[i] = Color.WHITE;
								} else {
									color[i] = new Color(155,193,242);
								}
							}
							commondMethods.setColor(tablebottom, color);
						}
					} catch (IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ��ʱ��", "������ʾ", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		// ��Ų�ѯ��ť
		btnSelid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �����Ʒ����������ԭ��Ϣ
				tmbottom.setNumRows(0);
				// ��ѯ�¼�
				// �������
				String[] strtop = { "���ݺ�", "����������", "��������", "֧���ܽ��", "������", "����״̬", "��ע" };
				// �������
				String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��", "����״̬" };
				if (!tfgoodid.getText().equals("")) {
					try {
						if (tfgoodid.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "��������Ҫ��ѯ�Ķ������", "������ʾ", JOptionPane.WARNING_MESSAGE);
							tfgoodid.setText("");
						} else {
							pur_id = Integer.parseInt(tfgoodid.getText());
							// ��ȡ���ݿ�����
							PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
							// ������Ϣ
							List<PurchaseOrderEmpSup> lstu = goodpurd.queryDelPurId(pur_id);
							// ������Ϣ��ά����
							Object[][] rowtop = new Object[lstu.size()][];
							for (int i = 0; i < rowtop.length; i++) {
								// ��ȡ���϶���
								PurchaseOrderEmpSup purempsup = lstu.get(i);
								String purstatus = purempsup.getPur_status() == 0 ? "δ���"
										: (purempsup.getPur_status() == 1 ? "���ͨ��"
												: (purempsup.getPur_status() == 2 ? "���δͨ��" : "ȡ������"));
								// ����������תΪ����洢
								Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(),
										purempsup.getPur_date(), purempsup.getPur_pay(), purempsup.getEmp_name(),
										purstatus, purempsup.getPur_mark() };
								// ����λ���鸳ֵ
								rowtop[i] = obj;
							}
							tmtop.setDataVector(rowtop, strtop);
							Color[] color = new Color[100];
							for (int i = 0; i < 100; i++) {
								int num = i % 2;
								if (num == 0) {
									color[i] = Color.WHITE;
								} else {
									color[i] = new Color(155,193,242);
								}
							}
							commondMethods.setColor(tabletop, color);
							// ��Ʒ��Ϣ��ά����
							// ��Ʒ��Ϣ
							List<PurchaseOrderDetailGood> lstugood = goodpurd.queryDelGoodId(pur_id);
							Object[][] rows = new Object[lstugood.size()][];
							for (int i = 0; i < rows.length; i++) {
								// ��ȡ���϶���
								PurchaseOrderDetailGood goodpur = lstugood.get(i);
								String status = goodpur.getpDet_status() == 0 ? "�����"
										: (goodpur.getpDet_status() == 1 ? "δ���" : "���˻�");
								// ����������תΪ����洢
								Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(),
										goodpur.getGoods_units(), goodpur.getGoods_size(), goodpur.getGoods_purPrise(),
										goodpur.getpDet_number(),
										goodpur.getGoods_purPrise() * goodpur.getpDet_number(),
										goodpur.getGoods_sellPrice(), goodpur.getGoods_stoId(),
										goodpur.getGoods_keepDays(), goodpur.getGoods_minNumber(), status };
								// ����λ���鸳ֵ
								rows[i] = obj;
							}
							tmbottom.setDataVector(rows, str);
							for (int i = 0; i < 100; i++) {
								int num = i % 2;
								if (num == 0) {
									color[i] = Color.WHITE;
								} else {
									color[i] = new Color(155,193,242);
								}
							}
							commondMethods.setColor(tablebottom, color);
						}
					} catch (NumberFormatException eid) {
						// �˻�ʧ����ʾ
						JOptionPane.showMessageDialog(null, "��������ȷ�ı�ţ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		// new PurchaseDelGoodQuery();
	}
}
