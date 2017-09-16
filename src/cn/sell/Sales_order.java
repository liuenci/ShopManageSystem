package cn.sell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
/**
 * ��
 * ��ѡ��õ���Ʒ���ɶ���
 * ������������ӹ��ܶ�����ѯ��һ����Ʒ����
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import cn.dao.sell.SalesDao2;
import cn.model.common.Employee;
import cn.model.common.Good;
import cn.model.common.SellDetail;
import cn.model.common.SellOrder;
import cn.model.sell.RefundOrder;
import cn.view.purchase.MTable;

/**
 * 
 * @author ���һ� 1.����:2017-08-13 2.���๦��: a.ѡ����Ʒ b.ɾ����Ʒ c.�޸���Ʒ���� d.���ڿؼ�ѡ������
 *         e.�쳣�Ĳ���ͨ����ʾ�򴫵ݸ��û���֪����Ϣ 3.�ڲ��๦�� a.�ָ���������ʾ�Լ��ֿ����Ʒ���ұ���ʾ��ѡ�����Ʒ
 *         b.��Ʒ���ģ����ѯ��Ʒ c.��ѡ�����Ʒ�ṩ�޸�������ɾ������ d.ѡ�����ͨ����ģ�ͺͱ����ֵ���ݸ����ࡣ
 *         e.ͨ����̬����������Ʒ���ܼ۸�
 * 
 */
public class Sales_order extends JFrame {
	Format gs = new Format();// ��ʽ
	SalesDao2 dao = new SalesDao2();// dao��
	private JPanel saleMainpanel = null;// ���������
	private JButton add_goods = null;// �����Ʒ
	private JLabel customer_name = null;// �����ͻ����Ʊ�ǩ
	private JLabel custname = null;// �ı�����ͻ�����
	private JLabel date_of_sale = null;// �������ڱ�ǩ
	String[] good = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��" };// ��ͷ
	private JLabel amount_receivable = null;// Ӧ�ս���ǩ
	static JLabel tfsupply1;// ��̬�ı������ܽ��
	private JLabel paid_in_amount = null;// ʵ�ս���ǩ
	private JTextField txte_Paid_in_amount;// ����ʵ�ս���ı���
	private JLabel handler = null;// ������
	private JComboBox empName = null;// ����Ա����
	private JLabel remarks = null;// ��ע��ǩ
	private JTextField txte_Remarks = null;// ��ע�ı���
	private JButton confirm = null;// ȷ�ϰ�ť
	private JButton cancel = null;// ȡ����ť
	private JButton delete_goods = null;// ɾ����ť
	private JButton modify_goods = null;// �޸�
	private JTable sp1 = null;// ������
	private DefaultTableModel closing_statement = null;// ������ģ��
	private JScrollPane spright = null;
	private JLabel order_number = null;// �����ű�ǩ
	private JLabel txet_order_number = null;// ��ʾ�����ű�ǩ
	private JTextField numText = null;// �޸������ı���
	private JLabel latit = null;
	private double moneys = 0.0;// �����ܽ��
	int addtb_sellOrder = 0;// ������
	int good_purPlanNum = 0;// �ƻ��ɹ�����
	double good_purPlanPrice;// ��Ʒ�Ĳɹ�����
	static double priceCount = 0.0;// ����Ϊ��̬�������ڶ�����ڽ���ֻ�仯
	private JTextField Text_Select_date = null;
	private JPanel catpan = null;// Ŀ¼���
	private JPanel butpan = null;// ��ť���
	private JPanel conpan = null;// �������
	private JLabel lbTime;
	String newday;
	// ��ʼ��

	public Sales_order() {
		this.inin();
		addTable();
		addAdd();
		clickEvent();
		// ���ô��ڿɼ�
		this.setVisible(true);

	}

