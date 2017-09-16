package cn.dao.purchase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.PurDetail;
import cn.model.common.PurchaseOrder;
import cn.model.purchase.EmployeePosition;
import cn.model.purchase.PurchaseEmpSup;
/**
 * 1.日期：2017-8-20
 * 2.主要内容
 *  a.新增进货单信息
 *  b.更新订单号信息（根据订单编号）
 *  c.删除订单号方法（根据订单编号）
 *  d.查询订单信息（全部查询）
 *  e.查询id最大订单信息（全部查询）
 * @author 熊晨晨
 *
 */
public class PurchaseOrderDao extends DBUtil {
	/**
	 * 新增进货单信息
	 * 
	 * @param pur
	 * @return 返回最大id
	 */
	public int addPurchaseOrder(PurchaseOrder pur) {
		// sql语句
		String sql = "insert into tb_purchaseOrder (pur_supplyId,pur_date,pur_pay,pur_empId,pur_status,pur_mark) values(?,?,?,?,?,?) ";
		// 调用sql方法
		int row = updateExecuted(sql, new ParamSet(pur.getPur_supplyId(), pur.getPur_date(), pur.getPur_pay(),
				pur.getPur_empId(), pur.getPur_status(), pur.getPur_mark()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;
	}

	/**
	 * 更新订单号信息（根据订单编号）
	 * 
	 * @param pur
	 * @return
	 */
	public int updatePurchaseOrder(PurchaseOrder pur) {
		// SQL语句
		String sql = "update tb_purchaseOrder set pur_supplyId = ?,pur_date =?,pur_pay =?,pur_empId=?,pur_status=?,pur_mark=?  where pur_id = ?";
		int row = updateExecuted(sql, new ParamSet(pur.getPur_supplyId(), pur.getPur_date(), pur.getPur_pay(),
				pur.getPur_empId(), pur.getPur_status(), pur.getPur_mark(), pur.getPur_id()));
		return row;
	}

	
	/**
	 * 删除订单号方法（根据订单编号）
	 * 
	 * @param pur
	 * @return
	 */
	public int delPurchaseOrder(PurchaseOrder pur) {
		int row = 0;
		String sql = "delete from tb_purchaseOrder where pur_id = ?";
		// 调用删除方法
		row = updateExecuted(sql, new ParamSet(pur.getPur_id()));
		return row;
	}
	
	

	/**
	 * 查询订单信息（全部查询）
	 */
	public List<PurchaseOrder> getPurchaseOrder() {
		// 创建PurchaseOrder集合保存学生信息
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// 查询sql语句
		String sql = "select pur_id,pur_supplyId,pur_date,pur_pay,pur_empId,pur_status,pur_mark from tb_purchaseOrder";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			PurchaseOrder pur = new PurchaseOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					pur.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("pur_supplyId")) {
					pur.setPur_supplyId((int) e.getValue());
				} else if (e.getKey().equals("pur_date")) {
					pur.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					pur.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("pur_empId")) {
					pur.setPur_empId((int) e.getValue());
				} else if (e.getKey().equals("pur_status")) {
					pur.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					pur.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(pur);
		}
		return lst;
	}
	
	
	/**
	 * 查询id最大订单信息（全部查询）
	 */
	public List<PurchaseEmpSup> getPurchaseOrderEmpSup() {
		// 创建PurchaseOrder集合保存学生信息
		List<PurchaseEmpSup> lst = new ArrayList<PurchaseEmpSup>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId)pur_supplyName,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) AS pur_empName,pur_status,pur_mark from tb_purchaseOrder ORDER BY pur_id DESC limit 1";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			PurchaseEmpSup pur = new PurchaseEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					pur.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("pur_supplyName")) {
					pur.setSup_name(e.getValue().toString()); 
				} else if (e.getKey().equals("pur_date")) {
					pur.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					pur.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("pur_empName")) {
					pur.setEmp_name(e.getValue().toString()); 
				} else if (e.getKey().equals("pur_status")) {
					pur.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					pur.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(pur);
		}
		return lst;
	}

	/**
	 * 条件查询，返回一条数据
	 * 
	 * @param
	 * @return
	 */
	/**
	 * 查询id最大订单信息（全部查询）
	 */
	public List<PurchaseOrder> getPurchaseOrderById() {
		// 创建PurchaseOrder集合保存学生信息
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId)pur_supplyName,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) AS pur_empName,pur_status,pur_mark from tb_purchaseOrder ORDER BY pur_id DESC limit 1";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			PurchaseOrder pur = new PurchaseOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					pur.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("pur_supplyName")) {
					pur.setPur_supplyId((int)e.getValue());
				} else if (e.getKey().equals("pur_date")) {
					pur.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					pur.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("pur_empName")) {
					pur.setPur_empId((int) e.getValue());
				} else if (e.getKey().equals("pur_status")) {
					pur.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					pur.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(pur);
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
	 * 新增采购订单详情表信息
	 * 
	 * @param 
	 * @return 
	 */
	public int addPurDetail(PurDetail pur) {
		// sql语句
		String sql = "insert into tb_purDetail (pDet_purId,pDet_goodId,pDet_number,pDet_goodPrice,pDet_status,pDet_mark) values(?,?,?,?,?,?) ";
		// 调用sql方法
		int row = updateExecuted(sql, new ParamSet(pur.getpDet_purId(),pur.getpDet_goodId(),pur.getpDet_number(),pur.getpDet_goodPrice(),pur.getpDet_status(),pur.getpDet_mark()));
		return row;
	}

	public static void main(String[] args) {

	}

}
