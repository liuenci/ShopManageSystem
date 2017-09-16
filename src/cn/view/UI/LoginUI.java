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
 * 1.����:2017-08-01
 * 2.����:
 * 	a.ʵ�ֵ�¼��������
 * 	b.ʵ������������ת���Ի���
 * 	c.���ô�������
 * @author LuckyJavaCi
 */
public class LoginUI extends JFrame {
	JPanel topPanel = null;//�������
	JLabel topLabel = null;//����ͼƬ��ǩ
	JPanel logoPanel = null;//���logo
	JLabel spaceLabel = null;//�ո��ǩ
	JLabel logoLabel = null;//���ͼƬ��ǩ
	JPanel msgPanel = null;//��¼��Ϣ���
	JLabel userLabel =null;//�û���
	JLabel passwordLabel = null;//����
	static JTextField userText = null;//�û����ı���
	JPasswordField passwordText = null;//�����ı���
	JButton loginButton = null;//��¼��ť
	JButton forgivePasswordButton = null;//�������밴ť
	CommondMethods commondMethods =	null;//ͨ�÷���
	/*
	 * ���췽��
	 */
	public LoginUI() {
		commondMethods =new  CommondMethods();
		this.init();
		this.addPanel();
		this.elementListener();
		this.setVisible(true);
	}

	/*
	 * ��ʼ������
	 */
	public void init() {
		this.setTitle("���й���ϵͳ");
		this.setSize(450, 370);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���ô��ھ���
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		
		//���ô�С���ɱ�
		this.setResizable(false);
	}

	/*
	 * ������
	 */
	public void addPanel() {
		// TOP���
		topPanel = new JPanel();
		
		//����ͼƬ��ǩ
		topLabel = new JLabel();
		ImageIcon icon = new ImageIcon("images/logo.jpg");
		Image topImage = icon.getImage();
		int topWidth = 450;
		int topHeight = 200;
		topImage = topImage.getScaledInstance(topWidth, topHeight, Image.SCALE_DEFAULT);
		icon.setImage(topImage);
		topLabel.setIcon(icon);
		topPanel.add(topLabel);

		// Logo���
		logoPanel = new JPanel();  
		// �ո��ǩ�������ƶ�λ��
		spaceLabel = new JLabel("         ");
		logoLabel = new JLabel();
		ImageIcon logoIcon = new ImageIcon("images/����.png");
		Image logoImage = logoIcon.getImage();
		int logoWidth = 100;
		int logoHeight = 100;
		logoImage = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_DEFAULT);
		logoIcon.setImage(logoImage);
		logoLabel.setIcon(logoIcon);
		logoPanel.add(spaceLabel);
		logoPanel.add(logoLabel);

		// �˺��������
		msgPanel = new JPanel();
		msgPanel.setLayout(null);
		// �����˺ű�ǩ
		userLabel = commondMethods.createLabel("��  ��:", 30, 10, 80, 30, "����", 16);
		// ���������ǩ
		passwordLabel = commondMethods.createLabel("��  ��:", 30, 40, 80, 30, "����", 16);
		// �����˺������
		userText = commondMethods.createTextField("", 90, 15, 140, 20, "����", 16, new Color(236,242,242));
		// �������������
		passwordText = commondMethods.createPasswordField(90, 45, 140, 20, new Color(236,242,242));
		// ����ǩ��ӵ����
		msgPanel.add(userLabel);
		msgPanel.add(passwordLabel);
		// ���������ӵ����
		msgPanel.add(userText);
		msgPanel.add(passwordText);

		// ������½���������밴ť
		loginButton = commondMethods.createButton("��    ¼", 30, 80, 80, 30, "����", 14);
		forgivePasswordButton = commondMethods.createButton("��������", 150, 80, 80, 30, "����", 14);
		// ����ť��ӵ����
		msgPanel.add(loginButton);
		msgPanel.add(forgivePasswordButton);
		
		
		
		// �������ӵ�����
		this.add(topPanel, BorderLayout.NORTH);
		this.add(logoPanel, BorderLayout.WEST);
		this.add(msgPanel);
	}
	//����ص��������ʱ�������
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
						//����������壬�رյ�¼ҳ��
						LoginUI.this.dispose();
					}else if(userContent.equals("")||passwordContent.equals("")) {
						JOptionPane.showMessageDialog(null, "�˺Ż����벻��Ϊ��");
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
						JOptionPane.showMessageDialog(null, "�˺Ż��������");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "�˺Ż��������");
				}
				
				
			}
		});
		forgivePasswordButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ForgiveDialog(LoginUI.this, "��������", true);
			}
		});
	}
	// ��������
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
		//��������
		setTheme();
		//������½�������
		LoginUI loginUI = new LoginUI();
	}

}
