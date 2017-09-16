package cn.dao.purchase;

import java.sql.Date;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.PurPlanDetail;
import cn.model.common.PurchasePlan;

public class PurPlanDao3 extends DBUtil {

	/**
	 * �����ɹ��ƻ�
	 * 
	 * @param args
	 * @throws Exception
	 */
	public int addPurPlanDetail(PurPlanDetail purPlanDetail) {
		// sql���
		String sql = "insert into tb_purPlanDetail (planDet_id,planDet_purId,planDet_goodId,planDet_number,planDet_goodPrice,planDet_mark) values(?,?,?,?,?,?) ";
		// ����sql����
		int row = updateExecuted(sql,
				new ParamSet(purPlanDetail.getPlanDet_id(), purPlanDetail.getPlanDet_purId(),
						purPlanDetail.getPlanDet_goodId(), purPlanDetail.getPlanDet_number(),
						purPlanDetail.getPlanDet_goodPrice(), purPlanDetail.getPriceplanDet_mark()));
		return row;
	}

	/**
	 * ɾ���ɹ��ƻ���
	 * 
	 * @param sup
	 * @return
	 */
	public int delPurPlan(int plan_id) {
		int row = 0;
		String sql = "delete from tb_purchasePlan where plan_id = ?";
		// ����ɾ������
		row = updateExecuted(sql, new ParamSet(plan_id));
		return row;
	}

	/**
	 * ɾ���ɹ��ƻ��굥��
	 * 
	 * @param sup
	 * @return
	 */
	public int delPurPlanDetail(int plan_id) {
		int row = 0;
		String sql = "delete from tb_purPlanDetail where planDet_purId = ?";
		// ����ɾ������
		row = updateExecuted(sql, new ParamSet(plan_id));
		return row;
	}

	/**
	 * ���¹�Ӧ����Ϣ�����ݹ�Ӧ�����ƣ�
	 * 
	 * @param sup
	 * @return
	 */
	public int updatePlanData(Date plan_date, int plan_empId, String plan_mark, int plan_id) {
		// SQL���
		String sql = "update tb_purchasePlan set plan_date =?,plan_empId =?,plan_mark =? where plan_id = ?";
		int row = updateExecuted(sql, new ParamSet(plan_date, plan_empId, plan_mark, plan_id));
		return row;
	}

	public static void main(String[] args) {
	}

}
