package cn.storage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.table.DefaultTableModel;

import cn.dao.storage.StoTransDao3;
import cn.dao.storage.StorageDao3;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.model.common.Storage;
import cn.view.purchase.MTable;

public class StorageTransport extends JFrame {
	CommondMethods commondMethods = new CommondMethods();
	// 主面板,存放各种标签，按钮
	JPanel mainPanel = null;
	// 创建底部面板
	JPanel bottomPanel = null;
	// 名称标签
	JLabel nameLabel = null;
	// 调出仓库标签
	JLabel outLabel = null;
	// 调出仓库标签
	JLabel inputLabel = null;
	// 日期标签
	JLabel dateLabel = null;

	// 调出仓库名
	JTextField outText = null;
	// 调出仓库名
	JTextField inputText = null;

	int outSto_id = 0;
	int intputSto_id = 0;

	// 调出日期标签
	JLabel dateText = null;
	JButton goodFindBtn = null;

	String stoName = null;
	// 查找按钮
	JButton queryBtn1 = null;
	JButton queryBtn2 = null;

	// 创建滚动面板
	JScrollPane goodSc = null;
	// 创建表模型
	DefaultTableModel goodModel = null;
	// 创建表
	JTable goodTable;
	int RowId = 0;

	int good_id = 0;
	JComboBox stoOutBox = null;
	JComboBox stoInputBox = null;

	Object[][] goodRows = new Object[20][];

	// 经办人
	JLabel empLabel;
	// 经办人下拉列表框
	JComboBox empList;

	// 调拨完毕按钮
	JButton yesBtn;
	JButton cancelBtn;

	JPanel choicePanel;// 创建选择面板
	protected Object stoInputName;

	// 构造方法
	public StorageTransport() {
		init();
		addPanel();
		addTable();
		// addBottomPanel();
		addRegisterListener();
		setVisible(true);
	}

	// 初始化
	public void init() {
		setTitle("库存调拨");
		setSize(800, 500);
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
	}

	// 添加面板
	public void addPanel() {
		// 创建主面板
		mainPanel = createPanel(5, 5, 775, 450);

		// 创建名称标签
		nameLabel = createLabel("库存调拨", 320, 10, 200, 70, "隶书", 36);
		mainPanel.add(nameLabel);

		// 创建选择面板
		choicePanel = createPanel(5, 70, 765, 50);
		// 创建调出仓库标签
		outLabel = createLabel("调出仓库:", 30, 10, 160, 30, "宋体", 16);
		choicePanel.add(outLabel);
		mainPanel.add(choicePanel);
		// 创建仓库下拉列表框
		stoOutBox = createJComboBox(110, 10, 100, 30, "宋体", 14);
		StorageDao3 storageDao = new StorageDao3();
		List<Storage> stoList = storageDao.query();
		for (int i = 0; i < stoList.size(); i++) {
			stoOutBox.addItem(stoList.get(i).getName());
		}
		choicePanel.add(stoOutBox);

		inputLabel = createLabel("调入仓库:", 220, 10, 160, 30, "宋体", 16);
		choicePanel.add(inputLabel);
		mainPanel.add(choicePanel);
		stoInputBox = createJComboBox(300, 10, 100, 30, "宋体", 14);
		for (int i = 0; i < stoList.size(); i++) {
			stoInputBox.addItem(stoList.get(i).getName());
		}
		choicePanel.add(stoInputBox);

		dateLabel = createLabel("调拨日期:", 520, 10, 160, 30, "宋体", 16);
		choicePanel.add(dateLabel);
		mainPanel.add(choicePanel);

		DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		String transDate = df.format(new java.util.Date());

		dateText = createLabel("" + transDate, 600, 10, 160, 30, "宋体", 16);
		dateText.setForeground(Color.red);
		choicePanel.add(dateText);
		mainPanel.add(choicePanel);

		// 查询商品按钮
		goodFindBtn = createButton("查询商品", 420, 10, 80, 30, "宋体", 20);
		choicePanel.add(goodFindBtn);

		// 创建底部面板
		JPanel bottomPanel = createPanel(5, 390, 765, 50);
		mainPanel.add(bottomPanel);

		// 创建底部按钮
		yesBtn = createButton("调拨完成", 430, 10, 100, 30, "宋体", 16);
		bottomPanel.add(yesBtn);
		cancelBtn = createButton("取消调拨", 570, 10, 100, 30, "宋体", 16);
		bottomPanel.add(cancelBtn);

		this.add(mainPanel);
	}

