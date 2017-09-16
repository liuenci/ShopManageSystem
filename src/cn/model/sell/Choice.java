package cn.model.sell;

public class Choice {
	private String goods_name;//商品名称
	private String goods_units;//单位
	private double goods_sellPrice;//商品售价
	private int sDet_number;//数量
	private double sDet_goodPrice;//总金额
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
	public double getGoods_sellPrice() {
		return goods_sellPrice;
	}
	public void setGoods_sellPrice(double goods_sellPrice) {
		this.goods_sellPrice = goods_sellPrice;
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

}
