package cn.sell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
/**
 * 增
 * 把选择好的商品生成订单
 * 销售主面板下子功能订单查询下一级商品销售
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
 * @author 潘忠辉 1.日期:2017-08-13 2.主类功能: a.选择商品 b.删除商品 c.修改商品数量 d.日期控件选择日期
 *         e.异常的捕获，通过警示框传递给用户熟知的信息 3.内部类功能 a.分割面板左边显示自己仓库的商品，右边显示已选择的商品
 *         b.商品编号模糊查询商品 c.已选择的商品提供修改数量，删除功能 d.选择完成通过表模型和表对象将值传递给主类。
 *         e.通过静态变量计算商品的总价格
 * 
 */
public class Sales_order extends JFrame {
	Format gs = new Format();// 格式
	SalesDao2 dao = new SalesDao2();// dao层
	private JPanel saleMainpanel = null;// 销售主面板
	private JButton add_goods = null;// 添加商品
	private JLabel customer_name = null;// 创建客户名称标签
	private JLabel custname = null;// 文本输入客户姓名
	private JLabel date_of_sale = null;// 销售日期标签
	String[] good = { "商品编号", "商品名称", "单位", "单价", "数量", "总金额" };// 表头
	private JLabel amount_receivable = null;// 应收金额标签
	static JLabel tfsupply1;// 静态文本订单总金额
	private JLabel paid_in_amount = null;// 实收金额标签
	private JTextField txte_Paid_in_amount;// 输入实收金额文本框
	private JLabel handler = null;// 经办人
	private JComboBox empName = null;// 下拉员工名
	private JLabel remarks = null;// 备注标签
	private JTextField txte_Remarks = null;// 备注文本框
	private JButton confirm = null;// 确认按钮
	private JButton cancel = null;// 取消按钮
	private JButton delete_goods = null;// 删除按钮
	private JButton modify_goods = null;// 修改
	private JTable sp1 = null;// 表格对象
	private DefaultTableModel closing_statement = null;// 创建表模型
	private JScrollPane spright = null;
	private JLabel order_number = null;// 订单号标签
	private JLabel txet_order_number = null;// 显示订单号标签
	private JTextField numText = null;// 修改数量文本框
	private JLabel latit = null;
	private double moneys = 0.0;// 订单总金额
	int addtb_sellOrder = 0;// 订单号
	int good_purPlanNum = 0;// 计划采购数量
	double good_purPlanPrice;// 商品的采购单价
	static double priceCount = 0.0;// 设置为静态变量，在多个窗口进行只变化
	private JTextField Text_Select_date = null;
	private JPanel catpan = null;// 目录面板
	private JPanel butpan = null;// 按钮面板
	private JPanel conpan = null;// 内容面板
	private JLabel lbTime;
	String newday;
	// 初始化

	public Sales_order() {
		this.inin();
		addTable();
		addAdd();
		clickEvent();
		// 设置窗口可见
		this.setVisible(true);

	}

