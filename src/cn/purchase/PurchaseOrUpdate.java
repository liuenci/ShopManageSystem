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
 * 1.����2017-8-11 
 * 2.��Ҫ���� 
 * a.���������Ʒ����
 * @author �ܳ���
 *
 */
public class PurchaseOrUpdate extends JFrame {

	private DefaultTableModel tms;// ������ģ��
	private JTable tables;// ������
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

	public PurchaseOrUpdate(DefaultTableModel tms, JTable tables) {
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
		this.setSize(450, 380);
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
		row = tables.getSelectedRow();
		goods_id = Integer.parseInt(tms.getValueAt(row, 0).toString());
		goods_name = tms.getValueAt(row, 1).toString();
		goods_units = tms.getValueAt(row, 2).toString();
		goods_size = tms.getValueAt(row, 3).toString();
		goods_purPrise = Double.parseDouble(tms.getValueAt(row, 4).toString());
		goods_number = Integer.parseInt(tms.getValueAt(row, 5).toString());
		pDet_goodPrice = Double.parseDouble(tms.getValueAt(row, 6).toString());
		goods_sellPrice = Double.parseDouble(tms.getValueAt(row, 7).toString());
		goods_stoId = Integer.parseInt(tms.getValueAt(row, 8).toString());
		goods_keepDays = Integer.parseInt(tms.getValueAt(row, 9).toString());
		goods_minNumber = Integer.parseInt(tms.getValueAt(row, 10).toString());
		goods_mark = tms.getValueAt(row, 11).toString();
		// ���������
		jp = comMenth.createPanel(0, 0, 450, 400);
		JPanel jptop = comMenth.createPanel(20, 5, 400, 40);
		JPanel jpmessage = comMenth.createPanel(20, 50, 400, 240);
		JPanel jpbottom = comMenth.createPanel(20, 295, 400, 40);
		// �޲�������
		jp.setLayout(null);
		// ��ȡ���ֵ
		JLabel lbTitle = comMenth.createLabel("��Ʒ��Ϣ", 150, 2, 180, 40, "����", 24);

		// ȷ����ť
		btnSure = comMenth.createButton("ȷ��", 130, 5, 60, 30, "����", 14);
		// ȡ����ť
		btnDel = comMenth.createButton("ȡ��", 230, 5, 60, 30, "����", 14);
		JLabel laname = comMenth.createLabel("��Ʒ���ƣ�", 30, 10, 80, 25, "����", 14);
		tfname = comMenth.createLabel(goods_name, 110, 10, 120, 25, "����", 14);
		JLabel lapurPrice = comMenth.createLabel("��Ʒ���ۣ�", 30, 130, 80, 25, "����", 14);
		tfpurPrice = comMenth.createLabel("" + goods_purPrise, 110, 130, 120, 25, "����", 14);
		JLabel lasize = comMenth.createLabel("��Ʒ���", 250, 50, 80, 25, "����", 14);
		size = comMenth.createLabel(goods_size, 330, 50, 60, 25, "����", 14);

		JLabel lasellPrice = comMenth.createLabel("��Ʒ�ۼۣ�", 250, 130, 80, 25, "����", 14);
		tfsellPrice = comMenth.createLabel("" + goods_sellPrice, 330, 130, 120, 25, "����", 14);
		JLabel lastoId = comMenth.createLabel("��Ųֿ⣺", 30, 50, 80, 25, "����", 14);
		inputSto = comMenth.createLabel("" + goods_stoId, 110, 50, 120, 25, "����", 14);
		JLabel lakeepDays = comMenth.createLabel("�� �� �ڣ�", 250, 10, 80, 25, "����", 14);
		keepDays = comMenth.createLabel("" + goods_keepDays, 330, 10, 60, 25, "����", 14);
		JLabel laminNumbers = comMenth.createLabel("��", 370, 10, 25, 25, "����", 14);
		JLabel launits = comMenth.createLabel("��Ʒ��λ��", 30, 90, 80, 25, "����", 14);
		units = comMenth.createLabel(goods_units, 110, 90, 120, 25, "����", 14);
		JLabel laminNumber = comMenth.createLabel("��Ϳ�棺", 250, 90, 80, 25, "����", 14);
		minNumber = comMenth.createLabel("" + goods_minNumber, 330, 90, 60, 25, "����", 14);
		JLabel lanumber = comMenth.createLabel("��Ʒ������", 30, 170, 85, 25, "����", 14);
		tfnumber = comMenth.createTextField("" + goods_number, 110, 170, 60, 25, "����", 14, Color.WHITE);

		// ��ӽ����// ��ӽ����
		jptop.add(lbTitle);
		jpmessage.add(units);
		jpmessage.add(laname);
		jpmessage.add(tfname);
		jpmessage.add(launits);
		jpmessage.add(lasize);
		jpmessage.add(size);
		jpmessage.add(lastoId);
		jpmessage.add(inputSto);
		jpmessage.add(lakeepDays);
		jpmessage.add(keepDays);
		jpmessage.add(laminNumbers);
		jpmessage.add(lapurPrice);
		jpmessage.add(tfpurPrice);
		jpmessage.add(lasellPrice);
		jpmessage.add(tfsellPrice);
		jpmessage.add(lanumber);
		jpmessage.add(tfnumber);
		jpmessage.add(laminNumber);
		jpmessage.add(minNumber);

		// ��ӽ����
		jpbottom.add(btnSure);
		jpbottom.add(btnDel);
		// ��ӽ�����
		jp.add(jpmessage);
		jp.add(jptop);
		jp.add(jpbottom);
		this.add(jp);
	}

