package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.GoodPurDetailDao1;
import cn.dao.purchase.StoTransDao1;
import cn.method.CommondMethods;
import cn.model.common.Good;
import cn.model.purchase.GoodPurDetail;
import cn.view.purchase.MTable;
/**
 * 1.����2017-8-05
 * 2.��Ҫ����
 *  a.���չʾ������Ʒ��Ϣ
 *  b.�����ɸ�������ģ����ѯ��Ϣ��Ҳ��ȫ����ѯ
 *  c.˫����Ʒ������Ʒ��Ϣ���������
 *  d.ѡ�����Ʒ����ʾ���ұ����
 *  e.�ɶ��ұ�ѡ�е���Ʒ�����޸ģ�ɾ��
 * @author �ܳ���
 *
 */
public class PurchaseOldAdd extends JFrame {
	CommondMethods commondMethods = new CommondMethods();//ͨ�÷���
	JPanel jp;// ���������
	JSplitPane splitleftright;// �������ҷָ����
	private JTable tableleft;// ��߱�����
	private DefaultTableModel tmleft;// ��ߴ�����ģ��
	private JTable tableright;// �ұ߱�����
	private DefaultTableModel tmright;// �ұߴ�����ģ��
	DefaultTableModel tm;

	private JScrollPane spright;// �����ұ߹������
	private JScrollPane spleft;// ������߹������

	private JPanel pleft; // ������
	private JPanel pright; // �ұ����

	JTextField tfgoodName;// ������Ʒ������ť
	JButton btnSel;// ��Ʒ���Ʋ�ѯ��ť
	JButton btnAll;// ȫ����ѯ��ť
	JButton btnSure;// ȷ����ť
	JButton btnDel;// ɾ����ť
	JButton btnUpdate;// �޸İ�ť
	JButton btnCan;// ȡ����ť
	CommondMethods comMenth = null;

	public PurchaseOldAdd(JTable tables, DefaultTableModel tms) {
		this.tm = tms;
		this.tableright = tables;
		comMenth = new CommondMethods();
		this.inist();
		this.setSplit();
		this.btn();
		// ���ڿ��ӻ�
		this.setVisible(true);
		splitleftright.setDividerLocation(0.5);
		splitleftright.setEnabled(false);
	}