	public void inin() {
		// 设置窗口标题
		this.setTitle("商品销售");
		// 设置窗口大小
		this.setSize(815, 530);
		// 设置显示窗口的位置
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		// 设置窗口关闭
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void addAdd() {
		// 销售主面板
		saleMainpanel = gs.createPanel(5, 5, 775, 450);
		// 客户名称
		customer_name = gs.createLabel("客户名称:", 48, 30, 120, 20, "宋体", 14);
		// 文本框输入客户名称
		custname = gs.createLabel("普通客户", 115, 30, 120, 20, "宋体", 12);
		// 销售日期标签
		date_of_sale = gs.createLabel("销售日期:", 555, 30, 80, 20, "宋体", 14);
		// 创建文本框
		// java.util.Date now = new java.util.Date();
		// Text_Select_date = new JTextField("单击选择日期");
		// Text_Select_date.setBounds(620, 30, 100, 20);
		// Text_Select_date.setBackground(new Color(238, 238, 238));
		// MatteBorder borders = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		// Text_Select_date.setBorder(borders);
		// DateChooser d = DateChooser.getInstance("yyyy-MM-dd");
		// d.register(Text_Select_date);
		// 创建文本框
		DateFormat dfTime = new SimpleDateFormat("yyyy-MM-dd");
		newday = dfTime.format(new java.util.Date());
		lbTime = gs.createLabel(newday, 620, 30, 100, 20, "宋体", 12);

		// 添加商品按钮
		add_goods = gs.createButton("添加商品", 30, 5, 90, 25, "宋体", 16);
		// 应收金额标签
		amount_receivable = gs.createLabel("应收金额:", 25, 10, 120, 20, "宋体", 12);
		// 创建应收金额文本框
		tfsupply1 = new JLabel("" + priceCount);
		tfsupply1.setBounds(80, 10, 100, 20);
		tfsupply1.setBackground(new Color(238, 238, 238));
		MatteBorder border = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		tfsupply1.setBorder(border);
		paid_in_amount = gs.createLabel("实收金额:", 190, 10, 120, 20, "宋体", 12);
		// 创建实收金额文本框
		txte_Paid_in_amount = new JTextField();
		txte_Paid_in_amount.setBounds(250, 10, 120, 20);
		txte_Paid_in_amount.setBackground(new Color(238, 238, 238));
		MatteBorder border2 = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		txte_Paid_in_amount.setBorder(border2);
		handler = new JLabel("经办人:");
		handler.setBounds(540, 10, 120, 20);
		// 创建文本框
		empName = new JComboBox();
		// 从数据库获取员工名
		List<Employee> employeeList = dao.query(2);
		for (int i = 0; i < employeeList.size(); i++) {
			empName.addItem(employeeList.get(i).getEmp_name());
		}
		empName.setBounds(600, 10, 120, 20);
		// 备注标签
		remarks = gs.createLabel("备     注", 25, 60, 120, 20, "宋体", 12);
		// 创建备注文本框
		txte_Remarks = new JTextField("");
		txte_Remarks.setBounds(80, 60, 290, 20);
		txte_Remarks.setBackground(new Color(238, 238, 238));
		MatteBorder border4 = new MatteBorder(0, 0, 1, 0, Color.BLACK);
		txte_Remarks.setBorder(border4);
		// 确认按钮
		confirm = gs.createButton("确认", 540, 60, 90, 25, "宋体", 13);
		// 取消按钮
		cancel = gs.createButton("取消", 650, 60, 90, 25, "宋体", 13);
		// 删除商品按钮
		delete_goods = gs.createButton("删除商品", 650, 5, 90, 25, "宋体", 16);
		// 修改商品按钮
		modify_goods = gs.createButton("修改商品", 540, 5, 90, 25, "宋体", 16);
		// 填入表格
		sp1 = new JTable(closing_statement);
		// 表格格式
		sp1 = new MTable(closing_statement);
		// 居中
		sp1.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		spright = new JScrollPane(sp1);
		spright.setBounds(10, 170, 790, 200);
		// 商品标签
		latit = gs.createLabel("商品销售", 340, 10, 180, 40, "宋体", 16);
		// 商品销售标签格式
		latit.setFont(new Font("微软雅黑", Font.BOLD, 24));
		// 订单号标签
		order_number = gs.createLabel("订单号:", 560, 10, 180, 40, "宋体", 15);
		order_number.setForeground(Color.red);
		// 查找最新生成的订单号
		addtb_sellOrder = dao.getMaxId();
		// 订单号标签
		txet_order_number = gs.createLabel("" + addtb_sellOrder, 615, 10, 180, 40, "宋体", 13);
		txet_order_number.setForeground(Color.red);
		// 目录面板
		catpan = gs.createPanel(10, 50, 790, 80);
		// 按钮面板
		butpan = gs.createPanel(10, 130, 790, 37);
		// 内容面板
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
	 * 订单生成 算价格
	 * 
	 * @author Administrator
	 *
	 */
	class updateNum extends JFrame {
		// 添加面板
		JButton updateConfirm = new JButton("确定");
		JButton updateCancel = new JButton("取消");
		// 修改数量面板
		JPanel numPanel;
		// 添加修改标签
		JLabel updateLabel;
		// 添加按钮面板
		JPanel btnPanel;

		// 构造方法
		public updateNum() {
			this.init();
			addPanel();
			addListener();
			setVisible(true);
		}

		// 初始化
		public void init() {
			setTitle("修改数量");
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

		// 添加面板
		public void addPanel() {
			// 添加面板
			numPanel = gs.createPanel(0, 10, 365, 80);
			numPanel.setLayout(null);
			numPanel.setBorder(BorderFactory.createEtchedBorder());
			// 添加修改标签
			updateLabel = gs.createLabel("修改数量:", 40, 30, 100, 30, "宋体", 20);
			// 添加数量文本框
			numText = gs.createTextField("", 160, 30, 100, 30, "宋体", 18, Color.white);
			// 添加按钮面板
			btnPanel = gs.createPanel(10, 100, 365, 40);
			btnPanel.setLayout(null);
			btnPanel.setBorder(BorderFactory.createEtchedBorder());
			// 添加按钮
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
			 * 修改数量确认按钮点击事件
			 */
			updateConfirm.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						good_purPlanNum = Integer.parseInt(numText.getText());
						if (good_purPlanNum <= 0) {
							JOptionPane.showMessageDialog(null, "数量不能为负数或者0");
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
									// 将总金额归零
									priceCount = 0;
									// 获取行的总数
									int mainFrameRows = closing_statement.getRowCount();
									for (int i = 0; i < mainFrameRows; i++) {
										// 获取每一行的总金额
										double rowPrice = (double) sp1.getValueAt(i, 5);
										priceCount += rowPrice;
									}
									tfsupply1.setText("" + priceCount);
									updateNum.this.dispose();
								} else {
									JOptionPane.showConfirmDialog(null, "库存没有您需要的数量请确定修改", "错误提示",
											JOptionPane.DEFAULT_OPTION);
								}
							}

						}
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的数字");
					}

				}
			});
			/**
			 * 修改数量取消按钮点击事件
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
	 * 点击事件
	 */
	public void clickEvent() {
		/**
		 * 添加商品
		 */
		add_goods.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Choose_goods(closing_statement);

			}
		});
		/**
		 * 确认按钮 1.实收金额要大于应收金额 2.swing商品数量不能大于数据库 2.1如果不满足条件返回修改
		 * 3.如果满足条件先把swing数据保存 4.生成销售订单跟销售详情订单（先生成销售订单）（在生成销售详情订单） 4.1循环添加销售详情订单
		 * 5.找到商品库存把数量弄出来并循环减去生成的订单商品数量
		 */
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date date = null;
				// 获得行的最大数量
				int mainFrameRows = closing_statement.getRowCount();
				if (mainFrameRows == 0) {
					JOptionPane.showMessageDialog(null, "请选择商品在生成订单");
				} else {
					try {
						// 当实付金额小于应付金额时
						if (Double.parseDouble(txte_Paid_in_amount.getText()) < priceCount) {
							JOptionPane.showMessageDialog(null, "请输入正确的应付金额，本超市概不赊账");
						} else {
							String sDet_mark = null;
							try {
								date = Date.valueOf(newday);
								SellOrder sellOrder = new SellOrder();
								// 获取订单经办人id
								Employee emp = dao.queryEmpID(empName.getSelectedItem().toString());
								SellOrder goods = new SellOrder(emp.getEmp_id(), date, priceCount, 0,
										txte_Remarks.getText());
								goods.setSell_id(addtb_sellOrder);
								int sel = dao.reviseSales(goods);
								for (int i = 0; i < mainFrameRows; i++) {
									// 循环修改数据库的商品数量
									int sDet_goodId;// 商品编号
									int sDet_number;// 商品数量
									sDet_goodId = Integer.parseInt(closing_statement.getValueAt(i, 0).toString());// 商品编号
									sDet_number = Integer.parseInt(closing_statement.getValueAt(i, 4).toString());// 数量
									int updateStoNum = dao.updateGoodNum(sDet_goodId, sDet_number);
									// 生成销售详情单
									double sDet_goodPrice = Double
											.parseDouble(closing_statement.getValueAt(i, 5).toString());// 商品总价
									int sDet_status = 0;// 状态
									sDet_mark = txte_Remarks.getText();// 备注
									// 通过ID把商品数量弄出来
									List<Good> number = dao.goodsnum(sDet_goodId);
									Object[][] rows = new Object[number.size()][];
									// 获取详单表的订单编号
									int pDetId = addtb_sellOrder;
									// 创建详单表对象
									SellDetail sellDetail = new SellDetail();
									sellDetail.setsDet_goodId(sDet_goodId);
									sellDetail.setsDet_sellId(pDetId);
									sellDetail.setsDet_number(sDet_number);
									sellDetail.setsDet_goodPrice(sDet_goodPrice);
									sellDetail.setsDet_status(sDet_status);
									sellDetail.setsDet_mark(sDet_mark);
									dao.addtb_sellDetail(sellDetail);
								}
								JOptionPane.showMessageDialog(null, "销售成功！");
								double a=Double.parseDouble(txte_Paid_in_amount.getText()) - priceCount;
								JOptionPane.showMessageDialog(null, "找零："+a);
								Sales_order.this.dispose();
								priceCount=0;
							} catch (IllegalArgumentException e1) {
								JOptionPane.showMessageDialog(null, "日期错误");
							}
						}
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "请填入正确的实收金额");
					}
				}
			}
		});
		/**
		 * 取消按钮事件
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
		 * 删除商品按钮事件
		 */
		delete_goods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 选择行
				int row = sp1.getSelectedRow();
				// 将总金额归零
				priceCount = 0;
				if (row > -1) {
					closing_statement.removeRow(row);
					// 获取行的总数
					int mainFrameRows = closing_statement.getRowCount();
					for (int i = 0; i < mainFrameRows; i++) {
						// 获取每一行的总金额
						double rowPrice = (double) sp1.getValueAt(i, 5);
						priceCount += rowPrice;
					}
					tfsupply1.setText("" + priceCount);
				}
			}
		});
		/**
		 * 修改按钮事件
		 */
		modify_goods.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取选中的行
				int row = sp1.getSelectedRow();
				if (row > -1) {
					new updateNum();
				}
			}
		});
	}

	public void addTable() {
		closing_statement = new DefaultTableModel(null, good);// good表头
	}
}
