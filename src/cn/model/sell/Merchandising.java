package cn.model.sell;

/**
 * �����������Ʒ���ϲ���
 * 
 * @author Administrator
 *
 */
public class Merchandising {
	private int goods_id;// id
	private String goods_name;// ��Ʒ����
	private String goods_units;// ��Ʒ��λ
	private String goods_size;// ��Ʒ����С
	private double goods_sellPrice;// ��Ʒ�ۼ�
	private int goods_number;// ��Ʒ����
	private int sDet_number;// ��������

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
