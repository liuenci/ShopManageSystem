package cn.storage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.tree.DefaultMutableTreeNode;

import cn.dao.storage.GoodDao3;
import cn.dao.storage.StoTransDao3;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.view.purchase.MTable;

/**
 * 日期:2017-08-17 
 * 功能: 
 * 	1.分仓库查询商品
 * 	2.修改商品信息
 * 	3.根据商品编号或名称模糊查询
 * @author LuckyJavaCi
 *
 */
public class StorageQuery extends JFrame {
	private CommondMethods commondMethods = new CommondMethods();// 通用方法
	private JSplitPane splitPane; // 创建分割面板
	private JScrollPane spleft; // 左边滚动面板
	private JScrollPane spright; // 右边滚动面板
	private JTree tree; // 创建树形菜单
	private JTable table; // 创建表格
	private DefaultTableModel goodModel; // 创建表模型
	static int goodId = 0;
	String[] colname = { "商品编号", "商品姓名", "商品单位", "规格大小", "商品进价", "商品售价", "库存数量", "仓库名称", "保质天数", "最低库存", "商品备注" };

	/**
	 * 构造方法
	 */
	public StorageQuery() {
		this.init();// 调用初始化窗口
		this.addPanel();
		this.setVisible(true);// 设置窗口可见
		splitPane.setDividerLocation(0.25);
	}

