package cn.sell;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;

/**
 * 删 商品退货
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import cn.model.sell.RefunddetailsOrder;
import cn.view.purchase.MTable;

/**
 * 1.日期:2017-08-18
 * 2.主类功能:
 * a.商品退货面板上表双击全退功能
 * （根据时间查找退货）
 * （根据订单号模糊查询选择退货）
 * b.商品退货面板下表双击选择性退货功能
 * c.退货查询面板上查询出订单的基本信息
 * d.退货查询面板下查询出订单的详细信息
 * （根据时间查查询）
 * （根据订单号模糊查询）
 * @author 潘忠辉
 *
 */
public class Merchandise_returns extends JFrame {
	JSplitPane reSplitupanddown;// 退货选项卡创建上下分割面板
	private JTable reupperform;// 退货选项卡上表格对象
	private DefaultTableModel reupperdm;// 退货选项卡上边创建表模型
	private JTable redownform;// 退货选项卡下表格对象
	private DefaultTableModel redowndm;// 退货选项卡下边创建表模型
	private JScrollPane redownrollingpanel;;// 退货选项卡 创建下边滚动面板
	private JScrollPane reupperrollingpanel;// 退货选项卡创建上边滚动面板
	private JPanel reupperrolling;// 退货选项卡上面板
	private JPanel redownrollin;// 退货选项卡下面板
	private JLabel requerytime;// 退货选项卡查询时间
	private JTextField reChoicetime;// 退货选项卡选择时间
	private JButton requeryButton;// 退货选项卡 时间查询按钮
	private JLabel reinputOrder;// 退货选项卡输入订单
	private JTextField reOrderinputframe;// 退货选项卡订单输入框
	private JButton reOrderqueryButton;// 退货选项卡订单号查询按钮
	private JTextField reNumbers;// 退货选项卡订单号显示框
	int reid;// 退货选项卡单据号
	String reCustomer;// 退货选项卡客户

	private JPanel retitlePanel;// 退货选项卡标题面板
	private JPanel rebuttonpanel;// 退货选项卡按钮面板
	private JLabel reupTotal;// 商品退货上表总数
	private JLabel retotalColle;// 商品退货上表总数金额
	private JPanel reUpsummary;// 上汇总面板
	private JPanel redowummary;// 下汇总
	private JLabel redowTotal;// 下表总数
	private JLabel redowtalColle;// 下表总数金额
	// ------------------------------------------------------------
	private JSplitPane quSplitupanddown;// 退货选项卡创建上下分割面板
	private JTable quupperform;// 退货选项卡上表格对象
	private DefaultTableModel quupperdm;// 退货选项卡上边创建表模型
	private JTable qudownform;// 退货选项卡下表格对象
	private DefaultTableModel qudowndm;// 退货选项卡下边创建表模型
	private JScrollPane qudownrollingpanel;;// 退货选项卡 创建下边滚动面板
	private JScrollPane quupperrollingpanel;// 退货选项卡创建上边滚动面板
	private JPanel quupperrolling;// 退货选项卡上面板
	private JPanel qudownrollin;// 退货选项卡下面板
	private JLabel ququerytime;// 退货选项卡查询时间
	private JTextField quChoicetime;// 退货选项卡选择时间
	private JButton ququeryButton;// 退货选项卡 时间查询按钮
	private JLabel quinputOrder;// 退货选项卡输入订单
	private JTextField quOrderinputframe;// 退货选项卡订单输入框
	private JButton quOrderqueryButton;// 退货选项卡订单号查询按钮
	private JTextField quNumbers;// 退货选项卡订单号显示框
	int quid;// 退货选项卡单据号
	String quCustomer;// 退货选项卡客户
	// int qugoodsid;// 退货选项卡商品编号
	private JPanel qutitlePanel;// 退货选项卡标题面板
	private JPanel qubuttonpanel;// 退货选项卡按钮面板
	private JLabel quupTotal;// 商品退货上表总数
	private JLabel qutotalColle;// 商品退货上表总数金额
	private JPanel quUpsummary;// 上汇总面板
	private JPanel qudowummary;// 下汇总
	private JLabel qudowTotal;// 商品退货下表总数
	private JLabel qudowtalColle;// 商品退货下表总数金额
	// ------------------------------------------------------------
	SalesDao2 dao = new SalesDao2();
	Format gs = new Format();// 格式
	JSplitPane queryreturn;// 查询退货分割面板
	JTabbedPane tabpanel;// 选项卡面板
	JPanel mainpanel;// 主面板
	// 初始化

