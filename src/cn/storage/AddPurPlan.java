package cn.storage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
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
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.PurPlanDao3;
import cn.dao.storage.EmpDao3;
import cn.dao.storage.StorageDao3;
import cn.liuenci.swing.DateChooser;
import cn.method.CommondMethods;
import cn.model.common.Employee;
import cn.model.common.PurPlanDetail;
import cn.model.common.Storage;
import cn.model.common.Supply;
import cn.view.purchase.MTable;

/**
 * 1.����:2017-08-03 
 * 2.���๦��:
 *  a.��Ӧ��ѡ��
 *  b.�ջ��ֿ�ѡ��
 *  c.�������Ʒ
 *  d.ɾ����Ʒ
 *  e.�޸���Ʒ����
 *  f.���ڿؼ�ѡ������
 *  g.�쳣�Ĳ���ͨ����ʾ�򴫵ݸ��û���֪����Ϣ
 *  l.�ɹ��ƻ��Ĵ��������������ݿ�
 * 3.�ڲ��๦��
 * 	a.�ָ���������ʾ�Լ��ֿ���������Ʒ���ұ���ʾ��ѡ�����Ʒ
 *  b.��Ʒ���ģ����ѯ��Ʒ c.��ѡ�����Ʒ�ṩ�޸�������ɾ������
 * 	d.ѡ�����ͨ����ģ�ͺͱ����ֵ���ݸ����ࡣ
 *  e.ͨ����̬����������Ʒ���ܼ۸�
 * @author LuckyJavaCi
 */
public class AddPurPlan extends JFrame {
	private JPanel mainPanel;// �ɹ��ƻ������
	private JPanel inforPanel;// ��ʾ�ֿ⹩���̺ͼƻ�����
	private JPanel funcPanl;// ������壬��ʾ��ť
	private JPanel bottomPanel;// �ײ����
	private JScrollPane goodSc;// ���������
	private JTextField supText;// ��Ӧ���ı���
	private JButton supFindBtn;// ���ҹ�Ӧ��
	private JTextField stoText;// �ֿ��ı���
	private JButton stoFindBtn;// ���Ҳֿ�
	private JTextField dateText;// �����ı���
	private JLabel orderNumber;// ������ű�ǩ
	private int purPlanOrder;// �������
	private String stoName;// �ֿ���
	private String supName;// ��Ӧ����
	private JButton addOldGoodsBtn;// �������Ʒ
	private JButton delOldGoodBtn;// ���"ɾ����Ʒ"��ť
	private JButton updateOldGoodBtn;// ���"�޸���Ʒ"��ť
	private DefaultTableModel planGoodModel;// �ƻ���Ʒ��ģ��
	private JTable planGoodTable;// �ƻ���Ʒ��
	private JScrollPane planGoodPane;// �ƻ���Ʒ�������
	static JTextField shouldPayText;// Ӧ������ı���
	JTextField truePayText;// ʵ������ı���
	private JComboBox empList;// �������б��
	private JTextField markText;// ��ע�б��
	private JButton confirmBtn;// ȷ����ť
	private JButton cancelBtn;// ȡ����ť
	private String[] title = { "��Ʒ���", "��Ʒ����", "��λ", "����С", "����", "����", "�ܽ��" };// ��ͷ
	static double priceCount = 0;// ����Ϊ��̬�������ڶ�����ڽ���ֻ�仯
	private JTextField numText;// �޸������ı���
	private int good_purPlanNum;// �ƻ��ɹ�����
	private double good_purPlanPrice;// ��Ʒ�Ĳɹ�����
	CommondMethods commondMethods = null;// ͨ�÷���
	Color mainColor = null;// ͨ����ɫ

	public AddPurPlan() {
		commondMethods = new CommondMethods();
		this.init();
		this.addPanel();
		this.elementListener();
		this.addTable();
		this.setVisible(true);
	}

	/*
	 * ��ʼ������
	 */
	public void init() {
		this.setTitle("�ɹ��ƻ�");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		// ���ô��ڲ��ɸı�
		this.setResizable(false);
	}

