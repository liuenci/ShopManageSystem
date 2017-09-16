package cn.storage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.dao.storage.GoodDao3;
import cn.dao.storage.StoTransDao3;
import cn.method.CommondMethods;
import cn.model.common.Good;

public // 内部类，获得点击的商品信息
class GetInformation extends JFrame {
	private CommondMethods commondMethods = new CommondMethods();// 通用方法
	JButton inforConfirmBtn;// 确定按钮
	JButton inforCancelBtn;// 取消按钮
	String nameText;// 商品名称
	String unitsText;// 基本单位
	String sizeText;// 规格大小
	Double purPriceText;// 商品进价
	Double sellPriceText;// 商品售价
	int goodNumber;// 当前库存数量
	int stoId;// 所在仓库
	int keepDays;// 保质期
	int minNumber;// 最小库存
	String goodMark;// 商品备注
	private int good_id = StorageQuery.goodId;
	JTextField name = null;
	JComboBox units = null;
	JTextField size = null;
	JTextField purPrise = null;
	JTextField sellPrise = null;
	JComboBox stoBox = null;
	JTextField keep = null;
	JTextField min = null;
	JTextField mark = null;

	// 构造方法
	public GetInformation() {
		this.init();
		addPanel();
		addMouseListener();
		setVisible(true);
	}