	public void inin() {
		// ���ô��ڱ���
		this.setTitle("��Ʒ����");
		// ���ô��ڴ�С
		this.setSize(815, 530);
		// ������ʾ���ڵ�λ��
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		// ���ô��ڹر�
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void addAdd() {
		// ���������
		saleMainpanel = gs.createPanel(5, 5, 775, 450);
		// �ͻ�����
		customer_name = gs.createLabel("�ͻ�����:", 48, 30, 120, 20, "����", 14);
		// �ı�������ͻ�����
		custname = gs.createLabel("��ͨ�ͻ�", 115, 30, 120, 20, "����", 12);
		// �������ڱ�ǩ
		date_of_sale = gs.createLabel("��������:", 555, 30, 80, 20, "����", 14);
		// �����ı���
		// java.util.Date now = new java.util.Date();
		// Text_Select_date = new JTextField("����ѡ������");
		// Text_Select_date.setBounds(620, 30, 100, 20);
		// Text_Select_date.setBackground(new Color(238, 238, 238));
		// MatteBorder borders = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		// Text_Select_date.setBorder(borders);
		// DateChooser d = DateChooser.getInstance("yyyy-MM-dd");
		// d.register(Text_Select_date);
		// �����ı���
		DateFormat dfTime = new SimpleDateFormat("yyyy-MM-dd");
		newday = dfTime.format(new java.util.Date());
		lbTime = gs.createLabel(newday, 620, 30, 100, 20, "����", 12);

		// �����Ʒ��ť
		add_goods = gs.createButton("�����Ʒ", 30, 5, 90, 25, "����", 16);
		// Ӧ�ս���ǩ
		amount_receivable = gs.createLabel("Ӧ�ս��:", 25, 10, 120, 20, "����", 12);
		// ����Ӧ�ս���ı���
		tfsupply1 = new JLabel("" + priceCount);
		tfsupply1.setBounds(80, 10, 100, 20);
		tfsupply1.setBackground(new Color(238, 238, 238));
		MatteBorder border = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfsupply1.setBorder(border);
		paid_in_amount = gs.createLabel("ʵ�ս��:", 190, 10, 120, 20, "����", 12);
		// ����ʵ�ս���ı���
		txte_Paid_in_amount = new JTextField();
		txte_Paid_in_amount.setBounds(250, 10, 120, 20);
		txte_Paid_in_amount.setBackground(new Color(238, 238, 238));
		MatteBorder border2 = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		txte_Paid_in_amount.setBorder(border2);
		handler = new JLabel("������:");
		handler.setBounds(540, 10, 120, 20);
		// �����ı���
		empName = new JComboBox();
		// �����ݿ��ȡԱ����
		List<Employee> employeeList = dao.query(2);
		for (int i = 0; i < employeeList.size(); i++) {
			empName.addItem(employeeList.get(i).getEmp_name());
		}
		empName.setBounds(600, 10, 120, 20);
		// ��ע��ǩ
		remarks = gs.createLabel("��     ע", 25, 60, 120, 20, "����", 12);
		// ������ע�ı���
		txte_Remarks = new JTextField("");
		txte_Remarks.setBounds(80, 60, 290, 20);
		txte_Remarks.setBackground(new Color(238, 238, 238));
		MatteBorder border4 = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		txte_Remarks.setBorder(border4);
		// ȷ�ϰ�ť
		confirm = gs.createButton("ȷ��", 540, 60, 90, 25, "����", 13);
		// ȡ����ť
		cancel = gs.createButton("ȡ��", 650, 60, 90, 25, "����", 13);
		// ɾ����Ʒ��ť
		delete_goods = gs.createButton("ɾ����Ʒ", 650, 5, 90, 25, "����", 16);
		// �޸���Ʒ��ť
		modify_goods = gs.createButton("�޸���Ʒ", 540, 5, 90, 25, "����", 16);
		// ������
		sp1 = new JTable(closing_statement);
		// ����ʽ
		sp1 = new MTable(closing_statement);
		// ����
		sp1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		spright = new JScrollPane(sp1);
		spright.setBounds(10, 170, 790, 200);
		// ��Ʒ��ǩ
		latit = gs.createLabel("��Ʒ����", 340, 10, 180, 40, "����", 16);
		// ��Ʒ���۱�ǩ��ʽ
		latit.setFont(new Font("΢���ź�", Font.BOLD, 24));
		// �����ű�ǩ
		order_number = gs.createLabel("������:", 560, 10, 180, 40, "����", 15);
		order_number.setForeground(Color.red);
		// �����������ɵĶ�����
		addtb_sellOrder = dao.getMaxId();
		// �����ű�ǩ
		txet_order_number = gs.createLabel("" + addtb_sellOrder, 615, 10, 180, 40, "����", 13);
		txet_order_number.setForeground(Color.red);
		// Ŀ¼���
		catpan = gs.createPanel(10, 50, 790, 80);
		// ��ť���
		butpan = gs.createPanel(10, 130, 790, 37);
		// �������
		conpan = gs.createPanel(10, 380, 790, 100);
		// add
		catpan.add(lbTime);
		catpan.add(date_of_sale);
		catpan.add(custname);
		catpan.add(customer_name);
		butpan.add(modify_goods);
		butpan.add(delete_goods);
		butpan.add(add_goods);
		conpan.add(tfsupply1);
		conpan.add(amount_receivable);
		conpan.add(txte_Paid_in_amount);
		conpan.add(empName);
		conpan.add(remarks);
		conpan.add(txte_Remarks);
		conpan.add(cancel);
		conpan.add(confirm);
		conpan.add(paid_in_amount);
		conpan.add(handler);
		saleMainpanel.add(catpan);
		saleMainpanel.add(butpan);
		saleMainpanel.add(conpan);
		saleMainpanel.add(txet_order_number);
		saleMainpanel.add(order_number);
		saleMainpanel.add(spright);
		saleMainpanel.add(latit);
		this.add(saleMainpanel);
	}

	public static void main(String[] args) {
		new Sales_order();
	}

	/**
	 * �������� ��۸�
	 * 
	 * @author Administrator
	 *
	 */
	class updateNum extends JFrame {
		// ������
		JButton updateConfirm = new JButton("ȷ��");
		JButton updateCancel = new JButton("ȡ��");
		// �޸��������
		JPanel numPanel;
		// ����޸ı�ǩ
		JLabel updateLabel;
		// ��Ӱ�ť���
		JPanel btnPanel;

		// ���췽��
		public updateNum() {
			this.init();
			addPanel();
			addListener();
			setVisible(true);
		}

		// ��ʼ��
		public void init() {
			setTitle("�޸�����");
			setSize(400, 185);
			setLayout(null);
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenWidth = screenSize.width;
			int screenHeight = screenSize.height;
			int windowWidth = this.getWidth();
			int windowHeight = this.getHeight();
			int x = (screenWidth - windowWidth) / 2;
			int y = (screenHeight - windowHeight) / 2;
			setLocation(x, y);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		// ������
		public void addPanel() {
			// ������
			numPanel = gs.createPanel(0, 10, 365, 80);
			numPanel.setLayout(null);
			numPanel.setBorder(BorderFactory.createEtchedBorder());
			// ����޸ı�ǩ
			updateLabel = gs.createLabel("�޸�����:", 40, 30, 100, 30, "����", 20);
			// ��������ı���
			numText = gs.createTextField("", 160, 30, 100, 30, "����", 18, Color.white);
			// ��Ӱ�ť���
			btnPanel = gs.createPanel(10, 100, 365, 40);
			btnPanel.setLayout(null);
			btnPanel.setBorder(BorderFactory.createEtchedBorder());
			// ��Ӱ�ť
			updateConfirm.setBounds(10, 10, 100, 20);
			btnPanel.add(updateConfirm);
			updateCancel.setBounds(200, 10, 100, 20);
			numPanel.add(numText);
			btnPanel.add(updateCancel);
			numPanel.add(updateLabel);
			this.add(numPanel);
			this.add(btnPanel);

		}

		public void addListener() {
			/**
			 * �޸�����ȷ�ϰ�ť����¼�
			 */
			updateConfirm.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						good_purPlanNum = Integer.parseInt(numText.getText());
						if (good_purPlanNum <= 0) {
							JOptionPane.showMessageDialog(null, "��������Ϊ��������0");
						} else {
							int row = sp1.getSelectedRow();
							List<Good> number = dao
									.goodsnum(Integer.parseInt(closing_statement.getValueAt(row, 0).toString()));
							Object[][] rows = new Object[number.size()][];
							for (int j = 0; j < rows.length; j++) {
								Good sb = number.get(j);
								Object[] obj = { sb.getGoods_number() };
								rows[j] = obj;
								if (Integer.parseInt(obj[j].toString()) - good_purPlanNum > 0) {
									good_purPlanPrice = (double) sp1.getValueAt(row, 3);
									closing_statement.setValueAt(good_purPlanNum, row, 4);
									closing_statement.setValueAt(good_purPlanNum * good_purPlanPrice, row, 5);
									// ���ܽ�����
									priceCount = 0;
									// ��ȡ�е�����
									int mainFrameRows = closing_statement.getRowCount();
									for (int i = 0; i < mainFrameRows; i++) {
										// ��ȡÿһ�е��ܽ��
										double rowPrice = (double) sp1.getValueAt(i, 5);
										priceCount += rowPrice;
									}
									tfsupply1.setText("" + priceCount);
									updateNum.this.dispose();
								} else {
									JOptionPane.showConfirmDialog(null, "���û������Ҫ��������ȷ���޸�", "������ʾ",
											JOptionPane.DEFAULT_OPTION);
								}
							}

						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ������");
					}

				}
			});
			/**
			 * �޸�����ȡ����ť����¼�
			 */
			updateCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					updateNum.this.dispose();
				}
			});
		}
	}

	/**
	 * ����¼�
	 */
	public void clickEvent() {
		/**
		 * �����Ʒ
		 */
		add_goods.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Choose_goods(closing_statement);

			}
		});
		/**
		 * ȷ�ϰ�ť 1.ʵ�ս��Ҫ����Ӧ�ս�� 2.swing��Ʒ�������ܴ������ݿ� 2.1������������������޸�
		 * 3.������������Ȱ�swing���ݱ��� 4.�������۶������������鶩�������������۶��������������������鶩���� 4.1ѭ������������鶩��
		 * 5.�ҵ���Ʒ��������Ū������ѭ����ȥ���ɵĶ�����Ʒ����
		 */
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date date = null;
				// ����е��������
				int mainFrameRows = closing_statement.getRowCount();
				if (mainFrameRows == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ����Ʒ�����ɶ���");
				} else {
					try {
						// ��ʵ�����С��Ӧ�����ʱ
						if (Double.parseDouble(txte_Paid_in_amount.getText()) < priceCount) {
							JOptionPane.showMessageDialog(null, "��������ȷ��Ӧ���������иŲ�����");
						} else {
							String sDet_mark = null;
							try {
								date = Date.valueOf(newday);
								SellOrder sellOrder = new SellOrder();
								// ��ȡ����������id
								Employee emp = dao.queryEmpID(empName.getSelectedItem().toString());
								SellOrder goods = new SellOrder(emp.getEmp_id(), date, priceCount, 0,
										txte_Remarks.getText());
								goods.setSell_id(addtb_sellOrder);
								int sel = dao.reviseSales(goods);
								for (int i = 0; i < mainFrameRows; i++) {
									// ѭ���޸����ݿ����Ʒ����
									int sDet_goodId;// ��Ʒ���
									int sDet_number;// ��Ʒ����
									sDet_goodId = Integer.parseInt(closing_statement.getValueAt(i, 0).toString());// ��Ʒ���
									sDet_number = Integer.parseInt(closing_statement.getValueAt(i, 4).toString());// ����
									int updateStoNum = dao.updateGoodNum(sDet_goodId, sDet_number);
									// �����������鵥
									double sDet_goodPrice = Double
											.parseDouble(closing_statement.getValueAt(i, 5).toString());// ��Ʒ�ܼ�
									int sDet_status = 0;// ״̬
									sDet_mark = txte_Remarks.getText();// ��ע
									// ͨ��ID����Ʒ����Ū����
									List<Good> number = dao.goodsnum(sDet_goodId);
									Object[][] rows = new Object[number.size()][];
									// ��ȡ�굥��Ķ������
									int pDetId = addtb_sellOrder;
									// �����굥�����
									SellDetail sellDetail = new SellDetail();
									sellDetail.setsDet_goodId(sDet_goodId);
									sellDetail.setsDet_sellId(pDetId);
									sellDetail.setsDet_number(sDet_number);
									sellDetail.setsDet_goodPrice(sDet_goodPrice);
									sellDetail.setsDet_status(sDet_status);
									sellDetail.setsDet_mark(sDet_mark);
									dao.addtb_sellDetail(sellDetail);
								}
								JOptionPane.showMessageDialog(null, "���۳ɹ���");
								double a=Double.parseDouble(txte_Paid_in_amount.getText()) - priceCount;
								JOptionPane.showMessageDialog(null, "���㣺"+a);
								Sales_order.this.dispose();
								priceCount=0;
							} catch (IllegalArgumentException e1) {
								JOptionPane.showMessageDialog(null, "���ڴ���");
							}
						}
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "��������ȷ��ʵ�ս��");
					}
				}
			}
		});
		/**
		 * ȡ����ť�¼�
		 */
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int delete = 0;
				SalesDao2 de = new SalesDao2();
				RefundOrder re = new RefundOrder();
				re.setSell_id(addtb_sellOrder);
				int scid = de.delete(re);
				Sales_order.this.dispose();
			}
		});
		/**
		 * ɾ����Ʒ��ť�¼�
		 */
		delete_goods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ѡ����
				int row = sp1.getSelectedRow();
				// ���ܽ�����
				priceCount = 0;
				if (row > -1) {
					closing_statement.removeRow(row);
					// ��ȡ�е�����
					int mainFrameRows = closing_statement.getRowCount();
					for (int i = 0; i < mainFrameRows; i++) {
						// ��ȡÿһ�е��ܽ��
						double rowPrice = (double) sp1.getValueAt(i, 5);
						priceCount += rowPrice;
					}
					tfsupply1.setText("" + priceCount);
				}
			}
		});
		/**
		 * �޸İ�ť�¼�
		 */
		modify_goods.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡѡ�е���
				int row = sp1.getSelectedRow();
				if (row > -1) {
					new updateNum();
				}
			}
		});
	}

	public void addTable() {
		closing_statement = new DefaultTableModel(null, good);// good��ͷ
	}
}
