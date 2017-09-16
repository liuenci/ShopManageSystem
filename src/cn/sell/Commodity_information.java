package cn.sell;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * ���һ� 1.����:2017-08-19
 * 1.��ʾ��ӵ���Ʒ
 * 2.ѡ�����������ܳ����ֿ��������
 *
 */
public class Commodity_information extends JFrame {
	Format gs = new Format();// ��ʽ
	JTable sp = null;// �����
	DefaultTableModel tm = null;// ������ģ��
	private JPanel p = null;
	private JSplitPane slipt = null;
	private JTextField jtextField = null;
	private String a = null;
	DefaultTableModel tm1;
	DefaultTableModel tm2;
	private JLabel comnumber = null;// ��Ʒ���
	private JLabel getconumbe = null;// ��ȡ��Ʒ���
	private JLabel spemodel = null;// ����ͺ�
	private JLabel getspeModel = null;// �����ͺ�
	private JLabel curtin = null;// ��ǰ���
	private JLabel getcuinv = null;// ��ȡ��ǰ���
	private JLabel comname = null;// ��Ʒ����
	private JLabel getcommname = null;// ��ȡ��Ʒ����
	private JLabel company = null;// ��λ
	private JLabel getCompany = null;// ��ȡ��λ
	private JLabel sellprice = null;// ���ۼ۸�
	private JLabel getsellprice = null;// ��ȡ���ۼ۸�
	private JLabel number = null;// ����
	private JButton determine = null;// ȷ�ϰ�ť
	private JButton cancel = null;// ȡ����ť
	private JPanel catpanel = null;// Ŀ¼���
	private JPanel conpanel = null;// �������
	private JPanel butpanel = null;// ��ť���
	Object[] sz = new Object[6];

	// ��ʼ��
	public Commodity_information(DefaultTableModel tm, JTable sp, DefaultTableModel tm1) {
		// ���ó�ʼ������
		this.inin();
		this.sp = sp;
		this.tm = tm;
		this.tm1 = tm1;
		this.addPanel();
		Clickevent();
		// ���ô��ڿɼ�
		this.setVisible(true);
	}

