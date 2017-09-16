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
 * 1.日期：2017-8-15 
 * 2.主要内容 
 * a.新增SQL员工语句 
 * b.修改SQL员工语句 
 * c.删除SQL员工语句 
 * d.查询SQL员工语句
 * 
 * @author 熊晨晨
 *
 */
public class EmployeeDao extends DBUtil {
	/**
	 * 新增员工信息
	 * 
	 * @param emp
	 * @return 返回最大id
	 */
	public int addEmployee(EmployeePosition emp) {
		// sql语句
		String sql = "insert into tb_employee (emp_password,emp_name,emp_sex,emp_position_id,emp_phone,emp_birthday,emp_salary,emp_status,emp_mark) values(?,?,?,?,?,?,?,?,?) ";
		// 调用sql方法
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
	 * 更新员工信息（根据员工姓名）
	 * 
	 * @param sup
	 * @return
	 */
	public int updateEmployee(EmployeePosition emp) {
		// SQL语句
		String sql = "update tb_employee set emp_password =?,emp_position_id =?,emp_phone =?,emp_salary=? where emp_id = ?";
		int row = updateExecuted(sql, new ParamSet(emp.getEmp_password(), emp.getEmp_position_id(), emp.getEmp_phone(),
				emp.getEmp_salary(), emp.getEmp_id()));
		return row;
	}

	/**
	 * 删除员工方法（根据员工名称）
	 * 
	 * @param sup
	 * @return
	 */
	public int delEmployee(EmployeePosition emp) {
		int row = 0;
		String sql = "update tb_employee set emp_status= 0 where emp_id = ?";
		// 调用删除方法
		row = updateExecuted(sql, new ParamSet(emp.getEmp_id()));
		return row;
	}

	/**
	 * 查询员工信息（全部查询）
	 */
	public List<EmployeePosition> getEmployee() {
		// 创建Supply集合保存员工信息
		List<EmployeePosition> lst = new ArrayList<EmployeePosition>();
		// 查询sql语句
		String sql = "select emp_id,emp_password,emp_name,emp_sex,(select posi_name	from tb_position where emp_position_id=posi_id) as posi_id,emp_phone,emp_birthday,emp_salary,emp_status,emp_mark from tb_employee";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建员工对象保存数据
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
		// 创建Supply集合保存员工信息
		List<EmployeePosition> lst = new ArrayList<EmployeePosition>();
		// 查询sql语句
		String sql = "select emp_name from tb_employee";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建员工对象保存数据
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
	 * 根据姓名查询id
	 * 
	 * @param
	 * @return
	 */
	public int getEmployeename(String emp_name) {
		Employee emp = new Employee();
		// 查询sql语句
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
	 * 条件查询信息（模糊查询）根据名称
	 */
	public List<EmployeePosition> getEmployeeName(String emp_name) {
		// 创建EmployeePosition集合保存信息
		List<EmployeePosition> lst = new ArrayList<EmployeePosition>();
		// 查询sql语句
		String sql = "select emp_id,emp_password,emp_name,emp_sex,(select posi_name	from tb_position where emp_position_id=posi_id) as posi_id,emp_phone,emp_birthday,emp_salary,emp_status,emp_mark from tb_employee where emp_name like ?";
		String str = "%" + emp_name + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
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
	 * 通过职位查询员工
	 */
	public List<Employee> query(int posi_id) {
		// 创建员工结果集合
		List<Employee> result = new ArrayList<Employee>();
		String sql = "select emp_name from tb_employee where emp_position_id = ?";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(posi_id));
		for (Map<String, Object> m : lmp) {
			// 创建员工对象
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
	 * 查询最新的id 值
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
