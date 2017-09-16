package cn.view.UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.dao.storage.EmpDao3;
import cn.method.CommondMethods;
import cn.model.common.Employee;
/**
 * 日期:2017-08-03
 * 功能：
 * 	1.提供退出登录功能
 * 	2.给管理员提供所有功能
 * 	3.给采购员提供采购进货功能
 * 	4.给销售员提供销售功能
 * 	5.给仓管员提供仓库管理功能
 * @author LuckyJavaCi
 *
 */
public class ContentUI extends JFrame {
	private CommondMethods commondMethods = null;// 通用方法
	private JPanel topPanel = null;// 顶栏面板
	private JPanel leftPanel = null;// 左侧面板
	private JPanel rightPanel = null;// 右侧面板
	private JPanel centerPanel = null;// 子模块面板
	private JPanel bottomPanel = null;// 底部面板
	private JLabel nameLabel;// 顶部名字标签
	private JButton leftBtn1 = null;// 采购进货
	private JButton leftBtn2 = null;// 销售管理
	private JButton leftBtn3 = null;// 库存管理
	private JButton leftBtn4 = null;// 统计报表
	private JButton leftBtn5 = null;// 系统管理
	StorageUI stoUI = null;// 仓管页面对象
	PurchaseUI purUI = null;// 采购页面对象
	StatisticsUI statisticsUI = null;// 统计页面对象
	SystemManageUI systemManage = null;// 系统管理对象
	SellUI sellUI = null;// 销售页面对象
	CardLayout cl = null;// 创建卡片布局对象
	JLabel rightLabel = null;// 左侧标签，用于显示图片
	Color color = null;// 通用颜色
	JLabel manageLabel = null;

	/**
	 * 构造方法
	 *  1.实例化各种对象
	 *  2.调用实现方法
	 */
	public ContentUI() {
		commondMethods = new CommondMethods();// 通用方法
		leftBtn1 = new JButton("采购进货");
		leftBtn2 = new JButton("销售管理");
		leftBtn3 = new JButton("库存管理");
		leftBtn4 = new JButton("统计报表");
		leftBtn5 = new JButton("系统管理");

		cl = new CardLayout();
		color = new Color(165, 234, 255);
		init();// 初始化
		addTopPanel();// 添加顶层面板
		addLeftPanel();// 添加左侧面板
		addContentPanel();// 添加内容面板
		setVisible(true);// 设置可见
	}

	/**
	 * 初始化窗口方法
	 */
	public void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置进程关闭
		this.setSize(1000, 600);

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
		this.setTitle("超市管理系统");
		this.setLayout(null);

		setResizable(false);// 设置不可改变大小
	}

	/**
	 * 添加顶部面板
	 */
	public void addTopPanel() {
		// 创建顶部面板
		topPanel = commondMethods.createPanel(0, 0, 1000, 100);
		nameLabel = commondMethods.createLabel("<html>超市管理系统欢迎您</html>", 180, 0, 1000, 100, "华文行楷", 50);
		topPanel.add(nameLabel);
		this.add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(new Color(184,222,223));

		// 创建用户名标签
		JLabel userLabel = commondMethods.createLabel("用户名:", 750, 70, 100, 30, "", 0);
		userLabel.setFont(new Font("宋体", Font.PLAIN, 24));
		topPanel.add(userLabel);

		// 创建退出按钮
//		JButton exitBtn = commondMethods.createButton("退出", 920, 75, 60, 20, "宋体", 20);
//		topPanel.add(exitBtn);
		//创建退出标签
		JLabel exitLabel = commondMethods.createLabel("<html><a href ='#'>退出</a></html>", 920, 68, 60, 30, "宋体", 20);
		topPanel.add(exitLabel);
		exitLabel.setForeground(Color.BLACK);
		exitLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
					ContentUI.this.dispose();
					new LoginUI();
			}
		});
