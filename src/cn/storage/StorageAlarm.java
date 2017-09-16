package cn.storage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.dao.storage.StoTransDao3;
import cn.dao.storage.StorageDao3;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.view.purchase.MTable;

/**
 * 日期:2017-8-21 功能:显示库存量小于最低库存的商品
 * 
 * @author LuckyJavaCi
 *
 */
public class StorageAlarm extends JFrame {
	private CommondMethods commondMethods = null;// 通用方法
	private DefaultTableModel stoGoodModel;// 计划商品表模型
	private Color color = null;// 通用颜色
	private JTable stoGoodTable;// 计划商品表
	private JScrollPane stoGoodPane;// 计划商品滚动面板
	private String[] title = { "商品编号", "商品名称", "商品单位", "规格大小", "采购单价", "销售单价", "所在仓库", "保质天数", "库存数量", "最低库存", "商品备注" };
	private JTextField numText = null;// 数量更新文本框
	private int goodMinNum;// 最小库存数量文本框
	private int goodId;// 商品编号
	int selectRow;
	JButton queryBtn;

	/**
	 * 构造方法
	 * 
	 * @param args
	 */
	public StorageAlarm() {
		commondMethods = new CommondMethods();
		color = new Color(237, 242, 248);
		this.init();
		this.addPanel();
		this.setVisible(true);
	}

	/**
	 * 初始化窗口
	 * 
	 * @param args
	 */
	public void init() {
		this.setTitle("库存警报");
		this.setSize(800, 500);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		// 设置窗口不可改变
		this.setResizable(false);
	}

