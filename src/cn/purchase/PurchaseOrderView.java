package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import cn.dao.purchase.EmployeeDao1;
import cn.dao.purchase.PurchaseDao1;
import cn.dao.purchase.PurchaseOrderDao1;
import cn.dao.purchase.StoTransDao1;
import cn.dao.purchase.SupplyDao1;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.model.common.PurDetail;
import cn.model.common.PurchaseOrder;
import cn.model.common.Supply;
import cn.model.purchase.PurchaseEmpSup;
import cn.view.purchase.MTable;


/**
 * 1.日期：2017-8-03 
 * 2.主要功能 
 * 	a.内部类实现动态供货商选择 
 * 	b.老商品添加页面跳转 
 * 	c.新商品添加页面跳转 
 * 	d.计划商品添加页面跳转
 * 	e.修改商品，对表格中的商品数量进行修改 
 * 	f.删除商品，对表格中的商品进行删除
 * 	g.动态根据选择商品的数量单价计算应付金额
 * 	h.填写实付金额，对实付金额进行异常处理 
 * 	i.动态读取数据库采购员的名称
 * 	j.生产的采购订单默认为未审核状态 
 * 	k.可填写备注内容
 * 	l.点击确定更改采购订单表，生产采购订单详情表 
 * 	m.点击取消删除采购订单 
 * 	n.异常捕获，对用户可更改的部分进行错误操作警示框提醒
 * 
 * @author 熊晨晨
 */
public class PurchaseOrderView extends JFrame {
	CommondMethods commondMethods = new CommondMethods();
	DefaultTableModel tablemodel;// 创建表模型
	private JPanel jpbtn;// 按钮面板
	private JScrollPane spmains;// 滚动面板
	private DefaultTableModel tms;// 创建表模型
	private JTable tables;// 表格对象
	private JButton btnOgoods;// 老商品添加按钮
	private JButton btnNgoods;// 新商品添加按钮
	private JButton btnPgoods;// 计划商品添加按钮
	private JButton btnDet;// 删除按钮
	private JButton btnUpdate;// 修改商品
	private JButton btnSure;// 确定按钮
	private JButton btnDel;// 取消按钮
	private JButton btnsup;// 供应商查找
	private JButton btnemp;// 员工查找
	JTextField tfsupply = null;
	String supplyName = "";
	static double purAllPrice = 0;

	int goods_id;// 商品编号
	String goods_name;// 商品名称
	String goods_units;// 商品单位
	String goods_size;// 商品规格大小
	double goods_purPrise;// 商品进价
	double goods_sellPrice;// 商品售价
	int goods_number;// 商品数量
	int goods_stoId;// 仓库编号
	int goods_keepDays;// 保质期
	int goods_minNumber;// 最低库存
	String goods_mark;// 备注

	int row = 0;

	int pur_id;// 订单编号
	String pur_supplyName;// 供货商编号，外键
	Date pur_date;// 采购日期
	double pur_pay;// 支付总金额
	String pur_empName;// 员工编号，外键
	int pur_status;// 是否审核（0：未审核1：已审核通过 2：审核未通过退回采购员）
	String pur_mark;// 备注
	String stata;
	JLabel lapurid;// 订单id
	String newday;// 订单日期
	static JTextField tfallmoney;// 总金额
	JTextField tfRealMoney;// 实付金额
	JComboBox empList;// 经办人
	JLabel lbstatus;// 状态
	JTextField ftmark;// 备注
	CommondMethods comMenth = null;

	int pDet_id;// 采购订单详情表
	int pDet_purId;// 采购订单表编号，外键
	int pDet_goodId;// 商品编号
	int pDet_number;// 采购数量
	double pDet_goodPrice;// 每种商品的进价总价格
	int pDet_status;// 采购状态（0：入库1：未入库2）
	String pDet_mark;// 备注

	/**
	 * 构造方法
	 * 
	 * @param tmright
	 * @param tableright
	 */
	public PurchaseOrderView() {
		comMenth = new CommondMethods();
		this.purinit();
		this.init();
		this.addpanel();
		this.btn();
		// 窗口可视化
		this.setVisible(true);
	}

