package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import cn.dao.purchase.GoodPurDetailDao1;
import cn.dao.purchase.StoTransDao1;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.model.purchase.GoodPurDetail;
import cn.view.purchase.MTable;
/**
 * 1.日期2017-8-05
 * 2.主要功能
 *  a.左边展示所有商品信息
 *  b.输入框可根据名称模糊查询信息，也可全部查询
 *  c.双击商品弹出商品信息框添加数量
 *  d.选择的商品可显示在右边面板
 *  e.可对右边选中的商品进行修改，删除
 * @author 熊晨晨
 *
 */
public class PurchaseOldAdd extends JFrame {
	CommondMethods commondMethods = new CommondMethods();//通用方法
	JPanel jp;// 创建主面板
	JSplitPane splitleftright;// 创建左右分割面板
	private JTable tableleft;// 左边表格对象
	private DefaultTableModel tmleft;// 左边创建表模型
	private JTable tableright;// 右边表格对象
	private DefaultTableModel tmright;// 右边创建表模型
	DefaultTableModel tm;

	private JScrollPane spright;// 创建右边滚动面板
	private JScrollPane spleft;// 创建左边滚动面板

	private JPanel pleft; // 左边面板
	private JPanel pright; // 右边面板

	JTextField tfgoodName;// 输入商品姓名按钮
	JButton btnSel;// 商品名称查询按钮
	JButton btnAll;// 全部查询按钮
	JButton btnSure;// 确定按钮
	JButton btnDel;// 删除按钮
	JButton btnUpdate;// 修改按钮
	JButton btnCan;// 取消按钮
	CommondMethods comMenth = null;

	public PurchaseOldAdd(JTable tables, DefaultTableModel tms) {
		this.tm = tms;
		this.tableright = tables;
		comMenth = new CommondMethods();
		this.inist();
		this.setSplit();
		this.btn();
		// 窗口可视化
		this.setVisible(true);
		splitleftright.setDividerLocation(0.5);
		splitleftright.setEnabled(false);
	}

