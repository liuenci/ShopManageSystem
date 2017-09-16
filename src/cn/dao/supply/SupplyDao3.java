package cn.dao.supply;

import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Supply;

public class SupplyDao3 extends DBUtil{
	public Supply get(int sup_id){
		String sql = " select sup_name from tb_supply where sup_id = ? ";
		Map<String, Object> map = query(sql, new ParamSet(sup_id));
		Supply supply = new Supply();
		for (Entry<String, Object> e : map.entrySet()) {
			if (e.getKey().equals("sup_name")) {
				supply.setSup_name((String) e.getValue());
			}
		}
		return supply;
	}

	public static void main(String[] args) {
	}

}
