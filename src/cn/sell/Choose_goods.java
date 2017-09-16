package cn.sell;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * ���һ� 1.����:2017-08-11
 * 1.�����Ʒ
 * a.ѡ����Ʒ 
 * b.ɾ����Ʒ
 * c.�޸���Ʒ����
 * 2.�ڲ��๦�� 
 * a.�ָ���������ʾ�Լ��ֿ����Ʒ���ұ���ʾ��ѡ�����Ʒ
 * b.��Ʒ���ģ����ѯ��Ʒ 
 * c.��ѡ�����Ʒ�ṩ�޸�������ɾ������ 
 * d.ѡ�����ͨ����ģ�ͺͱ����ֵ���ݸ����ࡣ
 */
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

import cn.dao.sell.SalesControl2;
import cn.dao.sell.SalesDao2;
import cn.model.common.Good;
import cn.view.purchase.MTable;

public class Choose_goods extends JFrame {
	private JPanel p = null;// ��Ʒ�嵥�����
	private JTable sp = null;// ��Ʒ�嵥������
	private JTable sp1 = null;// ��Ʒ�嵥������
	private DefaultTableModel tm = null; // ������ģ��
	private DefaultTableModel tm1 = null; // ������ģ��
	private DefaultTableModel tm2 = null; // ������ģ��
	private JSplitPane slipt = null; // �����ָ�������
	private JPanel leftht = null; // ������
	private JScrollPane spright = null; // ��߹������
	private JScrollPane spright1 = null; // �ұ߹������
	private JPanel pright = null; // �ұ����
	String[] good = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��" };

	// ��ʽ
	Format gs = new Format();
	JLabel sm = null;// ����
	JLabel smm = null;// ����
	JLabel prompt = null;// ��������Ʒ��Ų�ѯ��Ʒ
	JTextField numtext = null;// ��Ʒ��������ı���
	JButton jiar = null;// ������Ʒ��ť
	private JPanel Propan = null;// �������
	private JPanel butpan = null;// ��ť���
	private JButton modify = null;// �޸�
	private JButton confirm = null;// ȷ��
	private JButton delete = null;// ɾ��
	private JButton cancel = null;// ȡ��
	private JPanel list = null;// �б�
	Object[] sz = new Object[5];

	// ��ʼ��
	public Choose_goods(DefaultTableModel tm2) {
		// ���ó�ʼ������
		this.inin();
		this.addPanel();
		Clickevent();
		this.tm2 = tm2;
		// ���ô��ڿɼ�
		this.setVisible(true);
	}

	public Choose_goods() {
		this.inin();
		this.addPanel();
		Clickevent();
		// ���ô��ڿɼ�
		this.setVisible(true);
	}

	public void inin() {
		// ���ô��ڱ���
		this.setTitle("ѡ����Ʒ");
		// ���ô��ڴ�С
		this.setSize(815, 530);
		// ������ʾ���ڵ�λ��
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		// ���ô��ڹر�
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// ���������Ԫ��
	public void addPanel() {
		// �����ָ�������
		slipt = new JSplitPane();
		slipt.setDividerLocation(407);
		// ���ó�ʼ�������巽��
		this.setRightleftht();
		// ��������������
		slipt.add(leftht, JSplitPane.LEFT);
		// ���ó�ʼ���ұ���巽��
		this.setRightPanel();
		// �����ұ����
		slipt.add(pright, JSplitPane.RIGHT);
		slipt.setEnabled(false);
		// �������ӵ�����
		this.add(slipt);
	}

	// ������
	private void setRightleftht() {
		leftht = new JPanel();
		leftht.setLayout(null);
		sm = gs.createLabel("��ѡ��Ҫ�������Ʒ,���鿴��ϸ��Ϣ��ϸ�˶Բ�����Ҫ���������", 35, 0, 390, 50, "����", 12);
		smm = gs.createLabel("��ע��һ������Դ���㲻����ӵ��ұ���壬��ѡ������Ʒ", 10, 20, 390, 50, "����", 12);
		prompt = gs.createLabel("��������Ʒ��Ų�ѯ��Ʒ:", 10, 55, 150, 50, "����", 13);
		numtext = gs.createTextField("", 165, 68, 100, 25, "����", 12, Color.white);
		jiar = gs.createButton("��ѯ", 290, 68, 95, 25, "����", 12);

		// �������
		String[] goods = { "��Ʒ���", "��Ʒ����", "��λ", "���", "�ۼ�", "�����" };
		// �����ݿ��ȡ��Ʒ�嵥��Ϣ
		SalesControl2 selquery = new SalesControl2();
		List<Good> lststu = selquery.selqueryGood();
		Object[][] rows = new Object[lststu.size()][];
		for (int i = 0; i < rows.length; i++) {
			// ��ȡ�����е���Ʒ�嵥����
			Good sb = lststu.get(i);
			// ����������תΪ����洢
			Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_size(),
					sb.getGoods_sellPrice(), sb.getGoods_number() };
			// ����ά���鸳ֵ
			rows[i] = obj;
		}

		tm = new DefaultTableModel(rows, goods);// good��ͷ
		sp = new JTable(tm);
		sp = new MTable(tm);
		JScrollPane xx = new JScrollPane(sp);

		// ����п�����Ӧ
		sp.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		spright = new JScrollPane(sp);
		spright.setBounds(5, 110, 400, 380);
		Propan = gs.createPanel(5, 5, 400, 100);
		Propan.add(prompt);
		Propan.add(sm);
		Propan.add(smm);
		Propan.add(jiar);
		Propan.add(numtext);
		leftht.add(Propan);
		leftht.add(spright);

	}

