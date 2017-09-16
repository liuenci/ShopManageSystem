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
/**
 * 1.���ڣ�2017-8-19
 * 2.��Ҫ����
 *  a.��ѯ����δ��˲ɹ�����
 *  b.��ѯ���ж���
 *  c.����id��ѯ������Ϣ
 *  d.����ʱ���ѯ������Ϣ
 * @author �ܳ���
 *
 */
public class PurchaseDao3 extends DBUtil {
	/**
	 * ��ѯ����δ��ˣ�pur_status = 0���ɹ�����
	 * @return
	 */
	public List<PurchaseOrder> query() {
		// ������Ʒ����
		List<PurchaseOrder> result = new ArrayList<PurchaseOrder>();
		String sql = "select * from tb_purchaseOrder where pur_status = 0";
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
	/**
	 * ��ѯ���ж���
	 * @return
	 */
	public List<PurchaseOrder> queryAll() {
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
/**
 * ����id��ѯ����
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
 * ����ʱ���ѯ������Ϣ
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
	 * ������ѯ��������Ϣ��ģ����ѯ�����ݶ�����
	 */
	public List<PurchaseOrder> query(int pur_id) {
		// ����Supply���ϱ���ѧ����Ϣ
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// ��ѯsql���
		String sql = "select * from tb_purchaseOrder where pur_id  like ?";
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
	 * ����id��ѯ����δ���Ķ�����Ϣ
	 * @param pur_id
	 * @return
	 */
	public List<PurchaseOrder> queryByorderId(int pur_id) {
		// ����Supply���ϱ���ѧ����Ϣ
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// ��ѯsql���
		String sql = "select * from tb_purchaseOrder where pur_id  like ? and pur_status = 0";
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
 * ����ʱ���ѯ����δ���Ķ�����Ϣ
 * @param pur_date
 * @return
 */
	public List<PurchaseOrder> query(Date pur_date) {
		// ����Supply���ϱ���ѧ����Ϣ
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// ��ѯsql���
		String sql = "select * from tb_purchaseOrder where pur_date  = ? and pur_status = 0";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
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
	 * ����ʱ���ѯ����δ��˵Ķ�����Ϣ
	 * @param pur_date
	 * @return
	 */
	public List<PurchaseOrder> queryByDate(Date pur_date) {
		// ����PurchaseOrder���ϱ�����Ϣ
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// ��ѯsql���
		String sql = "select * from tb_purchaseOrder where pur_date  = ? and pur_status = 0";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
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
	public List<PurchaseOrder> queryAllByDate(Date pur_date) {
		// ����PurchaseOrder���ϱ�����Ϣ
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// ��ѯsql���
		String sql = "select * from tb_purchaseOrder where pur_date  = ?";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
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
 * ����id��ѯ���ж�����Ϣ
 * @param pur_id
 * @return
 */
	public List<PurchaseOrder> queryById(int pur_id) {
		// �������ϱ�����Ʒ��Ϣ
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// ��ѯsql���
		String sql = "select * from tb_purchaseOrder where pur_id  like ?";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet("%" + pur_id + "%"));
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
	 * ���ݶ�������id��ѯ��Ʒ��Ϣ
	 * @param pDet_purId
	 * @return
	 */
	public List<Good> queryAllGoodByPurOrder(int pDet_purId) {
		// �������ϱ�����Ʒ��Ϣ
		List<Good> lst = new ArrayList<Good>();
		// ��ѯsql���
		String sql = "SELECT pDet_goodId ,goods_name,goods_units ,goods_size,goods_purPrice, pDet_number ,(goods_purPrice*pDet_number) as pDet_goodPrice from tb_purDetail \r\n"
				+ "LEFT JOIN tb_good on tb_purDetail.pDet_goodId = tb_good.goods_id where pDet_purId = ?";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pDet_purId));
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
/**
 * ������Ʒid��ѯ������Ʒ��Ϣ
 * @param goods_id
 * @return
 */
	public List<Good> queryByGoodId(int goods_id) {
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
 * ������Ʒ���Ʋ�ѯ��Ʒ��Ϣ
 * @param goods_name
 * @return
 */
	public List<Good> queryByGoodName(String goods_name) {
		// ����Supply���ϱ���ѧ����Ϣ
		List<Good> lst = new ArrayList<Good>();
		// ��ѯsql���
		String sql = "select * from tb_good where goods_name  like ?";
		String str = "%" + goods_name + "%";
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

	

	public static void main(String[] args) {
		System.out.println(new PurchaseDao3().queryAllGoodByPurOrder(20171001).get(1).getGoods_name());
	}

}
