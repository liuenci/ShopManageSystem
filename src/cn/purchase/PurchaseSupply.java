package cn.purchase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.SupplyDao1;
import cn.model.common.Supply;
import cn.view.purchase.MTable;
/**
 * 1.���ڣ�2017-8-20
 * 2.��Ҫ����
 *  a.������Ӧ��
 *  b.�޸Ĺ�Ӧ����Ϣ
 *  c.ɾ����Ӧ�̣��޸��ֶ�
 *  d.������ѯ������������ѯ
 *  e.ȫ����ѯ
 *  f.�˳��ý���
 * @author �ܳ���
 *
 */
public class PurchaseSupply extends JFrame {
	private JPanel jp;// ������
	private DefaultTableModel tm;// ������ģ��
	private JScrollPane sp;// �������
	private JTable table;// ������

	public PurchaseSupply() {
		this.inist();
		this.addpanel();
		// ���ڿ��ӻ�
		this.setVisible(true);
	}

	/**
	 * ��ʼ��ҳ��
	 */
	public void inist() {
		// ���ô��ڴ�С
		this.setSize(815, 530);
		// ���ô�����ʾλ��
		this.setLocation(280, 85);
		// �����޸Ĵ��ڵĴ�С
		this.setResizable(false);
		this.setTitle("��Ӧ������");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * �������
	 */
	public void addpanel() {
		// ���������
		jp = new JPanel();
		// �޲�������
		jp.setLayout(null);
		// ����С
		jp.setSize(1200, 600);

		// �������
		String[] str = { "��Ӧ�̱��", "��˾����", "��ַ", "��ϵ��", "��ϵ�绰", "����״̬", "��ע" };
		// ��ȡ���ݿ�����
		SupplyDao1 supd = new SupplyDao1();
		List<Supply> lsup = supd.getSupply();
		Object[][] rows = new Object[lsup.size()][];
		for (int i = 0; i < rows.length; i++) {
			// ��ȡ��Ӧ�̼��϶���
			Supply sup = lsup.get(i);
			String status = sup.getSup_status() == 0 ? "���ֺ���" : "�������";
			// ������תΪ����洢
			Object[] obj = { sup.getSup_id(), sup.getSup_name(), sup.getSup_address(), sup.getSup_linkMan(),
					sup.getSup_phone(), status, sup.getSup_mark() };
			// ����ά���鸳ֵ
			rows[i] = obj;
		}
		tm = new DefaultTableModel(rows, str);
		table = new JTable(tm);
		table = new MTable(tm);

		sp = new JScrollPane(table);
		sp.setBounds(0, 100, 800, 400);

		// ���ӹ�Ӧ�̰�ť
		JButton btnAdd = new JButton();
		btnAdd.setBounds(50, 20, 50, 50);
		// ����ͼƬ����
		Icon iconAdd = new ImageIcon("images\\addSupply.png");
		btnAdd.setIcon(iconAdd);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ���ӹ�Ӧ����Ϣ
				// int row = table.getSelectedRow();
				new PurchaseSupAdd(tm, table);

			}
		});
		// �޸Ĺ�Ӧ����Ϣ��ť
		JButton btnUpdate = new JButton();
		btnUpdate.setBounds(130, 20, 50, 50);
		// ����ͼƬ����
		Icon iconUpdae = new ImageIcon("images\\updateSupply.png");
		btnUpdate.setIcon(iconUpdae);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �޸Ĺ�Ӧ����Ϣ
				int row = table.getSelectedRow();
				if (row != -1) {
					new PurchaseSupUpdate(tm, table);
				} else {
					// ��ʾ���
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵĹ�Ӧ�̣�", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		// ɾ����Ӧ�̰�ť
		JButton btnDel = new JButton();
		btnDel.setBounds(200, 20, 50, 50);
		// ����ͼƬ����
		Icon iconDel = new ImageIcon("images\\deleteSupply.png");
		btnDel.setIcon(iconDel);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ɾ����Ӧ����Ϣ
				int row = table.getSelectedRow();

				if (row != -1) {
					String status = tm.getValueAt(row, 5).toString();
					if (status.equals("�������")) {
						// ��������������
						JOptionPane.showMessageDialog(null, "�ù�Ӧ���Ѿ�ɾ����", "������ʾ", JOptionPane.ERROR_MESSAGE);
					} else {
						int sup_id = Integer.parseInt(tm.getValueAt(row, 0).toString());
						SupplyDao1 supd = new SupplyDao1();
						// �½���Ӧ�̶���
						Supply sup = new Supply();
						sup.setSup_id(sup_id);
						int afrow = 0;
						Object[] obj = { "����ɾ��", "��������" };
						// ѡ����ʾ���
						int res = JOptionPane.showOptionDialog(null, "�Ƿ�ȷ��ɾ����", "ɾ��������ʾ",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
						if (res == JOptionPane.YES_OPTION) {
							// �����޸ķ���
							afrow = supd.delSupply(sup);
						} else {

						}
						if (afrow > 0) {
							// ɾ���ɹ���ʾ���
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
							tm.setValueAt("�������", row, 5);
							// tm.removeRow(row);
						} else {
							// ����ʧ�ܾ���
							JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�", "ɾ��������ʾ", JOptionPane.WARNING_MESSAGE);
						}
					}
				} else {
					// ��������������
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���Ĺ�Ӧ�̣�", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// ȫ����ѯ��Ӧ����Ϣ��ť
		JButton btnSelAll = new JButton();
		btnSelAll.setBounds(280, 20, 50, 50);
		// ����ͼƬ����
		Icon iconSelAll = new ImageIcon("images\\queryAll.png");
		btnSelAll.setIcon(iconSelAll);
		btnSelAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ѯ��Ӧ����Ϣ
				// �������
				String[] str = { "��Ӧ�̱��", "��˾����", "��ַ", "��ϵ��", "��ϵ�绰", "����״̬", "��ע" };
				// ��ȡ���ݿ�����
				SupplyDao1 supd = new SupplyDao1();
				List<Supply> lsup = supd.getSupply();
				Object[][] rows = new Object[lsup.size()][];
				for (int i = 0; i < rows.length; i++) {
					// ��ȡ��Ӧ�̼��϶���
					Supply sup = lsup.get(i);
					String status = sup.getSup_status() == 0 ? "���ֺ���" : "�������";
					// ������תΪ����洢
					Object[] obj = { sup.getSup_id(), sup.getSup_name(), sup.getSup_address(), sup.getSup_linkMan(),
							sup.getSup_phone(), status, sup.getSup_mark() };
					// ����ά���鸳ֵ
					rows[i] = obj;
				}
				tm.setDataVector(rows, str);
			}
		});

		JLabel latit = new JLabel("�����빩Ӧ�̹�˾����");
		latit.setBounds(450, 15, 140, 25);
		// ������ѯ�����
		JTextField flserach = new JTextField();
		flserach.setBounds(450, 35, 180, 25);

		// ������ѯ��Ӧ����Ϣ��ť
		JButton btnSel = new JButton();
		btnSel.setBounds(360, 20, 50, 50);
		// ����ͼƬ����
		Icon iconSel = new ImageIcon("images\\querySupply.png");
		btnSel.setIcon(iconSel);
		btnSel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ѯ��Ӧ����Ϣ
				// String sup_name = "";
				String search = "" + flserach.getText();
				search.trim();

				if (!search.equals("")) {
					// �������
					String[] str = { "��Ӧ�̱��", "��˾����", "��ַ", "��ϵ��", "��ϵ�绰", "����״̬", "��ע" };
					// ��ȡ���ݿ�����
					SupplyDao1 supd = new SupplyDao1();
					List<Supply> sup = supd.getSupplyName(search);
					Object[][] rows = new Object[sup.size()][];
					for (int i = 0; i < rows.length; i++) {
						// ��ȡ��Ӧ�̼��϶���
						Supply supply = sup.get(i);
						String status = supply.getSup_status() == 0 ? "���ֺ���" : "�������";
						// ��ȡ��Ӧ�ı��
						// ������תΪ����洢
						Object[] obj = { supply.getSup_id(), supply.getSup_name(), supply.getSup_address(),
								supply.getSup_linkMan(), supply.getSup_phone(), status, supply.getSup_mark() };
						// ����ά���鸳ֵ
						rows[i] = obj;
					}
					tm.setDataVector(rows, str);
				} else {
					// ��������������
					JOptionPane.showMessageDialog(PurchaseSupply.this, "��ѯ����Ϊ�գ�", "������ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// �˳�������ť
		JButton btnOut = new JButton();
		btnOut.setBounds(700, 20, 50, 50);
		// ����ͼƬ����
		Icon iconOut = new ImageIcon("images\\exit.png");
		btnOut.setIcon(iconOut);
		btnOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PurchaseSupply.this.dispose();
			}
		});

		// ��ť��ӽ����
		jp.add(btnAdd);
		jp.add(btnDel);
		jp.add(btnUpdate);
		jp.add(btnSel);
		jp.add(btnSelAll);
		jp.add(latit);
		jp.add(flserach);
		jp.add(btnOut);
		jp.add(sp);
		// ��ӽ�����
		this.add(jp);

	}

	public static void main(String[] args) {
		new PurchaseSupply();
	}
}
