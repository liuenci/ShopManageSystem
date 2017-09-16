package cn.storage;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.PurchaseDao3;
import cn.dao.storage.CheckPurOrderDao3;
import cn.dao.storage.EmpDao3;
import cn.dao.storage.StoTransDao3;
import cn.dao.supply.SupplyDao3;
import cn.liuenci.swing.DateChooser;
import cn.method.CommondMethods;
import cn.model.common.PurchaseOrder;
import cn.model.storage.NewPurDetail;
import cn.view.purchase.MTable;

public class CheckPurOrder extends JFrame {
	CommondMethods commondMethods = null;// ͨ�÷���
	JTextArea markText = null;
	JButton queryAllBtn;
	JButton queryByIdBtn;
	JButton queryByDateBtn;
	JPanel mainPanel;

	JTextField idText = null;
	JTextField dateText = null;

	JScrollPane purchaseSc = null;
	DefaultTableModel purchaseModel = null;
	JTable purchaseTable = null;
	int pDet_id = 0;

	// �������
	String purOrderId;

	// ���췽��
	public CheckPurOrder() {
		commondMethods = new CommondMethods();
		init();
		addPanel();
		addTable();
		addRegisterLisetener();
		setVisible(true);
	}

	// ��ʼ��
	public void init() {
		setTitle("��˲ɹ�����");
		setSize(800, 600);
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
		this.setResizable(false);
	}

