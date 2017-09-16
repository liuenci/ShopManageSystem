package cn.sell;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import cn.dao.sell.SalesControl2;
import cn.dao.sell.SalesDao2;
import cn.liuenci.swing.DateChooser;
import cn.model.common.SellOrder;
import cn.model.common.Storage;
import cn.model.sell.Goods;
import cn.model.sell.RefundOrder;
import cn.model.sell.RefunddetailsOrder;
import cn.view.purchase.MTable;

/**
 * 
 * @author 潘忠辉
 *
 */
public class Inventory_query extends JFrame {
	JTable jt;
	DefaultTableModel tm1;// 创建表模型
	String[] good = { "商品编号", "商品名称", "单位", "库存量", "进价", "售价", "库存总值", "规格型号", "商品保质期", "备注" };
	JButton queryAllBtn = null;
	JScrollPane goodsSc = new JScrollPane();
	JScrollPane spright;
	JButton querySingleBtn;
	JTextField idText = null;
	JComboBox inputSto = null;
	int outSto_id = 0;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JTabbedPane tp;
	private JSplitPane Splitupanddown;
	private JPanel p4;
	private JLabel querytime;// 查询时间
	private JTextField Choicetime;// 选择时间
	private JButton queryButton;// 时间查询按钮
	private JLabel inputOrder;// 输入订单
	private JTextField Orderinputframe;// 订单输入框
	private JButton OrderqueryButton;// 订单号查询按钮
	private JTextField Numbers;// 订单号显示框
	private JTable upperform;// 上表格对象
	private DefaultTableModel upperdm;// 上边创建表模型
	private JTable downform;// 下表格对象
	private DefaultTableModel downdm;// 下边创建表模型
	private JScrollPane upperrollingpanel;// 创建上边滚动面板
	private JScrollPane downrollingpanel;;// 创建下边滚动面板
	private DefaultTableModel dm;

	int id;// 订单号
	String Customer;// 客户
	int goodsid;// 商品编号
	Date sell_date;// 时间
	// 初始化

	public Inventory_query() {
		this.inin();
		addCurrent_inventory_query();
		p2();
		p3();
		addRegisterListener();
		// 设置窗口可见
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

	public void addCurrent_inventory_query() {
		// 地步面板
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(5, 5, 807, 520);
		// 创建选项卡面板
		tp = new JTabbedPane(JTabbedPane.TOP);// 显示在上方
		tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tp.setBounds(5, 5, 800, 510);
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setBounds(5, 5, 800, 510);
		p3 = new JPanel();
		p3.setLayout(null);
		p3.setBounds(5, 5, 800, 510);
		// 将p2,p3放到选项卡面板中
		tp.add("库存查询", p2);
		// 将TP放进p1中
		p1.add(tp);
		this.add(p1);

	}

	public void p2() {
		SalesDao2 selquery = new SalesDao2();
		List<Goods> lststu = selquery.geCurrent_inventory_query();
		Object[][] rows = new Object[lststu.size()][];
		for (int i = 0; i < rows.length; i++) {
			// 获取集合中的商品清单对象
			Goods sb = lststu.get(i);
			// 将对象属性转为数组存储
			Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_number(),
					sb.getGoods_purPrise(), sb.getGoods_sellPrice(), sb.getWorth(), sb.getGoods_size(),
					sb.getGoods_keepDays(), sb.getGoods_mark() };
			// 给二维数组赋值
			rows[i] = obj;
		}
		inputSto = new JComboBox();
		// 从数据库获仓库名信息
		SalesControl2 Storage = new SalesControl2();
		List<Storage> lst = Storage.getStorage();
		Object[][] objrow = new String[lst.size()][];
		for (int i = 0; i < objrow.length; i++) {
			Storage sb = lst.get(i);
			// 给二维数组赋值;
			inputSto.addItem(sb.getName());
		}
		inputSto.setBounds(690, 20, 100, 20);
		tm1 = new DefaultTableModel(rows, good);// good表头
		jt = new JTable(tm1);
		jt = new MTable(rows, good);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		spright = new JScrollPane(jt);
		spright.setBounds(5, 120, 795, 380);
		queryAllBtn = new JButton("查询所有商品");
		queryAllBtn.setBounds(10, 15, 180, 30);
		querySingleBtn = new JButton("根据商品编号查询");
		querySingleBtn.setBounds(10, 50, 180, 30);
		idText = new JTextField();
		idText.setBounds(10, 85, 100, 30);
		p2.add(inputSto);
		p2.add(queryAllBtn);
		p2.add(idText);
		p2.add(queryAllBtn);
		p2.add(querySingleBtn);
		p2.add(spright);
	}

