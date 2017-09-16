package cn.model.common;

/**
 * ��Ʒ��
 * 
 * @author LuckyJavaCi
 *
 */

public class Good {
	// ��Ʒ���
	private int goods_id;
	// ��Ʒ����
	private String goods_name;
	// ��Ʒ��λ
	private String goods_units;
	// ��Ʒ����С
	private String goods_size;
	// ��Ʒ����
	private double goods_purPrise;
	// ��Ʒ�ۼ�
	private double goods_sellPrice;
	// ��Ʒ����
	private int goods_number;
	// �ֿ���
	private int goods_stoId;
	// ������
	private int goods_keepDays;
	// ��Ϳ��
	private int goods_minNumber;
	// ��ע
	private String goods_mark;

	

	public Good( String goods_name, String goods_units, String goods_size, double goods_purPrise,
			double goods_sellPrice, int goods_number, int goods_stoId, int goods_keepDays, int goods_minNumber,
			String goods_mark) {
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
	}

	public Good() {
		// TODO Auto-generated constructor stub
	}
	public Good(int goods_id, int goods_stoId) {
		this.goods_id = goods_id;
		this.goods_stoId = goods_stoId;
	}

	// ����set��get����
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