	/**
	 * 初始化页面
	 */
	public void inist() {
		// 设置窗口大小
		this.setSize(1000, 530);
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
		this.setTitle("老商品添加");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * 设置分割面板
	 */
	public void setSplit() {
		// 创建分割面板对象
		splitleftright = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		// 调用左右面板方法
		this.setLeftPanel();
		this.setRightPanel();
		splitleftright.add(pleft, JSplitPane.LEFT);
		splitleftright.add(pright, JSplitPane.RIGHT);
		// 把分割面板添加到窗口
		this.add(splitleftright);
	}

	/**
	 * 设置左边面板
	 */
	public void setLeftPanel() {
		// 添加面板对象
		pleft = comMenth.createPanel(0, 0, 1000, 530);
		// 无布局设置
		pleft.setLayout(null);
		// 文字内容提示和编号提示框以及按钮
		JLabel lbtop = comMenth.createLabel("输入商品编号或名称查询商品信息，选择后商品信息将在右边展示",10, 10, 400, 25, "宋体", 12);
		lbtop.setForeground(Color.RED);
		JLabel lbtopid = comMenth.createLabel("请输入商品名称:", 10, 40, 100, 25, "宋体", 12);
		tfgoodName = comMenth.createTextField("", 120, 40, 160, 25, "宋体", 12, Color.white);
		btnSel = comMenth.createImagesButton("images\\new.png", 25, 25,290, 40, 25, 25);
		btnAll =  comMenth.createImagesButton("images\\refresh.png", 25, 25, 320, 40, 25, 25);
		// 创建表格
		String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "售价", "最低库存", };
		// 获取数据库数据
		GoodPurDetailDao1 goodpurd = new GoodPurDetailDao1();
		List<GoodPurDetail> lstu = goodpurd.queryStatus();
		Object[][] rows = new Object[lstu.size()][];
		for (int i = 0; i < rows.length; i++) {
			// 获取集合对象
			GoodPurDetail goodpur = lstu.get(i);
			// 将对象属性转为数组存储
			Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
					goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getGoods_number(),
					goodpur.getGoods_sellPrice(), goodpur.getGoods_minNumber() };
			// 给二位数组赋值
			rows[i] = obj;
		}
		tmleft = new DefaultTableModel(rows, str);
		tableleft = new JTable(tmleft);
		tableleft = new MTable(tmleft);
		spleft = new JScrollPane(tableleft);
		spleft.setBounds(10, 80, 480, 400);
		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(155,193,242);
			}

		}
		commondMethods.setColor(tableleft, color);
		// 双击滚动面板选择商品
		tableleft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = tableleft.getSelectedRow();
					int column = tableleft.getAutoResizeMode();
					tableleft.getValueAt(row, column);
					if (row != -1) {
						new PurchaseOldSel(tmleft, tableleft, tmright);
					}
				}
			}
		});
		
		pleft.add(lbtop);
		pleft.add(tfgoodName);
		pleft.add(lbtopid);
		pleft.add(btnAll);
		pleft.add(btnSel);
		pleft.add(spleft);
		this.add(pleft);
	}

	/**
	 * 设置右边面板
	 */
	public void setRightPanel() {

		// 添加面板对象
		pright = comMenth.createPanel(0, 0, 1000, 530);
		// 无布局设置
		pright.setLayout(null);
		// 确定按钮
		btnSure = comMenth.createButton("确定", 50, 450, 60, 30, "宋体",12);
		// 取消按钮
		btnCan = comMenth.createButton("取消", 150, 450, 60, 30, "宋体",12);
		// 修改按钮
		btnUpdate = comMenth.createButton("修改", 250, 450, 60, 30, "宋体",12);
		// 删除按钮
		btnDel =  comMenth.createButton("删除", 350, 450, 60, 30, "宋体",12);
		// 创建表格
		String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "总价", "售价", "最低库存" };
		Object[][] rows = new Object[0][];

		tmright = new DefaultTableModel(rows, str);
		tableright = new JTable(tmright);
		tableright = new MTable(tmright);
		spright = new JScrollPane(tableright);
		spright.setBounds(10, 10, 480, 420);
		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(155,193,242);
			}
		}
		commondMethods.setColor(tableright, color);
		// 添加进面板
		pright.add(spright);
		pright.add(btnSure);
		pright.add(btnDel);
		pright.add(btnCan);
		pright.add(btnUpdate);
		this.add(pright);
	}

	/**
	 * 按钮事件
	 */
	public void btn() {
		// 名称查询事件
		btnSel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 老商品添加
				// 条件查询信息
				String search = "" + tfgoodName.getText();
				search.trim();
				if (!search.equals("")) {
					// 创建表格
					String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "售价", "数量", "最低库存", };
					// 获取数据库数据
					GoodPurDetailDao1 goodpurd = new GoodPurDetailDao1();
					List<GoodPurDetail> lstu = goodpurd.queryId(search);
					Object[][] rows = new Object[lstu.size()][];
					for (int i = 0; i < rows.length; i++) {
						// 获取集合对象
						GoodPurDetail goodpur = lstu.get(i);
						// 将对象属性转为数组存储
						Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
								goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getGoods_number(),
								goodpur.getGoods_sellPrice(), goodpur.getGoods_minNumber() };
						// 给二位数组赋值
						rows[i] = obj;
					}
					tmleft.setDataVector(rows, str);
					Color[] color = new Color[100];
					for (int i = 0; i < 100; i++) {
						int num = i % 2;
						if (num == 0) {
							color[i] = Color.WHITE;
						} else {
							color[i] = new Color(155,193,242);
						}
					}
					commondMethods.setColor(tableleft, color);
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(PurchaseOldAdd.this, "查询框不能为空！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// 全部查询
		btnAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 全部查询按钮
				// 创建表格
				String[] str = { "商品编号", "商品名称", "单位", "规格型号", "进价", "数量", "售价", "最低库存", };
				// 获取数据库数据
				GoodPurDetailDao1 goodpurd = new GoodPurDetailDao1();
				List<GoodPurDetail> lstu = goodpurd.queryStatus();
				Object[][] rows = new Object[lstu.size()][];
				for (int i = 0; i < rows.length; i++) {
					// 获取集合对象
					GoodPurDetail goodpur = lstu.get(i);
					// 将对象属性转为数组存储
					Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
							goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getGoods_number(),
							goodpur.getGoods_sellPrice(), goodpur.getGoods_minNumber() };
					// 给二位数组赋值
					rows[i] = obj;
				}
				tmleft.setDataVector(rows, str);
				Color[] color = new Color[100];
				for (int i = 0; i < 100; i++) {
					int num = i % 2;
					if (num == 0) {
						color[i] = Color.WHITE;
					} else {
						color[i] = new Color(155,193,242);
					}
				}
				commondMethods.setColor(tableleft, color);
			}
		});
		// 右边确定按钮
		btnSure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 确定按钮事件
				int col = tableright.getColumnCount();// 获取列
				int row = tableright.getRowCount();// 获取行
				Object[][] rowcol = new Object[row][col];
				for (int i = 0; i < row; i++) {
					// 获取商品编号
					int goodid = (int) tableright.getValueAt(i, 0);
					// 获取商品数量
					int goodnumber = (int) tableright.getValueAt(i, 5);
					//获取商品单价
					double goodPrice = (double) tableright.getValueAt(i, 4);
					//静态变脸存储商品总价
					PurchaseOrderView.purAllPrice +=  goodnumber*goodPrice;
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
				PurchaseOrderView.tfallmoney.setText(""+PurchaseOrderView.purAllPrice);
				PurchaseOldAdd.this.dispose();
			}
		});
		// 修改按钮
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 修改按钮
				int rows = tableright.getSelectedRow();
				if (rows != -1) {
					// 商品修改事件
					new PurchaseOldUpdate(tmright, tableright);
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(null, "请选择要修改的商品信息！", "操作提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// 删除按钮
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 删除按钮
				int rows = tableright.getSelectedRow();
				if (rows != -1) {
					Object[] obj = { "任性删除", "手下留情" };
					// 选择提示语句
					int res = JOptionPane.showOptionDialog(PurchaseOldAdd.this, "是否确定删除！", "删除操作提示", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					if (res == JOptionPane.YES_OPTION) {
						tmright.removeRow(rows);
						// 删除成功提示语句
						JOptionPane.showMessageDialog(PurchaseOldAdd.this,"删除成功");
					} else {
						// 保存失败警告
						JOptionPane.showMessageDialog(PurchaseOldAdd.this, "删除失败！", "删除操作提示", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					// 错误操作警告语句
					JOptionPane.showMessageDialog(PurchaseOldAdd.this, "请选择要删除的商品！", "删除操作提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		//取消按钮
		btnCan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PurchaseOldAdd.this.dispose();
			}
		});
	}

	public static void main(String[] args) {
		// new PurchaseOldAdd();
	}
}
