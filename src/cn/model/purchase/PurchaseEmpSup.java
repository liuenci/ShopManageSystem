package cn.model.purchase;

import java.sql.Date;

public class PurchaseEmpSup {
	// �������
	private int pur_id;
	// �����̱�ţ����
	private int pur_supplyId;
	// �ɹ�����
	private Date pur_date;
	// ֧���ܽ��
	private double pur_pay;
	// Ա����ţ����
	private int pur_empId;
	// �Ƿ���ˣ�0��δ���1�������ͨ�� 2�����δͨ���˻زɹ�Ա��
	private int pur_status;
	// ��ע
	private String pur_mark;
	// Ա����ţ���¼�˺�
	private int emp_id;
	// ��¼����
	private String emp_password;
	// Ա������
	private String emp_name;
	// �Ա� ��0��Ů 1���У�
	private int emp_sex;
	// Ա��ְ����,���
	private int emp_position_id;
	// Ա����ϵ�绰
	private String emp_phone;
	// Ա����������
	private Date emp_birthday;
	// Ա������
	private int emp_salary;
	// Ա��״̬��0:���� 1����ְ��
	private int emp_status;
	// ��ע
	private String emp_mark;
	// ��Ӧ�̱��
	private int sup_id;
	// ��Ӧ������
	private String sup_name;
	// ��Ӧ�̵�ַ
	private String sup_address;
	// ��Ӧ����ϵ��
	private String sup_linkMan;
	// ��Ӧ����ϵ�绰
	private String sup_phone;
	// ����״̬��0:���ֺ���1�����������
	private int sup_status;
	// ��ע
	private String sup_mark;
	
	

	public PurchaseEmpSup() {
		super();
	}

	public PurchaseEmpSup(int pur_supplyId, Date pur_date, double pur_pay, int pur_empId, int pur_status,
			String pur_mark, String emp_password, String emp_name, int emp_sex, int emp_position_id, String emp_phone,
			Date emp_birthday, int emp_salary, int emp_status, String emp_mark, String sup_name, String sup_address,
			String sup_linkMan, String sup_phone, int sup_status, String sup_mark) {
		super();
		this.pur_supplyId = pur_supplyId;
		this.pur_date = pur_date;
		this.pur_pay = pur_pay;
		this.pur_empId = pur_empId;
		this.pur_status = pur_status;
		this.pur_mark = pur_mark;
		this.emp_password = emp_password;
		this.emp_name = emp_name;
		this.emp_sex = emp_sex;
		this.emp_position_id = emp_position_id;
		this.emp_phone = emp_phone;
		this.emp_birthday = emp_birthday;
		this.emp_salary = emp_salary;
		this.emp_status = emp_status;
		this.emp_mark = emp_mark;
		this.sup_name = sup_name;
		this.sup_address = sup_address;
		this.sup_linkMan = sup_linkMan;
		this.sup_phone = sup_phone;
		this.sup_status = sup_status;
		this.sup_mark = sup_mark;
	}

	public int getPur_id() {
		return pur_id;
	}

	public void setPur_id(int pur_id) {
		this.pur_id = pur_id;
	}

	public int getPur_supplyId() {
		return pur_supplyId;
	}

	public void setPur_supplyId(int pur_supplyId) {
		this.pur_supplyId = pur_supplyId;
	}

	public Date getPur_date() {
		return pur_date;
	}

	public void setPur_date(Date pur_date) {
		this.pur_date = pur_date;
	}

	public double getPur_pay() {
		return pur_pay;
	}

	public void setPur_pay(double pur_pay) {
		this.pur_pay = pur_pay;
	}

	public int getPur_empId() {
		return pur_empId;
	}

	public void setPur_empId(int pur_empId) {
		this.pur_empId = pur_empId;
	}

	public int getPur_status() {
		return pur_status;
	}

	public void setPur_status(int pur_status) {
		this.pur_status = pur_status;
	}

	public String getPur_mark() {
		return pur_mark;
	}

	public void setPur_mark(String pur_mark) {
		this.pur_mark = pur_mark;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public int getEmp_sex() {
		return emp_sex;
	}

	public void setEmp_sex(int emp_sex) {
		this.emp_sex = emp_sex;
	}

	public int getEmp_position_id() {
		return emp_position_id;
	}

	public void setEmp_position_id(int emp_position_id) {
		this.emp_position_id = emp_position_id;
	}

	public String getEmp_phone() {
		return emp_phone;
	}

	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}

	public Date getEmp_birthday() {
		return emp_birthday;
	}

	public void setEmp_birthday(Date emp_birthday) {
		this.emp_birthday = emp_birthday;
	}

	public int getEmp_salary() {
		return emp_salary;
	}

	public void setEmp_salary(int emp_salary) {
		this.emp_salary = emp_salary;
	}

	public int getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(int emp_status) {
		this.emp_status = emp_status;
	}

	public String getEmp_mark() {
		return emp_mark;
	}

	public void setEmp_mark(String emp_mark) {
		this.emp_mark = emp_mark;
	}

	public int getSup_id() {
		return sup_id;
	}

	public void setSup_id(int sup_id) {
		this.sup_id = sup_id;
	}

	public String getSup_name() {
		return sup_name;
	}

	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}

	public String getSup_address() {
		return sup_address;
	}

	public void setSup_address(String sup_address) {
		this.sup_address = sup_address;
	}

	public String getSup_linkMan() {
		return sup_linkMan;
	}

	public void setSup_linkMan(String sup_linkMan) {
		this.sup_linkMan = sup_linkMan;
	}

	public String getSup_phone() {
		return sup_phone;
	}

	public void setSup_phone(String sup_phone) {
		this.sup_phone = sup_phone;
	}

	public int getSup_status() {
		return sup_status;
	}

	public void setSup_status(int sup_status) {
		this.sup_status = sup_status;
	}

	public String getSup_mark() {
		return sup_mark;
	}

	public void setSup_mark(String sup_mark) {
		this.sup_mark = sup_mark;
	}

}