	/**
	 * ��ʼ��ҳ��
	 */
	public void inist() {
		// ���ô��ڴ�С
		this.setSize(1000, 530);
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
		// �����޸Ĵ��ڵĴ�С
		this.setResizable(false);
		this.setTitle("����Ʒ���");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * ���÷ָ����
	 */
	public void setSplit() {
		// �����ָ�������
		splitleftright = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		// ����������巽��
		this.setLeftPanel();
		this.setRightPanel();
		splitleftright.add(pleft, JSplitPane.LEFT);
		splitleftright.add(pright, JSplitPane.RIGHT);
		// �ѷָ������ӵ�����
		this.add(splitleftright);
	}

	/**
	 * ����������
	 */
	public void setLeftPanel() {
		// ���������
		pleft = comMenth.createPanel(0, 0, 1000, 530);
		// �޲�������
		pleft.setLayout(null);
		// ����������ʾ�ͱ����ʾ���Լ���ť
		JLabel lbtop = comMenth.createLabel("������Ʒ��Ż����Ʋ�ѯ��Ʒ��Ϣ��ѡ�����Ʒ��Ϣ�����ұ�չʾ",10, 10, 400, 25, "����", 12);
		lbtop.setForeground(Color.RED);
		JLabel lbtopid = comMenth.createLabel("��������Ʒ����:", 10, 40, 100, 25, "����", 12);
		tfgoodName = comMenth.createTextField("", 120, 40, 160, 25, "����", 12, Color.white);
		btnSel = comMenth.createImagesButton("images\\new.png", 25, 25,290, 40, 25, 25);
		btnAll =  comMenth.createImagesButton("images\\refresh.png", 25, 25, 320, 40, 25, 25);
		// �������
		String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ۼ�", "��Ϳ��", };
		// ��ȡ���ݿ�����
		GoodPurDetailDao1 goodpurd = new GoodPurDetailDao1();
		List<GoodPurDetail> lstu = goodpurd.queryStatus();
		Object[][] rows = new Object[lstu.size()][];
		for (int i = 0; i < rows.length; i++) {
			// ��ȡ���϶���
			GoodPurDetail goodpur = lstu.get(i);
			// ����������תΪ����洢
			Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
					goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getGoods_number(),
					goodpur.getGoods_sellPrice(), goodpur.getGoods_minNumber() };
			// ����λ���鸳ֵ
			rows[i] = obj;
		}
		tmleft = new DefaultTableModel(rows, str);
		tableleft = new JTable(tmleft);
		tableleft = new MTable(tmleft);
		spleft = new JScrollPane(tableleft);
		spleft.setBounds(10, 80, 480, 400);
		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(155,193,242);
			}

		}
		commondMethods.setColor(tableleft, color);
		// ˫���������ѡ����Ʒ
		tableleft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = tableleft.getSelectedRow();
					int column = tableleft.getAutoResizeMode();
					tableleft.getValueAt(row, column);
					if (row != -1) {
						new PurchaseOldSel(tmleft, tableleft, tmright);
					}
				}
			}
		});
		
		pleft.add(lbtop);
		pleft.add(tfgoodName);
		pleft.add(lbtopid);
		pleft.add(btnAll);
		pleft.add(btnSel);
		pleft.add(spleft);
		this.add(pleft);
	}

	/**
	 * �����ұ����
	 */
	public void setRightPanel() {

		// ���������
		pright = comMenth.createPanel(0, 0, 1000, 530);
		// �޲�������
		pright.setLayout(null);
		// ȷ����ť
		btnSure = comMenth.createButton("ȷ��", 50, 450, 60, 30, "����",12);
		// ȡ����ť
		btnCan = comMenth.createButton("ȡ��", 150, 450, 60, 30, "����",12);
		// �޸İ�ť
		btnUpdate = comMenth.createButton("�޸�", 250, 450, 60, 30, "����",12);
		// ɾ����ť
		btnDel =  comMenth.createButton("ɾ��", 350, 450, 60, 30, "����",12);
		// �������
		String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ܼ�", "�ۼ�", "��Ϳ��" };
		Object[][] rows = new Object[0][];

		tmright = new DefaultTableModel(rows, str);
		tableright = new JTable(tmright);
		tableright = new MTable(tmright);
		spright = new JScrollPane(tableright);
		spright.setBounds(10, 10, 480, 420);
		Color[] color = new Color[100];
		for (int i = 0; i < 100; i++) {
			int num = i % 2;
			if (num == 0) {
				color[i] = Color.WHITE;
			} else {
				color[i] = new Color(155,193,242);
			}
		}
		commondMethods.setColor(tableright, color);
		// ��ӽ����
		pright.add(spright);
		pright.add(btnSure);
		pright.add(btnDel);
		pright.add(btnCan);
		pright.add(btnUpdate);
		this.add(pright);
	}

	/**
	 * ��ť�¼�
	 */
	public void btn() {
		// ���Ʋ�ѯ�¼�
		btnSel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ����Ʒ���
				// ������ѯ��Ϣ
				String search = "" + tfgoodName.getText();
				search.trim();
				if (!search.equals("")) {
					// �������
					String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "�ۼ�", "����", "��Ϳ��", };
					// ��ȡ���ݿ�����
					GoodPurDetailDao1 goodpurd = new GoodPurDetailDao1();
					List<GoodPurDetail> lstu = goodpurd.queryId(search);
					Object[][] rows = new Object[lstu.size()][];
					for (int i = 0; i < rows.length; i++) {
						// ��ȡ���϶���
						GoodPurDetail goodpur = lstu.get(i);
						// ����������תΪ����洢
						Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
								goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getGoods_number(),
								goodpur.getGoods_sellPrice(), goodpur.getGoods_minNumber() };
						// ����λ���鸳ֵ
						rows[i] = obj;
					}
					tmleft.setDataVector(rows, str);
					Color[] color = new Color[100];
					for (int i = 0; i < 100; i++) {
						int num = i % 2;
						if (num == 0) {
							color[i] = Color.WHITE;
						} else {
							color[i] = new Color(155,193,242);
						}
					}
					commondMethods.setColor(tableleft, color);
				} else {
					// ��������������
					JOptionPane.showMessageDialog(PurchaseOldAdd.this, "��ѯ����Ϊ�գ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ȫ����ѯ
		btnAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ȫ����ѯ��ť
				// �������
				String[] str = { "��Ʒ���", "��Ʒ����", "��λ", "����ͺ�", "����", "����", "�ۼ�", "��Ϳ��", };
				// ��ȡ���ݿ�����
				GoodPurDetailDao1 goodpurd = new GoodPurDetailDao1();
				List<GoodPurDetail> lstu = goodpurd.queryStatus();
				Object[][] rows = new Object[lstu.size()][];
				for (int i = 0; i < rows.length; i++) {
					// ��ȡ���϶���
					GoodPurDetail goodpur = lstu.get(i);
					// ����������תΪ����洢
					Object[] obj = { goodpur.getGoods_id(), goodpur.getGoods_name(), goodpur.getGoods_units(),
							goodpur.getGoods_size(), goodpur.getGoods_purPrise(), goodpur.getGoods_number(),
							goodpur.getGoods_sellPrice(), goodpur.getGoods_minNumber() };
					// ����λ���鸳ֵ
					rows[i] = obj;
				}
				tmleft.setDataVector(rows, str);
				Color[] color = new Color[100];
				for (int i = 0; i < 100; i++) {
					int num = i % 2;
					if (num == 0) {
						color[i] = Color.WHITE;
					} else {
						color[i] = new Color(155,193,242);
					}
				}
				commondMethods.setColor(tableleft, color);
			}
		});
		// �ұ�ȷ����ť
		btnSure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ȷ����ť�¼�
				int col = tableright.getColumnCount();// ��ȡ��
				int row = tableright.getRowCount();// ��ȡ��
				Object[][] rowcol = new Object[row][col];
				for (int i = 0; i < row; i++) {
					// ��ȡ��Ʒ���
					int goodid = (int) tableright.getValueAt(i, 0);
					// ��ȡ��Ʒ����
					int goodnumber = (int) tableright.getValueAt(i, 5);
					//��ȡ��Ʒ����
					double goodPrice = (double) tableright.getValueAt(i, 4);
					//��̬�����洢��Ʒ�ܼ�
					PurchaseOrderView.purAllPrice +=  goodnumber*goodPrice;
					// ������ƷID��ȡ��Ʒ��Ϣ
					StoTransDao1 stod = new StoTransDao1();
					// ������Ʒ��Ϣ
					Good good = null;
					try {
						good = stod.get(goodid);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					Object[] obj = { good.getGoods_id(), good.getGoods_name(), good.getGoods_units(),
							good.getGoods_size(), good.getGoods_purPrise(), goodnumber,
							good.getGoods_purPrise() * goodnumber, good.getGoods_sellPrice(), good.getGoods_stoId(),
							good.getGoods_keepDays(), good.getGoods_minNumber(), good.getGoods_mark() };
					rowcol[i] = obj;
					tm.addRow(obj);
				}
				PurchaseOrderView.tfallmoney.setText(""+PurchaseOrderView.purAllPrice);
				PurchaseOldAdd.this.dispose();
			}
		});
		// �޸İ�ť
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �޸İ�ť
				int rows = tableright.getSelectedRow();
				if (rows != -1) {
					// ��Ʒ�޸��¼�
					new PurchaseOldUpdate(tmright, tableright);
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���Ʒ��Ϣ��", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// ɾ����ť
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ɾ����ť
				int rows = tableright.getSelectedRow();
				if (rows != -1) {
					Object[] obj = { "����ɾ��", "��������" };
					// ѡ����ʾ���
					int res = JOptionPane.showOptionDialog(PurchaseOldAdd.this, "�Ƿ�ȷ��ɾ����", "ɾ��������ʾ", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
					if (res == JOptionPane.YES_OPTION) {
						tmright.removeRow(rows);
						// ɾ���ɹ���ʾ���
						JOptionPane.showMessageDialog(PurchaseOldAdd.this,"ɾ���ɹ�");
					} else {
						// ����ʧ�ܾ���
						JOptionPane.showMessageDialog(PurchaseOldAdd.this, "ɾ��ʧ�ܣ�", "ɾ��������ʾ", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					// ��������������
					JOptionPane.showMessageDialog(PurchaseOldAdd.this, "��ѡ��Ҫɾ������Ʒ��", "ɾ��������ʾ", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		//ȡ����ť
		btnCan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PurchaseOldAdd.this.dispose();
			}
		});
	}

	public static void main(String[] args) {
		// new PurchaseOldAdd();
	}
}