	/**
	 * 初始化页面
	 */
	public void init() {
		// 设置窗口大小
		this.setSize(815, 530);
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
		// 不许修改窗口的大小
		this.setResizable(false);
		this.setTitle("采购进货板块");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * 获取新建订单号信息
	 */
	public void purinit() {
		// 创建表格
		String[] strpur = { "采购订单号", "供货商编号", "采购日期", "支付总金额", "员工编号", "是否审核", "备注" };
		// 获取数据库数据
		PurchaseOrderDao1 purd = new PurchaseOrderDao1();
		List<PurchaseEmpSup> lsup = purd.getPurchaseOrderEmpSup();
		Object[][] rows = new Object[lsup.size()][];
		for (int i = 0; i < rows.length; i++) {
			// 获取供应商集合对象
			PurchaseEmpSup pur = lsup.get(i);
			stata = pur.getSup_status() == 0 ? "未审核" : (pur.getSup_status() == 1 ? "已审核通过" : "审核未通过");

			// 将对象转为数组存储
			Object[] obj = { pur.getPur_id(), pur.getSup_name(), pur.getPur_date(), pur.getPur_pay(), pur.getEmp_name(),
					stata, pur.getPur_mark() };
			// 给二维数组赋值
			rows[i] = obj;
		}
		tablemodel = new DefaultTableModel(rows, strpur);

		// 获取采购订单初始值
		pur_id = Integer.parseInt(tablemodel.getValueAt(row, 0).toString());
		pur_supplyName = tablemodel.getValueAt(row, 1).toString();
		pur_date = Date.valueOf(tablemodel.getValueAt(row, 2).toString());
		pur_pay = Double.parseDouble(tablemodel.getValueAt(row, 3).toString());
		pur_empName = tablemodel.getValueAt(row, 4).toString();
		stata = tablemodel.getValueAt(row, 5).toString();
		pur_mark = tablemodel.getValueAt(row, 6).toString();
	}

	/**
	 * 创建面板
	 */
	public void addpanel() {
		// 连接数据库
		StoTransDao1 std = new StoTransDao1();
		// 获取最大商品id
		final int maxid = std.getMaxId();

		// 添加面板对象
		jpbtn = comMenth.createPanel(280, 85, 815, 530);
		// 添加面板对象
		JPanel jptop = comMenth.createPanel(20, 50, 770, 40);
		JPanel jpmodel = comMenth.createPanel(20, 100, 770, 45);
		JPanel jpbottom = comMenth.createPanel(20, 400, 770, 80);
		// 无布局设置
		jpbtn.setLayout(null);
		jptop.setLayout(null);
		jpmodel.setLayout(null);
		jpbottom.setLayout(null);
		jpbtn.add(jptop);
		jpbtn.add(jpmodel);
		jpbtn.add(jpbottom);

		JLabel latit = comMenth.createLabel("采购进货", 340, 10, 180, 40, "隶书", 30);
		latit.setFont(new Font("隶书", Font.BOLD, 30));
		JLabel laorder = comMenth.createLabel("订单号：", 540, 30, 80, 20, "宋体", 14);
		laorder.setFont(new Font("宋体", Font.BOLD, 14));
		laorder.setForeground(Color.RED);
		lapurid = comMenth.createLabel("" + pur_id, 600, 30, 80, 20, "宋体", 14);
		lapurid.setFont(new Font("宋体", Font.BOLD, 14));
		lapurid.setForeground(Color.RED);

		// 创建供货商标签
		JLabel lbsupply = comMenth.createLabel("供货商:", 40, 10, 80, 20, "宋体", 14);
		lbsupply.setFont(new Font("宋体", Font.PLAIN, 14));
		
		// 创建文本框
		tfsupply = comMenth.createTextField(pur_supplyName, 100, 10, 120, 20, "宋体", 14, new Color(237, 242, 248));
		MatteBorder border = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfsupply.setBorder(border);
		tfsupply.setEditable(false);

		btnsup = comMenth.createButton("查找", 230, 7, 60, 25, "宋体", 14);

		jptop.add(lbsupply);
		jptop.add(tfsupply);
		jptop.add(btnsup);

		// 创建入仓库时间标签
		JLabel lbtime = comMenth.createLabel("进货日期:", 520, 10, 80, 25, "宋体", 14);
		lbtime.setFont(new Font("宋体", Font.PLAIN, 14));
		
		// 创建文本框
		DateFormat dfTime = new SimpleDateFormat("yyyy-MM-dd");
		newday = dfTime.format(new java.util.Date());
		JLabel lbTime = comMenth.createLabel(newday, 590, 10, 100, 25, "宋体", 14);

		jptop.add(lbtime);
		jptop.add(lbTime);

		// 老商品按钮
		btnOgoods = comMenth.createButton("老商品添加", 40, 7, 100, 25, "宋体", 14);
		// 创建新商品按钮
		btnNgoods = comMenth.createButton("新商品添加", 160, 7, 100, 25, "宋体", 14);
		// 计划商品按钮
		btnPgoods = comMenth.createButton("计划商品添加", 280, 7, 115, 25, "宋体", 14);
		// 商品删除
		btnDet = comMenth.createButton("删除商品", 520, 7, 90, 25, "宋体", 14);
		// 商品修改
		btnUpdate = comMenth.createButton("修改商品", 640, 7, 90, 25, "宋体", 14);
		// 确定按钮
		btnSure = comMenth.createButton("确定", 520, 40, 60, 25, "宋体", 14);
		// 取消按钮
		btnDel = comMenth.createButton("取消", 640, 40, 60, 25, "宋体", 14);

		jpmodel.add(btnOgoods);
		jpmodel.add(btnNgoods);
		jpmodel.add(btnPgoods);
		jpmodel.add(btnDet);
		jpmodel.add(btnUpdate);

		// 创建表格
		String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "总价", "售价", "仓库编号", "保质期", "最低库存", "备注" };
		tms = new DefaultTableModel(null, str);
		tables = new JTable(tms);
		tables = new MTable(tms);

		spmains = new JScrollPane(tables);
		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(155,193,242);
			}

		}
		commondMethods.setColor(tables, color);
		spmains.setBounds(20, 150, 770, 220);

		JLabel lballmoney = comMenth.createLabel("应付金额:", 20, 10, 80, 30, "宋体", 14);
		lballmoney.setFont(new Font("宋体", Font.PLAIN, 14));
		tfallmoney = comMenth.createTextField("" + pur_pay, 85, 10, 60, 25, "宋体", 14, new Color(237, 242, 248));
		tfallmoney.setEditable(false);
		MatteBorder border2 = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfallmoney.setBorder(border2);

		JLabel lbRealMoney = comMenth.createLabel("实付金额:", 160, 10, 80, 30, "宋体", 14);
		lbRealMoney.setFont(new Font("宋体", Font.PLAIN, 14));
		tfRealMoney = comMenth.createTextField("", 230, 10, 60, 25, "宋体", 14, new Color(237, 242, 248));
		MatteBorder borderReal = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfRealMoney.setBorder(borderReal);

		JLabel lbpeople = comMenth.createLabel("经办人：", 300, 10, 80, 25, "宋体", 14);
		lbpeople.setFont(new Font("宋体", Font.PLAIN, 14));
		// 创建经办人列表框
		empList = comMenth.createJComboBox(350, 10, 80, 25, "宋体", 14);
		// 从数据库获取员工名
		EmployeeDao1 empDao = new EmployeeDao1();
		List<cn.model.common.Employee> employeeList = empDao.query(1);
		for (int i = 0; i < employeeList.size(); i++) {
			empList.addItem(employeeList.get(i).getEmp_name());
		}
		jpbottom.add(empList);

		JLabel lbsure = comMenth.createLabel("审核:", 520, 10, 60, 25, "宋体", 14);
		lbsure.setFont(new Font("宋体", Font.PLAIN, 14));
		lbstatus = comMenth.createLabel(stata, 570, 10, 80, 25, "宋体", 14);
		JLabel lbmark = comMenth.createLabel("备注：", 20, 40, 60, 25, "宋体", 14);
		lbmark.setFont(new Font("宋体", Font.PLAIN, 14));
		ftmark = comMenth.createTextField(pur_mark, 60, 40, 370, 25, "宋体", 14, new Color(237, 242, 248));
		MatteBorder border5 = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		ftmark.setBorder(border5);

		// 添加到面板
		jpbtn.add(spmains);
		jpbtn.add(laorder);
		jpbtn.add(latit);
		jpbtn.add(lapurid);

		jpbottom.add(lbsure);
		jpbottom.add(lbstatus);
		jpbottom.add(lbmark);
		jpbottom.add(ftmark);
		jpbottom.add(lbpeople);
		jpbottom.add(lbRealMoney);
		jpbottom.add(tfRealMoney);
		jpbottom.add(lballmoney);
		jpbottom.add(tfallmoney);
		jpbottom.add(btnSure);
		jpbottom.add(btnDel);

		// 添加进窗口
		this.add(jpbtn);
	}

	/**
	 * 按钮点击事件
	 */
	public void btn() {
		// 连接数据库
		StoTransDao1 std = new StoTransDao1();
		// 获取最大商品id
		final int maxid = std.getMaxId();
		// 老商品添加事件
		btnOgoods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 老商品添加事件
				new PurchaseOldAdd(tables, tms);
			}
		});
		// 新商品添加事件
		btnNgoods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 新商品添加事件
				new PurchaseOrdNew(tms, tables);
			}
		});
		// 计划商品添加商品
		btnPgoods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 计划单商品添加事件
				new PurchasePlan(tms, tables);
			}
		});
		// 商品删除按钮
		btnDet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 商品删除事件
				// 删除供应商信息
				int rows = tables.getSelectedRow();
				if (rows != -1) {
					int goodId = Integer.parseInt(tms.getValueAt(rows, 0).toString());
					Object[] obj = { "任性删除", "手下留情" };
					// 选择提示语句
					int res = JOptionPane.showOptionDialog(null, "是否确定删除！", "删除操作提示", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					if (res == JOptionPane.YES_OPTION) {
						if (goodId > maxid) {
							StoTransDao1 std = new StoTransDao1();
							Good good = new Good();
							good.setGoods_id(goodId);
							std.delGood(good);
							// 删除成功提示语句
							JOptionPane.showMessageDialog(null, "删除成功！");
							tms.removeRow(rows);
						} else {
							// 删除成功提示语句
							JOptionPane.showMessageDialog(null, "删除成功！");
							tms.removeRow(rows);
						}
					} else {
						// 保存失败警告
						JOptionPane.showMessageDialog(null, "删除失败！", "删除操作提示", JOptionPane.WARNING_MESSAGE);
					}
					// 获取最大行数
					int maxRows = tables.getRowCount();
					// 商品的总价重置为0
					purAllPrice = 0;
					// 遍历
					for (int i = 0; i < maxRows; i++) {
						// 得到每一行的总金额
						double perGoodPrice = (double) tables.getValueAt(i, 6);
						purAllPrice += perGoodPrice;
					}
					tfallmoney.setText("" + purAllPrice);
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(null, "请选择要删除的商品！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// 商品修改按钮
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rows = tables.getSelectedRow();
				if (rows != -1) {
					// 商品修改事件
					new PurchaseOrUpdate(tms, tables);
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(null, "请选择要修改的商品信息！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// 确定按钮
		btnSure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 更新采购订单表,生成采购详单表
				// 获取总共多少行
				int allrow = tables.getRowCount();
				if (allrow == 0) {
					// 输入失败警告
					JOptionPane.showMessageDialog(null, "请选择商品加入采购订单！", "操作提示", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						pur_id = Integer.parseInt(lapurid.getText());
						if (tfsupply.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "供应商不能为空", "操作提示", JOptionPane.WARNING_MESSAGE);
						} else {
								pur_supplyName = tfsupply.getText();
						}
						pur_date = Date.valueOf(newday);
						pur_pay = Double.parseDouble(tfallmoney.getText());
						double pur_RealMoney = Double.parseDouble(tfRealMoney.getText());
						// 判断实付金额
						if (pur_pay > pur_RealMoney) {
							JOptionPane.showMessageDialog(null, "实付金额不能小于应付金额", "操作提示", JOptionPane.WARNING_MESSAGE);
							tfRealMoney.setText("");
						} else if (pur_RealMoney <= 0) {
							JOptionPane.showMessageDialog(null, "实付金额不能小于零", "操作提示", JOptionPane.WARNING_MESSAGE);
							tfRealMoney.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "找零" + (pur_RealMoney - pur_pay) + "元");
							pur_empName = empList.getSelectedItem().toString();
							int statas = 0;
							pur_mark = ftmark.getText();
							SupplyDao1 sud = new SupplyDao1();
							int sup_id = sud.getSupplyname(pur_supplyName);
							EmployeeDao1 empd = new EmployeeDao1();
							int emp_id = empd.getEmployeename(pur_empName);
							// 连接数据库
							PurchaseOrderDao1 purd = new PurchaseOrderDao1();
							// 新建供应商对象
							PurchaseOrder pur = new PurchaseOrder(sup_id, pur_date, pur_pay, emp_id, statas, pur_mark);
							pur.setPur_id(pur_id);
							purd.updatePurchaseOrder(pur);
							// 新增详单表
							for (int i = 0; i < allrow; i++) {
								pDet_purId = Integer.parseInt(lapurid.getText());// 采购订单编号
								pDet_goodId = Integer.parseInt(tms.getValueAt(i, 0).toString());// 商品编号
								pDet_number = Integer.parseInt(tms.getValueAt(i, 5).toString());// 采购数量
								pDet_goodPrice = Double.parseDouble(tms.getValueAt(i, 6).toString());// 采购商品总金额
								pDet_status = 1;// 采购状态，是否入库
								pDet_mark = tms.getValueAt(i, 11).toString();
								PurDetail purdet = new PurDetail(pDet_purId, pDet_goodId, pDet_number, pDet_goodPrice,
										pDet_status, pDet_mark);
								purdet.setpDet_id(pDet_id);
								purd.addPurDetail(purdet);
							}
							JOptionPane.showMessageDialog(null, "采购成功");
							PurchaseOrderView.this.dispose();
							purAllPrice = 0;
						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的实付金额", "操作提示", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		// 取消按钮事件
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 取消按钮事件
				int statas = 0;
				SupplyDao1 sud = new SupplyDao1();
				int sup_id = sud.getSupplyname(pur_supplyName);
				EmployeeDao1 empd = new EmployeeDao1();
				int emp_id = empd.getEmployeename(pur_empName);
				// 连接数据库
				PurchaseOrderDao1 purd = new PurchaseOrderDao1();
				// 新建供应商对象
				PurchaseOrder pur = new PurchaseOrder(sup_id, pur_date, pur_pay, emp_id, statas, pur_mark);
				pur.setPur_id(pur_id);
				purd.delPurchaseOrder(pur);
				PurchaseOrderView.this.dispose();
			}
		});
		// 供应商全部查询
		btnsup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SupplyFrame();
			}
		});
	}

	/**
	 * 供应商显示在选择框 内部类
	 * 
	 * @author 熊晨晨
	 *
	 */
	class SupplyFrame extends JFrame {
		private JScrollPane spmain;// 滚动面板
		private DefaultTableModel tm;// 创建表模型
		private JTable table;// 表格对象

		/**
		 * 构造方法
		 */
		public SupplyFrame() {
			this.init();
			this.addpanel();
			setUndecorated(true);
			// 窗口可视化
			this.setVisible(true);
		}

		/**
		 * 初始化页面
		 */
		public void init() {
			// 设置窗口大小
			this.setSize(400, 120);
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
			this.setTitle("进货商查询");
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		/**
		 * 创建面板
		 */
		public void addpanel() {
			// 创建表格
			String[] str = { "公司名称", "联系人", "联系电话", "合作状态" };
			// 获取数据库数据
			PurchaseDao1 supd = new PurchaseDao1();
			List<Supply> lsup = supd.getSupply();
			Object[][] rows = new Object[lsup.size()][];
			for (int i = 0; i < rows.length; i++) {
				// 获取供应商集合对象
				Supply sup = lsup.get(i);
				String status = sup.getSup_status() == 0 ? "保持合作" : "解除合约";
				// 将对象转为数组存储
				Object[] obj = { sup.getSup_name(), sup.getSup_linkMan(), sup.getSup_phone(), status };
				// 给二维数组赋值
				rows[i] = obj;
			}
			tm = new DefaultTableModel(rows, str);
			table = new JTable(tm);
			table = new MTable(rows, str);
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						int row = table.getSelectedRow();
						int column = table.getAutoResizeMode();
						table.getValueAt(row, column);
						supplyName = tm.getValueAt(row, 0).toString();
						tfsupply.setText(supplyName);
						SupplyFrame.this.dispose();
					}
				}
			});
			spmain = new JScrollPane(table);
			spmain.setBounds(0, 0, 400, 330);
			// 添加进窗口
			this.add(spmain);
		}
	}

	public static void main(String[] args) {
		new PurchaseOrderView();
	}
}
