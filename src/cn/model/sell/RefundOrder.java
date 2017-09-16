package cn.model.sell;

import java.sql.Date;
/**
 * 退款订单+客户名称+单据类型
 * @author Administrator
 *
 */

public class RefundOrder {
	// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
	private int sell_id;// 销售订单编号
	private Date sell_date;// 销售日期
	private String Customername;// 客户名称
	private double sell_profit;// 收款金额
	private String Billtype;// 单据类型
	private int sell_empId;// 经办人
	private String sell_mark;// 备注
	private int sDet_status;//状态
	public int getsDet_status() {
		return sDet_status;
	}

	public void setsDet_status(int sDet_status) {
		this.sDet_status = sDet_status;
	}

	public int getSell_id() {
		return sell_id;
	}

	public void setSell_id(int sell_id) {
		this.sell_id = sell_id;
	}

	public Date getSell_date() {
		return sell_date;
	}

	public void setSell_date(Date sell_date) {
		this.sell_date = sell_date;
	}

	public String getCustomername() {
		Customername="普通客户";
		return Customername;
	}

	public void setCustomername(String customername) {
		Customername = customername;
	}

	public double getSell_profit() {
		return sell_profit;
	}

	public void setSell_profit(double sell_profit) {
		this.sell_profit = sell_profit;
	}

	public String getBilltype() {
		Billtype="商品销售";
		return Billtype;
	}

	public void setBilltype(String billtype) {
		Billtype = billtype;
	}

	public int getSell_empId() {
		sell_empId=2;
		return sell_empId;
	}

	public void setSell_empId(int sell_empId) {
		this.sell_empId = sell_empId;
	}

	public String getSell_mark() {
		return sell_mark;
	}

	public void setSell_mark(String sell_mark) {
		this.sell_mark = sell_mark;
	}

}
