package cn.model.common;
/**
 * �ֿ��
 * @author LuckyJavaCi
 *
 */
public class Storage {
	// �ֿ���
	private int sto_id;
	// �ֿ�����
	private String name;
	// Ա�����
	private int sto_empId;
	// �ֿ��ַ
	private String sto_address;
	// �ֿⱸע
	private String sto_mark;
	
	
	//����set��get����
	public int getSto_id() {
		return sto_id;
	}
	public void setSto_id(int sto_id) {
		this.sto_id = sto_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSto_empId() {
		return sto_empId;
	}
	public void setSto_empId(int sto_empId) {
		this.sto_empId = sto_empId;
	}
	public String getSto_address() {
		return sto_address;
	}
	public void setSto_address(String sto_address) {
		this.sto_address = sto_address;
	}
	public String getSto_mark() {
		return sto_mark;
	}
	public void setSto_mark(String sto_mark) {
		this.sto_mark = sto_mark;
	}
	
	
	
	
}