	// 添加表
	public void addTable() {
		// 创建表头
		String[] title = { "商品编号", "商品名称", "商品单位", "商品规格", "商品数量", "所在仓库" };
		goodModel = new DefaultTableModel(null, title);
		goodTable = new JTable(goodModel);
		goodTable = new MTable(goodModel);
		goodSc = new JScrollPane(goodTable);
		goodSc.setBounds(5, 130, 765, 245);
		mainPanel.add(goodSc);
		
		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(155,193,242);
			}

		}
		commondMethods.setColor(goodTable, color);
		
		goodTable.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e1) {
				if (e1.getClickCount() == 2) {

					if (stoInputName.equals("主仓库")) {
						intputSto_id = 1;
					} else if (stoInputName.equals("饮料库")) {
						intputSto_id = 2;
					} else if (stoInputName.equals("酒库")) {
						intputSto_id = 3;
					} else if (stoInputName.equals("零食库")) {
						intputSto_id = 4;
					}
					
					int res = JOptionPane.showConfirmDialog(StorageTransport.this, "是否调拨", "提示信息", JOptionPane.YES_NO_OPTION);
					StoTransDao3 goodsTrans = new StoTransDao3();
					if (res == JOptionPane.YES_OPTION) {
						
						int row = goodTable.getSelectedRow();
						int column = goodTable.getAutoResizeMode();
						goodTable.getValueAt(row, column);
						good_id = (int) goodModel.getValueAt(row, 0);
						Good good = new Good(good_id, intputSto_id);
						int goodRow = goodsTrans.updateGood(good);
						goodFindBtn.doClick();
						JOptionPane.showMessageDialog(null, "调拨成功");
					}else {
						JOptionPane.showMessageDialog(null, "取消成功");
					}
					e1 = null;
					Color[] color = new Color[100];
					for (int i = 0; i < 100; i++) {
						int num = i % 2;
						if (num == 0) {
							color[i] = Color.WHITE;
						} else {
							color[i] = new Color(155,193,242);
						}

					}
					commondMethods.setColor(goodTable, color);
				}
			}
		});
	}

	// 给按钮添加点击事件
	public void addRegisterListener() {
		goodFindBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String stoOutName = stoOutBox.getSelectedItem().toString();
				 stoInputName = stoInputBox.getSelectedItem().toString();
				if (stoOutName.equals("主仓库")) {
					outSto_id = 1;
				} else if (stoOutName.equals("饮料库")) {
					outSto_id = 2;
				} else if (stoOutName.equals("酒库")) {
					outSto_id = 3;
				} else if (stoOutName.equals("零食库")) {
					outSto_id = 4;
				}
				// 创建表格
				String[] str = { "商品编号", "商品名称", "商品单位", "商品规格", "商品数量", "所在仓库"};
				// 获取数据库数据
				StoTransDao3 goodDao = new StoTransDao3();
				List<Good> listSto = goodDao.queryByStoId(outSto_id);
				Object[][] rows = new Object[listSto.size()][];
				for (int i = 0; i < rows.length; i++) {
					// 获取供应商集合对象
					Good good = listSto.get(i);
					// 将对象转为数组存储
					Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
							good.getGoods_size(), good.getGoods_number(), stoOutName };
					// 给二维数组赋值
					rows[i] = obj;
				}
				goodModel.setDataVector(rows, str);
				goodTable.setModel(goodModel);
				Color[] color = new Color[100];
				for (int i = 0; i < 100; i++) {
					int num = i % 2;
					if (num == 0) {
						color[i] = Color.WHITE;
					} else {
						color[i] = new Color(155,193,242);
					}

				}
				commondMethods.setColor(goodTable, color);
			}

		});

		yesBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StorageTransport.this.dispose();
			}

		});
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StorageTransport.this.dispose();
			}

		});

	}

	// 创建面板方法
	public JPanel createPanel(int x, int y, int width, int height) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		Color color = new Color(237, 242, 248);
		jPanel.setBackground(color);
		jPanel.setBounds(x, y, width, height);
		jPanel.setBorder(BorderFactory.createEtchedBorder());
		return jPanel;
	}

	// 创建标签方法
	public JLabel createLabel(String name, int x, int y, int width, int height, String fontName, int fontSize) {
		JLabel jLabel = new JLabel(name);
		jLabel.setBounds(x, y, width, height);
		jLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
		return jLabel;
	}

	// 创建按钮方法
	public JButton createButton(String name, int x, int y, int width, int height, String fontName, int fontSize) {
		JButton jButton = new JButton(name);
		jButton.setBounds(x, y, width, height);
		Color color = new Color(129, 194, 214);
		jButton.setBackground(color);
		return jButton;
	}

	// 创建文本框方法
	public JTextField createTextField(String text, int x, int y, int width, int height, String fontName, int fontSize,
			Color color) {
		JTextField jTextField = new JTextField();
		jTextField.setBounds(x, y, width, height);
		jTextField.setText(text);
		jTextField.setFont(new Font(text, Font.PLAIN, fontSize));
		jTextField.setBackground(color);
		return jTextField;
	}

	// 创建下拉列表框方法
	public JComboBox createJComboBox(int x, int y, int width, int height, String fontName, int fontSize) {
		JComboBox jComboBox = new JComboBox();
		jComboBox.setBounds(x, y, width, height);
		jComboBox.setFont(new Font(fontName, Font.PLAIN, fontSize));
		return jComboBox;
	}

	public static void main(String[] args) {
		new StorageTransport();
	}

}
