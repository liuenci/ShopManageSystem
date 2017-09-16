package cn.sell;

public class NewSellDetOrder {
	private int sDetId;//详单表序号
	private int sellId;//销售订单表编号
	private int goodId;//商品编号
	private String goodName;//商品名称
	private double goodSellPrice;//商品售价
	private int sDetNum;//销售数量
	private double goodPrice;//销售金额
	private int sDetStatus;//销售状态
	private String sDetMark;//备注
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
