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
	CommondMethods commondMethods = null;// 通用方法
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

	// 订单编号
	String purOrderId;

	// 构造方法
	public CheckPurOrder() {
		commondMethods = new CommondMethods();
		init();
		addPanel();
		addTable();
		addRegisterLisetener();
		setVisible(true);
	}

	// 初始化
	public void init() {
		setTitle("审核采购订单");
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

	// 添加面板
	public void addPanel() {
		// 添加主面板
		mainPanel = commondMethods.createPanel(5, 5, 772, 550);
		mainPanel.setBackground(null);

		// 功能名称面板
		JPanel namePanel = commondMethods.createPanel(10, 10, 755, 70);

		// 功能名称标签
		JLabel nameLabel = commondMethods.createLabel("采购订单", 330, 24, 140, 20, "隶书", 30);
		namePanel.add(nameLabel);
		mainPanel.add(namePanel);

		// 功能按钮面板
		JPanel btnPanel = commondMethods.createPanel(10, 90, 755, 50);
		mainPanel.add(btnPanel);
		this.add(mainPanel);

		// 添加按钮
		queryAllBtn = commondMethods.createButton("查询所有订单", 30, 10, 100, 25, "宋体", 14);
		btnPanel.add(queryAllBtn);
		queryByIdBtn = commondMethods.createButton("采购编号查询", 330, 10, 100, 25, "宋体", 14);
		btnPanel.add(queryByIdBtn);
		queryByDateBtn = commondMethods.createButton("采购日期查询", 620, 10, 100, 25, "宋体", 14);
		btnPanel.add(queryByDateBtn);

		// 添加文本框
		idText = commondMethods.createTextField("", 170, 10, 150, 25, "宋体", 20, new Color(237, 242, 248));
		btnPanel.add(idText);
		dateText = commondMethods.createTextField("单击选择日期", 458, 10, 150, 25, "宋体", 16, new Color(237, 242, 248));
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(dateText);
		btnPanel.add(dateText);
		
		//添加底部面板
		JPanel bottomPanel = commondMethods.createPanel(10, 476, 755, 60);
		mainPanel.add(bottomPanel);
		//创建确定按钮
		JButton goButton = commondMethods.createButton("确定", 510, 15, 60, 25, "宋体", 14);
		bottomPanel.add(goButton);
		
		goButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = purchaseTable.getSelectedRow();
				if(row!=-1) {
					purOrderId = purchaseModel.getValueAt(row, 0).toString();
					new purDetailInfor();
				}else {
					JOptionPane.showMessageDialog(null, "请单击选择一行");
				}
			}
		});
		//创建取消按钮
		JButton cancelBtn = commondMethods.createButton("取消", 610, 15, 60, 25, "宋体", 14);
		bottomPanel.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckPurOrder.this.dispose();
			}
		});

	}

	// 添加表
	public void addTable() {
		// 创建表头
		String[] title = { "采购订单号", "供应商名称", "采购日期", "支付总金额", "员工名称", "审核状态", "备注" };
		// 从数据库获取商品信息
		PurchaseDao3 purchaseDao = new PurchaseDao3();
		List<PurchaseOrder> listPurchase = purchaseDao.query();
		Object[][] rows = new Object[listPurchase.size()][];
		SupplyDao3 supplyDao = new SupplyDao3();
		EmpDao3 empDao = new EmpDao3();
		for (int i = 0; i < rows.length; i++) {

			PurchaseOrder purchaseOrder = listPurchase.get(i);
			String sup_name = null;
			String emp_name = null;
			String status = purchaseOrder.getPur_status() == 1 ? "审核通过"
					: (purchaseOrder.getPur_status() == 2 ? "审核未通过" : "未审核");
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
			rows[i] = obj;
		}
		purchaseModel = new DefaultTableModel(rows, title);
		purchaseTable = new JTable(purchaseModel);
		purchaseTable = new MTable(purchaseModel);
		// 给采购订单表添加点击事件
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
				// 创建表头
				String[] title = { "采购订单号", "供应商名称", "采购日期", "支付总金额", "员工名称", "审核状态", "备注" };
				// 从数据库获取商品信息
				PurchaseDao3 purchaseDao = new PurchaseDao3();
				List<PurchaseOrder> listPurchase = purchaseDao.query();
				Object[][] rows = new Object[listPurchase.size()][];
				SupplyDao3 supplyDao = new SupplyDao3();
				EmpDao3 empDao = new EmpDao3();
				for (int i = 0; i < rows.length; i++) {
					PurchaseOrder purchaseOrder = listPurchase.get(i);
					String sup_name = null;
					String emp_name = null;
					String status = purchaseOrder.getPur_status() == 1 ? "审核通过"
							: (purchaseOrder.getPur_status() == 2 ? "审核未通过" : "未审核");
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
					rows[i] = obj;
				}
				purchaseModel.setDataVector(rows, title);
				// 给采购订单表添加点击事件
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
						JOptionPane.showMessageDialog(null, "请输入正确的编号");
					}

					// 创建表格
					String[] title = { "采购订单号", "供应商名称", "采购日期", "支付总金额", "员工名称", "审核状态", "备注" };
					// 从数据库中获取商品信息
					PurchaseDao3 purchaseDao = new PurchaseDao3();
					List<PurchaseOrder> listPurchase = purchaseDao.queryByorderId(id);
					Object[][] rows = new Object[listPurchase.size()][];
					SupplyDao3 supplyDao = new SupplyDao3();
					EmpDao3 empDao = new EmpDao3();
					for (int i = 0; i < rows.length; i++) {
						PurchaseOrder purchaseOrder = listPurchase.get(i);
						String sup_name = null;
						String emp_name = null;
						String status = purchaseOrder.getPur_status() == 1 ? "审核通过"
								: (purchaseOrder.getPur_status() == 2 ? "审核未通过" : "未审核");
						try {
							sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
							emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						// 将对象转换为数组
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
						// 创建表格
						String[] title = { "采购订单号", "供应商名称", "采购日期", "支付总金额", "员工名称", "审核状态", "备注" };
						// 从数据库中获取商品信息
						PurchaseDao3 purchaseDao = new PurchaseDao3();
						List<PurchaseOrder> listPur = purchaseDao.queryByDate(date);
						Object[][] rows = new Object[listPur.size()][];
						SupplyDao3 supplyDao = new SupplyDao3();
						EmpDao3 empDao = new EmpDao3();
						for (int i = 0; i < rows.length; i++) {
							PurchaseOrder purchaseOrder = listPur.get(i);
							String sup_name = null;
							String emp_name = null;
							String status = purchaseOrder.getPur_status() == 1 ? "审核通过"
									: (purchaseOrder.getPur_status() == 2 ? "审核未通过" : "未审核");
							sup_name = supplyDao.get(purchaseOrder.getPur_supplyId()).getSup_name();
							emp_name = empDao.get(purchaseOrder.getPur_empId()).getEmp_name();
							// 将对象转换为数组
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
						JOptionPane.showMessageDialog(null, "请选择正确的日期");
					}

				}
			}
		});
	}

	public static void main(String[] args) {
		new CheckPurOrder();
	}

	class purDetailInfor extends JFrame {
		// 主面板
		JPanel mainPanel;
		// 名称面板
		JPanel namePanel;
		// 查询面板
		JPanel findPanel;
		// 添加采购订单标签
		JLabel purOrderLabel;
		// 添加查询按钮
		JButton findBtn;
		// 滚动面板
		JScrollPane goodDetSc;
		// 创建表模型
		DefaultTableModel goodDetModel;
		// 创建表格对象
		JTable goodDetTable;
		JTextField findText = null;
		JButton successBtn = null;
		// 创建底部面板
		JPanel purBPanel = null;
		// 添加退出按钮
		JButton failBtn = null;
		JPanel markPanel = null;// 备注面板

		// 构造方法
		public purDetailInfor() {
			init();
			addPanel();
			addTable();
			addMouseListerer();
			setVisible(true);
		}

		// 初始化
		public void init() {
			// 设置窗口大小
			this.setSize(800, 600);
			// 设置窗口显示位置
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenWidth = screenSize.width;
			int screenHeight = screenSize.height;
			int windowWidth = this.getWidth();
			int windowHeight = this.getHeight();
			int x = (screenWidth - windowWidth) / 2;
			int y = (screenHeight - windowHeight) / 2;
			setLocation(x, y);
			// 不许修改窗口的大小
			this.setResizable(false);
			this.setLayout(null);
			this.setTitle("采购订单详情");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		// 添加面板
		public void addPanel() {
			// 添加主面板
			mainPanel = commondMethods.createPanel(5, 5, 785, 550);
			mainPanel.setLayout(null);

			// 添加名称面板
			namePanel = commondMethods.createPanel(5, 5, 775, 50);
			// 添加名称标签
			JLabel nameLabel = commondMethods.createLabel("采购订单详情表", 325, 10, 200, 20, "隶书", 24);
			namePanel.add(nameLabel);
			mainPanel.add(namePanel);

			// 添加查询面板
			findPanel = commondMethods.createPanel(5, 60, 775, 40);
			// 添加采购订单标签
			purOrderLabel = commondMethods.createLabel("采购订单号:", 50, 10, 100, 20, "宋体", 16);
			// 显示订单号
			JLabel orderLabel = commondMethods.createLabel("" + purOrderId, 150, 10, 100, 20, "宋体", 16);
			

			// 创建备注面板
			markPanel = commondMethods.createPanel(5, 340, 775, 120);
			mainPanel.add(markPanel);
			// 创建备注标签
			JLabel markLabel = commondMethods.createLabel("备注:", 10, 10, 100, 20, "宋体", 16);
			markPanel.add(markLabel);
			// 创建备注文本域
			markText = commondMethods.createTextArea("", 60, 10, 700, 90, "宋体", 16, new Color(237, 242, 248));
			markPanel.add(markText);
			// 创建底部面板
			purBPanel = commondMethods.createPanel(5, 470, 775, 60);
			// 添加审核按钮
			successBtn = commondMethods.createButton("审核通过", 400, 10, 100, 30, "宋体", 16);
			purBPanel.add(successBtn);
			// 添加退出按钮
			failBtn = commondMethods.createButton("审核不通过", 550, 10, 100, 30, "宋体", 16);
			purBPanel.add(failBtn);
			// 将底部面板添加到主面板
			purBPanel.add(successBtn);
			findPanel.add(orderLabel);
			findPanel.add(purOrderLabel);
			mainPanel.add(findPanel);
			mainPanel.add(purBPanel);
			this.add(mainPanel);
		}

		// 添加表
		public void addTable() {
			// 创建表头
			String[] title = { "序号", "商品编号", "商品名称", "单位", "规格大小", "商品进价", "商品数量", "进价总金额", "采购状态", "备注" };

			// 从数据库获取数据
			CheckPurOrderDao3 checkPurOrderDao = new CheckPurOrderDao3();
			List<NewPurDetail> listPurDetail = checkPurOrderDao.query(Integer.parseInt(purOrderId));
			Object[][] goodRows = new Object[listPurDetail.size()][];
			for (int i = 0; i < goodRows.length; i++) {
				// 创建对象存储数据
				NewPurDetail newPurDetail = listPurDetail.get(i);
				newPurDetail.setGoodAllPrice(newPurDetail.getGoodPurNum() * newPurDetail.getGoodPurPrice());

				String status = newPurDetail.getGoodStatus() == 0 ? "已入库" : "未入库";
				pDet_id = newPurDetail.getpDet_id();
				// 将对象转换为数组
				Object[] obj = { pDet_id, newPurDetail.getGoodId(), newPurDetail.getGoodName(),
						newPurDetail.getGoodUnits(), newPurDetail.getGoodSize(), newPurDetail.getGoodPurPrice(),
						newPurDetail.getGoodPurNum(), newPurDetail.getGoodAllPrice(), status,
						newPurDetail.getGoodPurMark() };
				// 给二维数组赋值
				goodRows[i] = obj;
			}

			// 创建表模型
			goodDetModel = new DefaultTableModel(goodRows, title);
			// 创建表
			goodDetTable = new JTable(goodDetModel);
			// 设置居中
			goodDetTable = new MTable(goodRows, title);
			// 将表格添加到滚动面板
			// 创建滚动面板
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

		// 给按钮添加点击事件
		public void addMouseListerer() {
			failBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CheckPurOrderDao3 checkPurOrderDao = new CheckPurOrderDao3();
					if (markText.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "备注不能为空");
					} else {
						checkPurOrderDao.updatePurOrderIdto2(Integer.parseInt(purOrderId),markText.getText().trim());
						queryAllBtn.doClick();
						JOptionPane.showMessageDialog(null, "审核不通过");
						purDetailInfor.this.dispose();
					}

				}
			});
			successBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CheckPurOrderDao3 checkPurOrderDao = new CheckPurOrderDao3();
					checkPurOrderDao.updatePurOrderIdto1(Integer.parseInt(purOrderId));
					// 获取行的总数
					int goodRows = goodDetTable.getRowCount();
					// 创建Dao对象
					StoTransDao3 stoTransDao = new StoTransDao3();
					for (int i = 0; i < goodRows; i++) {
						pDet_id = (int) goodDetTable.getValueAt(i, 0);
						// 获取每一行的商品编号
						int goodId = (int) goodDetTable.getValueAt(i, 1);
						// 获取每一行的商品总数
						int goodNum = (int) goodDetTable.getValueAt(i, 6);
						// 调用dao层数据更新方法
						stoTransDao.updateGood(goodId, goodNum, pDet_id);
					}
					queryAllBtn.doClick();
					JOptionPane.showMessageDialog(null, "审核成功");
					purDetailInfor.this.dispose();
				}
			});
		}
	}

}