	public void btn() {
		// ȷ����ť����¼�
		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ȷ����ť�¼�
				// ȷ����ť�¼�
				try {
					goods_number = Integer.parseInt(tfnumber.getText());
					if (tfnumber.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "��������Ҫ�����Ʒ������", "������ʾ", JOptionPane.WARNING_MESSAGE);
						tfnumber.setText("");
					} else if (goods_number <= 0) {
						JOptionPane.showMessageDialog(null, "��������С�ڻ������", "������ʾ", JOptionPane.WARNING_MESSAGE);
						tfnumber.setText("");
					} else {
						goods_name = tfname.getText();
						goods_units = units.getText();
						goods_size = size.getText();
						goods_purPrise = Double.parseDouble(tfpurPrice.getText());
						goods_sellPrice = Double.parseDouble(tfsellPrice.getText());

						goods_stoId = Integer.parseInt(inputSto.getText());
						goods_keepDays = Integer.parseInt(keepDays.getText());
						goods_minNumber = Integer.parseInt(minNumber.getText());
						pDet_goodPrice = goods_purPrise * goods_number;
						JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
						// �޸�table����Ϣ
						tms.setValueAt(goods_purPrise, row, 4);
						tms.setValueAt(goods_number, row, 5);
						tms.setValueAt(pDet_goodPrice, row, 6);
						tms.setValueAt(goods_sellPrice, row, 7);
						int maxRows = tables.getRowCount();
						// ��Ʒ���ܼ�����Ϊ0
						PurchaseOrderView.purAllPrice = 0;
						// ����
						for (int i = 0; i < maxRows; i++) {
							// �õ�ÿһ�е��ܽ��
							double perGoodPrice = (double) tables.getValueAt(i, 6);
							PurchaseOrderView.purAllPrice += perGoodPrice;
						}
						PurchaseOrderView.tfallmoney.setText("" + PurchaseOrderView.purAllPrice);
						// �رյ�ǰ����
						PurchaseOrUpdate.this.dispose();
					}
				} catch (NumberFormatException numbernull) {
					JOptionPane.showMessageDialog(null, "�����ı�����������", "������ʾ", JOptionPane.WARNING_MESSAGE);
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
				PurchaseOrUpdate.this.dispose();
			}
		});
	}

	public static void main(String[] args) {
		// new PurchaseOrUpdate();
	}
}
