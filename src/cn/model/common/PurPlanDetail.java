package cn.model.common;

public class PurPlanDetail {
	private int planDet_id;//采购详单编号，自增，无意义
	private int planDet_purId;//采购订单号，订单表的主键
	private int planDet_goodId;//商品编号
	private int planDet_number;//采购商品数量
	private double planDet_goodPrice;//每种采购商品的数量
	private String PriceplanDet_mark;//每种商品的备注
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
	public double getPlanDet_goodPrice() {
		return planDet_goodPrice;
	}
	public void setPlanDet_goodPrice(double planDet_goodPrice) {
		this.planDet_goodPrice = planDet_goodPrice;
	}
	public String getPriceplanDet_mark() {
		return PriceplanDet_mark;
	}
	public void setPriceplanDet_mark(String priceplanDet_mark) {
		PriceplanDet_mark = priceplanDet_mark;
	}
	
	
}
