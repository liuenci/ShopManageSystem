package cn.model.common;

/**
 * 采购计划表
 */
import java.sql.Date;

public class PurchasePlan {
	// 采购计划编号
	private int plan_id;
	// 采购计划日期
	private Date plan_date;
	// 员工编号
	private int plan_empId;
	// 备注
	private String plan_mark;

	// 设置set和get方法
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