	/**
	 * 添加面板
	 * 
	 * @param args
	 */
	public void addPanel() {
		// 创建主面板
		JPanel mainPanel = commondMethods.createPanel(5, 5, 785, 460);
		this.add(mainPanel);
		// 创建名称标签
		JLabel nameLabel = commondMethods.createLabel("库存警报", 340, 10, 200, 30, "隶书", 30);
		mainPanel.add(nameLabel);
		// 创建功能面板
		JPanel funcPanel = commondMethods.createPanel(5, 50, 775, 60);
		mainPanel.add(funcPanel);
		// 创建商品编号或名称标签
		JLabel goodLabel = commondMethods.createLabel("按商品编号/名称:", 10, 10, 150, 20, "宋体", 14);
		funcPanel.add(goodLabel);
		// 创建编号或者名称查询文本框
		JTextField goodText = commondMethods.createTextField("", 130, 10, 100, 20, "宋体", 14, color);
		funcPanel.add(goodText);
		// 创建查询按钮
		queryBtn = commondMethods.createButton("查询", 230, 10, 60, 20, "宋体", 14);
		funcPanel.add(queryBtn);
		StorageDao3 storageDao3 = new StorageDao3();
		queryBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StoTransDao3 stoTransDao3 = new StoTransDao3();
				List<Good> listGood = stoTransDao3.QueryIdOrName(goodText.getText());
				Object[][] goodRows = new Object[listGood.size()][];
				for (int i = 0; i < listGood.size(); i++) {
					Good good = listGood.get(i);
					String stoName = stoTransDao3.getName(good.getGoods_stoId()).getName();
					Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
							good.getGoods_size(), good.getGoods_purPrise(), good.getGoods_sellPrice(), stoName,
							good.getGoods_keepDays(), good.getGoods_number(), good.getGoods_minNumber(),
							good.getGoods_mark() };
					goodRows[i] = obj;
				}
				stoGoodModel.setDataVector(goodRows, title);
			}
		});
		// 从数据库中获取数据
		StoTransDao3 stoTransDao3 = new StoTransDao3();
		List<Good> listGood = stoTransDao3.alarmQuery();
		Object[][] goodRows = new Object[listGood.size()][];
		for (int i = 0; i < listGood.size(); i++) {
			Good good = listGood.get(i);
			String stoName = stoTransDao3.getName(good.getGoods_stoId()).getName();
			Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(), good.getGoods_size(),
					good.getGoods_purPrise(), good.getGoods_sellPrice(), stoName, good.getGoods_keepDays(),
					good.getGoods_number(), good.getGoods_minNumber(), good.getGoods_mark() };
			goodRows[i] = obj;
		}

		// 创建表模型
		stoGoodModel = new DefaultTableModel(goodRows, title);
		stoGoodTable = new JTable(stoGoodModel);
		stoGoodTable = new MTable(stoGoodModel);
		stoGoodPane = new JScrollPane(stoGoodTable);
		stoGoodPane.setBounds(5, 120, 775, 180);
		mainPanel.add(stoGoodPane);
		
		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(155,193,242);
			}

		}
		commondMethods.setColor(stoGoodTable, color);
		// 创建声明面板
		JPanel statePanel = commondMethods.createPanel(5, 310, 775, 100);
		mainPanel.add(statePanel);
		// 创建声明标签
		JLabel stateLabel = commondMethods.createLabel("<html>以上商品库存不足<br/>请采购员尽快采购<br/>请销售员酌量销售</html>", 300, 0, 500,
				90, "隶书", 24);
		stateLabel.setForeground(Color.red);
		statePanel.add(stateLabel);
		// 创建确定和忽略按钮
		JButton confirmBtn = commondMethods.createButton("我已得知", 500, 420, 100, 30, "宋体", 20);
		mainPanel.add(confirmBtn);
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StorageAlarm.this.dispose();
			}
		});
		// 创建修改按钮
		JButton updateNumBtn = commondMethods.createButton("修改最低库存", 500, 10, 100, 30, "宋体", 14);
		funcPanel.add(updateNumBtn);
		updateNumBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectRow = stoGoodTable.getSelectedRow();
				if (selectRow > -1) {
					new UpdateNum();
				} else {
					JOptionPane.showMessageDialog(null, "请选中一行");
				}

			}
		});

	}

	/**
	 * 功能:获得更改后的商品数量
	 * 
	 * @author LuckyJavaCi
	 */
	class UpdateNum extends JFrame {
		JButton updateConfirm;// 确定按钮
		JButton updateCancel;// 取消按钮

		// 构造方法
		public UpdateNum() {
			this.init();
			this.addPanel();
			this.addListener();
			this.setVisible(true);
		}

		// 初始化
		public void init() {
			this.setTitle("修改数量");
			this.setSize(400, 185);
			this.setLayout(null);
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
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		// 添加面板
		public void addPanel() {
			// 添加数字面板
			JPanel numPanel = commondMethods.createPanel(10, 10, 365, 80);
			numPanel.setBorder(BorderFactory.createEtchedBorder());
			this.add(numPanel);

			// 添加修改标签
			JLabel updateLabel = commondMethods.createLabel("修改数量", 40, 30, 100, 30, "宋体", 20);
			numPanel.add(updateLabel);

			// 添加数量文本框
			numText = commondMethods.createTextField("", 160, 30, 100, 30, "宋体", 14, color);
			numPanel.add(numText);
			// 添加按钮面板
			JPanel btnPanel = commondMethods.createPanel(10, 100, 365, 40);
			// 添加按钮
			updateConfirm = commondMethods.createButton("确定", 10, 10, 100, 20, "宋体", 14);
			updateCancel = commondMethods.createButton("取消", 200, 10, 100, 20, "宋体", 14);
			btnPanel.add(updateConfirm);
			btnPanel.add(updateCancel);
			this.add(btnPanel);
		}

		// 时间监听方法
		public void addListener() {
			updateConfirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						// 最低商品数量
						goodMinNum = Integer.parseInt(numText.getText());
						goodId = (int) stoGoodTable.getValueAt(selectRow, 0);
						// 更新数据库数据
						StoTransDao3 stoTransDao3 = new StoTransDao3();
						stoTransDao3.updateMinNum(goodId, goodMinNum);
						JOptionPane.showMessageDialog(null, "数量更新成功");
						UpdateNum.this.dispose();
						queryBtn.doClick();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "请输入正确的数量");
					}

				}
			});
			// 取消更改
			updateCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UpdateNum.this.dispose();
				}
			});
		}
	}

	public static void main(String[] args) {
		new StorageAlarm();
	}

}
