package cn.purchase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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

import cn.dao.purchase.PurchaseOrderDetailGoodDao1;
import cn.liuenci.swing.DateChooser;
import cn.method.CommondMethods;
import cn.model.purchase.PurchaseOrderDetailGood;
import cn.model.purchase.PurchaseOrderEmpSup;
import cn.view.purchase.MTable;

/**
 * 1.日期：2017-8-15 
 * 2.主要功能 
 * a.根据时间查询已经入库的订单
 * b.根据订单号查询已经入库的订单
 * c.可整单退货 
 * d.可部分退货
 * @author 熊晨晨
 *
 */
public class PurchaseDelGood extends JPanel {
	CommondMethods commondMethods = new CommondMethods();
	JSplitPane splittopbottom;// 创建上下分割面板
	private JTable tabletop;// 上边表格对象
	private DefaultTableModel tmtop;// 上边创建表模型
	private JTable tablebottom;// 下边表格对象
	private DefaultTableModel tmbottom;// 下边创建表模型

	private JScrollPane spbottom;// 创建下边滚动面板
	private JScrollPane sptop;// 创建上边滚动面板

	private JPanel ptop; // 上边面板
	private JPanel pbottom; // 下边面板

	JTextField tfgoodid;// 编号输入框
	JTextField tfgoodTime;// 时间
	JButton btnSelid;// 编号查询按钮
	JButton btnSelTime;// 时间查询
	int pur_id;
	int purid;
	int goodid;
	int status;
	Date pur_date;
	JButton btnAllDel;
	JButton btnLitDel;
	CommondMethods comMenth = null;

	public PurchaseDelGood(JSplitPane splittopDel) {
		this.splittopbottom = splittopDel;
		comMenth = new CommondMethods();
		this.setLayout(new BorderLayout());
		this.inist();
		this.setSplit();
		this.btn();
		// 窗口可视化
		this.setVisible(true);
	}

	/**
	 * 初始化页面
	 */
	public void inist() {
		// 设置窗口大小
		this.setSize(815, 530);
		// 设置窗口显示位置
		this.setLocation(280, 85);
		// 不许修改窗口的大小
	}

