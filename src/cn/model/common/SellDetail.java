package cn.model.common;

/**
 * ���۶��������
 * 
 * @author Administrator
 *
 */
public class SellDetail {
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

	public SellDetail(int sDet_sellId, int sDet_goodId, int sDet_number, double sDet_goodPrice, int sDet_status,
			String sDet_mark) {
		this.sDet_sellId = sDet_sellId;
		this.sDet_goodId = sDet_goodId;
		this.sDet_number = sDet_number;
		this.sDet_goodPrice = sDet_goodPrice;
		this.sDet_status = sDet_status;
		this.sDet_mark = sDet_mark;
	}
	public SellDetail(){
		
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

}
