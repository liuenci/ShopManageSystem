package cn.view.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cn.dao.storage.EmpDao3;
import cn.init.frame.InitFrame;
import cn.init.frame.InitPurFrame;
import cn.init.frame.InitSellFrame;
import cn.init.frame.InitStoFrame;
import cn.init.frame.StorageAlarmInit;
import cn.method.CommondMethods;
/**
 * 1.日期:2017-08-01
 * 2.功能:
 * 	a.实现登录到主界面
 * 	b.实现忘记密码跳转到对话框
 * 	c.设置窗口主题
 * @author LuckyJavaCi
 */
public class LoginUI extends JFrame {
	JPanel topPanel = null;//北边面板
	JLabel topLabel = null;//北边图片标签
	JPanel logoPanel = null;//左边logo
	JLabel spaceLabel = null;//空格标签
	JLabel logoLabel = null;//左边图片标签
	JPanel msgPanel = null;//登录信息面板
	JLabel userLabel =null;//用户名
	JLabel passwordLabel = null;//密码
	static JTextField userText = null;//用户名文本框
	JPasswordField passwordText = null;//密码文本框
	JButton loginButton = null;//登录按钮
	JButton forgivePasswordButton = null;//忘记密码按钮
	CommondMethods commondMethods =	null;//通用方法
	/*
	 * 构造方法
	 */
	public LoginUI() {
		commondMethods =new  CommondMethods();
		this.init();
		this.addPanel();
		this.elementListener();
		this.setVisible(true);
	}

	/*
	 * 初始化窗口
	 */
	public void init() {
		this.setTitle("超市管理系统");
		this.setSize(450, 370);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置窗口居中
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		
		//设置大小不可变
		this.setResizable(false);
	}

	/*
	 * 添加面板
	 */
	public void addPanel() {
		// TOP面板
		topPanel = new JPanel();
		
		//设置图片标签
		topLabel = new JLabel();
		ImageIcon icon = new ImageIcon("images/logo.jpg");
		Image topImage = icon.getImage();
		int topWidth = 450;
		int topHeight = 200;
		topImage = topImage.getScaledInstance(topWidth, topHeight, Image.SCALE_DEFAULT);
		icon.setImage(topImage);
		topLabel.setIcon(icon);
		topPanel.add(topLabel);

		// Logo面板
		logoPanel = new JPanel();  
		// 空格标签，用于移动位置
		spaceLabel = new JLabel("         ");
		logoLabel = new JLabel();
		ImageIcon logoIcon = new ImageIcon("images/超市.png");
		Image logoImage = logoIcon.getImage();
		int logoWidth = 100;
		int logoHeight = 100;
		logoImage = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_DEFAULT);
		logoIcon.setImage(logoImage);
		logoLabel.setIcon(logoIcon);
		logoPanel.add(spaceLabel);
		logoPanel.add(logoLabel);

		// 账号密码面板
		msgPanel = new JPanel();
		msgPanel.setLayout(null);
		// 创建账号标签
		userLabel = commondMethods.createLabel("账  号:", 30, 10, 80, 30, "宋体", 16);
		// 创建密码标签
		passwordLabel = commondMethods.createLabel("密  码:", 30, 40, 80, 30, "宋体", 16);
		// 创建账号输入框
		userText = commondMethods.createTextField("", 90, 15, 140, 20, "宋体", 16, new Color(236,242,242));
		// 创建密码输入框
		passwordText = commondMethods.createPasswordField(90, 45, 140, 20, new Color(236,242,242));
		// 将标签添加到面板
		msgPanel.add(userLabel);
		msgPanel.add(passwordLabel);
		// 将输入框添加到面板
		msgPanel.add(userText);
		msgPanel.add(passwordText);

		// 创建登陆和忘记密码按钮
		loginButton = commondMethods.createButton("登    录", 30, 80, 80, 30, "宋体", 14);
		forgivePasswordButton = commondMethods.createButton("忘记密码", 150, 80, 80, 30, "宋体", 14);
		// 将按钮添加到面板
		msgPanel.add(loginButton);
		msgPanel.add(forgivePasswordButton);
		
		
		
		// 将面板添加到窗口
		this.add(topPanel, BorderLayout.NORTH);
		this.add(logoPanel, BorderLayout.WEST);
		this.add(msgPanel);
	}
	//给相关的组件设置时间监听器
	public void elementListener() {
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userContent = userText.getText();
				String passwordContent = passwordText.getText();
				EmpDao3 empDao3 = new EmpDao3();
				try {
					if(userContent.equals("admin")&&passwordContent.equals("trkj")) {
						InitFrame inf=new InitFrame();
						inf.start();
						//进入内容面板，关闭登录页面
						LoginUI.this.dispose();
					}else if(userContent.equals("")||passwordContent.equals("")) {
						JOptionPane.showMessageDialog(null, "账号或密码不能为空");
					}else if(empDao3.get(Integer.parseInt(userContent)).getEmp_password().equals(passwordContent)){
						int roleId = empDao3.get(Integer.parseInt(userContent)).getEmp_position_id();
						if(roleId==1) {
							LoginUI.this.dispose();
							InitPurFrame initPurFrame = new InitPurFrame();
							initPurFrame.start();
						}else if(roleId==2) {
							LoginUI.this.dispose();
							InitSellFrame initSellFrame = new InitSellFrame();
							initSellFrame.start();
						}else if(roleId==3) {
							LoginUI.this.dispose();
							InitStoFrame initStoFrame = new InitStoFrame();
							initStoFrame.start();
						}
					}else{
						JOptionPane.showMessageDialog(null, "账号或密码错误");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "账号或密码错误");
				}
				
				
			}
		});
		forgivePasswordButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ForgiveDialog(LoginUI.this, "忘记密码", true);
			}
		});
	}
	// 设置主题
	public static void setTheme() {
		setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//设置主题
		setTheme();
		//创建登陆界面对象
		LoginUI loginUI = new LoginUI();
	}

}