	/**
	 * 设置分割面板
	 */
	public void setSplit() {
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
		ptop = comMenth.createPanel(0, 0, 415, 280);
		// 布局为null
		ptop.setLayout(null);
		JLabel lbTitle = comMenth.createLabel("采购退货", 340, 10, 180, 40, "隶书", 30);

		btnAllDel = comMenth.createButton("整单退货", 25, 50, 90, 25, "宋体", 14);
		btnLitDel = comMenth.createButton("部分退货", 140, 50, 90, 25, "宋体", 14);

		JLabel lbtime = comMenth.createLabel("查询时间:", 25, 90, 100, 25, "宋体", 14);
		tfgoodTime = comMenth.createTextField("单击选择时间", 25, 90, 100, 25, "宋体", 14, Color.WHITE);
		DateChooser dateChooser = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(tfgoodTime);
		tfgoodTime.setBounds(100, 90, 160, 25);
		// 时间查询按钮
		btnSelTime = comMenth.createButton("查询", 280, 90, 60, 25, "宋体", 14);
		JLabel lbtopid = comMenth.createLabel("请输入订单号:", 440, 90, 100, 25, "宋体", 14);
		tfgoodid = comMenth.createTextField("", 540, 90, 160, 25, "宋体", 14, Color.white);
		// 订单号查询按钮
		btnSelid = comMenth.createButton("查询", 720, 90, 60, 25, "宋体", 14);
		// 创建表格
		String[] strtop = { "单据号", "供货商名称", "开单日期", "支付总金额", "经办人", "采购状态", "备注" };
		Object[][] rowtop = new Object[0][];

		tmtop = new DefaultTableModel(rowtop, strtop);
		tabletop = new JTable(tmtop);
		tabletop = new MTable(tmtop);
		sptop = new JScrollPane(tabletop);
		sptop.setBounds(10, 130, 780, 100);
		// 双击滚动面板选择商品
		tabletop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 双击全部退货
				if (e.getClickCount() == 2) {
					int afrow = 0;
					int getrow = tabletop.getSelectedRow();
					if (getrow > -1) {
						Object[] obj = { "任性退货", "手下留情" };
						// 选择提示语句
						int res = JOptionPane.showOptionDialog(null, "是否取消订单！", "操作提示",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
						if (res == JOptionPane.YES_OPTION) {
							int rowbottom = tablebottom.getRowCount();// 获取商品详情行
							for (int j = 0; j < rowbottom; j++) {
								// 获取数据库数据
								PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
								// 商品信息
								afrow = goodpurd.updatePassGood((int) tablebottom.getValueAt(j, 5),
										(int) tabletop.getValueAt(getrow, 0), (int) tablebottom.getValueAt(j, 0));
								// 修改table的信息
								tmtop.setValueAt("取消订单", getrow, 5);
								tmbottom.setValueAt("已退货", j, 10);
								String goodtime = tfgoodTime.getText().trim();
							}
						} else {
							// 错误操作警告语句
							JOptionPane.showMessageDialog(null, "退货操作已取消！", "操作提示", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						// 错误操作警告语句
						JOptionPane.showMessageDialog(null, "请选择要退货的商品信息！", "操作提示", JOptionPane.ERROR_MESSAGE);
					}
					// 成功提示
					if (afrow > 0) {
						JOptionPane.showMessageDialog(null, "退货成功！");
					}
				} else if (e.getClickCount() == 1) {
					int row = tabletop.getSelectedRow();
					int column = tabletop.getAutoResizeMode();
					tabletop.getValueAt(row, column);
					purid = (int) tabletop.getValueAt(row, 0);
					PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
					// 判断订单中是否还有商品，没有则改变订单状态
					int rowNum = goodpurd.queryPassIsNull(purid);
					if (rowNum == 0) {
						goodpurd.updateOrderStatus(purid);
					}
					if (row != -1) {
						// 创建表格
						String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "总价", "售价", "仓库编号", "保质期", "最低库存",
								"订单状态" };
						// 获取数据库数据
						// 商品信息
						List<PurchaseOrderDetailGood> lstugood = goodpurd.queryPassId(purid);
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
									goodpur.getGoods_purPrise() * goodpur.getpDet_number(),
									goodpur.getGoods_sellPrice(), goodpur.getGoods_stoId(), goodpur.getGoods_keepDays(),
									goodpur.getGoods_minNumber(), status };
							rows[i] = obj;
						}
						tmbottom.setDataVector(rows, str);
						Color[] color = new Color[100];
						for (int i = 0; i < 100; i++) {
							int num = i % 2;
							if (num == 0) {
								color[i] = Color.WHITE;
							} else {
								color[i] = new Color(155,193,242);
							}
						}
						commondMethods.setColor(tablebottom, color);
					}
				}
			}
		});
		ptop.add(lbTitle);
		ptop.add(btnAllDel);
		ptop.add(btnLitDel);
		ptop.add(lbtopid);
		ptop.add(lbtime);
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
		pbottom = comMenth.createPanel(0, 0, 415, 280);
		// 布局为null
		pbottom.setLayout(null);
		// 提示文字
		JLabel lbmain = comMenth.createLabel("订货单据详细信息：", 5, 0, 160, 25, "宋体", 14);
		lbmain.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lbmain.setForeground(Color.RED);
		// 创建表格
		String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "总价", "售价", "仓库编号", "保质期", "最低库存", "订单状态" };
		Object[][] rows = new Object[0][];

		tmbottom = new DefaultTableModel(rows, str);
		tablebottom = new JTable(tmbottom);
		tablebottom = new MTable(tmbottom);
		spbottom = new JScrollPane(tablebottom);
		spbottom.setBounds(10, 30, 780, 180);
		// 双击滚动面板选择商品
		tablebottom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int afrow = 0;
					int row = tablebottom.getSelectedRow();
					goodid = (int) tablebottom.getValueAt(row, 0);
					int goodnumber = (int) tablebottom.getValueAt(row, 5);
					int rows = tabletop.getSelectedRow();
					if (row != -1) {
						Object[] obj = { "任性退货", "手下留情" };
						// 选择提示语句
						int res = JOptionPane.showOptionDialog(null, "是否确定退货！", "操作提示",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
						if (res == JOptionPane.YES_OPTION) {
							PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
							afrow = goodpurd.delPassGood(goodnumber, goodid, purid);
							// 判断是否为最后一件商品
							tmbottom.removeRow(row);
							if (tmbottom.getRowCount() == 0) {
								goodpurd.updatePassPur(purid);
								tmtop.setValueAt("取消订单", rows, 5);
							}
						} else {
							// 退货失败提示
							JOptionPane.showMessageDialog(null, "操作已取消！", "操作提示", JOptionPane.WARNING_MESSAGE);
						}
					}
					if (afrow > 0) {
						JOptionPane.showMessageDialog(null, "退货成功！");
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
				// 清空商品订单详情表格原信息
				tmbottom.setNumRows(0);
				// 查询事件
				// 创建表格
				String[] strtop = { "单据号", "供货商名称", "开单日期", "支付总金额", "经办人", "采购状态", "备注" };
				// 创建表格
				if (!tfgoodTime.getText().equals("") || !tfgoodTime.getText().equals("单击选择时间")) {
					try {
						pur_date = Date.valueOf(tfgoodTime.getText());
						// 获取数据库数据
						PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
						// 订单信息
						List<PurchaseOrderEmpSup> lstu = goodpurd.queryOrdPassTime(pur_date);
						// 订单信息二维数组
						Object[][] rowtop = new Object[lstu.size()][];
						for (int i = 0; i < rowtop.length; i++) {
							// 获取集合对象
							PurchaseOrderEmpSup purempsup = lstu.get(i);
							String purstatus = purempsup.getPur_status() == 0 ? "未审核"
									: (purempsup.getPur_status() == 1 ? "审核通过"
											: (purempsup.getPur_status() == 2 ? "审核未通过" : "取消订单"));
							// 将对象属性转为数组存储
							Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
									purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus,
									purempsup.getPur_mark() };
							// 给二位数组赋值
							rowtop[i] = obj;
						}
						tmtop.setDataVector(rowtop, strtop);
						Color[] color = new Color[100];
						for (int i = 0; i < 100; i++) {
							int num = i % 2;
							if (num == 0) {
								color[i] = Color.WHITE;
							} else {
								color[i] = new Color(155,193,242);
							}
						}
						commondMethods.setColor(tabletop, color);
					} catch (IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的日期", "操作提示", JOptionPane.WARNING_MESSAGE);
						tfgoodTime.setText("");
					}
				}
			}
		});
		// 编号查询按钮
		btnSelid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 清空商品订单详情表格原信息
				tmbottom.setNumRows(0);
				// 查询事件
				// 创建表格
				String[] strtop = { "单据号", "供货商名称", "开单日期", "支付总金额", "经办人", "采购状态", "备注" };
				// 创建表格
				if (!tfgoodid.getText().equals("")) {
					try {
						pur_id = Integer.parseInt(tfgoodid.getText());
						// 获取数据库数据
						PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
						// 订单信息
						List<PurchaseOrderEmpSup> lstu = goodpurd.queryOrdPassId(pur_id);
						// 订单信息二维数组
						Object[][] rowtop = new Object[lstu.size()][];
						for (int i = 0; i < rowtop.length; i++) {
							// 获取集合对象
							PurchaseOrderEmpSup purempsup = lstu.get(i);
							String purstatus = purempsup.getPur_status() == 0 ? "未审核"
									: (purempsup.getPur_status() == 1 ? "审核通过"
											: (purempsup.getPur_status() == 2 ? "审核未通过" : "取消订单"));
							// 将对象属性转为数组存储
							Object[] obj = { purempsup.getPur_id(), purempsup.getSup_name(), purempsup.getPur_date(),
									purempsup.getPur_pay(), purempsup.getEmp_name(), purstatus,
									purempsup.getPur_mark() };
							// 给二位数组赋值
							rowtop[i] = obj;
						}
						tmtop.setDataVector(rowtop, strtop);
						Color[] color = new Color[100];
						for (int i = 0; i < 100; i++) {
							int num = i % 2;
							if (num == 0) {
								color[i] = Color.WHITE;
							} else {
								color[i] = new Color(155,193,242);
							}
						}
						commondMethods.setColor(tabletop, color);
					} catch (IllegalArgumentException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的订单号", "操作提示", JOptionPane.WARNING_MESSAGE);
						tfgoodid.setText("");
					}
				}
			}
		});
		//全部退货
		btnAllDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int afrow = 0;
				int getrow = tabletop.getSelectedRow();
				if (getrow > -1) {
					Object[] obj = { "任性退货", "手下留情" };
					// 选择提示语句
					int res = JOptionPane.showOptionDialog(null, "是否整单退货！", "操作提示",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					if (res == JOptionPane.YES_OPTION) {
						int rowbottom = tablebottom.getRowCount();// 获取商品详情行
						for (int j = 0; j < rowbottom; j++) {
							// 获取数据库数据
							PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
							// 商品信息
							afrow = goodpurd.updatePassGood((int) tablebottom.getValueAt(j, 5),
									(int) tabletop.getValueAt(getrow, 0), (int) tablebottom.getValueAt(j, 0));
							// 成功提示
							if (afrow > 0) {
								JOptionPane.showMessageDialog(null, "退货成功！");
								// 修改table的信息
								tmtop.setValueAt("取消订单", getrow, 5);
								tmbottom.setValueAt("已退货", j, 10);
								String goodtime = tfgoodTime.getText().trim();
							}else{
								// 错误操作警告语句
								JOptionPane.showMessageDialog(null, "退货失败！", "操作提示", JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						// 错误操作警告语句
						JOptionPane.showMessageDialog(null, "退货操作已取消！", "操作提示", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(null, "请选择要退货的商品信息！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnLitDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int afrow = 0;
				int row = tablebottom.getSelectedRow();
				if (row != -1) {
					goodid = (int) tablebottom.getValueAt(row, 0);
					int goodnumber = (int) tablebottom.getValueAt(row, 5);
					int rows = tabletop.getSelectedRow();
					Object[] obj = { "任性退货", "手下留情" };
					// 选择提示语句
					int res = JOptionPane.showOptionDialog(null, "是否确定退货！", "操作提示", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					if (res == JOptionPane.YES_OPTION) {
						PurchaseOrderDetailGoodDao1 goodpurd = new PurchaseOrderDetailGoodDao1();
						afrow = goodpurd.delPassGood(goodnumber, goodid, purid);
						if (afrow > 0) {
							JOptionPane.showMessageDialog(null, "退货成功！");
						}else{
							// 错误操作警告语句
							JOptionPane.showMessageDialog(null, "退货失败！", "操作提示", JOptionPane.ERROR_MESSAGE);
						}
						// 判断是否为最后一件商品
						tmbottom.removeRow(row);
						if (tmbottom.getRowCount() == 0) {
							goodpurd.updatePassPur(purid);
							tmtop.setValueAt("取消订单", rows, 5);
						}
					} else {
						// 退货失败提示
						JOptionPane.showMessageDialog(null, "操作已取消！", "操作提示", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(null, "请选择要退货的商品信息！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public static void main(String[] args) {
		// new PurchaseDelGood();
	}
}
