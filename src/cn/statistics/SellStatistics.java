package cn.statistics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
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
import javax.swing.ImageIcon;
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

import cn.dao.sell.SellDao3;
import cn.dao.storage.EmpDao3;
import cn.liuenci.swing.DateChooser;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.model.common.SellOrder;
import cn.view.purchase.MTable;

/**
 * 1.����ͳ�ƽ��� 2.2017-08-17
 * 
 * @author LuckyJavaCi
 *
 */
public class SellStatistics extends JFrame {
	CommondMethods commondMethods = new CommondMethods();
	Color color = new Color(237, 242, 248);// ���ͨ����ɫ
	JTextField idText1;// ��������ı���
	JTextField dateText;// ���������ı���
	JButton findBtn1;// ������Ų��Ұ�ť
	JButton findBtn2;// �������ڲ��Ұ�ť
	JButton findBtn3;// ��Ʒ��Ų��Ұ�ť
	JButton findBtn4;// ��Ʒ���Ʋ��Ұ�ť
	JButton exitBtn1;// �˳���ť
	JButton exitBtn2;// �˳���ť
	Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);// �߿��ް�ť
	DefaultTableModel sellOrderModel;// ���۶�����ģ��
	JTable sellOrderTable;// ���۶�����
	String[] sellOrderTitle = { "���۶�����", "�ɹ�����", "Ա������", "���۽��", "��ע" };
	JScrollPane sellOrderJS;// ���۶����������
	JTextField idText2;// ��Ʒ����ı���
	JTextField nameText;// ��Ʒ�����ı���
	DefaultTableModel sellDetModel;// ������ϸ������ģ��
	JTable sellDetTable;// ������ϸ������
	String[] sellDetTitle = { "��Ʒ����", "��λ", "����ͺ�", "����", "�ܽ��" };
	JScrollPane sellDetJS;// �����굥�������
	double price = 0;
	JLabel orderPrice =null;
	JLabel orderCount =null;
	int sellOrderNum = 0;
	int sellDetNum = 0;
	/*
	 * ���췽��
	 */

	public SellStatistics() {
		init();
		addPanel();
		addMouseListener();
		setVisible(true);
	}

	/*
	 * ���ڳ�ʼ��
	 */
	public void init() {
		this.setTitle("����ͳ��");
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
		JPanel sellOrderPanel = new JPanel();
		sellOrderPanel.setLayout(null);
		sellOrderPanel.setBounds(10, 10, 820, 500);
		sellOrderPanel.setBorder(BorderFactory.createEtchedBorder());

		// ��Ӱ�ť���
		JPanel btnPanel1 = new JPanel();
		btnPanel1.setLayout(null);
		btnPanel1.setBackground(color);
		btnPanel1.setBounds(10, 10, 800, 70);
		btnPanel1.setBorder(BorderFactory.createEtchedBorder());
		sellOrderPanel.add(btnPanel1);
		this.add(sellOrderPanel);


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

		// ȫ����ѯ��ť
		JButton queryAllBtn = commondMethods.createButton("����ȫ������", 540, 10, 120, 20, "����", 16);
		btnPanel1.add(queryAllBtn);
		queryAllBtn.addActionListener(new ActionListener() {
			
			private Object[][] sellOrderRows1;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// �����ݿ��ȡ����
				SellDao3 sellDao = new SellDao3();
				List<SellOrder> listSellOrder = sellDao.query();
				sellOrderRows1 = new Object[listSellOrder.size()][];
				price = 0;
				for (int i = 0; i < sellOrderRows1.length; i++) {
					SellOrder sellOrder = listSellOrder.get(i);
					String empName = null;
					try {
						empName = sellDao.get(sellOrder.getSell_empId()).getEmp_name();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					// ������ת��������
					Object[] obj = { sellOrder.getSell_id(), sellOrder.getSell_date(), empName, sellOrder.getSell_profit(),
							sellOrder.getSell_mark() };
					// ����ά���鸳ֵ
					sellOrderRows1[i] = obj;
					price += sellOrder.getSell_profit();
				}
				sellOrderModel.setDataVector(sellOrderRows1, sellOrderTitle);
				int rowsCount = listSellOrder.size();
				orderCount.setText("��������:"+rowsCount);
				NumberFormat format=NumberFormat.getNumberInstance() ; 
				format.setMaximumFractionDigits(2); 
			    String s= format.format(price) ; 
				orderPrice.setText("�����ܽ��:" + s);
			}
		});
 
		// �˳���ť
		exitBtn1 = commondMethods.createButton("�˳�", 700, 10, 60, 20, "����", 16);
		btnPanel1.add(exitBtn1);
		// ע����ǩ
		JLabel noteLabel = commondMethods.createLabel("ע:��ͳ�����ݰ��������������ݣ����������˻����ݡ�", 30, 45, 500, 20, "����", 14);
		noteLabel.setForeground(Color.RED);
		btnPanel1.add(noteLabel);
		
		
		//����Ϊexcel
				JButton exportPurOrderExcel = commondMethods.createButton("����������", 650, 40, 100, 20, "����", 16);
				btnPanel1.add(exportPurOrderExcel);
				
				exportPurOrderExcel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						File file = new File("D:\\excel1\\sellOrderExcel"+sellOrderNum+".xls");
						sellOrderNum++;
						ExcelExporter excelExporter = new ExcelExporter();
						
						try {
							excelExporter.exportTable(sellOrderTable, file);
							JOptionPane.showMessageDialog(null, "����Ϊ������ɹ�");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "����Ϊ������ʧ��");
							e1.printStackTrace();
						}
					}
				});
				JButton exportPurDetExcel = commondMethods.createButton("�����굥��",650, 270, 100, 20, "����", 16);
				sellOrderPanel.add(exportPurDetExcel);
				exportPurDetExcel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						File file = new File("D:\\excel1\\sellDetExcel"+sellDetNum+".xls");
						sellOrderNum++;
						ExcelExporter excelExporter = new ExcelExporter();
						try {
							excelExporter.exportTable(sellDetTable, file);
							JOptionPane.showMessageDialog(null, "����Ϊ���ɹ�");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "�����굥��ʧ��");
							e1.printStackTrace();
						}
					}
				});

		// �������ǩ
		JLabel orderLabel = commondMethods.createLabel("������:", 10, 80, 100, 20, "����", 14);
		orderLabel.setForeground(Color.RED);
		sellOrderPanel.add(orderLabel);
		// ����������ǩ
		JLabel DetailLabel = commondMethods.createLabel("���������:", 10, 280, 100, 20, "����", 14);
		DetailLabel.setForeground(Color.RED);
		sellOrderPanel.add(DetailLabel);
		// �����յĶ�ά����
		Object[][] sellOrderRows = new Object[0][];
		// ������ģ��
		sellOrderModel = new DefaultTableModel(sellOrderRows, sellOrderTitle);
		// ������
		sellOrderTable = new JTable(sellOrderModel);
		sellOrderTable = new MTable(sellOrderModel);
		sellOrderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int row = sellOrderTable.getSelectedRow();
					int orderId = (int) sellOrderTable.getValueAt(row, 0);
					SellDao3 sellDao = new SellDao3();
					List<Good> listDet = sellDao.queryAllGoodBySellOrder(orderId);
					Object[][] det = new Object[listDet.size()][];
					for (int i = 0; i < listDet.size(); i++) {
						Good good = listDet.get(i);
						Object[] obj = { good.getGoods_name(), good.getGoods_units(), good.getGoods_size(),
								good.getGoods_number(), good.getGoods_sellPrice() * good.getGoods_number() };
						det[i] = obj;
					}
					sellDetModel.setDataVector(det, sellDetTitle);
				}

			}
		});
		sellOrderJS = new JScrollPane(sellOrderTable);
		sellOrderJS.setBounds(10, 100, 800, 150);
		sellOrderPanel.add(sellOrderJS);

		// �����ݿ��ȡ���� **
		SellDao3 sellDao = new SellDao3();
		List<SellOrder> listSellOrder = sellDao.query();
		sellOrderRows = new Object[listSellOrder.size()][];
		for (int i = 0; i < sellOrderRows.length; i++) {
			SellOrder sellOrder = listSellOrder.get(i);
			String empName = null;
			try {
				empName = sellDao.get(sellOrder.getSell_empId()).getEmp_name();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// ������ת��������
			Object[] obj = { sellOrder.getSell_id(), sellOrder.getSell_date(), empName, sellOrder.getSell_profit(),
					sellOrder.getSell_mark() };
			// ����ά���鸳ֵ
			sellOrderRows[i] = obj;
		}
		sellOrderModel.setDataVector(sellOrderRows, sellOrderTitle);

		// �����굥��
		sellDetModel = new DefaultTableModel(null, sellDetTitle);
		// ������
		sellDetTable = new JTable(sellDetModel);
		sellDetTable = new MTable(sellDetModel);
		sellDetJS = new JScrollPane(sellDetTable);
		sellDetJS.setBounds(10, 300, 800, 180);
		sellOrderPanel.add(sellDetJS);
		orderCount = commondMethods.createLabel("��������:" + sellOrderTable.getRowCount(), 10, 250, 100, 20, "����",
				14);
		orderCount.setForeground(Color.blue);
		sellOrderPanel.add(orderCount);
		for (int i = 0; i < sellOrderTable.getRowCount(); i++) {
			price += (double) sellOrderTable.getValueAt(i, 3);
		}
		NumberFormat format=NumberFormat.getNumberInstance() ; 
		format.setMaximumFractionDigits(2); 
	    String s= format.format(price) ; 
		orderPrice = commondMethods.createLabel("�����ܽ��:" + s, 500, 250, 150, 20, "����", 14);
		orderPrice.setForeground(Color.blue);
		sellOrderPanel.add(orderPrice);
	}

	/*
	 * ����ͼƬ��ť����
	 */
	public void setBtnImages(JButton button, String path, int width, int height) {
		ImageIcon icon = new ImageIcon(path);
		Image topImage = icon.getImage();
		int topWidth = width;
		int topHeight = height;
		topImage = topImage.getScaledInstance(topWidth, topHeight, Image.SCALE_DEFAULT);
		icon.setImage(topImage);
		button.setIcon(icon);
		button.setOpaque(false);
	}

	/*
	 * ��ť�ĵ���¼�
	 */
	public void addMouseListener() {
		findBtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (idText1.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "�����붩�����");
				} else {
					try {
						int sell_id = Integer.parseInt(idText1.getText());
						SellDao3 sellDao = new SellDao3();
						// ����list���ϱ���ɹ���������
						List<SellOrder> listSellOrder = sellDao.queryById(sell_id);
						EmpDao3 empDao = new EmpDao3();
						// ������ά����
						Object[][] sellOrderRows = new Object[listSellOrder.size()][];
						price = 0;
						for (int i = 0; i < listSellOrder.size(); i++) {
							// �����ɹ���������
							SellOrder sellOrder = listSellOrder.get(i);
							String empName = empDao.get(sellOrder.getSell_empId()).getEmp_name();
							Object[] obj = { sellOrder.getSell_id(), sellOrder.getSell_date(), empName,
									sellOrder.getSell_profit(), sellOrder.getSell_mark() };
							sellOrderRows[i] = obj;
							price += sellOrder.getSell_profit();
						}
						sellOrderModel.setDataVector(sellOrderRows, sellOrderTitle);
						int rowsCount = listSellOrder.size();
						orderCount.setText("��������:"+rowsCount);
						NumberFormat format=NumberFormat.getNumberInstance() ; 
						format.setMaximumFractionDigits(2); 
					    String s= format.format(price) ; 
						orderPrice.setText("�����ܽ��:" + s);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ�Ķ������");
					}

				}

			}
		});
		findBtn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dateText.getText().trim().equals("����ѡ������")) {
					JOptionPane.showMessageDialog(null, "��ѡ������");
				} else if (dateText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "���ڲ���Ϊ��");
				} else {
					try {
						Date sell_date = Date.valueOf(dateText.getText());
						SellDao3 sellDao = new SellDao3();
						// ����list���ϱ���ɹ���������
						List<SellOrder> listPurOrder = sellDao.query(sell_date);
						EmpDao3 empDao = new EmpDao3();
						// ������ά����
						Object[][] purOrderRows = new Object[listPurOrder.size()][];
						price = 0;
						for (int i = 0; i < listPurOrder.size(); i++) {
							// �����ɹ���������
							SellOrder sellOrder = listPurOrder.get(i);
							String empName = empDao.get(sellOrder.getSell_empId()).getEmp_name();
							Object[] obj = { sellOrder.getSell_id(), sellOrder.getSell_date(), empName,
									sellOrder.getSell_profit(), sellOrder.getSell_mark() };
							purOrderRows[i] = obj;
							price += sellOrder.getSell_profit();
						}
						sellOrderModel.setDataVector(purOrderRows, sellOrderTitle);
						int rowsCount = listPurOrder.size();
						orderCount.setText("��������:"+rowsCount);
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
				SellStatistics.this.dispose();
			}
		});

	}

	public static void main(String[] args) {
		new SellStatistics();
	}

}
