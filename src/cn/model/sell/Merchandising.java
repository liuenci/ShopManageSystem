package cn.model.sell;

/**
 * 销售详情跟商品联合查找
 * 
 * @author Administrator
 *
 */
public class Merchandising {
	private int goods_id;// id
	private String goods_name;// 商品名称
	private String goods_units;// 商品单位
	private String goods_size;// 商品规格大小
	private double goods_sellPrice;// 商品售价
	private int goods_number;// 商品数量
	private int sDet_number;// 销售数量

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

	public int getsDet_number() {
		return sDet_number;
	}

	public void setsDet_number(int sDet_number) {
		this.sDet_number = sDet_number;
	}

}
