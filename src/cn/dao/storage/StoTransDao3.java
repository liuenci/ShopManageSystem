package cn.dao.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Good;
import cn.model.common.Storage;
import cn.model.common.Supply;

public class StoTransDao3 extends DBUtil {

	// 查询所有商品
	public List<Good> query() {
		// 创建商品集合
		List<Good> result = new ArrayList<Good>();
		String sql = "select * from tb_good ";
		List<Map<String, Object>> lmp = queryList(sql);
		for (Map<String, Object> m : lmp) {
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
			// 将商品对象添加到集合中
			result.add(good);

		}
		return result;
	}
	/**
	 * 根据商品仓库ID查询仓库名称
	 * @return
	 */
	public Storage getName(int sto_id) {
		Storage storage = new Storage();
		String sql = "select * from tb_storage where sto_id = ?";
		Map<String, Object> lmp = query(sql,new ParamSet(sto_id));
		for(Entry<String, Object> m:lmp.entrySet()) {
			if(m.getKey().equals("sto_name")) {
				storage.setName(m.getValue().toString());
			}
		}
		return storage;
	}

	// 查询所有商品
	public List<Good> alarmQuery() {
		// 创建商品集合
		List<Good> result = new ArrayList<Good>();
		String sql = "select * from tb_good where goods_number < goods_minNumber;";
		List<Map<String, Object>> lmp = queryList(sql);
		for (Map<String, Object> m : lmp) {
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
			// 将商品对象添加到集合中
			result.add(good);

		}
		return result;
	}

	public List<Good> QueryIdOrName(Object obj) {
		// 创建商品集合
		List<Good> result = new ArrayList<Good>();
		String sql = "select * from tb_good where goods_number < goods_minNumber and CONCAT(goods_id,goods_name) like ?";
		List<Map<String, Object>> lmp = queryList(sql,new ParamSet("%"+obj+"%"));
		for (Map<String, Object> m : lmp) {
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
			// 将商品对象添加到集合中
			result.add(good);

		}
		return result;
	}
	public List<Good> query(Object obj) {
		// 创建商品集合
		List<Good> result = new ArrayList<Good>();
		String sql = "select * from tb_good where  CONCAT(goods_id,goods_name) like ?";
		List<Map<String, Object>> lmp = queryList(sql,new ParamSet("%"+obj+"%"));
		for (Map<String, Object> m : lmp) {
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
			// 将商品对象添加到集合中
			result.add(good);

		}
		return result;
	}
	

	public Good get(int goods_id) throws Exception {
		String sql = " select * from tb_good where goods_id like ? ";
		Map<String, Object> map = query(sql, new ParamSet(goods_id));
		Good good = new Good();
		for (Entry<String, Object> e : map.entrySet()) {
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
		return good;
	}

	public List<Good> query(int goods_id) {
		// 创建Supply集合保存学生信息
		List<Good> lst = new ArrayList<Good>();
		// 查询sql语句
		String sql = "select * from tb_good where goods_id  like ?";
		String str1 = "%" + goods_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str1));
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

	public List<Good> queryByStoId(int goods_stoId) {
		// 创建Supply集合保存学生信息
		List<Good> lst = new ArrayList<Good>();
		// 查询sql语句
		String sql = "select * from tb_good where goods_stoId  = ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(goods_stoId));
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

	public int updateGood(Good good) {
		// SQL语句
		String sql = "update tb_good set goods_stoId =? where goods_id = ?";
		int row = updateExecuted(sql, new ParamSet(good.getGoods_stoId(), good.getGoods_id()));
		return row;
	}

	public int updateGood(int goods_id, int goods_number, int pDet_id) {
		String sql = "update tb_good ,tb_purdetail set pDet_status=0 , goods_number=goods_number+? WHERE tb_good.goods_id=tb_purdetail.pDet_goodId AND pDet_id=? AND goods_id =?;";
		int row = updateExecuted(sql, new ParamSet(goods_number, pDet_id, goods_id));
		return 0;
	}
	public int updateMinNum(int goods_id, int goods_minNumber) {
		String sql = "update tb_good set goods_minNumber=? where goods_id = ?";
		int row = updateExecuted(sql, new ParamSet(goods_minNumber,goods_id));
		return row;
	}

	public static void main(String[] args) {
//		Object obj = 10013;
		System.out.println(new StoTransDao3().QueryIdOrName(10013).get(0).getGoods_id());
	}

}
