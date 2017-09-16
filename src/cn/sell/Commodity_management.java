package cn.sell;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import cn.dao.sell.SalesDao2;
import cn.liuenci.swing.DateChooser;
import cn.model.sell.PurchaseOrderDetailGood;
import cn.model.sell.PurchaseOrderEmpSup;
import cn.model.sell.RefundOrder;
import cn.model.sell.RefunddetailsOrder;
import cn.view.purchase.MTable;

public class Commodity_management extends JFrame {
	private JPanel Majp;// 主面板
	private JPanel StoLejp;// 库存左面板
	private JPanel Striupjp;// 库存右上
	private JSplitPane Stuplojp;// 库存创建上下分割面板
	private JPanel Strilowjp;// 库存右下
	private JPanel Purupjp;// 上采购
	private JPanel PuLowjp;// 下采购
	private JPanel Salupjp;// 上销售
	private JPanel Salowjp;// 下销售
	private JTabbedPane tp;// 选项卡面板
	private JSplitPane StoSpl;// 库存创建左右分割面板
	private JSplitPane PurSpl;// 采购创建上下分割面板
	private JSplitPane SaSpl;// 销售创建上下分割面板
	// 采购
	private JLabel Purque;// 查询时间
	private JTextField PurCho;// 选择时间
	private JButton Purqueb;// 时间查询按钮
	private JLabel Purinpu;// 输入订单
	private JTextField PurOrder;// 订单输入框
	private JButton PurOrderqu;// 订单号查询按钮

	// ------------------------------
	// 销售
	private JLabel Saque;// 查询时间
	private JTextField SaCho;// 选择时间
	private JButton Saqueb;// 时间查询按钮
	private JLabel Sainpu;// 输入订单
	private JTextField SaOrder;// 订单输入框
	private JButton SaOrderqu;// 订单号查询按钮
	// ------------------------------
	// 采购
	private DefaultTableModel Puupdm;// 进货商品信息上表格
	private JTable Puupjl;// 对象
	private JScrollPane Puupsp;// 滚动面板
	private DefaultTableModel Pulodm;// 进货商品信息下表格
	private JTable Pulojl;// 对象
	private JScrollPane Pulosp;// 滚动面板
	// ------------------------------
	// 销售
	private DefaultTableModel Saupdm;// 进货商品信息上表格
	private JTable Saupjl;// 对象
	private JScrollPane Saupsp;// 滚动面板
	private DefaultTableModel Salodm;// 进货商品信息下表格
	private JTable Salojl;// 对象
	private JScrollPane Salosp;// 滚动面板

	// ------------------------------
	// 采购
	private JLabel Purintat;// 单据数标签
	private JLabel Purtotal;// 总价标签
	private JTextField PurNumb;// 提示单据号文本输入框
	int Purmai;// 上行总数
	double Purtomo = 0.0;// 上表总钱
	int Purid;// 放进文本里的单据号（PurNumb）
	// 下表数量总计
	JLabel PuTosepr;// 放下表售价总计
	JLabel Puloto;// 放下表总行数
	JLabel PuTopr;// 放下表总价总计Pupr
	JLabel Punum;// 单据数
	JLabel PuTtpp;// 进价总计
	// ----------------------------------
	// 销售
	private JLabel Saintat;// 单据数标签
	private JLabel Satotal;// 总价标签
	private JTextField SaNumb;// 提示单据号文本输入框
	int Samai;// 上行总数
	double Satomo = 0.0;// 上表总钱
	int Said;// 放进文本里的单据号（PurNumb）
	JLabel Sanum;
	JLabel Saquto;
	JLabel SaTopr;
	JLabel SaTosepr;
	// ------------------------------------------------
	// 日期

	// 格式
	Format gs = new Format();
	// dao层
	SalesDao2 dao = new SalesDao2();
	int row;

	public Commodity_management() {
		this.inin();
		Stock();
		Purchase();
		Sale();
		PuClickevent();
		SaClickevent();
		this.setVisible(true);

	}

