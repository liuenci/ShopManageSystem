package cn.storage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.DefaultTableModel;

import cn.dao.storage.StoTransDao3;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.view.purchase.MTable;

public class PlanAddOldGoods extends JFrame {
	// 创建左边面板
	JPanel leftPanel = new JPanel();
	// 创建右边面板
	JPanel rightPanel = new JPanel();
	// 创建左边表格对象
	private JTable leftTable;
	// 创建左边表格对象
	private JTable rightTable;
	// 创建表模型
	private DefaultTableModel leftModel;
	private DefaultTableModel rightModel;
	private JSplitPane splitPane; // 创建分割面板对象
	private JScrollPane leftSc; // 左边滚动面板
	private JScrollPane rightSc; // 右边滚动面板
	// 创建左边表头
	String[] title1 = { "商品编号", "商品名称", "单位", "规格", "参考进价", "库存数", "参考售价" };
	// 创建右边表头
	String[] title2 = { "商品编号", "商品名称", "单位", "进价", "数量", "总金额" };
	// 添加文本框
	JTextField rearchText;
	// 添加查询按钮
	JButton queryBtn;
	JPanel leftMainPanel = null;
	JPanel rightMainPanel = null;
	Color stepColor = new Color(237, 242, 248);
	// 添加四个按钮
	JButton updateBtn = new JButton("修改");
	JButton delBtn = new JButton("删除");
	JButton confirmBtn = new JButton("确定");
	JButton cancelBtn = new JButton("取消");
	int good_id;// 点击得到商品ID
	JTextField numText;// 更改商品数量文本框
	int good_purPlanNum;// 更改的采购数量
	double good_purPlanPrice;// 商品的采购单价
	// 商品表
	static JTable goodTable;
	// 商品表模型
	static DefaultTableModel goodModel;
	CommondMethods commondMethods = new CommondMethods();

	// 构造方法
	public PlanAddOldGoods(JTable table, DefaultTableModel dtm) throws Exception {
		this.goodTable = table;
		this.goodModel = dtm;
		this.init();
		this.addLeftPanel();
		this.addTable();
		this.addRightPanel();
		this.addRegisterListener();
		this.setVisible(true);
	}

