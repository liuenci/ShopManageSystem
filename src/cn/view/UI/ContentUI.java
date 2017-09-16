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
 * ����:2017-08-03
 * ���ܣ�
 * 	1.�ṩ�˳���¼����
 * 	2.������Ա�ṩ���й���
 * 	3.���ɹ�Ա�ṩ�ɹ���������
 * 	4.������Ա�ṩ���۹���
 * 	5.���ֹ�Ա�ṩ�ֿ������
 * @author LuckyJavaCi
 *
 */
public class ContentUI extends JFrame {
	private CommondMethods commondMethods = null;// ͨ�÷���
	private JPanel topPanel = null;// �������
	private JPanel leftPanel = null;// ������
	private JPanel rightPanel = null;// �Ҳ����
	private JPanel centerPanel = null;// ��ģ�����
	private JPanel bottomPanel = null;// �ײ����
	private JLabel nameLabel;// �������ֱ�ǩ
	private JButton leftBtn1 = null;// �ɹ�����
	private JButton leftBtn2 = null;// ���۹���
	private JButton leftBtn3 = null;// ������
	private JButton leftBtn4 = null;// ͳ�Ʊ���
	private JButton leftBtn5 = null;// ϵͳ����
	StorageUI stoUI = null;// �ֹ�ҳ�����
	PurchaseUI purUI = null;// �ɹ�ҳ�����
	StatisticsUI statisticsUI = null;// ͳ��ҳ�����
	SystemManageUI systemManage = null;// ϵͳ�������
	SellUI sellUI = null;// ����ҳ�����
	CardLayout cl = null;// ������Ƭ���ֶ���
	JLabel rightLabel = null;// ����ǩ��������ʾͼƬ
	Color color = null;// ͨ����ɫ
	JLabel manageLabel = null;

	/**
	 * ���췽��
	 *  1.ʵ�������ֶ���
	 *  2.����ʵ�ַ���
	 */
	public ContentUI() {
		commondMethods = new CommondMethods();// ͨ�÷���
		leftBtn1 = new JButton("�ɹ�����");
		leftBtn2 = new JButton("���۹���");
		leftBtn3 = new JButton("������");
		leftBtn4 = new JButton("ͳ�Ʊ���");
		leftBtn5 = new JButton("ϵͳ����");

		cl = new CardLayout();
		color = new Color(165, 234, 255);
		init();// ��ʼ��
		addTopPanel();// ��Ӷ������
		addLeftPanel();// ���������
		addContentPanel();// ����������
		setVisible(true);// ���ÿɼ�
	}

	/**
	 * ��ʼ�����ڷ���
	 */
	public void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ý��̹ر�
		this.setSize(1000, 600);

		// ���ô��ھ���
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		this.setTitle("���й���ϵͳ");
		this.setLayout(null);

		setResizable(false);// ���ò��ɸı��С
	}

	/**
	 * ��Ӷ������
	 */
	public void addTopPanel() {
		// �����������
		topPanel = commondMethods.createPanel(0, 0, 1000, 100);
		nameLabel = commondMethods.createLabel("<html>���й���ϵͳ��ӭ��</html>", 180, 0, 1000, 100, "�����п�", 50);
		topPanel.add(nameLabel);
		this.add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(new Color(184,222,223));

		// �����û�����ǩ
		JLabel userLabel = commondMethods.createLabel("�û���:", 750, 70, 100, 30, "", 0);
		userLabel.setFont(new Font("����", Font.PLAIN, 24));
		topPanel.add(userLabel);

		// �����˳���ť
//		JButton exitBtn = commondMethods.createButton("�˳�", 920, 75, 60, 20, "����", 20);
//		topPanel.add(exitBtn);
		//�����˳���ǩ
		JLabel exitLabel = commondMethods.createLabel("<html><a href ='#'>�˳�</a></html>", 920, 68, 60, 30, "����", 20);
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
	 * ���������
	 */
	public void addLeftPanel() {
		leftPanel = commondMethods.createPanel(0, 100, 120, 500);
		leftPanel.setBackground(new Color(184,222,223));
		BoxLayout bl = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);// ���ĺ��Ӳ���
		leftPanel.setLayout(bl);

		// ����ť��ӵ����
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(leftBtn1);
		leftBtn1.setFont(new Font("����", Font.BOLD, 20));
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(leftBtn2);
		leftBtn2.setFont(new Font("����", Font.BOLD, 20));
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(leftBtn3);
		leftBtn3.setFont(new Font("����", Font.BOLD, 20));
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(leftBtn4);
		leftBtn4.setFont(new Font("����", Font.BOLD, 20));
		leftPanel.add(Box.createVerticalStrut(30));
		leftPanel.add(leftBtn5);
		leftBtn5.setFont(new Font("����", Font.BOLD, 20));
		leftPanel.add(Box.createVerticalStrut(50));

		// �������ӵ�����
		this.add(leftPanel, BorderLayout.WEST);
	}

	/**
	 * �����������
	 */
	public void addContentPanel() {
		centerPanel = commondMethods.createPanel(120, 100, 880, 500);

		// ���ÿ�Ƭ����
		centerPanel.setLayout(cl);

		// ����ѡ����
		centerPanel.setBackground(color);
		stoUI = new StorageUI();
		purUI = new PurchaseUI();
		statisticsUI = new StatisticsUI();
		sellUI = new SellUI();
		systemManage = new SystemManageUI();

		// ��ѡ������ӵ��������
		centerPanel.add(purUI);
		centerPanel.add(sellUI);
		centerPanel.add(stoUI);
		centerPanel.add(statisticsUI);
		centerPanel.add(systemManage);

		// ��������л�����
		CardAction ca = new CardAction();

		// ��5����ť����¼�
		leftBtn1.addActionListener(ca);
		leftBtn2.addActionListener(ca);
		leftBtn3.addActionListener(ca);
		leftBtn4.addActionListener(ca);
		leftBtn5.addActionListener(ca);

		// ������½�û���ǩ
		if (LoginUI.userText.getText().equals("admin")) {
			manageLabel = commondMethods.createLabel("����Ա", 840, 70, 100, 30, "����", 24);
			manageLabel.setFont(new Font("����", Font.PLAIN, 24));
			topPanel.add(manageLabel);
		} else {
			// ������ʾ������
			EmpDao3 empDao3 = new EmpDao3();
			Employee emp = empDao3.get(Integer.parseInt(LoginUI.userText.getText()));
			JLabel roleName = commondMethods.createLabel(emp.getEmp_name(), 840, 70, 100, 30, "����", 24);
			roleName.setFont(new Font("����", Font.PLAIN, 24));
			topPanel.add(roleName);
			// ���ð�ť���ɱ༭
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

		// �������ӵ�����
		this.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * ��ť�����������Ա�ڲ��ࣩ
	 */
	class CardAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// �����л��¼�
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
