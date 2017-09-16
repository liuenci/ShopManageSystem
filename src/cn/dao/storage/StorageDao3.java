package cn.dao.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Storage;
import cn.model.common.Supply;

public class StorageDao3 extends DBUtil {
	// ��ѯ���вֿ�
	public List<Storage> query() {
		// �����ֿ⼯��
		List<Storage> result = new ArrayList<Storage>();
		String sql = "select * from tb_storage";
		List<Map<String, Object>> lmp = queryList(sql);
		for (Map<String, Object> m : lmp) {
			Storage storage = new Storage();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sto_id")) {
					storage.setSto_id((int) e.getValue());
				} else if (e.getKey().equals("sto_name")) {
					storage.setName(e.getValue().toString());
				} else if (e.getKey().equals("sto_empId")) {
					storage.setSto_empId((int) e.getValue());
				} else if (e.getKey().equals("sto_address")) {
					storage.setSto_address(e.getValue().toString());
				} else if (e.getKey().equals("sto_mark")) {
					storage.setSto_mark(e.getValue().toString());
				}
			}
			result.add(storage);
		}
		return result;
	}

	// �����ɹ�����
	/**
	 * ������������Ϣ
	 * 
	 * @param sup
	 * @return �������id
	 */
	public int addPurPlan(Date plan_date, int plan_empId) {
		// sql���
		String sql = "insert into tb_purchaseplan (plan_date,plan_empId)" + " values(?,?) ";
		// ����sql����
		int row = updateExecuted(sql, new ParamSet(plan_date, plan_empId));
		if (row > 0) {
			row = getMaxId();
		}
		return row;
	}

	/**
	 * ��ѯ���µ�id ֵ
	 * 
	 * @return
	 */
	public int getMaxId() {
		String sql = "select max(plan_id) as id from tb_purchaseplan";
		Map<String, Object> lmp = query(sql);
		return (int) lmp.get("id");
	}

	/**
	 * ��ѯ��Ӧ����Ϣ��ȫ����ѯ��
	 */
	public List<Supply> getSupply() {
		// ����Supply���ϱ���ѧ����Ϣ
		List<Supply> lst = new ArrayList<Supply>();
		// ��ѯsql���
		String sql = "select * from tb_supply where sup_status = 0";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ������Ӧ�̶��󱣴�����
			Supply sup = new Supply();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sup_id")) {
					sup.setSup_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					sup.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("sup_address")) {
					sup.setSup_address(e.getValue().toString());
				} else if (e.getKey().equals("sup_linkMan")) {
					sup.setSup_linkMan(e.getValue().toString());
				} else if (e.getKey().equals("sup_phone")) {
					sup.setSup_phone(e.getValue().toString());
				} else if (e.getKey().equals("sup_status")) {
					sup.setSup_status((int) e.getValue());
				} else if (e.getKey().equals("sup_mark")) {
					sup.setSup_mark(e.getValue().toString());
				}
			}
			lst.add(sup);
		}
		return lst;
	}

	public static void main(String[] args) {
	}

}