	// 初始化
	public void init() {
		setTitle("商品信息");
		setSize(544, 400);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void addPanel() {
		this.setLayout(null);
		// 创建主面板
		JPanel mainGoodPanel = new JPanel();
		mainGoodPanel.setLayout(null);
		mainGoodPanel.setBounds(5, 50, 520, 250);
		mainGoodPanel.setBorder(BorderFactory.createEtchedBorder());

		// 创建名称面板
		JPanel goodPanel1 = new JPanel();
		goodPanel1.setBounds(5, 5, 520, 40);
		goodPanel1.setBorder(BorderFactory.createEtchedBorder());
		this.add(goodPanel1);

		// 创建名称标签
		JLabel informationLabel = new JLabel("商品信息");
		informationLabel.setFont(new Font("隶书", Font.PLAIN, 26));
		goodPanel1.add(informationLabel);
		// 创建标签
		JLabel idLabel = new JLabel("商品编号:");
		idLabel.setBounds(15, 15, 80, 20);
		idLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		mainGoodPanel.add(idLabel);

		JLabel nameLabel = new JLabel("商品名称:");
		nameLabel.setBounds(280, 15, 80, 20);
		nameLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		mainGoodPanel.add(nameLabel);

		JLabel unitsLabel = new JLabel("基本单位:");
		unitsLabel.setBounds(15, 55, 80, 20);
		unitsLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		mainGoodPanel.add(unitsLabel);

		JLabel sizeLabel = new JLabel("规格型号:");
		sizeLabel.setBounds(280, 55, 80, 20);
		sizeLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		mainGoodPanel.add(sizeLabel);

		JLabel purPriceLabel = new JLabel("商品进价:");
		purPriceLabel.setBounds(15, 95, 80, 20);
		purPriceLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		mainGoodPanel.add(purPriceLabel);

		JLabel sellPriceLabel = new JLabel("商品售价:");
		sellPriceLabel.setBounds(280, 95, 80, 20);
		sellPriceLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		mainGoodPanel.add(sellPriceLabel);

		JLabel numberLabel = new JLabel("当前库存:");
		numberLabel.setBounds(15, 135, 80, 20);
		numberLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		mainGoodPanel.add(numberLabel);

		JLabel stoIdLabel = new JLabel("所在仓库:");
		stoIdLabel.setBounds(280, 135, 80, 20);
		stoIdLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		mainGoodPanel.add(stoIdLabel);

		JLabel keepDaysLabel = new JLabel("保质期:");
		keepDaysLabel.setBounds(15, 175, 80, 20);
		keepDaysLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		mainGoodPanel.add(keepDaysLabel);

		JLabel minNumberLabel = new JLabel("最小库存:");
		minNumberLabel.setBounds(280, 175, 80, 20);
		minNumberLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		mainGoodPanel.add(minNumberLabel);

		JLabel markLabel = new JLabel("商品备注:");
		markLabel.setBounds(15, 215, 80, 20);
		markLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		mainGoodPanel.add(markLabel);

		// 显示信息标签
		// 从数据库获取商品信息
		StoTransDao3 stoTransDao = new StoTransDao3();
		Good good = null;
		try {
			good = stoTransDao.get(good_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JTextField goodIdText = commondMethods.createTextField("" + good_id, 90, 15, 100, 20, "宋体", 16, null);
		mainGoodPanel.add(goodIdText);
		goodIdText.setEditable(false);
		goodIdText.setBackground(Color.LIGHT_GRAY);

		nameText = good.getGoods_name();// 商品名称
		name = commondMethods.createTextField(nameText, 360, 15, 100, 20, "宋体", 16, null);
		mainGoodPanel.add(name);

		unitsText = good.getGoods_units();// 基本单位
		units = commondMethods.createJComboBox(90, 55, 100, 20, "宋体", 12);
		units.addItem("个");
		units.addItem("瓶");
		units.addItem("包");
		mainGoodPanel.add(units);

		sizeText = good.getGoods_size();// 规格大小
		size = commondMethods.createTextField(sizeText, 360, 55, 100, 20, "宋体", 16, null);
		mainGoodPanel.add(size);

		purPriceText = good.getGoods_purPrise();// 商品进价
		purPrise = commondMethods.createTextField("" + purPriceText, 90, 95, 100, 20, "宋体", 16, null);
		mainGoodPanel.add(purPrise);
		purPrise.setEditable(false);
		purPrise.setBackground(Color.LIGHT_GRAY);

		sellPriceText = good.getGoods_sellPrice();// 商品售价
		sellPrise = commondMethods.createTextField("" + sellPriceText, 360, 95, 100, 20, "宋体", 16, null);
		mainGoodPanel.add(sellPrise);

		goodNumber = good.getGoods_number();// 当前库存数量

		JTextField number = commondMethods.createTextField("" + goodNumber, 90, 135, 100, 20, "宋体", 16, null);
		mainGoodPanel.add(number);
		number.setEditable(false);
		number.setBackground(Color.LIGHT_GRAY);

		stoBox = commondMethods.createJComboBox(360, 135, 100, 20, "宋体", 12);
		stoBox.addItem("主仓库");
		stoBox.addItem("酒库");
		stoBox.addItem("饮料库");
		stoBox.addItem("零食库");
		

		mainGoodPanel.add(stoBox);

		keepDays = good.getGoods_keepDays();// 保质期

		keep = commondMethods.createTextField("" + keepDays, 90, 175, 100, 20, "宋体", 16, null);
		mainGoodPanel.add(keep);

		minNumber = good.getGoods_minNumber();// 最小库存

		min = commondMethods.createTextField("" + minNumber, 360, 175, 100, 20, "宋体", 16, null);
		mainGoodPanel.add(min);

		goodMark = good.getGoods_mark();// 商品备注

		mark = commondMethods.createTextField(goodMark, 90, 215, 370, 20, "宋体", 16, null);
		mainGoodPanel.add(mark);

		this.add(mainGoodPanel);
		// 添加底部按钮面板
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		btnPanel.setBounds(5, 305, 520, 50);
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		// 添加按钮
		inforConfirmBtn = new JButton("确定");
		inforConfirmBtn.setBounds(130, 10, 100, 30);
		inforConfirmBtn.setBackground(new Color(175, 208, 244));
		inforConfirmBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		btnPanel.add(inforConfirmBtn);
		inforCancelBtn = new JButton("取消");
		inforCancelBtn.setBounds(300, 10, 100, 30);
		inforCancelBtn.setBackground(new Color(175, 208, 244));
		inforCancelBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		btnPanel.add(inforCancelBtn);
		// 添加确定和退出按钮
		this.add(btnPanel);

	}

	public void addMouseListener() {
		inforConfirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 从数据库中更新数据
				GoodDao3 goodDao3 = new GoodDao3();
				if (stoBox.getSelectedItem().toString().equals("主仓库")) {
					stoId = 1;
				} else if (stoBox.getSelectedItem().toString().equals("酒库")) {
					stoId = 2;
				} else if (stoBox.getSelectedItem().toString().equals("饮料库")) {
					stoId = 3;
				} else if (stoBox.getSelectedItem().toString().equals("零食库")) {
					stoId = 4;
				}
				goodDao3.updateGood(name.getText(), units.getSelectedItem().toString(), size.getText(),
						Double.parseDouble(sellPrise.getText()), stoId, Integer.parseInt(keep.getText()),
						Integer.parseInt(min.getText()), mark.getText(), StorageQuery.goodId);
				JOptionPane.showMessageDialog(null, "商品修改成功");
				GetInformation.this.dispose();
			}

		});
		inforCancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "商品修改失败");
				GetInformation.this.dispose();
			}
		});
	}

	public static void main(String[] args) {
		new GetInformation();
	}
}