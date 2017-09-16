package cn.model.common;

import java.sql.Date;

/**
 * �ɹ�������
 * 
 * @author �ܳ���
 *
 */
public class PurchaseOrder {
	// �������
	private int pur_id;
	// �����̱�ţ����
	private int pur_supplyId;
	// �ɹ�����
	private Date pur_date;
	// ֧���ܽ��
	private double pur_pay;
	// Ա����ţ����
	private int pur_empId;
	// �Ƿ���ˣ�0��δ���1�������ͨ�� 2�����δͨ���˻زɹ�Ա��
	private int pur_status;
	// ��ע
	private String pur_mark;
	

	public PurchaseOrder() {
		super();
	}

	




	public PurchaseOrder( int pur_supplyId, Date pur_date, double pur_pay, int pur_empId, int pur_status,
			String pur_mark) {
		super();
		this.pur_supplyId = pur_supplyId;
		this.pur_date = pur_date;
		this.pur_pay = pur_pay;
		this.pur_empId = pur_empId;
		this.pur_status = pur_status;
		this.pur_mark = pur_mark;
	}






	public int getPur_id() {
		return pur_id;
	}

	public void setPur_id(int pur_id) {
		this.pur_id = pur_id;
	}

	public int getPur_supplyId() {
		return pur_supplyId;
	}

	public void setPur_supplyId(int pur_supplyId) {
		this.pur_supplyId = pur_supplyId;
	}

	public Date getPur_date() {
		return pur_date;
	}

	public void setPur_date(Date pur_date) {
		this.pur_date = pur_date;
	}

	public double getPur_pay() {
		return pur_pay;
	}

	public void setPur_pay(double pur_pay) {
		this.pur_pay = pur_pay;
	}

	public int getPur_empId() {
		return pur_empId;
	}

	public void setPur_empId(int pur_empId) {
		this.pur_empId = pur_empId;
	}

	public int getPur_status() {
		return pur_status;
	}

	public void setPur_status(int pur_status) {
		this.pur_status = pur_status;
	}

	public String getPur_mark() {
		return pur_mark;
	}

	public void setPur_mark(String pur_mark) {
		this.pur_mark = pur_mark;
	}

}
