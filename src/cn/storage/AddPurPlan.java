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
 * 1.日期:2017-08-03 
 * 2.主类功能:
 *  a.供应商选择
 *  b.收货仓库选择
 *  c.添加老商品
 *  d.删除商品
 *  e.修改商品数量
 *  f.日期控件选择日期
 *  g.异常的捕获，通过警示框传递给用户熟知的信息
 *  l.采购计划的创建，并更新数据库
 * 3.内部类功能
 * 	a.分割面板左边显示自己仓库已有老商品，右边显示已选择的商品
 *  b.商品编号模糊查询商品 c.已选择的商品提供修改数量，删除功能
 * 	d.选择完成通过表模型和表对象将值传递给主类。
 *  e.通过静态变量计算商品的总价格
 * @author LuckyJavaCi
 */
public class AddPurPlan extends JFrame {
	private JPanel mainPanel;// 采购计划主面板
	private JPanel inforPanel;// 显示仓库供货商和计划日期
	private JPanel funcPanl;// 功能面板，显示按钮
	private JPanel bottomPanel;// 底部面板
	private JScrollPane goodSc;// 表格滚动面板
	private JTextField supText;// 供应商文本框
	private JButton supFindBtn;// 查找供应商
	private JTextField stoText;// 仓库文本框
	private JButton stoFindBtn;// 查找仓库
	private JTextField dateText;// 日期文本框
	private JLabel orderNumber;// 订单编号标签
	private int purPlanOrder;// 订单编号
	private String stoName;// 仓库名
	private String supName;// 供应商名
	private JButton addOldGoodsBtn;// 添加老商品
	private JButton delOldGoodBtn;// 添加"删除商品"按钮
	private JButton updateOldGoodBtn;// 添加"修改商品"按钮
	private DefaultTableModel planGoodModel;// 计划商品表模型
	private JTable planGoodTable;// 计划商品表
	private JScrollPane planGoodPane;// 计划商品滚动面板
	static JTextField shouldPayText;// 应付金额文本框
	JTextField truePayText;// 实付金额文本框
	private JComboBox empList;// 经办人列表框
	private JTextField markText;// 备注列表框
	private JButton confirmBtn;// 确定按钮
	private JButton cancelBtn;// 取消按钮
	private String[] title = { "商品编号", "商品名称", "单位", "规格大小", "单价", "数量", "总金额" };// 表头
	static double priceCount = 0;// 设置为静态变量，在多个窗口进行只变化
	private JTextField numText;// 修改数量文本框
	private int good_purPlanNum;// 计划采购数量
	private double good_purPlanPrice;// 商品的采购单价
	CommondMethods commondMethods = null;// 通用方法
	Color mainColor = null;// 通用颜色

	public AddPurPlan() {
		commondMethods = new CommondMethods();
		this.init();
		this.addPanel();
		this.elementListener();
		this.addTable();
		this.setVisible(true);
	}

	/*
	 * 初始化窗口
	 */
	public void init() {
		this.setTitle("采购计划");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 设置窗口居中
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		// 设置窗口不可改变
		this.setResizable(false);
	}

