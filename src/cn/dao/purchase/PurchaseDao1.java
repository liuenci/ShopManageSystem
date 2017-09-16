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

/**
 * 1.���ڣ�2017-8-18 
 * 2.��Ҫ���� 
 * a.������Ӧ��
   b.�޸Ĺ�Ӧ�� 
   c.ɾ����Ӧ�� 
   d.���ݹ�˾����������ѯ��Ӧ�� 
   e.ȫ����ѯ��Ӧ����Ϣ
 * @author �ܳ���
 *
 */
public class PurchaseDao1 extends DBUtil {

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
	 * ����id��
	 * 
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
	 * �������ڲ�
	 * 
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
	 * ������ѯ��Ӧ����Ϣ��ģ����ѯ�����ݹ�˾����
	 */
	public List<PurchaseOrder> query(int pur_id) {
		// ����Supply���ϱ���ѧ����Ϣ
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// ��ѯsql���
		String sql = "select * from tb_supply where sup_name  like ?";
		String str = "%" + pur_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ������Ӧ�̶��󱣴�����
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
	 * ��ȡ����id
	 * 
	 * @return
	 */
	public int getMaxId() {
		String sql = "select max(sup_id) as id from tb_supply";
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
		String sql = "select sup_name,sup_linkMan,sup_phone,sup_status from tb_supply";
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

	// ��ѯ���вɹ�����
	public List<PurchaseOrder> query() {
		// ������Ʒ����
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
			// ����Ʒ������ӵ�������
			result.add(purchaseOrder);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(new PurchaseDao1().query(0).get(1).getPur_pay());
	}

}
