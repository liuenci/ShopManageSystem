package cn.sell;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * 潘忠辉 1.日期:2017-08-19
 * 1.显示添加的商品
 * 2.选择数量（不能超过仓库的数量）
 *
 */
public class Commodity_information extends JFrame {
	Format gs = new Format();// 格式
	JTable sp = null;// 主面板
	DefaultTableModel tm = null;// 创建表模型
	private JPanel p = null;
	private JSplitPane slipt = null;
	private JTextField jtextField = null;
	private String a = null;
	DefaultTableModel tm1;
	DefaultTableModel tm2;
	private JLabel comnumber = null;// 商品编号
	private JLabel getconumbe = null;// 获取商品编号
	private JLabel spemodel = null;// 规格型号
	private JLabel getspeModel = null;// 获规格型号
	private JLabel curtin = null;// 当前库存
	private JLabel getcuinv = null;// 获取当前库存
	private JLabel comname = null;// 商品名称
	private JLabel getcommname = null;// 获取商品名称
	private JLabel company = null;// 单位
	private JLabel getCompany = null;// 获取单位
	private JLabel sellprice = null;// 销售价格
	private JLabel getsellprice = null;// 获取销售价格
	private JLabel number = null;// 数量
	private JButton determine = null;// 确认按钮
	private JButton cancel = null;// 取消按钮
	private JPanel catpanel = null;// 目录面板
	private JPanel conpanel = null;// 内容面板
	private JPanel butpanel = null;// 按钮面板
	Object[] sz = new Object[6];

	// 初始化
	public Commodity_information(DefaultTableModel tm, JTable sp, DefaultTableModel tm1) {
		// 调用初始化窗口
		this.inin();
		this.sp = sp;
		this.tm = tm;
		this.tm1 = tm1;
		this.addPanel();
		Clickevent();
		// 设置窗口可见
		this.setVisible(true);
	}

