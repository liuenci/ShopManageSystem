package cn.sell;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * 潘忠辉 1.日期:2017-08-11
 * 1.添加商品
 * a.选择商品 
 * b.删除商品
 * c.修改商品数量
 * 2.内部类功能 
 * a.分割面板左边显示自己仓库的商品，右边显示已选择的商品
 * b.商品编号模糊查询商品 
 * c.已选择的商品提供修改数量，删除功能 
 * d.选择完成通过表模型和表对象将值传递给主类。
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.dao.sell.SalesControl2;
import cn.dao.sell.SalesDao2;
import cn.model.common.Good;
import cn.view.purchase.MTable;

public class Choose_goods extends JFrame {
	private JPanel p = null;// 商品清单主面板
	private JTable sp = null;// 商品清单表格对象
	private JTable sp1 = null;// 商品清单表格对象
	private DefaultTableModel tm = null; // 创建表模型
	private DefaultTableModel tm1 = null; // 创建表模型
	private DefaultTableModel tm2 = null; // 创建表模型
	private JSplitPane slipt = null; // 创建分割面板对象
	private JPanel leftht = null; // 左边面板
	private JScrollPane spright = null; // 左边滚动面板
	private JScrollPane spright1 = null; // 右边滚动面板
	private JPanel pright = null; // 右边面板
	String[] good = { "商品编号", "商品名称", "单位", "单价", "数量", "总金额" };

	// 格式
	Format gs = new Format();
	JLabel sm = null;// 详情
	JLabel smm = null;// 详情
	JLabel prompt = null;// 请输入商品编号查询商品
	JTextField numtext = null;// 商品编号输入文本框
	JButton jiar = null;// 加入商品按钮
	private JPanel Propan = null;// 内容面板
	private JPanel butpan = null;// 按钮面板
	private JButton modify = null;// 修改
	private JButton confirm = null;// 确认
	private JButton delete = null;// 删除
	private JButton cancel = null;// 取消
	private JPanel list = null;// 列表
	Object[] sz = new Object[5];

	// 初始化
	public Choose_goods(DefaultTableModel tm2) {
		// 调用初始化窗口
		this.inin();
		this.addPanel();
		Clickevent();
		this.tm2 = tm2;
		// 设置窗口可见
		this.setVisible(true);
	}

	public Choose_goods() {
		this.inin();
		this.addPanel();
		Clickevent();
		// 设置窗口可见
		this.setVisible(true);
	}

	public void inin() {
		// 设置窗口标题
		this.setTitle("选择商品");
		// 设置窗口大小
		this.setSize(815, 530);
		// 设置显示窗口的位置
		this.setResizable(false);
		this.setLocationRelativeTo(getOwner());
		// 设置窗口关闭
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// 给窗口添加元素
	public void addPanel() {
		// 创建分割面板对象
		slipt = new JSplitPane();
		slipt.setDividerLocation(407);
		// 调用初始化左边面板方法
		this.setRightleftht();
		// 调用左边面板设置
		slipt.add(leftht, JSplitPane.LEFT);
		// 调用初始化右边面板方法
		this.setRightPanel();
		// 设置右边面板
		slipt.add(pright, JSplitPane.RIGHT);
		slipt.setEnabled(false);
		// 将面板添加到窗口
		this.add(slipt);
	}

	// 左边面板
	private void setRightleftht() {
		leftht = new JPanel();
		leftht.setLayout(null);
		sm = gs.createLabel("请选择要购买的商品,并查看详细信息仔细核对并输入要购买的数量", 35, 0, 390, 50, "宋体", 12);
		smm = gs.createLabel("请注意一旦库存货源不足不能添加到右边面板，请选其他商品", 10, 20, 390, 50, "宋体", 12);
		prompt = gs.createLabel("请输入商品编号查询商品:", 10, 55, 150, 50, "宋体", 13);
		numtext = gs.createTextField("", 165, 68, 100, 25, "宋体", 12, Color.white);
		jiar = gs.createButton("查询", 290, 68, 95, 25, "宋体", 12);

		// 创建表格
		String[] goods = { "商品编号", "商品名称", "单位", "规格", "售价", "库存量" };
		// 从数据库获取商品清单信息
		SalesControl2 selquery = new SalesControl2();
		List<Good> lststu = selquery.selqueryGood();
		Object[][] rows = new Object[lststu.size()][];
		for (int i = 0; i < rows.length; i++) {
			// 获取集合中的商品清单对象
			Good sb = lststu.get(i);
			// 将对象属性转为数组存储
			Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_size(),
					sb.getGoods_sellPrice(), sb.getGoods_number() };
			// 给二维数组赋值
			rows[i] = obj;
		}

		tm = new DefaultTableModel(rows, goods);// good表头
		sp = new JTable(tm);
		sp = new MTable(tm);
		JScrollPane xx = new JScrollPane(sp);

		// 表格列宽自适应
		sp.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		spright = new JScrollPane(sp);
		spright.setBounds(5, 110, 400, 380);
		Propan = gs.createPanel(5, 5, 400, 100);
		Propan.add(prompt);
		Propan.add(sm);
		Propan.add(smm);
		Propan.add(jiar);
		Propan.add(numtext);
		leftht.add(Propan);
		leftht.add(spright);

	}

