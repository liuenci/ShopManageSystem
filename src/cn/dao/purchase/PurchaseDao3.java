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
/**
 * 1.日期：2017-8-19
 * 2.主要内容
 *  a.查询所有未审核采购订单
 *  b.查询所有订单
 *  c.根据id查询订单信息
 *  d.根据时间查询订单信息
 * @author 熊晨晨
 *
 */
public class PurchaseDao3 extends DBUtil {
	/**
	 * 查询所有未审核（pur_status = 0）采购订单
	 * @return
	 */
	public List<PurchaseOrder> query() {
		// 创建商品集合
		List<PurchaseOrder> result = new ArrayList<PurchaseOrder>();
		String sql = "select * from tb_purchaseOrder where pur_status = 0";
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
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<PurchaseOrder> queryAll() {
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
/**
 * 根据id查询订单
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
 * 根据时间查询订单信息
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
	 * 条件查询订单表信息（模糊查询）根据订单号
	 */
	public List<PurchaseOrder> query(int pur_id) {
		// 创建Supply集合保存学生信息
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// 查询sql语句
		String sql = "select * from tb_purchaseOrder where pur_id  like ?";
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
	 * 根据id查询所有未入库的订单信息
	 * @param pur_id
	 * @return
	 */
	public List<PurchaseOrder> queryByorderId(int pur_id) {
		// 创建Supply集合保存学生信息
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// 查询sql语句
		String sql = "select * from tb_purchaseOrder where pur_id  like ? and pur_status = 0";
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
 * 根据时间查询所有未入库的订单信息
 * @param pur_date
 * @return
 */
	public List<PurchaseOrder> query(Date pur_date) {
		// 创建Supply集合保存学生信息
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// 查询sql语句
		String sql = "select * from tb_purchaseOrder where pur_date  = ? and pur_status = 0";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
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
	 * 根据时间查询所有未审核的订单信息
	 * @param pur_date
	 * @return
	 */
	public List<PurchaseOrder> queryByDate(Date pur_date) {
		// 创建PurchaseOrder集合保存信息
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// 查询sql语句
		String sql = "select * from tb_purchaseOrder where pur_date  = ? and pur_status = 0";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
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
	public List<PurchaseOrder> queryAllByDate(Date pur_date) {
		// 创建PurchaseOrder集合保存信息
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// 查询sql语句
		String sql = "select * from tb_purchaseOrder where pur_date  = ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
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
 * 根据id查询所有订单信息
 * @param pur_id
 * @return
 */
	public List<PurchaseOrder> queryById(int pur_id) {
		// 创建集合保存商品信息
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// 查询sql语句
		String sql = "select * from tb_purchaseOrder where pur_id  like ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet("%" + pur_id + "%"));
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
	 * 根据订单详情id查询商品信息
	 * @param pDet_purId
	 * @return
	 */
	public List<Good> queryAllGoodByPurOrder(int pDet_purId) {
		// 创建集合保存商品信息
		List<Good> lst = new ArrayList<Good>();
		// 查询sql语句
		String sql = "SELECT pDet_goodId ,goods_name,goods_units ,goods_size,goods_purPrice, pDet_number ,(goods_purPrice*pDet_number) as pDet_goodPrice from tb_purDetail \r\n"
				+ "LEFT JOIN tb_good on tb_purDetail.pDet_goodId = tb_good.goods_id where pDet_purId = ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pDet_purId));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			Good good = new Good();
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
					good.setGoods_number((int) e.getValue());
				} else if (e.getKey().equals("pDet_goodPrice")) {
					good.setGoods_purPrise((double) e.getValue());
				} else if (e.getKey().equals("goods_mark")) {
					good.setGoods_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}
/**
 * 根据商品id查询所有商品信息
 * @param goods_id
 * @return
 */
	public List<Good> queryByGoodId(int goods_id) {
		// 创建Supply集合保存学生信息
		List<Good> lst = new ArrayList<Good>();
		// 查询sql语句
		String sql = "select * from tb_good where goods_id  like ?";
		String str = "%" + goods_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			Good good = new Good();
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
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_number")) {
					good.setGoods_number((int) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("goods_mark")) {
					good.setGoods_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}
/**
 * 根据商品名称查询商品信息
 * @param goods_name
 * @return
 */
	public List<Good> queryByGoodName(String goods_name) {
		// 创建Supply集合保存学生信息
		List<Good> lst = new ArrayList<Good>();
		// 查询sql语句
		String sql = "select * from tb_good where goods_name  like ?";
		String str = "%" + goods_name + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			Good good = new Good();
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
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_number")) {
					good.setGoods_number((int) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("goods_mark")) {
					good.setGoods_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}

	

	public static void main(String[] args) {
		System.out.println(new PurchaseDao3().queryAllGoodByPurOrder(20171001).get(1).getGoods_name());
	}

}