	public void inin() {
		// 设置窗口标题
		this.setTitle("商品信息");
		// 设置窗口大小
		this.setSize(500, 300);
		// 设置显示窗口的位置
		this.setLocationRelativeTo(getOwner());
		// 不许修改窗口的大小
		this.setResizable(false);
		// 设置窗口关闭
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void addPanel() {
		// 创建面板
		p = new JPanel();
		p.setLayout(null);
		int row = sp.getSelectedRow();
		// 商品编号
		comnumber = gs.createLabel("商品编号:", 30, 10, 100, 25, "宋体", 14);
		// 获取商品编号
		getconumbe = gs.createLabel(tm.getValueAt(row, 0).toString(), 100, 10, 100, 25, "宋体", 15);
		// 规格型号
		spemodel = gs.createLabel("规格型号:", 30, 45, 100, 25, "宋体", 14);
		// 获规格型号
		getspeModel = gs.createLabel(tm.getValueAt(row, 3).toString(), 100, 45, 100, 25, "宋体", 15);
		// 当前库存
		curtin = gs.createLabel("当前库存:", 30, 80, 100, 25, "宋体", 14);
		// 获取当前库存
		getcuinv = gs.createLabel(tm.getValueAt(row, 5).toString(), 100, 80, 100, 25, "宋体", 14);
		// 商品名称
		comname = gs.createLabel("商品名称:", 310, 10, 100, 25, "宋体", 14);
		// 获取商品名称
		getcommname = gs.createLabel(tm.getValueAt(row, 1).toString(), 380, 10, 100, 25, "宋体", 15);
		// 单位
		company = gs.createLabel("单     位:", 30, 115, 100, 25, "宋体", 14);
		// 获取单位
		getCompany = gs.createLabel(tm.getValueAt(row, 2).toString(), 100, 115, 100, 25, "宋体", 15);
		// 销售价格
		sellprice = gs.createLabel("销售价格:", 310, 60, 100, 25, "宋体", 14);
		// 获取销售价格
		getsellprice = gs.createLabel(tm.getValueAt(row, 4).toString(), 380, 60, 100, 25, "宋体", 15);
		// 数量
		number = gs.createLabel("数    量:", 310, 110, 100, 25, "宋体", 14);
		// 输入数量
		jtextField = new JTextField();
		jtextField.setFont(new Font("宋体", Font.PLAIN, 15));
		jtextField.setBounds(380, 110, 60, 25);
		// 确认按钮
		determine = gs.createButton("确定", 100, 20, 75, 25, "宋体", 15);
		// 取消按钮
		cancel = gs.createButton("取消", 320, 20, 75, 25, "宋体", 15);
		JLabel commtion = gs.createLabel("商品信息", 210, 0, 80, 40, "宋体", 15);
		// 目录面板
		catpanel = gs.createPanel(10, 7, 480, 40);
		// 内容面板
		conpanel = gs.createPanel(10, 55, 480, 150);
		// 按钮面板
		butpanel = gs.createPanel(10, 210, 480, 60);
		conpanel.add(comnumber);
		conpanel.add(getconumbe);
		conpanel.add(spemodel);
		conpanel.add(getspeModel);
		conpanel.add(curtin);
		conpanel.add(getcuinv);
		conpanel.add(comname);
		conpanel.add(getcommname);
		conpanel.add(company);
		conpanel.add(getCompany);
		conpanel.add(sellprice);
		conpanel.add(getsellprice);
		conpanel.add(number);
		conpanel.add(jtextField);
		butpanel.add(cancel);
		butpanel.add(determine);
		catpanel.add(commtion);
		p.add(catpanel);
		p.add(conpanel);
		p.add(butpanel);
		this.add(p);
	}

	public void Clickevent() {

		// 确认按钮事件
		determine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(jtextField.getText())) {
					JOptionPane.showMessageDialog(null, "数量不能为空");
				}else {
					// 如果右边面板没有商品，就直接将该商品加到右边面板
					try {
						if (tm1.getRowCount() == 0) {
							if (Integer.parseInt(jtextField.getText()) > Integer.parseInt(getcuinv.getText())
									|| Integer.parseInt(jtextField.getText()) <= 0) {
								JOptionPane.showMessageDialog(null, "您输入的数量不够或小于0");
							} else if (Integer.parseInt(jtextField.getText()) <= Integer.parseInt(getcuinv.getText())) {
								sz[0] = getconumbe.getText();
								sz[1] = getcommname.getText();
								sz[2] = getCompany.getText();
								sz[3] = Double.parseDouble(getsellprice.getText());
								sz[4] = Integer.parseInt(jtextField.getText());
								sz[5] = (int) sz[4] * (double) sz[3];
								tm1.addRow(sz);
								Commodity_information.this.dispose();
							}
						} else {
							// 如果右边面板不为空，则进行匹配，如果匹配到了，则直接修改数量,并且修改金额
							int sum = 0;
							double money = 0;
							if (Integer.parseInt(jtextField.getText()) > Integer.parseInt(getcuinv.getText())
									|| Integer.parseInt(jtextField.getText()) <= 0 ) {
								JOptionPane.showMessageDialog(null, "您输入的数量不够或小于0");
							} else {
								for (int i = 0; i < tm1.getRowCount(); i++) {
									if (Integer.parseInt(getconumbe.getText()) == Integer
											.parseInt(tm1.getValueAt(i, 0).toString().trim())
											) {
										if (Integer.parseInt(jtextField.getText())
													+ Integer.parseInt(tm1.getValueAt(i, 4).toString().trim()) <= Integer
															.parseInt(getcuinv.getText())) {

											sum = Integer.parseInt(jtextField.getText())
													+ Integer.parseInt(tm1.getValueAt(i, 4).toString().trim());
											money = Integer.parseInt(jtextField.getText())
													* Double.parseDouble(getsellprice.getText())
													+ Double.parseDouble(tm1.getValueAt(i, 5).toString().trim());
											tm1.setValueAt(sum, i, 4);
											tm1.setValueAt(money, i, 5);

											Commodity_information.this.dispose();
										}else {
											JOptionPane.showMessageDialog(null, "您输入的数量不够或小于0");
										}
										break;
									} else if (i == tm1.getRowCount() - 1) {
										if (Integer.parseInt(jtextField.getText()) > Integer.parseInt(getcuinv.getText())
												&& Integer.parseInt(jtextField.getText()) <= 0) {
											JOptionPane.showMessageDialog(null, "您输入的数量不够或小于0");
										} else if (Integer.parseInt(jtextField.getText()) <= Integer
												.parseInt(getcuinv.getText())) {
											sz[0] = getconumbe.getText();
											sz[1] = getcommname.getText();
											sz[2] = getCompany.getText();
											sz[3] = Double.parseDouble(getsellprice.getText());
											sz[4] = Integer.parseInt(jtextField.getText());
											sz[5] = (int) sz[4] * (double) sz[3];
											tm1.addRow(sz);
											Commodity_information.this.dispose();
											break;
										}
									}
								}

							}
						}
					}catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的数字");
					}
				}
				
				
			}
		}

		);
		// 取消按钮事件
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Commodity_information.this.dispose();

			}

		});
	}

	public void xx() {
		JOptionPane.showConfirmDialog(null, "库存没有您需要的数量请确定修改", "错误提示", JOptionPane.DEFAULT_OPTION);
	}

	public static void main(String[] args) {
	}
}