//		exitBtn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ContentUI.this.dispose();
//				new LoginUI();
//			}
//		});
	}

	/**
	 * 添加左侧面板
	 */
	public void addLeftPanel() {
		leftPanel = commondMethods.createPanel(0, 100, 120, 500);
		leftPanel.setBackground(new Color(184,222,223));
		BoxLayout bl = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);// 左侧的盒子布局
		leftPanel.setLayout(bl);

		// 将按钮添加到面板
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(leftBtn1);
		leftBtn1.setFont(new Font("宋体", Font.BOLD, 20));
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(leftBtn2);
		leftBtn2.setFont(new Font("宋体", Font.BOLD, 20));
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(leftBtn3);
		leftBtn3.setFont(new Font("宋体", Font.BOLD, 20));
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(leftBtn4);
		leftBtn4.setFont(new Font("宋体", Font.BOLD, 20));
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(leftBtn5);
		leftBtn5.setFont(new Font("宋体", Font.BOLD, 20));
		leftPanel.add(Box.createVerticalStrut(50));

		// 将面板添加到窗口
		this.add(leftPanel, BorderLayout.WEST);
	}

	/**
	 * 创建内容面板
	 */
	public void addContentPanel() {
		centerPanel = commondMethods.createPanel(120, 100, 880, 500);

		// 设置卡片布局
		centerPanel.setLayout(cl);

		// 创建选项卡面板
		centerPanel.setBackground(color);
		stoUI = new StorageUI();
		purUI = new PurchaseUI();
		statisticsUI = new StatisticsUI();
		sellUI = new SellUI();
		systemManage = new SystemManageUI();

		// 将选项卡面板添加到内容面板
		centerPanel.add(purUI);
		centerPanel.add(sellUI);
		centerPanel.add(stoUI);
		centerPanel.add(statisticsUI);
		centerPanel.add(systemManage);

		// 创建面板切换对象
		CardAction ca = new CardAction();

		// 给5个按钮添加事件
		leftBtn1.addActionListener(ca);
		leftBtn2.addActionListener(ca);
		leftBtn3.addActionListener(ca);
		leftBtn4.addActionListener(ca);
		leftBtn5.addActionListener(ca);

		// 创建登陆用户标签
		if (LoginUI.userText.getText().equals("admin")) {
			manageLabel = commondMethods.createLabel("管理员", 840, 70, 100, 30, "宋体", 24);
			manageLabel.setFont(new Font("宋体", Font.PLAIN, 24));
			topPanel.add(manageLabel);
		} else {
			// 设置显示的名字
			EmpDao3 empDao3 = new EmpDao3();
			Employee emp = empDao3.get(Integer.parseInt(LoginUI.userText.getText()));
			JLabel roleName = commondMethods.createLabel(emp.getEmp_name(), 840, 70, 100, 30, "宋体", 24);
			roleName.setFont(new Font("宋体", Font.PLAIN, 24));
			topPanel.add(roleName);
			// 设置按钮不可编辑
			if (emp.getEmp_position_id() == 1) {
				leftBtn2.setEnabled(false);
				leftBtn3.setEnabled(false);
				leftBtn4.setEnabled(false);
				leftBtn5.setEnabled(false);
				
			} else if (emp.getEmp_position_id() == 2) {
				leftBtn1.setEnabled(false);
				leftBtn3.setEnabled(false);
				leftBtn4.setEnabled(false);
				leftBtn5.setEnabled(false);
				centerPanel.removeAll();
				centerPanel.add(sellUI);
				sellUI.setVisible(true);
			} else if (emp.getEmp_position_id() == 3) {
				leftBtn1.setEnabled(false);
				leftBtn2.setEnabled(false);
				leftBtn4.setEnabled(false);
				leftBtn5.setEnabled(false);
				centerPanel.removeAll();
				centerPanel.add(stoUI);
				stoUI.setVisible(true);
			}
		}

		// 将面板添加到窗口
		this.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * 按钮点击操作（成员内部类）
	 */
	class CardAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 设置切换事件
			if (e.getSource() == leftBtn1) {
				centerPanel.removeAll();
				centerPanel.add(purUI);
				purUI.setVisible(true);
			} else if (e.getSource() == leftBtn2) {
				centerPanel.removeAll();
				centerPanel.add(sellUI);
				sellUI.setVisible(true);
			} else if (e.getSource() == leftBtn3) {
				centerPanel.removeAll();
				centerPanel.add(stoUI);
				stoUI.setVisible(true);
			} else if (e.getSource() == leftBtn4) {
				centerPanel.removeAll();
				centerPanel.add(statisticsUI);
				statisticsUI.setVisible(true);
			} else if (e.getSource() == leftBtn5) {
				centerPanel.removeAll();
				centerPanel.add(systemManage);
				systemManage.setVisible(true);
			}
			centerPanel.repaint();
		}
	}

	public static void main(String[] args) {
		new ContentUI();
	}
}
