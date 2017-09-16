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
	// ����������
	JPanel leftPanel = new JPanel();
	// �����ұ����
	JPanel rightPanel = new JPanel();
	// ������߱�����
	private JTable leftTable;
	// ������߱�����
	private JTable rightTable;
	// ������ģ��
	private DefaultTableModel leftModel;
	private DefaultTableModel rightModel;
	private JSplitPane splitPane; // �����ָ�������
	private JScrollPane leftSc; // ��߹������
	private JScrollPane rightSc; // �ұ߹������
	// ������߱�ͷ
	String[] title1 = { "��Ʒ���", "��Ʒ����", "��λ", "���", "�ο�����", "�����", "�ο��ۼ�" };
	// �����ұ߱�ͷ
	String[] title2 = { "��Ʒ���", "��Ʒ����", "��λ", "����", "����", "�ܽ��" };
	// ����ı���
	JTextField rearchText;
	// ��Ӳ�ѯ��ť
	JButton queryBtn;
	JPanel leftMainPanel = null;
	JPanel rightMainPanel = null;
	Color stepColor = new Color(237, 242, 248);
	// ����ĸ���ť
	JButton updateBtn = new JButton("�޸�");
	JButton delBtn = new JButton("ɾ��");
	JButton confirmBtn = new JButton("ȷ��");
	JButton cancelBtn = new JButton("ȡ��");
	int good_id;// ����õ���ƷID
	JTextField numText;// ������Ʒ�����ı���
	int good_purPlanNum;// ���ĵĲɹ�����
	double good_purPlanPrice;// ��Ʒ�Ĳɹ�����
	// ��Ʒ��
	static JTable goodTable;
	// ��Ʒ��ģ��
	static DefaultTableModel goodModel;
	CommondMethods commondMethods = new CommondMethods();

	// ���췽��
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

	// ��ʼ��
	public void init() {
		this.setTitle("�����Ʒ");
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

	// ������
	public void addLeftPanel() {
		// �����ָ�������
		splitPane = new JSplitPane();

		splitPane.setDividerLocation(500);
		leftPanel.setLayout(null);

		// �����������
		leftMainPanel = new JPanel();
		leftMainPanel.setLayout(null);
		leftMainPanel.setBounds(5, 5, 495, 550);
		leftMainPanel.setBackground(new Color(235, 235, 235));
		leftMainPanel.setBorder(BorderFactory.createEtchedBorder());
		// ��Ӳ������
		JPanel stepPanel = new JPanel();
		stepPanel.setLayout(null);
		stepPanel.setBounds(5, 5, 485, 90);
		stepPanel.setBackground(stepColor);
		stepPanel.setBorder(BorderFactory.createEtchedBorder());
		// ������ֱ�ǩ
		JLabel stepLabel = new JLabel("<html>��ѯ��Ʒ�б�<br/>����һ:&nbsp;&nbsp;��������Ʒ��Ż����Ʋ�ѯ��Ʒ����ѯ����Ʒ����ӵ��ұ���ѡ��Ʒ<br/>"
				+ "�����:&nbsp;&nbsp;������µ���Ʒ��Ŀ������ϵ�ɹ�Ա���вɹ���<br/><br/>��������Ʒ��Ż����Ʋ�ѯ��Ʒ:</html>");
		stepLabel.setBounds(5, 0, 480, 85);
		stepLabel.setFont(new Font("����", Font.PLAIN, 12));
		// ��������ı���
		rearchText = new JTextField();
		rearchText.setBounds(185, 65, 100, 20);
		stepPanel.add(rearchText);
		// ��Ӳ�ѯ��ť
		queryBtn = new JButton("Ѱ����Ʒ");
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
			// ������ת��Ϊ����
			Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(), good.getGoods_size(),
					good.getGoods_sellPrice(), good.getGoods_number(), good.getGoods_sellPrice() };
			// ����ά���鸳ֵ
			rows[i] = obj;
		}
		leftModel = new DefaultTableModel(rows, title1);
		leftTable = new JTable(leftModel);
		leftTable = new MTable(leftModel);
		// ���˫���¼�
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
		rightPanel.setLayout(null);// �ұ����
		rightMainPanel = new JPanel();// �ұ������
		rightMainPanel.setLayout(null);// �ұ����������Ϊ��
		rightMainPanel.setBounds(5, 5, 465, 490);
		rightMainPanel.setBackground(new Color(235, 235, 235));
		rightMainPanel.setBorder(BorderFactory.createEtchedBorder());

		// �������
		JPanel namePanel = new JPanel();
		namePanel.setBounds(5, 5, 453, 50);
		namePanel.setBackground(stepColor);
		namePanel.setBorder(BorderFactory.createEtchedBorder());
		// ���Ʊ�ǩ
		JLabel nameLabel = new JLabel("��ѡ��Ʒ");
		nameLabel.setBounds(5, 13, 100, 20);
		nameLabel.setFont(new Font("����", Font.PLAIN, 24));
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
		
	    

		// ��ӵײ����
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(5, 500, 465, 55);
		bottomPanel.setBackground(stepColor);
		bottomPanel.setBorder(BorderFactory.createEtchedBorder());

		// ����޸İ�ť
		updateBtn.setBounds(10, 10, 60, 30);
		bottomPanel.add(updateBtn);
		// ���ɾ����ť
		delBtn.setBounds(130, 10, 60, 30);
		bottomPanel.add(delBtn);
		// ���ȷ����ť
		confirmBtn.setBounds(250, 10, 60, 30);
		bottomPanel.add(confirmBtn);
		// ���ȡ����ť
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
					// �������
					String[] title = { "��Ʒ���", "��Ʒ����", "��λ", "���", "����", "�����" };
					// �����ݿ��ȡ��Ʒ��Ϣ
					StoTransDao3 goodDao = new StoTransDao3();
					List<Good> listGood = goodDao.query(searchText);
					Object[][] rows = new Object[listGood.size()][];
					for (int i = 0; i < rows.length; i++) {
						Good good = listGood.get(i);
						// ������ת��Ϊ����
						Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
								good.getGoods_size(), good.getGoods_sellPrice(), good.getGoods_number() };
						// ����ά���鸳ֵ
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
					JOptionPane.showMessageDialog(PlanAddOldGoods.this, "��������ȷ����Ʒ��Ż�����", "������ʾ",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		confirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �ұ߱���е�����
				int rightRowsCount = rightModel.getRowCount();

				for (int i = 0; i < rightRowsCount; i++) {
					// ��ȡ��Ʒ�ı��
					int gId = (int) rightTable.getValueAt(i, 0);
					// ��ȡ��Ʒ������
					int gNum = (int) rightTable.getValueAt(i, 4);
					// �����ݿ�������
					StoTransDao3 stoTransDao = new StoTransDao3();
					// �������󱣴�����
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
					JOptionPane.showMessageDialog(null, "�뵥��ѡ���ұ߱��һ��","������ʾ",JOptionPane.WARNING_MESSAGE);
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

	// �ڲ��࣬��õ������Ʒ��Ϣ
	class GetInformation extends JFrame {
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
		JTextField purNumText;// �ɹ������ı���

		// ���췽��
		public GetInformation() {
			this.init();
			addPanel();
			addMouseListener();
			setVisible(true);
		}

		// ��ʼ��
		public void init() {
			setTitle("��Ʒ��Ϣ���ɹ��ƻ���");
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
			// ���������
			JPanel mainGoodPanel = new JPanel();
			mainGoodPanel.setLayout(null);
			mainGoodPanel.setBackground(stepColor);
			mainGoodPanel.setBounds(5, 50, 520, 250);
			mainGoodPanel.setBorder(BorderFactory.createEtchedBorder());

			// �����������
			JPanel goodPanel1 = new JPanel();
			goodPanel1.setBackground(stepColor);
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
			JLabel idInfor = new JLabel("" + good_id);
			idInfor.setBounds(90, 15, 100, 20);
			idInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(idInfor);

			nameText = good.getGoods_name();// ��Ʒ����
			JLabel nameInfor = new JLabel(nameText);
			nameInfor.setBounds(360, 15, 100, 20);
			nameInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(nameInfor);

			unitsText = good.getGoods_units();// ������λ
			JLabel unitsInfor = new JLabel(unitsText);
			unitsInfor.setBounds(90, 55, 100, 20);
			unitsInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(unitsInfor);

			sizeText = good.getGoods_size();// ����С
			JLabel sizeInfor = new JLabel(sizeText);
			sizeInfor.setBounds(360, 55, 100, 20);
			sizeInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(sizeInfor);

			purPriceText = good.getGoods_purPrise();// ��Ʒ����
			JLabel purPriseInfor = new JLabel("" + purPriceText);
			purPriseInfor.setBounds(90, 95, 100, 20);
			nameInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(purPriseInfor);

			sellPriceText = good.getGoods_sellPrice();// ��Ʒ�ۼ�
			JLabel sellPriceInfor = new JLabel("" + sellPriceText);
			sellPriceInfor.setBounds(360, 95, 100, 20);
			sellPriceInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(sellPriceInfor);

			goodNumber = good.getGoods_number();// ��ǰ�������
			JLabel numberInfor = new JLabel("" + goodNumber);
			numberInfor.setBounds(90, 135, 100, 20);
			numberInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(numberInfor);

			stoId = good.getGoods_stoId();// ���ڲֿ�
			JLabel stoIdInfor = new JLabel("" + stoId);
			stoIdInfor.setBounds(360, 135, 100, 20);
			stoIdInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(stoIdInfor);

			keepDays = good.getGoods_keepDays();// ������
			JLabel keeyDaysInfor = new JLabel("" + keepDays + "��");
			keeyDaysInfor.setBounds(90, 175, 100, 20);
			stoIdInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(keeyDaysInfor);

			minNumber = good.getGoods_minNumber();// ��С���
			JLabel minNumberInfor = new JLabel("" + minNumber);
			minNumberInfor.setBounds(360, 175, 100, 20);
			minNumberInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(minNumberInfor);

			goodMark = good.getGoods_mark();// ��Ʒ��ע
			JLabel markInfor = new JLabel(goodMark);
			markInfor.setBounds(90, 210, 300, 30);
			markInfor.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(markInfor);

			JLabel purNumber = new JLabel("�ɹ�����:");
			purNumber.setBounds(280, 215, 100, 20);
			purNumber.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(purNumber);

			purNumText = new JTextField();
			purNumText.setBounds(360, 215, 100, 20);
			purNumText.setFont(new Font("����", Font.PLAIN, 16));
			mainGoodPanel.add(purNumText);

			this.add(mainGoodPanel);
			// ��ӵײ���ť���
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(null);
			btnPanel.setBackground(stepColor);
			btnPanel.setBounds(5, 305, 520, 50);
			btnPanel.setBorder(BorderFactory.createEtchedBorder());
			// ��Ӱ�ť
			inforConfirmBtn = new JButton("ȷ��");
			inforConfirmBtn.setBounds(130, 15, 100, 20);
			inforConfirmBtn.setBackground(new Color(175, 208, 244));
			inforConfirmBtn.setFont(new Font("����", Font.PLAIN, 12));
			btnPanel.add(inforConfirmBtn);
			inforCancelBtn = new JButton("ȡ��");
			inforCancelBtn.setBounds(300, 15, 100, 20);
			inforCancelBtn.setBackground(new Color(175, 208, 244));
			inforCancelBtn.setFont(new Font("����", Font.PLAIN, 12));
			btnPanel.add(inforCancelBtn);
			// ���ȷ�����˳���ť
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
							JOptionPane.showMessageDialog(null, "��������Ϊ��");
						} else if (Integer.parseInt(purNumText.getText()) < 0
								|| Integer.parseInt(purNumText.getText()) == 0) {
							JOptionPane.showMessageDialog(null, "��������Ϊ��������0");
						}
					} catch (NumberFormatException numberFormatException) {
						JOptionPane.showMessageDialog(null, "��������ȷ������");
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
		// ������
		JButton updateConfirm = new JButton("ȷ��");
		JButton updateCancel = new JButton("ȡ��");

		// ���췽��
		public updateNum() {
			this.init();
			addPanel();
			addListener();
			setVisible(true);
		}

		// ��ʼ��
		public void init() {
			setTitle("�޸�����");
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

		// ������
		public void addPanel() {
			// ������
			JPanel numPanel = new JPanel();
			numPanel.setLayout(null);
			numPanel.setBackground(stepColor);
			numPanel.setBounds(10, 10, 365, 80);
			numPanel.setBorder(BorderFactory.createEtchedBorder());
			this.add(numPanel);

			// ����޸ı�ǩ
			JLabel updateLabel = new JLabel("�޸�����:");
			updateLabel.setFont(new Font("����", Font.PLAIN, 20));
			updateLabel.setBounds(40, 30, 100, 30);
			numPanel.add(updateLabel);

			// ��������ı���
			numText = new JTextField();
			numText.setBounds(160, 30, 100, 30);
			numPanel.add(numText);

			// ��Ӱ�ť���
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(null);
			btnPanel.setBackground(stepColor);
			btnPanel.setBounds(10, 100, 365, 40);
			btnPanel.setBorder(BorderFactory.createEtchedBorder());
			// ��Ӱ�ť
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
						JOptionPane.showMessageDialog(null, "��������ȷ������");
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
