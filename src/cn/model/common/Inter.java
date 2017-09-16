package cn.model.common;

/**
 * 采购计划表和商品表的中间表
 * 
 * @author LuckyJavaCi
 *
 */
public class Inter {
	// 关系表主键
	private int inter_id;
	// 采购计划编号
	private int inter_planId;
	// 商品编号
	private int inter_goodsId;

	// 设置set和get方法
	public int getInter_id() {
		return inter_id;
	}

	public void setInter_id(int inter_id) {
		this.inter_id = inter_id;
	}

	public int getInter_planId() {
		return inter_planId;
	}

	public void setInter_planId(int inter_planId) {
		this.inter_planId = inter_planId;
	}

	public int getInter_goodsId() {
		return inter_goodsId;
	}

	public void setInter_goodsId(int inter_goodsId) {
		this.inter_goodsId = inter_goodsId;
	}

}
