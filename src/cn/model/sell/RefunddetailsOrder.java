package cn.model.sell;

import java.sql.Date;

/**
 * ��������+��Ʒ
 * 
 * @author Administrator
 *
 */
public class RefunddetailsOrder {
	// ��Ʒ��� ��Ʒ���� ��λ ���� ���� �ܽ�� ����С
	// ��Ʒ���
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
	// (�굥���) ����
	private int sDet_id;
	// (���۶�����) ���۶��������۶����ŵ����
	private int sDet_sellId;
	// (��Ʒ���)��Ʒ����Ʒ��ŵ����
	private int sDet_goodId;
	// (����)���۵�����
	private int sDet_number;
	// (�ܽ��)ÿ����Ʒ���ܽ��
	private double sDet_goodPrice;
	// (����״̬)0.�ѳ��� 1.���˿�
	private int sDet_status;
	// (��ע)
	private String sDet_mark;
	// (���۶�����)����
	private int sell_id;
	// (Ա�����) Ա���������
	private int sell_empId;
	// (��������)��ȡ����ʱ��
	private Date sell_date;
	// (�����ܽ��)
	private Double sell_profit;
	// (��ע)
	private String sell_mark;
	// ����״̬
	private int sell_status;
	private String emp_name;
	private String Billtype;// ��������
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getBilltype() {
		Billtype="��Ʒ����";
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
