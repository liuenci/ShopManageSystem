package cn.dao.purchase;

import java.sql.Date;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.PurPlanDetail;
import cn.model.common.PurchasePlan;

public class PurPlanDao3 extends DBUtil {

	/**
	 * 新增采购计划
	 * 
	 * @param args
	 * @throws Exception
	 */
	public int addPurPlanDetail(PurPlanDetail purPlanDetail) {
		// sql语句
		String sql = "insert into tb_purPlanDetail (planDet_id,planDet_purId,planDet_goodId,planDet_number,planDet_goodPrice,planDet_mark) values(?,?,?,?,?,?) ";
		// 调用sql方法
		int row = updateExecuted(sql,
				new ParamSet(purPlanDetail.getPlanDet_id(), purPlanDetail.getPlanDet_purId(),
						purPlanDetail.getPlanDet_goodId(), purPlanDetail.getPlanDet_number(),
						purPlanDetail.getPlanDet_goodPrice(), purPlanDetail.getPriceplanDet_mark()));
		return row;
	}

	/**
	 * 删除采购计划表
	 * 
	 * @param sup
	 * @return
	 */
	public int delPurPlan(int plan_id) {
		int row = 0;
		String sql = "delete from tb_purchasePlan where plan_id = ?";
		// 调用删除方法
		row = updateExecuted(sql, new ParamSet(plan_id));
		return row;
	}

	/**
	 * 删除采购计划详单表
	 * 
	 * @param sup
	 * @return
	 */
	public int delPurPlanDetail(int plan_id) {
		int row = 0;
		String sql = "delete from tb_purPlanDetail where planDet_purId = ?";
		// 调用删除方法
		row = updateExecuted(sql, new ParamSet(plan_id));
		return row;
	}

	/**
	 * 更新供应商信息（根据供应商名称）
	 * 
	 * @param sup
	 * @return
	 */
	public int updatePlanData(Date plan_date, int plan_empId, String plan_mark, int plan_id) {
		// SQL语句
		String sql = "update tb_purchasePlan set plan_date =?,plan_empId =?,plan_mark =? where plan_id = ?";
		int row = updateExecuted(sql, new ParamSet(plan_date, plan_empId, plan_mark, plan_id));
		return row;
	}

	public static void main(String[] args) {
	}

}
