package cn.model.common;

import java.sql.Date;

/**
 * 员工表
 * 
 * @author 熊晨晨
 *
 */
public class Employee {
	// 员工编号，登录账号
	private int emp_id;
	// 登录密码
	private String emp_password;
	// 员工姓名
	private String emp_name;
	// 性别 （0：女 1：男）
	private int emp_sex;
	// 员工职务编号,外键
	private int emp_position_id;
	// 员工联系电话
	private String emp_phone;
	// 员工出生日期
	private Date emp_birthday;
	// 员工工资
	private int emp_salary;
	// 员工状态（0:开出 1：在职）
	private int emp_status;
	// 备注
	private String emp_mark;
    
	
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

}
