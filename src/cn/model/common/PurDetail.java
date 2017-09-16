package cn.model.common;

import java.sql.Date;

/**
 * 采购订单详情表
 * 
 * @author 熊晨晨
 *
 */
public class PurDetail {
	// 采购订单详情表
	private int pDet_id;
	// 采购订单表编号，外键
	private int pDet_purId;
	// 商品编号
	private int pDet_goodId;
	// 采购数量
	private int pDet_number;
	// 每种商品的进价总价格
	private double pDet_goodPrice;
	// 采购状态（0：入库1：未入库）
	private int pDet_status;
	// 备注
	private String pDet_mark;


	public PurDetail() {
		super();
	}

	public PurDetail(int pDet_purId, int pDet_goodId, int pDet_number, double pDet_goodPrice, 
			int pDet_status, String pDet_mark) {
		super();
		this.pDet_purId = pDet_purId;
		this.pDet_goodId = pDet_goodId;
		this.pDet_number = pDet_number;
		this.pDet_goodPrice = pDet_goodPrice;
		this.pDet_status = pDet_status;
		this.pDet_mark = pDet_mark;
	}

	public int getpDet_id() {
		return pDet_id;
	}

	public void setpDet_id(int pDet_id) {
		this.pDet_id = pDet_id;
	}

	public int getpDet_purId() {
		return pDet_purId;
	}

	public void setpDet_purId(int pDet_purId) {
		this.pDet_purId = pDet_purId;
	}

	public int getpDet_goodId() {
		return pDet_goodId;
	}

	public void setpDet_goodId(int pDet_goodId) {
		this.pDet_goodId = pDet_goodId;
	}

	public int getpDet_number() {
		return pDet_number;
	}

	public void setpDet_number(int pDet_number) {
		this.pDet_number = pDet_number;
	}

	public double getpDet_goodPrice() {
		return pDet_goodPrice;
	}

	public void setpDet_goodPrice(double pDet_goodPrice) {
		this.pDet_goodPrice = pDet_goodPrice;
	}


	public int getpDet_status() {
		return pDet_status;
	}

	public void setpDet_status(int pDet_status) {
		this.pDet_status = pDet_status;
	}

	public String getpDet_mark() {
		return pDet_mark;
	}

	public void setpDet_mark(String pDet_mark) {
		this.pDet_mark = pDet_mark;
	}

}
