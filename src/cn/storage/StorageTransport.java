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
	// �����,��Ÿ��ֱ�ǩ����ť
	JPanel mainPanel = null;
	// �����ײ����
	JPanel bottomPanel = null;
	// ���Ʊ�ǩ
	JLabel nameLabel = null;
	// �����ֿ��ǩ
	JLabel outLabel = null;
	// �����ֿ��ǩ
	JLabel inputLabel = null;
	// ���ڱ�ǩ
	JLabel dateLabel = null;

	// �����ֿ���
	JTextField outText = null;
	// �����ֿ���
	JTextField inputText = null;

	int outSto_id = 0;
	int intputSto_id = 0;

	// �������ڱ�ǩ
	JLabel dateText = null;
	JButton goodFindBtn = null;

	String stoName = null;
	// ���Ұ�ť
	JButton queryBtn1 = null;
	JButton queryBtn2 = null;

	// �����������
	JScrollPane goodSc = null;
	// ������ģ��
	DefaultTableModel goodModel = null;
	// ������
	JTable goodTable;
	int RowId = 0;

	int good_id = 0;
	JComboBox stoOutBox = null;
	JComboBox stoInputBox = null;

	Object[][] goodRows = new Object[20][];

	// ������
	JLabel empLabel;
	// �����������б��
	JComboBox empList;

	// ������ϰ�ť
	JButton yesBtn;
	JButton cancelBtn;

	JPanel choicePanel;// ����ѡ�����
	protected Object stoInputName;

	// ���췽��
	public StorageTransport() {
		init();
		addPanel();
		addTable();
		// addBottomPanel();
		addRegisterListener();
		setVisible(true);
	}

	// ��ʼ��
	public void init() {
		setTitle("������");
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

	// ������
	public void addPanel() {
		// ���������
		mainPanel = createPanel(5, 5, 775, 450);

		// �������Ʊ�ǩ
		nameLabel = createLabel("������", 320, 10, 200, 70, "����", 36);
		mainPanel.add(nameLabel);

		// ����ѡ�����
		choicePanel = createPanel(5, 70, 765, 50);
		// ���������ֿ��ǩ
		outLabel = createLabel("�����ֿ�:", 30, 10, 160, 30, "����", 16);
		choicePanel.add(outLabel);
		mainPanel.add(choicePanel);
		// �����ֿ������б��
		stoOutBox = createJComboBox(110, 10, 100, 30, "����", 14);
		StorageDao3 storageDao = new StorageDao3();
		List<Storage> stoList = storageDao.query();
		for (int i = 0; i < stoList.size(); i++) {
			stoOutBox.addItem(stoList.get(i).getName());
		}
		choicePanel.add(stoOutBox);

		inputLabel = createLabel("����ֿ�:", 220, 10, 160, 30, "����", 16);
		choicePanel.add(inputLabel);
		mainPanel.add(choicePanel);
		stoInputBox = createJComboBox(300, 10, 100, 30, "����", 14);
		for (int i = 0; i < stoList.size(); i++) {
			stoInputBox.addItem(stoList.get(i).getName());
		}
		choicePanel.add(stoInputBox);

		dateLabel = createLabel("��������:", 520, 10, 160, 30, "����", 16);
		choicePanel.add(dateLabel);
		mainPanel.add(choicePanel);

		DateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
		String transDate = df.format(new java.util.Date());

		dateText = createLabel("" + transDate, 600, 10, 160, 30, "����", 16);
		dateText.setForeground(Color.red);
		choicePanel.add(dateText);
		mainPanel.add(choicePanel);

		// ��ѯ��Ʒ��ť
		goodFindBtn = createButton("��ѯ��Ʒ", 420, 10, 80, 30, "����", 20);
		choicePanel.add(goodFindBtn);

		// �����ײ����
		JPanel bottomPanel = createPanel(5, 390, 765, 50);
		mainPanel.add(bottomPanel);

		// �����ײ���ť
		yesBtn = createButton("�������", 430, 10, 100, 30, "����", 16);
		bottomPanel.add(yesBtn);
		cancelBtn = createButton("ȡ������", 570, 10, 100, 30, "����", 16);
		bottomPanel.add(cancelBtn);

		this.add(mainPanel);
	}

	// ��ӱ�
	public void addTable() {
		// ������ͷ
		String[] title = { "��Ʒ���", "��Ʒ����", "��Ʒ��λ", "��Ʒ���", "��Ʒ����", "���ڲֿ�" };
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

					if (stoInputName.equals("���ֿ�")) {
						intputSto_id = 1;
					} else if (stoInputName.equals("���Ͽ�")) {
						intputSto_id = 2;
					} else if (stoInputName.equals("�ƿ�")) {
						intputSto_id = 3;
					} else if (stoInputName.equals("��ʳ��")) {
						intputSto_id = 4;
					}
					
					int res = JOptionPane.showConfirmDialog(StorageTransport.this, "�Ƿ����", "��ʾ��Ϣ", JOptionPane.YES_NO_OPTION);
					StoTransDao3 goodsTrans = new StoTransDao3();
					if (res == JOptionPane.YES_OPTION) {
						
						int row = goodTable.getSelectedRow();
						int column = goodTable.getAutoResizeMode();
						goodTable.getValueAt(row, column);
						good_id = (int) goodModel.getValueAt(row, 0);
						Good good = new Good(good_id, intputSto_id);
						int goodRow = goodsTrans.updateGood(good);
						goodFindBtn.doClick();
						JOptionPane.showMessageDialog(null, "�����ɹ�");
					}else {
						JOptionPane.showMessageDialog(null, "ȡ���ɹ�");
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

	// ����ť��ӵ���¼�
	public void addRegisterListener() {
		goodFindBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String stoOutName = stoOutBox.getSelectedItem().toString();
				 stoInputName = stoInputBox.getSelectedItem().toString();
				if (stoOutName.equals("���ֿ�")) {
					outSto_id = 1;
				} else if (stoOutName.equals("���Ͽ�")) {
					outSto_id = 2;
				} else if (stoOutName.equals("�ƿ�")) {
					outSto_id = 3;
				} else if (stoOutName.equals("��ʳ��")) {
					outSto_id = 4;
				}
				// �������
				String[] str = { "��Ʒ���", "��Ʒ����", "��Ʒ��λ", "��Ʒ���", "��Ʒ����", "���ڲֿ�"};
				// ��ȡ���ݿ�����
				StoTransDao3 goodDao = new StoTransDao3();
				List<Good> listSto = goodDao.queryByStoId(outSto_id);
				Object[][] rows = new Object[listSto.size()][];
				for (int i = 0; i < rows.length; i++) {
					// ��ȡ��Ӧ�̼��϶���
					Good good = listSto.get(i);
					// ������תΪ����洢
					Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
							good.getGoods_size(), good.getGoods_number(), stoOutName };
					// ����ά���鸳ֵ
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

	// ������巽��
	public JPanel createPanel(int x, int y, int width, int height) {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		Color color = new Color(237, 242, 248);
		jPanel.setBackground(color);
		jPanel.setBounds(x, y, width, height);
		jPanel.setBorder(BorderFactory.createEtchedBorder());
		return jPanel;
	}

	// ������ǩ����
	public JLabel createLabel(String name, int x, int y, int width, int height, String fontName, int fontSize) {
		JLabel jLabel = new JLabel(name);
		jLabel.setBounds(x, y, width, height);
		jLabel.setFont(new Font(fontName, Font.PLAIN, fontSize));
		return jLabel;
	}

	// ������ť����
	public JButton createButton(String name, int x, int y, int width, int height, String fontName, int fontSize) {
		JButton jButton = new JButton(name);
		jButton.setBounds(x, y, width, height);
		Color color = new Color(129, 194, 214);
		jButton.setBackground(color);
		return jButton;
	}

	// �����ı��򷽷�
	public JTextField createTextField(String text, int x, int y, int width, int height, String fontName, int fontSize,
			Color color) {
		JTextField jTextField = new JTextField();
		jTextField.setBounds(x, y, width, height);
		jTextField.setText(text);
		jTextField.setFont(new Font(text, Font.PLAIN, fontSize));
		jTextField.setBackground(color);
		return jTextField;
	}

	// ���������б�򷽷�
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
