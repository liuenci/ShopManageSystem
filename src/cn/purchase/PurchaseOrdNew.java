package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.StoTransDao1;
import cn.method.CommondMethods;
import cn.model.common.Good;

/**
 * 1.日期2017-8-21 
 * 2.主要功能 
 *  a.新增商品信息
 *  b.对商品不为空的鼠标移动事件
 *  c.确定按钮二次判断
 * 
 * @author 熊晨晨
 *
 */
public class PurchaseOrdNew extends JFrame {

	private DefaultTableModel tms;// 创建表模型
	private JTable tables;// 表格对象
	int goods_id;// 商品编号
	String goods_name;// 商品名称
	String goods_units;// 商品单位
	String goods_size;// 商品规格大小
	double goods_purPrise;// 商品进价
	double goods_sellPrice;// 商品售价
	int goods_number;// 商品数量
	int goods_stoId;// 仓库编号
	int goods_keepDays;// 保质期
	int goods_minNumber;// 最低库存
	String goods_mark;// 备注

	JTextField tfname;
	JLabel noteName;// 名称提示语
	JComboBox units;
	JTextField tfsize;
	JLabel noteSize;// 规格提示语
	JTextField tfpurPrice;
	JLabel noteMoney;// 进价提示语句
	JTextField tfsellPrice;
	JLabel notePrice;// 售价提示语
	JTextField tfnumber;
	JLabel noteMum;// 商品数量提示语句
	JComboBox inputSto;
	JTextField tfkeepDays;
	JLabel noteKeepDays;// 保质期提示语句
	JTextField tfminNumber;
	JLabel noteMin;// 最低库存提示语句
	JTextArea tfmark;
	JPanel jp;// 创建面板
	JButton btnSure;// 确认按钮
	JButton btnDel;// 取消按钮
	CommondMethods comMenth = null;

