package cn.dao.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Good;

public class GoodDao3 extends DBUtil {
	public int updateGood(String name,String units, String sizeText, double sellPriceText, int stoId, int keepDays,
			int minNumber, String goodMark,int goods_id) {
		// SQL语句
		String sql = "update tb_good set goods_name = ?,goods_units = ?, goods_size = ?, goods_sellPrice = ?, goods_stoId = ?, goods_keepDays = ?,\r\n"
				+ "			goods_minNumber = ?, goods_mark = ? where goods_id = ?";
		int row = updateExecuted(sql, new ParamSet(name,units,sizeText,sellPriceText,stoId,keepDays,
				minNumber, goodMark,goods_id));
		return row;
	}
	public List<Good> QueryIdOrName(Object obj) {
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

}
