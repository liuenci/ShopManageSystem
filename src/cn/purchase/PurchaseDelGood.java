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
 * 1.���ڣ�2017-8-15 
 * 2.��Ҫ���� 
 * a.����ʱ���ѯ�Ѿ����Ķ���
 * b.���ݶ����Ų�ѯ�Ѿ����Ķ���
 * c.�������˻� 
 * d.�ɲ����˻�
 * @author �ܳ���
 *
 */
public class PurchaseDelGood extends JPanel {
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
	JButton btnAllDel;
	JButton btnLitDel;
	CommondMethods comMenth = null;

	public PurchaseDelGood(JSplitPane splittopDel) {
		this.splittopbottom = splittopDel;
		comMenth = new CommondMethods();
		this.setLayout(new BorderLayout());
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
		// �����޸Ĵ��ڵĴ�С
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
		JLabel lbTitle = comMenth.createLabel("�ɹ��˻�", 340, 10, 180, 40, "����", 30);

		btnAllDel = comMenth.createButton("�����˻�", 25, 50, 90, 25, "����", 14);
		btnLitDel = comMenth.createButton("�����˻�", 140, 50, 90, 25, "����", 14);

		JLabel lbtime = comMenth.createLabel("��ѯʱ��:", 25, 90, 100, 25, "����", 14);
		tfgoodTime = comMenth.createTextField("����ѡ��ʱ��", 25, 90, 100, 25, "����", 14, Color.WHITE);
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(tfgoodTime);
		tfgoodTime.setBounds(100, 90, 160, 25);
		// ʱ���ѯ��ť
		btnSelTime = comMenth.createButton("��ѯ", 280, 90, 60, 25, "����", 14);
		JLabel lbtopid = comMenth.createLabel("�����붩����:", 440, 90, 100, 25, "����", 14);
		tfgoodid = comMenth.createTextField("", 540, 90, 160, 25, "����", 14, Color.white);
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
				if (e.getClickCount() == 2) {
					int afrow = 0;
					int getrow = tabletop.getSelectedRow();
					if (getrow > -1) {
						Object[] obj = { "�����˻�", "��������" };
						// ѡ����ʾ���
						int res = JOptionPane.showOptionDialog(null, "�Ƿ�ȡ��������", "������ʾ",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
						if (res == JOptionPane.YES_OPTION) {
							int rowbottom = tablebottom.getRowCount();// ��ȡ��Ʒ������
							for (int j = 0; j < rowbottom; j++) {
								// ��ȡ���ݿ�����
								PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
								// ��Ʒ��Ϣ
								afrow = goodpurd.updatePassGood((int) tablebottom.getValueAt(j, 5),
										(int) tabletop.getValueAt(getrow, 0), (int) tablebottom.getValueAt(j, 0));
								// �޸�table����Ϣ
								tmtop.setValueAt("ȡ������", getrow, 5);
								tmbottom.setValueAt("���˻�", j, 10);
								String goodtime = tfgoodTime.getText().trim();
							}
						} else {
							// ��������������
							JOptionPane.showMessageDialog(null, "�˻�������ȡ����", "������ʾ", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						// ��������������
						JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�˻�����Ʒ��Ϣ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
					}
					// �ɹ���ʾ
					if (afrow > 0) {
						JOptionPane.showMessageDialog(null, "�˻��ɹ���");
					}
				} else if (e.getClickCount() == 1) {
					int row = tabletop.getSelectedRow();
					int column = tabletop.getAutoResizeMode();
					tabletop.getValueAt(row, column);
					purid = (int) tabletop.getValueAt(row, 0);
					PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
					// �ж϶������Ƿ�����Ʒ��û����ı䶩��״̬
					int rowNum = goodpurd.queryPassIsNull(purid);
					if (rowNum == 0) {
						goodpurd.updateOrderStatus(purid);
					}
					if (row != -1) {
						// �������
						String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "�ֿ���", "������", "��Ϳ��",
								"����״̬" };
						// ��ȡ���ݿ�����
						// ��Ʒ��Ϣ
						List<PurchaseOrderDetailGood> lstugood = goodpurd.queryPassId(purid);
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
		ptop.add(btnAllDel);
		ptop.add(btnLitDel);
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
		// ˫���������ѡ����Ʒ
		tablebottom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int afrow = 0;
					int row = tablebottom.getSelectedRow();
					goodid = (int) tablebottom.getValueAt(row, 0);
					int goodnumber = (int) tablebottom.getValueAt(row, 5);
					int rows = tabletop.getSelectedRow();
					if (row != -1) {
						Object[] obj = { "�����˻�", "��������" };
						// ѡ����ʾ���
						int res = JOptionPane.showOptionDialog(null, "�Ƿ�ȷ���˻���", "������ʾ",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
						if (res == JOptionPane.YES_OPTION) {
							PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
							afrow = goodpurd.delPassGood(goodnumber, goodid, purid);
							// �ж��Ƿ�Ϊ���һ����Ʒ
							tmbottom.removeRow(row);
							if (tmbottom.getRowCount() == 0) {
								goodpurd.updatePassPur(purid);
								tmtop.setValueAt("ȡ������", rows, 5);
							}
						} else {
							// �˻�ʧ����ʾ
							JOptionPane.showMessageDialog(null, "������ȡ����", "������ʾ", JOptionPane.WARNING_MESSAGE);
						}
					}
					if (afrow > 0) {
						JOptionPane.showMessageDialog(null, "�˻��ɹ���");
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
				// �����Ʒ����������ԭ��Ϣ
				tmbottom.setNumRows(0);
				// ��ѯ�¼�
				// �������
				String[] strtop = { "���ݺ�", "����������", "��������", "֧���ܽ��", "������", "�ɹ�״̬", "��ע" };
				// �������
				if (!tfgoodTime.getText().equals("") || !tfgoodTime.getText().equals("����ѡ��ʱ��")) {
					try {
						pur_date = Date.valueOf(tfgoodTime.getText());
						// ��ȡ���ݿ�����
						PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
						// ������Ϣ
						List<PurchaseOrderEmpSup> lstu = goodpurd.queryOrdPassTime(pur_date);
						// ������Ϣ��ά����
						Object[][] rowtop = new Object[lstu.size()][];
						for (int i = 0; i < rowtop.length; i++) {
							// ��ȡ���϶���
							PurchaseOrderEmpSup purempsup = lstu.get(i);
							String purstatus = purempsup.getPur_status() == 0 ? "δ���"
									: (purempsup.getPur_status() == 1 ? "���ͨ��"
											: (purempsup.getPur_status() == 2 ? "���δͨ��" : "ȡ������"));
							// ����������תΪ����洢
							Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
									purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus,
									purempsup.getPur_mark() };
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
					} catch (IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ������", "������ʾ", JOptionPane.WARNING_MESSAGE);
						tfgoodTime.setText("");
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
				String[] strtop = { "���ݺ�", "����������", "��������", "֧���ܽ��", "������", "�ɹ�״̬", "��ע" };
				// �������
				if (!tfgoodid.getText().equals("")) {
					try {
						pur_id = Integer.parseInt(tfgoodid.getText());
						// ��ȡ���ݿ�����
						PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
						// ������Ϣ
						List<PurchaseOrderEmpSup> lstu = goodpurd.queryOrdPassId(pur_id);
						// ������Ϣ��ά����
						Object[][] rowtop = new Object[lstu.size()][];
						for (int i = 0; i < rowtop.length; i++) {
							// ��ȡ���϶���
							PurchaseOrderEmpSup purempsup = lstu.get(i);
							String purstatus = purempsup.getPur_status() == 0 ? "δ���"
									: (purempsup.getPur_status() == 1 ? "���ͨ��"
											: (purempsup.getPur_status() == 2 ? "���δͨ��" : "ȡ������"));
							// ����������תΪ����洢
							Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
									purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus,
									purempsup.getPur_mark() };
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
					} catch (IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ�Ķ�����", "������ʾ", JOptionPane.WARNING_MESSAGE);
						tfgoodid.setText("");
					}
				}
			}
		});
		//ȫ���˻�
		btnAllDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int afrow = 0;
				int getrow = tabletop.getSelectedRow();
				if (getrow > -1) {
					Object[] obj = { "�����˻�", "��������" };
					// ѡ����ʾ���
					int res = JOptionPane.showOptionDialog(null, "�Ƿ������˻���", "������ʾ",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					if (res == JOptionPane.YES_OPTION) {
						int rowbottom = tablebottom.getRowCount();// ��ȡ��Ʒ������
						for (int j = 0; j < rowbottom; j++) {
							// ��ȡ���ݿ�����
							PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
							// ��Ʒ��Ϣ
							afrow = goodpurd.updatePassGood((int) tablebottom.getValueAt(j, 5),
									(int) tabletop.getValueAt(getrow, 0), (int) tablebottom.getValueAt(j, 0));
							// �ɹ���ʾ
							if (afrow > 0) {
								JOptionPane.showMessageDialog(null, "�˻��ɹ���");
								// �޸�table����Ϣ
								tmtop.setValueAt("ȡ������", getrow, 5);
								tmbottom.setValueAt("���˻�", j, 10);
								String goodtime = tfgoodTime.getText().trim();
							}else{
								// ��������������
								JOptionPane.showMessageDialog(null, "�˻�ʧ�ܣ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						// ��������������
						JOptionPane.showMessageDialog(null, "�˻�������ȡ����", "������ʾ", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�˻�����Ʒ��Ϣ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnLitDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int afrow = 0;
				int row = tablebottom.getSelectedRow();
				if (row != -1) {
					goodid = (int) tablebottom.getValueAt(row, 0);
					int goodnumber = (int) tablebottom.getValueAt(row, 5);
					int rows = tabletop.getSelectedRow();
					Object[] obj = { "�����˻�", "��������" };
					// ѡ����ʾ���
					int res = JOptionPane.showOptionDialog(null, "�Ƿ�ȷ���˻���", "������ʾ", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					if (res == JOptionPane.YES_OPTION) {
						PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
						afrow = goodpurd.delPassGood(goodnumber, goodid, purid);
						if (afrow > 0) {
							JOptionPane.showMessageDialog(null, "�˻��ɹ���");
						}else{
							// ��������������
							JOptionPane.showMessageDialog(null, "�˻�ʧ�ܣ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
						}
						// �ж��Ƿ�Ϊ���һ����Ʒ
						tmbottom.removeRow(row);
						if (tmbottom.getRowCount() == 0) {
							goodpurd.updatePassPur(purid);
							tmtop.setValueAt("ȡ������", rows, 5);
						}
					} else {
						// �˻�ʧ����ʾ
						JOptionPane.showMessageDialog(null, "������ȡ����", "������ʾ", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�˻�����Ʒ��Ϣ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public static void main(String[] args) {
		// new PurchaseDelGood();
	}
}