	public void p3() {
		Splitupanddown = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		p3 = new JPanel();
		p3.setLayout(null);
		Splitupanddown.add(p3, JSplitPane.TOP);
		p4 = new JPanel();
		p4.setLayout(null);
		Splitupanddown.add(p4, JSplitPane.BOTTOM);
		querytime = new JLabel("查询时间:");
		querytime.setBounds(25, 65, 100, 25);

		Choicetime = new JTextField("单击选择时间");
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(Choicetime);
		Choicetime.setBounds(100, 65, 160, 25);
		// 订单号查询按钮
		queryButton = new JButton("查询");
		queryButton.setBounds(280, 65, 60, 25);

		inputOrder = new JLabel("请输入订单号:");
		inputOrder.setBounds(450, 65, 100, 25);
		Orderinputframe = new JTextField();
		Orderinputframe.setBounds(540, 65, 160, 25);
		// 订单号查询按钮
		OrderqueryButton = new JButton("查询");
		OrderqueryButton.setBounds(720, 65, 60, 25);
		// 创建表格
		String[] strsellOrderform = { "销售订单编号", "销售日期", "经办人", "收款金额", "备注" };
		SalesDao2 selquery = new SalesDao2();
		List<SellOrder> lststu = selquery.SeeRefundOrders();
		Object[][] rowsellOrderform = new Object[lststu.size()][];
		for (int i = 0; i < rowsellOrderform.length; i++) {
			SellOrder sellOrder = lststu.get(i);
			Object[] obj = { sellOrder.getSell_id(), sellOrder.getSell_date(), sellOrder.getSell_empId(),
					sellOrder.getSell_profit(), sellOrder.getSell_mark() };
			rowsellOrderform[i] = obj;
		}
		upperdm = new DefaultTableModel(rowsellOrderform, strsellOrderform);
		upperform = new JTable(upperdm);
		upperform = new MTable(upperdm);
		upperrollingpanel = new JScrollPane(upperform);
		upperrollingpanel.setBounds(10, 105, 780, 140);
		int mainFrameRows = upperform.getRowCount();
		double money = 0;
		for (int i = 0; i < mainFrameRows; i++) {
			money += Double.parseDouble(upperdm.getValueAt(i, 3).toString());
		}
		String[] blank = { "单据数:" + mainFrameRows, "", "", "总:" + money, "", "", "" };
		dm = new DefaultTableModel(null, blank);
		JTable jt = new JTable(dm);
		jt = new MTable(dm);
		JScrollPane js = new JScrollPane(jt);
		js.setBounds(10, 250, 780, 25);
		// 提示文字
		JLabel lbmain = new JLabel("销售单据详细信息：");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("微软雅黑", Font.BOLD, 13));
		lbmain.setForeground(Color.black);
		Numbers = new JTextField("ID:" + id + Customer);
		Numbers.setBounds(130, 0, 200, 25);
		Numbers.setFont(new Font("微软雅黑", Font.BOLD, 12));
		MatteBorder border1 = new MatteBorder(0, 0, 0, 0, Color.RED);
		Numbers.setBorder(border1);
		// 创建表格
		String[] str = { "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小" };
		downdm = new DefaultTableModel(null, str);
		downform = new JTable(downdm);
		downform = new MTable(downdm);
		downrollingpanel = new JScrollPane(downform);
		downrollingpanel.setBounds(10, 30, 780, 165);

