package cn.model.sell;

import java.sql.Date;
/**
 * �˿��+�ͻ�����+��������
 * @author Administrator
 *
 */

public class RefundOrder {
	// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
	private int sell_id;// ���۶������
	private Date sell_date;// ��������
	private String Customername;// �ͻ�����
	private double sell_profit;// �տ���
	private String Billtype;// ��������
	private int sell_empId;// ������
	private String sell_mark;// ��ע
	private int sDet_status;//״̬
	public int getsDet_status() {
		return sDet_status;
	}

	public void setsDet_status(int sDet_status) {
		this.sDet_status = sDet_status;
	}

	public int getSell_id() {
		return sell_id;
	}

	public void setSell_id(int sell_id) {
		this.sell_id = sell_id;
	}

	public Date getSell_date() {
		return sell_date;
	}

	public void setSell_date(Date sell_date) {
		this.sell_date = sell_date;
	}

	public String getCustomername() {
		Customername="��ͨ�ͻ�";
		return Customername;
	}

	public void setCustomername(String customername) {
		Customername = customername;
	}

	public double getSell_profit() {
		return sell_profit;
	}

	public void setSell_profit(double sell_profit) {
		this.sell_profit = sell_profit;
	}

	public String getBilltype() {
		Billtype="��Ʒ����";
		return Billtype;
	}

	public void setBilltype(String billtype) {
		Billtype = billtype;
	}

	public int getSell_empId() {
		sell_empId=2;
		return sell_empId;
	}

	public void setSell_empId(int sell_empId) {
		this.sell_empId = sell_empId;
	}

	public String getSell_mark() {
		return sell_mark;
	}

	public void setSell_mark(String sell_mark) {
		this.sell_mark = sell_mark;
	}

}