	// ������
	public void addPanel() {
		// ��������
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainColor = new Color(235, 235, 235);
		mainPanel.setBackground(mainColor);
		mainPanel.setBorder(BorderFactory.createEtchedBorder());

		// ��ӱ����ǩ
		JLabel titleLabel = commondMethods.createLabel("�ɹ��ƻ�", 330, 10, 150, 30, "����", 28);
		mainPanel.add(titleLabel);

		// ��Ӷ����ű�ǩ
		JLabel orderLabel = commondMethods.createLabel("������:", 500, 20, 150, 30, "����", 14);
		orderLabel.setForeground(Color.RED);
		mainPanel.add(orderLabel);

		// ��ʾ������
		StorageDao3 storage = new StorageDao3();
		purPlanOrder = storage.getMaxId();
		orderNumber = commondMethods.createLabel("" + purPlanOrder, 550, 20, 150, 30, "����", 14);
		orderNumber.setForeground(Color.RED);
		mainPanel.add(orderNumber);

		// �����Ϣ���
		inforPanel = commondMethods.createPanel(12, 50, 772, 72);
		Color inforColor = new Color(237, 242, 248);
		inforPanel.setBackground(inforColor);

		// ��ӹ����̱�ǩ
		JLabel supLabel = commondMethods.createLabel("��Ӧ��:", 40, 24, 100, 20, "����", 14);
		inforPanel.add(supLabel);

		// ��ӹ�Ӧ���ı���
		supText = commondMethods.createTextField("����Ͱ͹�Ӧ��", 90, 25, 100, 20, "����", 14, inforColor);
		MatteBorder bottomBorder = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		supText.setBorder(bottomBorder);
		supText.setHorizontalAlignment(JTextField.CENTER);
		inforPanel.add(supText);

		//��Ӳ��Ұ�ť
		supFindBtn = commondMethods.createButton("��ѯ", 195, 22, 60, 25, "����", 14);
		inforPanel.add(supFindBtn);
		
		// ����ջ��ֿ��ǩ
		JLabel inputStoLabel = commondMethods.createLabel("�ջ��ֿ�:", 270, 24, 100, 20, "����", 14);
		inforPanel.add(inputStoLabel);

		// ��Ӳֿ��ı���
		stoText = commondMethods.createTextField("���ֿ�", 340, 25, 100, 20, "����", 14, inforColor);
		stoText.setHorizontalAlignment(JTextField.CENTER);
		stoText.setBorder(bottomBorder);
		inforPanel.add(stoText);

		// ��Ӳ���ͼƬ��ť
		
		stoFindBtn = commondMethods.createButton("��ѯ", 445, 22, 60, 25, "����", 14);
		inforPanel.add(stoFindBtn);
		mainPanel.add(inforPanel);

		// ��Ӽƻ����ڱ�ǩ
		JLabel dateLabel = commondMethods.createLabel("�ƻ�����:", 510, 24, 100, 20, "����", 14);
		inforPanel.add(dateLabel);

		// ��������ı���
		dateText = commondMethods.createTextField("����ѡ������", 580, 25, 100, 20, "����", 14, inforColor);
		dateText.setBorder(bottomBorder);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(dateText);
		inforPanel.add(dateText);

		// ��ӹ������
		funcPanl = commondMethods.createPanel(12, 125, 772, 30);

		// ��ӡ��������Ʒ��Ʒ����ť
		addOldGoodsBtn = commondMethods.createButton("�������Ʒ", 30, 5, 100, 20, "����", 14);
		addOldGoodsBtn.setBackground(new Color(175, 208, 244));
		funcPanl.add(addOldGoodsBtn);
		mainPanel.add(funcPanl);

		// ���"ɾ����Ʒ"��ť
		delOldGoodBtn = commondMethods.createButton("ɾ����Ʒ", 450, 5, 100, 20, "����", 14);
		delOldGoodBtn.setBackground(new Color(175, 208, 244));
		funcPanl.add(delOldGoodBtn);
		mainPanel.add(funcPanl);

		// ���"�޸���Ʒ"��ť
		updateOldGoodBtn = commondMethods.createButton("�޸���Ʒ", 600, 5, 100, 20, "����", 14);
		updateOldGoodBtn.setBackground(new Color(175, 208, 244));
		funcPanl.add(updateOldGoodBtn);
		mainPanel.add(funcPanl);

		// ��ӵײ����
		bottomPanel = commondMethods.createPanel(12, 385, 772, 72);
		mainPanel.add(bottomPanel);

		// Ӧ������ǩ
		JLabel shouldPayLabel = commondMethods.createLabel("Ӧ�����", 30, 5, 150, 20, "����", 14);
		bottomPanel.add(shouldPayLabel);

		// Ӧ������ı���
		shouldPayText = commondMethods.createTextField("" + priceCount, 95, 10, 80, 14, "����", 14, inforColor);
		shouldPayText.setBorder(bottomBorder);
		shouldPayText.setHorizontalAlignment(JTextField.CENTER);
		bottomPanel.add(shouldPayText);
		// ʵ������ǩ
		JLabel truePayLabel = commondMethods.createLabel("ʵ�����", 190, 5, 80, 20, "����", 14);
		bottomPanel.add(truePayLabel);
		// ʵ������ı���
		truePayText = commondMethods.createTextField("", 253, 10, 80, 14, "����", 14, inforColor);
		truePayText.setBorder(bottomBorder);
		truePayText.setHorizontalAlignment(JTextField.CENTER);
		bottomPanel.add(truePayText);
		// �����˱�ǩ
		JLabel empLabel = commondMethods.createLabel("������:", 460, 10, 80, 14, "����", 14);
		bottomPanel.add(empLabel);
		// �������б�
		empList = commondMethods.createJComboBox(520, 6, 80, 20, "����", 14);
		EmpDao3 empDao = new EmpDao3();
		List<Employee> emp = empDao.query(3);
		for (int i = 0; i < emp.size(); i++) {
			empList.addItem(emp.get(i).getEmp_name());
		}
		bottomPanel.add(empList);

		// ��ӱ�ע��ǩ
		JLabel markLabel = commondMethods.createLabel("��ע", 30, 40, 150, 20, "����", 14);
		bottomPanel.add(markLabel);
		// ��ӱ�ע�ı���
		markText = commondMethods.createTextField("", 70, 40, 264, 20, "����", 14, inforColor);
		markText.setBorder(bottomBorder);
		bottomPanel.add(markText);
		// ȷ����ť
		confirmBtn = commondMethods.createButton("ȷ��", 460, 40, 100, 20, "����", 14);
		bottomPanel.add(confirmBtn);
		// ȡ����ť
		cancelBtn = commondMethods.createButton("ȡ��", 610, 40, 100, 20, "����", 14);
		bottomPanel.add(cancelBtn);
		this.add(mainPanel);
	}