	// ������
	public void addPanel() {
		// ��������
		mainPanel = commondMethods.createPanel(5, 5, 772, 550);
		mainPanel.setBackground(null);

		// �����������
		JPanel namePanel = commondMethods.createPanel(10, 10, 755, 70);

		// �������Ʊ�ǩ
		JLabel nameLabel = commondMethods.createLabel("�ɹ�����", 330, 24, 140, 20, "����", 30);
		namePanel.add(nameLabel);
		mainPanel.add(namePanel);

		// ���ܰ�ť���
		JPanel btnPanel = commondMethods.createPanel(10, 90, 755, 50);
		mainPanel.add(btnPanel);
		this.add(mainPanel);

		// ��Ӱ�ť
		queryAllBtn = commondMethods.createButton("��ѯ���ж���", 30, 10, 100, 25, "����", 14);
		btnPanel.add(queryAllBtn);
		queryByIdBtn = commondMethods.createButton("�ɹ���Ų�ѯ", 330, 10, 100, 25, "����", 14);
		btnPanel.add(queryByIdBtn);
		queryByDateBtn = commondMethods.createButton("�ɹ����ڲ�ѯ", 620, 10, 100, 25, "����", 14);
		btnPanel.add(queryByDateBtn);

		// ����ı���
		idText = commondMethods.createTextField("", 170, 10, 150, 25, "����", 20, new Color(237, 242, 248));
		btnPanel.add(idText);
		dateText = commondMethods.createTextField("����ѡ������", 458, 10, 150, 25, "����", 16, new Color(237, 242, 248));
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(dateText);
		btnPanel.add(dateText);
		
		//��ӵײ����
		JPanel bottomPanel = commondMethods.createPanel(10, 476, 755, 60);
		mainPanel.add(bottomPanel);
		//����ȷ����ť
		JButton goButton = commondMethods.createButton("ȷ��", 510, 15, 60, 25, "����", 14);
		bottomPanel.add(goButton);
		
		goButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = purchaseTable.getSelectedRow();
				if(row!=-1) {
					purOrderId = purchaseModel.getValueAt(row, 0).toString();
					new purDetailInfor();
				}else {
					JOptionPane.showMessageDialog(null, "�뵥��ѡ��һ��");
				}
			}
		});
		//����ȡ����ť
		JButton cancelBtn = commondMethods.createButton("ȡ��", 610, 15, 60, 25, "����", 14);
		bottomPanel.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckPurOrder.this.dispose();
			}
		});

	}

	// ��ӱ�
	public void addTable() {
		// ������ͷ
		String[] title = { "�ɹ�������", "��Ӧ������", "�ɹ�����", "֧���ܽ��", "Ա������", "���״̬", "��ע" };
		// �����ݿ��ȡ��Ʒ��Ϣ
		PurchaseDao3 purchaseDao = new PurchaseDao3();
		List<PurchaseOrder> listPurchase = purchaseDao.query();
		Object[][] rows = new Object[listPurchase.size()][];
		SupplyDao3 supplyDao = new SupplyDao3();
		EmpDao3 empDao = new EmpDao3();
		for (int i = 0; i < rows.length; i++) {

			PurchaseOrder purchaseOrder = listPurchase.get(i);
			String sup_name = null;
			String emp_name = null;
			String status = purchaseOrder.getPur_status() == 1 ? "���ͨ��"
					: (purchaseOrder.getPur_status() == 2 ? "���δͨ��" : "δ���");
			try {
				sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
				emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// ������ת��������
			Object[] obj = { purchaseOrder.getPur_id(), sup_name, purchaseOrder.getPur_date(),
					purchaseOrder.getPur_pay(), emp_name, status, purchaseOrder.getPur_mark() };
			// ����ά���鸳ֵ
			rows[i] = obj;
		}
		purchaseModel = new DefaultTableModel(rows, title);
		purchaseTable = new JTable(purchaseModel);
		purchaseTable = new MTable(purchaseModel);
		// ���ɹ���������ӵ���¼�
		purchaseTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = purchaseTable.getSelectedRow();
					purOrderId = purchaseModel.getValueAt(row, 0).toString();
					new purDetailInfor();
				}
			}
		});
		purchaseSc = new JScrollPane(purchaseTable);
		purchaseSc.setBounds(10, 145, 755, 320);
		mainPanel.add(purchaseSc);
		
		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(155,193,242);
			}

		}
		commondMethods.setColor(purchaseTable, color);

	}

	public void addRegisterLisetener() {
		queryAllBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ������ͷ
				String[] title = { "�ɹ�������", "��Ӧ������", "�ɹ�����", "֧���ܽ��", "Ա������", "���״̬", "��ע" };
				// �����ݿ��ȡ��Ʒ��Ϣ
				PurchaseDao3 purchaseDao = new PurchaseDao3();
				List<PurchaseOrder> listPurchase = purchaseDao.query();
				Object[][] rows = new Object[listPurchase.size()][];
				SupplyDao3 supplyDao = new SupplyDao3();
				EmpDao3 empDao = new EmpDao3();
				for (int i = 0; i < rows.length; i++) {
					PurchaseOrder purchaseOrder = listPurchase.get(i);
					String sup_name = null;
					String emp_name = null;
					String status = purchaseOrder.getPur_status() == 1 ? "���ͨ��"
							: (purchaseOrder.getPur_status() == 2 ? "���δͨ��" : "δ���");
					try {
						sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
						emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					// ������ת��������
					Object[] obj = { purchaseOrder.getPur_id(), sup_name, purchaseOrder.getPur_date(),
							purchaseOrder.getPur_pay(), emp_name, status, purchaseOrder.getPur_mark() };
					// ����ά���鸳ֵ
					rows[i] = obj;
				}
				purchaseModel.setDataVector(rows, title);
				// ���ɹ���������ӵ���¼�
				Color[] color = new Color[100];
				for (int i = 0; i < 100; i++) {
					int num = i % 2;
					if (num == 0) {
						color[i] = Color.WHITE;
					} else {
						color[i] = new Color(155,193,242);
					}

				}
				commondMethods.setColor(purchaseTable, color);
			}
		});

		queryByIdBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 0;

				if (idText.getText() != null) {
					try {
						id = Integer.parseInt(idText.getText());
					} catch (NumberFormatException numberFormatException) {
						JOptionPane.showMessageDialog(null, "��������ȷ�ı��");
					}

					// �������
					String[] title = { "�ɹ�������", "��Ӧ������", "�ɹ�����", "֧���ܽ��", "Ա������", "���״̬", "��ע" };
					// �����ݿ��л�ȡ��Ʒ��Ϣ
					PurchaseDao3 purchaseDao = new PurchaseDao3();
					List<PurchaseOrder> listPurchase = purchaseDao.queryByorderId(id);
					Object[][] rows = new Object[listPurchase.size()][];
					SupplyDao3 supplyDao = new SupplyDao3();
					EmpDao3 empDao = new EmpDao3();
					for (int i = 0; i < rows.length; i++) {
						PurchaseOrder purchaseOrder = listPurchase.get(i);
						String sup_name = null;
						String emp_name = null;
						String status = purchaseOrder.getPur_status() == 1 ? "���ͨ��"
								: (purchaseOrder.getPur_status() == 2 ? "���δͨ��" : "δ���");
						try {
							sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
							emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						// ������ת��Ϊ����
						Object[] obj = { purchaseOrder.getPur_id(), sup_name, purchaseOrder.getPur_date(),
								purchaseOrder.getPur_pay(), emp_name, status, purchaseOrder.getPur_mark() };
						rows[i] = obj;
					}
					purchaseModel.setDataVector(rows, title);
					Color[] color = new Color[100];
					for (int i = 0; i < 100; i++) {
						int num = i % 2;
						if (num == 0) {
							color[i] = Color.WHITE;
						} else {
							color[i] = new Color(155,193,242);
						}

					}
					commondMethods.setColor(purchaseTable, color);
				}
			}
		});
		queryByDateBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date date = null;
				if (dateText.getText() != null) {
					try {
						date = Date.valueOf(dateText.getText());
						// �������
						String[] title = { "�ɹ�������", "��Ӧ������", "�ɹ�����", "֧���ܽ��", "Ա������", "���״̬", "��ע" };
						// �����ݿ��л�ȡ��Ʒ��Ϣ
						PurchaseDao3 purchaseDao = new PurchaseDao3();
						List<PurchaseOrder> listPur = purchaseDao.queryByDate(date);
						Object[][] rows = new Object[listPur.size()][];
						SupplyDao3 supplyDao = new SupplyDao3();
						EmpDao3 empDao = new EmpDao3();
						for (int i = 0; i < rows.length; i++) {
							PurchaseOrder purchaseOrder = listPur.get(i);
							String sup_name = null;
							String emp_name = null;
							String status = purchaseOrder.getPur_status() == 1 ? "���ͨ��"
									: (purchaseOrder.getPur_status() == 2 ? "���δͨ��" : "δ���");
							sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
							emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
							// ������ת��Ϊ����
							Object[] obj = { purchaseOrder.getPur_id(), sup_name, purchaseOrder.getPur_date(),
									purchaseOrder.getPur_pay(), emp_name, status, purchaseOrder.getPur_mark() };
							rows[i] = obj;
						}
						purchaseModel.setDataVector(rows, title);
						Color[] color = new Color[100];
						for (int i = 0; i < 100; i++) {
							int num = i % 2;
							if (num == 0) {
								color[i] = Color.WHITE;
							} else {
								color[i] = new Color(155,193,242);
							}

						}
						commondMethods.setColor(purchaseTable, color);
					} catch (IllegalArgumentException illegalArgumentException) {
						JOptionPane.showMessageDialog(null, "��ѡ����ȷ������");
					}

				}
			}
		});
	}

	public static void main(String[] args) {
		new CheckPurOrder();
	}

	class purDetailInfor extends JFrame {
		// �����
		JPanel mainPanel;
		// �������
		JPanel namePanel;
		// ��ѯ���
		JPanel findPanel;
		// ��Ӳɹ�������ǩ
		JLabel purOrderLabel;
		// ��Ӳ�ѯ��ť
		JButton findBtn;
		// �������
		JScrollPane goodDetSc;
		// ������ģ��
		DefaultTableModel goodDetModel;
		// ����������
		JTable goodDetTable;
		JTextField findText = null;
		JButton successBtn = null;
		// �����ײ����
		JPanel purBPanel = null;
		// ����˳���ť
		JButton failBtn = null;
		JPanel markPanel = null;// ��ע���

		// ���췽��
		public purDetailInfor() {
			init();
			addPanel();
			addTable();
			addMouseListerer();
			setVisible(true);
		}

		// ��ʼ��
		public void init() {
			// ���ô��ڴ�С
			this.setSize(800, 600);
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
			this.setLayout(null);
			this.setTitle("�ɹ���������");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		// ������
		public void addPanel() {
			// ��������
			mainPanel = commondMethods.createPanel(5, 5, 785, 550);
			mainPanel.setLayout(null);

			// ����������
			namePanel = commondMethods.createPanel(5, 5, 775, 50);
			// ������Ʊ�ǩ
			JLabel nameLabel = commondMethods.createLabel("�ɹ����������", 325, 10, 200, 20, "����", 24);
			namePanel.add(nameLabel);
			mainPanel.add(namePanel);

			// ��Ӳ�ѯ���
			findPanel = commondMethods.createPanel(5, 60, 775, 40);
			// ��Ӳɹ�������ǩ
			purOrderLabel = commondMethods.createLabel("�ɹ�������:", 50, 10, 100, 20, "����", 16);
			// ��ʾ������
			JLabel orderLabel = commondMethods.createLabel("" + purOrderId, 150, 10, 100, 20, "����", 16);
			

			// ������ע���
			markPanel = commondMethods.createPanel(5, 340, 775, 120);
			mainPanel.add(markPanel);
			// ������ע��ǩ
			JLabel markLabel = commondMethods.createLabel("��ע:", 10, 10, 100, 20, "����", 16);
			markPanel.add(markLabel);
			// ������ע�ı���
			markText = commondMethods.createTextArea("", 60, 10, 700, 90, "����", 16, new Color(237, 242, 248));
			markPanel.add(markText);
			// �����ײ����
			purBPanel = commondMethods.createPanel(5, 470, 775, 60);
			// �����˰�ť
			successBtn = commondMethods.createButton("���ͨ��", 400, 10, 100, 30, "����", 16);
			purBPanel.add(successBtn);
			// ����˳���ť
			failBtn = commondMethods.createButton("��˲�ͨ��", 550, 10, 100, 30, "����", 16);
			purBPanel.add(failBtn);
			// ���ײ������ӵ������
			purBPanel.add(successBtn);
			findPanel.add(orderLabel);
			findPanel.add(purOrderLabel);
			mainPanel.add(findPanel);
			mainPanel.add(purBPanel);
			this.add(mainPanel);
		}

		// ��ӱ�
		public void addTable() {
			// ������ͷ
			String[] title = { "���", "��Ʒ���", "��Ʒ����", "��λ", "����С", "��Ʒ����", "��Ʒ����", "�����ܽ��", "�ɹ�״̬", "��ע" };

			// �����ݿ��ȡ����
			CheckPurOrderDao3 checkPurOrderDao = new CheckPurOrderDao3();
			List<NewPurDetail> listPurDetail = checkPurOrderDao.query(Integer.parseInt(purOrderId));
			Object[][] goodRows = new Object[listPurDetail.size()][];
			for (int i = 0; i < goodRows.length; i++) {
				// ��������洢����
				NewPurDetail newPurDetail = listPurDetail.get(i);
				newPurDetail.setGoodAllPrice(newPurDetail.getGoodPurNum() * newPurDetail.getGoodPurPrice());

				String status = newPurDetail.getGoodStatus() == 0 ? "�����" : "δ���";
				pDet_id = newPurDetail.getpDet_id();
				// ������ת��Ϊ����
				Object[] obj = { pDet_id, newPurDetail.getGoodId(), newPurDetail.getGoodName(),
						newPurDetail.getGoodUnits(), newPurDetail.getGoodSize(), newPurDetail.getGoodPurPrice(),
						newPurDetail.getGoodPurNum(), newPurDetail.getGoodAllPrice(), status,
						newPurDetail.getGoodPurMark() };
				// ����ά���鸳ֵ
				goodRows[i] = obj;
			}

			// ������ģ��
			goodDetModel = new DefaultTableModel(goodRows, title);
			// ������
			goodDetTable = new JTable(goodDetModel);
			// ���þ���
			goodDetTable = new MTable(goodRows, title);
			// �������ӵ��������
			// �����������
			goodDetSc = new JScrollPane(goodDetTable);
			goodDetSc.setBounds(5, 120, 775, 200);
			mainPanel.add(goodDetSc);
			
			Color[] color = new Color[100];
			for (int i = 0; i < 100; i++) {
				int num = i % 2;
				if (num == 0) {
					color[i] = Color.WHITE;
				} else {
					color[i] = new Color(155,193,242);
				}

			}
			commondMethods.setColor(goodDetTable, color);
		}

		// ����ť��ӵ���¼�
		public void addMouseListerer() {
			failBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CheckPurOrderDao3 checkPurOrderDao = new CheckPurOrderDao3();
					if (markText.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "��ע����Ϊ��");
					} else {
						checkPurOrderDao.updatePurOrderIdto2(Integer.parseInt(purOrderId),markText.getText().trim());
						queryAllBtn.doClick();
						JOptionPane.showMessageDialog(null, "��˲�ͨ��");
						purDetailInfor.this.dispose();
					}

				}
			});
			successBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CheckPurOrderDao3 checkPurOrderDao = new CheckPurOrderDao3();
					checkPurOrderDao.updatePurOrderIdto1(Integer.parseInt(purOrderId));
					// ��ȡ�е�����
					int goodRows = goodDetTable.getRowCount();
					// ����Dao����
					StoTransDao3 stoTransDao = new StoTransDao3();
					for (int i = 0; i < goodRows; i++) {
						pDet_id = (int) goodDetTable.getValueAt(i, 0);
						// ��ȡÿһ�е���Ʒ���
						int goodId = (int) goodDetTable.getValueAt(i, 1);
						// ��ȡÿһ�е���Ʒ����
						int goodNum = (int) goodDetTable.getValueAt(i, 6);
						// ����dao�����ݸ��·���
						stoTransDao.updateGood(goodId, goodNum, pDet_id);
					}
					queryAllBtn.doClick();
					JOptionPane.showMessageDialog(null, "��˳ɹ�");
					purDetailInfor.this.dispose();
				}
			});
		}
	}

}
