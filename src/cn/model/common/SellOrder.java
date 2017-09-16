package cn.model.common;
/**
 * 销售订单表
 * @author Administrator
 *
 */

import java.sql.Date;

public class SellOrder {
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
	//订单状态
	private int sell_status;
	

	
	public int getSell_status() {
		return sell_status;
	}
	public void setSell_status(int sell_status) {
		this.sell_status = sell_status;
	}
	public SellOrder(int sell_id, int sell_empId, Date sell_date, Double sell_profit,String sell_mark) {
		
		this.sell_id = sell_id;
		this.sell_empId = sell_empId;
		this.sell_date = sell_date;
		this.sell_profit = sell_profit;
		this.sell_mark = sell_mark;
	}
	public SellOrder(){
		
	}

	public SellOrder( int sell_empId, Date sell_date, Double sell_profit,int sell_status, String sell_mark) {
		
		this.sell_empId = sell_empId;
		this.sell_date = sell_date;
		this.sell_profit = sell_profit;
		this.sell_status = sell_status;
		this.sell_mark = sell_mark;
	}
	public SellOrder(int sell_empId, Date sell_date, Double sell_profit, String sell_mark) {

		this.sell_empId = sell_empId;
		this.sell_date = sell_date;
		this.sell_profit = sell_profit;
		this.sell_mark = sell_mark;
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
//		String date = "2017-8-13";
//		java.sql.Date sell_date = java.sql.Date.valueOf(date);
		return sell_date;
	}

	public void setSell_date(Date sell_date) {
		this.sell_date = sell_date;
	}

	public Double getSell_profit() {
//		sell_profit=0.0;
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

}
