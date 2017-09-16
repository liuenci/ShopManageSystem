package cn.dao.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Employee;

public class EmpDao3 extends DBUtil {
	// 通过职位查询员工
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

	public Employee get(String emp_name) throws Exception {
		String sql = " select emp_id from tb_employee where emp_name = ? ";
		Map<String, Object> map = query(sql, new ParamSet(emp_name));
		Employee employee = new Employee();
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().equals("emp_id")) {
				employee.setEmp_id((int) e.getValue());
			}
		}
		return employee;
	}

	public Employee get(int emp_id){
		String sql = " select emp_name ,emp_position_id ,emp_password from tb_employee where emp_id = ? ";
		Map<String, Object> map = query(sql, new ParamSet(emp_id));
		Employee employee = new Employee();
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().equals("emp_name")) {
				employee.setEmp_name((String) e.getValue());
			}else if(e.getKey().equals("emp_position_id")) {
				employee.setEmp_position_id((int)e.getValue());
			}else if(e.getKey().equals("emp_password")) {
				employee.setEmp_password(e.getValue().toString());
			}
		}
		return employee;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(new EmpDao3().query(3).get(0).getEmp_name());
	}

}