	// 初始化
	public void init() {
		this.setTitle("添加商品");
		this.setSize(1000, 600);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

	// 添加面板
	public void addLeftPanel() {
		// 创建分割面板对象
		splitPane = new JSplitPane();

		splitPane.setDividerLocation(500);
		leftPanel.setLayout(null);

		// 添加左边主面板
		leftMainPanel = new JPanel();
		leftMainPanel.setLayout(null);
		leftMainPanel.setBounds(5, 5, 495, 550);
		leftMainPanel.setBackground(new Color(235, 235, 235));
		leftMainPanel.setBorder(BorderFactory.createEtchedBorder());
		// 添加步骤面板
		JPanel stepPanel = new JPanel();
		stepPanel.setLayout(null);
		stepPanel.setBounds(5, 5, 485, 90);
		stepPanel.setBackground(stepColor);
		stepPanel.setBorder(BorderFactory.createEtchedBorder());
		// 添加文字标签
		JLabel stepLabel = new JLabel("<html>查询商品列表<br/>步骤一:&nbsp;&nbsp;请输入商品编号或名称查询商品，查询到商品后添加到右边所选商品<br/>"
				+ "步骤二:&nbsp;&nbsp;如果是新的商品项目，请联系采购员自行采购。<br/><br/>请输入商品编号或名称查询商品:</html>");
		stepLabel.setBounds(5, 0, 480, 85);
		stepLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		// 添加搜索文本框
		rearchText = new JTextField();
		rearchText.setBounds(185, 65, 100, 20);
		stepPanel.add(rearchText);
		// 添加查询按钮
		queryBtn = new JButton("寻找商品");
		queryBtn.setBounds(300, 65, 80, 20);
		stepPanel.add(queryBtn);
		stepPanel.add(stepLabel);
		leftMainPanel.add(stepPanel);
		leftPanel.add(leftMainPanel);
	}

	public void addTable() {
		StoTransDao3 goodDao = new StoTransDao3();
		List<Good> listGood = goodDao.query();
		Object[][] rows = new Object[listGood.size()][];
		for (int i = 0; i < rows.length; i++) {
			Good good = listGood.get(i);
			// 将对象转换为数组
			Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(), good.getGoods_size(),
					good.getGoods_sellPrice(), good.getGoods_number(), good.getGoods_sellPrice() };
			// 给二维数组赋值
			rows[i] = obj;
		}
		leftModel = new DefaultTableModel(rows, title1);
		leftTable = new JTable(leftModel);
		leftTable = new MTable(leftModel);
		// 鼠标双击事件
		leftTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = leftTable.getSelectedRow();
					int column = leftTable.getAutoResizeMode();
					good_id = (int) leftTable.getValueAt(row, 0);
					if (row != -1) {
						new GetInformation();
					}

				}
			}
		});
		leftSc = new JScrollPane(leftTable);
		leftSc.setBounds(5, 100, 485, 440);
		leftMainPanel.add(leftSc);
		leftPanel.add(leftMainPanel);
		splitPane.add(leftPanel, JSplitPane.LEFT);
		this.add(splitPane);
		leftTable.setRowHeight(25);
	}

	public void addRightPanel() {
		rightPanel.setLayout(null);// 右边面板
		rightMainPanel = new JPanel();// 右边主面板
		rightMainPanel.setLayout(null);// 右边主面板设置为空
		rightMainPanel.setBounds(5, 5, 465, 490);
		rightMainPanel.setBackground(new Color(235, 235, 235));
		rightMainPanel.setBorder(BorderFactory.createEtchedBorder());

		// 名称面板
		JPanel namePanel = new JPanel();
		namePanel.setBounds(5, 5, 453, 50);
		namePanel.setBackground(stepColor);
		namePanel.setBorder(BorderFactory.createEtchedBorder());
		// 名称标签
		JLabel nameLabel = new JLabel("所选商品");
		nameLabel.setBounds(5, 13, 100, 20);
		nameLabel.setFont(new Font("宋体", Font.PLAIN, 24));
		namePanel.add(nameLabel);
		rightMainPanel.add(namePanel);

		Object[][] row = new Object[0][];
		rightModel = new DefaultTableModel(row, title2);
		rightTable = new JTable(rightModel);
		rightTable = new MTable(rightModel);
		rightTable.setRowHeight(25);
		rightSc = new JScrollPane(rightTable);
		rightSc.setBounds(5, 60, 453, 420);
		rightMainPanel.add(rightSc);
		rightPanel.add(rightMainPanel);
		
		rightTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = rightTable.getSelectedRow();
					int column = rightTable.getAutoResizeMode();
					good_id = (int) rightTable.getValueAt(row, 0);
					if (row != -1) {
						new updateNum();
					}

				}
			}
		});
		Color[] color= new Color[100];
		for(int i = 0;i<100;i++) {
			int num = i%2;
			if(num==0) {
				color[i] = Color.WHITE;
			}else {
				color[i] = new Color(155,193,242);
			}
			
		}
		commondMethods.setColor(rightTable,color);
		commondMethods.setColor(leftTable,color);
		
	    

		// 添加底部面板
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(5, 500, 465, 55);
		bottomPanel.setBackground(stepColor);
		bottomPanel.setBorder(BorderFactory.createEtchedBorder());

		// 添加修改按钮
		updateBtn.setBounds(10, 10, 60, 30);
		bottomPanel.add(updateBtn);
		// 添加删除按钮
		delBtn.setBounds(130, 10, 60, 30);
		bottomPanel.add(delBtn);
		// 添加确定按钮
		confirmBtn.setBounds(250, 10, 60, 30);
		bottomPanel.add(confirmBtn);
		// 添加取消按钮
		cancelBtn.setBounds(370, 10, 60, 30);
		bottomPanel.add(cancelBtn);

		rightPanel.add(bottomPanel);
		splitPane.add(rightPanel, JSplitPane.RIGHT);

	}

	public void addRegisterListener() {
		queryBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String search = "" + rearchText.getText();
				search.trim();
				try {
					Object searchText = search;
					// 创建表格
					String[] title = { "商品编号", "商品名称", "单位", "规格", "单价", "库存量" };
					// 从数据库获取商品信息
					StoTransDao3 goodDao = new StoTransDao3();
					List<Good> listGood = goodDao.query(searchText);
					Object[][] rows = new Object[listGood.size()][];
					for (int i = 0; i < rows.length; i++) {
						Good good = listGood.get(i);
						// 将对象转换为数组
						Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
								good.getGoods_size(), good.getGoods_sellPrice(), good.getGoods_number() };
						// 给二维数组赋值
						rows[i] = obj;
					}
					leftModel.setDataVector(rows, title);
					Color[] color= new Color[100];
					for(int i = 0;i<100;i++) {
						int num = i%2;
						if(num==0) {
							color[i] = Color.WHITE;
						}else {
							color[i] = new Color(155,193,242);
						}
						
					}
					commondMethods.setColor(leftTable,color);
				} catch (NumberFormatException numberFormatException) {
					JOptionPane.showMessageDialog(PlanAddOldGoods.this, "请输入正确的商品编号或名称", "操作提示",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		confirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 右边表的行的总数
				int rightRowsCount = rightModel.getRowCount();

				for (int i = 0; i < rightRowsCount; i++) {
					// 获取商品的编号
					int gId = (int) rightTable.getValueAt(i, 0);
					// 获取商品的数量
					int gNum = (int) rightTable.getValueAt(i, 4);
					// 从数据库获得数据
					StoTransDao3 stoTransDao = new StoTransDao3();
					// 创建对象保存数据
					Good good = null;
					try {
						good = stoTransDao.get(gId);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
							good.getGoods_size(), good.getGoods_purPrise(), gNum, good.getGoods_purPrise() * gNum };
					goodModel.addRow(obj);
					AddPurPlan.priceCount += good.getGoods_purPrise() * gNum;
				}

				AddPurPlan.shouldPayText.setText("" + AddPurPlan.priceCount);
				PlanAddOldGoods.this.dispose();
			}
		});

		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = rightTable.getSelectedRow();
				if (row != -1) {
					new updateNum();
				}else {
					JOptionPane.showMessageDialog(null, "请单击选择右边表格一行","操作提示",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		delBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int row = rightTable.getSelectedRow();
				if (row > -1) {
					rightModel.removeRow(row);
				}

			}
		});
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PlanAddOldGoods.this.dispose();
			}
		});
	}

	// 内部类，获得点击的商品信息
	class GetInformation extends JFrame {
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
		JTextField purNumText;// 采购数量文本框

		// 构造方法
		public GetInformation() {
			this.init();
			addPanel();
			addMouseListener();
			setVisible(true);
		}

		// 初始化
		public void init() {
			setTitle("商品信息（采购计划）");
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
			this.setResizable(false);
		}

		public void addPanel() {
			this.setLayout(null);
			// 创建主面板
			JPanel mainGoodPanel = new JPanel();
			mainGoodPanel.setLayout(null);
			mainGoodPanel.setBackground(stepColor);
			mainGoodPanel.setBounds(5, 50, 520, 250);
			mainGoodPanel.setBorder(BorderFactory.createEtchedBorder());

			// 创建名称面板
			JPanel goodPanel1 = new JPanel();
			goodPanel1.setBackground(stepColor);
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
			JLabel idInfor = new JLabel("" + good_id);
			idInfor.setBounds(90, 15, 100, 20);
			idInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(idInfor);

			nameText = good.getGoods_name();// 商品名称
			JLabel nameInfor = new JLabel(nameText);
			nameInfor.setBounds(360, 15, 100, 20);
			nameInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(nameInfor);

			unitsText = good.getGoods_units();// 基本单位
			JLabel unitsInfor = new JLabel(unitsText);
			unitsInfor.setBounds(90, 55, 100, 20);
			unitsInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(unitsInfor);

			sizeText = good.getGoods_size();// 规格大小
			JLabel sizeInfor = new JLabel(sizeText);
			sizeInfor.setBounds(360, 55, 100, 20);
			sizeInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(sizeInfor);

			purPriceText = good.getGoods_purPrise();// 商品进价
			JLabel purPriseInfor = new JLabel("" + purPriceText);
			purPriseInfor.setBounds(90, 95, 100, 20);
			nameInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(purPriseInfor);

			sellPriceText = good.getGoods_sellPrice();// 商品售价
			JLabel sellPriceInfor = new JLabel("" + sellPriceText);
			sellPriceInfor.setBounds(360, 95, 100, 20);
			sellPriceInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(sellPriceInfor);

			goodNumber = good.getGoods_number();// 当前库存数量
			JLabel numberInfor = new JLabel("" + goodNumber);
			numberInfor.setBounds(90, 135, 100, 20);
			numberInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(numberInfor);

			stoId = good.getGoods_stoId();// 所在仓库
			JLabel stoIdInfor = new JLabel("" + stoId);
			stoIdInfor.setBounds(360, 135, 100, 20);
			stoIdInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(stoIdInfor);

			keepDays = good.getGoods_keepDays();// 保质期
			JLabel keeyDaysInfor = new JLabel("" + keepDays + "天");
			keeyDaysInfor.setBounds(90, 175, 100, 20);
			stoIdInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(keeyDaysInfor);

			minNumber = good.getGoods_minNumber();// 最小库存
			JLabel minNumberInfor = new JLabel("" + minNumber);
			minNumberInfor.setBounds(360, 175, 100, 20);
			minNumberInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(minNumberInfor);

			goodMark = good.getGoods_mark();// 商品备注
			JLabel markInfor = new JLabel(goodMark);
			markInfor.setBounds(90, 210, 300, 30);
			markInfor.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(markInfor);

			JLabel purNumber = new JLabel("采购数量:");
			purNumber.setBounds(280, 215, 100, 20);
			purNumber.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(purNumber);

			purNumText = new JTextField();
			purNumText.setBounds(360, 215, 100, 20);
			purNumText.setFont(new Font("宋体", Font.PLAIN, 16));
			mainGoodPanel.add(purNumText);

			this.add(mainGoodPanel);
			// 添加底部按钮面板
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(null);
			btnPanel.setBackground(stepColor);
			btnPanel.setBounds(5, 305, 520, 50);
			btnPanel.setBorder(BorderFactory.createEtchedBorder());
			// 添加按钮
			inforConfirmBtn = new JButton("确定");
			inforConfirmBtn.setBounds(130, 15, 100, 20);
			inforConfirmBtn.setBackground(new Color(175, 208, 244));
			inforConfirmBtn.setFont(new Font("宋体", Font.PLAIN, 12));
			btnPanel.add(inforConfirmBtn);
			inforCancelBtn = new JButton("取消");
			inforCancelBtn.setBounds(300, 15, 100, 20);
			inforCancelBtn.setBackground(new Color(175, 208, 244));
			inforCancelBtn.setFont(new Font("宋体", Font.PLAIN, 12));
			btnPanel.add(inforCancelBtn);
			// 添加确定和退出按钮
			this.add(btnPanel);

		}

		public void addMouseListener() {
			inforConfirmBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (!purNumText.getText().equals("") && Integer.parseInt(purNumText.getText()) > 0) {

							int purNum = Integer.parseInt(purNumText.getText());
							good_purPlanPrice = purPriceText;
							Object[] obj = { good_id, nameText, unitsText, purPriceText, purNum,
									purPriceText * purNum };
							rightModel.addRow(obj);
							GetInformation.this.dispose();
						} else if (("").equals(purNumText.getText())) {
							JOptionPane.showMessageDialog(null, "数量不能为空");
						} else if (Integer.parseInt(purNumText.getText()) < 0
								|| Integer.parseInt(purNumText.getText()) == 0) {
							JOptionPane.showMessageDialog(null, "数量不能为负数或者0");
						}
					} catch (NumberFormatException numberFormatException) {
						JOptionPane.showMessageDialog(null, "请输入正确的数量");
					}

				}
			});
			inforCancelBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					GetInformation.this.dispose();
				}
			});
		}
	}

	class updateNum extends JFrame {
		// 添加面板
		JButton updateConfirm = new JButton("确定");
		JButton updateCancel = new JButton("取消");

		// 构造方法
		public updateNum() {
			this.init();
			addPanel();
			addListener();
			setVisible(true);
		}

		// 初始化
		public void init() {
			setTitle("修改数量");
			setSize(400, 185);
			setLayout(null);
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
			this.setResizable(false);
		}

		// 添加面板
		public void addPanel() {
			// 添加面板
			JPanel numPanel = new JPanel();
			numPanel.setLayout(null);
			numPanel.setBackground(stepColor);
			numPanel.setBounds(10, 10, 365, 80);
			numPanel.setBorder(BorderFactory.createEtchedBorder());
			this.add(numPanel);

			// 添加修改标签
			JLabel updateLabel = new JLabel("修改数量:");
			updateLabel.setFont(new Font("宋体", Font.PLAIN, 20));
			updateLabel.setBounds(40, 30, 100, 30);
			numPanel.add(updateLabel);

			// 添加数量文本框
			numText = new JTextField();
			numText.setBounds(160, 30, 100, 30);
			numPanel.add(numText);

			// 添加按钮面板
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(null);
			btnPanel.setBackground(stepColor);
			btnPanel.setBounds(10, 100, 365, 40);
			btnPanel.setBorder(BorderFactory.createEtchedBorder());
			// 添加按钮
			updateConfirm.setBounds(10, 10, 100, 20);
			btnPanel.add(updateConfirm);
			updateCancel.setBounds(200, 10, 100, 20);
			btnPanel.add(updateCancel);

			this.add(btnPanel);

		}

		public void addListener() {
			updateConfirm.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						good_purPlanNum = Integer.parseInt(numText.getText());
						int row = rightTable.getSelectedRow();
						good_purPlanPrice = (double) rightTable.getValueAt(row, 3);
						rightModel.setValueAt(good_purPlanNum, row, 4);
						rightModel.setValueAt(good_purPlanNum * good_purPlanPrice, row, 5);
						updateNum.this.dispose();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的数量");
					}

				}
			});
			updateCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					updateNum.this.dispose();
				}
			});
		}
	}

	public static void main(String[] args) throws Exception {
		new PlanAddOldGoods(goodTable, goodModel);
	}

}
