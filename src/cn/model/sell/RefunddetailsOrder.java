package cn.model.sell;

import java.sql.Date;

/**
 * 订单详情+商品
 * 
 * @author Administrator
 *
 */
public class RefunddetailsOrder {
	// 商品编号 商品名称 单位 单价 数量 总金额 规格大小
	// 商品编号
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
	// (详单编号) 主键
	private int sDet_id;
	// (销售订单号) 销售订单表销售订单号的外键
	private int sDet_sellId;
	// (商品编号)商品里商品编号的外键
	private int sDet_goodId;
	// (数量)销售的数量
	private int sDet_number;
	// (总金额)每总商品的总金额
	private double sDet_goodPrice;
	// (销售状态)0.已出售 1.已退款
	private int sDet_status;
	// (备注)
	private String sDet_mark;
	// (销售订单号)主键
	private int sell_id;
	// (员工编号) 员工表编号外键
	private int sell_empId;
	// (销售日期)获取当地时间
	private Date sell_date;
	// (销售总金额)
	private Double sell_profit;
	// (备注)
	private String sell_mark;
	// 订单状态
	private int sell_status;
	private String emp_name;
	private String Billtype;// 单据类型
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getBilltype() {
		Billtype="商品销售";
		return Billtype;
	}
	public void setBilltype(String billtype) {
		Billtype = billtype;
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
	public int getsDet_id() {
		return sDet_id;
	}
	public void setsDet_id(int sDet_id) {
		this.sDet_id = sDet_id;
	}
	public int getsDet_sellId() {
		return sDet_sellId;
	}
	public void setsDet_sellId(int sDet_sellId) {
		this.sDet_sellId = sDet_sellId;
	}
	public int getsDet_goodId() {
		return sDet_goodId;
	}
	public void setsDet_goodId(int sDet_goodId) {
		this.sDet_goodId = sDet_goodId;
	}
	public int getsDet_number() {
		return sDet_number;
	}
	public void setsDet_number(int sDet_number) {
		this.sDet_number = sDet_number;
	}
	public double getsDet_goodPrice() {
		return sDet_goodPrice;
	}
	public void setsDet_goodPrice(double sDet_goodPrice) {
		this.sDet_goodPrice = sDet_goodPrice;
	}
	public int getsDet_status() {
		return sDet_status;
	}
	public void setsDet_status(int sDet_status) {
		this.sDet_status = sDet_status;
	}
	public String getsDet_mark() {
		return sDet_mark;
	}
	public void setsDet_mark(String sDet_mark) {
		this.sDet_mark = sDet_mark;
	}
	public int getSell_id() {
		return sell_id;
	}
	public void setSell_id(int sell_id) {
		this.sell_id = sell_id;
	}
	public int getSell_empId() {
		return sell_empId;
	}
	public void setSell_empId(int sell_empId) {
		this.sell_empId = sell_empId;
	}
	public Date getSell_date() {
		return sell_date;
	}
	public void setSell_date(Date sell_date) {
		this.sell_date = sell_date;
	}
	public Double getSell_profit() {
		return sell_profit;
	}
	public void setSell_profit(Double sell_profit) {
		this.sell_profit = sell_profit;
	}
	public String getSell_mark() {
		return sell_mark;
	}
	public void setSell_mark(String sell_mark) {
		this.sell_mark = sell_mark;
	}
	public int getSell_status() {
		return sell_status;
	}
	public void setSell_status(int sell_status) {
		this.sell_status = sell_status;
	}
	
}
