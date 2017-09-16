package cn.model.common;

/**
 * 职务表
 * 
 * @author 熊晨晨
 *
 */
public class Position {
	// 职务编号
	private int posi_id;
	// 部门名称
	private String posi_name;
	// 职务简介
	private String posi_introduction;

	/**
	 * 封装属性
	 */
	public int getPosi_id() {
		return posi_id;
	}

	public void setPosi_id(int posi_id) {
		this.posi_id = posi_id;
	}

	public String getPosi_name() {
		return posi_name;
	}

	public void setPosi_name(String posi_name) {
		this.posi_name = posi_name;
	}

	public String getPosi_introduction() {
		return posi_introduction;
	}

	public void setPosi_introduction(String posi_introduction) {
		this.posi_introduction = posi_introduction;
	}

}
