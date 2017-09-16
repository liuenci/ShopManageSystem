package cn.dao.purchase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.PurDetail;
import cn.model.common.PurchaseOrder;
import cn.model.purchase.EmployeePosition;
import cn.model.purchase.PurchaseEmpSup;
/**
 * 1.���ڣ�2017-8-20
 * 2.��Ҫ����
 *  a.������������Ϣ
 *  b.���¶�������Ϣ�����ݶ�����ţ�
 *  c.ɾ�������ŷ��������ݶ�����ţ�
 *  d.��ѯ������Ϣ��ȫ����ѯ��
 *  e.��ѯid��󶩵���Ϣ��ȫ����ѯ��
 * @author �ܳ���
 *
 */
public class PurchaseOrderDao extends DBUtil {
	/**
	 * ������������Ϣ
	 * 
	 * @param pur
	 * @return �������id
	 */
	public int addPurchaseOrder(PurchaseOrder pur) {
		// sql���
		String sql = "insert into tb_purchaseOrder (pur_supplyId,pur_date,pur_pay,pur_empId,pur_status,pur_mark) values(?,?,?,?,?,?) ";
		// ����sql����
		int row = updateExecuted(sql, new ParamSet(pur.getPur_supplyId(), pur.getPur_date(), pur.getPur_pay(),
				pur.getPur_empId(), pur.getPur_status(), pur.getPur_mark()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;
	}

	/**
	 * ���¶�������Ϣ�����ݶ�����ţ�
	 * 
	 * @param pur
	 * @return
	 */
	public int updatePurchaseOrder(PurchaseOrder pur) {
		// SQL���
		String sql = "update tb_purchaseOrder set pur_supplyId = ?,pur_date =?,pur_pay =?,pur_empId=?,pur_status=?,pur_mark=?  where pur_id = ?";
		int row = updateExecuted(sql, new ParamSet(pur.getPur_supplyId(), pur.getPur_date(), pur.getPur_pay(),
				pur.getPur_empId(), pur.getPur_status(), pur.getPur_mark(), pur.getPur_id()));
		return row;
	}

	
	/**
	 * ɾ�������ŷ��������ݶ�����ţ�
	 * 
	 * @param pur
	 * @return
	 */
	public int delPurchaseOrder(PurchaseOrder pur) {
		int row = 0;
		String sql = "delete from tb_purchaseOrder where pur_id = ?";
		// ����ɾ������
		row = updateExecuted(sql, new ParamSet(pur.getPur_id()));
		return row;
	}
	
	

	/**
	 * ��ѯ������Ϣ��ȫ����ѯ��
	 */
	public List<PurchaseOrder> getPurchaseOrder() {
		// ����PurchaseOrder���ϱ���ѧ����Ϣ
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// ��ѯsql���
		String sql = "select pur_id,pur_supplyId,pur_date,pur_pay,pur_empId,pur_status,pur_mark from tb_purchaseOrder";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ������Ӧ�̶��󱣴�����
			PurchaseOrder pur = new PurchaseOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					pur.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("pur_supplyId")) {
					pur.setPur_supplyId((int) e.getValue());
				} else if (e.getKey().equals("pur_date")) {
					pur.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					pur.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("pur_empId")) {
					pur.setPur_empId((int) e.getValue());
				} else if (e.getKey().equals("pur_status")) {
					pur.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					pur.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(pur);
		}
		return lst;
	}
	
	
	/**
	 * ��ѯid��󶩵���Ϣ��ȫ����ѯ��
	 */
	public List<PurchaseEmpSup> getPurchaseOrderEmpSup() {
		// ����PurchaseOrder���ϱ���ѧ����Ϣ
		List<PurchaseEmpSup> lst = new ArrayList<PurchaseEmpSup>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId)pur_supplyName,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) AS pur_empName,pur_status,pur_mark from tb_purchaseOrder ORDER BY pur_id DESC limit 1";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ������Ӧ�̶��󱣴�����
			PurchaseEmpSup pur = new PurchaseEmpSup();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					pur.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("pur_supplyName")) {
					pur.setSup_name(e.getValue().toString()); 
				} else if (e.getKey().equals("pur_date")) {
					pur.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					pur.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("pur_empName")) {
					pur.setEmp_name(e.getValue().toString()); 
				} else if (e.getKey().equals("pur_status")) {
					pur.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					pur.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(pur);
		}
		return lst;
	}

	/**
	 * ������ѯ������һ������
	 * 
	 * @param
	 * @return
	 */
	/**
	 * ��ѯid��󶩵���Ϣ��ȫ����ѯ��
	 */
	public List<PurchaseOrder> getPurchaseOrderById() {
		// ����PurchaseOrder���ϱ���ѧ����Ϣ
		List<PurchaseOrder> lst = new ArrayList<PurchaseOrder>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId)pur_supplyName,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) AS pur_empName,pur_status,pur_mark from tb_purchaseOrder ORDER BY pur_id DESC limit 1";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ������Ӧ�̶��󱣴�����
			PurchaseOrder pur = new PurchaseOrder();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("pur_id")) {
					pur.setPur_id((int) e.getValue());
				} else if (e.getKey().equals("pur_supplyName")) {
					pur.setPur_supplyId((int)e.getValue());
				} else if (e.getKey().equals("pur_date")) {
					pur.setPur_date((Date) e.getValue());
				} else if (e.getKey().equals("pur_pay")) {
					pur.setPur_pay((double) e.getValue());
				} else if (e.getKey().equals("pur_empName")) {
					pur.setPur_empId((int) e.getValue());
				} else if (e.getKey().equals("pur_status")) {
					pur.setPur_status((int) e.getValue());
				} else if (e.getKey().equals("pur_mark")) {
					pur.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(pur);
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
	 * �����ɹ������������Ϣ
	 * 
	 * @param 
	 * @return 
	 */
	public int addPurDetail(PurDetail pur) {
		// sql���
		String sql = "insert into tb_purDetail (pDet_purId,pDet_goodId,pDet_number,pDet_goodPrice,pDet_status,pDet_mark) values(?,?,?,?,?,?) ";
		// ����sql����
		int row = updateExecuted(sql, new ParamSet(pur.getpDet_purId(),pur.getpDet_goodId(),pur.getpDet_number(),pur.getpDet_goodPrice(),pur.getpDet_status(),pur.getpDet_mark()));
		return row;
	}

	public static void main(String[] args) {

	}

}
