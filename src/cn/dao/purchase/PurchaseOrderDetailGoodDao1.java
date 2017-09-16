package cn.dao.purchase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Good;
import cn.model.common.PurDetail;
import cn.model.purchase.GoodPurDetail;
import cn.model.purchase.PurchaseOrderDetailGood;
import cn.model.purchase.PurchaseOrderEmpSup;

public class PurchaseOrderDetailGoodDao1 extends DBUtil {
	/**
	 * 根据详单id，查询
	 */
	public List<PurchaseOrderDetailGood> queryId(int pur_id) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// 查询sql语句
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where  pur_status=2 AND pur_id like ?";
		String str = "%" + pur_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("goods_id")) {
					good.setGoods_id((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					good.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					good.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					good.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("goods_purPrice")) {
					good.setGoods_purPrise((double) e.getValue());
				} else if (e.getKey().equals("pDet_number")) {
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}

	/**
	 * 根据订单详情号查询订单信息
	 */
	public List<PurchaseOrderEmpSup> queryOrdId(int pur_id) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=2 AND pur_id like ? ";
		String str = "%" + pur_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}

	/**
	 * 根据时间查询所有审核未通过（2）的单据
	 */
	public List<PurchaseOrderEmpSup> queryOrdTime(Date pur_date) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=2 AND pur_date like ? ";
//		String str = "%" + pur_date + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}

	/**
	 * 根据详单id，查询
	 */
	public List<PurchaseOrderDetailGood> queryTime(Date pur_date) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// 查询sql语句
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where pur_status=2 AND pur_date = ?";
		// String str = "%" + pur_date + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("goods_id")) {
					good.setGoods_id((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					good.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					good.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					good.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("goods_purPrice")) {
					good.setGoods_purPrise((double) e.getValue());
				} else if (e.getKey().equals("pDet_number")) {
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}

	/**
	 * 更新订单信息（根据订单编号）
	 * 
	 * @param sup
	 * @return
	 */
	/**
	 * 订单审核更新订单表，订单详情表
	 * @param pDet_goodId
	 * @param pur_id
	 * @return
	 */
	public int updateSelGood(int pur_id,int pDet_goodId) {
		// SQL语句
		String sql = "update tb_purchaseOrder,tb_purDetail,tb_good set pur_status =0,pDet_status=2 where tb_purchaseOrder.pur_id=tb_purDetail.pDet_purId and tb_purDetail.pDet_goodId=tb_good.goods_id and pur_id=? and pDet_goodId =? ";
		int row = updateExecuted(sql, new ParamSet(pur_id,pDet_goodId));
		return row;
	}
	/**
	 * 更新订单信息（根据订单编号）
	 * 双击商品退货详情
	 * @param sup
	 * @return
	 */
	public int updateSelGoods(int pur_id,int pDet_goodId) {
		// SQL语句
		String sql = "update tb_purchaseOrder,tb_purDetail,tb_good set pur_status =0,pDet_status=2 where tb_purchaseOrder.pur_id=tb_purDetail.pDet_purId and tb_purDetail.pDet_goodId=tb_good.goods_id and pur_id=? and pDet_goodId =? ";
		int row = updateExecuted(sql, new ParamSet(pur_id,pDet_goodId));
		return row;
	}

	/**
	 * 更新订单详情表信息（根据订单编号）PurchaseOrderDetailGood purgood
	 * purgood.getpDet_goodId(),purgood.getPur_id()
	 * 
	 * @param sup
	 * @return
	 */
	public int delGood(int goodid, int purid) {
		// SQL语句
		String sql = "DELETE from tb_purDetail where pDet_goodId = ? and pDet_purId in (select pur_id from tb_purchaseOrder where pur_status=2 and pur_id=?)";
		int row = updateExecuted(sql, new ParamSet(goodid, purid));
		return row;
	}

	public int updateGood(int goodid) {
		// SQL语句
		String sql = "update tb_purDetail set pDet_status=2 where pDet_purId=?";
		int row = updateExecuted(sql, new ParamSet(goodid));
		return row;
	}
	/**
	 * 根据时间查询所有审核已通过（1）的单据
	 */
	public List<PurchaseOrderEmpSup> queryOrdPassTime(Date pur_date) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=1  AND pur_date like ? ";
//		String str = "%" + pur_date + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * 根据时间查询所有审核已通过(1)详单信息，
	 */
	public List<PurchaseOrderDetailGood> queryPassTime(Date pur_date) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// 查询sql语句
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where pur_status=1 and pDet_status=0 AND pur_date = ?";
		// String str = "%" + pur_date + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("goods_id")) {
					good.setGoods_id((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					good.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					good.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					good.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("goods_purPrice")) {
					good.setGoods_purPrise((double) e.getValue());
				} else if (e.getKey().equals("pDet_number")) {
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * 根据订单详情号查询所有审核通过的（1）订单信息
	 */
	public List<PurchaseOrderEmpSup> queryOrdPassId(int pur_id) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=1  AND pur_id like ? ";
		String str = "%" + pur_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * 根据id查询详单所有审核通过（1）的信息
	 */
	public List<PurchaseOrderDetailGood> queryPassId(int pur_id) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// 查询sql语句
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where  pur_status=1  and pDet_status=0 AND pur_id like ?";
		String str = "%" + pur_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("goods_id")) {
					good.setGoods_id((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					good.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					good.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					good.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("goods_purPrice")) {
					good.setGoods_purPrise((double) e.getValue());
				} else if (e.getKey().equals("pDet_number")) {
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * 退货更新订单表，订单详情表，商品表数据
	 * @param goods_number
	 * @param pur_id
	 * @return
	 */
	public int updatePassGood(int goods_number,int pur_id,int pDet_goodId) {
		// SQL语句
		String sql = "update tb_purchaseOrder,tb_purDetail,tb_good set pur_status =3,pDet_status=2,goods_number=goods_number-? where tb_purchaseOrder.pur_id=tb_purDetail.pDet_purId and tb_purDetail.pDet_goodId=tb_good.goods_id and pur_id=? and pDet_goodId =? ";
		int row = updateExecuted(sql, new ParamSet(goods_number,pur_id,pDet_goodId));
		return row;
	}
	/**
	 * 部分退货
	 * @param goodid
	 * @param purid
	 * @return
	 */
	public int delPassGood(int goods_number, int pDet_goodId,int purid) {
		// SQL语句
		String sql = "update tb_purchaseOrder,tb_purDetail,tb_good set pDet_status=2,goods_number=goods_number-? where tb_purchaseOrder.pur_id=tb_purDetail.pDet_purId and tb_purDetail.pDet_goodId=tb_good.goods_id and  pDet_goodId =? and pur_id=?";
		int row = updateExecuted(sql, new ParamSet(goods_number, pDet_goodId,purid));
		return row;
	}
	public int queryPassIsNull(int pDet_purId){
		String sql = "select SUM(pDet_purId) from tb_purdetail WHERE pDet_purId = ? and pDet_status = 0";
		int row = queryList(sql,new ParamSet(pDet_purId)).size();
		return row;
	}
	public int updateOrderStatus(int pur_id){
		String sql = "update tb_purchaseOrder set pur_status = 3 where pur_id = ?";
		int row = updateExecuted(sql,new ParamSet(pur_id));
		return row;
	}
	/**
	 * 根据订单编号，当部分退货到最后一件时改变订单状态
	 * @param goods_number
	 * @param pur_id
	 * @param pDet_goodId
	 * @return
	 */
	public int updatePassPur(int pur_id) {
		// SQL语句
		String sql = "update tb_purchaseOrder,tb_purDetail,tb_good set pur_status =3 where tb_purchaseOrder.pur_id=tb_purDetail.pDet_purId and tb_purDetail.pDet_goodId=tb_good.goods_id and pur_id=? ";
		int row = updateExecuted(sql, new ParamSet(pur_id));
		return row;
	}
	/**
	 * 根据订单详情号查询已经退货的（3）订单信息
	 */
	public List<PurchaseOrderEmpSup> queryDelPurId(int pur_id) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=3  AND pur_id like ? ";
		String str = "%" + pur_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * 根据id查询所有已经退货（pur_status=3）的商品表中的商品详情（pDet_status=2）的信息
	 */
	public List<PurchaseOrderDetailGood> queryDelGoodId(int pur_id) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// 查询sql语句
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where  pur_status=3  and pDet_status=2 AND pur_id like ?";
		String str = "%" + pur_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("goods_id")) {
					good.setGoods_id((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					good.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					good.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					good.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("goods_purPrice")) {
					good.setGoods_purPrise((double) e.getValue());
				} else if (e.getKey().equals("pDet_number")) {
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * 根据时间查询所有已经退货（3）的单据
	 */
	public List<PurchaseOrderEmpSup> queryDelPurTime(Date pur_date) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=3  AND pur_date = ? ";
//		String str = "%" + pur_date + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * 根据时间查询所有已退货（3）的详单中退货商品的（2）信息，
	 */
	public List<PurchaseOrderDetailGood> queryDelGoodTime(Date pur_date) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// 查询sql语句
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where pur_status=3 and pDet_status=2 AND pur_date = ?";
		// String str = "%" + pur_date + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("goods_id")) {
					good.setGoods_id((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					good.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					good.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					good.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("goods_purPrice")) {
					good.setGoods_purPrise((double) e.getValue());
				} else if (e.getKey().equals("pDet_number")) {
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	public static void main(String[] args) {
//		Date date  =  Date.valueOf("2017-8-26");
//		System.out.println(new PurchaseOrderDetailGoodDao1().queryDelPurTime(date).get(0).getPur_id());
	}
}	

