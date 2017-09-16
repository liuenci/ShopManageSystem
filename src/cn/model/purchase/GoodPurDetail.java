package cn.model.purchase;

import java.awt.HeadlessException;
import java.sql.Date;

import javax.swing.JFrame;

public class GoodPurDetail{
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
	// 商品生产日期
	private Date pDet_goodDate;
	// 采购状态（0：入库1：未入库）
	private int pDet_status;
	// 备注
	private String pDet_mark;

	// 无参构造
	public GoodPurDetail() throws HeadlessException {
		super();
	}

	// 有参构造
	public GoodPurDetail( String goods_name, String goods_units, String goods_size, double goods_purPrise,
			double goods_sellPrice, int goods_number, int goods_stoId, int goods_keepDays, int goods_minNumber,
			String goods_mark,  int pDet_purId, int pDet_goodId, int pDet_number, double pDet_goodPrice,
			Date pDet_goodDate, int pDet_status, String pDet_mark) throws HeadlessException {
		super();
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
		this.pDet_purId = pDet_purId;
		this.pDet_goodId = pDet_goodId;
		this.pDet_number = pDet_number;
		this.pDet_goodPrice = pDet_goodPrice;
		this.pDet_goodDate = pDet_goodDate;
		this.pDet_status = pDet_status;
		this.pDet_mark = pDet_mark;
	}

	/**
	 * 属性封装
	 * 
	 * @return
	 */
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

	public Date getpDet_goodDate() {
		return pDet_goodDate;
	}

	public void setpDet_goodDate(Date pDet_goodDate) {
		this.pDet_goodDate = pDet_goodDate;
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
