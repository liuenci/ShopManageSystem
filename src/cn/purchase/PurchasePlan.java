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
import java.util.List;

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

import cn.dao.purchase.PurchasePlanDao1;
import cn.dao.purchase.StoTransDao1;
import cn.liuenci.swing.DateChooser;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.model.purchase.PurchasePlanDetGood;
import cn.view.purchase.MTable;

/**
 * 1.日期2017-8-10 
 * 2.主要功能 
 *  a.通过日期查询计划订单
 *  b.通过订单号模糊查询计划订单
 *   c.整单添加按钮可将商品整单添加
 *  d.部分添加按钮可选择商品进行添加
 * 
 * @author 熊晨晨
 *
 */
public class PurchasePlan extends JFrame {
	JSplitPane splittopbottom;// 创建上下分割面板
	private JTable tabletop;// 上边表格对象
	private DefaultTableModel tmtop;// 上边创建表模型
	private JTable tablebottom;// 下边表格对象
	private DefaultTableModel tmbottom;// 下边创建表模型
	DefaultTableModel tm;

	private JScrollPane spbottom;// 创建下边滚动面板
	private JScrollPane sptop;// 创建上边滚动面板

	private JPanel ptop; // 上边面板
	private JPanel pbottom; // 下边面板

	JTextField tfgoodid;// 编号输入框
	JTextField tfgoodTime;// 时间
	JButton btnSelid;// 编号查询按钮
	JButton btnSelTime;// 时间查询
	int plan_id;
	int purid;
	int goodid;
	int status;
	Date plan_date;

	JButton btnAll;
	JButton btnLittle;
	CommondMethods comMenth = null;

	public PurchasePlan(DefaultTableModel tms, JTable tables) {
		this.tablebottom = tables;
		this.tm = tms;
		comMenth = new CommondMethods();
		this.inist();
		this.setSplit();
		this.btn();
		// 窗口可视化
		this.setVisible(true);
		splittopbottom.setDividerLocation(0.5);
		splittopbottom.setEnabled(false);
	}

