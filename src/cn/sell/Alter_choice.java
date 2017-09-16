package cn.sell;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.dao.sell.SalesDao2;
import cn.model.common.Good;

/**
 * 
 * @author ���һ�
 *
 */

public class Alter_choice extends JFrame {
	private JPanel p = null;// ���
	private JTable sp1 = null;
	private DefaultTableModel tm1 = null;
	private int row = 0;
	private JLabel getcommname = null;// ��ȡ��Ʒ����
	private JLabel getcompany = null;// ��ȡ��λ
	private JLabel getSelpri = null;
	private JLabel commname = null;// ��Ʒ����
	private JLabel company = null;// ��λ
	private JLabel selpri = null;
	private JTextField jtextField = null;
	private JLabel number = null;
	private JButton determine = null;// ȷ�ϰ�ť
	private JButton cancel = null;
	double aggregate_amount = 0.0;// �ܽ��
	private JPanel catpanel = null;// Ŀ¼���
	private JPanel conpanel = null;// �������
	private JPanel butpanel = null;// ��ť���
	Format gs = new Format();// ��ʽ

	// ��ʼ��
	public Alter_choice(JTable sp1, DefaultTableModel tm1) {
		this.inin();
		// ���ô��ڿɼ�
		this.sp1 = sp1;
		this.tm1 = tm1;
		addgood();
		Clickevent();
		this.setVisible(true);

	}

	public void inin() {
		// ���ô��ڱ���
		this.setTitle("�޸���Ʒ");
		// ���ô��ڴ�С
		this.setSize(450, 250);
		// ������ʾ���ڵ�λ��
		this.setLocationRelativeTo(getOwner());
		// �����޸Ĵ��ڵĴ�С
		this.setResizable(false);
		// ���ô��ڹر�
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void addgood() {
		p = new JPanel();
		p.setLayout(null);
		row = sp1.getSelectedRow();
		// ��Ʒ����
		commname = gs.createLabel("��Ʒ����:", 30, 20, 90, 15, "����", 14);
		// ��ȡ��Ʒ����
		getcommname = gs.createLabel(tm1.getValueAt(row, 1).toString(), 95, 20, 90, 15, "����", 15);
		// ��λ
		company = gs.createLabel("��   λ:", 270, 20, 90, 15, "����", 14);
		// ��ȡ��λ
		getcompany = gs.createLabel(tm1.getValueAt(row, 2).toString(), 340, 20, 90, 15, "����", 15);
		// ���ۼ۸�
		selpri = gs.createLabel("���ۼ۸�:", 30, 60, 80, 18, "����", 14);
		// ��ȡ���ۼ۸�
		getSelpri = gs.createLabel(tm1.getValueAt(row, 3).toString(), 100, 60, 80, 18, "����", 15);
		number = gs.createLabel("��   ��:", 270, 60, 60, 25, "����", 14);
		// ��������
		jtextField = new JTextField(tm1.getValueAt(row, 4).toString());
		jtextField.setFont(new Font("����", Font.PLAIN, 15));
		jtextField.setBounds(330, 60, 50, 25);
		determine = gs.createButton("ȷ��", 50, 10, 100, 30, "����", 14);
		cancel = gs.createButton("ȡ��", 290, 10, 100, 30, "����", 14);
		JLabel commtion = gs.createLabel("��Ʒ��Ϣ", 175, 0, 80, 40, "����", 15);
		// Ŀ¼���
		catpanel = gs.createPanel(5, 5, 435, 40);
		// �������
		conpanel = gs.createPanel(5, 50, 435, 110);
		// ��ť���
		butpanel = gs.createPanel(5, 163, 435, 55);
		catpanel.add(commtion);
		butpanel.add(cancel);
		butpanel.add(determine);
		conpanel.add(number);
		conpanel.add(jtextField);
		conpanel.add(getSelpri);
		conpanel.add(selpri);
		conpanel.add(getcompany);
		conpanel.add(company);
		conpanel.add(getcommname);
		conpanel.add(commname);
		p.add(catpanel);
		p.add(conpanel);
		p.add(butpanel);
		this.add(p);

	}

	public void Clickevent() {
		// ȷ�ϰ�ť����¼�
		determine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SalesDao2 sum = new SalesDao2();
				List<Good> number = sum.goodsnum(Integer.parseInt(tm1.getValueAt(row, 0).toString()));
				Object[][] rows = new Object[number.size()][];
				for (int j = 0; j < rows.length; j++) {
					Good sb = number.get(j);
					Object[] obj = { sb.getGoods_number() };
					rows[j] = obj;
					try {
						if (Integer.parseInt(jtextField.getText()) > 0
								&& Integer.parseInt(jtextField.getText()) <= Integer.parseInt(obj[j].toString())) {
							tm1.setValueAt(jtextField.getText(), row, 4);
							aggregate_amount = Double.parseDouble(getSelpri.getText())
									* Double.parseDouble(jtextField.getText());
							tm1.setValueAt(aggregate_amount, row, 5);
							Alter_choice.this.dispose();
						} else {
							JOptionPane.showMessageDialog(null, "��������Ϊ0���߸���");
						}
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "��������ȷ������");
					}

				}

			}
		});
		// ȡ����ť����¼�
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Alter_choice.this.dispose();

			}
		});
	}

}