	// 添加面板
	public void addPanel() {
		// 添加主面板
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainColor = new Color(235, 235, 235);
		mainPanel.setBackground(mainColor);
		mainPanel.setBorder(BorderFactory.createEtchedBorder());

		// 添加标题标签
		JLabel titleLabel = commondMethods.createLabel("采购计划", 330, 10, 150, 30, "隶书", 28);
		mainPanel.add(titleLabel);

		// 添加订单号标签
		JLabel orderLabel = commondMethods.createLabel("订单号:", 500, 20, 150, 30, "宋体", 14);
		orderLabel.setForeground(Color.RED);
		mainPanel.add(orderLabel);

		// 显示订单号
		StorageDao3 storage = new StorageDao3();
		purPlanOrder = storage.getMaxId();
		orderNumber = commondMethods.createLabel("" + purPlanOrder, 550, 20, 150, 30, "宋体", 14);
		orderNumber.setForeground(Color.RED);
		mainPanel.add(orderNumber);

		// 添加信息面板
		inforPanel = commondMethods.createPanel(12, 50, 772, 72);
		Color inforColor = new Color(237, 242, 248);
		inforPanel.setBackground(inforColor);

		// 添加供货商标签
		JLabel supLabel = commondMethods.createLabel("供应商:", 40, 24, 100, 20, "宋体", 14);
		inforPanel.add(supLabel);

		// 添加供应商文本框
		supText = commondMethods.createTextField("阿里巴巴供应商", 90, 25, 100, 20, "宋体", 14, inforColor);
		MatteBorder bottomBorder = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		supText.setBorder(bottomBorder);
		supText.setHorizontalAlignment(JTextField.CENTER);
		inforPanel.add(supText);

		//添加查找按钮
		supFindBtn = commondMethods.createButton("查询", 195, 22, 60, 25, "宋体", 14);
		inforPanel.add(supFindBtn);
		
		// 添加收货仓库标签
		JLabel inputStoLabel = commondMethods.createLabel("收货仓库:", 270, 24, 100, 20, "宋体", 14);
		inforPanel.add(inputStoLabel);

		// 添加仓库文本框
		stoText = commondMethods.createTextField("主仓库", 340, 25, 100, 20, "宋体", 14, inforColor);
		stoText.setHorizontalAlignment(JTextField.CENTER);
		stoText.setBorder(bottomBorder);
		inforPanel.add(stoText);

		// 添加查找图片按钮
		
		stoFindBtn = commondMethods.createButton("查询", 445, 22, 60, 25, "宋体", 14);
		inforPanel.add(stoFindBtn);
		mainPanel.add(inforPanel);

		// 添加计划日期标签
		JLabel dateLabel = commondMethods.createLabel("计划日期:", 510, 24, 100, 20, "宋体", 14);
		inforPanel.add(dateLabel);

		// 添加日期文本框
		dateText = commondMethods.createTextField("单击选择日期", 580, 25, 100, 20, "宋体", 14, inforColor);
		dateText.setBorder(bottomBorder);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(dateText);
		inforPanel.add(dateText);

		// 添加功能面板
		funcPanl = commondMethods.createPanel(12, 125, 772, 30);

		// 添加“添加老商品商品”按钮
		addOldGoodsBtn = commondMethods.createButton("添加老商品", 30, 5, 100, 20, "宋体", 14);
		addOldGoodsBtn.setBackground(new Color(175, 208, 244));
		funcPanl.add(addOldGoodsBtn);
		mainPanel.add(funcPanl);

		// 添加"删除商品"按钮
		delOldGoodBtn = commondMethods.createButton("删除商品", 450, 5, 100, 20, "宋体", 14);
		delOldGoodBtn.setBackground(new Color(175, 208, 244));
		funcPanl.add(delOldGoodBtn);
		mainPanel.add(funcPanl);

		// 添加"修改商品"按钮
		updateOldGoodBtn = commondMethods.createButton("修改商品", 600, 5, 100, 20, "宋体", 14);
		updateOldGoodBtn.setBackground(new Color(175, 208, 244));
		funcPanl.add(updateOldGoodBtn);
		mainPanel.add(funcPanl);

		// 添加底部面板
		bottomPanel = commondMethods.createPanel(12, 385, 772, 72);
		mainPanel.add(bottomPanel);

		// 应付金额标签
		JLabel shouldPayLabel = commondMethods.createLabel("应付金额", 30, 5, 150, 20, "宋体", 14);
		bottomPanel.add(shouldPayLabel);

		// 应付金额文本框
		shouldPayText = commondMethods.createTextField("" + priceCount, 95, 10, 80, 14, "宋体", 14, inforColor);
		shouldPayText.setBorder(bottomBorder);
		shouldPayText.setHorizontalAlignment(JTextField.CENTER);
		bottomPanel.add(shouldPayText);
		// 实付金额标签
		JLabel truePayLabel = commondMethods.createLabel("实付金额", 190, 5, 80, 20, "宋体", 14);
		bottomPanel.add(truePayLabel);
		// 实付金额文本框
		truePayText = commondMethods.createTextField("", 253, 10, 80, 14, "宋体", 14, inforColor);
		truePayText.setBorder(bottomBorder);
		truePayText.setHorizontalAlignment(JTextField.CENTER);
		bottomPanel.add(truePayText);
		// 经办人标签
		JLabel empLabel = commondMethods.createLabel("经办人:", 460, 10, 80, 14, "宋体", 14);
		bottomPanel.add(empLabel);
		// 经办人列表
		empList = commondMethods.createJComboBox(520, 6, 80, 20, "宋体", 14);
		EmpDao3 empDao = new EmpDao3();
		List<Employee> emp = empDao.query(3);
		for (int i = 0; i < emp.size(); i++) {
			empList.addItem(emp.get(i).getEmp_name());
		}
		bottomPanel.add(empList);

		// 添加备注标签
		JLabel markLabel = commondMethods.createLabel("备注", 30, 40, 150, 20, "宋体", 14);
		bottomPanel.add(markLabel);
		// 添加备注文本框
		markText = commondMethods.createTextField("", 70, 40, 264, 20, "宋体", 14, inforColor);
		markText.setBorder(bottomBorder);
		bottomPanel.add(markText);
		// 确定按钮
		confirmBtn = commondMethods.createButton("确认", 460, 40, 100, 20, "宋体", 14);
		bottomPanel.add(confirmBtn);
		// 取消按钮
		cancelBtn = commondMethods.createButton("取消", 610, 40, 100, 20, "宋体", 14);
		bottomPanel.add(cancelBtn);
		this.add(mainPanel);
	}

