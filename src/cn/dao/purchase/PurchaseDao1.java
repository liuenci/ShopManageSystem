package cn.dao.purchase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Good;
import cn.model.common.PurchaseOrder;
import cn.model.common.Supply;

/**
 * 1.日期：2017-8-18 
 * 2.主要功能 
 * a.新增供应商
   b.修改供应商 
   c.删除供应商 
   d.根据公司名称条件查询供应商 
   e.全部查询供应商信息
 * @author 熊晨晨
 *
 */
public class PurchaseDao1 extends DBUtil {

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
	 * 根据id差
	 * 
	 * @param pur_id
	 * @return
	 * @throws Exception
	 */
	public PurchaseOrder get(int pur_id) throws Exception {
		String sql = " select * from tb_purchaseOrder where pur_id = ? ";
		Map<String, Object> map = query(sql, new ParamSet(pur_id));
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().equals("pur_id")) {
				purchaseOrder.setPur_id((int) e.getValue());
			} else if (e.getKey().equals("pur_supplyId")) {
				purchaseOrder.setPur_supplyId((int) e.getValue());
			} else if (e.getKey().equals("pur_date")) {
				purchaseOrder.setPur_date((Date) e.getValue());
			} else if (e.getKey().equals("pur_pay")) {
				purchaseOrder.setPur_pay((double) e.getValue());
			} else if (e.getKey().equals("pur_empId")) {
				purchaseOrder.setPur_empId((int) e.getValue());
			} else if (e.getKey().equals("pur_status")) {
				purchaseOrder.setPur_status((int) e.getValue());
			} else if (e.getKey().equals("pur_mark")) {
				purchaseOrder.setPur_mark(e.getValue().toString());
			}

		}
		return purchaseOrder;
	}

	/**
	 * 根据日期查
	 * 
	 * @param pur_date
	 * @return
	 * @throws Exception
	 */
	public PurchaseOrder get(Date pur_date) throws Exception {
		String sql = " select * from tb_purchaseOrder where pur_date = ? ";
		Map<String, Object> map = query(sql, new ParamSet(pur_date));
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().equals("pur_id")) {
				purchaseOrder.setPur_id((int) e.getValue());
			} else if (e.getKey().equals("pur_supplyId")) {
				purchaseOrder.setPur_supplyId((int) e.getValue());
			} else if (e.getKey().equals("pur_date")) {
				purchaseOrder.setPur_date((Date) e.getValue());
			} else if (e.getKey().equals("pur_pay")) {
				purchaseOrder.setPur_pay((double) e.getValue());
			} else if (e.getKey().equals("pur_empId")) {
				purchaseOrder.setPur_empId((int) e.getValue());
			} else if (e.getKey().equals("pur_status")) {
				purchaseOrder.setPur_status((int) e.getValue());
			} else if (e.getKey().equals("pur_mark")) {
				purchaseOrder.setPur_mark(e.getValue().toString());
			}

		}
		return purchaseOrder;
	}

	/**
	 * 条件查询供应商信息（模糊查询）根据公司名称
	 */
	public List<PurchaseOrder> query(int pur_id) {
		// 创建Supply集合保存学生信息
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// 查询sql语句
		String sql = "select * from tb_supply where sup_name  like ?";
		String str = "%" + pur_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					purchaseOrder.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("pur_supplyId")) {
					purchaseOrder.setPur_supplyId((int) e.getValue());
				} else if (e.getKey().equals("pur_date")) {
					purchaseOrder.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					purchaseOrder.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("pur_empId")) {
					purchaseOrder.setPur_empId((int) e.getValue());
				} else if (e.getKey().equals("pur_status")) {
					purchaseOrder.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					purchaseOrder.setPur_mark(e.getValue().toString());
				}

			}
			lst.add(purchaseOrder);
		}
		return lst;
	}

	/**
	 * 获取最新id
	 * 
	 * @return
	 */
	public int getMaxId() {
		String sql = "select max(sup_id) as id from tb_supply";
		Map<String, Object> lmp = query(sql);
		return (int) lmp.get("id");
	}

	/**
	 * 查询供应商信息（全部查询）
	 */
	public List<Supply> getSupply() {
		// 创建Supply集合保存学生信息
		List<Supply> lst = new ArrayList<Supply>();
		// 查询sql语句
		String sql = "select sup_name,sup_linkMan,sup_phone,sup_status from tb_supply";
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

	// 查询所有采购订单
	public List<PurchaseOrder> query() {
		// 创建商品集合
		List<PurchaseOrder> result = new ArrayList<PurchaseOrder>();
		String sql = "select * from tb_purchaseOrder";
		List<Map<String, Object>> lmp = queryList(sql);
		for (Map<String, Object> m : lmp) {
			PurchaseOrder purchaseOrder = new PurchaseOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					purchaseOrder.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("pur_supplyId")) {
					purchaseOrder.setPur_supplyId((int) e.getValue());
				} else if (e.getKey().equals("pur_date")) {
					purchaseOrder.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					purchaseOrder.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("pur_empId")) {
					purchaseOrder.setPur_empId((int) e.getValue());
				} else if (e.getKey().equals("pur_status")) {
					purchaseOrder.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					purchaseOrder.setPur_mark(e.getValue().toString());
				}
			}
			// 将商品对象添加到集合中
			result.add(purchaseOrder);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(new PurchaseDao1().query(0).get(1).getPur_pay());
	}

}
