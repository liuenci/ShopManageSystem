package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.StoTransDao1;
import cn.method.CommondMethods;
import cn.model.common.Good;

/**
 * 1.����2017-8-21 
 * 2.��Ҫ���� 
 *  a.������Ʒ��Ϣ
 *  b.����Ʒ��Ϊ�յ�����ƶ��¼�
 *  c.ȷ����ť�����ж�
 * 
 * @author �ܳ���
 *
 */
public class PurchaseOrdNew extends JFrame {

	private DefaultTableModel tms;// ������ģ��
	private JTable tables;// ������
	int goods_id;// ��Ʒ���
	String goods_name;// ��Ʒ����
	String goods_units;// ��Ʒ��λ
	String goods_size;// ��Ʒ����С
	double goods_purPrise;// ��Ʒ����
	double goods_sellPrice;// ��Ʒ�ۼ�
	int goods_number;// ��Ʒ����
	int goods_stoId;// �ֿ���
	int goods_keepDays;// ������
	int goods_minNumber;// ��Ϳ��
	String goods_mark;// ��ע

	JTextField tfname;
	JLabel noteName;// ������ʾ��
	JComboBox units;
	JTextField tfsize;
	JLabel noteSize;// �����ʾ��
	JTextField tfpurPrice;
	JLabel noteMoney;// ������ʾ���
	JTextField tfsellPrice;
	JLabel notePrice;// �ۼ���ʾ��
	JTextField tfnumber;
	JLabel noteMum;// ��Ʒ������ʾ���
	JComboBox inputSto;
	JTextField tfkeepDays;
	JLabel noteKeepDays;// ��������ʾ���
	JTextField tfminNumber;
	JLabel noteMin;// ��Ϳ����ʾ���
	JTextArea tfmark;
	JPanel jp;// �������
	JButton btnSure;// ȷ�ϰ�ť
	JButton btnDel;// ȡ����ť
	CommondMethods comMenth = null;

	public PurchaseOrdNew(DefaultTableModel tms, JTable tables) {
		this.tables = tables;
		this.tms = tms;
		comMenth = new CommondMethods();
		this.inist();
		this.addpanel();
		this.btn();
		// ���ڿ��ӻ�
		this.setVisible(true);
	}

