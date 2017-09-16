package cn.dao.purchase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Employee;
import cn.model.common.Supply;
import cn.model.purchase.EmployeePosition;

/**
 * 1.���ڣ�2017-8-15 
 * 2.��Ҫ���� 
 * a.����SQLԱ����� 
 * b.�޸�SQLԱ����� 
 * c.ɾ��SQLԱ����� 
 * d.��ѯSQLԱ�����
 * 
 * @author �ܳ���
 *
 */
public class EmployeeDao extends DBUtil {
	/**
	 * ����Ա����Ϣ
	 * 
	 * @param emp
	 * @return �������id
	 */
	public int addEmployee(EmployeePosition emp) {
		// sql���
		String sql = "insert into tb_employee (emp_password,emp_name,emp_sex,emp_position_id,emp_phone,emp_birthday,emp_salary,emp_status,emp_mark) values(?,?,?,?,?,?,?,?,?) ";
		// ����sql����
		int row = updateExecuted(sql,
				new ParamSet(emp.getEmp_password(), emp.getEmp_name(), emp.getEmp_sex(), emp.getEmp_position_id(),
						emp.getEmp_phone(), emp.getEmp_birthday(), emp.getEmp_salary(), emp.getEmp_status(),
						emp.getEmp_mark()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;
	}

	/**
	 * ����Ա����Ϣ������Ա��������
	 * 
	 * @param sup
	 * @return
	 */
	public int updateEmployee(EmployeePosition emp) {
		// SQL���
		String sql = "update tb_employee set emp_password =?,emp_position_id =?,emp_phone =?,emp_salary=? where emp_id = ?";
		int row = updateExecuted(sql, new ParamSet(emp.getEmp_password(), emp.getEmp_position_id(), emp.getEmp_phone(),
				emp.getEmp_salary(), emp.getEmp_id()));
		return row;
	}

	/**
	 * ɾ��Ա������������Ա�����ƣ�
	 * 
	 * @param sup
	 * @return
	 */
	public int delEmployee(EmployeePosition emp) {
		int row = 0;
		String sql = "update tb_employee set emp_status= 0 where emp_id = ?";
		// ����ɾ������
		row = updateExecuted(sql, new ParamSet(emp.getEmp_id()));
		return row;
	}

	/**
	 * ��ѯԱ����Ϣ��ȫ����ѯ��
	 */
	public List<EmployeePosition> getEmployee() {
		// ����Supply���ϱ���Ա����Ϣ
		List<EmployeePosition> lst = new ArrayList<EmployeePosition>();
		// ��ѯsql���
		String sql = "select emp_id,emp_password,emp_name,emp_sex,(select posi_name	from tb_position where emp_position_id=posi_id) as posi_id,emp_phone,emp_birthday,emp_salary,emp_status,emp_mark from tb_employee";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����Ա�����󱣴�����
			EmployeePosition emp = new EmployeePosition();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("emp_id")) {
					emp.setEmp_id((int) e.getValue());
				} else if (e.getKey().equals("emp_password")) {
					emp.setEmp_password(e.getValue().toString());
				} else if (e.getKey().equals("emp_name")) {
					emp.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("emp_sex")) {
					emp.setEmp_sex((int) e.getValue());
				} else if (e.getKey().equals("posi_id")) {
					emp.setPosi_name(e.getValue().toString());
				} else if (e.getKey().equals("emp_phone")) {
					emp.setEmp_phone(e.getValue().toString());
				} else if (e.getKey().equals("emp_birthday")) {
					emp.setEmp_birthday((Date) e.getValue());
				} else if (e.getKey().equals("emp_salary")) {
					emp.setEmp_salary((int) e.getValue());
				} else if (e.getKey().equals("emp_status")) {
					emp.setEmp_status((int) e.getValue());
				} else if (e.getKey().equals("emp_mark")) {
					emp.setEmp_mark(e.getValue().toString());
				}
			}
			lst.add(emp);
		}
		return lst;
	}

	public List<EmployeePosition> getEmployeeName() {
		// ����Supply���ϱ���Ա����Ϣ
		List<EmployeePosition> lst = new ArrayList<EmployeePosition>();
		// ��ѯsql���
		String sql = "select emp_name from tb_employee";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����Ա�����󱣴�����
			EmployeePosition emp = new EmployeePosition();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("emp_id")) {
					emp.setEmp_id((int) e.getValue());
				} else if (e.getKey().equals("emp_password")) {
					emp.setEmp_password(e.getValue().toString());
				} else if (e.getKey().equals("emp_name")) {
					emp.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("emp_sex")) {
					emp.setEmp_sex((int) e.getValue());
				} else if (e.getKey().equals("posi_id")) {
					emp.setPosi_name(e.getValue().toString());
				} else if (e.getKey().equals("emp_phone")) {
					emp.setEmp_phone(e.getValue().toString());
				} else if (e.getKey().equals("emp_birthday")) {
					emp.setEmp_birthday((Date) e.getValue());
				} else if (e.getKey().equals("emp_salary")) {
					emp.setEmp_salary((int) e.getValue());
				} else if (e.getKey().equals("emp_status")) {
					emp.setEmp_status((int) e.getValue());
				} else if (e.getKey().equals("emp_mark")) {
					emp.setEmp_mark(e.getValue().toString());
				}
			}
			lst.add(emp);
		}
		return lst;
	}

	/**
	 * ����������ѯid
	 * 
	 * @param
	 * @return
	 */
	public int getEmployeename(String emp_name) {
		Employee emp = new Employee();
		// ��ѯsql���
		String sql = "select emp_id from tb_employee where emp_name  = ?";
		// String str = "%" + sup_name + "%";
		Map<String, Object> lmp = query(sql, new ParamSet(emp_name));
		for (Entry<String, Object> em : lmp.entrySet()) {
			if (em.getKey().equals("emp_id")) {
				emp.setEmp_id((int) em.getValue());
			}
		}
		return emp.getEmp_id();
	}

	/**
	 * ������ѯ��Ϣ��ģ����ѯ����������
	 */
	public List<EmployeePosition> getEmployeeName(String emp_name) {
		// ����EmployeePosition���ϱ�����Ϣ
		List<EmployeePosition> lst = new ArrayList<EmployeePosition>();
		// ��ѯsql���
		String sql = "select emp_id,emp_password,emp_name,emp_sex,(select posi_name	from tb_position where emp_position_id=posi_id) as posi_id,emp_phone,emp_birthday,emp_salary,emp_status,emp_mark from tb_employee where emp_name like ?";
		String str = "%" + emp_name + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			EmployeePosition emp = new EmployeePosition();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("emp_id")) {
					emp.setEmp_id((int) e.getValue());
				} else if (e.getKey().equals("emp_password")) {
					emp.setEmp_password(e.getValue().toString());
				} else if (e.getKey().equals("emp_name")) {
					emp.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("emp_sex")) {
					emp.setEmp_sex((int) e.getValue());
				} else if (e.getKey().equals("posi_id")) {
					emp.setPosi_name(e.getValue().toString());
				} else if (e.getKey().equals("emp_phone")) {
					emp.setEmp_phone(e.getValue().toString());
				} else if (e.getKey().equals("emp_birthday")) {
					emp.setEmp_birthday((Date) e.getValue());
				} else if (e.getKey().equals("emp_salary")) {
					emp.setEmp_salary((int) e.getValue());
				} else if (e.getKey().equals("emp_status")) {
					emp.setEmp_status((int) e.getValue());
				} else if (e.getKey().equals("emp_mark")) {
					emp.setEmp_mark(e.getValue().toString());
				}
			}
			lst.add(emp);
		}
		return lst;
	}

	/**
	 * ͨ��ְλ��ѯԱ��
	 */
	public List<Employee> query(int posi_id) {
		// ����Ա���������
		List<Employee> result = new ArrayList<Employee>();
		String sql = "select emp_name from tb_employee where emp_position_id = ?";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(posi_id));
		for (Map<String, Object> m : lmp) {
			// ����Ա������
			Employee employee = new Employee();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("emp_name")) {
					employee.setEmp_name(e.getValue().toString());
				}
			}
			result.add(employee);
		}
		return result;
	}

	/**
	 * ��ѯ���µ�id ֵ
	 * 
	 * @return
	 */
	public int getMaxId() {
		String sql = "select max(emp_id) as id from tb_employee";
		Map<String, Object> lmp = query(sql);
		return (int) lmp.get("id");
	}
	public static void main(String[] args) {

	}
}
