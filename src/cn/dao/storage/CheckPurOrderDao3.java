package cn.dao.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.storage.NewPurDetail;
import cn.storage.CheckPurOrder;

public class CheckPurOrderDao3 extends DBUtil {
	// 查询订单详情表
	public List<NewPurDetail> query(int pDet_purId) {
		// 创建详情表集合
		List<NewPurDetail> result = new ArrayList<NewPurDetail>();
		String sql = "select pDet_id ,goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,pDet_status,pDet_mark from tb_purDetail \r\n"
				+ "LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId \r\n"
				+ "LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id\r\n"
				+ "where pDet_purId = ? and pDet_status = 1";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pDet_purId));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建订单详情表对象保存数据
			NewPurDetail newPurDetail = new NewPurDetail();
			for (Entry<String, Object> e : m.entrySet()) {
				if(e.getKey().equals("pDet_id")) {
					newPurDetail.setpDet_id((int) e.getValue());
				}else if (e.getKey().equals("goods_id")) {
					newPurDetail.setGoodId((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					newPurDetail.setGoodName(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					newPurDetail.setGoodUnits(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					newPurDetail.setGoodSize(e.getValue().toString());
				} else if (e.getKey().equals("goods_purPrice")) {
					newPurDetail.setGoodPurPrice((double) e.getValue());
				} else if (e.getKey().equals("pDet_number")) {
					newPurDetail.setGoodPurNum((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					newPurDetail.setGoodStatus((int) e.getValue());
				} else if (e.getKey().equals("pDet_mark")) {
					newPurDetail.setGoodPurMark(e.getValue().toString());
				}

			}
			result.add(newPurDetail);
		}
		return result;
	}

	// 根据商品名称查询
	public List<NewPurDetail> queryByName(String pDet_purGoodName) {
		// 创建详情表集合
		List<NewPurDetail> result = new ArrayList<NewPurDetail>();
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,pDet_status,pDet_mark "
				+ "  from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId where goods_name like ?";
		String str = "%" + pDet_purGoodName + "%";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建订单详情表对象保存数据
			NewPurDetail newPurDetail = new NewPurDetail();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("goods_id")) {
					newPurDetail.setGoodId((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					newPurDetail.setGoodName(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					newPurDetail.setGoodUnits(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					newPurDetail.setGoodSize(e.getValue().toString());
				} else if (e.getKey().equals("goods_purPrice")) {
					newPurDetail.setGoodPurPrice((double) e.getValue());
				} else if (e.getKey().equals("pDet_number")) {
					newPurDetail.setGoodPurNum((int) e.getValue());
				} else if (e.getKey().equals("pDet_status")) {
					newPurDetail.setGoodStatus((int) e.getValue());
				} else if (e.getKey().equals("pDet_mark")) {
					newPurDetail.setGoodPurMark(e.getValue().toString());
				}

			}
			result.add(newPurDetail);
		}
		return result;
	}

	public int updatePurOrderIdto1(int purOrderId) {
		// SQL语句
		String sql = "update tb_purchaseOrder set pur_status = 1 where pur_id = ?";
		int row = updateExecuted(sql, new ParamSet(purOrderId));
		return row;
	}
	
	public int updatePurOrderIdto2(int purOrderId,String pur_mark) {
		// SQL语句
		String sql = "update tb_purchaseOrder set pur_status = 2 , pur_mark = ? where pur_id = ?";
		int row = updateExecuted(sql, new ParamSet(pur_mark,purOrderId));
		return row;
	}

	public static void main(String[] args) {
		System.out.println(new CheckPurOrderDao3().updatePurOrderIdto1(20171001));
	}

}
