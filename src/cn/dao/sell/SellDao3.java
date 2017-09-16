package cn.dao.sell;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Employee;
import cn.model.common.Good;
import cn.model.common.SellOrder;
import cn.sell.NewSellDetOrder;

public class SellDao3 extends DBUtil {

	// 查询所有销售订单
	public List<SellOrder> query() {
		// 创建商品集合
		List<SellOrder> result = new ArrayList<SellOrder>();
		String sql = "select * from tb_sellOrder";
		List<Map<String, Object>> lmp = queryList(sql);
		for (Map<String, Object> m : lmp) {
			SellOrder sellOrder = new SellOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					sellOrder.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_empId")) {
					sellOrder.setSell_empId((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					sellOrder.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					sellOrder.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					sellOrder.setSell_mark((String) e.getValue());
				}

			}
			// 将销售订单对象添加到集合中
			result.add(sellOrder);

		}
		return result;
	}

	// // 查询所有销售详单表
	// public List<NewSellDetOrder> querySellDetOrder() {
	// // 创建商品集合
	// List<NewSellDetOrder> result = new ArrayList<NewSellDetOrder>();
	// String sql = "select * from tb_sellOrder";
	// List<Map<String, Object>> lmp = queryList(sql);
	// for (Map<String, Object> m : lmp) {
	// SellOrder sellOrder = new SellOrder();
	// for (Entry<String, Object> e : m.entrySet()) {
	// if (e.getKey().equals("sell_id")) {
	// sellOrder.setSell_id((int) e.getValue());
	// } else if (e.getKey().equals("sell_empId")) {
	// sellOrder.setSell_empId((int) e.getValue());
	// } else if (e.getKey().equals("sell_date")) {
	// sellOrder.setSell_date((Date) e.getValue());
	// } else if (e.getKey().equals("sell_profit")) {
	// sellOrder.setSell_profit((double) e.getValue());
	// } else if (e.getKey().equals("sell_mark")) {
	// sellOrder.setSell_mark((String) e.getValue());
	// }
	//
	// }
	// // 将销售订单对象添加到集合中
	// result.add(sellOrder);
	//
	// }
	// return result;
	// }

	/**
	 * 条件查询供应商信息（模糊查询）根据公司名称
	 */
	public List<SellOrder> query(int pur_id) {
		// 创建Supply集合保存学生信息
		List<SellOrder> lst = new ArrayList<SellOrder>();
		// 查询sql语句
		String sql = "select * from tb_sellOrder where sell_id  like ?";
		String str = "%" + pur_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			SellOrder sellOrder = new SellOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					sellOrder.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("pur_empId")) {
					sellOrder.setSell_empId((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					sellOrder.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					sellOrder.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					sellOrder.setSell_mark((String) e.getValue());
				}

			}
			lst.add(sellOrder);
		}
		return lst;
	}

	public List<SellOrder> query(Date pur_date) {
		// 创建Supply集合保存学生信息
		List<SellOrder> lst = new ArrayList<SellOrder>();
		// 查询sql语句
		String sql = "select * from tb_sellOrder where sell_date  = ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			SellOrder sellOrder = new SellOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					sellOrder.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_empId")) {
					sellOrder.setSell_empId((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					sellOrder.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					sellOrder.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					sellOrder.setSell_mark((String) e.getValue());
				}
			}
			lst.add(sellOrder);
		}
		return lst;
	}
	public List<SellOrder> queryByDate(Date date1,Date date2) {
		// 创建Supply集合保存学生信息
		List<SellOrder> lst = new ArrayList<SellOrder>();
		// 查询sql语句
		String sql = "select * from tb_sellOrder where sell_date  between ? and ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(date1,date2));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			SellOrder sellOrder = new SellOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					sellOrder.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_empId")) {
					sellOrder.setSell_empId((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					sellOrder.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					sellOrder.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					sellOrder.setSell_mark((String) e.getValue());
				}else if(e.getKey().equals("sell_status")) {
					sellOrder.setSell_status((int) e.getValue());
				}
			}
			lst.add(sellOrder);
		}
		return lst;
	}

	public List<Good> queryAllGoodBySellOrder(int sDet_sellId) {
		// 创建集合保存商品信息
		List<Good> lst = new ArrayList<Good>();
		// 查询sql语句
		String sql = "SELECT sDet_goodId ,goods_name,goods_units ,goods_size,goods_sellPrice, sDet_number ,(goods_sellPrice*sDet_number) as sDet_goodPrice from tb_sellDetail \r\n"
				+ "LEFT JOIN tb_good on tb_sellDetail.sDet_goodId = tb_good.goods_id where sDet_sellId = ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sDet_sellId));
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
				} else if (e.getKey().equals("sDet_number")) {
					good.setGoods_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
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

	public List<NewSellDetOrder> querySellOrder(int sDet_sellId) {
		// 创建集合保存商品信息
		List<NewSellDetOrder> lst = new ArrayList<NewSellDetOrder>();
		// 查询sql语句
		String sql = "select sDet_id,sDet_sellId,goods_id,goods_name,goods_sellPrice,sDet_number,sDet_goodPrice,sDet_status,sDet_mark from tb_good\r\n" + 
				"LEFT JOIN tb_sellDetail on tb_good.goods_id = tb_sellDetail.sDet_goodId where sDet_sellId like ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sDet_sellId));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			NewSellDetOrder newSellDetOrder = new NewSellDetOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if(e.getKey().equals("sDet_id")) {
					newSellDetOrder.setsDetId((int) e.getValue());
				}else if(e.getKey().equals("sDet_sellId")) {
					newSellDetOrder.setSellId((int)e.getValue());
				}else if(e.getKey().equals("goods_id")) {
					newSellDetOrder.setGoodId((int)e.getValue());
				}else if(e.getKey().equals("goods_name")) {
					newSellDetOrder.setGoodName(e.getValue().toString());
				}else if(e.getKey().equals("goods_sellPrice")) {
					newSellDetOrder.setGoodSellPrice((double)e.getValue());
				}else if(e.getKey().equals("sDet_number")) {
					newSellDetOrder.setsDetNum((int)e.getValue());
				}else if(e.getKey().equals("sDet_goodPrice")) {
					newSellDetOrder.setGoodPrice((double)e.getValue());
				}else if(e.getKey().equals("sDet_status")) {
					newSellDetOrder.setsDetStatus((int)e.getValue());
				}else if(e.getKey().equals("sDet_mark")) {
					newSellDetOrder.setsDetMark(e.getValue().toString());
				}
			}
			lst.add(newSellDetOrder);
		}
		return lst;
	}

	public List<SellOrder> queryById(int sell_id) {
		// 创建集合保存商品信息
		List<SellOrder> lst = new ArrayList<SellOrder>();
		// 查询sql语句
		String sql = "select * from tb_sellOrder where sell_id  like ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet("%" + sell_id + "%"));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
			SellOrder sellOrder = new SellOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					sellOrder.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_empId")) {
					sellOrder.setSell_empId((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					sellOrder.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					sellOrder.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					sellOrder.setSell_mark((String) e.getValue());
				}

			}
			lst.add(sellOrder);
		}
		return lst;
	}

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

	public Employee get(int emp_id) throws Exception {
		String sql = " select emp_name from tb_employee where emp_id = ? ";
		Map<String, Object> map = query(sql, new ParamSet(emp_id));
		Employee employee = new Employee();
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().equals("emp_name")) {
				employee.setEmp_name((String) e.getValue());
			}
		}
		return employee;
	}

	public static void main(String[] args) {
	}

}