	/**
	 * 初始化页面
	 */
	public void inist() {
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
		this.setTitle("采购计划订单查询");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * 设置分割面板
	 */
	public void setSplit() {
		// 创建分割面板对象
		splittopbottom = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		// 调用左右面板方法
		this.setTopPanel();
		this.setBottonPanel();
		splittopbottom.add(ptop, JSplitPane.TOP);
		splittopbottom.add(pbottom, JSplitPane.BOTTOM);
		// 把分割面板添加到窗口
		this.add(splittopbottom);
	}

	// 设置上面面板
	public void setTopPanel() {
		// 设置面板对象
		ptop = comMenth.createPanel(0, 0, 815, 530);
		;
		// 布局为null
		ptop.setLayout(null);

		JLabel lbtime = comMenth.createLabel("查询时间:", 25, 20, 100, 25, "宋体", 14);
		tfgoodTime = comMenth.createTextField("单击选择时间", 100, 20, 160, 25, "宋体", 14, Color.white);
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(tfgoodTime);
		// 订单号查询按钮
		btnSelTime = comMenth.createButton("查询", 280, 20, 60, 25, "宋体", 14);

		JLabel lbtopid = comMenth.createLabel("请输入订单号:", 450, 20, 100, 25, "宋体", 14);
		tfgoodid = comMenth.createTextField("", 540, 20, 160, 25, "宋体", 14, Color.white);
		// 订单号查询按钮
		btnSelid = comMenth.createButton("查询", 720, 20, 60, 25, "宋体", 14);
		// 整单添加按钮
		btnAll = comMenth.createButton("整单添加", 40, 60, 100, 25, "宋体", 14);
		// 部分添加按钮
		btnLittle = comMenth.createButton("部分添加", 210, 60, 100, 25, "宋体", 14);
		// 提示文字
		JLabel lbtabletop = comMenth.createLabel("计划订单表：", 10, 85, 100, 25, "宋体", 14);
		lbtabletop.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lbtabletop.setForeground(Color.RED);
		JLabel lbtopmainone = comMenth.createLabel("计划商品整单添加：可点击整单添加按钮或双击订单计划表", 400, 55, 400, 25, "宋体", 14);
		lbtopmainone.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lbtopmainone.setForeground(Color.RED);
		JLabel lbtopmaintwo = comMenth.createLabel("计划商品部分添加：可点击部分添加按钮或双击订单计划详情表", 400, 85, 400, 25, "宋体", 14);
		lbtopmaintwo.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lbtopmaintwo.setForeground(Color.RED);

		// 创建表格
		String[] strtop = { "单据号", "开单日期", "经办人", "备注" };
		Object[][] rowtop = new Object[0][];

		tmtop = new DefaultTableModel(rowtop, strtop);
		tabletop = new JTable(tmtop);
		tabletop = new MTable(tmtop);
		sptop = new JScrollPane(tabletop);
		sptop.setBounds(10, 110, 780, 120);
		// 双击滚动面板选择商品
		tabletop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int getrow = tabletop.getSelectedRow();
					if (getrow > -1) {
						Object[] objmessage = { "添加商品", "考虑一下" };
						// 选择提示语句
						int res = JOptionPane.showOptionDialog(null, "是否添加整单商品！", "操作提示",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, objmessage,
								objmessage[0]);
						if (res == JOptionPane.YES_OPTION) {
							int col = tablebottom.getColumnCount();// 获取列
							int row = tablebottom.getRowCount();// 获取行
							Object[][] rowcol = new Object[row][col];
							for (int i = 0; i < row; i++) {
								// 获取商品编号
								int goodid = (int) tablebottom.getValueAt(i, 0);
								// 获取商品数量
								int goodnumber = (int) tablebottom.getValueAt(i, 5);
								// 获取商品单价
								double goodPrice = (double) tablebottom.getValueAt(i, 4);
								// 静态变脸存储商品总价
								PurchaseOrderView.purAllPrice += goodnumber * goodPrice;
								// 根据商品ID获取商品信息
								StoTransDao1 stod = new StoTransDao1();
								// 保存商品信息
								Good good = null;
								try {
									good = stod.get(goodid);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
										good.getGoods_size(), good.getGoods_purPrise(), goodnumber,
										good.getGoods_purPrise() * goodnumber, good.getGoods_sellPrice(),
										good.getGoods_stoId(), good.getGoods_keepDays(), good.getGoods_minNumber(),
										good.getGoods_mark() };
								rowcol[i] = obj;
								tm.addRow(obj);
							}
							PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
							PurchasePlan.this.dispose();
						} else {
							// 错误操作警告语句
							JOptionPane.showMessageDialog(null, "添加操作已取消！", "操作提示", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						// 错误操作警告语句
						JOptionPane.showMessageDialog(null, "请选择要添加的商品信息！", "操作提示", JOptionPane.ERROR_MESSAGE);
					}
				} else if (e.getClickCount() == 1) {
					int row = tabletop.getSelectedRow();
					plan_id = (int) tabletop.getValueAt(row, 0);
					// 创建表格
					String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "总价", "售价", "仓库编号", "保质期", "最低库存" };
					// 获取数据库数据
					PurchasePlanDao1 goodpurd = new PurchasePlanDao1();
					// 商品信息
					List<PurchasePlanDetGood> lstugood = goodpurd.queryId(plan_id);
					// 商品信息二维数组
					Object[][] rows = new Object[lstugood.size()][];
					for (int i = 0; i < rows.length; i++) {
						// 获取集合对象
						PurchasePlanDetGood goodpur = lstugood.get(i);
						// 将对象属性转为数组存储
						Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
								goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getPlanDet_number(),
								goodpur.getGoods_purPrise() * goodpur.getPlanDet_number(), goodpur.getGoods_sellPrice(),
								goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(), goodpur.getGoods_minNumber() };
						// 给二位数组赋值
						rows[i] = obj;
					}
					tmbottom.setDataVector(rows, str);
				}
			}
		});

		ptop.add(btnAll);
		ptop.add(btnLittle);
		ptop.add(lbtopid);
		ptop.add(lbtime);
		ptop.add(lbtopmainone);
		ptop.add(lbtopmaintwo);
		ptop.add(lbtabletop);
		ptop.add(tfgoodTime);
		ptop.add(tfgoodid);
		ptop.add(btnSelid);
		ptop.add(btnSelTime);
		ptop.add(sptop);
		this.add(ptop);
	}

	// 设置下面面板
	public void setBottonPanel() {
		// 设置面板对象
		pbottom = new JPanel();
		// 布局为null
		pbottom.setLayout(null);
		// 提示文字
		JLabel lbmain = new JLabel("订货单据详细信息：");
		lbmain.setBounds(5, 0, 160, 25);
		lbmain.setFont(new Font("微软雅黑", Font.BOLD, 12));
		lbmain.setForeground(Color.RED);

		// 创建表格
		String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "总价", "售价", "仓库编号", "保质期", "最低库存" };
		Object[][] rows = new Object[0][];

		tmbottom = new DefaultTableModel(rows, str);
		tablebottom = new JTable(tmbottom);
		tablebottom = new MTable(tmbottom);
		spbottom = new JScrollPane(tablebottom);
		spbottom.setBounds(10, 30, 780, 200);
		// 双击滚动面板选择商品
		tablebottom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					int getrow = tablebottom.getSelectedRow();
					if (getrow > -1) {
						Object[] objmessage = { "添加商品", "考虑一下" };
						// 选择提示语句
						int res = JOptionPane.showOptionDialog(null, "是否添加该商品！", "操作提示",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, objmessage,
								objmessage[0]);
						if (res == JOptionPane.YES_OPTION) {
							// 获取商品编号
							int goodid = (int) tablebottom.getValueAt(getrow, 0);
							// 获取商品数量
							int goodnumber = (int) tablebottom.getValueAt(getrow, 5);
							// 获取商品单价
							double goodPrice = (double) tablebottom.getValueAt(getrow, 4);
							// 静态变量存储商品总价
							PurchaseOrderView.purAllPrice += goodnumber * goodPrice;
							// 根据商品ID获取商品信息
							StoTransDao1 stod = new StoTransDao1();
							// 保存商品信息
							Good good = null;
							try {
								good = stod.get(goodid);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
									good.getGoods_size(), good.getGoods_purPrise(), goodnumber,
									good.getGoods_purPrise() * goodnumber, good.getGoods_sellPrice(),
									good.getGoods_stoId(), good.getGoods_keepDays(), good.getGoods_minNumber(),
									good.getGoods_mark() };
							tm.addRow(obj);
							PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
							PurchasePlan.this.dispose();
							tmbottom.removeRow(getrow);
						} else {
							// 错误操作警告语句
							JOptionPane.showMessageDialog(null, "添加操作已取消！", "操作提示", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						// 错误操作警告语句
						JOptionPane.showMessageDialog(null, "请选择要添加的商品信息！", "操作提示", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		pbottom.add(lbmain);
		pbottom.add(spbottom);
		this.add(pbottom);
	}

	/**
	 * 按钮事件
	 */
	public void btn() {
		// 订单时间查询
		btnSelTime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 查询事件
				// 创建表格
				String[] strtop = { "单据号", "开单日期", "经办人", "备注" };
				// 创建表格
				String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "总价", "售价", "仓库编号", "保质期", "最低库存" };
				if (!tfgoodTime.getText().equals("")) {
					try {
						plan_date = Date.valueOf(tfgoodTime.getText());
						// 获取数据库数据
						PurchasePlanDao1 goodpurd = new PurchasePlanDao1();
						// 订单信息
						List<PurchasePlanDetGood> lstu = goodpurd.queryOrdTime(plan_date);
						// 订单信息二维数组
						Object[][] rowtop = new Object[lstu.size()][];
						for (int i = 0; i < rowtop.length; i++) {
							// 获取集合对象
							PurchasePlanDetGood purplan = lstu.get(i);
							// 将对象属性转为数组存储
							Object[] obj = { purplan.getPlan_id(), purplan.getPlan_date(), purplan.getEmp_name(),
									purplan.getPlan_mark() };
							// 给二位数组赋值
							rowtop[i] = obj;
						}
						tmtop.setDataVector(rowtop, strtop);
						// 商品信息
						List<PurchasePlanDetGood> lstugood = goodpurd.queryTime(plan_date);
						// 商品信息二维数组
						Object[][] rows = new Object[lstugood.size()][];
						for (int j = 0; j < rows.length; j++) {
							// 获取集合对象
							PurchasePlanDetGood goodpur = lstugood.get(j);
							// 将对象属性转为数组存储
							Object[] objgood = { goodpur.getGoods_id(), goodpur.getGoods_name(),
									goodpur.getGoods_units(), goodpur.getGoods_size(), goodpur.getGoods_purPrise(),
									goodpur.getPlanDet_number(),
									goodpur.getGoods_purPrise() * goodpur.getPlanDet_number(),
									goodpur.getGoods_sellPrice(), goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(),
									goodpur.getGoods_minNumber() };
							// 给二位数组赋值
							rows[j] = objgood;
						}
						tmbottom.setDataVector(rows, str);

					} catch (IllegalArgumentException timeerror) {
						JOptionPane.showMessageDialog(null, "请输入正确的时间", "操作提示", JOptionPane.WARNING_MESSAGE);
						tfgoodTime.setText("");
					}
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(null, "请选择查询的时间！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// 编号查询按钮
		btnSelid.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 查询事件
				// 创建表格
				String[] strtop = { "单据号", "开单日期", "经办人", "备注" };
				// 创建表格
				String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "总价", "售价", "仓库编号", "保质期", "最低库存" };
				if (!tfgoodid.getText().equals("")) {
					try {
						plan_id = Integer.parseInt(tfgoodid.getText());
						// 获取数据库数据
						PurchasePlanDao1 goodpurd = new PurchasePlanDao1();
						// 订单信息,模糊查询不行
						List<PurchasePlanDetGood> lstu = goodpurd.queryOrdId(plan_id);
						// 订单信息二维数组
						Object[][] rowtop = new Object[lstu.size()][];
						for (int i = 0; i < rowtop.length; i++) {
							// 获取集合对象
							PurchasePlanDetGood purplan = lstu.get(i);
							// 将对象属性转为数组存储
							Object[] obj = { purplan.getPlan_id(), purplan.getPlan_date(), purplan.getEmp_name(),
									purplan.getPlan_mark() };
							// 给二位数组赋值
							rowtop[i] = obj;
						}
						tmtop.setDataVector(rowtop, strtop);
						// 商品信息
						List<PurchasePlanDetGood> lstugood = goodpurd.queryId(plan_id);
						// 商品信息二维数组
						Object[][] rows = new Object[lstugood.size()][];
						for (int i = 0; i < rows.length; i++) {
							// 获取集合对象
							PurchasePlanDetGood goodpur = lstugood.get(i);
							// 将对象属性转为数组存储
							Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
									goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getPlanDet_number(),
									goodpur.getGoods_purPrise() * goodpur.getPlanDet_number(),
									goodpur.getGoods_sellPrice(), goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(),
									goodpur.getGoods_minNumber() };
							// 给二位数组赋值
							rows[i] = obj;
						}
						tmbottom.setDataVector(rows, str);
					} catch (NumberFormatException iderror) {
						JOptionPane.showMessageDialog(null, "请输入正确的编号", "操作提示", JOptionPane.WARNING_MESSAGE);
						tfgoodid.setText("");
					}
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(null, "请选择查询的编号！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// 整单添加按钮
		btnAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int getrow = tabletop.getSelectedRow();
				if (getrow > -1) {
					int col = tablebottom.getColumnCount();// 获取列
					int row = tablebottom.getRowCount();// 获取行
					Object[][] rowcol = new Object[row][col];
					for (int i = 0; i < row; i++) {
						// 获取商品编号
						int goodid = (int) tablebottom.getValueAt(i, 0);
						// 获取商品数量
						int goodnumber = (int) tablebottom.getValueAt(i, 5);
						// 获取商品单价
						double goodPrice = (double) tablebottom.getValueAt(i, 4);
						// 静态变脸存储商品总价
						PurchaseOrderView.purAllPrice += goodnumber * goodPrice;
						// 根据商品ID获取商品信息
						StoTransDao1 stod = new StoTransDao1();
						// 保存商品信息
						Good good = null;
						try {
							good = stod.get(goodid);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
								good.getGoods_size(), good.getGoods_purPrise(), goodnumber,
								good.getGoods_purPrise() * goodnumber, good.getGoods_sellPrice(), good.getGoods_stoId(),
								good.getGoods_keepDays(), good.getGoods_minNumber(), good.getGoods_mark() };
						rowcol[i] = obj;
						tm.addRow(obj);
					}
					PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
					PurchasePlan.this.dispose();
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(null, "请选择要添加的商品信息！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// 部分添加
		btnLittle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int getrow = tablebottom.getSelectedRow();
				if (getrow > -1) {
					// 获取商品编号
					int goodid = (int) tablebottom.getValueAt(getrow, 0);
					// 获取商品数量
					int goodnumber = (int) tablebottom.getValueAt(getrow, 5);
					// 获取商品单价
					double goodPrice = (double) tablebottom.getValueAt(getrow, 4);
					// 静态变量存储商品总价
					PurchaseOrderView.purAllPrice += goodnumber * goodPrice;
					// 根据商品ID获取商品信息
					StoTransDao1 stod = new StoTransDao1();
					// 保存商品信息
					Good good = null;
					try {
						good = stod.get(goodid);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
							good.getGoods_size(), good.getGoods_purPrise(), goodnumber,
							good.getGoods_purPrise() * goodnumber, good.getGoods_sellPrice(), good.getGoods_stoId(),
							good.getGoods_keepDays(), good.getGoods_minNumber(), good.getGoods_mark() };
					tm.addRow(obj);
					PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
					PurchasePlan.this.dispose();
					tmbottom.removeRow(getrow);
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(null, "请选择要添加的商品信息！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public static void main(String[] args) {
		// new PurchasePlan();
	}
}
