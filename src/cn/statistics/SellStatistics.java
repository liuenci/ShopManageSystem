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
 * 1.销售统计界面 2.2017-08-17
 * 
 * @author LuckyJavaCi
 *
 */
public class SellStatistics extends JFrame {
	CommondMethods commondMethods = new CommondMethods();
	Color color = new Color(237, 242, 248);// 面板通用颜色
	JTextField idText1;// 订单编号文本框
	JTextField dateText;// 销售日期文本框
	JButton findBtn1;// 订单编号查找按钮
	JButton findBtn2;// 销售日期查找按钮
	JButton findBtn3;// 商品编号查找按钮
	JButton findBtn4;// 商品名称查找按钮
	JButton exitBtn1;// 退出按钮
	JButton exitBtn2;// 退出按钮
	Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);// 边框无按钮
	DefaultTableModel sellOrderModel;// 销售订单表模型
	JTable sellOrderTable;// 销售订单表
	String[] sellOrderTitle = { "销售订单号", "采购日期", "员工名称", "销售金额", "备注" };
	JScrollPane sellOrderJS;// 销售订单滚动面板
	JTextField idText2;// 商品编号文本框
	JTextField nameText;// 商品名称文本框
	DefaultTableModel sellDetModel;// 销售详细订单表模型
	JTable sellDetTable;// 销售详细订单表
	String[] sellDetTitle = { "商品名称", "单位", "规格型号", "数量", "总金额" };
	JScrollPane sellDetJS;// 销售详单滚动面板
	double price = 0;
	JLabel orderPrice =null;
	JLabel orderCount =null;
	int sellOrderNum = 0;
	int sellDetNum = 0;
	/*
	 * 构造方法
	 */

	public SellStatistics() {
		init();
		addPanel();
		addMouseListener();
		setVisible(true);
	}

	/*
	 * 窗口初始化
	 */
	public void init() {
		this.setTitle("销售统计");
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
		JPanel sellOrderPanel = new JPanel();
		sellOrderPanel.setLayout(null);
		sellOrderPanel.setBounds(10, 10, 820, 500);
		sellOrderPanel.setBorder(BorderFactory.createEtchedBorder());

		// 添加按钮面板
		JPanel btnPanel1 = new JPanel();
		btnPanel1.setLayout(null);
		btnPanel1.setBackground(color);
		btnPanel1.setBounds(10, 10, 800, 70);
		btnPanel1.setBorder(BorderFactory.createEtchedBorder());
		sellOrderPanel.add(btnPanel1);
		this.add(sellOrderPanel);


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

		// 全部查询按钮
		JButton queryAllBtn = commondMethods.createButton("查找全部订单", 540, 10, 120, 20, "宋体", 16);
		btnPanel1.add(queryAllBtn);
		queryAllBtn.addActionListener(new ActionListener() {
			
			private Object[][] sellOrderRows1;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 从数据库获取数据
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
					// 将对象转化成数组
					Object[] obj = { sellOrder.getSell_id(), sellOrder.getSell_date(), empName, sellOrder.getSell_profit(),
							sellOrder.getSell_mark() };
					// 给二维数组赋值
					sellOrderRows1[i] = obj;
					price += sellOrder.getSell_profit();
				}
				sellOrderModel.setDataVector(sellOrderRows1, sellOrderTitle);
				int rowsCount = listSellOrder.size();
				orderCount.setText("订单总数:"+rowsCount);
				NumberFormat format=NumberFormat.getNumberInstance() ; 
				format.setMaximumFractionDigits(2); 
			    String s= format.format(price) ; 
				orderPrice.setText("订单总金额:" + s);
			}
		});
 
		// 退出按钮
		exitBtn1 = commondMethods.createButton("退出", 700, 10, 60, 20, "宋体", 16);
		btnPanel1.add(exitBtn1);
		// 注明标签
		JLabel noteLabel = commondMethods.createLabel("注:该统计数据包括所有销售数据，包括销售退货数据。", 30, 45, 500, 20, "宋体", 14);
		noteLabel.setForeground(Color.RED);
		btnPanel1.add(noteLabel);
		
		
		//导出为excel
				JButton exportPurOrderExcel = commondMethods.createButton("导出订单表", 650, 40, 100, 20, "宋体", 16);
				btnPanel1.add(exportPurOrderExcel);
				
				exportPurOrderExcel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						File file = new File("D:\\excel1\\sellOrderExcel"+sellOrderNum+".xls");
						sellOrderNum++;
						ExcelExporter excelExporter = new ExcelExporter();
						
						try {
							excelExporter.exportTable(sellOrderTable, file);
							JOptionPane.showMessageDialog(null, "导出为订单表成功");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "导出为订单表失败");
							e1.printStackTrace();
						}
					}
				});
				JButton exportPurDetExcel = commondMethods.createButton("导出详单表",650, 270, 100, 20, "宋体", 16);
				sellOrderPanel.add(exportPurDetExcel);
				exportPurDetExcel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						File file = new File("D:\\excel1\\sellDetExcel"+sellDetNum+".xls");
						sellOrderNum++;
						ExcelExporter excelExporter = new ExcelExporter();
						try {
							excelExporter.exportTable(sellDetTable, file);
							JOptionPane.showMessageDialog(null, "导出为详表成功");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "导出详单表失败");
							e1.printStackTrace();
						}
					}
				});

		// 订单表标签
		JLabel orderLabel = commondMethods.createLabel("订单表:", 10, 80, 100, 20, "宋体", 14);
		orderLabel.setForeground(Color.RED);
		sellOrderPanel.add(orderLabel);
		// 订单详情表标签
		JLabel DetailLabel = commondMethods.createLabel("订单详情表:", 10, 280, 100, 20, "宋体", 14);
		DetailLabel.setForeground(Color.RED);
		sellOrderPanel.add(DetailLabel);
		// 创建空的二维数组
		Object[][] sellOrderRows = new Object[0][];
		// 创建表模型
		sellOrderModel = new DefaultTableModel(sellOrderRows, sellOrderTitle);
		// 创建表
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

		// 从数据库获取数据 **
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
			// 将对象转化成数组
			Object[] obj = { sellOrder.getSell_id(), sellOrder.getSell_date(), empName, sellOrder.getSell_profit(),
					sellOrder.getSell_mark() };
			// 给二维数组赋值
			sellOrderRows[i] = obj;
		}
		sellOrderModel.setDataVector(sellOrderRows, sellOrderTitle);

		// 销售详单表
		sellDetModel = new DefaultTableModel(null, sellDetTitle);
		// 创建表
		sellDetTable = new JTable(sellDetModel);
		sellDetTable = new MTable(sellDetModel);
		sellDetJS = new JScrollPane(sellDetTable);
		sellDetJS.setBounds(10, 300, 800, 180);
		sellOrderPanel.add(sellDetJS);
		orderCount = commondMethods.createLabel("订单总数:" + sellOrderTable.getRowCount(), 10, 250, 100, 20, "宋体",
				14);
		orderCount.setForeground(Color.blue);
		sellOrderPanel.add(orderCount);
		for (int i = 0; i < sellOrderTable.getRowCount(); i++) {
			price += (double) sellOrderTable.getValueAt(i, 3);
		}
		NumberFormat format=NumberFormat.getNumberInstance() ; 
		format.setMaximumFractionDigits(2); 
	    String s= format.format(price) ; 
		orderPrice = commondMethods.createLabel("订单总金额:" + s, 500, 250, 150, 20, "宋体", 14);
		orderPrice.setForeground(Color.blue);
		sellOrderPanel.add(orderPrice);
	}

	/*
	 * 设置图片按钮属性
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
	 * 按钮的点击事件
	 */
	public void addMouseListener() {
		findBtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (idText1.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入订单编号");
				} else {
					try {
						int sell_id = Integer.parseInt(idText1.getText());
						SellDao3 sellDao = new SellDao3();
						// 创建list集合保存采购订单数据
						List<SellOrder> listSellOrder = sellDao.queryById(sell_id);
						EmpDao3 empDao = new EmpDao3();
						// 创建二维数组
						Object[][] sellOrderRows = new Object[listSellOrder.size()][];
						price = 0;
						for (int i = 0; i < listSellOrder.size(); i++) {
							// 创建采购订单对象
							SellOrder sellOrder = listSellOrder.get(i);
							String empName = empDao.get(sellOrder.getSell_empId()).getEmp_name();
							Object[] obj = { sellOrder.getSell_id(), sellOrder.getSell_date(), empName,
									sellOrder.getSell_profit(), sellOrder.getSell_mark() };
							sellOrderRows[i] = obj;
							price += sellOrder.getSell_profit();
						}
						sellOrderModel.setDataVector(sellOrderRows, sellOrderTitle);
						int rowsCount = listSellOrder.size();
						orderCount.setText("订单总数:"+rowsCount);
						NumberFormat format=NumberFormat.getNumberInstance() ; 
						format.setMaximumFractionDigits(2); 
					    String s= format.format(price) ; 
						orderPrice.setText("订单总金额:" + s);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的订单编号");
					}

				}

			}
		});
		findBtn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dateText.getText().trim().equals("单击选择日期")) {
					JOptionPane.showMessageDialog(null, "请选择日期");
				} else if (dateText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "日期不能为空");
				} else {
					try {
						Date sell_date = Date.valueOf(dateText.getText());
						SellDao3 sellDao = new SellDao3();
						// 创建list集合保存采购订单数据
						List<SellOrder> listPurOrder = sellDao.query(sell_date);
						EmpDao3 empDao = new EmpDao3();
						// 创建二维数组
						Object[][] purOrderRows = new Object[listPurOrder.size()][];
						price = 0;
						for (int i = 0; i < listPurOrder.size(); i++) {
							// 创建采购订单对象
							SellOrder sellOrder = listPurOrder.get(i);
							String empName = empDao.get(sellOrder.getSell_empId()).getEmp_name();
							Object[] obj = { sellOrder.getSell_id(), sellOrder.getSell_date(), empName,
									sellOrder.getSell_profit(), sellOrder.getSell_mark() };
							purOrderRows[i] = obj;
							price += sellOrder.getSell_profit();
						}
						sellOrderModel.setDataVector(purOrderRows, sellOrderTitle);
						int rowsCount = listPurOrder.size();
						orderCount.setText("订单总数:"+rowsCount);
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
				SellStatistics.this.dispose();
			}
		});

	}

	public static void main(String[] args) {
		new SellStatistics();
	}

}