	public Merchandise_returns() {
		this.inin();
		Tabpanel();
		clickEvent();
		// 设置窗口可见
		this.setVisible(true);

	}

	public void inin() {
		// 设置窗口标题
		this.setTitle("商品退货");
		// 设置窗口大小
		this.setSize(815, 530);
		// 设置显示窗口的位置
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		// 设置窗口关闭
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void Tabpanel() {
		// 设置面板对象(商品退货上)
		reupperrolling = new JPanel();
		// 布局为null(商品退货上)
		reupperrolling.setLayout(null);
		// 设置面板对象(商品退货下)
		redownrollin = new JPanel();
		// 布局为null(商品退货下)
		redownrollin.setLayout(null);
		// 设置面板对象(退货查询上)
		quupperrolling = new JPanel();
		quupperrolling.setLayout(null);
		// 设置面板对象(退货查询下)
		qudownrollin = new JPanel();
		qudownrollin.setLayout(null);
		tabpanel = new JTabbedPane(JTabbedPane.TOP);// 显示在上方
		tabpanel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabpanel.setBounds(0, 0, 812, 520);
		// 主面板
		mainpanel = new JPanel();
		mainpanel.setLayout(null);
		mainpanel.add(tabpanel);
		// 创建分割面板对象
		reSplitupanddown = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		// 调用左右面板方法
		this.classupperrolling();
		this.classdownrollin();
		reSplitupanddown.add(reupperrolling, JSplitPane.TOP);
		reSplitupanddown.add(redownrollin, JSplitPane.BOTTOM);
		reSplitupanddown.setDividerLocation(280);
		reSplitupanddown.setEnabled(false);
		// 销售查询退货面板上
		this.Salesqueryreturnspanel();
		// 销售查询退货面板下
		this.Salesinquiriesunderthepanel();
		quSplitupanddown = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		quSplitupanddown.add(quupperrolling, JSplitPane.TOP);
		quSplitupanddown.add(qudownrollin, JSplitPane.BOTTOM);
		quSplitupanddown.setDividerLocation(280);
		quSplitupanddown.setEnabled(false);
		tabpanel.add("商品退货", reSplitupanddown);
		tabpanel.add("退货查询", quSplitupanddown);
		this.add(tabpanel);
	}

	// 销售退货上
	public void classupperrolling() {
		requerytime = gs.createLabel("查询时间:", 25, 10, 100, 25, "宋体", 14);

		reChoicetime = new JTextField("单击选择时间");
		DateChooser dateChoosers = DateChooser.getInstance("yyyy-MM-dd");
		dateChoosers.register(reChoicetime);
		reChoicetime.setBounds(100, 10, 160, 25);
		// 订单号查询按钮
		requeryButton = gs.createButton("查询", 280, 10, 60, 25, "宋体", 14);

		reinputOrder = gs.createLabel("请输入订单号:", 450, 10, 100, 25, "宋体", 14);
		reOrderinputframe = gs.createTextField(null, 540, 10, 160, 25, "宋体", 14, Color.white);
		// 订单号查询按钮
		reOrderqueryButton = gs.createButton("查询", 720, 10, 60, 25, "宋体", 14);
		// 创建表格
		String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "单据类型", "备注" };
		List<RefunddetailsOrder> lststu = dao.SeeRefundOrder();
		Object[][] rowsellOrderform = new Object[lststu.size()][];
		for (int i = 0; i < rowsellOrderform.length; i++) {
			RefunddetailsOrder sb = lststu.get(i);
			Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
					sb.getBilltype(), sb.getSell_mark() };
			rowsellOrderform[i] = obj;
		}
		reupperdm = new DefaultTableModel(rowsellOrderform, strsellOrderform);
		reupperform = new JTable(reupperdm);
		reupperform = new MTable(reupperdm);
		reupperrollingpanel = new JScrollPane(reupperform);
		reupperrollingpanel.setBounds(5, 105, 790, 140);
		double money = 0;
		for (int i = 0; i < reupperform.getRowCount(); i++) {
			money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
		}
		// 商品退货上表总数
		reupTotal = gs.createLabel("总单数:" + reupperform.getRowCount(), 5, 5, 100, 20, "宋体", 14);
		// 商品退货上表总数金额
		retotalColle = gs.createLabel("总计:" + money, 395, 5, 100, 20, "宋体", 14);
		retitlePanel = gs.createPanel(5, 5, 790, 45);
		JLabel title = gs.createLabel("商品退货", 365, 10, 100, 25, "宋体", 16);
		// 按钮面板
		rebuttonpanel = gs.createPanel(5, 55, 790, 45);
		retitlePanel.add(title);
		// 汇总面板
		reUpsummary = gs.createPanel(5, 247, 790, 30);
		// 退款上表
		reUpsummary.add(retotalColle);
		reUpsummary.add(reupTotal);
		reupperrolling.add(reUpsummary);
		reupperrolling.add(retitlePanel);
		reupperrolling.add(rebuttonpanel);
		rebuttonpanel.add(requerytime);
		rebuttonpanel.add(reChoicetime);
		rebuttonpanel.add(requeryButton);
		rebuttonpanel.add(reinputOrder);
		rebuttonpanel.add(reOrderinputframe);
		rebuttonpanel.add(reOrderqueryButton);
		reupperrolling.add(reupperrollingpanel);
		this.add(reupperrolling);
	}

	// 销售退货下
	public void classdownrollin() {
		// 提示文字
		JLabel lbmain = new JLabel("销售单据详细信息：");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("微软雅黑", Font.BOLD, 13));
		lbmain.setForeground(Color.black);
		reNumbers = new JTextField("ID:" + reid + reCustomer);
		reNumbers.setBounds(130, 0, 200, 25);
		reNumbers.setFont(new Font("微软雅黑", Font.BOLD, 12));
		MatteBorder border1 = new MatteBorder(0, 0, 0, 0, Color.RED);
		reNumbers.setBorder(border1);
		// 创建表格
		String[] str = { "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小", "商品状态" };
		redowndm = new DefaultTableModel(null, str);
		redownform = new JTable(redowndm);
		redownform = new MTable(redowndm);
		redownrollingpanel = new JScrollPane(redownform);
		redownrollingpanel.setBounds(5, 30, 790, 130);
		// 商品退货下表总数
		redowTotal = gs.createLabel("总单数:" + 0, 5, 0, 100, 20, "宋体", 12);
		// 商品退货下表总数金额
		redowtalColle = gs.createLabel("小计:" + 0.0, 505, 0, 100, 20, "宋体", 12);
		// 汇总面板
		redowummary = gs.createPanel(5, 160, 790, 20);
		redowummary.add(redowTotal);
		redowummary.add(redowtalColle);
		redownrollin.add(redowummary);
		redownrollin.add(redownrollingpanel);
		redownrollin.add(reNumbers);
		redownrollin.add(lbmain);
		this.add(redownrollin);
	}

	// 销售查询退货面板上
	private void Salesinquiriesunderthepanel() {
		ququerytime = gs.createLabel("查询时间:", 25, 10, 100, 25, "宋体", 14);

		quChoicetime = new JTextField("单击选择时间");
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(quChoicetime);
		quChoicetime.setBounds(100, 10, 160, 25);
		// 订单号查询按钮
		ququeryButton = gs.createButton("查询", 280, 10, 60, 25, "宋体", 14);

		quinputOrder = gs.createLabel("请输入订单号:", 450, 10, 100, 25, "宋体", 14);
		quOrderinputframe = gs.createTextField(null, 540, 10, 160, 25, "宋体", 14, Color.white);
		// 订单号查询按钮
		quOrderqueryButton = gs.createButton("查询", 720, 10, 60, 25, "宋体", 14);
		// 创建表格
		String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "单据类型", "备注" };
		List<RefunddetailsOrder> lststu = dao.viewSalesOrderStatus();
		Object[][] rowsellOrderform = new Object[lststu.size()][];
		for (int i = 0; i < rowsellOrderform.length; i++) {
			RefunddetailsOrder sb = lststu.get(i);
			Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
					sb.getBilltype(), sb.getSell_mark() };
			rowsellOrderform[i] = obj;
		}
		quupperdm = new DefaultTableModel(rowsellOrderform, strsellOrderform);
		quupperform = new JTable(quupperdm);
		quupperform = new MTable(quupperdm);
		quupperrollingpanel = new JScrollPane(quupperform);
		quupperrollingpanel.setBounds(5, 105, 790, 140);
		double money = 0;
		for (int i = 0; i < quupperform.getRowCount(); i++) {
			money += Double.parseDouble(quupperdm.getValueAt(i, 3).toString());
		}
		// 商品退货上表总数
		quupTotal = gs.createLabel("总单数:" + quupperform.getRowCount(), 5, 5, 100, 20, "宋体", 14);
		// 商品退货上表总数金额
		qutotalColle = gs.createLabel("总计:" + money, 395, 5, 100, 20, "宋体", 14);
		// 标题面板
		qutitlePanel = gs.createPanel(5, 5, 790, 45);
		JLabel title = gs.createLabel("退货查询", 365, 10, 100, 25, "宋体", 16);
		// 按钮面板
		qubuttonpanel = gs.createPanel(5, 55, 790, 45);
		qutitlePanel.add(title);
		// 汇总面板
		quUpsummary = gs.createPanel(5, 247, 790, 30);
		// 退款上表
		quUpsummary.add(qutotalColle);
		quUpsummary.add(quupTotal);
		quupperrolling.add(quUpsummary);
		quupperrolling.add(qutitlePanel);
		quupperrolling.add(qubuttonpanel);
		quupperrolling.add(quupperrollingpanel);
		// 按钮
		qubuttonpanel.add(ququerytime);
		qubuttonpanel.add(quChoicetime);
		qubuttonpanel.add(ququeryButton);
		qubuttonpanel.add(quinputOrder);
		qubuttonpanel.add(quOrderinputframe);
		qubuttonpanel.add(quOrderqueryButton);

		this.add(quupperrolling);

	}

	// 销售查询退货面板下
	private void Salesqueryreturnspanel() {
		// 提示文字
		JLabel lbmain = new JLabel("销售单据详细信息：");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("微软雅黑", Font.BOLD, 13));
		lbmain.setForeground(Color.black);
		quNumbers = new JTextField("ID:" + quid + quCustomer);
		quNumbers.setBounds(130, 0, 200, 25);
		quNumbers.setFont(new Font("微软雅黑", Font.BOLD, 12));
		MatteBorder border1 = new MatteBorder(0, 0, 0, 0, Color.RED);
		quNumbers.setBorder(border1);
		// 创建表格
		String[] str = { "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小", "商品状态" };
		qudowndm = new DefaultTableModel(null, str);
		qudownform = new JTable(qudowndm);
		qudownform = new MTable(qudowndm);
		qudownrollingpanel = new JScrollPane(qudownform);
		qudownrollingpanel.setBounds(5, 30, 790, 130);
		double money = 0;
		int number = 0;
		for (int i = 0; i < qudownform.getRowCount(); i++) {
			money += Double.parseDouble(qudowndm.getValueAt(i, 5).toString());
			number += Integer.parseInt(qudowndm.getValueAt(i, 4).toString());
		}
		// 商品退货下表总数
		qudowTotal = gs.createLabel("总单数:" + qudownform.getRowCount(), 5, 0, 100, 20, "宋体", 12);
		// 商品退货下表总数金额
		qudowtalColle = gs.createLabel("小计:" + money, 505, 0, 100, 20, "宋体", 12);
		// 汇总面板
		qudowummary = gs.createPanel(5, 160, 790, 20);
		qudowummary.add(qudowTotal);
		qudowummary.add(qudowtalColle);
		qudownrollin.add(qudowummary);
		qudownrollin.add(qudownrollingpanel);
		qudownrollin.add(quNumbers);
		qudownrollin.add(lbmain);
		this.add(qudownrollin);

	}

	public void clickEvent() {
		/**
		 * 上表格点击事件
		 */
		reupperform.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = reupperform.getSelectedRow();// 获取行
				if (e.getClickCount() == 1) {
					reid = Integer.parseInt(reupperform.getValueAt(row, 0).toString());
					reCustomer = reupperform.getValueAt(row, 2).toString();
					reNumbers.setText("ID:" + reid + "	Name:" + reCustomer);
					// 点击根据id显示已出售详情表信息
					List<RefunddetailsOrder> lststu = dao.SeeRefunddetailsOrder(reid);
					Object[][] rowparticularsform = new Object[lststu.size()][];
					for (int i = 0; i < rowparticularsform.length; i++) {
						RefunddetailsOrder sb = lststu.get(i);
						// "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小"
						String status = sb.getsDet_status() == 0 ? "已销售" : "已退货";
						Object[] obj = { sb.getsDet_goodId(), sb.getGoods_name(), sb.getGoods_units(),
								sb.getGoods_sellPrice(), sb.getsDet_number(), sb.getsDet_goodPrice(),
								sb.getGoods_size(), status };
						rowparticularsform[i] = obj;
					}
					String[] str = { "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小", "商品状态" };
					redowndm.setDataVector(rowparticularsform, str);
					double money = 0;
					int number = 0;
					for (int i = 0; i < redownform.getRowCount(); i++) {
						money += Double.parseDouble(redowndm.getValueAt(i, 5).toString());
						number += Integer.parseInt(redowndm.getValueAt(i, 4).toString());
					}
					redowTotal.setText("总单数:" + redownform.getRowCount());
					redowtalColle.setText("小计:" + money);
				} else if (e.getClickCount() == 2) {
					Object[] options = { "确定", "取消" };
					int response = JOptionPane.showOptionDialog(null, "请确认是否退款", "提示", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					int sup = 0;
					if (response == 0) {
						for (int i = 0; i < redownform.getRowCount(); i++) {
							sup = dao.update((int) (redownform.getValueAt(i, 4)),
									Integer.parseInt(redownform.getValueAt(i, 0).toString()),
									Integer.parseInt(reupperform.getValueAt(row, 0).toString()));
						}
						if (sup > 0) {
							redowndm.setRowCount(0);
							JOptionPane.showMessageDialog(null, "退货成功！");
						} else {
							JOptionPane.showMessageDialog(null, "退货失败！", "操作提示", JOptionPane.WARNING_MESSAGE);
						}
						// 重置表格
						String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "单据类型", "备注" };
						List<RefunddetailsOrder> lststu = dao.SeeRefundOrder();
						Object[][] rowsellOrderform = new Object[lststu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lststu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						reupTotal.setText("总单数:" + reupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < reupperform.getRowCount(); i++) {
							money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
						}
						retotalColle.setText("总计:" + money);
						redowTotal.setText("总单数:" + 0);
						redowtalColle.setText("小计:" + 0.0);
					}
				}
			}
		});
		/**
		 * 下表格点击事件
		 */
		redownform.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int sup = 0;
				int row = redownform.getSelectedRow();// 获取行
				if (e.getClickCount() == 2) {
					Object[] options = { "确定", "取消" };
					int response = JOptionPane.showOptionDialog(null, "请确认是否退款", "提示", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					if (response == 0) {
						if (redowndm.getRowCount() > 1) {
							// 数据库的信息
							int goodup = Integer.parseInt(redownform.getValueAt(row, 4).toString());
							int goodid = Integer.parseInt(redownform.getValueAt(row, 0).toString());
							sup = dao.updetails(goodup, goodid, reid);
						} else if (redowndm.getRowCount() == 1) {
							sup = dao.update(Integer.parseInt(redownform.getValueAt(row, 4).toString()),
									Integer.parseInt(redownform.getValueAt(row, 0).toString()), reid);
							String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "单据类型", "备注" };
							List<RefunddetailsOrder> lststu = dao.SeeRefundOrder();
							Object[][] rowsellOrderform = new Object[lststu.size()][];
							for (int i = 0; i < rowsellOrderform.length; i++) {
								RefunddetailsOrder sb = lststu.get(i);
								Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(),
										sb.getSell_profit(), sb.getBilltype(), sb.getSell_mark() };
								rowsellOrderform[i] = obj;
							}
							reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
							reNumbers.setText("ID:" + 0 + " Name:" + null);
							reupTotal.setText("总单数:" + reupperform.getRowCount());
							double money = 0;
							for (int i = 0; i < reupperform.getRowCount(); i++) {
								money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
							}
							retotalColle.setText("总计:" + money);
							redowTotal.setText("总单数:" + 0);
							redowtalColle.setText("小计:" + 0.0);
						}
						if (sup > 0) {
							redownform.setValueAt("已退货", row, 7);
							JOptionPane.showMessageDialog(null, "退货成功！");
						} else {
							JOptionPane.showMessageDialog(null, "退货失败！", "操作提示", JOptionPane.WARNING_MESSAGE);
						}
					}
					List<RefunddetailsOrder> lststu = dao.SeeRefunddetailsOrder(reid);
					Object[][] rowparticularsform = new Object[lststu.size()][];
					for (int i = 0; i < rowparticularsform.length; i++) {
						RefunddetailsOrder sb = lststu.get(i);
						// "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小"
						String status = sb.getsDet_status() == 0 ? "已销售" : "已退货";
						Object[] obj = { sb.getsDet_goodId(), sb.getGoods_name(), sb.getGoods_units(),
								sb.getGoods_sellPrice(), sb.getsDet_number(), sb.getsDet_goodPrice(),
								sb.getGoods_size(), status };
						rowparticularsform[i] = obj;
					}
					String[] str = { "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小", "商品状态" };
					redowndm.setDataVector(rowparticularsform, str);
					double money = 0;
					int number = 0;
					for (int i = 0; i < redownform.getRowCount(); i++) {
						money += Double.parseDouble(redowndm.getValueAt(i, 5).toString());
						number += Integer.parseInt(redowndm.getValueAt(i, 4).toString());
					}
					redowTotal.setText("总单数:" + redownform.getRowCount());
					redowtalColle.setText("小计:" + money);
				}
			}
		});

		/**
		 * 根据订单号查询
		 */
		reOrderqueryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				redowndm.setRowCount(0);
				reNumbers.setText("ID:" + 0 + "	Name:" + null);
				try {
					if (!reOrderinputframe.getText().equals("")) {
						int idname = Integer.parseInt(reOrderinputframe.getText());
						List<RefunddetailsOrder> lstu = dao.To_find_the(idname);
						Object[][] rowsellOrderform = new Object[lstu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lstu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "单据类型", "备注" };
						reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						reupTotal.setText("总单数:" + reupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < reupperform.getRowCount(); i++) {
							money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
						}
						retotalColle.setText("总计:" + money);
						qudowTotal.setText("总单数:" + 0);
						qudowtalColle.setText("小计:" + 0.0);
					} else {
						// 创建表格
						String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "单据类型", "备注" };
						List<RefunddetailsOrder> lststu = dao.SeeRefundOrder();
						Object[][] rowsellOrderform = new Object[lststu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lststu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						reupTotal.setText("总单数:" + reupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < reupperform.getRowCount(); i++) {
							money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
						}
						retotalColle.setText("总计:" + money);
						redowTotal.setText("总单数:" + 0);
						redowtalColle.setText("小计:" + 0.0);
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "请输入正确的订单编号");
				}

			}
		});
		/**
		 * 根据时间查询已售的订单
		 */

		requeryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reNumbers.setText("ID:" + 0 + "	Name:" + null);
				redowndm.setRowCount(0);
				try {
					if (reupperdm.getRowCount() == -1) {
						JOptionPane.showMessageDialog(null, "空，请重新选择日期");
					} else if (reupperdm.getRowCount() >= 0 || !reChoicetime.getText().equals("")) {
						List<RefunddetailsOrder> lstu = dao.stafftime(Date.valueOf(reChoicetime.getText()));
						// 订单信息二维数组
						Object[][] rowsellOrderform = new Object[lstu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lstu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "单据类型", "备注" };
						reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						reupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						reupTotal.setText("总单数:" + reupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < reupperform.getRowCount(); i++) {
							money += Double.parseDouble(reupperdm.getValueAt(i, 3).toString());
						}
						retotalColle.setText("总计:" + money);
						redowTotal.setText("总单数:" + 0);
						redowtalColle.setText("小计:" + 0.0);
					}
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, "请输入正确的日期");
				}

			}
		});
		// 查询上表格点击事件
		quupperform.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = quupperform.getSelectedRow();// 获取行
				if (e.getClickCount() == 1) {
					quid = Integer.parseInt(quupperform.getValueAt(row, 0).toString());
					quCustomer = quupperform.getValueAt(row, 2).toString();
					quNumbers.setText("ID:" + quid + "	Name:" + quCustomer);
					// 点击根据id显示已出售详情表信息
					List<RefunddetailsOrder> lststu = dao.ordernumberfordetailsof(quid);
					Object[][] rowparticularsform = new Object[lststu.size()][];
					for (int i = 0; i < rowparticularsform.length; i++) {
						RefunddetailsOrder sb = lststu.get(i);
						// "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小"
						String status = sb.getsDet_status() == 1 ? "已退货" : "已销售";
						Object[] obj = { sb.getsDet_goodId(), sb.getGoods_name(), sb.getGoods_units(),
								sb.getGoods_sellPrice(), sb.getsDet_number(), sb.getsDet_goodPrice(),
								sb.getGoods_size(), status };
						rowparticularsform[i] = obj;
					}
					String[] str = { "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小", "商品状态" };
					qudowndm.setDataVector(rowparticularsform, str);
					double money = 0;
					int number = 0;
					for (int i = 0; i < qudownform.getRowCount(); i++) {
						money += Double.parseDouble(qudowndm.getValueAt(i, 5).toString());
						number += Integer.parseInt(qudowndm.getValueAt(i, 4).toString());
					}
					qudowTotal.setText("总单数:" + qudownform.getRowCount());
					qudowtalColle.setText("小计:" + money);
				}
			}
		});
		/**
		 * 根据时间查询已售的订单
		 */

		ququeryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quNumbers.setText("ID:" + 0 + "	Name:" + null);
				qudowndm.setRowCount(0);
				try {
					if (quupperdm.getRowCount() == -1) {
						JOptionPane.showMessageDialog(null, "空，请重新选择日期");
					} else if (quupperdm.getRowCount() >= 0 || !quChoicetime.getText().equals("")) {
						List<RefunddetailsOrder> lstu = dao.checkthedocumentsaccordingtothetime(Date.valueOf(quChoicetime.getText()));
						// 订单信息二维数组
						Object[][] rowsellOrderform = new Object[lstu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lstu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "单据类型", "备注" };
						quupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						quupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						quupTotal.setText("总单数:" + quupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < quupperform.getRowCount(); i++) {
							money += Double.parseDouble(quupperdm.getValueAt(i, 3).toString());
						}
						qutotalColle.setText("总计:" + money);
						qudowTotal.setText("总单数:" + 0);
						qudowtalColle.setText("小计:" + 0.0);
					}
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, "请输入正确的日期");
				}

			}
		});
		//根据订单查询
		quOrderqueryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				qudowndm.setRowCount(0);
				quNumbers.setText("ID:" + 0 + "	Name:" + null);
				try {
					if (!quOrderinputframe.getText().equals("")) {
						int idname = Integer.parseInt(quOrderinputframe.getText());
						List<RefunddetailsOrder> lstu = dao.findorderaccordingtoID(idname);
						Object[][] rowsellOrderform = new Object[lstu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lstu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "单据类型", "备注" };
						quupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						quupTotal.setText("总单数:" + quupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < quupperform.getRowCount(); i++) {
							money += Double.parseDouble(quupperdm.getValueAt(i, 3).toString());
						}
						qutotalColle.setText("总计:" + money);
						qudowTotal.setText("总单数:" + 0);
						qudowtalColle.setText("小计:" + 0.0);
					} else {
						// 创建表格
						String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "单据类型", "备注" };
						List<RefunddetailsOrder> lststu = dao.viewSalesOrderStatus();
						Object[][] rowsellOrderform = new Object[lststu.size()][];
						for (int i = 0; i < rowsellOrderform.length; i++) {
							RefunddetailsOrder sb = lststu.get(i);
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getEmp_name(), sb.getSell_profit(),
									sb.getBilltype(), sb.getSell_mark() };
							rowsellOrderform[i] = obj;
						}
						quupperdm.setDataVector(rowsellOrderform, strsellOrderform);
						quupTotal.setText("总单数:" + quupperform.getRowCount());
						double money = 0;
						for (int i = 0; i < quupperform.getRowCount(); i++) {
							money += Double.parseDouble(quupperdm.getValueAt(i, 3).toString());
						}
						qutotalColle.setText("总计:" + money);
						qudowTotal.setText("总单数:" + 0);
						qudowtalColle.setText("小计:" + 0.0);
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "请输入正确的订单编号");
				}
			}
		});
	}

	public static void main(String[] args) {
		new Merchandise_returns();
	}

}