		p4.add(lbmain);
		p4.add(Numbers);
		p4.add(downrollingpanel);
		p3.add(js);
		p3.add(upperrollingpanel);
		p3.add(querytime);
		p3.add(Choicetime);
		p3.add(inputOrder);
		p3.add(queryButton);
		p3.add(Orderinputframe);
		p3.add(OrderqueryButton);
		Splitupanddown.setDividerLocation(280);
		Splitupanddown.setEnabled(false);
		tp.add("退款订单", Splitupanddown);

	}

	public void addRegisterListener() {
		// 全部查询
		queryAllBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SalesDao2 selquery = new SalesDao2();
				List<Goods> lststu = selquery.geCurrent_inventory_query();
				Object[][] rows = new Object[lststu.size()][];
				for (int i = 0; i < rows.length; i++) {
					// 获取集合中的商品清单对象
					Goods sb = lststu.get(i);
					// 将对象属性转为数组存储
					Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_number(),
							sb.getGoods_purPrise(), sb.getGoods_sellPrice(), sb.getWorth(), sb.getGoods_size(),
							sb.getGoods_keepDays(), sb.getGoods_mark() };
					// 给二维数组赋值
					rows[i] = obj;
				}
				Inventory_query.this.remove(goodsSc);
				tm1 = new DefaultTableModel(rows, good);
				jt = new JTable(tm1);
				jt = new MTable(rows, good);
				spright = new JScrollPane(jt);
				spright.setBounds(12, 150, 795, 380);
				Inventory_query.this.add(spright);
			}

		});
		// 部分查询
		querySingleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				try {
					if (idText.getText() != null) {
						id = Integer.parseInt(idText.getText());
						SalesDao2 selquery = new SalesDao2();
						List<Goods> lststu = selquery.geCurrent_inventory_query(id);
						Object[][] rows = new Object[lststu.size()][];
						for (int i = 0; i < rows.length; i++) {
							Goods sb = null;
							try {
								sb = lststu.get(i);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							// 将对象属性转为数组存储
							Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(),
									sb.getGoods_number(), sb.getGoods_purPrise(), sb.getGoods_sellPrice(),
									sb.getWorth(), sb.getGoods_size(), sb.getGoods_keepDays(), sb.getGoods_mark() };
							// 给二维数组赋值
							rows[i] = obj;
						}
						Inventory_query.this.remove(goodsSc);
						tm1 = new DefaultTableModel(rows, good);
						jt = new JTable(tm1);
						jt = new MTable(rows, good);
						spright = new JScrollPane(jt);
						spright.setBounds(12, 150, 795, 380);
						Inventory_query.this.add(spright);
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "请输入正确的数字");
				}

			}
		});
		inputSto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object outStr = inputSto.getSelectedItem();
				if (outStr.equals("主仓库")) {
					outSto_id = 1;
				} else if (outStr.equals("饮料库")) {
					outSto_id = 2;
				} else if (outStr.equals("酒库")) {
					outSto_id = 3;
				} else if (outStr.equals("零食库")) {
					outSto_id = 4;
				}
				SalesDao2 goodDao = new SalesDao2();
				List<Goods> listSto = goodDao.queryByStoId(outSto_id);
				Object[][] rows = new Object[listSto.size()][];
				for (int i = 0; i < rows.length; i++) {
					Goods sb = listSto.get(i);
					Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_number(),
							sb.getGoods_purPrise(), sb.getGoods_sellPrice(), sb.getWorth(), sb.getGoods_size(),
							sb.getGoods_keepDays(), sb.getGoods_mark() };
					// 给二维数组赋值
					rows[i] = obj;
				}
				Inventory_query.this.remove(goodsSc);
				tm1 = new DefaultTableModel(rows, good);
				jt = new JTable(tm1);
				jt = new MTable(rows, good);
				spright = new JScrollPane(jt);
				spright.setBounds(5, 120, 795, 380);
				Inventory_query.this.add(spright);
			}
		});
		upperform.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = upperform.getSelectedRow();// 获取行
				((DefaultTableModel) downform.getModel()).getDataVector().clear(); // 清除表格数据
				id = Integer.parseInt(upperform.getValueAt(row, 0).toString());
				Customer = upperform.getValueAt(row, 2).toString();
				Numbers.setText("ID:" + id + "	Name:" + Customer);
				// 点击根据id显示已出售详情表信息
				SalesDao2 selquery = new SalesDao2();
				List<RefunddetailsOrder> lststu = selquery.SeeRefunddetailsOrders(id);
				Object[][] rowparticularsform = new Object[lststu.size()][];
				for (int i = 0; i < rowparticularsform.length; i++) {
					RefunddetailsOrder sb = lststu.get(i);
					// "商品编号", "商品名称", "单位", "单价", "数量", "总金额", "规格大小"
					Object[] obj = { sb.getsDet_goodId(), sb.getGoods_name(), sb.getGoods_units(),
							sb.getGoods_sellPrice(), sb.getsDet_number(), sb.getsDet_goodPrice(), sb.getGoods_size() };
					downdm.addRow(obj);

				}
			}
		});
		/**
		 * 根据订单号查询已售退回的订单
		 */
		// OrderqueryButton.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// if (!Orderinputframe.getText().equals("")) {
		// ((DefaultTableModel) upperform.getModel()).getDataVector().clear();
		// // 清除表格数据
		// int idname = Integer.parseInt(Orderinputframe.getText());
		// SalesDao dao = new SalesDao();
		// List<RefundOrder> lstu = dao.To_find_thes(idname);
		// // 订单信息二维数组
		// Object[][] rowtop = new Object[lstu.size()][];
		// for (int i = 0; i < rowtop.length; i++) {
		// // 获取集合对象
		// RefundOrder sb = lstu.get(i);
		// // 将对象属性转为数组存储
		// Object[] obj = { sb.getSell_id(), sb.getSell_date(),
		// sb.getCustomername(), sb.getSell_profit(),
		// sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
		// upperdm.addRow(obj);
		// }
		// } else {
		// ((DefaultTableModel) upperform.getModel()).getDataVector().clear();
		// // 清除表格数据
		// SalesDao selquery = new SalesDao();
		// List<RefundOrder> lststu = selquery.queryOrderById();
		// Object[][] rowsellOrderform = new Object[lststu.size()][];
		// for (int i = 0; i < rowsellOrderform.length; i++) {
		// RefundOrder sb = lststu.get(i);
		// Object[] obj = { sb.getSell_id(), sb.getSell_date(),
		// sb.getCustomername(), sb.getSell_profit(),
		// sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
		// rowsellOrderform[i] = obj;
		// upperdm.addRow(obj);
		// }
		// }
		// }
		// });
		/**
		 * 根据时间查询已售退回的订单
		 */
		queryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!Choicetime.getText().equals("")) {
						((DefaultTableModel) upperform.getModel()).getDataVector().clear(); // 清除表格数据
						sell_date = Date.valueOf(Choicetime.getText());
						SalesDao2 dao = new SalesDao2();
						List<RefundOrder> lstu = dao.querySellOrderByDate(sell_date);
						// 订单信息二维数组
						Object[][] rowtop = new Object[lstu.size()][];
						for (int i = 0; i < rowtop.length; i++) {
							// 获取集合对象
							RefundOrder sb = lstu.get(i);
							// 将对象属性转为数组存储
							Object[] obj = { sb.getSell_id(), sb.getSell_date(), sb.getCustomername(),
									sb.getSell_profit(), sb.getBilltype(), sb.getSell_empId(), sb.getSell_mark() };
							upperdm.addRow(obj);

						}
					}
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, "请输入正确的日期");
				}

			}
		});
	}

	public static void main(String[] args) {
		new Inventory_query();
	}

}
