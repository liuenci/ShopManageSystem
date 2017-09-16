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

public class StoTransDao extends DBUtil {

	// 查询所有商品
	public List<Good> query() {
		// 创建商品集合
		List<Good> result = new ArrayList<Good>();
		String sql = "select * from tb_good";
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
	 * 新增商品
	 * 
	 * @param args
	 * @throws Exception
	 */
	public int addGood(Good good) {
		// sql语句
		String sql = "insert into tb_good (goods_name,goods_units,goods_size,goods_purPrice,goods_sellPrice,goods_number,goods_stoId,goods_keepDays,goods_minNumber,goods_mark) values(?,?,?,?,?,?,?,?,?,?) ";
		// 调用sql方法
		int row = updateExecuted(sql, new ParamSet(good.getGoods_name(), good.getGoods_units(), good.getGoods_size(),
				good.getGoods_purPrise(), good.getGoods_sellPrice(), good.getGoods_number(), good.getGoods_stoId(),
				good.getGoods_keepDays(), good.getGoods_minNumber(), good.getGoods_mark()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;
	}

	/**
	 * 删除商品方法（根据商品编号）
	 * 
	 * @param pur
	 * @return
	 */
	public int delGood(Good good) {
		int row = 0;
		String sql = "delete from tb_good where goods_id = ?";
		// 调用删除方法
		row = updateExecuted(sql, new ParamSet(good.getGoods_id()));
		return row;
	}
	
	/**
	 * 更新供应商信息（根据供应商名称）
	 * 
	 * @param sup
	 * @return
	 */
	public int updateGood(Good good) {
		// SQL语句
		String sql = "update tb_good set  goods_purPrice =?,goods_number =?, goods_sellPrice =? where goods_id = ?";
		int row = updateExecuted(sql, new ParamSet(good.getGoods_purPrise(), good.getGoods_number(),good.getGoods_sellPrice(),good.getGoods_id()));
		return row;
	}
	
	/**
	 * 查询最新的id 值
	 * 
	 * @return
	 */
	public int getMaxId() {
		String sql = "select max(goods_id) as id from tb_good";
		Map<String, Object> lmp = query(sql);
		return (int) lmp.get("id");
	}

	public static void main(String[] args) throws Exception {
		StoTransDao ad = new StoTransDao();
		
	//	System.out.println(ad.getMaxId());
		// System.out.println(new
		// StoTransDao().query(2).get(0).getGoods_name());

	}

}