	// �ұ����
	private void setRightPanel() {
		pright = new JPanel();
		pright.setLayout(null);
		// �������
		tm1 = new DefaultTableModel(null, good);
		sp1 = new JTable(tm1);
		sp1 = new MTable(tm1);
		spright1 = new JScrollPane(sp1);
		modify = gs.createButton("�޸�", 20, 17, 80, 27, "����", 14);
		confirm = gs.createButton("ȷ��", 200, 17, 80, 27, "����", 14);
		delete = gs.createButton("ɾ��", 110, 17, 80, 27, "����", 14);

		cancel = gs.createButton("ȡ��", 290, 17, 80, 27, "����", 14);
		JLabel Title = gs.createLabel("��ѡ��Ʒ", 150, 15, 100, 20, "����", 18);
		list = gs.createPanel(10, 5, 380, 50);
		spright1.setBounds(10, 60, 380, 355);
		butpan = gs.createPanel(10, 420, 380, 60);
		list.add(Title);
		pright.add(list);
		butpan.add(cancel);
		butpan.add(delete);
		butpan.add(confirm);
		butpan.add(modify);
		pright.add(butpan);
		pright.add(spright1);
	}

	public void Clickevent() {
		// ��ѯ
		jiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (numtext.getText().equals("")) {
					// �������
					String[] goods = { "��Ʒ���", "��Ʒ����", "��λ", "���", "�ۼ�", "�����" };
					// �����ݿ��ȡ��Ʒ�嵥��Ϣ
					SalesControl2 selquery = new SalesControl2();
					List<Good> lststu = selquery.selqueryGood();
					Object[][] rows = new Object[lststu.size()][];
					for (int i = 0; i < rows.length; i++) {
						// ��ȡ�����е���Ʒ�嵥����
						Good sb = lststu.get(i);
						// ����������תΪ����洢
						Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_size(),
								sb.getGoods_sellPrice(), sb.getGoods_number() };
						// ����ά���鸳ֵ
						rows[i] = obj;
						String[] good = { "��Ʒ���", "��Ʒ����", "��λ", "���", "�ۼ�", "�����" };
						tm.setDataVector(rows, goods);
					}
				} else if (!numtext.getText().equals("")) {
					try {
						SalesDao2 dao = new SalesDao2();
						List<Good> lststu = dao.queryGoods(Integer.parseInt(numtext.getText()));
						Object[][] rows = new Object[lststu.size()][];
						for (int i = 0; i < rows.length; i++) {
							// ��ȡ�����е���Ʒ�嵥����
							Good sb = lststu.get(i);
							// ����������תΪ����洢
							Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_size(),
									sb.getGoods_sellPrice(), sb.getGoods_number() };
							// ����ά���鸳ֵ
							rows[i] = obj;
						}
						String[] goods = { "��Ʒ���", "��Ʒ����", "��λ", "���", "�ۼ�", "�����" };
						tm.setDataVector(rows, goods);
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "��������ȷ����Ʒ��ź�����");
					}
					
				}

			}
		});
		// �е��
		sp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = sp.getSelectedRow();
					int column = sp.getAutoResizeMode();
					sp.getValueAt(row, column);
					if (row != -1) {
						new Commodity_information(tm, sp, tm1);
					}
				}
			}
		});

		// ���е��
		sp1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
					int row = sp1.getSelectedRow();
					int column = sp1.getAutoResizeMode();
					sp1.getValueAt(row, column);
					if (row != -1) {
						new Alter_choice(sp1, tm1);
					}
				}
			}
		});
		// �޸�
		modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �޸�
				int row = sp1.getSelectedRow();
				if (row > -1) {
					new Alter_choice(sp1, tm1);
				} else {
					signal();
				}

			}
		});

		// ȷ��
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��������û����Ʒ��ֱ�����
				try {
					if (tm2.getRowCount() == 0) {
						for (int i = 0; i < tm1.getRowCount(); i++) {
							String z = tm1.getValueAt(i, 0).toString().trim();
							int zid = Integer.parseInt(z);
							// ��ȡ�˻�������
							String y = tm1.getValueAt(i, 4).toString().trim();
							int znum = Integer.parseInt(y);

							Object[][] rowobj = new Object[tm1.getColumnCount()][];
							Object[] obj = { sp1.getValueAt(i, 0), sp1.getValueAt(i, 1), sp1.getValueAt(i, 2),
									sp1.getValueAt(i, 3), sp1.getValueAt(i, 4), sp1.getValueAt(i, 5) };
							rowobj[i] = obj;
							Choose_goods.this.dispose();
							tm2.addRow(obj);
							Sales_order.priceCount += (double) sp1.getValueAt(i, 5);
							Sales_order.tfsupply1.setText("" + Sales_order.priceCount);
						}

					} else {
						// ���ѭ������������Ķ������������һ����ӵĶ�������ƥ�䣬�����ƥ�䵽ֱ���������������ֱ����ӵ������
						for (int i = 0; i < tm1.getRowCount(); i++) {
							// ��ȡÿһ����Ʒ�ı��
							String z = tm1.getValueAt(i, 0).toString().trim();
							int zid = Integer.parseInt(z);
							// ��ȡ��Ʒ������
							String y = tm1.getValueAt(i, 4).toString().trim();
							int znum = Integer.parseInt(y);
							// ��ȡ����Ʒ�Ľ��
							String money = tm1.getValueAt(i, 5).toString().trim();
							double xmoney = Double.parseDouble(money);
							int sum = 0;
							double smoney = 0;
							for (int j = 0; j < tm2.getRowCount(); j++) {
								if (zid == Integer.parseInt(tm2.getValueAt(j, 0).toString().trim())) {
									SalesDao2 dao = new SalesDao2();
									List<Good> lststu = dao.goodsnum(zid);
									Object[][] rows = new Object[lststu.size()][];
									for (int x = 0; x < rows.length; x++) {
										// ��ȡ�����е���Ʒ�嵥����
										Good sb = lststu.get(x);
										// ����������תΪ����洢
										Object[] obj = { sb.getGoods_number() };
										// ����ά���鸳ֵ
										rows[x] = obj;
										if (znum + Integer.parseInt(tm2.getValueAt(j, 4).toString().trim()) > Integer
												.parseInt(obj[x].toString())) {
											tm1.removeRow(x);
											JOptionPane.showMessageDialog(null, "������������Ѵﵽ���,��ѡ��������Ʒ");
										} else if (znum
												+ Integer.parseInt(tm2.getValueAt(j, 4).toString().trim()) <= Integer
														.parseInt(obj[x].toString())) {
											sum = znum + Integer.parseInt(tm2.getValueAt(j, 4).toString().trim());
											smoney = xmoney + Double.parseDouble(tm2.getValueAt(j, 5).toString().trim());
											tm2.setValueAt(sum, j, 4);
											tm2.setValueAt(smoney, j, 5);
											Sales_order.priceCount += xmoney;
											Sales_order.tfsupply1.setText("" + Sales_order.priceCount);
											Choose_goods.this.dispose();
											
										}
										break;
									}
									break;
								} else if (j == tm2.getRowCount() - 1) {
									// ���������ɣ���Ȼû��ƥ�䵽
									Object[][] rowobj = new Object[tm2.getRowCount()][];
									Object[] obj = { sp1.getValueAt(i, 0), sp1.getValueAt(i, 1), sp1.getValueAt(i, 2),
											sp1.getValueAt(i, 3), sp1.getValueAt(i, 4), sp1.getValueAt(i, 5), };
									rowobj[i] = obj;
									tm2.addRow(obj);
									Sales_order.priceCount += (double) sp1.getValueAt(i, 5);
									Sales_order.tfsupply1.setText("" + Sales_order.priceCount);
									Choose_goods.this.dispose();
									break;
								}
							}
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "��ѡ����Ʒ��ȷ��");
				}
				
			}
		});
		// ɾ��
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = sp1.getSelectedRow();
				if (row > -1) {
					tm1.removeRow(row);
				} else {
					signal();
				}
			}
		});

		// ȡ��
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tm1.getRowCount() == 0) {
					Choose_goods.this.dispose();
				}else {
					Object[] options = { "ȷ��", "ȡ��" };
					int response = JOptionPane.showOptionDialog(null, "��ȷ���Ƿ��˳�", "��ʾ", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					if (response == 0) {
						Choose_goods.this.dispose();
					}
				}
			}
		});
	}

	public void signal() {
		JOptionPane.showConfirmDialog(null, "��ѡ��Ҫ�����Ķ���", "������ʾ", JOptionPane.DEFAULT_OPTION);
	}

	public void xx1() {
		JOptionPane.showConfirmDialog(null, "��ѡ������Ʒ�Ƿ�ȷ�Ϸ����޸�", "������ʾ", JOptionPane.DEFAULT_OPTION);
	}

	public static void main(String[] args) {
		new Choose_goods();
	}
}
