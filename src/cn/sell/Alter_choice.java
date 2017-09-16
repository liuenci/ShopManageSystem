package cn.sell;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.dao.sell.SalesDao2;
import cn.model.common.Good;

/**
 * 
 * @author 潘忠辉
 *
 */

public class Alter_choice extends JFrame {
	private JPanel p = null;// 面板
	private JTable sp1 = null;
	private DefaultTableModel tm1 = null;
	private int row = 0;
	private JLabel getcommname = null;// 获取商品名称
	private JLabel getcompany = null;// 获取单位
	private JLabel getSelpri = null;
	private JLabel commname = null;// 商品名称
	private JLabel company = null;// 单位
	private JLabel selpri = null;
	private JTextField jtextField = null;
	private JLabel number = null;
	private JButton determine = null;// 确认按钮
	private JButton cancel = null;
	double aggregate_amount = 0.0;// 总金额
	private JPanel catpanel = null;// 目录面板
	private JPanel conpanel = null;// 内容面板
	private JPanel butpanel = null;// 按钮面板
	Format gs = new Format();// 格式

	// 初始化
	public Alter_choice(JTable sp1, DefaultTableModel tm1) {
		this.inin();
		// 设置窗口可见
		this.sp1 = sp1;
		this.tm1 = tm1;
		addgood();
		Clickevent();
		this.setVisible(true);

	}

	public void inin() {
		// 设置窗口标题
		this.setTitle("修改商品");
		// 设置窗口大小
		this.setSize(450, 250);
		// 设置显示窗口的位置
		this.setLocationRelativeTo(getOwner());
		// 不许修改窗口的大小
		this.setResizable(false);
		// 设置窗口关闭
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void addgood() {
		p = new JPanel();
		p.setLayout(null);
		row = sp1.getSelectedRow();
		// 商品名称
		commname = gs.createLabel("商品名称:", 30, 20, 90, 15, "宋体", 14);
		// 获取商品名称
		getcommname = gs.createLabel(tm1.getValueAt(row, 1).toString(), 95, 20, 90, 15, "宋体", 15);
		// 单位
		company = gs.createLabel("单   位:", 270, 20, 90, 15, "宋体", 14);
		// 获取单位
		getcompany = gs.createLabel(tm1.getValueAt(row, 2).toString(), 340, 20, 90, 15, "宋体", 15);
		// 销售价格
		selpri = gs.createLabel("销售价格:", 30, 60, 80, 18, "宋体", 14);
		// 获取销售价格
		getSelpri = gs.createLabel(tm1.getValueAt(row, 3).toString(), 100, 60, 80, 18, "宋体", 15);
		number = gs.createLabel("数   量:", 270, 60, 60, 25, "宋体", 14);
		// 输入数量
		jtextField = new JTextField(tm1.getValueAt(row, 4).toString());
		jtextField.setFont(new Font("宋体", Font.PLAIN, 15));
		jtextField.setBounds(330, 60, 50, 25);
		determine = gs.createButton("确定", 50, 10, 100, 30, "宋体", 14);
		cancel = gs.createButton("取消", 290, 10, 100, 30, "宋体", 14);
		JLabel commtion = gs.createLabel("商品信息", 175, 0, 80, 40, "宋体", 15);
		// 目录面板
		catpanel = gs.createPanel(5, 5, 435, 40);
		// 内容面板
		conpanel = gs.createPanel(5, 50, 435, 110);
		// 按钮面板
		butpanel = gs.createPanel(5, 163, 435, 55);
		catpanel.add(commtion);
		butpanel.add(cancel);
		butpanel.add(determine);
		conpanel.add(number);
		conpanel.add(jtextField);
		conpanel.add(getSelpri);
		conpanel.add(selpri);
		conpanel.add(getcompany);
		conpanel.add(company);
		conpanel.add(getcommname);
		conpanel.add(commname);
		p.add(catpanel);
		p.add(conpanel);
		p.add(butpanel);
		this.add(p);

	}

	public void Clickevent() {
		// 确认按钮点击事件
		determine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SalesDao2 sum = new SalesDao2();
				List<Good> number = sum.goodsnum(Integer.parseInt(tm1.getValueAt(row, 0).toString()));
				Object[][] rows = new Object[number.size()][];
				for (int j = 0; j < rows.length; j++) {
					Good sb = number.get(j);
					Object[] obj = { sb.getGoods_number() };
					rows[j] = obj;
					try {
						if (Integer.parseInt(jtextField.getText()) > 0
								&& Integer.parseInt(jtextField.getText()) <= Integer.parseInt(obj[j].toString())) {
							tm1.setValueAt(jtextField.getText(), row, 4);
							aggregate_amount = Double.parseDouble(getSelpri.getText())
									* Double.parseDouble(jtextField.getText());
							tm1.setValueAt(aggregate_amount, row, 5);
							Alter_choice.this.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "数量不能为0或者负数");
						}
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "请输入正确的数字");
					}

				}

			}
		});
		// 取消按钮点击事件
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Alter_choice.this.dispose();

			}
		});
	}

}