	// ��ӱ�
	public void addTable() {
		// ʵ������ģ��
		planGoodModel = new DefaultTableModel(null, title);
		// ʵ������
		planGoodTable = new JTable(planGoodModel);
		// ���ñ�񲻿�˫���޸Ĳ��ҵ�Ԫ�����
		planGoodTable = new MTable(planGoodModel);
		// ��ӵ��������
		planGoodPane = new JScrollPane(planGoodTable);
		planGoodPane.setBounds(12, 160, 772, 220);
		mainPanel.add(planGoodPane);
		this.add(mainPanel);

		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(155,193,242);
			}

		}
		commondMethods.setColor(planGoodTable, color);
		
	}

	public void elementListener() {
		// ���ҹ�Ӧ��
		supFindBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SupFrame();
			}
		});
		// ���Ҳֿ�
		stoFindBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StoFrame();
			}
		});
		// ȡ������
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ɾ���ɹ��ƻ������
				PurPlanDao3 purPlanDao = new PurPlanDao3();
				int detailRow = purPlanDao.delPurPlanDetail(purPlanOrder);
				int purRow = purPlanDao.delPurPlan(purPlanOrder);
				if (detailRow > -1 && purRow > -1) {
					JOptionPane.showMessageDialog(null, "�ɹ��ƻ�ȡ���ɹ�");
				}
				AddPurPlan.this.dispose();
			}
		});
		// ����Ʒ���
		addOldGoodsBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new PlanAddOldGoods(planGoodTable, planGoodModel);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		delOldGoodBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ѡ����
				int row = planGoodTable.getSelectedRow();
				// ���ܽ�����
				priceCount = 0;
				if (row > -1) {
					planGoodModel.removeRow(row);
					// ��ȡ�е�����
					int mainFrameRows = planGoodModel.getRowCount();
					for (int i = 0; i < mainFrameRows; i++) {
						// ��ȡÿһ�е��ܽ��
						double rowPrice = (double) planGoodTable.getValueAt(i, 6);
						priceCount += rowPrice;
					}
					shouldPayText.setText("" + priceCount);
				} else {
					JOptionPane.showConfirmDialog(null, "��ѡ��һ����");
				}
			}
		});
		// �޸�����Ʒ����
		updateOldGoodBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ȡѡ�е���
				int row = planGoodTable.getSelectedRow();
				if (row > -1) {
					new updateNum();
				}
			}
		});
		// ȷ������
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �ж�ʵ�����ʱ���Ϊ��
				String truePay = truePayText.getText();

				if (("����ѡ������").equals(dateText.getText()) || dateText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "��ѡ������");
				} else if (truePay.equals("")) {
					JOptionPane.showMessageDialog(null, "������ʵ�����");
				} else {
					if (Double.parseDouble(truePay) < priceCount) {
						JOptionPane.showMessageDialog(null, "ʵ����������Ӧ�����");
					} else {
						// ��ȡ����������
						int maxRows = planGoodTable.getRowCount();
						if (maxRows == 0) {
							JOptionPane.showMessageDialog(null, "��ѡ����Ʒ");
						}
						int rows = 0;
						// �����е����ݴ����ά����
						for (int i = 0; i < maxRows; i++) {
							// �������󱣴�����
							PurPlanDetail purPlanDetail = new PurPlanDetail();
							purPlanDetail.setPlanDet_purId(purPlanOrder);// ������
							// ��ȡÿһ�е�����
							// ��ȡ��Ʒ���
							int gId = (int) planGoodTable.getValueAt(i, 0);
							purPlanDetail.setPlanDet_goodId(gId);
							// ��ȡ��Ʒ�Ĳɹ�����
							int gNum = (int) planGoodTable.getValueAt(i, 5);
							purPlanDetail.setPlanDet_number(gNum);
							// ��ȡÿ����Ʒ���ܽ��
							double gAllPrice = (double) planGoodTable.getValueAt(i, 6);
							purPlanDetail.setPlanDet_goodPrice(gAllPrice);
							// ����dao���������
							PurPlanDao3 purPlanDao = new PurPlanDao3();
							rows = purPlanDao.addPurPlanDetail(purPlanDetail);
						}
						if (rows > 0) {
							// �޸Ĳɹ��ƻ���Ϣ
							EmpDao3 EmpDao = new EmpDao3();
							String emp_name = empList.getSelectedItem().toString();
							int plan_empId = 0;
							try {
								PurPlanDao3 purPlanDao = new PurPlanDao3();
								String plan_mark = markText.getText();
								plan_empId = EmpDao.get(emp_name).getEmp_id();
								Date date = Date.valueOf(dateText.getText());
								int updatePlanData = purPlanDao.updatePlanData(date, plan_empId, plan_mark,
										purPlanOrder);
								JOptionPane.showMessageDialog(null,
										"�ɹ��ɹ����ɹ�����" + (Double.parseDouble(truePay) - priceCount) + "Ԫ");
							} catch (IllegalArgumentException argumentException) {
								JOptionPane.showMessageDialog(null, "����������ڲ���ȷ");
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							AddPurPlan.this.dispose();
							priceCount=0;
						}
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		new AddPurPlan();
	}

	/**
	 * ����:��ø��ĺ����Ʒ����
	 * 
	 * @author LuckyJavaCi
	 */
	class updateNum extends JFrame {
		JButton updateConfirm;// ȷ����ť
		JButton updateCancel;// ȡ����ť

		// ���췽��
		public updateNum() {
			this.init();
			this.addPanel();
			this.addListener();
			this.setVisible(true);
		}

		// ��ʼ��
		public void init() {
			this.setTitle("�޸�����");
			this.setSize(400, 185);
			this.setLayout(null);
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
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		// ������
		public void addPanel() {
			// ����������
			JPanel numPanel = commondMethods.createPanel(10, 10, 365, 80);
			numPanel.setBorder(BorderFactory.createEtchedBorder());
			this.add(numPanel);

			// ����޸ı�ǩ
			JLabel updateLabel = commondMethods.createLabel("�޸�����", 40, 30, 100, 30, "����", 20);
			numPanel.add(updateLabel);

			// ��������ı���
			numText = commondMethods.createTextField("", 160, 30, 100, 30, "����", 14, mainColor);
			numPanel.add(numText);
			// ��Ӱ�ť���
			JPanel btnPanel = commondMethods.createPanel(10, 100, 365, 40);
			// ��Ӱ�ť
			updateConfirm = commondMethods.createButton("ȷ��", 10, 10, 100, 20, "����", 14);
			updateCancel = commondMethods.createButton("ȡ��", 200, 10, 100, 20, "����", 14);
			btnPanel.add(updateConfirm);
			btnPanel.add(updateCancel);
			this.add(btnPanel);
		}

		// ʱ���������
		public void addListener() {
			updateConfirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						// �ƻ���Ʒ����
						good_purPlanNum = Integer.parseInt(numText.getText());
						int row = planGoodTable.getSelectedRow();
						// ���±�����
						planGoodModel.setValueAt(good_purPlanNum, row, 5);
						good_purPlanPrice = (double) planGoodTable.getValueAt(row, 4);
						planGoodModel.setValueAt(good_purPlanNum * good_purPlanPrice, row, 6);

						// ���ܽ�����
						priceCount = 0;
						// ��ȡ�е�����
						int mainFrameRows = planGoodModel.getRowCount();
						for (int i = 0; i < mainFrameRows; i++) {
							// ��ȡÿһ�е��ܽ��
							double rowPrice = (double) planGoodTable.getValueAt(i, 6);
							priceCount += rowPrice;
						}
						shouldPayText.setText("" + priceCount);
						updateNum.this.dispose();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ������");
					}

				}
			});
			// ȡ������
			updateCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateNum.this.dispose();
				}
			});
		}
	}

	/**
	 * ����:��ѯ���вֿ⣬����ѡ��ֿ���
	 * 
	 * @author LuckyJavaCi
	 */
	class StoFrame extends JFrame {
		private JPanel stoPanel;// �ֿ����
		private JScrollPane stoSc;// �������
		private DefaultTableModel stoModel;// ������ģ��
		private JTable stoTable;// ������

		/**
		 * ���췽��
		 */
		public StoFrame() {
			this.init();
			this.addpanel();
			setUndecorated(true);
			// this.btn();
			// ���ڿ��ӻ�
			this.setVisible(true);
		}

		/**
		 * ��ʼ��ҳ��
		 */
		public void init() {
			// ���ô��ڴ�С
			this.setSize(400, 150);
			// ���ô�����ʾλ��
			this.setLocationRelativeTo(getOwner());
			// �����޸Ĵ��ڵĴ�С
			this.setResizable(false);
			this.setTitle("�ֿ��ѯ");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		/**
		 * �������
		 */
		public void addpanel() {
			// ���������
			stoPanel = new JPanel();
			// �޲�������
			stoPanel.setLayout(null);

			// �������
			String[] str = { "�ֿ���", "�ֿ�����", "Ա�����", "�ֿ��ַ", "��ע" };
			// ��ȡ���ݿ�����
			StorageDao3 supd = new StorageDao3();
			List<Storage> listSto = supd.query();
			Object[][] rows = new Object[listSto.size()][];
			for (int i = 0; i < rows.length; i++) {
				// ��ȡ��Ӧ�̼��϶���
				Storage storage = listSto.get(i);
				// ������תΪ����洢
				Object[] obj = { storage.getSto_id(), storage.getName(), storage.getSto_empId(),
						storage.getSto_address(), storage.getSto_mark() };
				// ����ά���鸳ֵ
				rows[i] = obj;
			}
			stoModel = new DefaultTableModel(rows, str);
			stoTable = new JTable(stoModel);
			stoTable = new MTable(rows, str);
			stoTable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {
						int row = stoTable.getSelectedRow();
						int column = stoTable.getAutoResizeMode();
						stoTable.getValueAt(row, column);
						stoName = stoModel.getValueAt(row, 1).toString();
						stoText.setText(stoName);
						StoFrame.this.dispose();
					}
				}
			});

			stoSc = new JScrollPane(stoTable);
			stoSc.setBounds(0, 0, 400, 150);
