package cn.dao.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Good;
import cn.model.common.PurDetail;
import cn.model.common.Supply;
import cn.model.purchase.GoodPurDetail;
/**
 * 1.����2017-8-17
 * 2.��Ҫ����
 *  a.ȫ����ѯ�����Ʒ��Ϣ
 *  b.����id�Ժ���ѯ��Ʒ��Ϣ
 * @author �ܳ���
 *
 */
public class GoodPurDetailDao extends DBUtil {
	/**
	 * �����Ƿ�����ѯ
	 */
	public List<GoodPurDetail> queryStatus() {
		// ����GoodPurDetail���ϱ�����Ϣ
		List<GoodPurDetail> lst = new ArrayList<GoodPurDetail>();
		// ��ѯsql���
		String sql = "select * from tb_good;";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ������Ӧ�̶��󱣴�����
			GoodPurDetail good = new GoodPurDetail();
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
	 * ����id��ģ����ѯ
	 */
	public List<GoodPurDetail> queryId(String goods_name) {
		// ����Supply���ϱ�����Ϣ
		List<GoodPurDetail> lst = new ArrayList<GoodPurDetail>();
		// ��ѯsql���
		String sql = "select * from tb_good where goods_id IN (SELECT pDet_goodId from tb_purDetail where pDet_status =1) and goods_name  like ?";
		String str = "%" + goods_name + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			GoodPurDetail good = new GoodPurDetail();
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
	

}
