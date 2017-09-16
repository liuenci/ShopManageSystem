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

	// ��ѯ������Ʒ
	public List<Good> query() {
		// ������Ʒ����
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
			// ����Ʒ������ӵ�������
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
		// ����Supply���ϱ���ѧ����Ϣ
		List<Good> lst = new ArrayList<Good>();
		// ��ѯsql���
		String sql = "select * from tb_good where goods_id  like ?";
		String str = "%" + goods_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ������Ӧ�̶��󱣴�����
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
	 * ������Ʒ
	 * 
	 * @param args
	 * @throws Exception
	 */
	public int addGood(Good good) {
		// sql���
		String sql = "insert into tb_good (goods_name,goods_units,goods_size,goods_purPrice,goods_sellPrice,goods_number,goods_stoId,goods_keepDays,goods_minNumber,goods_mark) values(?,?,?,?,?,?,?,?,?,?) ";
		// ����sql����
		int row = updateExecuted(sql, new ParamSet(good.getGoods_name(), good.getGoods_units(), good.getGoods_size(),
				good.getGoods_purPrise(), good.getGoods_sellPrice(), good.getGoods_number(), good.getGoods_stoId(),
				good.getGoods_keepDays(), good.getGoods_minNumber(), good.getGoods_mark()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;
	}

	/**
	 * ɾ����Ʒ������������Ʒ��ţ�
	 * 
	 * @param pur
	 * @return
	 */
	public int delGood(Good good) {
		int row = 0;
		String sql = "delete from tb_good where goods_id = ?";
		// ����ɾ������
		row = updateExecuted(sql, new ParamSet(good.getGoods_id()));
		return row;
	}
	
	/**
	 * ���¹�Ӧ����Ϣ�����ݹ�Ӧ�����ƣ�
	 * 
	 * @param sup
	 * @return
	 */
	public int updateGood(Good good) {
		// SQL���
		String sql = "update tb_good set  goods_purPrice =?,goods_number =?, goods_sellPrice =? where goods_id = ?";
		int row = updateExecuted(sql, new ParamSet(good.getGoods_purPrise(), good.getGoods_number(),good.getGoods_sellPrice(),good.getGoods_id()));
		return row;
	}
	
	/**
	 * ��ѯ���µ�id ֵ
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