	// 右边面板
	private void setRightPanel() {
		pright = new JPanel();
		pright.setLayout(null);
		// 创建表格
		tm1 = new DefaultTableModel(null, good);
		sp1 = new JTable(tm1);
		sp1 = new MTable(tm1);
		spright1 = new JScrollPane(sp1);
		modify = gs.createButton("修改", 20, 17, 80, 27, "宋体", 14);
		confirm = gs.createButton("确认", 200, 17, 80, 27, "宋体", 14);
		delete = gs.createButton("删除", 110, 17, 80, 27, "宋体", 14);

		cancel = gs.createButton("取消", 290, 17, 80, 27, "宋体", 14);
		JLabel Title = gs.createLabel("所选商品", 150, 15, 100, 20, "宋体", 18);
		list = gs.createPanel(10, 5, 380, 50);
		spright1.setBounds(10, 60, 380, 355);
		butpan = gs.createPanel(10, 420, 380, 60);
		list.add(Title);
		pright.add(list);
		butpan.add(cancel);
		butpan.add(delete);
		butpan.add(confirm);
		butpan.add(modify);
		pright.add(butpan);
		pright.add(spright1);
	}

	public void Clickevent() {
		// 查询
		jiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (numtext.getText().equals("")) {
					// 创建表格
					String[] goods = { "商品编号", "商品名称", "单位", "规格", "售价", "库存量" };
					// 从数据库获取商品清单信息
					SalesControl2 selquery = new SalesControl2();
					List<Good> lststu = selquery.selqueryGood();
					Object[][] rows = new Object[lststu.size()][];
					for (int i = 0; i < rows.length; i++) {
						// 获取集合中的商品清单对象
						Good sb = lststu.get(i);
						// 将对象属性转为数组存储
						Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_size(),
								sb.getGoods_sellPrice(), sb.getGoods_number() };
						// 给二维数组赋值
						rows[i] = obj;
						String[] good = { "商品编号", "商品名称", "单位", "规格", "售价", "库存量" };
						tm.setDataVector(rows, goods);
					}
				} else if (!numtext.getText().equals("")) {
					try {
						SalesDao2 dao = new SalesDao2();
						List<Good> lststu = dao.queryGoods(Integer.parseInt(numtext.getText()));
						Object[][] rows = new Object[lststu.size()][];
						for (int i = 0; i < rows.length; i++) {
							// 获取集合中的商品清单对象
							Good sb = lststu.get(i);
							// 将对象属性转为数组存储
							Object[] obj = { sb.getGoods_id(), sb.getGoods_name(), sb.getGoods_units(), sb.getGoods_size(),
									sb.getGoods_sellPrice(), sb.getGoods_number() };
							// 给二维数组赋值
							rows[i] = obj;
						}
						String[] goods = { "商品编号", "商品名称", "单位", "规格", "售价", "库存量" };
						tm.setDataVector(rows, goods);
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "请填入正确的商品编号和名称");
					}
					
				}

			}
		});
		// 行点击
		sp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = sp.getSelectedRow();
					int column = sp.getAutoResizeMode();
					sp.getValueAt(row, column);
					if (row != -1) {
						new Commodity_information(tm, sp, tm1);
					}
				}
			}
		});

		// 右行点击
		sp1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {
					int row = sp1.getSelectedRow();
					int column = sp1.getAutoResizeMode();
					sp1.getValueAt(row, column);
					if (row != -1) {
						new Alter_choice(sp1, tm1);
					}
				}
			}
		});
		// 修改
		modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 修改
				int row = sp1.getSelectedRow();
				if (row > -1) {
					new Alter_choice(sp1, tm1);
				} else {
					signal();
				}

			}
		});

		// 确认
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 如果主面板没有商品就直接添加
				try {
					if (tm2.getRowCount() == 0) {
						for (int i = 0; i < tm1.getRowCount(); i++) {
							String z = tm1.getValueAt(i, 0).toString().trim();
							int zid = Integer.parseInt(z);
							// 获取退货的数量
							String y = tm1.getValueAt(i, 4).toString().trim();
							int znum = Integer.parseInt(y);

							Object[][] rowobj = new Object[tm1.getColumnCount()][];
							Object[] obj = { sp1.getValueAt(i, 0), sp1.getValueAt(i, 1), sp1.getValueAt(i, 2),
									sp1.getValueAt(i, 3), sp1.getValueAt(i, 4), sp1.getValueAt(i, 5) };
							rowobj[i] = obj;
							Choose_goods.this.dispose();
							tm2.addRow(obj);
							Sales_order.priceCount += (double) sp1.getValueAt(i, 5);
							Sales_order.tfsupply1.setText("" + Sales_order.priceCount);
						}

					} else {
						// 外层循环，让左边面板的东西与主面板上一次添加的东西进行匹配，如果能匹配到直接相加数量，否则直接添加到主面板
						for (int i = 0; i < tm1.getRowCount(); i++) {
							// 获取每一样商品的编号
							String z = tm1.getValueAt(i, 0).toString().trim();
							int zid = Integer.parseInt(z);
							// 获取商品的数量
							String y = tm1.getValueAt(i, 4).toString().trim();
							int znum = Integer.parseInt(y);
							// 获取该商品的金额
							String money = tm1.getValueAt(i, 5).toString().trim();
							double xmoney = Double.parseDouble(money);
							int sum = 0;
							double smoney = 0;
							for (int j = 0; j < tm2.getRowCount(); j++) {
								if (zid == Integer.parseInt(tm2.getValueAt(j, 0).toString().trim())) {
									SalesDao2 dao = new SalesDao2();
									List<Good> lststu = dao.goodsnum(zid);
									Object[][] rows = new Object[lststu.size()][];
									for (int x = 0; x < rows.length; x++) {
										// 获取集合中的商品清单对象
										Good sb = lststu.get(x);
										// 将对象属性转为数组存储
										Object[] obj = { sb.getGoods_number() };
										// 给二维数组赋值
										rows[x] = obj;
										if (znum + Integer.parseInt(tm2.getValueAt(j, 4).toString().trim()) > Integer
												.parseInt(obj[x].toString())) {
											tm1.removeRow(x);
											JOptionPane.showMessageDialog(null, "您输入的数量已达到最大,请选择其他商品");
										} else if (znum
												+ Integer.parseInt(tm2.getValueAt(j, 4).toString().trim()) <= Integer
														.parseInt(obj[x].toString())) {
											sum = znum + Integer.parseInt(tm2.getValueAt(j, 4).toString().trim());
											smoney = xmoney + Double.parseDouble(tm2.getValueAt(j, 5).toString().trim());
											tm2.setValueAt(sum, j, 4);
											tm2.setValueAt(smoney, j, 5);
											Sales_order.priceCount += xmoney;
											Sales_order.tfsupply1.setText("" + Sales_order.priceCount);
											Choose_goods.this.dispose();
											
										}
										break;
									}
									break;
								} else if (j == tm2.getRowCount() - 1) {
									// 如果遍历完成，依然没有匹配到
									Object[][] rowobj = new Object[tm2.getRowCount()][];
									Object[] obj = { sp1.getValueAt(i, 0), sp1.getValueAt(i, 1), sp1.getValueAt(i, 2),
											sp1.getValueAt(i, 3), sp1.getValueAt(i, 4), sp1.getValueAt(i, 5), };
									rowobj[i] = obj;
									tm2.addRow(obj);
									Sales_order.priceCount += (double) sp1.getValueAt(i, 5);
									Sales_order.tfsupply1.setText("" + Sales_order.priceCount);
									Choose_goods.this.dispose();
									break;
								}
							}
						}
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "请选择商品再确认");
				}
				
			}
		});
		// 删除
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = sp1.getSelectedRow();
				if (row > -1) {
					tm1.removeRow(row);
				} else {
					signal();
				}
			}
		});

		// 取消
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tm1.getRowCount() == 0) {
					Choose_goods.this.dispose();
				}else {
					Object[] options = { "确定", "取消" };
					int response = JOptionPane.showOptionDialog(null, "请确认是否退出", "提示", JOptionPane.YES_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					if (response == 0) {
						Choose_goods.this.dispose();
					}
				}
			}
		});
	}

	public void signal() {
		JOptionPane.showConfirmDialog(null, "请选择要操作的对象", "错误提示", JOptionPane.DEFAULT_OPTION);
	}

	public void xx1() {
		JOptionPane.showConfirmDialog(null, "您选择了商品是否确认返回修改", "错误提示", JOptionPane.DEFAULT_OPTION);
	}

	public static void main(String[] args) {
		new Choose_goods();
	}
}
