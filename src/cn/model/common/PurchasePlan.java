package cn.model.common;

/**
 * �ɹ��ƻ���
 */
import java.sql.Date;

public class PurchasePlan {
	// �ɹ��ƻ����
	private int plan_id;
	// �ɹ��ƻ�����
	private Date plan_date;
	// Ա�����
	private int plan_empId;
	// ��ע
	private String plan_mark;

	// ����set��get����
	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

	public Date getPlan_date() {
		return plan_date;
	}

	public void setPlan_date(Date plan_date) {
		this.plan_date = plan_date;
	}

	public int getPlan_empId() {
		return plan_empId;
	}

	public void setPlan_empId(int plan_empId) {
		this.plan_empId = plan_empId;
	}

	public String getPlan_mark() {
		return plan_mark;
	}

	public void setPlan_mark(String plan_mark) {
		this.plan_mark = plan_mark;
	}

}