//			stoPanel.add(stoSc);

			// ��ӽ�����
			this.add(stoSc);
		}

	}

	/**
	 * ����:��ѯ���й�Ӧ�̣�����ѡ��Ӧ������
	 * 
	 * @author LuckyJavaCi
	 */
	class SupFrame extends JFrame {
		private JPanel supPanel;// �ֿ����
		private JScrollPane supSc;// �������
		private DefaultTableModel supModel;// ������ģ��
		private JTable suptable;// ������

		/**
		 * ���췽��
		 */
		public SupFrame() {
			this.init();
			this.addpanel();
			setUndecorated(true);
			// this.btn();
			// ���ڿ��ӻ�
			this.setVisible(true);
		}

		/**
		 * ��ʼ��ҳ��
		 */
		public void init() {
			// ���ô��ڴ�С
			this.setSize(400, 250);
			// ���ô�����ʾλ��
			this.setLocationRelativeTo(getOwner());
			// �����޸Ĵ��ڵĴ�С
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		/**
		 * �������
		 */
		public void addpanel() {
			// ���������
			supPanel = new JPanel();
			// �޲�������
			supPanel.setLayout(null);

			// �������
			String[] str = { "��Ӧ�̱��", "��Ӧ������", "��Ӧ����ϵ�绰", "�ֿ��ַ" };
			// ��ȡ���ݿ�����
			StorageDao3 supd = new StorageDao3();
			List<Supply> listSto = supd.getSupply();
			Object[][] rows = new Object[listSto.size()][];
			for (int i = 0; i < rows.length; i++) {
				// ��ȡ��Ӧ�̼��϶���
				Supply supply = listSto.get(i);
				// ������תΪ����洢
				Object[] obj = { supply.getSup_id(), supply.getSup_name(), supply.getSup_phone(),
						supply.getSup_address() };
				// ����ά���鸳ֵ
				rows[i] = obj;
			}
			supModel = new DefaultTableModel(rows, str);
			suptable = new JTable(supModel);
			suptable = new MTable(rows, str);
			suptable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {
						int row = suptable.getSelectedRow();
						int column = suptable.getAutoResizeMode();
						suptable.getValueAt(row, column);
						supName = supModel.getValueAt(row, 1).toString();
						supText.setText(supName);
						SupFrame.this.dispose();
					}
				}
			});

			supSc = new JScrollPane(suptable);
			supSc.setBounds(0, 0, 400, 280);
			supPanel.add(supSc);

			// ��ӽ�����
			this.add(supPanel);
		}

	}

}
