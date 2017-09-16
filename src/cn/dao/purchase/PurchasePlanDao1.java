package cn.dao.purchase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.purchase.PurchaseOrderDetailGood;
import cn.model.purchase.PurchaseOrderEmpSup;
import cn.model.purchase.PurchasePlanDetGood;

public class PurchasePlanDao1 extends DBUtil{
	/**
	 * �����굥id����ѯ
	 */
	public List<PurchasePlanDetGood> queryId(int plan_id) {
		// ����PurchasePlanDetGood���ϱ�����Ϣ
		List<PurchasePlanDetGood> lst = new ArrayList<PurchasePlanDetGood>();
		// ��ѯsql���
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,planDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber from tb_purPlanDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purPlanDetail.planDet_goodId LEFT JOIN  tb_purchasePlan on tb_purPlanDetail.planDet_purId = tb_purchasePlan.plan_id where  plan_id like ?";
		String str = "%" + plan_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchasePlanDetGood good = new PurchasePlanDetGood();
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
				} else if (e.getKey().equals("planDet_number")) {
					good.setPlanDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				}  else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} 
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * ���ݶ�������Ų�ѯ������Ϣ
	 */
	public List<PurchasePlanDetGood> queryOrdId(int plan_id) {
	// ����PurchasePlanDetGood���ϱ�����Ϣ
			List<PurchasePlanDetGood> lst = new ArrayList<PurchasePlanDetGood>();
			// ��ѯsql���
			String sql = "select plan_id,plan_date,(select emp_name from tb_employee where emp_id = plan_empId) as emp_name,plan_mark from tb_purchasePlan where  plan_id like ? ";
			String str = "%" + plan_id + "%";
			// ��ѯ
			List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
			// �������ݼ���
			for (Map<String, Object> m : lmp) {
				// �������󱣴�����
				PurchasePlanDetGood good = new PurchasePlanDetGood();
				for (Entry<String, Object> e : m.entrySet()) {
					if (e.getKey().equals("plan_id")) {
						good.setPlan_id((int) e.getValue()); 
					} else if (e.getKey().equals("plan_date")) {
						good.setPlan_date((Date) e.getValue());
					} else if (e.getKey().equals("emp_name")) {
						good.setEmp_name(e.getValue().toString());
					} else if (e.getKey().equals("plan_mark")) {
						good.setPlan_mark(e.getValue().toString());
					} 
				}
				lst.add(good);
			}
			return lst;
		}
	
	/**
	 * ����ʱ���ѯ����
	 */
	public List<PurchasePlanDetGood> queryOrdTime(Date plan_date) {
	// ����PurchaseOrderDetailGood���ϱ�����Ϣ
			List<PurchasePlanDetGood> lst = new ArrayList<PurchasePlanDetGood>();
			// ��ѯsql���
			String sql = "select plan_id,plan_date,(select emp_name from tb_employee where emp_id = plan_empId) as emp_name,plan_mark from tb_purchasePlan where  plan_date =? ";
		//String str = "%" + pur_date + "%";
			// ��ѯ
			List<Map<String, Object>> lmp = queryList(sql, new ParamSet(plan_date));
			// �������ݼ���
			for (Map<String, Object> m : lmp) {
				// �������󱣴�����
				PurchasePlanDetGood good = new PurchasePlanDetGood();
				for (Entry<String, Object> e : m.entrySet()) {
						if (e.getKey().equals("plan_id")) {
							good.setPlan_id((int) e.getValue()); 
						} else if (e.getKey().equals("plan_date")) {
							good.setPlan_date((Date) e.getValue());
						} else if (e.getKey().equals("emp_name")) {
							good.setEmp_name(e.getValue().toString());
						} else if (e.getKey().equals("plan_mark")) {
							good.setPlan_mark(e.getValue().toString());
						} 
				}
				lst.add(good);
			}
			return lst;
		}
	/**
	 * ���ݣ���ѯ
	 */
	public List<PurchasePlanDetGood> queryTime(Date plan_date) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchasePlanDetGood> lst = new ArrayList<PurchasePlanDetGood>();
		// ��ѯsql���
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,planDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber from tb_purPlanDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purPlanDetail.planDet_goodId LEFT JOIN  tb_purchasePlan on tb_purPlanDetail.planDet_purId = tb_purchasePlan.plan_id where  plan_date  =?";
	//	String str = "%" + pur_date + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(plan_date));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchasePlanDetGood good = new PurchasePlanDetGood();
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
				} else if (e.getKey().equals("planDet_number")) {
					good.setPlanDet_number((int) e.getValue()); 
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				}  else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} 
			}
			lst.add(good);
		}
		return lst;
	}
	
}
