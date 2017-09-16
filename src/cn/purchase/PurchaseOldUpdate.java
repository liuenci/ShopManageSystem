package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.method.CommondMethods;

/**
 * 1.日期2017-8-13 
 * 2.主要内容 
 * a.更改添加商品数量
 * 
 * @author 熊晨晨
 *
 */
public class PurchaseOldUpdate extends JFrame {
	// 创建表模型
	private JTable tableright;// 右边表格对象
	private DefaultTableModel tmright;// 右边创建表模型

	int row = -1;// 表格对象
	int goods_id;// 商品编号
	String goods_name;// 商品名称
	String goods_units;// 商品单位
	String goods_size;// 商品规格大小
	double goods_purPrise;// 商品进价
	int goods_number;// 商品数量
	double pDet_goodPrice;// 总价
	double goods_sellPrice;// 商品售价
	int goods_stoId;// 仓库编号
	int goods_keepDays;// 保质期
	int goods_minNumber;// 最低库存
	String goods_mark;// 备注

	JLabel tfname;
	JLabel units;
	JLabel size;
	JLabel tfpurPrice;
	JLabel tfsellPrice;
	JTextField tfnumber;
	JLabel minNumber;
	JPanel jp;
	JButton btnSure;
	JButton btnDel;
	CommondMethods comMenth = null;
	// 定义二维数组保存数据
	Object[] purold = new Object[11];

	public PurchaseOldUpdate(DefaultTableModel tmright, JTable tableright) {
		this.tmright = tmright;
		this.tableright = tableright;
		comMenth = new CommondMethods();
		this.inist();
		this.addpanel();
		// 窗口可视化
		this.setVisible(true);
	}

	/**
	 * 初始化页面
	 */
	public void inist() {
		// 设置窗口大小
		this.setSize(450, 320);
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
		this.setTitle("商品修改");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * 创建面板
	 */
	public void addpanel() {
		row = tableright.getSelectedRow();
		goods_id = (int) tmright.getValueAt(row, 0);
		goods_name = tmright.getValueAt(row, 1).toString();
		goods_units = tmright.getValueAt(row, 2).toString();
		goods_size = tmright.getValueAt(row, 3).toString();
		goods_purPrise = Double.parseDouble(tmright.getValueAt(row, 4).toString());
		goods_number = (int) tmright.getValueAt(row, 5);
		pDet_goodPrice = Double.parseDouble(tmright.getValueAt(row, 6).toString());
		goods_sellPrice = Double.parseDouble(tmright.getValueAt(row, 7).toString());
		goods_minNumber = (int) tmright.getValueAt(row, 8);
		// 添加面板对象
		jp = comMenth.createPanel(0, 0, 450, 300);
		JPanel jptop = comMenth.createPanel(20, 5, 400, 40);
		JPanel jpmessage = comMenth.createPanel(20, 50, 400, 180);
		JPanel jpbottom = comMenth.createPanel(20, 235, 400, 40);
		// 无布局设置
		jp.setLayout(null);
		// 确定按钮
		// 确定按钮
		btnSure = comMenth.createButton("确定", 120, 5, 60, 30, "宋体", 14);
		// 取消按钮
		btnDel = comMenth.createButton("取消", 240, 5, 60, 30, "宋体", 14);
		// 获取表格值
		JLabel lbTitle = comMenth.createLabel("商品信息", 160, 5, 180, 40, "隶书", 24);

		JLabel laname = comMenth.createLabel("商品名称：", 30, 20, 80, 25, "宋体", 14);
		tfname = comMenth.createLabel(goods_name, 110, 20, 120, 25, "宋体", 14);

		JLabel lapurPrice = comMenth.createLabel("商品进价：", 30, 100, 80, 25, "宋体", 14);
		tfpurPrice = comMenth.createLabel("" + goods_purPrise, 110, 100, 120, 25, "宋体", 14);

		JLabel lasize = comMenth.createLabel("商品规格：", 250, 20, 80, 25, "宋体", 14);
		size = comMenth.createLabel(goods_size, 330, 20, 60, 25, "宋体", 14);

		JLabel lasellPrice = comMenth.createLabel("商品售价：", 250, 100, 80, 25, "宋体", 14);
		tfsellPrice = comMenth.createLabel("" + goods_sellPrice, 330, 100, 120, 25, "宋体", 14);

		JLabel launits = comMenth.createLabel("商品单位：", 30, 60, 80, 25, "宋体", 14);
		units = comMenth.createLabel(goods_units, 110, 60, 120, 25, "宋体", 14);

		JLabel laminNumber = comMenth.createLabel("最低库存：", 250, 60, 80, 25, "宋体", 14);
		minNumber = comMenth.createLabel("" + goods_minNumber, 330, 60, 60, 25, "宋体", 14);

		JLabel lanumber = comMenth.createLabel("商品数量：", 30, 140, 85, 25, "宋体", 14);
		tfnumber = comMenth.createTextField(""+goods_number, 110, 140, 60, 25, "宋体", 14, Color.WHITE);

		// 添加进面板
		jptop.add(lbTitle);
		jpmessage.add(units);
		jpmessage.add(laname);
		jpmessage.add(tfname);
		jpmessage.add(launits);
		jpmessage.add(lasize);
		jpmessage.add(size);
		jpmessage.add(lapurPrice);
		jpmessage.add(tfpurPrice);
		jpmessage.add(lasellPrice);
		jpmessage.add(tfsellPrice);
		jpmessage.add(lanumber);
		jpmessage.add(tfnumber);
		jpmessage.add(laminNumber);
		jpmessage.add(minNumber);

		// 添加进面板
		jpbottom.add(btnSure);
		jpbottom.add(btnDel);
		// 添加进窗口
		jp.add(jpmessage);
		jp.add(jptop);
		jp.add(jpbottom);
		this.add(jp);

		// 确定按钮点击事件
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 确定按钮事件
				try {
					int goods_numbers = Integer.parseInt(tfnumber.getText());
					if (tfnumber.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "请输入需要添加商品的数量", "操作提示", JOptionPane.WARNING_MESSAGE);
						tfnumber.setText("");
					} else if (goods_numbers <= 0) {
						JOptionPane.showMessageDialog(null, "数量不能小于或等于零", "操作提示", JOptionPane.WARNING_MESSAGE);
						tfnumber.setText("");
					} else {
						purold[0] = Integer.parseInt(tmright.getValueAt(row, 0).toString());
						purold[1] = tfname.getText();
						purold[2] = units.getText();
						purold[3] = size.getText();
						purold[4] = Double.parseDouble(tfpurPrice.getText());
						purold[5] = Integer.parseInt(tfnumber.getText());
						purold[6] = Double.parseDouble(tfpurPrice.getText()) * Integer.parseInt(tfnumber.getText());
						purold[7] = Double.parseDouble(tfsellPrice.getText());
						purold[8] = Integer.parseInt(minNumber.getText());
						tmright.setValueAt(goods_numbers, row, 5);
						tmright.setValueAt(goods_purPrise * goods_numbers, row, 6);
						PurchaseOldUpdate.this.dispose();
					}
				} catch (NumberFormatException number) {
					JOptionPane.showMessageDialog(null, "请输入正确的数字", "操作提示", JOptionPane.WARNING_MESSAGE);
					tfnumber.setText("");
				}
			}
		});
		// 取消按钮点击事件
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 取消按钮事件
				// 关闭当前窗口
				PurchaseOldUpdate.this.dispose();
			}
		});
	}

}