	// 添加表
	public void addTable() {
		// 实例化表模型
		planGoodModel = new DefaultTableModel(null, title);
		// 实例化表
		planGoodTable = new JTable(planGoodModel);
		// 设置表格不可双击修改并且单元格居中
		planGoodTable = new MTable(planGoodModel);
		// 添加到滚动面板
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
		// 查找供应商
		supFindBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SupFrame();
			}
		});
		// 查找仓库
		stoFindBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StoFrame();
			}
		});
		// 取消订单
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 先删除采购计划详情表
				PurPlanDao3 purPlanDao = new PurPlanDao3();
				int detailRow = purPlanDao.delPurPlanDetail(purPlanOrder);
				int purRow = purPlanDao.delPurPlan(purPlanOrder);
				if (detailRow > -1 && purRow > -1) {
					JOptionPane.showMessageDialog(null, "采购计划取消成功");
				}
				AddPurPlan.this.dispose();
			}
		});
		// 老商品添加
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
				// 选择行
				int row = planGoodTable.getSelectedRow();
				// 将总金额归零
				priceCount = 0;
				if (row > -1) {
					planGoodModel.removeRow(row);
					// 获取行的总数
					int mainFrameRows = planGoodModel.getRowCount();
					for (int i = 0; i < mainFrameRows; i++) {
						// 获取每一行的总金额
						double rowPrice = (double) planGoodTable.getValueAt(i, 6);
						priceCount += rowPrice;
					}
					shouldPayText.setText("" + priceCount);
				} else {
					JOptionPane.showConfirmDialog(null, "请选中一个行");
				}
			}
		});
		// 修改老商品数量
		updateOldGoodBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取选中的行
				int row = planGoodTable.getSelectedRow();
				if (row > -1) {
					new updateNum();
				}
			}
		});
		// 确定订单
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 判断实付金额时候否为空
				String truePay = truePayText.getText();

				if (("单击选择日期").equals(dateText.getText()) || dateText.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "请选择日期");
				} else if (truePay.equals("")) {
					JOptionPane.showMessageDialog(null, "请输入实付金额");
				} else {
					if (Double.parseDouble(truePay) < priceCount) {
						JOptionPane.showMessageDialog(null, "实付金额不能少于应付金额");
					} else {
						// 获取表的最大行数
						int maxRows = planGoodTable.getRowCount();
						if (maxRows == 0) {
							JOptionPane.showMessageDialog(null, "请选择商品");
						}
						int rows = 0;
						// 将表中的数据存入二维数组
						for (int i = 0; i < maxRows; i++) {
							// 创建对象保存数据
							PurPlanDetail purPlanDetail = new PurPlanDetail();
							purPlanDetail.setPlanDet_purId(purPlanOrder);// 订单号
							// 获取每一行的数据
							// 获取商品编号
							int gId = (int) planGoodTable.getValueAt(i, 0);
							purPlanDetail.setPlanDet_goodId(gId);
							// 获取商品的采购数量
							int gNum = (int) planGoodTable.getValueAt(i, 5);
							purPlanDetail.setPlanDet_number(gNum);
							// 获取每种商品的总金额
							double gAllPrice = (double) planGoodTable.getValueAt(i, 6);
							purPlanDetail.setPlanDet_goodPrice(gAllPrice);
							// 调用dao层添加数据
							PurPlanDao3 purPlanDao = new PurPlanDao3();
							rows = purPlanDao.addPurPlanDetail(purPlanDetail);
						}
						if (rows > 0) {
							// 修改采购计划信息
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
										"采购成功，采购结余" + (Double.parseDouble(truePay) - priceCount) + "元");
							} catch (IllegalArgumentException argumentException) {
								JOptionPane.showMessageDialog(null, "您输入的日期不正确");
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
	 * 功能:获得更改后的商品数量
	 * 
	 * @author LuckyJavaCi
	 */
	class updateNum extends JFrame {
		JButton updateConfirm;// 确定按钮
		JButton updateCancel;// 取消按钮

		// 构造方法
		public updateNum() {
			this.init();
			this.addPanel();
			this.addListener();
			this.setVisible(true);
		}

		// 初始化
		public void init() {
			this.setTitle("修改数量");
			this.setSize(400, 185);
			this.setLayout(null);
			// 设置窗口居中
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

		// 添加面板
		public void addPanel() {
			// 添加数字面板
			JPanel numPanel = commondMethods.createPanel(10, 10, 365, 80);
			numPanel.setBorder(BorderFactory.createEtchedBorder());
			this.add(numPanel);

			// 添加修改标签
			JLabel updateLabel = commondMethods.createLabel("修改数量", 40, 30, 100, 30, "宋体", 20);
			numPanel.add(updateLabel);

			// 添加数量文本框
			numText = commondMethods.createTextField("", 160, 30, 100, 30, "宋体", 14, mainColor);
			numPanel.add(numText);
			// 添加按钮面板
			JPanel btnPanel = commondMethods.createPanel(10, 100, 365, 40);
			// 添加按钮
			updateConfirm = commondMethods.createButton("确定", 10, 10, 100, 20, "宋体", 14);
			updateCancel = commondMethods.createButton("取消", 200, 10, 100, 20, "宋体", 14);
			btnPanel.add(updateConfirm);
			btnPanel.add(updateCancel);
			this.add(btnPanel);
		}

		// 时间监听方法
		public void addListener() {
			updateConfirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						// 计划商品数量
						good_purPlanNum = Integer.parseInt(numText.getText());
						int row = planGoodTable.getSelectedRow();
						// 更新表数据
						planGoodModel.setValueAt(good_purPlanNum, row, 5);
						good_purPlanPrice = (double) planGoodTable.getValueAt(row, 4);
						planGoodModel.setValueAt(good_purPlanNum * good_purPlanPrice, row, 6);

						// 将总金额归零
						priceCount = 0;
						// 获取行的总数
						int mainFrameRows = planGoodModel.getRowCount();
						for (int i = 0; i < mainFrameRows; i++) {
							// 获取每一行的总金额
							double rowPrice = (double) planGoodTable.getValueAt(i, 6);
							priceCount += rowPrice;
						}
						shouldPayText.setText("" + priceCount);
						updateNum.this.dispose();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的数量");
					}

				}
			});
			// 取消更改
			updateCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateNum.this.dispose();
				}
			});
		}
	}

	/**
	 * 功能:查询所有仓库，单击选择仓库名
	 * 
	 * @author LuckyJavaCi
	 */
	class StoFrame extends JFrame {
		private JPanel stoPanel;// 仓库面板
		private JScrollPane stoSc;// 滚动面板
		private DefaultTableModel stoModel;// 创建表模型
		private JTable stoTable;// 表格对象

		/**
		 * 构造方法
		 */
		public StoFrame() {
			this.init();
			this.addpanel();
			setUndecorated(true);
			// this.btn();
			// 窗口可视化
			this.setVisible(true);
		}

		/**
		 * 初始化页面
		 */
		public void init() {
			// 设置窗口大小
			this.setSize(400, 150);
			// 设置窗口显示位置
			this.setLocationRelativeTo(getOwner());
			// 不许修改窗口的大小
			this.setResizable(false);
			this.setTitle("仓库查询");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		/**
		 * 创建面板
		 */
		public void addpanel() {
			// 添加面板对象
			stoPanel = new JPanel();
			// 无布局设置
			stoPanel.setLayout(null);

			// 创建表格
			String[] str = { "仓库编号", "仓库名称", "员工编号", "仓库地址", "备注" };
			// 获取数据库数据
			StorageDao3 supd = new StorageDao3();
			List<Storage> listSto = supd.query();
			Object[][] rows = new Object[listSto.size()][];
			for (int i = 0; i < rows.length; i++) {
				// 获取供应商集合对象
				Storage storage = listSto.get(i);
				// 将对象转为数组存储
				Object[] obj = { storage.getSto_id(), storage.getName(), storage.getSto_empId(),
						storage.getSto_address(), storage.getSto_mark() };
				// 给二维数组赋值
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

			// 添加进窗口
			this.add(stoSc);
		}

	}

	/**
	 * 功能:查询所有供应商，单击选择供应商名称
	 * 
	 * @author LuckyJavaCi
	 */
	class SupFrame extends JFrame {
		private JPanel supPanel;// 仓库面板
		private JScrollPane supSc;// 滚动面板
		private DefaultTableModel supModel;// 创建表模型
		private JTable suptable;// 表格对象

		/**
		 * 构造方法
		 */
		public SupFrame() {
			this.init();
			this.addpanel();
			setUndecorated(true);
			// this.btn();
			// 窗口可视化
			this.setVisible(true);
		}

		/**
		 * 初始化页面
		 */
		public void init() {
			// 设置窗口大小
			this.setSize(400, 250);
			// 设置窗口显示位置
			this.setLocationRelativeTo(getOwner());
			// 不许修改窗口的大小
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		/**
		 * 创建面板
		 */
		public void addpanel() {
			// 添加面板对象
			supPanel = new JPanel();
			// 无布局设置
			supPanel.setLayout(null);

			// 创建表格
			String[] str = { "供应商编号", "供应商名称", "供应商联系电话", "仓库地址" };
			// 获取数据库数据
			StorageDao3 supd = new StorageDao3();
			List<Supply> listSto = supd.getSupply();
			Object[][] rows = new Object[listSto.size()][];
			for (int i = 0; i < rows.length; i++) {
				// 获取供应商集合对象
				Supply supply = listSto.get(i);
				// 将对象转为数组存储
				Object[] obj = { supply.getSup_id(), supply.getSup_name(), supply.getSup_phone(),
						supply.getSup_address() };
				// 给二维数组赋值
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

			// 添加进窗口
			this.add(supPanel);
		}

	}

}
