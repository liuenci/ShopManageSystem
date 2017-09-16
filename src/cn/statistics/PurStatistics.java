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
 * 1.采购统计界面 2.2017-08-16
 * 
 * @author LuckyJavaCi
 *
 */
public class PurStatistics extends JFrame {
	CommondMethods commondMethods = new CommondMethods();
	Color color = new Color(237, 242, 248);// 面板通用颜色
	JTextField idText1;// 订单编号文本框
	JTextField dateText;// 采购日期文本框
	JButton findBtn1;// 订单编号查找按钮
	JButton findBtn2;// 采购日期查找按钮
	JButton findBtn3;// 商品编号查找按钮
	JButton findBtn4;// 商品名称查找按钮
	JButton exitBtn1;// 退出按钮
	JButton exitBtn2;// 退出按钮
	Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);// 边框无按钮
	DefaultTableModel purOrderModel;// 采购订单表模型
	JTable purOrderTable;// 采购订单表
	String[] purOrderTitle = { "采购订单号", "供应商名称", "采购日期", "支付金额", "员工姓名", "审核状态", "备注" };
	JScrollPane purOrderJS;// 采购订单滚动面板

	JTextField idText2;// 商品编号文本框
	JTextField nameText;// 商品名称文本框
	DefaultTableModel purDetModel;// 采购详细订单表模型
	JTable purDetTable;// 采购详细订单表
	String[] purDetTitle = { "商品名称", "单位", "规格型号", "数量", "总金额" };
	JScrollPane purDetJS;// 采购详单滚动面板
	int rowCount = 0;
	double price = 0;
	JLabel orderCount = null;
	JLabel orderPrice = null;
	int purOrderNum = 0;
	int purDetNum = 0;

	/*
	 * 构造方法
	 */
	public PurStatistics() {
		init();
		addPanel();
		addMouseListener();
		setVisible(true);
	}

	/*
	 * 窗口初始化
	 */
	public void init() {
		this.setTitle("采购统计");
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
	 * 添加面板
	 */
	public void addPanel() {
		// 添加采购订单面板
		JPanel purOrderPanel = new JPanel();
		purOrderPanel.setLayout(null);
		purOrderPanel.setBounds(10, 10, 820, 500);
		purOrderPanel.setBorder(BorderFactory.createEtchedBorder());

		// 添加按钮面板
		JPanel btnPanel1 = new JPanel();
		btnPanel1.setLayout(null);
		btnPanel1.setBackground(color);
		btnPanel1.setBounds(10, 10, 800, 70);
		btnPanel1.setBorder(BorderFactory.createEtchedBorder());
		purOrderPanel.add(btnPanel1);
		this.add(purOrderPanel);

		// 商品编号标签
		JLabel idLabel1 = commondMethods.createLabel("订单编号:", 30, 10, 100, 20, "宋体", 14);
		btnPanel1.add(idLabel1);

		// 商品编号文本框
		idText1 = commondMethods.createTextField("", 100, 10, 100, 20, "宋体", 16, null);
		btnPanel1.add(idText1);
		// id查找按钮
		findBtn1 = commondMethods.createButton("查找", 200, 10, 60, 20, "宋体", 16);
		btnPanel1.add(findBtn1);

		// 商品编号标签
		JLabel dateLabel = commondMethods.createLabel("采购日期:", 270, 10, 100, 20, "宋体", 16);
		btnPanel1.add(dateLabel);

		// 日期文本框
		dateText = new JTextField("单击选择日期");
		dateText.setBounds(350, 10, 100, 20);
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(dateText);
		btnPanel1.add(dateText);
		// 日期查找按钮
		findBtn2 = commondMethods.createButton("查找", 460, 10, 60, 20, "宋体", 16);
		btnPanel1.add(findBtn2);
		
		//全部查询按钮
		JButton queryAllBtn = commondMethods.createButton("查找全部订单", 540, 10, 120, 20, "宋体", 16);
		btnPanel1.add(queryAllBtn);
		queryAllBtn.addActionListener(new ActionListener() {
			
			private Object[][] purOrderRows2;

			@Override
			public void actionPerformed(ActionEvent e) {
				//清空下表
				purDetModel.setRowCount(0);
				// 从数据库获取数据
				PurchaseDao3 purchaseDao1 = new PurchaseDao3();
				EmpDao3 empDao = new EmpDao3();
				SupplyDao3 supplyDao = new SupplyDao3();
				List<PurchaseOrder> listPurchase1 = purchaseDao1.queryAll();
				purOrderRows2 = new Object[listPurchase1.size()][];
				for (int i = 0; i < purOrderRows2.length; i++) {

					PurchaseOrder purchaseOrder = listPurchase1.get(i);
					String sup_name = null;
					String emp_name = null;
					String status = purchaseOrder.getPur_status() == 0 ? "未审核"
							: (purchaseOrder.getPur_status() == 1 ? "审核通过" : "已退货");
					try {
						sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
						emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					// 将对象转化成数组
					Object[] obj = { purchaseOrder.getPur_id(), sup_name, purchaseOrder.getPur_date(),
							purchaseOrder.getPur_pay(), emp_name, status, purchaseOrder.getPur_mark() };
					// 给二维数组赋值
					purOrderRows2[i] = obj;
				}
				purOrderModel.setDataVector(purOrderRows2, purOrderTitle);
				rowCount = listPurchase1.size();
				price  =0;
				orderCount.setText("订单总数:" + rowCount);
				for (int i = 0; i < rowCount; i++) {
					price += (double) purOrderTable.getValueAt(i, 3);
				}
				NumberFormat format=NumberFormat.getNumberInstance() ; 
				format.setMaximumFractionDigits(2); 
			    String s= format.format(price) ; 
				orderPrice.setText("订单总金额:" + s);
			}
		});

		// 退出按钮
		exitBtn1 = commondMethods.createButton("退出", 700, 10, 60, 20, "宋体", 16);
		btnPanel1.add(exitBtn1);
		
		//导出为excel
		JButton exportPurOrderExcel = commondMethods.createButton("导出订单表", 650, 40, 100, 20, "宋体", 16);
		btnPanel1.add(exportPurOrderExcel);
		
		exportPurOrderExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File("D:\\excel\\purOrderExcel"+purOrderNum+".xls");
				purOrderNum++;
				ExcelExporter excelExporter = new ExcelExporter();
				
				try {
					excelExporter.exportTable(purOrderTable, file);
					JOptionPane.showMessageDialog(null, "导出为订单表成功");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "导出为订单表失败");
					e1.printStackTrace();
				}
			}
		});
		JButton exportPurDetExcel = commondMethods.createButton("导出详单表",650, 270, 100, 20, "宋体", 16);
		purOrderPanel.add(exportPurDetExcel);
		exportPurDetExcel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File("D:\\excel\\purDetExcel"+purDetNum+".xls");
				purOrderNum++;
				ExcelExporter excelExporter = new ExcelExporter();
				
				try {
					excelExporter.exportTable(purDetTable, file);
					JOptionPane.showMessageDialog(null, "导出详单表成功");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "导出详单表失败");
					e1.printStackTrace();
				}
			}
		});

		// 注明标签
		JLabel noteLabel = commondMethods.createLabel("注:该统计数据包括所有采购数据，包括采购退货数据。", 30, 45, 500, 20, "宋体", 14);
		noteLabel.setForeground(Color.RED);
		btnPanel1.add(noteLabel);

		// 订单表标签
		JLabel orderLabel = commondMethods.createLabel("订单表:", 10, 80, 100, 20, "宋体", 14);
		orderLabel.setForeground(Color.RED);
		purOrderPanel.add(orderLabel);

		// 订单详情表标签
		JLabel DetailLabel = commondMethods.createLabel("订单详情表:", 10, 280, 100, 20, "宋体", 14);
		DetailLabel.setForeground(Color.RED);
		purOrderPanel.add(DetailLabel);

		// 创建空的二维数组
		Object[][] purOrderRows1 = new Object[0][];
		// 创建表模型
		purOrderModel = new DefaultTableModel(null, purOrderTitle);
		// 创建表
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
		// 添加商品详情表
		purDetModel = new DefaultTableModel(null, purDetTitle);
		// 创建表
		purDetTable = new JTable(purDetModel);
		purDetTable = new MTable(purDetModel);
		purDetJS = new JScrollPane(purDetTable);
		purDetJS.setBounds(10, 300, 800, 180);
		purOrderPanel.add(purDetJS);

		// 从数据库获取数据
		PurchaseDao3 purchaseDao1 = new PurchaseDao3();
		EmpDao3 empDao = new EmpDao3();
		SupplyDao3 supplyDao = new SupplyDao3();
		List<PurchaseOrder> listPurchase1 = purchaseDao1.queryAll();
		purOrderRows1 = new Object[listPurchase1.size()][];
		for (int i = 0; i < purOrderRows1.length; i++) {

			PurchaseOrder purchaseOrder = listPurchase1.get(i);
			String sup_name = null;
			String emp_name = null;
			String status = purchaseOrder.getPur_status() == 0 ? "未审核"
					: (purchaseOrder.getPur_status() == 1 ? "审核通过" : "已退货");
			try {
				sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
				emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 将对象转化成数组
			Object[] obj = { purchaseOrder.getPur_id(), sup_name, purchaseOrder.getPur_date(),
					purchaseOrder.getPur_pay(), emp_name, status, purchaseOrder.getPur_mark() };
			// 给二维数组赋值
			purOrderRows1[i] = obj;
		}
		purOrderModel.setDataVector(purOrderRows1, purOrderTitle);
		rowCount = purOrderTable.getRowCount();
		orderCount = commondMethods.createLabel("订单总数:" + rowCount, 10, 250, 150, 20, "宋体", 14);
		orderCount.setForeground(Color.blue);
		purOrderPanel.add(orderCount);
		for (int i = 0; i < rowCount; i++) {
			price += (double) purOrderTable.getValueAt(i, 3);
		}
		NumberFormat format=NumberFormat.getNumberInstance() ; 
		format.setMaximumFractionDigits(2); 
	    String s= format.format(price) ; 
		orderPrice = commondMethods.createLabel("订单总金额:" + s, 350, 250, 250, 20, "宋体", 14);
		orderPrice.setForeground(Color.blue);
		purOrderPanel.add(orderPrice);
	}

	/*
	 * 按钮的点击事件
	 */
	public void addMouseListener() {
		findBtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				purDetModel.setRowCount(0);
				if (idText1.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入订单编号");
				} else {
					try {
						int pur_id = Integer.parseInt(idText1.getText());
						PurchaseDao3 purchaseDao = new PurchaseDao3();
						// 创建list集合保存采购订单数据
						List<PurchaseOrder> listPurOrder = purchaseDao.queryById(pur_id);
						// 创建二维数组
						Object[][] purOrderRows = new Object[listPurOrder.size()][];
						SupplyDao3 supplyDao = new SupplyDao3();
						EmpDao3 empDao = new EmpDao3();
						price = 0;
						for (int i = 0; i < listPurOrder.size(); i++) {
							// 创建采购订单对象
							PurchaseOrder purchaseOrder = listPurOrder.get(i);
							String sup_name = null;
							String emp_name = null;
							String status = purchaseOrder.getPur_status() == 0 ? "未审核"
									: (purchaseOrder.getPur_status() == 1 ? "审核通过" : "已退货");
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
						orderCount.setText("订单总数:" + rowCount);
						NumberFormat format=NumberFormat.getNumberInstance() ; 
						format.setMaximumFractionDigits(2); 
					    String s= format.format(price) ; 
						orderPrice.setText("订单总金额:" + s);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的数字");
					}
				}

			}
		});
		findBtn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				purDetModel.setRowCount(0);
				if (dateText.getText().equals("单击选择日期")) {
					JOptionPane.showMessageDialog(null, "请选择日期");
				} else {
					try {
						Date pur_date = Date.valueOf(dateText.getText());
						PurchaseDao3 purchaseDao = new PurchaseDao3();
						// 创建list集合保存采购订单数据
						List<PurchaseOrder> listPurOrder = purchaseDao.queryAllByDate(pur_date);
						// 创建二维数组
						Object[][] purOrderRows = new Object[listPurOrder.size()][];
						SupplyDao3 supplyDao = new SupplyDao3();
						EmpDao3 empDao = new EmpDao3();
						price = 0;
						for (int i = 0; i < listPurOrder.size(); i++) {
							// 创建采购订单对象
							PurchaseOrder purchaseOrder = listPurOrder.get(i);
							String sup_name = null;
							String emp_name = null;
							String status = purchaseOrder.getPur_status() == 0 ? "未审核"
									: (purchaseOrder.getPur_status() == 1 ? "审核通过" : "已退货");
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
						orderCount.setText("订单总数:" + rowCount);
						NumberFormat format=NumberFormat.getNumberInstance() ; 
						format.setMaximumFractionDigits(2); 
					    String s= format.format(price) ; 
						orderPrice.setText("订单总金额:" + s);
					} catch (IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的日期");
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
