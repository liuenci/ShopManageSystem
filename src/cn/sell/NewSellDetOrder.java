package cn.sell;

public class NewSellDetOrder {
	private int sDetId;//�굥�����
	private int sellId;//���۶�������
	private int goodId;//��Ʒ���
	private String goodName;//��Ʒ����
	private double goodSellPrice;//��Ʒ�ۼ�
	private int sDetNum;//��������
	private double goodPrice;//���۽��
	private int sDetStatus;//����״̬
	private String sDetMark;//��ע
	public int getsDetId() {
		return sDetId;
	}
	public void setsDetId(int sDetId) {
		this.sDetId = sDetId;
	}
	public int getSellId() {
		return sellId;
	}
	public void setSellId(int sellId) {
		this.sellId = sellId;
	}
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public int getsDetNum() {
		return sDetNum;
	}
	public void setsDetNum(int sDetNum) {
		this.sDetNum = sDetNum;
	}
	public double getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(double goodPrice) {
		this.goodPrice = goodPrice;
	}
	public int getsDetStatus() {
		return sDetStatus;
	}
	public void setsDetStatus(int sDetStatus) {
		this.sDetStatus = sDetStatus;
	}
	public String getsDetMark() {
		return sDetMark;
	}
	public void setsDetMark(String sDetMark) {
		this.sDetMark = sDetMark;
	}
	public double getGoodSellPrice() {
		return goodSellPrice;
	}
	public void setGoodSellPrice(double goodSellPrice) {
		this.goodSellPrice = goodSellPrice;
	}
	
}
