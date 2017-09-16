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
 * ����:2017-08-17 
 * ����: 
 * 	1.�ֲֿ��ѯ��Ʒ
 * 	2.�޸���Ʒ��Ϣ
 * 	3.������Ʒ��Ż�����ģ����ѯ
 * @author LuckyJavaCi
 *
 */
public class StorageQuery extends JFrame {
	private CommondMethods commondMethods = new CommondMethods();// ͨ�÷���
	private JSplitPane splitPane; // �����ָ����
	private JScrollPane spleft; // ��߹������
	private JScrollPane spright; // �ұ߹������
	private JTree tree; // �������β˵�
	private JTable table; // �������
	private DefaultTableModel goodModel; // ������ģ��
	static int goodId = 0;
	String[] colname = { "��Ʒ���", "��Ʒ����", "��Ʒ��λ", "����С", "��Ʒ����", "��Ʒ�ۼ�", "�������", "�ֿ�����", "��������", "��Ϳ��", "��Ʒ��ע" };

	/**
	 * ���췽��
	 */
	public StorageQuery() {
		this.init();// ���ó�ʼ������
		this.addPanel();
		this.setVisible(true);// ���ô��ڿɼ�
		splitPane.setDividerLocation(0.25);
	}

	/**
	 * ��ʼ������
	 */
	public void init() {
		// ���ô��ڱ���
		this.setTitle("����ѯ");
		// ���ô��ڴ�С
		this.setSize(1000, 600);
		// ���ô���λ��
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		// ���ô��ڹر�
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
	}

	public void addPanel() {
		// �����ָ�������
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		// ����������
		JPanel leftPanel = commondMethods.createPanel(10, 10, 100, 100);
		leftPanel.setBackground(Color.white);

		// ������������
		JPanel leftMain = commondMethods.createPanel(5, 5, 230, 550);
		leftMain.setBorder(BorderFactory.createTitledBorder("��Ʒ���"));

		// ��������������
		JPanel leftTop = commondMethods.createPanel(10, 30, 210, 50);
		leftMain.add(leftTop);
		// ������Ʒ����ǩ
		JLabel typeLabel = commondMethods.createLabel("��Ʒ���:", 10, 10, 100, 30, "����", 14);
		leftTop.add(typeLabel);

		// ������Ʒ����ı���
		JTextField typeText = commondMethods.createTextField("", 80, 14, 110, 25, "����", 13, null);
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
				if (stoName.equals("���ֿ�")) {
					stoId = 1;
				} else if (stoName.equals("���Ͽ�")) {
					stoId = 2;
				} else if (stoName.equals("�ƿ�")) {
					stoId = 3;
				} else if (stoName.equals("��ʳ��")) {
					stoId = 4;
				} else if (stoName.equals("��Ʒ���")) {
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
		// �����ұ����
		JPanel rightPanel = commondMethods.createPanel(0, 0, 0, 0);
		// �����ұ������
		JPanel rightMain = commondMethods.createPanel(10, 10, 710, 540);
		rightPanel.add(rightMain);
		// �����ұ߹������
		JPanel funcPanel = commondMethods.createPanel(10, 10, 690, 80);
		rightMain.add(funcPanel);
		// ������ť
		JButton goodUpdateBtn = commondMethods.createButton("�޸�", 20, 20, 60, 30, "����",16);
		funcPanel.add(goodUpdateBtn);
		goodUpdateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int goodRow = table.getSelectedRow();
				if(goodRow!=-1) {
					goodId = (int) table.getValueAt(goodRow, 0);
					new GetInformation();
				}else {
					JOptionPane.showMessageDialog(null, "����Ҫ�޸ĵ���");
				}
			}
		});
		JButton exitBtn = commondMethods.createButton("�˳�", 570, 20, 60, 30,"����",16);
		funcPanel.add(exitBtn);
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StorageQuery.this.dispose();
			}
		});
		//������ѯ��ǩ
		JLabel queryLabel = commondMethods.createLabel("��Ʒ���/����:", 130, 20, 150, 30, "����", 16);
		funcPanel.add(queryLabel);
		//������ѯ�ı���
		JTextField queryText = commondMethods.createTextField("", 245, 25, 150, 25, "����", 15, null);
		funcPanel.add(queryText);
		//������ѯ��ť
		JButton queryBtn = commondMethods.createButton("��ѯ",410, 20, 60, 30, "����", 16);
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
						stoName = "���ֿ�";
					}else if(good.getGoods_stoId()==2) {
						stoName = "�ƿ�";
					}else if(good.getGoods_stoId()==3) {
						stoName = "���Ͽ�";
					}else if(good.getGoods_stoId()==4) {
						stoName = "��ʳ��";
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
		//����������巽��
		this.setLeft();
		this.setRight();
		spleft.setBounds(10, 80, 210, 465);
		leftMain.add(spleft);

		// �������ҷָ����
		splitPane.add(leftPanel, JSplitPane.LEFT);
		splitPane.add(rightPanel, JSplitPane.RIGHT);
		// �����ұ����
		spright = new JScrollPane(table);
		spright.setBounds(10, 100, 690, 430);
		rightMain.add(spright);
		
		// ���ָ������ӵ�����
		this.add(splitPane);
	}

	// ����������
	public void setLeft() {
		// �������ڵ�
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("��Ʒ���");
		

		// ����ӽڵ�
		node.add(new DefaultMutableTreeNode("���ֿ�"));
		node.add(new DefaultMutableTreeNode("���Ͽ�"));
		node.add(new DefaultMutableTreeNode("�ƿ�"));
		node.add(new DefaultMutableTreeNode("��ʳ��"));

		tree = new JTree(node);

		// ��tree����¼�
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// ��ȡѡ�еĽڵ�
				DefaultMutableTreeNode nd = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				String[] colname = { "��Ʒ���", "��Ʒ����", "��Ʒ��λ", "����С", "��Ʒ����", "��Ʒ�ۼ�", "�������", "�ֿ�����", "��������", "��Ϳ��",
						"��Ʒ��ע" };
				int stoId = 0;
				if (nd.toString().equals("���ֿ�")) {
					stoId = 1;
				} else if (nd.toString().equals("���Ͽ�")) {
					stoId = 2;
				} else if (nd.toString().equals("�ƿ�")) {
					stoId = 3;
				} else if (nd.toString().equals("��ʳ��")) {
					stoId = 4;
				} else if (nd.toString().equals("��Ʒ���")) {
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
		// ����������
		spleft = new JScrollPane(tree);

	}

	// �����ұ����
	public void setRight() {
		String[] colname = { "��Ʒ���", "��Ʒ����", "��Ʒ��λ", "����С", "��Ʒ����", "��Ʒ�ۼ�", "�������", "�ֿ�����", "��������", "��Ϳ��", "��Ʒ��ע" };
		StoTransDao3 stoTransDao = new StoTransDao3();
		List<Good> listGood = stoTransDao.query();
		Object[][] rows = new Object[listGood.size()][];
		for (int i = 0; i < listGood.size(); i++) {
			Good good = listGood.get(i);
			String sto = null;
			if (good.getGoods_stoId() == 1) {
				sto = "���ֿ�";
			} else if (good.getGoods_stoId() == 2) {
				sto = "���Ͽ�";
			} else if (good.getGoods_stoId() == 3) {
				sto = "�ƿ�";
			} else if (good.getGoods_stoId() == 4) {
				sto = "��ʳ��";
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
