package cn.model.sell;
/**
 * 两表联合查询   商品加销售详情
 * @author Administrator
 *
 */
public class Add_asles_Detailed_list {
	private String Detailed_list_goods_name;//名称
	private String Detailed_list_goods_units;//单位
	private double Detailed_list_goods_sellPrice;//单价
	private int Detailed_list_sDet_number;//销售数量
	private double Detailed_list_sDet_goodPrice;//总金额
	public String getDetailed_list_goods_name() {
		return Detailed_list_goods_name;
	}
	public void setDetailed_list_goods_name(String detailed_list_goods_name) {
		Detailed_list_goods_name = detailed_list_goods_name;
	}
	public String getDetailed_list_goods_units() {
		return Detailed_list_goods_units;
	}
	public void setDetailed_list_goods_units(String detailed_list_goods_units) {
		Detailed_list_goods_units = detailed_list_goods_units;
	}
	public double getDetailed_list_goods_sellPrice() {
		return Detailed_list_goods_sellPrice;
	}
	public void setDetailed_list_goods_sellPrice(double detailed_list_goods_sellPrice) {
		Detailed_list_goods_sellPrice = detailed_list_goods_sellPrice;
	}
	public int getDetailed_list_sDet_number() {
		return Detailed_list_sDet_number;
	}
	public void setDetailed_list_sDet_number(int detailed_list_sDet_number) {
		Detailed_list_sDet_number = detailed_list_sDet_number;
	}
	public double getDetailed_list_sDet_goodPrice() {
		return Detailed_list_sDet_goodPrice;
	}
	public void setDetailed_list_sDet_goodPrice(double detailed_list_sDet_goodPrice) {
		Detailed_list_sDet_goodPrice = detailed_list_sDet_goodPrice;
	}
	
}
