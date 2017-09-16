package cn.dao.purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Supply;

public class SupplyDao1 extends DBUtil {
	/**
	 * ������������Ϣ
	 * 
	 * @param sup
	 * @return �������id
	 */
	public int addSupply(Supply sup) {
		// sql���
		String sql = "insert into tb_supply (sup_name,sup_address,sup_linkMan,sup_phone,sup_status,sup_mark) values(?,?,?,?,?,?) ";
		// ����sql����
		int row = updateExecuted(sql, new ParamSet(sup.getSup_name(), sup.getSup_address(), sup.getSup_linkMan(),
				sup.getSup_phone(), sup.getSup_status(), sup.getSup_mark()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;
	}

	/**
	 * ���¹�Ӧ����Ϣ�����ݹ�Ӧ�����ƣ�
	 * 
	 * @param sup
	 * @return
	 */
	public int updateSupply(Supply sup) {
		// SQL���
		String sql = "update tb_supply set sup_address =?,sup_linkMan =?,sup_phone =?,sup_mark =? where sup_id = ?";
		int row = updateExecuted(sql, new ParamSet(sup.getSup_address(), sup.getSup_linkMan(), sup.getSup_phone(),
				sup.getSup_mark(), sup.getSup_id()));
		return row;
	}

	/**
	 * ɾ����Ӧ�̷��������ݹ�Ӧ�����ƣ�
	 * 
	 * @param sup
	 * @return
	 */
	public int delSupply(Supply sup) {
		int row = 0;
		String sql = "update tb_supply set sup_status=1 where sup_id = ?";
		// ����ɾ������
		row = updateExecuted(sql, new ParamSet(sup.getSup_id()));
		return row;
	}

	/**
	 * ��ѯ��Ӧ����Ϣ��ȫ����ѯ��
	 */
	public List<Supply> getSupply() {
		// ����Supply���ϱ���ѧ����Ϣ
		List<Supply> lst = new ArrayList<Supply>();
		// ��ѯsql���
		String sql = "select * from tb_supply";
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

	/**
	 * ���ֲ�ѯ�����ݹ�Ӧ�����Ʋ�ѯ
	 * 
	 * @param sup
	 * @return
	 */
	public Map<String, Object> getSupply(Supply sup) {
		// ��ѯsql���
		String sql = "select * from tb_supply where sup_name = ?";
		Map<String, Object> lmp = query(sql, new ParamSet(sup.getSup_name()));
		for (Entry<String, Object> em : lmp.entrySet()) {
			// System.out.println("id:"+em.getKey()+em.getValue());
			// System.out.println("������"+em.getKey()+em.getValue());
			// System.out.println("�Ա�:" +em.getKey()+em.getValue());
			// System.out.println("��������:"+em.getKey() +em.getValue());
			System.out.println(em.getKey() + "\t" + em.getValue());
		}
		return lmp;
	}

	public int getSupplyname(String sup_name) {
		Supply supply = new Supply();
		// ��ѯsql���
		String sql = "select sup_id from tb_supply where sup_name  = ?";
	//	String str = "%" + sup_name + "%";
		Map<String, Object> lmp = query(sql, new ParamSet(sup_name));
		for (Entry<String, Object> em : lmp.entrySet()) {
			if (em.getKey().equals("sup_id")) {
				supply.setSup_id((int) em.getValue());
			}
		}
		return supply.getSup_id();
	}

	/**
	 * ������ѯ������һ������
	 * 
	 * @param sup_name
	 * @return
	 */
	public Supply getSupplyByName(String sup_name) {
		Supply supply = new Supply();
		// ��ѯsql���
		String sql = "select * from tb_supply where sup_name  like ?";
		String str = "%" + sup_name + "%";
		Map<String, Object> lmp = query(sql, new ParamSet(str));
		for (Entry<String, Object> em : lmp.entrySet()) {
			if (em.getKey().equals("sup_id")) {
				supply.setSup_id((int) em.getValue());
			} else if (em.getKey().equals("sup_name")) {
				supply.setSup_name((String) em.getValue());
			} else if (em.getKey().equals("sup_address")) {
				supply.setSup_address((String) em.getValue());
			} else if (em.getKey().equals("sup_linkMan")) {
				supply.setSup_linkMan((String) em.getValue());
			} else if (em.getKey().equals("sup_phone")) {
				supply.setSup_phone((String) em.getValue());
			} else if (em.getKey().equals("sup_status")) {
				supply.setSup_status((int) em.getValue());
			} else if (em.getKey().equals("sup_mark")) {
				supply.setSup_mark((String) em.getValue());
			}
			// System.out.println(em.getKey() + "\t" + em.getValue());
		}
		return supply;
	}

	/**
	 * ������ѯ��Ӧ����Ϣ��ģ����ѯ�����ݹ�˾����
	 */
	public List<Supply> getSupplyName(String sup_name) {
		// ����Supply���ϱ���ѧ����Ϣ
		List<Supply> lst = new ArrayList<Supply>();
		// ��ѯsql���
		String sql = "select * from tb_supply where sup_name  like ?";
		String str = "%" + sup_name + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
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

	/**
	 * ��ѯ���µ�id ֵ
	 * 
	 * @return
	 */
	public int getMaxId() {
		String sql = "select max(sup_id) as id from tb_supply";
		Map<String, Object> lmp = query(sql);
		return (int) lmp.get("id");
	}

	/**
	 * ģ����ѯ�����ݹ�Ӧ�����Ʋ�ѯ
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SupplyDao1 ad = new SupplyDao1();
		// Supply sup = new Supply("�������޹�Ӧ��", "�й�", "С���N", "1399874939", 0, "");
		// ad.addSupply(sup);
		// Supply supp = new Supply();
		// ad.updateSupply(sup);
		// ad.delSupply(sup);
		// ad.getSupply();
		// ad.getSupply(sup);
		// List<Supply> lst = ad.getSupply();
		// System.out.println(ad.getSupplyName("��Ӧ��").get(1).getSup_name());

		System.out.println(ad.getSupplyname("����Ͱ͹�Ӧ��"));
	}
	// }
	// public void addSupply() throws SQLException{
	// Connection conn = DBUtil.getConnection();
	// String sql = "insert into tb_supply
	// (sup_name,sup_address,sup_linkMan,sup_phone,sup_status,sup_mark)
	// values(?,?,?,?,?,?)";
	// PreparedStatement ptmt = conn.prepareStatement(sql);
	//
	// }
}
