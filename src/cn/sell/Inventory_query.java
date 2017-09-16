package cn.sell;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import cn.dao.sell.SalesControl2;
import cn.dao.sell.SalesDao2;
import cn.liuenci.swing.DateChooser;
import cn.model.common.SellOrder;
import cn.model.common.Storage;
import cn.model.sell.Goods;
import cn.model.sell.RefundOrder;
import cn.model.sell.RefunddetailsOrder;
import cn.view.purchase.MTable;

/**
 * 
 * @author ���һ�
 *
 */
public class Inventory_query extends JFrame {
	JTable jt;
	DefaultTableModel tm1;// ������ģ��
	String[] good = { "��Ʒ���", "��Ʒ����", "��λ", "�����", "����", "�ۼ�", "�����ֵ", "����ͺ�", "��Ʒ������", "��ע" };
	JButton queryAllBtn = null;
	JScrollPane goodsSc = new JScrollPane();
	JScrollPane spright;
	JButton querySingleBtn;
	JTextField idText = null;
	JComboBox inputSto = null;
	int outSto_id = 0;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JTabbedPane tp;
	private JSplitPane Splitupanddown;
	private JPanel p4;
	private JLabel querytime;// ��ѯʱ��
	private JTextField Choicetime;// ѡ��ʱ��
	private JButton queryButton;// ʱ���ѯ��ť
	private JLabel inputOrder;// ���붩��
	private JTextField Orderinputframe;// ���������
	private JButton OrderqueryButton;// �����Ų�ѯ��ť
	private JTextField Numbers;// ��������ʾ��
	private JTable upperform;// �ϱ�����
	private DefaultTableModel upperdm;// �ϱߴ�����ģ��
	private JTable downform;// �±�����
	private DefaultTableModel downdm;// �±ߴ�����ģ��
	private JScrollPane upperrollingpanel;// �����ϱ߹������
	private JScrollPane downrollingpanel;;// �����±߹������
	private DefaultTableModel dm;

	int id;// ������
	String Customer;// �ͻ�
	int goodsid;// ��Ʒ���
	Date sell_date;// ʱ��
	// ��ʼ��

	public Inventory_query() {
		this.inin();
		addCurrent_inventory_query();
		p2();
		p3();
		addRegisterListener();
		// ���ô��ڿɼ�
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

	public void addCurrent_inventory_query() {
		// �ز����
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(5, 5, 807, 520);
		// ����ѡ����
		tp = new JTabbedPane(JTabbedPane.TOP);// ��ʾ���Ϸ�
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tp.setBounds(5, 5, 800, 510);
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setBounds(5, 5, 800, 510);
		p3 = new JPanel();
		p3.setLayout(null);
		p3.setBounds(5, 5, 800, 510);
		// ��p2,p3�ŵ�ѡ������
		tp.add("����ѯ", p2);
		// ��TP�Ž�p1��
		p1.add(tp);
		this.add(p1);

	}

	public void p2() {
		SalesDao2 selquery = new SalesDao2();
		List<Goods> lststu = selquery.geCurrent_inventory_query();
		Object[][] rows = new Object[lststu.size()][];
		for (int i = 0; i < rows.length; i++) {
			// ��ȡ�����е���Ʒ�嵥����
			Goods sb = lststu.get(i);
			// ����������תΪ����洢
			Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_number(),
					sb.getGoods_purPrise(), sb.getGoods_sellPrice(), sb.getWorth(), sb.getGoods_size(),
					sb.getGoods_keepDays(), sb.getGoods_mark() };
			// ����ά���鸳ֵ
			rows[i] = obj;
		}
		inputSto = new JComboBox();
		// �����ݿ��ֿ�����Ϣ
		SalesControl2 Storage = new SalesControl2();
		List<Storage> lst = Storage.getStorage();
		Object[][] objrow = new String[lst.size()][];
		for (int i = 0; i < objrow.length; i++) {
			Storage sb = lst.get(i);
			// ����ά���鸳ֵ;
			inputSto.addItem(sb.getName());
		}
		inputSto.setBounds(690, 20, 100, 20);
		tm1 = new DefaultTableModel(rows, good);// good��ͷ
		jt = new JTable(tm1);
		jt = new MTable(rows, good);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		spright = new JScrollPane(jt);
		spright.setBounds(5, 120, 795, 380);
		queryAllBtn = new JButton("��ѯ������Ʒ");
		queryAllBtn.setBounds(10, 15, 180, 30);
		querySingleBtn = new JButton("������Ʒ��Ų�ѯ");
		querySingleBtn.setBounds(10, 50, 180, 30);
		idText = new JTextField();
		idText.setBounds(10, 85, 100, 30);
		p2.add(inputSto);
		p2.add(queryAllBtn);
		p2.add(idText);
		p2.add(queryAllBtn);
		p2.add(querySingleBtn);
		p2.add(spright);
	}

