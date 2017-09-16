package cn.model.sell;

import java.sql.Date;

public class PurchaseOrderEmpSup {
	// 订单编号
		private int pur_id;
		// 供货商编号，外键
		private int pur_supplyId;
		// 采购日期
		private Date pur_date;
		// 支付总金额
		private double pur_pay;
		// 员工编号，外键
		private int pur_empId;
		// 是否审核（0：未审核1：已审核通过 2：审核未通过退回采购员）
		private int pur_status;
		// 备注
		private String pur_mark;
		// 员工姓名
		private String emp_name;
		// 供应商名称
		private String sup_name;
		
		
		
		public PurchaseOrderEmpSup() {
			super();
		}
		public PurchaseOrderEmpSup(int pur_supplyId, Date pur_date, double pur_pay, int pur_empId, int pur_status,
				String pur_mark, String emp_name, String sup_name) {
			super();
			this.pur_supplyId = pur_supplyId;
			this.pur_date = pur_date;
			this.pur_pay = pur_pay;
			this.pur_empId = pur_empId;
			this.pur_status = pur_status;
			this.pur_mark = pur_mark;
			this.emp_name = emp_name;
			this.sup_name = sup_name;
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
		public String getEmp_name() {
			return emp_name;
		}
		public void setEmp_name(String emp_name) {
			this.emp_name = emp_name;
		}
		public String getSup_name() {
			return sup_name;
		}
		public void setSup_name(String sup_name) {
			this.sup_name = sup_name;
		}
		
		
}
