package cn.purchase;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.method.CommondMethods;

/**
 * 1.����2017-8-12 
 * 2.��Ҫ���� 
 * a.����������Ʒ����
 * @author �ܳ���
 *
 */
public class PurchaseOldSel extends JFrame {
	// ������ģ��
	private DefaultTableModel tmleft;
	private DefaultTableModel tmright;

	// �������
	private JTable tableleft;// ��߱������

	int row = -1;
	int goods_id;// ��Ʒ���
	String goods_name;// ��Ʒ����
	String goods_units;// ��Ʒ��λ
	String goods_size;// ��Ʒ����С
	double goods_purPrise;// ��Ʒ����
	int goods_number;// ��Ʒ����
	double pDet_goodPrice;// �ܼ�
	double goods_sellPrice;// ��Ʒ�ۼ�
	int goods_stoId;// �ֿ���
	int goods_keepDays;// ������
	int goods_minNumber;// ��Ϳ��
	String goods_mark;// ��ע

	JLabel tfname;
	JLabel units;
	JLabel size;
	JLabel tfpurPrice;
	JLabel tfsellPrice;
	JTextField tfnumber;
	JLabel inputSto;
	JLabel keepDays;
	JLabel minNumber;
	JTextArea tfmark;
	JPanel jp;
	JButton btnSure;
	JButton btnDel;
	CommondMethods comMenth = null;
	// �����ά���鱣������
	Object[] purold = new Object[11];

	public PurchaseOldSel(DefaultTableModel tmleft, JTable tableleft, DefaultTableModel tmright) {
		this.tableleft = tableleft;
		this.tmleft = tmleft;
		this.tmright = tmright;
		comMenth = new CommondMethods();
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
		this.setSize(450, 320);
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
		this.setTitle("��Ʒ�޸�");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * �������
	 */
	public void addpanel() {
		row = tableleft.getSelectedRow();
		goods_id = (int) tmleft.getValueAt(row, 0);
		goods_name = tmleft.getValueAt(row, 1).toString();
		goods_units = tmleft.getValueAt(row, 2).toString();
		goods_size = tmleft.getValueAt(row, 3).toString();
		goods_purPrise = Double.parseDouble(tmleft.getValueAt(row, 4).toString());
		goods_number = (int) tmleft.getValueAt(row, 5);
		goods_sellPrice = Double.parseDouble(tmleft.getValueAt(row, 6).toString());
		goods_minNumber = (int) tmleft.getValueAt(row, 7);
		// ����������
		jp = comMenth.createPanel(0, 0, 450, 300);
		JPanel jptop = comMenth.createPanel(20, 5, 400, 40);
		JPanel jpmessage = comMenth.createPanel(20, 50, 400, 180);
		JPanel jpbottom = comMenth.createPanel(20, 235, 400, 40);
		// �޲�������
		jp.setLayout(null);
		// ȷ����ť
		btnSure = comMenth.createButton("ȷ��", 120, 5, 60, 30, "����", 14);
		// ȡ����ť
		btnDel = comMenth.createButton("ȡ��", 240, 5, 60, 30, "����", 14);
		// ��ȡ����ֵ
		JLabel lbTitle = comMenth.createLabel("��Ʒ��Ϣ", 160, 5, 180, 40, "����", 24);

		JLabel laname = comMenth.createLabel("��Ʒ���ƣ�", 30, 20, 80, 25, "����", 14);
		tfname = comMenth.createLabel(goods_name, 110, 20, 120, 25, "����", 14);

		JLabel lapurPrice = comMenth.createLabel("��Ʒ���ۣ�", 30, 100, 80, 25, "����", 14);
		tfpurPrice = comMenth.createLabel("" + goods_purPrise, 110, 100, 120, 25, "����", 14);

		JLabel lasize = comMenth.createLabel("��Ʒ���", 250, 20, 80, 25, "����", 14);
		size = comMenth.createLabel(goods_size, 330, 20, 60, 25, "����", 14);

		JLabel lasellPrice = comMenth.createLabel("��Ʒ�ۼۣ�", 250, 100, 80, 25, "����", 14);
		tfsellPrice = comMenth.createLabel("" + goods_sellPrice, 330, 100, 120, 25, "����", 14);

		JLabel launits = comMenth.createLabel("��Ʒ��λ��", 30, 60, 80, 25, "����", 14);
		units = comMenth.createLabel(goods_units, 110, 60, 120, 25, "����", 14);

		JLabel laminNumber = comMenth.createLabel("��Ϳ�棺", 250, 60, 80, 25, "����", 14);
		minNumber = comMenth.createLabel("" + goods_minNumber, 330, 60, 60, 25, "����", 14);

		JLabel lanumber = comMenth.createLabel("��Ʒ������", 30, 140, 85, 25, "����", 14);
		tfnumber = comMenth.createTextField("", 110, 140, 60, 25, "����", 14, Color.WHITE);

		// ���ӽ����
		jptop.add(lbTitle);
		jpmessage.add(units);
		jpmessage.add(laname);
		jpmessage.add(tfname);
		jpmessage.add(launits);
		jpmessage.add(lasize);
		jpmessage.add(size);
		jpmessage.add(lapurPrice);
		jpmessage.add(tfpurPrice);
		jpmessage.add(lasellPrice);
		jpmessage.add(tfsellPrice);
		jpmessage.add(lanumber);
		jpmessage.add(tfnumber);
		jpmessage.add(laminNumber);
		jpmessage.add(minNumber);

		// ���ӽ����
		jpbottom.add(btnSure);
		jpbottom.add(btnDel);
		// ���ӽ�����
		jp.add(jpmessage);
		jp.add(jptop);
		jp.add(jpbottom);
		this.add(jp);

		// ȷ����ť����¼�
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowright = tmright.getRowCount();
				try {
					int goodNum = Integer.parseInt(tfnumber.getText());
					if (goodNum <= 0) {
						JOptionPane.showMessageDialog(null, "��������С�ڻ������");
						tfnumber.setText("");
					} else if (tfnumber.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "��������Ҫ������Ʒ������", "������ʾ", JOptionPane.WARNING_MESSAGE);
						tfnumber.setText("");
					} else {
						purold[0] = Integer.parseInt(tmleft.getValueAt(row, 0).toString());
						purold[1] = tfname.getText();
						purold[2] = units.getText();
						purold[3] = size.getText();
						purold[4] = Double.parseDouble(tfpurPrice.getText());
						purold[5] = Integer.parseInt(tfnumber.getText());
						purold[6] = Double.parseDouble(tfpurPrice.getText()) * Integer.parseInt(tfnumber.getText());
						purold[7] = Double.parseDouble(tfsellPrice.getText());
						purold[8] = Integer.parseInt(minNumber.getText());
						tmright.addRow(purold);
						PurchaseOldSel.this.dispose();
					}
				} catch (NumberFormatException number) {
					// ����ʧ�ܾ���
					JOptionPane.showMessageDialog(null, "�ף���������ȷ������Ŷ��", "������ʾ", JOptionPane.WARNING_MESSAGE);
					tfnumber.setText("");
				}
			}
		});
		// ȡ����ť����¼�
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ȡ����ť�¼�
				// �رյ�ǰ����
				PurchaseOldSel.this.dispose();
			}
		});
	}

}