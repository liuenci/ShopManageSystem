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
 * ����:2017-8-21 ����:��ʾ�����С����Ϳ�����Ʒ
 * 
 * @author LuckyJavaCi
 *
 */
public class StorageAlarm extends JFrame {
	private CommondMethods commondMethods = null;// ͨ�÷���
	private DefaultTableModel stoGoodModel;// �ƻ���Ʒ��ģ��
	private Color color = null;// ͨ����ɫ
	private JTable stoGoodTable;// �ƻ���Ʒ��
	private JScrollPane stoGoodPane;// �ƻ���Ʒ�������
	private String[] title = { "��Ʒ���", "��Ʒ����", "��Ʒ��λ", "����С", "�ɹ�����", "���۵���", "���ڲֿ�", "��������", "�������", "��Ϳ��", "��Ʒ��ע" };
	private JTextField numText = null;// ���������ı���
	private int goodMinNum;// ��С��������ı���
	private int goodId;// ��Ʒ���
	int selectRow;
	JButton queryBtn;

	/**
	 * ���췽��
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
	 * ��ʼ������
	 * 
	 * @param args
	 */
	public void init() {
		this.setTitle("��澯��");
		this.setSize(800, 500);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		// ���ô��ڲ��ɸı�
		this.setResizable(false);
	}

	/**
	 * ������
	 * 
	 * @param args
	 */
	public void addPanel() {
		// ���������
		JPanel mainPanel = commondMethods.createPanel(5, 5, 785, 460);
		this.add(mainPanel);
		// �������Ʊ�ǩ
		JLabel nameLabel = commondMethods.createLabel("��澯��", 340, 10, 200, 30, "����", 30);
		mainPanel.add(nameLabel);
		// �����������
		JPanel funcPanel = commondMethods.createPanel(5, 50, 775, 60);
		mainPanel.add(funcPanel);
		// ������Ʒ��Ż����Ʊ�ǩ
		JLabel goodLabel = commondMethods.createLabel("����Ʒ���/����:", 10, 10, 150, 20, "����", 14);
		funcPanel.add(goodLabel);
		// ������Ż������Ʋ�ѯ�ı���
		JTextField goodText = commondMethods.createTextField("", 130, 10, 100, 20, "����", 14, color);
		funcPanel.add(goodText);
		// ������ѯ��ť
		queryBtn = commondMethods.createButton("��ѯ", 230, 10, 60, 20, "����", 14);
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
		// �����ݿ��л�ȡ����
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

		// ������ģ��
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
		// �����������
		JPanel statePanel = commondMethods.createPanel(5, 310, 775, 100);
		mainPanel.add(statePanel);
		// ����������ǩ
		JLabel stateLabel = commondMethods.createLabel("<html>������Ʒ��治��<br/>��ɹ�Ա����ɹ�<br/>������Ա��������</html>", 300, 0, 500,
				90, "����", 24);
		stateLabel.setForeground(Color.red);
		statePanel.add(stateLabel);
		// ����ȷ���ͺ��԰�ť
		JButton confirmBtn = commondMethods.createButton("���ѵ�֪", 500, 420, 100, 30, "����", 20);
		mainPanel.add(confirmBtn);
		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StorageAlarm.this.dispose();
			}
		});
		// �����޸İ�ť
		JButton updateNumBtn = commondMethods.createButton("�޸���Ϳ��", 500, 10, 100, 30, "����", 14);
		funcPanel.add(updateNumBtn);
		updateNumBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectRow = stoGoodTable.getSelectedRow();
				if (selectRow > -1) {
					new UpdateNum();
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ��һ��");
				}

			}
		});

	}

	/**
	 * ����:��ø��ĺ����Ʒ����
	 * 
	 * @author LuckyJavaCi
	 */
	class UpdateNum extends JFrame {
		JButton updateConfirm;// ȷ����ť
		JButton updateCancel;// ȡ����ť

		// ���췽��
		public UpdateNum() {
			this.init();
			this.addPanel();
			this.addListener();
			this.setVisible(true);
		}

		// ��ʼ��
		public void init() {
			this.setTitle("�޸�����");
			this.setSize(400, 185);
			this.setLayout(null);
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
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		// ������
		public void addPanel() {
			// ����������
			JPanel numPanel = commondMethods.createPanel(10, 10, 365, 80);
			numPanel.setBorder(BorderFactory.createEtchedBorder());
			this.add(numPanel);

			// ����޸ı�ǩ
			JLabel updateLabel = commondMethods.createLabel("�޸�����", 40, 30, 100, 30, "����", 20);
			numPanel.add(updateLabel);

			// ��������ı���
			numText = commondMethods.createTextField("", 160, 30, 100, 30, "����", 14, color);
			numPanel.add(numText);
			// ��Ӱ�ť���
			JPanel btnPanel = commondMethods.createPanel(10, 100, 365, 40);
			// ��Ӱ�ť
			updateConfirm = commondMethods.createButton("ȷ��", 10, 10, 100, 20, "����", 14);
			updateCancel = commondMethods.createButton("ȡ��", 200, 10, 100, 20, "����", 14);
			btnPanel.add(updateConfirm);
			btnPanel.add(updateCancel);
			this.add(btnPanel);
		}

		// ʱ���������
		public void addListener() {
			updateConfirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						// �����Ʒ����
						goodMinNum = Integer.parseInt(numText.getText());
						goodId = (int) stoGoodTable.getValueAt(selectRow, 0);
						// �������ݿ�����
						StoTransDao3 stoTransDao3 = new StoTransDao3();
						stoTransDao3.updateMinNum(goodId, goodMinNum);
						JOptionPane.showMessageDialog(null, "�������³ɹ�");
						UpdateNum.this.dispose();
						queryBtn.doClick();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ������");
					}

				}
			});
			// ȡ������
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