	public void inin() {
		// ���ô��ڱ���
		this.setTitle("��Ʒ��Ϣ");
		// ���ô��ڴ�С
		this.setSize(500, 300);
		// ������ʾ���ڵ�λ��
		this.setLocationRelativeTo(getOwner());
		// �����޸Ĵ��ڵĴ�С
		this.setResizable(false);
		// ���ô��ڹر�
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void addPanel() {
		// �������
		p = new JPanel();
		p.setLayout(null);
		int row = sp.getSelectedRow();
		// ��Ʒ���
		comnumber = gs.createLabel("��Ʒ���:", 30, 10, 100, 25, "����", 14);
		// ��ȡ��Ʒ���
		getconumbe = gs.createLabel(tm.getValueAt(row, 0).toString(), 100, 10, 100, 25, "����", 15);
		// ����ͺ�
		spemodel = gs.createLabel("����ͺ�:", 30, 45, 100, 25, "����", 14);
		// �����ͺ�
		getspeModel = gs.createLabel(tm.getValueAt(row, 3).toString(), 100, 45, 100, 25, "����", 15);
		// ��ǰ���
		curtin = gs.createLabel("��ǰ���:", 30, 80, 100, 25, "����", 14);
		// ��ȡ��ǰ���
		getcuinv = gs.createLabel(tm.getValueAt(row, 5).toString(), 100, 80, 100, 25, "����", 14);
		// ��Ʒ����
		comname = gs.createLabel("��Ʒ����:", 310, 10, 100, 25, "����", 14);
		// ��ȡ��Ʒ����
		getcommname = gs.createLabel(tm.getValueAt(row, 1).toString(), 380, 10, 100, 25, "����", 15);
		// ��λ
		company = gs.createLabel("��     λ:", 30, 115, 100, 25, "����", 14);
		// ��ȡ��λ
		getCompany = gs.createLabel(tm.getValueAt(row, 2).toString(), 100, 115, 100, 25, "����", 15);
		// ���ۼ۸�
		sellprice = gs.createLabel("���ۼ۸�:", 310, 60, 100, 25, "����", 14);
		// ��ȡ���ۼ۸�
		getsellprice = gs.createLabel(tm.getValueAt(row, 4).toString(), 380, 60, 100, 25, "����", 15);
		// ����
		number = gs.createLabel("��    ��:", 310, 110, 100, 25, "����", 14);
		// ��������
		jtextField = new JTextField();
		jtextField.setFont(new Font("����", Font.PLAIN, 15));
		jtextField.setBounds(380, 110, 60, 25);
		// ȷ�ϰ�ť
		determine = gs.createButton("ȷ��", 100, 20, 75, 25, "����", 15);
		// ȡ����ť
		cancel = gs.createButton("ȡ��", 320, 20, 75, 25, "����", 15);
		JLabel commtion = gs.createLabel("��Ʒ��Ϣ", 210, 0, 80, 40, "����", 15);
		// Ŀ¼���
		catpanel = gs.createPanel(10, 7, 480, 40);
		// �������
		conpanel = gs.createPanel(10, 55, 480, 150);
		// ��ť���
		butpanel = gs.createPanel(10, 210, 480, 60);
		conpanel.add(comnumber);
		conpanel.add(getconumbe);
		conpanel.add(spemodel);
		conpanel.add(getspeModel);
		conpanel.add(curtin);
		conpanel.add(getcuinv);
		conpanel.add(comname);
		conpanel.add(getcommname);
		conpanel.add(company);
		conpanel.add(getCompany);
		conpanel.add(sellprice);
		conpanel.add(getsellprice);
		conpanel.add(number);
		conpanel.add(jtextField);
		butpanel.add(cancel);
		butpanel.add(determine);
		catpanel.add(commtion);
		p.add(catpanel);
		p.add(conpanel);
		p.add(butpanel);
		this.add(p);
	}

	public void Clickevent() {

		// ȷ�ϰ�ť�¼�
		determine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if("".equals(jtextField.getText())) {
					JOptionPane.showMessageDialog(null, "��������Ϊ��");
				}else {
					// ����ұ����û����Ʒ����ֱ�ӽ�����Ʒ�ӵ��ұ����
					try {
						if (tm1.getRowCount() == 0) {
							if (Integer.parseInt(jtextField.getText()) > Integer.parseInt(getcuinv.getText())
									|| Integer.parseInt(jtextField.getText()) <= 0) {
								JOptionPane.showMessageDialog(null, "�����������������С��0");
							} else if (Integer.parseInt(jtextField.getText()) <= Integer.parseInt(getcuinv.getText())) {
								sz[0] = getconumbe.getText();
								sz[1] = getcommname.getText();
								sz[2] = getCompany.getText();
								sz[3] = Double.parseDouble(getsellprice.getText());
								sz[4] = Integer.parseInt(jtextField.getText());
								sz[5] = (int) sz[4] * (double) sz[3];
								tm1.addRow(sz);
								Commodity_information.this.dispose();
							}
						} else {
							// ����ұ���岻Ϊ�գ������ƥ�䣬���ƥ�䵽�ˣ���ֱ���޸�����,�����޸Ľ��
							int sum = 0;
							double money = 0;
							if (Integer.parseInt(jtextField.getText()) > Integer.parseInt(getcuinv.getText())
									|| Integer.parseInt(jtextField.getText()) <= 0 ) {
								JOptionPane.showMessageDialog(null, "�����������������С��0");
							} else {
								for (int i = 0; i < tm1.getRowCount(); i++) {
									if (Integer.parseInt(getconumbe.getText()) == Integer
											.parseInt(tm1.getValueAt(i, 0).toString().trim())
											) {
										if (Integer.parseInt(jtextField.getText())
													+ Integer.parseInt(tm1.getValueAt(i, 4).toString().trim()) <= Integer
															.parseInt(getcuinv.getText())) {

											sum = Integer.parseInt(jtextField.getText())
													+ Integer.parseInt(tm1.getValueAt(i, 4).toString().trim());
											money = Integer.parseInt(jtextField.getText())
													* Double.parseDouble(getsellprice.getText())
													+ Double.parseDouble(tm1.getValueAt(i, 5).toString().trim());
											tm1.setValueAt(sum, i, 4);
											tm1.setValueAt(money, i, 5);

											Commodity_information.this.dispose();
										}else {
											JOptionPane.showMessageDialog(null, "�����������������С��0");
										}
										break;
									} else if (i == tm1.getRowCount() - 1) {
										if (Integer.parseInt(jtextField.getText()) > Integer.parseInt(getcuinv.getText())
												&& Integer.parseInt(jtextField.getText()) <= 0) {
											JOptionPane.showMessageDialog(null, "�����������������С��0");
										} else if (Integer.parseInt(jtextField.getText()) <= Integer
												.parseInt(getcuinv.getText())) {
											sz[0] = getconumbe.getText();
											sz[1] = getcommname.getText();
											sz[2] = getCompany.getText();
											sz[3] = Double.parseDouble(getsellprice.getText());
											sz[4] = Integer.parseInt(jtextField.getText());
											sz[5] = (int) sz[4] * (double) sz[3];
											tm1.addRow(sz);
											Commodity_information.this.dispose();
											break;
										}
									}
								}

							}
						}
					}catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "��������ȷ������");
					}
				}
				
				
			}
		}

		);
		// ȡ����ť�¼�
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Commodity_information.this.dispose();

			}

		});
	}

	public void xx() {
		JOptionPane.showConfirmDialog(null, "���û������Ҫ��������ȷ���޸�", "������ʾ", JOptionPane.DEFAULT_OPTION);
	}

	public static void main(String[] args) {
	}
}