	/**
	 * 初始化窗口
	 */
	public void init() {
		// 设置窗口标题
		this.setTitle("库存查询");
		// 设置窗口大小
		this.setSize(1000, 600);
		// 设置窗口位置
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		// 设置窗口关闭
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

	public void addPanel() {
		// 创建分割面板对象
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		// 创建左边面板
		JPanel leftPanel = commondMethods.createPanel(10, 10, 100, 100);
		leftPanel.setBackground(Color.white);

		// 创建左边主面板
		JPanel leftMain = commondMethods.createPanel(5, 5, 230, 550);
		leftMain.setBorder(BorderFactory.createTitledBorder("商品类别"));

		// 创建左边上面面板
		JPanel leftTop = commondMethods.createPanel(10, 30, 210, 50);
		leftMain.add(leftTop);
		// 创建商品类别标签
		JLabel typeLabel = commondMethods.createLabel("商品类别:", 10, 10, 100, 30, "宋体", 14);
		leftTop.add(typeLabel);

		// 创建商品类别文本框
		JTextField typeText = commondMethods.createTextField("", 80, 14, 110, 25, "宋体", 13, null);
		leftTop.add(typeText);
		Document dt = typeText.getDocument();
		dt.addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String stoName = typeText.getText().trim();
				int stoId = 0;
				if (stoName.equals("主仓库")) {
					stoId = 1;
				} else if (stoName.equals("饮料库")) {
					stoId = 2;
				} else if (stoName.equals("酒库")) {
					stoId = 3;
				} else if (stoName.equals("零食库")) {
					stoId = 4;
				} else if (stoName.equals("商品类别")) {
				}
				StoTransDao3 stoTransDao = new StoTransDao3();
				List<Good> listGood = stoTransDao.queryByStoId(stoId);
				Object[][] rows = new Object[listGood.size()][];
				for (int i = 0; i < listGood.size(); i++) {
					Good good = listGood.get(i);
					Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
							good.getGoods_size(), good.getGoods_purPrise(), good.getGoods_sellPrice(),
							good.getGoods_number(), stoName, good.getGoods_keepDays(), good.getGoods_minNumber(),
							good.getGoods_mark() };
					rows[i] = obj;
				}
				goodModel.setDataVector(rows, colname);
				Color[] color = new Color[100];
				for (int i = 0; i < 100; i++) {
					int num = i % 2;
					if (num == 0) {
						color[i] = Color.WHITE;
					} else {
						color[i] = new Color(165,234,255);
					}

				}
				commondMethods.setColor(table, color);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		leftPanel.add(leftMain);
		// 创建右边面板
		JPanel rightPanel = commondMethods.createPanel(0, 0, 0, 0);
		// 创建右边主面板
		JPanel rightMain = commondMethods.createPanel(10, 10, 710, 540);
		rightPanel.add(rightMain);
		// 创建右边功能面板
		JPanel funcPanel = commondMethods.createPanel(10, 10, 690, 80);
		rightMain.add(funcPanel);
		// 创建按钮
		JButton goodUpdateBtn = commondMethods.createButton("修改", 20, 20, 60, 30, "宋体",16);
		funcPanel.add(goodUpdateBtn);
		goodUpdateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int goodRow = table.getSelectedRow();
				if(goodRow!=-1) {
					goodId = (int) table.getValueAt(goodRow, 0);
					new GetInformation();
				}else {
					JOptionPane.showMessageDialog(null, "请点击要修改的行");
				}
			}
		});
		JButton exitBtn = commondMethods.createButton("退出", 570, 20, 60, 30,"宋体",16);
		funcPanel.add(exitBtn);
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StorageQuery.this.dispose();
			}
		});
		//创建查询标签
		JLabel queryLabel = commondMethods.createLabel("商品编号/名称:", 130, 20, 150, 30, "宋体", 16);
		funcPanel.add(queryLabel);
		//创建查询文本框
		JTextField queryText = commondMethods.createTextField("", 245, 25, 150, 25, "宋体", 15, null);
		funcPanel.add(queryText);
		//创建查询按钮
		JButton queryBtn = commondMethods.createButton("查询",410, 20, 60, 30, "宋体", 16);
		funcPanel.add(queryBtn);
		queryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GoodDao3 goodDao3 = new GoodDao3();
				List<Good> queryIdOrName = goodDao3.QueryIdOrName(queryText.getText());
				Object[][] goodsRow = new Object[queryIdOrName.size()][];
				for(int i=0;i <goodsRow.length;i++) {
					Good good = queryIdOrName.get(i);
					String stoName = "";
					if(good.getGoods_stoId()==1) {
						stoName = "主仓库";
					}else if(good.getGoods_stoId()==2) {
						stoName = "酒库";
					}else if(good.getGoods_stoId()==3) {
						stoName = "饮料库";
					}else if(good.getGoods_stoId()==4) {
						stoName = "零食库";
					}
					Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
							good.getGoods_size(), good.getGoods_purPrise(), good.getGoods_sellPrice(),
							good.getGoods_number(), stoName, good.getGoods_keepDays(), good.getGoods_minNumber(),
							good.getGoods_mark() };
					goodsRow[i] = obj;
				}
				goodModel.setDataVector(goodsRow,colname);
				Color[] color = new Color[100];
				for (int i = 0; i < 100; i++) {
					int num = i % 2;
					if (num == 0) {
						color[i] = Color.WHITE;
					} else {
						color[i] = new Color(165,234,255);
					}

				}
				commondMethods.setColor(table, color);
			}
		});
		//调用左右面板方法
		this.setLeft();
		this.setRight();
		spleft.setBounds(10, 80, 210, 465);
		leftMain.add(spleft);

		// 设置左右分割面板
		splitPane.add(leftPanel, JSplitPane.LEFT);
		splitPane.add(rightPanel, JSplitPane.RIGHT);
		// 创建右边面板
		spright = new JScrollPane(table);
		spright.setBounds(10, 100, 690, 430);
		rightMain.add(spright);
		
		// 将分割面板添加到窗口
		this.add(splitPane);
	}

	// 设置左边面板
	public void setLeft() {
		// 创建父节点
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("商品类别");
		

		// 添加子节点
		node.add(new DefaultMutableTreeNode("主仓库"));
		node.add(new DefaultMutableTreeNode("饮料库"));
		node.add(new DefaultMutableTreeNode("酒库"));
		node.add(new DefaultMutableTreeNode("零食库"));

		tree = new JTree(node);

		// 给tree添加事件
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// 获取选中的节点
				DefaultMutableTreeNode nd = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				String[] colname = { "商品编号", "商品姓名", "商品单位", "规格大小", "商品进价", "商品售价", "库存数量", "仓库名称", "保质天数", "最低库存",
						"商品备注" };
				int stoId = 0;
				if (nd.toString().equals("主仓库")) {
					stoId = 1;
				} else if (nd.toString().equals("饮料库")) {
					stoId = 2;
				} else if (nd.toString().equals("酒库")) {
					stoId = 3;
				} else if (nd.toString().equals("零食库")) {
					stoId = 4;
				} else if (nd.toString().equals("商品类别")) {
				}
				StoTransDao3 stoTransDao = new StoTransDao3();
				List<Good> listGood = stoTransDao.queryByStoId(stoId);
				Object[][] rows = new Object[listGood.size()][];
				for (int i = 0; i < listGood.size(); i++) {
					Good good = listGood.get(i);
					Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
							good.getGoods_size(), good.getGoods_purPrise(), good.getGoods_sellPrice(),
							good.getGoods_number(), nd.toString(), good.getGoods_keepDays(), good.getGoods_minNumber(),
							good.getGoods_mark() };
					rows[i] = obj;
				}
				goodModel.setDataVector(rows, colname);
				Color[] color = new Color[100];
				for (int i = 0; i < 100; i++) {
					int num = i % 2;
					if (num == 0) {
						color[i] = Color.WHITE;
					} else {
						color[i] = new Color(165,234,255);
					}

				}
				commondMethods.setColor(table, color);
			}
		});
		// 创建左边面板
		spleft = new JScrollPane(tree);

	}

	// 设置右边面板
	public void setRight() {
		String[] colname = { "商品编号", "商品姓名", "商品单位", "规格大小", "商品进价", "商品售价", "库存数量", "仓库名称", "保质天数", "最低库存", "商品备注" };
		StoTransDao3 stoTransDao = new StoTransDao3();
		List<Good> listGood = stoTransDao.query();
		Object[][] rows = new Object[listGood.size()][];
		for (int i = 0; i < listGood.size(); i++) {
			Good good = listGood.get(i);
			String sto = null;
			if (good.getGoods_stoId() == 1) {
				sto = "主仓库";
			} else if (good.getGoods_stoId() == 2) {
				sto = "饮料库";
			} else if (good.getGoods_stoId() == 3) {
				sto = "酒库";
			} else if (good.getGoods_stoId() == 4) {
				sto = "零食库";
			}
			Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(), good.getGoods_size(),
					good.getGoods_purPrise(), good.getGoods_sellPrice(), good.getGoods_number(), sto,
					good.getGoods_keepDays(), good.getGoods_minNumber(), good.getGoods_mark() };
			rows[i] = obj;
		}
		goodModel = new DefaultTableModel(rows, colname);
		table = new JTable(goodModel);
		table = new MTable(goodModel);

		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(165,234,255);
			}

		}
		commondMethods.setColor(table, color);
		
		
	}

	public static void main(String[] args) {
		new StorageQuery();

	}

}
