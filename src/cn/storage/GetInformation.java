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

public // �ڲ��࣬��õ������Ʒ��Ϣ
class GetInformation extends JFrame {
	private CommondMethods commondMethods = new CommondMethods();// ͨ�÷���
	JButton inforConfirmBtn;// ȷ����ť
	JButton inforCancelBtn;// ȡ����ť
	String nameText;// ��Ʒ����
	String unitsText;// ������λ
	String sizeText;// ����С
	Double purPriceText;// ��Ʒ����
	Double sellPriceText;// ��Ʒ�ۼ�
	int goodNumber;// ��ǰ�������
	int stoId;// ���ڲֿ�
	int keepDays;// ������
	int minNumber;// ��С���
	String goodMark;// ��Ʒ��ע
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

	// ���췽��
	public GetInformation() {
		this.init();
		addPanel();
		addMouseListener();
		setVisible(true);
	}

	// ��ʼ��
	public void init() {
		setTitle("��Ʒ��Ϣ");
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
		// ���������
		JPanel mainGoodPanel = new JPanel();
		mainGoodPanel.setLayout(null);
		mainGoodPanel.setBounds(5, 50, 520, 250);
		mainGoodPanel.setBorder(BorderFactory.createEtchedBorder());

		// �����������
		JPanel goodPanel1 = new JPanel();
		goodPanel1.setBounds(5, 5, 520, 40);
		goodPanel1.setBorder(BorderFactory.createEtchedBorder());
		this.add(goodPanel1);

		// �������Ʊ�ǩ
		JLabel informationLabel = new JLabel("��Ʒ��Ϣ");
		informationLabel.setFont(new Font("����", Font.PLAIN, 26));
		goodPanel1.add(informationLabel);
		// ������ǩ
		JLabel idLabel = new JLabel("��Ʒ���:");
		idLabel.setBounds(15, 15, 80, 20);
		idLabel.setFont(new Font("����", Font.PLAIN, 16));
		mainGoodPanel.add(idLabel);

		JLabel nameLabel = new JLabel("��Ʒ����:");
		nameLabel.setBounds(280, 15, 80, 20);
		nameLabel.setFont(new Font("����", Font.PLAIN, 14));
		mainGoodPanel.add(nameLabel);

		JLabel unitsLabel = new JLabel("������λ:");
		unitsLabel.setBounds(15, 55, 80, 20);
		unitsLabel.setFont(new Font("����", Font.PLAIN, 16));
		mainGoodPanel.add(unitsLabel);

		JLabel sizeLabel = new JLabel("����ͺ�:");
		sizeLabel.setBounds(280, 55, 80, 20);
		sizeLabel.setFont(new Font("����", Font.PLAIN, 16));
		mainGoodPanel.add(sizeLabel);

		JLabel purPriceLabel = new JLabel("��Ʒ����:");
		purPriceLabel.setBounds(15, 95, 80, 20);
		purPriceLabel.setFont(new Font("����", Font.PLAIN, 16));
		mainGoodPanel.add(purPriceLabel);

		JLabel sellPriceLabel = new JLabel("��Ʒ�ۼ�:");
		sellPriceLabel.setBounds(280, 95, 80, 20);
		sellPriceLabel.setFont(new Font("����", Font.PLAIN, 16));
		mainGoodPanel.add(sellPriceLabel);

		JLabel numberLabel = new JLabel("��ǰ���:");
		numberLabel.setBounds(15, 135, 80, 20);
		numberLabel.setFont(new Font("����", Font.PLAIN, 16));
		mainGoodPanel.add(numberLabel);

		JLabel stoIdLabel = new JLabel("���ڲֿ�:");
		stoIdLabel.setBounds(280, 135, 80, 20);
		stoIdLabel.setFont(new Font("����", Font.PLAIN, 16));
		mainGoodPanel.add(stoIdLabel);

		JLabel keepDaysLabel = new JLabel("������:");
		keepDaysLabel.setBounds(15, 175, 80, 20);
		keepDaysLabel.setFont(new Font("����", Font.PLAIN, 16));
		mainGoodPanel.add(keepDaysLabel);

		JLabel minNumberLabel = new JLabel("��С���:");
		minNumberLabel.setBounds(280, 175, 80, 20);
		minNumberLabel.setFont(new Font("����", Font.PLAIN, 16));
		mainGoodPanel.add(minNumberLabel);

		JLabel markLabel = new JLabel("��Ʒ��ע:");
		markLabel.setBounds(15, 215, 80, 20);
		markLabel.setFont(new Font("����", Font.PLAIN, 16));
		mainGoodPanel.add(markLabel);

		// ��ʾ��Ϣ��ǩ
		// �����ݿ��ȡ��Ʒ��Ϣ
		StoTransDao3 stoTransDao = new StoTransDao3();
		Good good = null;
		try {
			good = stoTransDao.get(good_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JTextField goodIdText = commondMethods.createTextField("" + good_id, 90, 15, 100, 20, "����", 16, null);
		mainGoodPanel.add(goodIdText);
		goodIdText.setEditable(false);
		goodIdText.setBackground(Color.LIGHT_GRAY);

		nameText = good.getGoods_name();// ��Ʒ����
		name = commondMethods.createTextField(nameText, 360, 15, 100, 20, "����", 16, null);
		mainGoodPanel.add(name);

		unitsText = good.getGoods_units();// ������λ
		units = commondMethods.createJComboBox(90, 55, 100, 20, "����", 12);
		units.addItem("��");
		units.addItem("ƿ");
		units.addItem("��");
		mainGoodPanel.add(units);

		sizeText = good.getGoods_size();// ����С
		size = commondMethods.createTextField(sizeText, 360, 55, 100, 20, "����", 16, null);
		mainGoodPanel.add(size);

		purPriceText = good.getGoods_purPrise();// ��Ʒ����
		purPrise = commondMethods.createTextField("" + purPriceText, 90, 95, 100, 20, "����", 16, null);
		mainGoodPanel.add(purPrise);
		purPrise.setEditable(false);
		purPrise.setBackground(Color.LIGHT_GRAY);

		sellPriceText = good.getGoods_sellPrice();// ��Ʒ�ۼ�
		sellPrise = commondMethods.createTextField("" + sellPriceText, 360, 95, 100, 20, "����", 16, null);
		mainGoodPanel.add(sellPrise);

		goodNumber = good.getGoods_number();// ��ǰ�������

		JTextField number = commondMethods.createTextField("" + goodNumber, 90, 135, 100, 20, "����", 16, null);
		mainGoodPanel.add(number);
		number.setEditable(false);
		number.setBackground(Color.LIGHT_GRAY);

		stoBox = commondMethods.createJComboBox(360, 135, 100, 20, "����", 12);
		stoBox.addItem("���ֿ�");
		stoBox.addItem("�ƿ�");
		stoBox.addItem("���Ͽ�");
		stoBox.addItem("��ʳ��");
		

		mainGoodPanel.add(stoBox);

		keepDays = good.getGoods_keepDays();// ������

		keep = commondMethods.createTextField("" + keepDays, 90, 175, 100, 20, "����", 16, null);
		mainGoodPanel.add(keep);

		minNumber = good.getGoods_minNumber();// ��С���

		min = commondMethods.createTextField("" + minNumber, 360, 175, 100, 20, "����", 16, null);
		mainGoodPanel.add(min);

		goodMark = good.getGoods_mark();// ��Ʒ��ע

		mark = commondMethods.createTextField(goodMark, 90, 215, 370, 20, "����", 16, null);
		mainGoodPanel.add(mark);

		this.add(mainGoodPanel);
		// ��ӵײ���ť���
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		btnPanel.setBounds(5, 305, 520, 50);
		btnPanel.setBorder(BorderFactory.createEtchedBorder());
		// ��Ӱ�ť
		inforConfirmBtn = new JButton("ȷ��");
		inforConfirmBtn.setBounds(130, 10, 100, 30);
		inforConfirmBtn.setBackground(new Color(175, 208, 244));
		inforConfirmBtn.setFont(new Font("����", Font.PLAIN, 14));
		btnPanel.add(inforConfirmBtn);
		inforCancelBtn = new JButton("ȡ��");
		inforCancelBtn.setBounds(300, 10, 100, 30);
		inforCancelBtn.setBackground(new Color(175, 208, 244));
		inforCancelBtn.setFont(new Font("����", Font.PLAIN, 14));
		btnPanel.add(inforCancelBtn);
		// ���ȷ�����˳���ť
		this.add(btnPanel);

	}

	public void addMouseListener() {
		inforConfirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �����ݿ��и�������
				GoodDao3 goodDao3 = new GoodDao3();
				if (stoBox.getSelectedItem().toString().equals("���ֿ�")) {
					stoId = 1;
				} else if (stoBox.getSelectedItem().toString().equals("�ƿ�")) {
					stoId = 2;
				} else if (stoBox.getSelectedItem().toString().equals("���Ͽ�")) {
					stoId = 3;
				} else if (stoBox.getSelectedItem().toString().equals("��ʳ��")) {
					stoId = 4;
				}
				goodDao3.updateGood(name.getText(), units.getSelectedItem().toString(), size.getText(),
						Double.parseDouble(sellPrise.getText()), stoId, Integer.parseInt(keep.getText()),
						Integer.parseInt(min.getText()), mark.getText(), StorageQuery.goodId);
				JOptionPane.showMessageDialog(null, "��Ʒ�޸ĳɹ�");
				GetInformation.this.dispose();
			}

		});
		inforCancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "��Ʒ�޸�ʧ��");
				GetInformation.this.dispose();
			}
		});
	}

	public static void main(String[] args) {
		new GetInformation();
	}
}