	public PurchaseOrdNew(DefaultTableModel tms, JTable tables) {
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
		this.setSize(480, 400);
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
		this.setTitle("新商品添加");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * 创建面板
	 */
	public void addpanel() {
		// 添加面板对象
		jp = comMenth.createPanel(0, 0, 550, 400);
		// 无布局设置
		jp.setLayout(null);
		// 确定按钮
		btnSure = comMenth.createButton("确定", 180, 300, 60, 30, "宋体", 14);
		// 取消按钮
		btnDel = comMenth.createButton("取消", 280, 300, 60, 30, "宋体", 14);
		// 商品名称与判断语句
		JLabel laname = comMenth.createLabel("商品名称：", 10, 10, 80, 25, "宋体", 14);
		tfname = comMenth.createTextField("", 80, 10, 120, 25, "宋体", 14, Color.white);
		tfname.setName("商品名称");
		JLabel lbnames = comMenth.createLabel("", 210, 10, 25, 25, "宋体", 14);
		Icon iconames = new ImageIcon("images\\goods.png");
		lbnames.setIcon(iconames);
		noteName = comMenth.createLabel("", 20, 35, 160, 25, "宋体", 12);
		noteName.setForeground(Color.red);
		jp.add(noteName);
		tfname.addFocusListener(new My(noteName, tfname));

		// 商品进价与判断语句
		JLabel lapurPrice = comMenth.createLabel("商品进价", 10, 60, 80, 25, "宋体", 14);
		tfpurPrice = comMenth.createTextField("", 80, 60, 120, 25, "宋体", 14, Color.white);
		tfpurPrice.setName("商品进价");
		JLabel lbmoney = new JLabel();
		Icon iconmoney = new ImageIcon("images\\money.png");
		lbmoney.setIcon(iconmoney);
		lbmoney.setBounds(210, 60, 25, 25);
		noteMoney = comMenth.createLabel("", 20, 85, 160, 25, "宋体", 12);
		noteMoney.setForeground(Color.red);
		jp.add(noteMoney);
		tfpurPrice.addFocusListener(new My(noteMoney, tfpurPrice));

		// 商品规格与提示语句
		JLabel lasize = comMenth.createLabel("商品规格：", 270, 60, 80, 25, "宋体", 14);
		tfsize = comMenth.createTextField("", 350, 60, 60, 25, "宋体", 12, Color.WHITE);
		tfsize.setName("商品规格");
		noteSize = comMenth.createLabel("", 270, 85, 160, 25, "宋体", 12);
		noteSize.setForeground(Color.red);
		jp.add(noteSize);
		tfsize.addFocusListener(new My(noteSize, tfsize));

		// 商品售价与提示语句
		JLabel lasellPrice = comMenth.createLabel("商品售价：", 10, 110, 80, 25, "宋体", 14);
		tfsellPrice = comMenth.createTextField("", 80, 110, 120, 25, "宋体", 14, Color.white);
		tfsellPrice.setName("商品售价");
		JLabel lbmoney2 = comMenth.createLabel("", 210, 110, 25, 25, "宋体", 14);
		Icon iconmoney2 = new ImageIcon("images\\money2.png");
		lbmoney2.setIcon(iconmoney2);
		notePrice = comMenth.createLabel("", 20, 135, 160, 25, "宋体", 12);
		notePrice.setForeground(Color.red);
		jp.add(notePrice);
		tfsellPrice.addFocusListener(new My(notePrice, tfsellPrice));

		// 库存
		JLabel lastoId = comMenth.createLabel("存放仓库：", 10, 160, 80, 25, "宋体", 14);
		// 创建调入仓库列表
		inputSto = new JComboBox();
		// 给列表添加选项
		inputSto.addItem("主仓库");
		inputSto.addItem("酒库");
		inputSto.addItem("饮料库");
		inputSto.addItem("零食库");
		inputSto.setBounds(80, 160, 120, 25);
		JLabel btnsto = comMenth.createLabel("", 210, 160, 20, 20, "宋体", 14);
		Icon icon = new ImageIcon("images\\refresh.png");
		btnsto.setIcon(icon);

		// 保质期与提示语句
		JLabel lakeepDays = comMenth.createLabel("保质时间", 270, 160, 85, 25, "宋体", 14);
		tfkeepDays = comMenth.createTextField("", 350, 160, 60, 25, "宋体", 14, Color.WHITE);
		JLabel laminNumbers = comMenth.createLabel("天", 420, 160, 25, 25, "宋体", 14);
		tfkeepDays.setName("保质时间");
		noteKeepDays = comMenth.createLabel("", 270, 185, 160, 25, "宋体", 12);
		noteKeepDays.setForeground(Color.red);
		jp.add(noteKeepDays);
		tfkeepDays.addFocusListener(new My(noteKeepDays, tfkeepDays));

		JLabel launits = comMenth.createLabel("商品单位：", 270, 210, 80, 25, "宋体", 14);
		units = new JComboBox();
		// 给列表添加选项
		units.addItem("瓶");
		units.addItem("包");
		units.addItem("箱");
		units.addItem("个");
		units.setBounds(350, 210, 60, 25);

		// 最低库存与提示语句
		JLabel laminNumber = comMenth.createLabel("最低库存：", 270, 110, 80, 25, "宋体", 14);
		tfminNumber = comMenth.createTextField("", 350, 110, 60, 25, "宋体", 14, Color.WHITE);
		tfminNumber.setName("最低库存");
		noteMin = comMenth.createLabel("", 270, 135, 160, 25, "宋体", 12);
		noteMin.setForeground(Color.red);
		jp.add(noteMin);
		tfminNumber.addFocusListener(new My(noteMin, tfminNumber));

		// 商品数量与提示语句
		JLabel lanumber = comMenth.createLabel("商品数量：", 270, 10, 80, 25, "宋体", 14);
		tfnumber = comMenth.createTextField("", 350, 10, 60, 25, "宋体", 14, Color.WHITE);
		tfnumber.setName("商品数量");
		noteMum = comMenth.createLabel("", 270, 35, 160, 25, "宋体", 12);
		noteMum.setForeground(Color.red);
		jp.add(noteMum);
		tfnumber.addFocusListener(new My(noteMum, tfnumber));

		JLabel lamark = comMenth.createLabel("备  注：", 10, 210, 80, 25, "宋体", 14);
		tfmark = new JTextArea();
		tfmark.setBounds(80, 210, 120, 40);
		MatteBorder borders = new MatteBorder(1, 1, 1, 1, new Color(122, 138, 153));
		tfmark.setBorder(borders);

		// 添加进面板
		jp.add(lbnames);
		jp.add(units);
		jp.add(lbmoney);
		jp.add(lbmoney2);
		jp.add(inputSto);
		jp.add(btnsto);
		jp.add(laname);
		jp.add(tfname);
		jp.add(launits);
		jp.add(lasize);
		jp.add(tfsize);
		jp.add(lapurPrice);
		jp.add(tfpurPrice);
		jp.add(lasellPrice);
		jp.add(tfsellPrice);
		jp.add(lanumber);
		jp.add(tfnumber);
		jp.add(lastoId);
		jp.add(lakeepDays);
		jp.add(tfkeepDays);
		jp.add(laminNumber);
		jp.add(tfminNumber);
		jp.add(lamark);
		jp.add(tfmark);
		jp.add(laminNumbers);

		jp.add(btnSure);
		jp.add(btnDel);
		// 添加进窗口
		this.add(jp);
	}

	public void btn() {
		// 确定按钮事件
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ("".equals(tfname.getText().trim())) {
					JOptionPane.showMessageDialog(null, "商品姓名不能为空");
				} else if ("".equals(tfnumber.getText())) {
					JOptionPane.showMessageDialog(null, "商品数量不能为空");
				} else if ("".equals(tfpurPrice.getText().trim())) {
					JOptionPane.showMessageDialog(null, "商品单价不能为空");
				} else if ("".equals(tfsize.getText().trim())) {
					JOptionPane.showMessageDialog(null, "商品规格大小不能为空");
				} else if ("".equals(tfsellPrice.getText())) {
					JOptionPane.showMessageDialog(null, "商品售价不能为空");
				} else if ("".equals(tfminNumber.getText())) {
					JOptionPane.showMessageDialog(null, "商品最低库存不能为空");
				} else if ("".equals(tfkeepDays.getText())) {
					JOptionPane.showMessageDialog(null, "商品保质天数不能为空");
				} else {
					if (!tfname.getText().trim().matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "商品名称只能为汉字");
						tfname.setText("");
					} else if (!tfnumber.getText().trim().matches("[1-9]\\d*")) {
						JOptionPane.showMessageDialog(null, "采购数量只能为正整数");
						tfnumber.setText("");
					} else if (!tfpurPrice.getText().trim().matches(
							"^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$")) {
						JOptionPane.showMessageDialog(null, "商品单价只能为正数");
						tfpurPrice.setText("");
					} else if (!tfsize.getText().matches("^[A-Za-z0-9]+$")) {
						JOptionPane.showMessageDialog(null, "商品规格只能为数字+英文");
						tfsize.setText("");
					} else if (!tfsellPrice.getText().trim().matches(
							"^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$")) {
						JOptionPane.showMessageDialog(null, "商品售价只能为正数");
						tfsellPrice.setText("");
					} else if (!tfminNumber.getText().trim().matches("[1-9]\\d*")) {
						JOptionPane.showMessageDialog(null, "最小库存只能为正整数");
						tfminNumber.setText("");
					} else if (!tfkeepDays.getText().trim().matches("[1-9]\\d*")) {
						JOptionPane.showMessageDialog(null, "商品保质期只能为正整数");
						tfkeepDays.setText("");
					} else {
						goods_name = tfname.getText();
						goods_units = (String) units.getSelectedItem();
						goods_size = tfsize.getText();
						goods_purPrise = Double.parseDouble(tfpurPrice.getText());
						goods_sellPrice = Double.parseDouble(tfsellPrice.getText());
						goods_keepDays = Integer.parseInt(tfkeepDays.getText());
						goods_minNumber = Integer.parseInt(tfminNumber.getText());
						goods_mark = tfmark.getText();
						goods_number = Integer.parseInt(tfnumber.getText());
						if (inputSto.getSelectedItem().equals("主仓库")) {
							goods_stoId = 1;
						} else if (inputSto.getSelectedItem().equals("饮料库")) {
							goods_stoId = 2;
						} else if (inputSto.getSelectedItem().equals("酒库")) {
							goods_stoId = 3;
						} else if (inputSto.getSelectedItem().equals("零食库")) {
							goods_stoId = 4;
						}
						// 获取数据库数据
						StoTransDao1 stod = new StoTransDao1();
						int afrow = 0;
						Good good = new Good(goods_name, goods_units, goods_size, goods_purPrise, goods_sellPrice,
								goods_number, goods_stoId, goods_keepDays, goods_minNumber, goods_mark);
						afrow = stod.addGood(good);
						if (afrow > 0) {
							Object[] rowData = { afrow, goods_name, goods_units, goods_size, goods_purPrise,
									goods_number, goods_purPrise * goods_number, goods_sellPrice, goods_stoId,
									goods_keepDays, goods_minNumber, goods_mark };
							// 表格新增一行
							tms.addRow(rowData);
							PurchaseOrderView.purAllPrice += goods_purPrise * goods_number;
							PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
							// 保存成功提示
							JOptionPane.showMessageDialog(null, "保存成功！");
							// 关闭当前窗口
							PurchaseOrdNew.this.dispose();
						} else {
							// 保存失败提示
							JOptionPane.showMessageDialog(null, "保存失败！", "操作提示", JOptionPane.WARNING_MESSAGE);
						}
					}
				}

			}
		});
		// 取消按钮事件
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 取消按钮事件
				JOptionPane.showMessageDialog(null, "新增商品取消");
				// 关闭当前窗口
				PurchaseOrdNew.this.dispose();
			}
		});
	}
}

class My implements FocusListener {
	JLabel noteName;
	JTextField tfname;

	public My(JLabel noteName, JTextField tfname) {
		this.noteName = noteName;
		this.tfname = tfname;
	}

	@Override
	public void focusGained(FocusEvent e) {
		tfname.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (tfname.getText().trim().equals("")) {
			noteName.setText(tfname.getName() + "不能为空哦");
		} else {
			noteName.setText("");
		}
	}

}
