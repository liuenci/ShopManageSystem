package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
 * 1.日期2017-8-11 
 * 2.主要内容 
 * a.更改添加商品数量
 * @author 熊晨晨
 *
 */
public class PurchaseOrUpdate extends JFrame {

	private DefaultTableModel tms;// 创建表模型
	private JTable tables;// 表格对象
	int row = -1;
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
	JLabel inputSto;
	JLabel keepDays;
	JLabel minNumber;
	JTextArea tfmark;
	JPanel jp;
	JButton btnSure;
	JButton btnDel;
	CommondMethods comMenth = null;

	public PurchaseOrUpdate(DefaultTableModel tms, JTable tables) {
		this.tables = tables;
		this.tms = tms;
		comMenth = new CommondMethods();
		this.inist();
		this.addpanel();
		this.btn();
		// 窗口可视化
		this.setVisible(true);
	}

	/**
	 * 初始化页面
	 */
	public void inist() {
		// 设置窗口大小
		this.setSize(450, 380);
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
		row = tables.getSelectedRow();
		goods_id = Integer.parseInt(tms.getValueAt(row, 0).toString());
		goods_name = tms.getValueAt(row, 1).toString();
		goods_units = tms.getValueAt(row, 2).toString();
		goods_size = tms.getValueAt(row, 3).toString();
		goods_purPrise = Double.parseDouble(tms.getValueAt(row, 4).toString());
		goods_number = Integer.parseInt(tms.getValueAt(row, 5).toString());
		pDet_goodPrice = Double.parseDouble(tms.getValueAt(row, 6).toString());
		goods_sellPrice = Double.parseDouble(tms.getValueAt(row, 7).toString());
		goods_stoId = Integer.parseInt(tms.getValueAt(row, 8).toString());
		goods_keepDays = Integer.parseInt(tms.getValueAt(row, 9).toString());
		goods_minNumber = Integer.parseInt(tms.getValueAt(row, 10).toString());
		goods_mark = tms.getValueAt(row, 11).toString();
		// 添加面板对象
		jp = comMenth.createPanel(0, 0, 450, 400);
		JPanel jptop = comMenth.createPanel(20, 5, 400, 40);
		JPanel jpmessage = comMenth.createPanel(20, 50, 400, 240);
		JPanel jpbottom = comMenth.createPanel(20, 295, 400, 40);
		// 无布局设置
		jp.setLayout(null);
		// 获取表格值
		JLabel lbTitle = comMenth.createLabel("商品信息", 150, 2, 180, 40, "隶书", 24);

		// 确定按钮
		btnSure = comMenth.createButton("确定", 130, 5, 60, 30, "宋体", 14);
		// 取消按钮
		btnDel = comMenth.createButton("取消", 230, 5, 60, 30, "宋体", 14);
		JLabel laname = comMenth.createLabel("商品名称：", 30, 10, 80, 25, "宋体", 14);
		tfname = comMenth.createLabel(goods_name, 110, 10, 120, 25, "宋体", 14);
		JLabel lapurPrice = comMenth.createLabel("商品进价：", 30, 130, 80, 25, "宋体", 14);
		tfpurPrice = comMenth.createLabel("" + goods_purPrise, 110, 130, 120, 25, "宋体", 14);
		JLabel lasize = comMenth.createLabel("商品规格：", 250, 50, 80, 25, "宋体", 14);
		size = comMenth.createLabel(goods_size, 330, 50, 60, 25, "宋体", 14);

		JLabel lasellPrice = comMenth.createLabel("商品售价：", 250, 130, 80, 25, "宋体", 14);
		tfsellPrice = comMenth.createLabel("" + goods_sellPrice, 330, 130, 120, 25, "宋体", 14);
		JLabel lastoId = comMenth.createLabel("存放仓库：", 30, 50, 80, 25, "宋体", 14);
		inputSto = comMenth.createLabel("" + goods_stoId, 110, 50, 120, 25, "宋体", 14);
		JLabel lakeepDays = comMenth.createLabel("保 质 期：", 250, 10, 80, 25, "宋体", 14);
		keepDays = comMenth.createLabel("" + goods_keepDays, 330, 10, 60, 25, "宋体", 14);
		JLabel laminNumbers = comMenth.createLabel("天", 370, 10, 25, 25, "宋体", 14);
		JLabel launits = comMenth.createLabel("商品单位：", 30, 90, 80, 25, "宋体", 14);
		units = comMenth.createLabel(goods_units, 110, 90, 120, 25, "宋体", 14);
		JLabel laminNumber = comMenth.createLabel("最低库存：", 250, 90, 80, 25, "宋体", 14);
		minNumber = comMenth.createLabel("" + goods_minNumber, 330, 90, 60, 25, "宋体", 14);
		JLabel lanumber = comMenth.createLabel("商品数量：", 30, 170, 85, 25, "宋体", 14);
		tfnumber = comMenth.createTextField("" + goods_number, 110, 170, 60, 25, "宋体", 14, Color.WHITE);

		// 添加进面板// 添加进面板
		jptop.add(lbTitle);
		jpmessage.add(units);
		jpmessage.add(laname);
		jpmessage.add(tfname);
		jpmessage.add(launits);
		jpmessage.add(lasize);
		jpmessage.add(size);
		jpmessage.add(lastoId);
		jpmessage.add(inputSto);
		jpmessage.add(lakeepDays);
		jpmessage.add(keepDays);
		jpmessage.add(laminNumbers);
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
	}

	public void btn() {
		// 确定按钮点击事件
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 确定按钮事件
				// 确定按钮事件
				try {
					goods_number = Integer.parseInt(tfnumber.getText());
					if (tfnumber.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "请输入需要添加商品的数量", "操作提示", JOptionPane.WARNING_MESSAGE);
						tfnumber.setText("");
					} else if (goods_number <= 0) {
						JOptionPane.showMessageDialog(null, "数量不能小于或等于零", "操作提示", JOptionPane.WARNING_MESSAGE);
						tfnumber.setText("");
					} else {
						goods_name = tfname.getText();
						goods_units = units.getText();
						goods_size = size.getText();
						goods_purPrise = Double.parseDouble(tfpurPrice.getText());
						goods_sellPrice = Double.parseDouble(tfsellPrice.getText());

						goods_stoId = Integer.parseInt(inputSto.getText());
						goods_keepDays = Integer.parseInt(keepDays.getText());
						goods_minNumber = Integer.parseInt(minNumber.getText());
						pDet_goodPrice = goods_purPrise * goods_number;
						JOptionPane.showMessageDialog(null, "修改成功！");
						// 修改table的信息
						tms.setValueAt(goods_purPrise, row, 4);
						tms.setValueAt(goods_number, row, 5);
						tms.setValueAt(pDet_goodPrice, row, 6);
						tms.setValueAt(goods_sellPrice, row, 7);
						int maxRows = tables.getRowCount();
						// 商品的总价重置为0
						PurchaseOrderView.purAllPrice = 0;
						// 遍历
						for (int i = 0; i < maxRows; i++) {
							// 得到每一行的总金额
							double perGoodPrice = (double) tables.getValueAt(i, 6);
							PurchaseOrderView.purAllPrice += perGoodPrice;
						}
						PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
						// 关闭当前窗口
						PurchaseOrUpdate.this.dispose();
					}
				} catch (NumberFormatException numbernull) {
					JOptionPane.showMessageDialog(null, "请在文本框输入数字", "操作提示", JOptionPane.WARNING_MESSAGE);
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
				PurchaseOrUpdate.this.dispose();
			}
		});
	}

	public static void main(String[] args) {
		// new PurchaseOrUpdate();
	}
}
