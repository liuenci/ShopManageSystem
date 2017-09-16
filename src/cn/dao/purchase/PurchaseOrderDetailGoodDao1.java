package cn.dao.purchase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Good;
import cn.model.common.PurDetail;
import cn.model.purchase.GoodPurDetail;
import cn.model.purchase.PurchaseOrderDetailGood;
import cn.model.purchase.PurchaseOrderEmpSup;

public class PurchaseOrderDetailGoodDao1 extends DBUtil {
	/**
	 * �����굥id����ѯ
	 */
	public List<PurchaseOrderDetailGood> queryId(int pur_id) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// ��ѯsql���
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where  pur_status=2 AND pur_id like ?";
		String str = "%" + pur_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
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
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}

	/**
	 * ���ݶ�������Ų�ѯ������Ϣ
	 */
	public List<PurchaseOrderEmpSup> queryOrdId(int pur_id) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=2 AND pur_id like ? ";
		String str = "%" + pur_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}

	/**
	 * ����ʱ���ѯ�������δͨ����2���ĵ���
	 */
	public List<PurchaseOrderEmpSup> queryOrdTime(Date pur_date) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=2 AND pur_date like ? ";
//		String str = "%" + pur_date + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}

	/**
	 * �����굥id����ѯ
	 */
	public List<PurchaseOrderDetailGood> queryTime(Date pur_date) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// ��ѯsql���
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where pur_status=2 AND pur_date = ?";
		// String str = "%" + pur_date + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
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
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}

	/**
	 * ���¶�����Ϣ�����ݶ�����ţ�
	 * 
	 * @param sup
	 * @return
	 */
	/**
	 * ������˸��¶��������������
	 * @param pDet_goodId
	 * @param pur_id
	 * @return
	 */
	public int updateSelGood(int pur_id,int pDet_goodId) {
		// SQL���
		String sql = "update tb_purchaseOrder,tb_purDetail,tb_good set pur_status =0,pDet_status=2 where tb_purchaseOrder.pur_id=tb_purDetail.pDet_purId and tb_purDetail.pDet_goodId=tb_good.goods_id and pur_id=? and pDet_goodId =? ";
		int row = updateExecuted(sql, new ParamSet(pur_id,pDet_goodId));
		return row;
	}
	/**
	 * ���¶�����Ϣ�����ݶ�����ţ�
	 * ˫����Ʒ�˻�����
	 * @param sup
	 * @return
	 */
	public int updateSelGoods(int pur_id,int pDet_goodId) {
		// SQL���
		String sql = "update tb_purchaseOrder,tb_purDetail,tb_good set pur_status =0,pDet_status=2 where tb_purchaseOrder.pur_id=tb_purDetail.pDet_purId and tb_purDetail.pDet_goodId=tb_good.goods_id and pur_id=? and pDet_goodId =? ";
		int row = updateExecuted(sql, new ParamSet(pur_id,pDet_goodId));
		return row;
	}

	/**
	 * ���¶����������Ϣ�����ݶ�����ţ�PurchaseOrderDetailGood purgood
	 * purgood.getpDet_goodId(),purgood.getPur_id()
	 * 
	 * @param sup
	 * @return
	 */
	public int delGood(int goodid, int purid) {
		// SQL���
		String sql = "DELETE from tb_purDetail where pDet_goodId = ? and pDet_purId in (select pur_id from tb_purchaseOrder where pur_status=2 and pur_id=?)";
		int row = updateExecuted(sql, new ParamSet(goodid, purid));
		return row;
	}

	public int updateGood(int goodid) {
		// SQL���
		String sql = "update tb_purDetail set pDet_status=2 where pDet_purId=?";
		int row = updateExecuted(sql, new ParamSet(goodid));
		return row;
	}
	/**
	 * ����ʱ���ѯ���������ͨ����1���ĵ���
	 */
	public List<PurchaseOrderEmpSup> queryOrdPassTime(Date pur_date) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=1  AND pur_date like ? ";
