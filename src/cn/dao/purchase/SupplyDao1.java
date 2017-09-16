package cn.dao.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Supply;

public class SupplyDao1 extends DBUtil {
	/**
	 * 新增供货商信息
	 * 
	 * @param sup
	 * @return 返回最大id
	 */
	public int addSupply(Supply sup) {
		// sql语句
		String sql = "insert into tb_supply (sup_name,sup_address,sup_linkMan,sup_phone,sup_status,sup_mark) values(?,?,?,?,?,?) ";
		// 调用sql方法
		int row = updateExecuted(sql, new ParamSet(sup.getSup_name(), sup.getSup_address(), sup.getSup_linkMan(),
				sup.getSup_phone(), sup.getSup_status(), sup.getSup_mark()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;
	}

	/**
	 * 更新供应商信息（根据供应商名称）
	 * 
	 * @param sup
	 * @return
	 */
	public int updateSupply(Supply sup) {
		// SQL语句
		String sql = "update tb_supply set sup_address =?,sup_linkMan =?,sup_phone =?,sup_mark =? where sup_id = ?";
		int row = updateExecuted(sql, new ParamSet(sup.getSup_address(), sup.getSup_linkMan(), sup.getSup_phone(),
				sup.getSup_mark(), sup.getSup_id()));
		return row;
	}

	/**
	 * 删除供应商方法（根据供应商名称）
	 * 
	 * @param sup
	 * @return
	 */
	public int delSupply(Supply sup) {
		int row = 0;
		String sql = "update tb_supply set sup_status=1 where sup_id = ?";
		// 调用删除方法
		row = updateExecuted(sql, new ParamSet(sup.getSup_id()));
		return row;
	}

	/**
	 * 查询供应商信息（全部查询）
	 */
	public List<Supply> getSupply() {
		// 创建Supply集合保存学生信息
		List<Supply> lst = new ArrayList<Supply>();
		// 查询sql语句
		String sql = "select * from tb_supply";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			Supply sup = new Supply();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sup_id")) {
					sup.setSup_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					sup.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("sup_address")) {
					sup.setSup_address(e.getValue().toString());
				} else if (e.getKey().equals("sup_linkMan")) {
					sup.setSup_linkMan(e.getValue().toString());
				} else if (e.getKey().equals("sup_phone")) {
					sup.setSup_phone(e.getValue().toString());
				} else if (e.getKey().equals("sup_status")) {
					sup.setSup_status((int) e.getValue());
				} else if (e.getKey().equals("sup_mark")) {
					sup.setSup_mark(e.getValue().toString());
				}
			}
			lst.add(sup);
		}
		return lst;
	}

	/**
	 * 部分查询，根据供应商名称查询
	 * 
	 * @param sup
	 * @return
	 */
	public Map<String, Object> getSupply(Supply sup) {
		// 查询sql语句
		String sql = "select * from tb_supply where sup_name = ?";
		Map<String, Object> lmp = query(sql, new ParamSet(sup.getSup_name()));
		for (Entry<String, Object> em : lmp.entrySet()) {
			// System.out.println("id:"+em.getKey()+em.getValue());
			// System.out.println("姓名："+em.getKey()+em.getValue());
			// System.out.println("性别:" +em.getKey()+em.getValue());
			// System.out.println("出生日期:"+em.getKey() +em.getValue());
			System.out.println(em.getKey() + "\t" + em.getValue());
		}
		return lmp;
	}

	public int getSupplyname(String sup_name) {
		Supply supply = new Supply();
		// 查询sql语句
		String sql = "select sup_id from tb_supply where sup_name  = ?";
	//	String str = "%" + sup_name + "%";
		Map<String, Object> lmp = query(sql, new ParamSet(sup_name));
		for (Entry<String, Object> em : lmp.entrySet()) {
			if (em.getKey().equals("sup_id")) {
				supply.setSup_id((int) em.getValue());
			}
		}
		return supply.getSup_id();
	}

	/**
	 * 条件查询，返回一条数据
	 * 
	 * @param sup_name
	 * @return
	 */
	public Supply getSupplyByName(String sup_name) {
		Supply supply = new Supply();
		// 查询sql语句
		String sql = "select * from tb_supply where sup_name  like ?";
		String str = "%" + sup_name + "%";
		Map<String, Object> lmp = query(sql, new ParamSet(str));
		for (Entry<String, Object> em : lmp.entrySet()) {
			if (em.getKey().equals("sup_id")) {
				supply.setSup_id((int) em.getValue());
			} else if (em.getKey().equals("sup_name")) {
				supply.setSup_name((String) em.getValue());
			} else if (em.getKey().equals("sup_address")) {
				supply.setSup_address((String) em.getValue());
			} else if (em.getKey().equals("sup_linkMan")) {
				supply.setSup_linkMan((String) em.getValue());
			} else if (em.getKey().equals("sup_phone")) {
				supply.setSup_phone((String) em.getValue());
			} else if (em.getKey().equals("sup_status")) {
				supply.setSup_status((int) em.getValue());
			} else if (em.getKey().equals("sup_mark")) {
				supply.setSup_mark((String) em.getValue());
			}
			// System.out.println(em.getKey() + "\t" + em.getValue());
		}
		return supply;
	}

	/**
	 * 条件查询供应商信息（模糊查询）根据公司名称
	 */
	public List<Supply> getSupplyName(String sup_name) {
		// 创建Supply集合保存学生信息
		List<Supply> lst = new ArrayList<Supply>();
		// 查询sql语句
		String sql = "select * from tb_supply where sup_name  like ?";
		String str = "%" + sup_name + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			Supply sup = new Supply();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sup_id")) {
					sup.setSup_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					sup.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("sup_address")) {
					sup.setSup_address(e.getValue().toString());
				} else if (e.getKey().equals("sup_linkMan")) {
					sup.setSup_linkMan(e.getValue().toString());
				} else if (e.getKey().equals("sup_phone")) {
					sup.setSup_phone(e.getValue().toString());
				} else if (e.getKey().equals("sup_status")) {
					sup.setSup_status((int) e.getValue());
				} else if (e.getKey().equals("sup_mark")) {
					sup.setSup_mark(e.getValue().toString());
				}
			}
			lst.add(sup);
		}
		return lst;
	}

	/**
	 * 查询最新的id 值
	 * 
	 * @return
	 */
	public int getMaxId() {
		String sql = "select max(sup_id) as id from tb_supply";
		Map<String, Object> lmp = query(sql);
		return (int) lmp.get("id");
	}

	/**
	 * 模糊查询，根据供应商名称查询
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SupplyDao1 ad = new SupplyDao1();
		// Supply sup = new Supply("能力有限供应商", "中国", "小升昇", "1399874939", 0, "");
		// ad.addSupply(sup);
		// Supply supp = new Supply();
		// ad.updateSupply(sup);
		// ad.delSupply(sup);
		// ad.getSupply();
		// ad.getSupply(sup);
		// List<Supply> lst = ad.getSupply();
		// System.out.println(ad.getSupplyName("供应商").get(1).getSup_name());

		System.out.println(ad.getSupplyname("阿里巴巴供应商"));
	}
	// }
	// public void addSupply() throws SQLException{
	// Connection conn = DBUtil.getConnection();
	// String sql = "insert into tb_supply
	// (sup_name,sup_address,sup_linkMan,sup_phone,sup_status,sup_mark)
	// values(?,?,?,?,?,?)";
	// PreparedStatement ptmt = conn.prepareStatement(sql);
	//
	// }
}