	public void p3() {
		Splitupanddown = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		p3 = new JPanel();
		p3.setLayout(null);
		Splitupanddown.add(p3, JSplitPane.TOP);
		p4 = new JPanel();
		p4.setLayout(null);
		Splitupanddown.add(p4, JSplitPane.BOTTOM);
		querytime = new JLabel("��ѯʱ��:");
		querytime.setBounds(25, 65, 100, 25);

		Choicetime = new JTextField("����ѡ��ʱ��");
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(Choicetime);
		Choicetime.setBounds(100, 65, 160, 25);
		// �����Ų�ѯ��ť
		queryButton = new JButton("��ѯ");
		queryButton.setBounds(280, 65, 60, 25);

		inputOrder = new JLabel("�����붩����:");
		inputOrder.setBounds(450, 65, 100, 25);
		Orderinputframe = new JTextField();
		Orderinputframe.setBounds(540, 65, 160, 25);
		// �����Ų�ѯ��ť
		OrderqueryButton = new JButton("��ѯ");
		OrderqueryButton.setBounds(720, 65, 60, 25);
		// �������
		String[] strsellOrderform = { "���۶������", "��������", "������", "�տ���", "��ע" };
		SalesDao2 selquery = new SalesDao2();
		List<SellOrder> lststu = selquery.SeeRefundOrders();
		Object[][] rowsellOrderform = new Object[lststu.size()][];
		for (int i = 0; i < rowsellOrderform.length; i++) {
			SellOrder sellOrder = lststu.get(i);
			Object[] obj = { sellOrder.getSell_id(), sellOrder.getSell_date(), sellOrder.getSell_empId(),
					sellOrder.getSell_profit(), sellOrder.getSell_mark() };
			rowsellOrderform[i] = obj;
		}
		upperdm = new DefaultTableModel(rowsellOrderform, strsellOrderform);
		upperform = new JTable(upperdm);
		upperform = new MTable(upperdm);
		upperrollingpanel = new JScrollPane(upperform);
		upperrollingpanel.setBounds(10, 105, 780, 140);
		int mainFrameRows = upperform.getRowCount();
		double money = 0;
		for (int i = 0; i < mainFrameRows; i++) {
			money += Double.parseDouble(upperdm.getValueAt(i, 3).toString());
		}
		String[] blank = { "������:" + mainFrameRows, "", "", "��:" + money, "", "", "" };
		dm = new DefaultTableModel(null, blank);
		JTable jt = new JTable(dm);
		jt = new MTable(dm);
		JScrollPane js = new JScrollPane(jt);
		js.setBounds(10, 250, 780, 25);
		// ��ʾ����
		JLabel lbmain = new JLabel("���۵�����ϸ��Ϣ��");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("΢���ź�", Font.BOLD, 13));
		lbmain.setForeground(Color.black);
		Numbers = new JTextField("ID:" + id + Customer);
		Numbers.setBounds(130, 0, 200, 25);
		Numbers.setFont(new Font("΢���ź�", Font.BOLD, 12));
		MatteBorder border1 = new MatteBorder(0, 0, 0, 0, Color.RED);
		Numbers.setBorder(border1);
		// �������
		String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С" };
		downdm = new DefaultTableModel(null, str);
		downform = new JTable(downdm);
		downform = new MTable(downdm);
		downrollingpanel = new JScrollPane(downform);
		downrollingpanel.setBounds(10, 30, 780, 165);