//		String str = "%" + pur_date + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * ����ʱ���ѯ���������ͨ��(1)�굥��Ϣ��
	 */
	public List<PurchaseOrderDetailGood> queryPassTime(Date pur_date) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// ��ѯsql���
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where pur_status=1 and pDet_status=0 AND pur_date = ?";
		// String str = "%" + pur_date + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
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
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * ���ݶ�������Ų�ѯ�������ͨ���ģ�1��������Ϣ
	 */
	public List<PurchaseOrderEmpSup> queryOrdPassId(int pur_id) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=1  AND pur_id like ? ";
		String str = "%" + pur_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * ����id��ѯ�굥�������ͨ����1������Ϣ
	 */
	public List<PurchaseOrderDetailGood> queryPassId(int pur_id) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// ��ѯsql���
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where  pur_status=1  and pDet_status=0 AND pur_id like ?";
		String str = "%" + pur_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
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
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * �˻����¶����������������Ʒ������
	 * @param goods_number
	 * @param pur_id
	 * @return
	 */
	public int updatePassGood(int goods_number,int pur_id,int pDet_goodId) {
		// SQL���
		String sql = "update tb_purchaseOrder,tb_purDetail,tb_good set pur_status =3,pDet_status=2,goods_number=goods_number-? where tb_purchaseOrder.pur_id=tb_purDetail.pDet_purId and tb_purDetail.pDet_goodId=tb_good.goods_id and pur_id=? and pDet_goodId =? ";
		int row = updateExecuted(sql, new ParamSet(goods_number,pur_id,pDet_goodId));
		return row;
	}
	/**
	 * �����˻�
	 * @param goodid
	 * @param purid
	 * @return
	 */
	public int delPassGood(int goods_number, int pDet_goodId,int purid) {
		// SQL���
		String sql = "update tb_purchaseOrder,tb_purDetail,tb_good set pDet_status=2,goods_number=goods_number-? where tb_purchaseOrder.pur_id=tb_purDetail.pDet_purId and tb_purDetail.pDet_goodId=tb_good.goods_id and  pDet_goodId =? and pur_id=?";
		int row = updateExecuted(sql, new ParamSet(goods_number, pDet_goodId,purid));
		return row;
	}
	public int queryPassIsNull(int pDet_purId){
		String sql = "select SUM(pDet_purId) from tb_purdetail WHERE pDet_purId = ? and pDet_status = 0";
		int row = queryList(sql,new ParamSet(pDet_purId)).size();
		return row;
	}
	public int updateOrderStatus(int pur_id){
		String sql = "update tb_purchaseOrder set pur_status = 3 where pur_id = ?";
		int row = updateExecuted(sql,new ParamSet(pur_id));
		return row;
	}
	/**
	 * ���ݶ�����ţ��������˻������һ��ʱ�ı䶩��״̬
	 * @param goods_number
	 * @param pur_id
	 * @param pDet_goodId
	 * @return
	 */
	public int updatePassPur(int pur_id) {
		// SQL���
		String sql = "update tb_purchaseOrder,tb_purDetail,tb_good set pur_status =3 where tb_purchaseOrder.pur_id=tb_purDetail.pDet_purId and tb_purDetail.pDet_goodId=tb_good.goods_id and pur_id=? ";
		int row = updateExecuted(sql, new ParamSet(pur_id));
		return row;
	}
	/**
	 * ���ݶ�������Ų�ѯ�Ѿ��˻��ģ�3��������Ϣ
	 */
	public List<PurchaseOrderEmpSup> queryDelPurId(int pur_id) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=3  AND pur_id like ? ";
		String str = "%" + pur_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * ����id��ѯ�����Ѿ��˻���pur_status=3������Ʒ���е���Ʒ���飨pDet_status=2������Ϣ
	 */
	public List<PurchaseOrderDetailGood> queryDelGoodId(int pur_id) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// ��ѯsql���
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where  pur_status=3  and pDet_status=2 AND pur_id like ?";
		String str = "%" + pur_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
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
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * ����ʱ���ѯ�����Ѿ��˻���3���ĵ���
	 */
	public List<PurchaseOrderEmpSup> queryDelPurTime(Date pur_date) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder where  pur_status=3  AND pur_date = ? ";
//		String str = "%" + pur_date + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					good.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("sup_name")) {
					good.setSup_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_date")) {
					good.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					good.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("emp_name")) {
					good.setEmp_name(e.getValue().toString());
				} else if (e.getKey().equals("pur_status")) {
					good.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	/**
	 * ����ʱ���ѯ�������˻���3�����굥���˻���Ʒ�ģ�2����Ϣ��
	 */
	public List<PurchaseOrderDetailGood> queryDelGoodTime(Date pur_date) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// ��ѯsql���
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id where pur_status=3 and pDet_status=2 AND pur_date = ?";
		// String str = "%" + pur_date + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// �������󱣴�����
			PurchaseOrderDetailGood good = new PurchaseOrderDetailGood();
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
					good.setpDet_number((int) e.getValue());
				} else if (e.getKey().equals("goods_sellPrice")) {
					good.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_stoId")) {
					good.setGoods_stoId((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					good.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_minNumber")) {
					good.setGoods_minNumber((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					good.setpDet_status((int) e.getValue());
				}
			}
			lst.add(good);
		}
		return lst;
	}
	public static void main(String[] args) {
//		Date date  =  Date.valueOf("2017-8-26");
//		System.out.println(new PurchaseOrderDetailGoodDao1().queryDelPurTime(date).get(0).getPur_id());
	}
}	

