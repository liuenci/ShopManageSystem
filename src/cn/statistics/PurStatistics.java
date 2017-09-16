package cn.statistics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.PurchaseDao3;
import cn.dao.storage.EmpDao3;
import cn.dao.supply.SupplyDao3;
import cn.liuenci.swing.DateChooser;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.model.common.PurchaseOrder;
import cn.view.purchase.MTable;

/**
 * 1.�ɹ�ͳ�ƽ��� 2.2017-08-16
 * 
 * @author LuckyJavaCi
 *
 */
public class PurStatistics extends JFrame {
	CommondMethods commondMethods = new CommondMethods();
	Color color = new Color(237, 242, 248);// ���ͨ����ɫ
	JTextField idText1;// ��������ı���
	JTextField dateText;// �ɹ������ı���
	JButton findBtn1;// ������Ų��Ұ�ť
	JButton findBtn2;// �ɹ����ڲ��Ұ�ť
	JButton findBtn3;// ��Ʒ��Ų��Ұ�ť
	JButton findBtn4;// ��Ʒ���Ʋ��Ұ�ť
	JButton exitBtn1;// �˳���ť
	JButton exitBtn2;// �˳���ť
	Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);// �߿��ް�ť
	DefaultTableModel purOrderModel;// �ɹ�������ģ��
	JTable purOrderTable;// �ɹ�������
	String[] purOrderTitle = { "�ɹ�������", "��Ӧ������", "�ɹ�����", "֧�����", "Ա������", "���״̬", "��ע" };
	JScrollPane purOrderJS;// �ɹ������������

	JTextField idText2;// ��Ʒ����ı���
	JTextField nameText;// ��Ʒ�����ı���
	DefaultTableModel purDetModel;// �ɹ���ϸ������ģ��
	JTable purDetTable;// �ɹ���ϸ������
	String[] purDetTitle = { "��Ʒ����", "��λ", "����ͺ�", "����", "�ܽ��" };
	JScrollPane purDetJS;// �ɹ��굥�������
	int rowCount = 0;
	double price = 0;
	JLabel orderCount = null;
	JLabel orderPrice = null;
	int purOrderNum = 0;
	int purDetNum = 0;

	/*
	 * ���췽��
	 */
	public PurStatistics() {
		init();
		addPanel();
		addMouseListener();
		setVisible(true);
	}

	/*
	 * ���ڳ�ʼ��
	 */
	public void init() {
		this.setTitle("�ɹ�ͳ��");
		this.setSize(850, 550);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		this.setResizable(false);
	}

	/*
	 * ������
	 */
	public void addPanel() {
		// ��Ӳɹ��������
		JPanel purOrderPanel = new JPanel();
		purOrderPanel.setLayout(null);
		purOrderPanel.setBounds(10, 10, 820, 500);
		purOrderPanel.setBorder(BorderFactory.createEtchedBorder());

		// ��Ӱ�ť���
		JPanel btnPanel1 = new JPanel();
		btnPanel1.setLayout(null);
		btnPanel1.setBackground(color);
		btnPanel1.setBounds(10, 10, 800, 70);
		btnPanel1.setBorder(BorderFactory.createEtchedBorder());
		purOrderPanel.add(btnPanel1);
		this.add(purOrderPanel);

		// ��Ʒ��ű�ǩ
		JLabel idLabel1 = commondMethods.createLabel("�������:", 30, 10, 100, 20, "����", 14);
		btnPanel1.add(idLabel1);

		// ��Ʒ����ı���
		idText1 = commondMethods.createTextField("", 100, 10, 100, 20, "����", 16, null);
		btnPanel1.add(idText1);
		// id���Ұ�ť
		findBtn1 = commondMethods.createButton("����", 200, 10, 60, 20, "����", 16);
		btnPanel1.add(findBtn1);

		// ��Ʒ��ű�ǩ
		JLabel dateLabel = commondMethods.createLabel("�ɹ�����:", 270, 10, 100, 20, "����", 16);
		btnPanel1.add(dateLabel);

		// �����ı���
		dateText = new JTextField("����ѡ������");
		dateText.setBounds(350, 10, 100, 20);
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(dateText);
		btnPanel1.add(dateText);
		// ���ڲ��Ұ�ť
		findBtn2 = commondMethods.createButton("����", 460, 10, 60, 20, "����", 16);
		btnPanel1.add(findBtn2);
		
		//ȫ����ѯ��ť
		JButton queryAllBtn = commondMethods.createButton("����ȫ������", 540, 10, 120, 20, "����", 16);
		btnPanel1.add(queryAllBtn);
		queryAllBtn.addActionListener(new ActionListener() {
			
			private Object[][] purOrderRows2;

			@Override
			public void actionPerformed(ActionEvent e) {
				//����±�
				purDetModel.setRowCount(0);
				// �����ݿ��ȡ����
				PurchaseDao3 purchaseDao1 = new PurchaseDao3();
				EmpDao3 empDao = new EmpDao3();
				SupplyDao3 supplyDao = new SupplyDao3();
				List<PurchaseOrder> listPurchase1 = purchaseDao1.queryAll();
				purOrderRows2 = new Object[listPurchase1.size()][];
				for (int i = 0; i < purOrderRows2.length; i++) {

					PurchaseOrder purchaseOrder = listPurchase1.get(i);
					String sup_name = null;
					String emp_name = null;
					String status = purchaseOrder.getPur_status() == 0 ? "δ���"
							: (purchaseOrder.getPur_status() == 1 ? "���ͨ��" : "���˻�");
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
					purOrderRows2[i] = obj;
				}
				purOrderModel.setDataVector(purOrderRows2, purOrderTitle);
				rowCount = listPurchase1.size();
				price  =0;
				orderCount.setText("��������:" + rowCount);
				for (int i = 0; i < rowCount; i++) {
					price += (double) purOrderTable.getValueAt(i, 3);
				}
				NumberFormat format=NumberFormat.getNumberInstance() ; 
				format.setMaximumFractionDigits(2); 
			    String s= format.format(price) ; 
				orderPrice.setText("�����ܽ��:" + s);
			}
		});

		// �˳���ť
		exitBtn1 = commondMethods.createButton("�˳�", 700, 10, 60, 20, "����", 16);
		btnPanel1.add(exitBtn1);
		
		//����Ϊexcel
		JButton exportPurOrderExcel = commondMethods.createButton("����������", 650, 40, 100, 20, "����", 16);
		btnPanel1.add(exportPurOrderExcel);
		
		exportPurOrderExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File("D:\\excel\\purOrderExcel"+purOrderNum+".xls");
				purOrderNum++;
				ExcelExporter excelExporter = new ExcelExporter();
				
				try {
					excelExporter.exportTable(purOrderTable, file);
					JOptionPane.showMessageDialog(null, "����Ϊ������ɹ�");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "����Ϊ������ʧ��");
					e1.printStackTrace();
				}
			}
		});
		JButton exportPurDetExcel = commondMethods.createButton("�����굥��",650, 270, 100, 20, "����", 16);
		purOrderPanel.add(exportPurDetExcel);
		exportPurDetExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File("D:\\excel\\purDetExcel"+purDetNum+".xls");
				purOrderNum++;
				ExcelExporter excelExporter = new ExcelExporter();
				
				try {
					excelExporter.exportTable(purDetTable, file);
					JOptionPane.showMessageDialog(null, "�����굥��ɹ�");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "�����굥��ʧ��");
					e1.printStackTrace();
				}
			}
		});

		// ע����ǩ
		JLabel noteLabel = commondMethods.createLabel("ע:��ͳ�����ݰ������вɹ����ݣ������ɹ��˻����ݡ�", 30, 45, 500, 20, "����", 14);
		noteLabel.setForeground(Color.RED);
		btnPanel1.add(noteLabel);

		// �������ǩ
		JLabel orderLabel = commondMethods.createLabel("������:", 10, 80, 100, 20, "����", 14);
		orderLabel.setForeground(Color.RED);
		purOrderPanel.add(orderLabel);

		// ����������ǩ
		JLabel DetailLabel = commondMethods.createLabel("���������:", 10, 280, 100, 20, "����", 14);
		DetailLabel.setForeground(Color.RED);
		purOrderPanel.add(DetailLabel);

		// �����յĶ�ά����
		Object[][] purOrderRows1 = new Object[0][];
		// ������ģ��
		purOrderModel = new DefaultTableModel(null, purOrderTitle);
		// ������
		purOrderTable = new JTable(purOrderModel);
		purOrderTable = new MTable(purOrderModel);
		purOrderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int row = purOrderTable.getSelectedRow();
					int orderId = (int) purOrderTable.getValueAt(row, 0);
					PurchaseDao3 purchaseDao = new PurchaseDao3();
					List<Good> listDet = purchaseDao.queryAllGoodByPurOrder(orderId);
					Object[][] det = new Object[listDet.size()][];
					for (int i = 0; i < listDet.size(); i++) {
						Good good = listDet.get(i);
						Object[] obj = { good.getGoods_name(), good.getGoods_units(), good.getGoods_size(),
								good.getGoods_number(), good.getGoods_purPrise() * good.getGoods_number() };
						det[i] = obj;
					}
					purDetModel.setDataVector(det, purDetTitle);
					
				}

			}
		});

		purOrderJS = new JScrollPane(purOrderTable);
		purOrderJS.setBounds(10, 100, 800, 150);
		purOrderPanel.add(purOrderJS);
		// �����Ʒ�����
		purDetModel = new DefaultTableModel(null, purDetTitle);
		// ������
		purDetTable = new JTable(purDetModel);
		purDetTable = new MTable(purDetModel);
		purDetJS = new JScrollPane(purDetTable);
		purDetJS.setBounds(10, 300, 800, 180);
		purOrderPanel.add(purDetJS);

		// �����ݿ��ȡ����
		PurchaseDao3 purchaseDao1 = new PurchaseDao3();
		EmpDao3 empDao = new EmpDao3();
		SupplyDao3 supplyDao = new SupplyDao3();
		List<PurchaseOrder> listPurchase1 = purchaseDao1.queryAll();
		purOrderRows1 = new Object[listPurchase1.size()][];
		for (int i = 0; i < purOrderRows1.length; i++) {

			PurchaseOrder purchaseOrder = listPurchase1.get(i);
			String sup_name = null;
			String emp_name = null;
			String status = purchaseOrder.getPur_status() == 0 ? "δ���"
					: (purchaseOrder.getPur_status() == 1 ? "���ͨ��" : "���˻�");
			try {
				sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
				emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// ������ת��������
			Object[] obj = { purchaseOrder.getPur_id(), sup_name, purchaseOrder.getPur_date(),
					purchaseOrder.getPur_pay(), emp_name, status, purchaseOrder.getPur_mark() };
			// ����ά���鸳ֵ
			purOrderRows1[i] = obj;
		}
		purOrderModel.setDataVector(purOrderRows1, purOrderTitle);
		rowCount = purOrderTable.getRowCount();
		orderCount = commondMethods.createLabel("��������:" + rowCount, 10, 250, 150, 20, "����", 14);
		orderCount.setForeground(Color.blue);
		purOrderPanel.add(orderCount);
		for (int i = 0; i < rowCount; i++) {
			price += (double) purOrderTable.getValueAt(i, 3);
		}
		NumberFormat format=NumberFormat.getNumberInstance() ; 
		format.setMaximumFractionDigits(2); 
	    String s= format.format(price) ; 
		orderPrice = commondMethods.createLabel("�����ܽ��:" + s, 350, 250, 250, 20, "����", 14);
		orderPrice.setForeground(Color.blue);
		purOrderPanel.add(orderPrice);
	}

	/*
	 * ��ť�ĵ���¼�
	 */
	public void addMouseListener() {
		findBtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				purDetModel.setRowCount(0);
				if (idText1.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "�����붩�����");
				} else {
					try {
						int pur_id = Integer.parseInt(idText1.getText());
						PurchaseDao3 purchaseDao = new PurchaseDao3();
						// ����list���ϱ���ɹ���������
						List<PurchaseOrder> listPurOrder = purchaseDao.queryById(pur_id);
						// ������ά����
						Object[][] purOrderRows = new Object[listPurOrder.size()][];
						SupplyDao3 supplyDao = new SupplyDao3();
						EmpDao3 empDao = new EmpDao3();
						price = 0;
						for (int i = 0; i < listPurOrder.size(); i++) {
							// �����ɹ���������
							PurchaseOrder purchaseOrder = listPurOrder.get(i);
							String sup_name = null;
							String emp_name = null;
							String status = purchaseOrder.getPur_status() == 0 ? "δ���"
									: (purchaseOrder.getPur_status() == 1 ? "���ͨ��" : "���˻�");
							try {
								sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
								emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							Object[] obj = { purchaseOrder.getPur_id(), sup_name, purchaseOrder.getPur_date(),
									purchaseOrder.getPur_pay(), emp_name, status, purchaseOrder.getPur_mark() };
							purOrderRows[i] = obj;
							price += purchaseOrder.getPur_pay();
						}
						purOrderModel.setDataVector(purOrderRows, purOrderTitle);
						rowCount = listPurOrder.size();
						orderCount.setText("��������:" + rowCount);
						NumberFormat format=NumberFormat.getNumberInstance() ; 
						format.setMaximumFractionDigits(2); 
					    String s= format.format(price) ; 
						orderPrice.setText("�����ܽ��:" + s);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ������");
					}
				}

			}
		});
		findBtn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				purDetModel.setRowCount(0);
				if (dateText.getText().equals("����ѡ������")) {
					JOptionPane.showMessageDialog(null, "��ѡ������");
				} else {
					try {
						Date pur_date = Date.valueOf(dateText.getText());
						PurchaseDao3 purchaseDao = new PurchaseDao3();
						// ����list���ϱ���ɹ���������
						List<PurchaseOrder> listPurOrder = purchaseDao.queryAllByDate(pur_date);
						// ������ά����
						Object[][] purOrderRows = new Object[listPurOrder.size()][];
						SupplyDao3 supplyDao = new SupplyDao3();
						EmpDao3 empDao = new EmpDao3();
						price = 0;
						for (int i = 0; i < listPurOrder.size(); i++) {
							// �����ɹ���������
							PurchaseOrder purchaseOrder = listPurOrder.get(i);
							String sup_name = null;
							String emp_name = null;
							String status = purchaseOrder.getPur_status() == 0 ? "δ���"
									: (purchaseOrder.getPur_status() == 1 ? "���ͨ��" : "���˻�");
							try {
								sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
								emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							Object[] obj = { purchaseOrder.getPur_id(), sup_name, purchaseOrder.getPur_date(),
									purchaseOrder.getPur_pay(), emp_name, status, purchaseOrder.getPur_mark() };
							purOrderRows[i] = obj;
							price += purchaseOrder.getPur_pay();
						}
						purOrderModel.setDataVector(purOrderRows, purOrderTitle);
						rowCount = listPurOrder.size();
						orderCount.setText("��������:" + rowCount);
						NumberFormat format=NumberFormat.getNumberInstance() ; 
						format.setMaximumFractionDigits(2); 
					    String s= format.format(price) ; 
						orderPrice.setText("�����ܽ��:" + s);
					} catch (IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ������");
					}

				}

			}
		});
		exitBtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PurStatistics.this.dispose();
			}
		});

	}

	public static void main(String[] args) {
		new PurStatistics();
	}

}