		p4.add(lbmain);
		p4.add(Numbers);
		p4.add(downrollingpanel);
		p3.add(js);
		p3.add(upperrollingpanel);
		p3.add(querytime);
		p3.add(Choicetime);
		p3.add(inputOrder);
		p3.add(queryButton);
		p3.add(Orderinputframe);
		p3.add(OrderqueryButton);
		Splitupanddown.setDividerLocation(280);
		Splitupanddown.setEnabled(false);
		tp.add("�˿��", Splitupanddown);

	}

	public void addRegisterListener() {
		// ȫ����ѯ
		queryAllBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SalesDao2 selquery = new SalesDao2();
				List<Goods> lststu = selquery.geCurrent_inventory_query();
				Object[][] rows = new Object[lststu.size()][];
				for (int i = 0; i < rows.length; i++) {
					// ��ȡ�����е���Ʒ�嵥����
					Goods sb = lststu.get(i);
					// ����������תΪ����洢
					Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_number(),
							sb.getGoods_purPrise(), sb.getGoods_sellPrice(), sb.getWorth(), sb.getGoods_size(),
							sb.getGoods_keepDays(), sb.getGoods_mark() };
					// ����ά���鸳ֵ
					rows[i] = obj;
				}
				Inventory_query.this.remove(goodsSc);
				tm1 = new DefaultTableModel(rows, good);
				jt = new JTable(tm1);
				jt = new MTable(rows, good);
				spright = new JScrollPane(jt);
				spright.setBounds(12, 150, 795, 380);
				Inventory_query.this.add(spright);
			}

		});
		// ���ֲ�ѯ
		querySingleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				try {
					if (idText.getText() != null) {
						id = Integer.parseInt(idText.getText());
						SalesDao2 selquery = new SalesDao2();
						List<Goods> lststu = selquery.geCurrent_inventory_query(id);
						Object[][] rows = new Object[lststu.size()][];
						for (int i = 0; i < rows.length; i++) {
							Goods sb = null;
							try {
								sb = lststu.get(i);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							// ����������תΪ����洢
							Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(),
									sb.getGoods_number(), sb.getGoods_purPrise(), sb.getGoods_sellPrice(),
									sb.getWorth(), sb.getGoods_size(), sb.getGoods_keepDays(), sb.getGoods_mark() };
							// ����ά���鸳ֵ
							rows[i] = obj;
						}
						Inventory_query.this.remove(goodsSc);
						tm1 = new DefaultTableModel(rows, good);
						jt = new JTable(tm1);
						jt = new MTable(rows, good);
						spright = new JScrollPane(jt);
						spright.setBounds(12, 150, 795, 380);
						Inventory_query.this.add(spright);
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "��������ȷ������");
				}

			}
		});
		inputSto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object outStr = inputSto.getSelectedItem();
				if (outStr.equals("���ֿ�")) {
					outSto_id = 1;
				} else if (outStr.equals("���Ͽ�")) {
					outSto_id = 2;
				} else if (outStr.equals("�ƿ�")) {
					outSto_id = 3;
				} else if (outStr.equals("��ʳ��")) {
					outSto_id = 4;
				}
				SalesDao2 goodDao = new SalesDao2();
				List<Goods> listSto = goodDao.queryByStoId(outSto_id);
				Object[][] rows = new Object[listSto.size()][];
				for (int i = 0; i < rows.length; i++) {
					Goods sb = listSto.get(i);
					Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_number(),
							sb.getGoods_purPrise(), sb.getGoods_sellPrice(), sb.getWorth(), sb.getGoods_size(),
							sb.getGoods_keepDays(), sb.getGoods_mark() };
					// ����ά���鸳ֵ
					rows[i] = obj;
				}
				Inventory_query.this.remove(goodsSc);
				tm1 = new DefaultTableModel(rows, good);
				jt = new JTable(tm1);
				jt = new MTable(rows, good);
				spright = new JScrollPane(jt);
				spright.setBounds(5, 120, 795, 380);
				Inventory_query.this.add(spright);
			}
		});
		upperform.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = upperform.getSelectedRow();// ��ȡ��
				((DefaultTableModel) downform.getModel()).getDataVector().clear(); // ����������
				id = Integer.parseInt(upperform.getValueAt(row, 0).toString());
				Customer = upperform.getValueAt(row, 2).toString();
				Numbers.setText("ID:" + id + "	Name:" + Customer);
				// �������id��ʾ�ѳ����������Ϣ
				SalesDao2 selquery = new SalesDao2();
				List<RefunddetailsOrder> lststu = selquery.SeeRefunddetailsOrders(id);
				Object[][] rowparticularsform = new Object[lststu.size()][];
				for (int i = 0; i < rowparticularsform.length; i++) {
					RefunddetailsOrder sb = lststu.get(i);
					// "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��", "����С"
					Object[] obj = { sb.getsDet_goodId(), sb.getGoods_name(), sb.getGoods_units(),
							sb.getGoods_sellPrice(), sb.getsDet_number(), sb.getsDet_goodPrice(), sb.getGoods_size() };
					downdm.addRow(obj);

				}
			}
		});
		/**
		 * ���ݶ����Ų�ѯ�����˻صĶ���
		 */
		// OrderqueryButton.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// if (!Orderinputframe.getText().equals("")) {
		// ((DefaultTableModel) upperform.getModel()).getDataVector().clear();
		// // ����������
		// int idname = Integer.parseInt(Orderinputframe.getText());
		// SalesDao dao = new SalesDao();
		// List<RefundOrder> lstu = dao.To_find_thes(idname);
		// // ������Ϣ��ά����
		// Object[][] rowtop = new Object[lstu.size()][];
		// for (int i = 0; i < rowtop.length; i++) {
		// // ��ȡ���϶���
		// RefundOrder sb = lstu.get(i);
		// // ����������תΪ����洢
		// Object[] obj = { sb.getSell_id(), sb.getSell_date(),
		// sb.getCustomername(), sb.getSell_profit(),
		// sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
		// upperdm.addRow(obj);
		// }
		// } else {
		// ((DefaultTableModel) upperform.getModel()).getDataVector().clear();
		// // ����������
		// SalesDao selquery = new SalesDao();
		// List<RefundOrder> lststu = selquery.queryOrderById();
		// Object[][] rowsellOrderform = new Object[lststu.size()][];
		// for (int i = 0; i < rowsellOrderform.length; i++) {
		// RefundOrder sb = lststu.get(i);
		// Object[] obj = { sb.getSell_id(), sb.getSell_date(),
		// sb.getCustomername(), sb.getSell_profit(),
		// sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
		// rowsellOrderform[i] = obj;
		// upperdm.addRow(obj);
		// }
		// }
		// }
		// });
		/**
		 * ����ʱ���ѯ�����˻صĶ���
		 */
		queryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!Choicetime.getText().equals("")) {
						((DefaultTableModel) upperform.getModel()).getDataVector().clear(); // ����������
						sell_date = Date.valueOf(Choicetime.getText());
						SalesDao2 dao = new SalesDao2();
						List<RefundOrder> lstu = dao.querySellOrderByDate(sell_date);
						// ������Ϣ��ά����
						Object[][] rowtop = new Object[lstu.size()][];
						for (int i = 0; i < rowtop.length; i++) {
							// ��ȡ���϶���
							RefundOrder sb = lstu.get(i);
							// ����������תΪ����洢
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getCustomername(),
									sb.getSell_profit(), sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
							upperdm.addRow(obj);

						}
					}
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, "��������ȷ������");
				}

			}
		});
	}

	public static void main(String[] args) {
		new Inventory_query();
	}

}