	/**
	 * ��ʼ��ҳ��
	 */
	public void inist() {
		// ���ô��ڴ�С
		this.setSize(480, 400);
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
	 * �������
	 */
	public void addpanel() {
		// ���������
		jp = comMenth.createPanel(0, 0, 550, 400);
		// �޲�������
		jp.setLayout(null);
		// ȷ����ť
		btnSure = comMenth.createButton("ȷ��", 180, 300, 60, 30, "����", 14);
		// ȡ����ť
		btnDel = comMenth.createButton("ȡ��", 280, 300, 60, 30, "����", 14);
		// ��Ʒ�������ж����
		JLabel laname = comMenth.createLabel("��Ʒ���ƣ�", 10, 10, 80, 25, "����", 14);
		tfname = comMenth.createTextField("", 80, 10, 120, 25, "����", 14, Color.white);
		tfname.setName("��Ʒ����");
		JLabel lbnames = comMenth.createLabel("", 210, 10, 25, 25, "����", 14);
		Icon iconames = new ImageIcon("images\\goods.png");
		lbnames.setIcon(iconames);
		noteName = comMenth.createLabel("", 20, 35, 160, 25, "����", 12);
		noteName.setForeground(Color.red);
		jp.add(noteName);
		tfname.addFocusListener(new My(noteName, tfname));

		// ��Ʒ�������ж����
		JLabel lapurPrice = comMenth.createLabel("��Ʒ����", 10, 60, 80, 25, "����", 14);
		tfpurPrice = comMenth.createTextField("", 80, 60, 120, 25, "����", 14, Color.white);
		tfpurPrice.setName("��Ʒ����");
		JLabel lbmoney = new JLabel();
		Icon iconmoney = new ImageIcon("images\\money.png");
		lbmoney.setIcon(iconmoney);
		lbmoney.setBounds(210, 60, 25, 25);
		noteMoney = comMenth.createLabel("", 20, 85, 160, 25, "����", 12);
		noteMoney.setForeground(Color.red);
		jp.add(noteMoney);
		tfpurPrice.addFocusListener(new My(noteMoney, tfpurPrice));

		// ��Ʒ�������ʾ���
		JLabel lasize = comMenth.createLabel("��Ʒ���", 270, 60, 80, 25, "����", 14);
		tfsize = comMenth.createTextField("", 350, 60, 60, 25, "����", 12, Color.WHITE);
		tfsize.setName("��Ʒ���");
		noteSize = comMenth.createLabel("", 270, 85, 160, 25, "����", 12);
		noteSize.setForeground(Color.red);
		jp.add(noteSize);
		tfsize.addFocusListener(new My(noteSize, tfsize));

		// ��Ʒ�ۼ�����ʾ���
		JLabel lasellPrice = comMenth.createLabel("��Ʒ�ۼۣ�", 10, 110, 80, 25, "����", 14);
		tfsellPrice = comMenth.createTextField("", 80, 110, 120, 25, "����", 14, Color.white);
		tfsellPrice.setName("��Ʒ�ۼ�");
		JLabel lbmoney2 = comMenth.createLabel("", 210, 110, 25, 25, "����", 14);
		Icon iconmoney2 = new ImageIcon("images\\money2.png");
		lbmoney2.setIcon(iconmoney2);
		notePrice = comMenth.createLabel("", 20, 135, 160, 25, "����", 12);
		notePrice.setForeground(Color.red);
		jp.add(notePrice);
		tfsellPrice.addFocusListener(new My(notePrice, tfsellPrice));

		// ���
		JLabel lastoId = comMenth.createLabel("��Ųֿ⣺", 10, 160, 80, 25, "����", 14);
		// ��������ֿ��б�
		inputSto = new JComboBox();
		// ���б����ѡ��
		inputSto.addItem("���ֿ�");
		inputSto.addItem("�ƿ�");
		inputSto.addItem("���Ͽ�");
		inputSto.addItem("��ʳ��");
		inputSto.setBounds(80, 160, 120, 25);
		JLabel btnsto = comMenth.createLabel("", 210, 160, 20, 20, "����", 14);
		Icon icon = new ImageIcon("images\\refresh.png");
		btnsto.setIcon(icon);

		// ����������ʾ���
		JLabel lakeepDays = comMenth.createLabel("����ʱ��", 270, 160, 85, 25, "����", 14);
		tfkeepDays = comMenth.createTextField("", 350, 160, 60, 25, "����", 14, Color.WHITE);
		JLabel laminNumbers = comMenth.createLabel("��", 420, 160, 25, 25, "����", 14);
		tfkeepDays.setName("����ʱ��");
		noteKeepDays = comMenth.createLabel("", 270, 185, 160, 25, "����", 12);
		noteKeepDays.setForeground(Color.red);
		jp.add(noteKeepDays);
		tfkeepDays.addFocusListener(new My(noteKeepDays, tfkeepDays));

		JLabel launits = comMenth.createLabel("��Ʒ��λ��", 270, 210, 80, 25, "����", 14);
		units = new JComboBox();
		// ���б����ѡ��
		units.addItem("ƿ");
		units.addItem("��");
		units.addItem("��");
		units.addItem("��");
		units.setBounds(350, 210, 60, 25);

		// ��Ϳ������ʾ���
		JLabel laminNumber = comMenth.createLabel("��Ϳ�棺", 270, 110, 80, 25, "����", 14);
		tfminNumber = comMenth.createTextField("", 350, 110, 60, 25, "����", 14, Color.WHITE);
		tfminNumber.setName("��Ϳ��");
		noteMin = comMenth.createLabel("", 270, 135, 160, 25, "����", 12);
		noteMin.setForeground(Color.red);
		jp.add(noteMin);
		tfminNumber.addFocusListener(new My(noteMin, tfminNumber));

		// ��Ʒ��������ʾ���
		JLabel lanumber = comMenth.createLabel("��Ʒ������", 270, 10, 80, 25, "����", 14);
		tfnumber = comMenth.createTextField("", 350, 10, 60, 25, "����", 14, Color.WHITE);
		tfnumber.setName("��Ʒ����");
		noteMum = comMenth.createLabel("", 270, 35, 160, 25, "����", 12);
		noteMum.setForeground(Color.red);
		jp.add(noteMum);
		tfnumber.addFocusListener(new My(noteMum, tfnumber));

		JLabel lamark = comMenth.createLabel("��  ע��", 10, 210, 80, 25, "����", 14);
		tfmark = new JTextArea();
		tfmark.setBounds(80, 210, 120, 40);
		MatteBorder borders = new MatteBorder(1, 1, 1, 1, new Color(122, 138, 153));
		tfmark.setBorder(borders);

		// ��ӽ����
		jp.add(lbnames);
		jp.add(units);
		jp.add(lbmoney);
		jp.add(lbmoney2);
		jp.add(inputSto);
		jp.add(btnsto);
		jp.add(laname);
		jp.add(tfname);
		jp.add(launits);
		jp.add(lasize);
		jp.add(tfsize);
		jp.add(lapurPrice);
		jp.add(tfpurPrice);
		jp.add(lasellPrice);
		jp.add(tfsellPrice);
		jp.add(lanumber);
		jp.add(tfnumber);
		jp.add(lastoId);
		jp.add(lakeepDays);
		jp.add(tfkeepDays);
		jp.add(laminNumber);
		jp.add(tfminNumber);
		jp.add(lamark);
		jp.add(tfmark);
		jp.add(laminNumbers);

		jp.add(btnSure);
		jp.add(btnDel);
		// ��ӽ�����
		this.add(jp);
	}

	public void btn() {
		// ȷ����ť�¼�
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ("".equals(tfname.getText().trim())) {
					JOptionPane.showMessageDialog(null, "��Ʒ��������Ϊ��");
				} else if ("".equals(tfnumber.getText())) {
					JOptionPane.showMessageDialog(null, "��Ʒ��������Ϊ��");
				} else if ("".equals(tfpurPrice.getText().trim())) {
					JOptionPane.showMessageDialog(null, "��Ʒ���۲���Ϊ��");
				} else if ("".equals(tfsize.getText().trim())) {
					JOptionPane.showMessageDialog(null, "��Ʒ����С����Ϊ��");
				} else if ("".equals(tfsellPrice.getText())) {
					JOptionPane.showMessageDialog(null, "��Ʒ�ۼ۲���Ϊ��");
				} else if ("".equals(tfminNumber.getText())) {
					JOptionPane.showMessageDialog(null, "��Ʒ��Ϳ�治��Ϊ��");
				} else if ("".equals(tfkeepDays.getText())) {
					JOptionPane.showMessageDialog(null, "��Ʒ������������Ϊ��");
				} else {
					if (!tfname.getText().trim().matches("[\\u4e00-\\u9fa5]*")) {
						JOptionPane.showMessageDialog(null, "��Ʒ����ֻ��Ϊ����");
						tfname.setText("");
					} else if (!tfnumber.getText().trim().matches("[1-9]\\d*")) {
						JOptionPane.showMessageDialog(null, "�ɹ�����ֻ��Ϊ������");
						tfnumber.setText("");
					} else if (!tfpurPrice.getText().trim().matches(
							"^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$")) {
						JOptionPane.showMessageDialog(null, "��Ʒ����ֻ��Ϊ����");
						tfpurPrice.setText("");
					} else if (!tfsize.getText().matches("^[A-Za-z0-9]+$")) {
						JOptionPane.showMessageDialog(null, "��Ʒ���ֻ��Ϊ����+Ӣ��");
						tfsize.setText("");
					} else if (!tfsellPrice.getText().trim().matches(
							"^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$")) {
						JOptionPane.showMessageDialog(null, "��Ʒ�ۼ�ֻ��Ϊ����");
						tfsellPrice.setText("");
					} else if (!tfminNumber.getText().trim().matches("[1-9]\\d*")) {
						JOptionPane.showMessageDialog(null, "��С���ֻ��Ϊ������");
						tfminNumber.setText("");
					} else if (!tfkeepDays.getText().trim().matches("[1-9]\\d*")) {
						JOptionPane.showMessageDialog(null, "��Ʒ������ֻ��Ϊ������");
						tfkeepDays.setText("");
					} else {
						goods_name = tfname.getText();
						goods_units = (String) units.getSelectedItem();
						goods_size = tfsize.getText();
						goods_purPrise = Double.parseDouble(tfpurPrice.getText());
						goods_sellPrice = Double.parseDouble(tfsellPrice.getText());
						goods_keepDays = Integer.parseInt(tfkeepDays.getText());
						goods_minNumber = Integer.parseInt(tfminNumber.getText());
						goods_mark = tfmark.getText();
						goods_number = Integer.parseInt(tfnumber.getText());
						if (inputSto.getSelectedItem().equals("���ֿ�")) {
							goods_stoId = 1;
						} else if (inputSto.getSelectedItem().equals("���Ͽ�")) {
							goods_stoId = 2;
						} else if (inputSto.getSelectedItem().equals("�ƿ�")) {
							goods_stoId = 3;
						} else if (inputSto.getSelectedItem().equals("��ʳ��")) {
							goods_stoId = 4;
						}
						// ��ȡ���ݿ�����
						StoTransDao1 stod = new StoTransDao1();
						int afrow = 0;
						Good good = new Good(goods_name, goods_units, goods_size, goods_purPrise, goods_sellPrice,
								goods_number, goods_stoId, goods_keepDays, goods_minNumber, goods_mark);
						afrow = stod.addGood(good);
						if (afrow > 0) {
							Object[] rowData = { afrow, goods_name, goods_units, goods_size, goods_purPrise,
									goods_number, goods_purPrise * goods_number, goods_sellPrice, goods_stoId,
									goods_keepDays, goods_minNumber, goods_mark };
							// �������һ��
							tms.addRow(rowData);
							PurchaseOrderView.purAllPrice += goods_purPrise * goods_number;
							PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
							// ����ɹ���ʾ
							JOptionPane.showMessageDialog(null, "����ɹ���");
							// �رյ�ǰ����
							PurchaseOrdNew.this.dispose();
						} else {
							// ����ʧ����ʾ
							JOptionPane.showMessageDialog(null, "����ʧ�ܣ�", "������ʾ", JOptionPane.WARNING_MESSAGE);
						}
					}
				}

			}
		});
		// ȡ����ť�¼�
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ȡ����ť�¼�
				JOptionPane.showMessageDialog(null, "������Ʒȡ��");
				// �رյ�ǰ����
				PurchaseOrdNew.this.dispose();
			}
		});
	}
}

class My implements FocusListener {
	JLabel noteName;
	JTextField tfname;

	public My(JLabel noteName, JTextField tfname) {
		this.noteName = noteName;
		this.tfname = tfname;
	}

	@Override
	public void focusGained(FocusEvent e) {
		tfname.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (tfname.getText().trim().equals("")) {
			noteName.setText(tfname.getName() + "����Ϊ��Ŷ");
		} else {
			noteName.setText("");
		}
	}

}
