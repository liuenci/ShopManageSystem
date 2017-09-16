package cn.model.purchase;

import java.sql.Date;

public class PurchasePlanDetGood {
	// 采购计划编号
	private int plan_id;
	// 计划日期
	private Date plan_date;
	// 外键，员工编号
	private int plan_empId;
	// 备注
	private String plan_mark;
	// 主键，自增
	private int planDet_id;
	// 采购计划表编号，外键
	private int planDet_purId;
	// 商品编号
	private int planDet_goodId;
	// 采购数量
	private int planDet_number;
	// 每种商品的进价总价格
	private Double planDet_goodPrice;
	// 备注
	private String planDet_mark;
	// 商品编号
	private int goods_id;
	// 商品名称
	private String goods_name;
	// 商品单位
	private String goods_units;
	// 商品规格大小
	private String goods_size;
	// 商品进价
	private double goods_purPrise;
	// 商品售价
	private double goods_sellPrice;
	// 商品数量
	private int goods_number;
	// 仓库编号
	private int goods_stoId;
	// 保质期
	private int goods_keepDays;
	// 最低库存
	private int goods_minNumber;
	// 备注
	private String goods_mark;
	// 员工姓名
	private String emp_name;
	
	
	public PurchasePlanDetGood() {
		super();
	}

	public PurchasePlanDetGood(Date plan_date, int plan_empId, String plan_mark, int planDet_purId, int planDet_goodId,
			int planDet_number, Double planDet_goodPrice, String planDet_mark, String goods_name, String goods_units,
			String goods_size, double goods_purPrise, double goods_sellPrice, int goods_number, int goods_stoId,
			int goods_keepDays, int goods_minNumber, String goods_mark, String emp_name) {
		super();
		this.plan_date = plan_date;
		this.plan_empId = plan_empId;
		this.plan_mark = plan_mark;
		this.planDet_purId = planDet_purId;
		this.planDet_goodId = planDet_goodId;
		this.planDet_number = planDet_number;
		this.planDet_goodPrice = planDet_goodPrice;
		this.planDet_mark = planDet_mark;
		this.goods_name = goods_name;
		this.goods_units = goods_units;
		this.goods_size = goods_size;
		this.goods_purPrise = goods_purPrise;
		this.goods_sellPrice = goods_sellPrice;
		this.goods_number = goods_number;
		this.goods_stoId = goods_stoId;
		this.goods_keepDays = goods_keepDays;
		this.goods_minNumber = goods_minNumber;
		this.goods_mark = goods_mark;
		this.emp_name = emp_name;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

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

	public int getPlanDet_id() {
		return planDet_id;
	}

	public void setPlanDet_id(int planDet_id) {
		this.planDet_id = planDet_id;
	}

	public int getPlanDet_purId() {
		return planDet_purId;
	}

	public void setPlanDet_purId(int planDet_purId) {
		this.planDet_purId = planDet_purId;
	}

	public int getPlanDet_goodId() {
		return planDet_goodId;
	}

	public void setPlanDet_goodId(int planDet_goodId) {
		this.planDet_goodId = planDet_goodId;
	}

	public int getPlanDet_number() {
		return planDet_number;
	}

	public void setPlanDet_number(int planDet_number) {
		this.planDet_number = planDet_number;
	}

	public Double getPlanDet_goodPrice() {
		return planDet_goodPrice;
	}

	public void setPlanDet_goodPrice(Double planDet_goodPrice) {
		this.planDet_goodPrice = planDet_goodPrice;
	}

	public String getPlanDet_mark() {
		return planDet_mark;
	}

	public void setPlanDet_mark(String planDet_mark) {
		this.planDet_mark = planDet_mark;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_units() {
		return goods_units;
	}

	public void setGoods_units(String goods_units) {
		this.goods_units = goods_units;
	}

	public String getGoods_size() {
		return goods_size;
	}

	public void setGoods_size(String goods_size) {
		this.goods_size = goods_size;
	}

	public double getGoods_purPrise() {
		return goods_purPrise;
	}

	public void setGoods_purPrise(double goods_purPrise) {
		this.goods_purPrise = goods_purPrise;
	}

	public double getGoods_sellPrice() {
		return goods_sellPrice;
	}

	public void setGoods_sellPrice(double goods_sellPrice) {
		this.goods_sellPrice = goods_sellPrice;
	}

	public int getGoods_number() {
		return goods_number;
	}

	public void setGoods_number(int goods_number) {
		this.goods_number = goods_number;
	}

	public int getGoods_stoId() {
		return goods_stoId;
	}

	public void setGoods_stoId(int goods_stoId) {
		this.goods_stoId = goods_stoId;
	}

	public int getGoods_keepDays() {
		return goods_keepDays;
	}

	public void setGoods_keepDays(int goods_keepDays) {
		this.goods_keepDays = goods_keepDays;
	}

	public int getGoods_minNumber() {
		return goods_minNumber;
	}

	public void setGoods_minNumber(int goods_minNumber) {
		this.goods_minNumber = goods_minNumber;
	}

	public String getGoods_mark() {
		return goods_mark;
	}

	public void setGoods_mark(String goods_mark) {
		this.goods_mark = goods_mark;
	}

}