	public void inin() {
		// 设置窗口标题
		this.setTitle("当前库存查询");
		// 设置窗口大小
		this.setSize(815, 530);
		// 设置显示窗口的位置
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		// 设置窗口关闭
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void Stock() {
		Majp = new JPanel();
		Majp.setLayout(null);
		Majp.setBounds(5, 5, 807, 520);
		tp = new JTabbedPane(JTabbedPane.TOP);// 显示在上方
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tp.setBounds(5, 5, 800, 510);
		Majp.add(tp);
		this.add(Majp);

	}

	public void Purchase() {
		PurSpl = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		Purupjp = new JPanel();
		Purupjp.setLayout(null);
		PurSpl.add(Purupjp, JSplitPane.TOP);
		PuLowjp = new JPanel();
		PuLowjp.setLayout(null);
		Purque = gs.createLabel("查询时间:", 25, 15, 100, 25, "宋体", 12);

		PurCho = new JTextField("单击选择时间");
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(PurCho);
		PurCho.setBounds(100, 15, 160, 25);
		// 订单号查询按钮
		Purqueb = gs.createButton("查询", 280, 15, 60, 25, "宋体", 12);

		Purinpu = gs.createLabel("请输入订单号:", 450, 15, 100, 25, "宋体", 12);
		PurOrder = new JTextField();
		PurOrder.setBounds(540, 15, 160, 25);
		// 订单号查询按钮
		PurOrderqu = gs.createButton("查询", 720, 15, 60, 25, "宋体", 12);
		// 创建表格
		String[] pugood = { "单据号", "供货商名称", "开单日期", "支付总金额", "经办人", "审核状态", "备注" };
		List<PurchaseOrderEmpSup> lstu = dao.queryOrdTime();
		Object[][] rowtop = new Object[lstu.size()][];
		for (int i = 0; i < rowtop.length; i++) {
			// 获取集合对象
			PurchaseOrderEmpSup purempsup = lstu.get(i);
			String purstatus = purempsup.getPur_status() == 0 ? "未审核"
					: (purempsup.getPur_status() == 1 ? "审核通过" : purempsup.getPur_status() == 2 ? "审核未通过" : "取消订单");
			// 将对象属性转为数组存储
			Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
					purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus, purempsup.getPur_mark() };
			// 给二位数组赋值
			rowtop[i] = obj;
		}
		Puupdm = new DefaultTableModel(rowtop, pugood);
		Puupjl = new JTable(Puupdm);
		Puupjl = new MTable(Puupdm);
		Puupsp = new JScrollPane(Puupjl);
		Puupsp.setBounds(10, 65, 780, 100);
		// 行总数
		Purmai = Puupdm.getRowCount();
		// 统计
		Purintat = gs.createLabel("单据数:" + Purmai, 10, 165, 130, 25, "宋体", 12);
		for (int j = 0; j < Purmai; j++) {
			Purtomo += Double.parseDouble(Puupdm.getValueAt(j, 3).toString());
		}
		Purtotal = gs.createLabel("总" + Purtomo, 345, 165, 130, 25, "宋体", 12);
		PurSpl.add(PuLowjp, JSplitPane.BOTTOM);
		PurSpl.setDividerLocation(190);
		PurSpl.setEnabled(false);
		JLabel lbmain = new JLabel("采购单据详细信息：");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("微软雅黑", Font.BOLD, 13));
		lbmain.setForeground(Color.black);
		PurNumb = new JTextField("ID:" + Purid);
		PurNumb.setBounds(130, 0, 200, 25);
		PurNumb.setFont(new Font("微软雅黑", Font.BOLD, 12));
		MatteBorder border1 = new MatteBorder(0, 0, 0, 0, Color.RED);
		PurNumb.setBorder(border1);
		String[] Sagood = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "总价", "售价", "仓库编号", "保质期", "最低库存", "订单状态" };
		Object[][] data = new Object[0][];
		Pulodm = new DefaultTableModel(null, Sagood);
		Pulojl = new JTable(Pulodm);
		Pulojl = new MTable(Pulodm);
		Pulosp = new JScrollPane(Pulojl);
		Pulosp.setBounds(10, 35, 780, 200);
		Punum = gs.createLabel("单据数:" + 0, 10, 240, 50, 25, "宋体", 12);
		PuTtpp = gs.createLabel("|" + 0.0, 265, 240, 50, 25, "宋体", 12);
		Puloto = gs.createLabel("|" + 0.0, 330, 240, 50, 25, "宋体", 12);
		PuTopr = gs.createLabel("|" + 0.0, 395, 240, 50, 25, "宋体", 12);
		PuTosepr = gs.createLabel("|" + 0, 460, 240, 50, 25, "宋体", 12);
		Purupjp.add(Purtotal);
		Purupjp.add(Purintat);
		Purupjp.add(Puupsp);
		Purupjp.add(Purque);
		Purupjp.add(PurCho);
		Purupjp.add(Purqueb);
		Purupjp.add(Purinpu);
		Purupjp.add(PurOrder);
		Purupjp.add(PurOrderqu);
		PuLowjp.add(lbmain);
		PuLowjp.add(PurNumb);
		PuLowjp.add(Pulosp);
		PuLowjp.add(Punum);
		PuLowjp.add(PuTtpp);
		PuLowjp.add(Puloto);
		PuLowjp.add(PuTosepr);
		PuLowjp.add(PuTopr);
		tp.add("进货商品信息", PurSpl);

	}

	public void Sale() {
		SaSpl = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		Salupjp = new JPanel();
		Salupjp.setLayout(null);
		SaSpl.add(Salupjp, JSplitPane.TOP);
		Salowjp = new JPanel();
		Salowjp.setLayout(null);
		Saque = gs.createLabel("查询时间:", 25, 15, 100, 25, "宋体", 12);

		SaCho = new JTextField("单击选择时间");
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(SaCho);
		SaCho.setBounds(100, 15, 160, 25);
		// 订单号查询按钮
		Saqueb = gs.createButton("查询", 280, 15, 60, 25, "宋体", 12);

		Sainpu = gs.createLabel("请输入订单号:", 450, 15, 100, 25, "宋体", 12);
		SaOrder = new JTextField();
		SaOrder.setBounds(540, 15, 160, 25);
		// 订单号查询按钮
		SaOrderqu = gs.createButton("查询", 720, 15, 60, 25, "宋体", 12);
		// 创建表格
		String[] upgood = { "销售订单编号", "销售日期", "客户名称", "收款金额", "单据类型", "经办人", "备注" };
		List<RefundOrder> lststu = dao.SeeRefundOrderx();
		Object[][] rowsellOrderform = new Object[lststu.size()][];
		for (int i = 0; i < rowsellOrderform.length; i++) {
			RefundOrder sb = lststu.get(i);
			Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getCustomername(), sb.getSell_profit(),
					sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
			rowsellOrderform[i] = obj;
		}
		Saupdm = new DefaultTableModel(rowsellOrderform, upgood);
		Saupjl = new JTable(Saupdm);
		Saupjl = new MTable(Saupdm);
		Saupsp = new JScrollPane(Saupjl);
		Saupsp.setBounds(10, 65, 780, 100);
		// 行总数
		Samai = Saupdm.getRowCount();
		// 统计
		Saintat = gs.createLabel("单据数:" + Samai, 10, 165, 130, 25, "宋体", 12);
		for (int j = 0; j < Samai; j++) {
			Satomo += Double.parseDouble(Saupdm.getValueAt(j, 3).toString());
		}
		Satotal = gs.createLabel("总" + Satomo, 350, 165, 130, 25, "宋体", 12);
		SaSpl.add(Salowjp, JSplitPane.BOTTOM);
		SaSpl.setDividerLocation(190);
		SaSpl.setEnabled(false);
		JLabel lbmain = new JLabel("销售单据详细信息：");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("微软雅黑", Font.BOLD, 13));
		lbmain.setForeground(Color.black);
		SaNumb = new JTextField("ID:" + Said);
		SaNumb.setBounds(130, 0, 200, 25);
		SaNumb.setFont(new Font("微软雅黑", Font.BOLD, 12));
		MatteBorder border1 = new MatteBorder(0, 0, 0, 0, Color.RED);
		SaNumb.setBorder(border1);
		String[] str = { "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小", "订单状态" };
		Salodm = new DefaultTableModel(null, str);
		Salojl = new JTable(Salodm);
		Salojl = new MTable(Salodm);
		Salosp = new JScrollPane(Salojl);
		Salosp.setBounds(10, 35, 780, 200);
		Sanum = gs.createLabel("单据数:" + 0, 10, 240, 50, 25, "宋体", 12);
		// 单价总计
		Saquto = gs.createLabel("|" + 0.0, 300, 240, 50, 25, "宋体", 12);
		// 数量总计
		SaTopr = gs.createLabel("|" + 0, 395, 240, 50, 25, "宋体", 12);
		// 总金额
		SaTosepr = gs.createLabel("|" + 0.0, 495, 240, 50, 25, "宋体", 12);
		Salupjp.add(Saque);
		Salupjp.add(Sainpu);
		Salupjp.add(Saqueb);
		Salupjp.add(SaOrder);
		Salupjp.add(SaOrderqu);
		Salupjp.add(SaCho);
		Salupjp.add(Saupsp);
		Salupjp.add(Saintat);
		Salupjp.add(Satotal);
		Salowjp.add(lbmain);
		Salowjp.add(Sanum);
		Salowjp.add(SaTopr);
		Salowjp.add(SaTosepr);
		Salowjp.add(SaNumb);
		Salowjp.add(Salosp);
		Salowjp.add(SaTosepr);
		Salowjp.add(SaTopr);
		Salowjp.add(Saquto);
		tp.add("销售商品信息", SaSpl);
	}

	public void PuClickevent() {
		/**
		 * 行点击
		 */
		Puupjl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 1) {
					row = Puupjl.getSelectedRow();
					((DefaultTableModel) Pulojl.getModel()).getDataVector().clear(); // 清除表格数据
					List<PurchaseOrderDetailGood> lstugood = dao.queryTime((int) Puupjl.getValueAt(row, 0));
					Purid = (int) Puupjl.getValueAt(row, 0);
					PurNumb.setText("ID:" + Purid);
					// 商品信息二维数组
					Object[][] rows = new Object[lstugood.size()][];
					for (int i = 0; i < rows.length; i++) {
						// 获取集合对象
						PurchaseOrderDetailGood goodpur = lstugood.get(i);
						String status = goodpur.getpDet_status() == 0 ? "已入库"
								: (goodpur.getpDet_status() == 1 ? "未入库" : "已退货");
						// 将对象属性转为数组存储
						Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
								goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getpDet_number(),
								goodpur.getGoods_purPrise() * goodpur.getpDet_number(), goodpur.getGoods_sellPrice(),
								goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(), goodpur.getGoods_minNumber(),
								status };
						// 给二位数组赋值
						rows[i] = obj;
						Pulodm.addRow(obj);
					}
					int sum = Pulodm.getRowCount();
					// 总价
					double Pupr = 0.0;
					// 总数
					int Puto = 0;
					// 进价总计
					double Putoppr = 0.0;
					// 下表售价总计
					double Pusepr = 0.0;
					for (int i = 0; i < sum; i++) {
						Puto += (int) Pulodm.getValueAt(i, 5);
						Pupr += (double) Pulodm.getValueAt(i, 6);
						Putoppr += (double) Pulodm.getValueAt(i, 4);
						Pusepr += (double) Pulodm.getValueAt(i, 7);
					}
					PuTosepr.setText("|" + Pusepr);
					PuTtpp.setText("|" + Putoppr);
					Punum.setText("单据数:" + sum);
					Puloto.setText("|" + Puto);
					PuTopr.setText("|" + Pupr);

				}

			}
		});
		/**
		 * 订单查询
		 */
		PurOrderqu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!PurOrder.getText().equals("")) {
					int Order_number = Integer.parseInt(PurOrder.getText());
					((DefaultTableModel) Pulojl.getModel()).getDataVector().clear(); // 清除表格数据
					((DefaultTableModel) Puupjl.getModel()).getDataVector().clear(); // 清除表格数据
					List<PurchaseOrderEmpSup> lstu = dao.queryOrdTime(Order_number);
					Object[][] rowtop = new Object[lstu.size()][];
					for (int i = 0; i < rowtop.length; i++) {
						// 获取集合对象
						PurchaseOrderEmpSup purempsup = lstu.get(i);
						String purstatus = purempsup.getPur_status() == 0 ? "未审核"
								: (purempsup.getPur_status() == 1 ? "审核通过"
										: purempsup.getPur_status() == 2 ? "审核未通过" : "取消订单");
						// 将对象属性转为数组存储
						Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
								purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus, purempsup.getPur_mark() };
						// 给二位数组赋值
						rowtop[i] = obj;
						String[] upgood = { "销售订单编号", "销售日期", "客户名称", "收款金额", "单据类型", "经办人", "备注" };
						Puupdm.setDataVector(rowtop, upgood);
						// 获取
						Purid = (int) Puupjl.getValueAt(row, 0);

						PurNumb.setText("ID:" + Purid);

					}
					List<PurchaseOrderDetailGood> lstugood = dao.queryTime(Order_number);
					// 商品信息二维数组
					Object[][] rows = new Object[lstugood.size()][];
					for (int i = 0; i < rows.length; i++) {
						// 获取集合对象
						PurchaseOrderDetailGood goodpur = lstugood.get(i);
						String status = goodpur.getpDet_status() == 0 ? "已入库"
								: (goodpur.getpDet_status() == 1 ? "未入库" : "已退货");
						// 将对象属性转为数组存储
						Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
								goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getpDet_number(),
								goodpur.getGoods_purPrise() * goodpur.getpDet_number(), goodpur.getGoods_sellPrice(),
								goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(), goodpur.getGoods_minNumber(),
								status };
						// 给二位数组赋值
						rows[i] = obj;
						Pulodm.addRow(obj);

					}

					int sum = Pulodm.getRowCount();
					// 总价
					double Pupr = 0.0;
					// 总数
					int Puto = 0;
					// 进价总计
					double Putoppr = 0.0;
					// 下表售价总计
					double Pusepr = 0.0;
					for (int i = 0; i < sum; i++) {
						Puto += (int) Pulodm.getValueAt(i, 5);
						Pupr += (double) Pulodm.getValueAt(i, 6);
						Putoppr += (double) Pulodm.getValueAt(i, 4);
						Pusepr += (double) Pulodm.getValueAt(i, 7);
					}
					PuTosepr.setText("|" + Pusepr);
					PuTtpp.setText("|" + Putoppr);
					Punum.setText("单据数:" + sum);
					Puloto.setText("|" + Puto);
					PuTopr.setText("|" + Pupr);

				} else if (PurOrder.getText().equals("")) {
					List<PurchaseOrderEmpSup> lstu = dao.queryOrdTime();
					Object[][] rowtop = new Object[lstu.size()][];
					for (int i = 0; i < rowtop.length; i++) {
						// 获取集合对象
						PurchaseOrderEmpSup purempsup = lstu.get(i);
						String purstatus = purempsup.getPur_status() == 0 ? "未审核"
								: (purempsup.getPur_status() == 1 ? "审核通过"
										: purempsup.getPur_status() == 2 ? "审核未通过" : "取消订单");
						// 将对象属性转为数组存储
						Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
								purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus, purempsup.getPur_mark() };
						// 给二位数组赋值
						rowtop[i] = obj;
						Puupdm.addRow(obj);
						Pulodm.setRowCount(0);
					}
				}
			}
		});
		/**
		 * 时间查询
		 */
		Purqueb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!PurCho.getText().equals("")) {
					List<PurchaseOrderEmpSup> lstu = dao.queryOrdTime(Date.valueOf(PurCho.getText()));
					Object[][] rowtop = new Object[lstu.size()][];
					for (int i = 0; i < rowtop.length; i++) {
						// 获取集合对象
						PurchaseOrderEmpSup purempsup = lstu.get(i);
						String purstatus = purempsup.getPur_status() == 0 ? "未审核"
								: (purempsup.getPur_status() == 1 ? "审核通过"
										: purempsup.getPur_status() == 2 ? "审核未通过" : "取消订单");
						// 将对象属性转为数组存储
						Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
								purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus, purempsup.getPur_mark() };
						// 给二位数组赋值
						rowtop[i] = obj;
					}
					String[] upgood = { "销售订单编号", "销售日期", "客户名称", "收款金额", "单据类型", "经办人", "备注" };
					Puupdm.setDataVector(rowtop, upgood);
				}
			}
		});
	}

	public void SaClickevent() {
		/**
		 * 行点击
		 */
		Saupjl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = Saupjl.getSelectedRow();// 获取行
				if (e.getClickCount() == 1) {
					Said = Integer.parseInt(Saupjl.getValueAt(row, 0).toString());
					SaNumb.setText("ID:" + Said);
					List<RefunddetailsOrder> lststu = dao.SeeRefunddetailsOrderx(Said);
					Object[][] rowparticularsform = new Object[lststu.size()][];
					for (int i = 0; i < rowparticularsform.length; i++) {
						RefunddetailsOrder sb = lststu.get(i);
						String status = sb.getsDet_status() == 0 ? "已销售" : "已退回";
						Object[] obj = { sb.getsDet_goodId(), sb.getGoods_name(), sb.getGoods_units(),
								sb.getGoods_sellPrice(), sb.getsDet_number(), sb.getsDet_goodPrice(),
								sb.getGoods_size(), status };
						rowparticularsform[i] = obj;
					}
					String[] str = { "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小", "订单状态" };
					Salodm.setDataVector(rowparticularsform, str);
					int Sanumber = Salodm.getRowCount();// 销售下表的总行数
					double Sato = 0.0;
					int Sapr = 0;
					double Sasepr = 0.0;
					for (int i = 0; i < Sanumber; i++) {
						Sato += (double) Salodm.getValueAt(i, 3);
						Sapr += (int) Salodm.getValueAt(i, 4);
						Sasepr += (double) Salodm.getValueAt(i, 5);
					}
					Sanum.setText("单据数:" + Sanumber);
					Saquto.setText("|" + Sato);
					SaTopr.setText("|" + Sapr);
					SaTosepr.setText("|" + Sasepr);
				}
			}
		});
		/**
		 * 时间查询
		 */
		Saqueb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!SaCho.getText().equals("")) {
					Date sell_date = Date.valueOf(SaCho.getText());
					List<RefundOrder> lstu = dao.stafftimex(sell_date);
					Object[][] rowtop = new Object[lstu.size()][];
					for (int i = 0; i < rowtop.length; i++) {
						// 获取集合对象
						RefundOrder sb = lstu.get(i);
						// 将对象属性转为数组存储
						Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getCustomername(), sb.getSell_profit(),
								sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
						rowtop[i] = obj;
					}
					String[] upgood = { "销售订单编号", "销售日期", "客户名称", "收款金额", "单据类型", "经办人", "备注" };
					Saupdm.setDataVector(rowtop, upgood);
				}
			}
		});
		/**
		 * 订单查询
		 */
		SaOrderqu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!SaOrder.getText().equals("")) {
					((DefaultTableModel) Salojl.getModel()).getDataVector().clear(); // 清除表格数据
					((DefaultTableModel) Saupjl.getModel()).getDataVector().clear(); // 清除表格数据
					int idname = Integer.parseInt(SaOrder.getText());
					List<RefundOrder> lstu = dao.To_find_thex(idname);
					Object[][] rowtop = new Object[lstu.size()][];
					for (int i = 0; i < rowtop.length; i++) {
						// 获取集合对象
						RefundOrder sb = lstu.get(i);
						// 将对象属性转为数组存储
						Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getCustomername(), sb.getSell_profit(),
								sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
						rowtop[i] = obj;
					}
					String[] upgood = { "销售订单编号", "销售日期", "客户名称", "收款金额", "单据类型", "经办人", "备注" };
					Saupdm.setDataVector(rowtop, upgood);
				} else if(SaOrder.getText().equals("")){
					List<RefundOrder> lststu = dao.SeeRefundOrderx();
					Object[][] rowsellOrderform = new Object[lststu.size()][];
					for (int i = 0; i < rowsellOrderform.length; i++) {
						RefundOrder sb = lststu.get(i);
						Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getCustomername(), sb.getSell_profit(),
								sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
						rowsellOrderform[i] = obj;
						Saupdm.addRow(obj);
						Salodm.setRowCount(0);
					}
					
				}

			}
		});
	}

	public static void main(String[] args) {
		new Commodity_management();
	}
}
