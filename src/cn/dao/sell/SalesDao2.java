
package cn.dao.sell;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.datebase.base.DBUtil;
import cn.datebase.base.ParamSet;
import cn.model.common.Employee;
import cn.model.common.Good;
import cn.model.common.SellDetail;
import cn.model.common.SellOrder;
import cn.model.common.Storage;
import cn.model.sell.Goods;
import cn.model.sell.PurchaseOrderDetailGood;
import cn.model.sell.PurchaseOrderEmpSup;
import cn.model.sell.RefundOrder;
import cn.model.sell.RefunddetailsOrder;

public class SalesDao2 extends DBUtil {

	// ͨ��ְλ��ѯԱ��
	public List<Employee> query(int posi_id) {
		// ����Ա���������
		List<Employee> result = new ArrayList<Employee>();
		String sql = "select emp_name from tb_employee where emp_position_id = ?";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(posi_id));
		for (Map<String, Object> m : lmp) {
			// ����Ա������
			Employee employee = new Employee();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("emp_name")) {
					employee.setEmp_name(e.getValue().toString());
				}
			}
			result.add(employee);
		}
		return result;
	}

	/**
	 * ��ѯ���µ�id ֵ
	 * 
	 * @return
	 */
	public int getMaxId() {
		String sql = "select max(sell_id) as id from tb_sellorder";
		Map<String, Object> lmp = query(sql);
		return (int) lmp.get("id");
	}

	public List<Goods> geCurrent_inventory_query() {
		List<Goods> lst = new ArrayList<Goods>();
		// ��ѯsql
		String sql = "select *from tb_good";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			Goods g = new Goods();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("goods_id")) {
					g.setGoods_id((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					g.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					g.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					g.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("goods_purPrice")) {
					g.setGoods_purPrise(Double.parseDouble(e.getValue().toString()));
				} else if (e.getKey().equals("goods_sellPrice")) {
					g.setGoods_sellPrice(Double.parseDouble(e.getValue().toString()));
				} else if (e.getKey().equals("goods_number")) {
					g.setGoods_number((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					g.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_mark")) {
					g.setGoods_mark(e.getValue().toString());
				}

			}
			lst.add(g);
		}
		return lst;

	}

	public List<Goods> geCurrent_inventory_query(int goods_id) {
		List<Goods> lst = new ArrayList<Goods>();
		// ��ѯsql���
		String sql = "select * from tb_good where goods_id  like ?";
		String str = "%" + goods_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));

		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			Goods g = new Goods();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("goods_id")) {
					g.setGoods_id((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					g.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					g.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					g.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("goods_purPrice")) {
					g.setGoods_purPrise(Double.parseDouble(e.getValue().toString()));
				} else if (e.getKey().equals("goods_sellPrice")) {
					g.setGoods_sellPrice(Double.parseDouble(e.getValue().toString()));
				} else if (e.getKey().equals("goods_number")) {
					g.setGoods_number((int) e.getValue());
				} else if (e.getKey().equals("goods_keepDays")) {
					g.setGoods_keepDays((int) e.getValue());
				} else if (e.getKey().equals("goods_mark")) {
					g.setGoods_mark(e.getValue().toString());
				}

			}
			lst.add(g);
		}
		return lst;

	}

	public List<Goods> queryByStoId(int goods_stoId) {
		//
		List<Goods> lst = new ArrayList<Goods>();
		// ��ѯsql���
		String sql = "select * from tb_good where goods_stoId  = ?";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(goods_stoId));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ������Ӧ�̶��󱣴�����
			Goods good = new Goods();
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
	 * 1.������۶�����
	 * 
	 * @param bo
	 * @return Ӱ�������
	 */
	public int addtb_sellDetail(SellDetail sellDetail) {
		String sql = "insert into tb_sellDetail(sDet_sellId ,sDet_goodId ,sDet_number "
				+ ",sDet_goodPrice ,sDet_mark,sDet_status) values (?,?,?,?,?,?)";
		// �������ӷ���
		int row = updateExecuted(sql,
				new ParamSet(sellDetail.getsDet_sellId(), sellDetail.getsDet_goodId(), sellDetail.getsDet_number(),
						sellDetail.getsDet_goodPrice(), sellDetail.getsDet_mark(), sellDetail.getsDet_status()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;

	}

	/**
	 * �������۵���Ϣ
	 * 
	 * @param sell
	 * @return �������id
	 */
	public int addSellOrder(SellOrder sell) {
		// sql���
		String sql = "insert into tb_sellOrder (sell_empId,sell_date,sell_profit,sell_status,sell_mark) VALUES(?,?,?,0,?) ";
		// ����sql����
		int row = updateExecuted(sql,
				new ParamSet(sell.getSell_empId(), sell.getSell_date(), sell.getSell_profit(), sell.getSell_mark()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;
	}

	/**
	 * ���¶�����
	 * 
	 * @param sell
	 * @return �������id
	 */
	public int reviseSales(SellOrder sell) {
		// Ա����� ʱ�� �ܽ�� ��ע
		String sql = "update tb_sellOrder set sell_empId =?,sell_date =?,sell_profit =?,sell_mark =? where sell_id = ?";
		int row = updateExecuted(sql, new ParamSet(sell.getSell_empId(), sell.getSell_date(), sell.getSell_profit(),
				sell.getSell_mark(), sell.getSell_id()));

		return row;
	}

	/**
	 * ͨ��id��ѯ����
	 * 
	 * @param goods_number
	 * @return
	 */
	public List<Good> goodsnum(int goods_number) {
		// ����Ա���������
		List<Good> result = new ArrayList<Good>();
		String sql = "select goods_number from tb_good where goods_id = ?";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(goods_number));
		for (Map<String, Object> m : lmp) {
			// ������������
			Good num = new Good();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("goods_number")) {
					num.setGoods_number((int) e.getValue());
				}
			}
			result.add(num);
		}
		return result;
	}

	/**
	 * ͨ����Ʒ��Ų�ѯ�����Ʒ����
	 */
	public Good queryStoNum(int goods_id) {
		Good good = new Good();
		String sql = "select goods_number from tb_good where goods_id = ?";
		Map<String, Object> lmp = query(sql, new ParamSet(goods_id));

		for (Entry<String, Object> e : lmp.entrySet()) {
			if (e.getKey().equals("goods_number")) {
				good.setGoods_number((int) e.getValue());
			}
		}

		return good;
	}

	// ͨ����������Ա��Id
	public Employee queryEmpID(String emp_name) {
		Employee employee = new Employee();
		String sql = "select emp_id from tb_employee where emp_name = ?";
		Map<String, Object> lmp = query(sql, new ParamSet(emp_name));
		for (Entry<String, Object> e : lmp.entrySet()) {
			if (e.getKey().equals("emp_id")) {
				employee.setEmp_id((int) e.getValue());
			}
		}

		return employee;
	}

	/**
	 * ������Ʒ���� ״̬goods_number0�����۳�ȥ ״̬goods_number1���������˻�����
	 * 
	 * @param sell
	 * @return �������id
	 */
	public int update(int goodnumber,int sDetgoodId,int sellid) {
		// Ա����� ʱ�� �ܽ�� ��ע
		String sql = "update tb_sellDetail,tb_good,tb_sellOrder set sDet_status=1,sell_status=1,goods_number =goods_number + ? where tb_sellOrder.sell_id=tb_sellDetail.sDet_sellId and tb_sellDetail.sDet_goodId=tb_good.goods_id AND sDet_goodId= ? and sell_id= ?";
		int row = updateExecuted(sql, new ParamSet(goodnumber,sDetgoodId,sellid));
		return row;
	}

	public int updateGoodNum(int goods_id, int pDet_num) {
		// Ա����� ʱ�� �ܽ�� ��ע
		String sql = "update tb_good set goods_number=goods_number- ? where goods_id= ?";
		int row = updateExecuted(sql, new ParamSet(pDet_num, goods_id));
		return row;
	}

	/**
	 * �鿴���۶���״̬Ϊȫ�� ���ظ�
	 * 
	 * @return
	 */

	public List<RefundOrder> SeeRefundOrderx() {
		List<RefundOrder> lst = new ArrayList<RefundOrder>();
		// ��ѯsql
		String sql = "select distinct sell_id,sell_date,sell_profit,sell_mark from tb_sellOrder LEFT JOIN tb_sellDetail on sDet_sellId=sell_id";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefundOrder g = new RefundOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * �鿴���۶���״̬Ϊ0 ���ظ�
	 * 
	 * @return
	 */

	public List<RefunddetailsOrder> SeeRefundOrder() {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// ��ѯsql
		String sql = "select distinct sell_id,sell_date,sell_profit,sell_mark,emp_name from tb_sellOrder LEFT JOIN tb_employee on emp_id=sell_empId WHERE sell_status=0";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "���۶������","��������","�ͻ�����","�տ���","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				}else if(e.getKey().equals("emp_name")){
					g.setEmp_name(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;
	}
	/**
	 * �鿴���۶���״̬Ϊ0 ���ظ�
	 * 
	 * @return
	 */

	public List<RefunddetailsOrder> viewSalesOrderStatus() {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// ��ѯsql
		String sql = "SELECT DISTINCT tb_sellorder.sell_id,tb_sellorder.sell_date,tb_sellorder.sell_mark,tb_employee.emp_name,tb_sellorder.sell_profit,tb_selldetail.sDet_status FROM tb_sellorder ,tb_selldetail ,tb_employee WHERE tb_employee.emp_id = tb_sellorder.sell_empId AND tb_selldetail.sDet_status = 1 AND tb_selldetail.sDet_sellId = tb_sellorder.sell_id";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "���۶������","��������","�ͻ�����","�տ���","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				}else if(e.getKey().equals("emp_name")){
					g.setEmp_name(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;
	}
	/**
	 * �鿴���۶���״̬Ϊ1 ���ظ�
	 * 
	 * @return
	 */

	public List<SellOrder> SeeRefundOrders() {
		List<SellOrder> lst = new ArrayList<SellOrder>();
		// ��ѯsql
		String sql = "select * from tb_sellOrder";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			SellOrder g = new SellOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				} else if (e.getKey().equals("sell_empId")) {
					g.setSell_empId((int) e.getValue());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	public List<SellOrder> queryOrderById() {
		List<SellOrder> lst = new ArrayList<SellOrder>();
		// ��ѯsql
		String sql = "select * from tb_sellOrder where sell_id like ? and ";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList("%" + sql + "%");
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			SellOrder g = new SellOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				} else if (e.getKey().equals("sell_empId")) {
					g.setSell_empId((int) e.getValue());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ���� �����Ų�ѯ �����۵Ķ�������
	 * 
	 * @return
	 */
	public List<RefunddetailsOrder> SeeRefunddetailsOrder(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// ��ѯsql
		String sql = "select sDet_goodId,goods_name,goods_units,goods_size,goods_sellPrice,sDet_number,sDet_goodPrice,sDet_status from tb_sellDetail LEFT JOIN tb_good on sDet_goodId=goods_id LEFT JOIN tb_sellOrder ON sDet_sellId=sell_id where sDet_status= 0 AND sell_id= ?";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sell_id));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefunddetailsOrder g = new RefunddetailsOrder();
			// ��Ʒ��� ��Ʒ���� ��λ ���� ���� �ܽ�� ����С
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sDet_goodId")) {
					g.setsDet_goodId((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					g.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					g.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_sellPrice")) {
					g.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("sDet_number")) {
					g.setsDet_number((int) e.getValue());
				} else if (e.getKey().equals("sDet_goodPrice")) {
					g.setsDet_goodPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_size")) {
					g.setGoods_size(e.getValue().toString());
				}else if (e.getKey().equals("sDet_status")) {
					g.setsDet_status((int)e.getValue());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ���� �����Ų�ѯ �����۵��˻���������
	 * 
	 * @return
	 */
	public List<RefunddetailsOrder> ordernumberfordetailsof(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// ��ѯsql
		String sql = "select sDet_goodId,goods_name,goods_units,goods_size,sDet_status,goods_sellPrice,sDet_number,sDet_goodPrice from tb_sellDetail LEFT JOIN tb_good on sDet_goodId=goods_id LEFT JOIN tb_sellOrder ON sDet_sellId=sell_id where sDet_status= 1 AND sell_id= ?";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sell_id));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefunddetailsOrder g = new RefunddetailsOrder();
			// ��Ʒ��� ��Ʒ���� ��λ ���� ���� �ܽ�� ����С
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sDet_goodId")) {
					g.setsDet_goodId((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					g.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					g.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_sellPrice")) {
					g.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("sDet_number")) {
					g.setsDet_number((int) e.getValue());
				} else if (e.getKey().equals("sDet_goodPrice")) {
					g.setsDet_goodPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_size")) {
					g.setGoods_size(e.getValue().toString());
				}else if (e.getKey().equals("sDet_status")) {
					g.setsDet_status((int)e.getValue());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ���� �����Ų�ѯ �����۵Ķ������� ��״̬��ʾ
	 * 
	 * @return
	 */
	public List<RefunddetailsOrder> SeeRefunddetailsOrderx(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// ��ѯsql
		String sql = "select sDet_goodId,goods_name,goods_units,sDet_status,goods_size,goods_sellPrice,sDet_number,sDet_goodPrice from tb_sellDetail LEFT JOIN tb_good on sDet_goodId=goods_id LEFT JOIN tb_sellOrder ON sDet_sellId=sell_id where sell_id= ?";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sell_id));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefunddetailsOrder g = new RefunddetailsOrder();
			// ��Ʒ��� ��Ʒ���� ��λ ���� ���� �ܽ�� ����С
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sDet_goodId")) {
					g.setsDet_goodId((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					g.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					g.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_sellPrice")) {
					g.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("sDet_number")) {
					g.setsDet_number((int) e.getValue());
				} else if (e.getKey().equals("sDet_goodPrice")) {
					g.setsDet_goodPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_size")) {
					g.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("sDet_status")) {
					g.setsDet_status((int) e.getValue());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ���� �����Ų�ѯ �����۵Ķ������� zt1
	 * 
	 * @return
	 */
	public List<RefunddetailsOrder> SeeRefunddetailsOrders(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// ��ѯsql
		String sql = "select sDet_goodId,goods_name,goods_units,goods_size,goods_sellPrice,sDet_number,sDet_goodPrice from tb_sellDetail LEFT JOIN tb_good on sDet_goodId=goods_id LEFT JOIN tb_sellOrder ON sDet_sellId=sell_id where sDet_status= 1 AND sell_id= ?";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sell_id));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefunddetailsOrder g = new RefunddetailsOrder();
			// ��Ʒ��� ��Ʒ���� ��λ ���� ���� �ܽ�� ����С
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sDet_goodId")) {
					g.setsDet_goodId((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					g.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					g.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_sellPrice")) {
					g.setGoods_sellPrice((double) e.getValue());
				} else if (e.getKey().equals("sDet_number")) {
					g.setsDet_number((int) e.getValue());
				} else if (e.getKey().equals("sDet_goodPrice")) {
					g.setsDet_goodPrice((double) e.getValue());
				} else if (e.getKey().equals("goods_size")) {
					g.setGoods_size(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ���Ҹ���ID���Ҷ���
	 * 
	 * @param goods_id
	 * @return
	 */
	public List<RefunddetailsOrder> To_find_the(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// ��ѯsql���
		String sql = "SELECT DISTINCT tb_employee.emp_name,tb_sellorder.sell_id,tb_sellorder.sell_date,tb_sellorder.sell_profit,tb_sellorder.sell_mark FROM tb_employee ,tb_sellorder,tb_selldetail WHERE tb_employee.emp_id = tb_sellorder.sell_empId AND tb_sellorder.sell_id = tb_selldetail.sDet_sellId AND tb_sellorder.sell_status = 0 and sell_id like ?";
		String str = "%" + sell_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				}else if(e.getKey().equals("emp_name")){
					g.setEmp_name(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ���Ҹ���ID���Ҷ���zt1
	 * 
	 * @param goods_id
	 * @return
	 */
	public List<RefunddetailsOrder> findorderaccordingtoID(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// ��ѯsql���
		String sql = "SELECT DISTINCT tb_employee.emp_name,tb_sellorder.sell_id,tb_sellorder.sell_date,tb_sellorder.sell_profit,tb_sellorder.sell_mark FROM tb_employee ,tb_sellorder,tb_selldetail WHERE tb_employee.emp_id = tb_sellorder.sell_empId AND tb_sellorder.sell_id = tb_selldetail.sDet_sellId AND sDet_status= 1 and sell_id like ?";
		String str = "%" + sell_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				}else if(e.getKey().equals("emp_name")){
					g.setEmp_name(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;
	}
	/**
	 * ���Ҹ���ID���Ҷ��� zt1
	 * 
	 * @param goods_id
	 * @return
	 */
	public List<RefundOrder> To_find_thes(int sell_id) {
		List<RefundOrder> lst = new ArrayList<RefundOrder>();
		// ��ѯsql���
		String sql = "select * from tb_sellOrder LEFT JOIN tb_sellDetail ON sDet_sellId=sell_id where sDet_status= 1 OR sell_date like ?";
		String str = "%" + sell_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefundOrder g = new RefundOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ������ ���ݶ����� ��Ʒ��� �޸�״̬���޸�����
	 * 
	 * @param state
	 * @return
	 */
	public int updetails(int number, int goodId, int sellid) {
		String sql = "update tb_sellDetail,tb_good,tb_sellOrder set sDet_status=1,goods_number =goods_number + ? where tb_sellOrder.sell_id=tb_sellDetail.sDet_sellId and tb_sellDetail.sDet_goodId=tb_good.goods_id and sDet_goodId= ? AND sell_id= ?";
		int row = updateExecuted(sql, new ParamSet(number, goodId, sellid));
		return row;
	}

	/**
	 * ȫ���˻� ���ݶ������޸�״̬
	 * 
	 * @param state
	 * @return
	 */
	public int upwhole(SellDetail state) {
		String sql = "update tb_sellDetail set sDet_status=1 where sDet_sellId= ?";
		int row = updateExecuted(sql, new ParamSet(state.getsDet_sellId()));
		return row;

	}

	/**
	 * ����ʱ���ѯ���� ztquanb
	 */
	public List<RefundOrder> stafftimex(Date sell_date) {
		List<RefundOrder> lst = new ArrayList<RefundOrder>();
		String sql = "select distinct sell_id,sell_date,sell_profit,sell_mark from tb_sellOrder LEFT JOIN tb_sellDetail ON sDet_sellId=sell_id where sell_date like ?";
		String str = "%" + sell_date + "%";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefundOrder g = new RefundOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ����ʱ���ѯ���� zt0
	 */
	public List<RefunddetailsOrder> stafftime(Date sell_date) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		String sql = "SELECT DISTINCT tb_employee.emp_name,tb_sellorder.sell_id,tb_sellorder.sell_date,tb_sellorder.sell_profit,tb_sellorder.sell_mark FROM tb_employee ,tb_sellorder,tb_selldetail WHERE tb_employee.emp_id = tb_sellorder.sell_empId AND tb_sellorder.sell_id = tb_selldetail.sDet_sellId AND tb_sellorder.sell_status = 0 and sell_date like ?";
		String str = "%" + sell_date + "%";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				}else if(e.getKey().equals("emp_name")){
					g.setEmp_name(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;
	}
	/**
	 * ����ʱ���ѯ���� zt0
	 */
	public List<RefunddetailsOrder> checkthedocumentsaccordingtothetime(Date sell_date) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		String sql = "SELECT DISTINCT tb_employee.emp_name,tb_sellorder.sell_id,tb_sellorder.sell_date,tb_sellorder.sell_profit,tb_sellorder.sell_mark FROM tb_employee ,tb_sellorder,tb_selldetail WHERE tb_employee.emp_id = tb_sellorder.sell_empId AND tb_sellorder.sell_id = tb_selldetail.sDet_sellId AND sDet_status= 1 and sell_date like ?";
		String str = "%" + sell_date + "%";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				}else if(e.getKey().equals("emp_name")){
					g.setEmp_name(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ����ʱ���ѯ���� zt 1
	 * 
	 * @param sell_date
	 * @return
	 */
	public List<RefundOrder> querySellOrderByDate(Date sell_date) {
		List<RefundOrder> lst = new ArrayList<RefundOrder>();
		String sql = "select * from tb_sellOrder where sell_date = ?";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sell_date));
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefundOrder g = new RefundOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				} else if (e.getKey().equals("sell_empId")) {
					g.setSell_empId((int) e.getValue());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ɾ�����ݶ������
	 * 
	 * @return
	 */
	public int delete(RefundOrder de) {
		int row = 0;
		String sql = "delete from tb_sellOrder where sell_id= ?";
		row = updateExecuted(sql, new ParamSet(de.getSell_id()));
		return row;

	}

	/**
	 * ����������ID
	 * 
	 * @param emp_name
	 * @return
	 */
	public List<Employee> addEmployee(String emp_name) {
		List<Employee> lst = new ArrayList<Employee>();
		// ��ѯsql
		String sql = "select emp_position_id from tb_employee where emp_name = ?";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(emp_name));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			Employee g = new Employee();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("emp_position_id")) {
					g.setEmp_position_id((int) e.getValue());
				}
			}
			lst.add(g);
		}
		return lst;
	}

	/**
	 * ���ݶ�������Ų�ѯ������Ϣ //
	 */
	// public List<PurchaseOrderEmpSup> queryOrdId(int pur_id) {
	// // ����PurchaseOrderDetailGood���ϱ�����Ϣ
	// List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
	// // ��ѯsql���
	// String sql = "select pur_id,(select sup_name from tb_supply where sup_id
	// = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from
	// tb_employee where emp_id = pur_empId) as emp_name,pur_mark from
	// tb_purchaseorder where pur_id like ? ";
	// String str = "%" + pur_id + "%";
	// // ��ѯ
	// List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
	// // �������ݼ���
	// for (Map<String, Object> m : lmp) {
	// // �������󱣴�����
	// PurchaseOrderEmpSup good = new PurchaseOrderEmpSup();
	// for (Entry<String, Object> e : m.entrySet()) {
	// if (e.getKey().equals("pur_id")) {
	// good.setPur_id((int) e.getValue());
	// } else if (e.getKey().equals("sup_name")) {
	// good.setSup_name(e.getValue().toString());
	// } else if (e.getKey().equals("pur_date")) {
	// good.setPur_date((Date) e.getValue());
	// } else if (e.getKey().equals("pur_pay")) {
	// good.setPur_pay((double) e.getValue());
	// } else if (e.getKey().equals("emp_name")) {
	// good.setEmp_name(e.getValue().toString());
	// } else if (e.getKey().equals("pur_mark")) {
	// good.setPur_mark(e.getValue().toString());
	// }
	// }
	// lst.add(good);
	// }
	// return lst;
	// }

	/**
	 * ȫ����ѯ
	 */
	public List<PurchaseOrderEmpSup> queryOrdTime() {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder ";

		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
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
	 * ����ID
	 * 
	 * @param pur_id
	 * @return
	 */
	public List<PurchaseOrderEmpSup> queryOrdTime(int pur_id) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder WHERE pur_id like ?";
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
	 * �����굥id����ѯ
	 */
	public List<PurchaseOrderDetailGood> queryTime(int pur_id) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// ��ѯsql���
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id WHERE pur_id like ?";
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
	 * ����ʱ���ѯ����
	 */
	public List<PurchaseOrderEmpSup> queryOrdTime(Date pur_date) {
		// ����PurchaseOrderDetailGood���ϱ�����Ϣ
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// ��ѯsql���
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_mark from tb_purchaseorder where  pur_date = ? ";
		// String str = "%" + pur_date + "%";
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
				} else if (e.getKey().equals("pur_mark")) {
					good.setPur_mark(e.getValue().toString());
				}
			}
			lst.add(good);
		}
		return lst;
	}

	/**
	 * ��ȡ�ֿ�����Inventory_query
	 * 
	 * @return
	 */
	public List<Storage> Obtain_Library_information() {

		// ����studentBean�������ڱ���ֿ���
		List<Storage> lst = new ArrayList<Storage>();
		// ��ѯsql
		String sql = "select sto_name FROM tb_storage";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			Storage g = new Storage();
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sto_name")) {
					g.setName(e.getValue().toString());
				}
			}
			lst.add(g);
		}
		return lst;

	}

	/**
	 * ����Ʒ��Ų�ѯ��Ϣ
	 */
	public List<Good> queryGoods(int goods_id) {
		// ����studentBean�������ڱ�����Ʒ�嵥����Ϣ
		List<Good> lst = new ArrayList<Good>();
		// ��ѯsql
		String sql = "select goods_id,goods_name,goods_units,goods_size,goods_sellPrice,goods_number from tb_good WHERE goods_id like ?";
		// ��ѯ
		String str = "%" + goods_id + "%";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			Good g = new Good();
			for (Entry<String, Object> e : m.entrySet()) {
				// ��
				if (e.getKey().equals("goods_id")) {
					g.setGoods_id((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					g.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					g.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					g.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("goods_sellPrice")) {
					g.setGoods_sellPrice(Double.parseDouble(e.getValue().toString()));
				} else if (e.getKey().equals("goods_number")) {
					g.setGoods_number(((int) e.getValue()));
				}
			}
			lst.add(g);
		}
		return lst;

	}

	/**
	 * ��ѯ��Ʒ�嵥 Choose_goods
	 * 
	 * @return
	 */
	public List<Good> queryGood() {
		// ����studentBean�������ڱ�����Ʒ�嵥����Ϣ
		List<Good> lst = new ArrayList<Good>();
		// ��ѯsql
		String sql = "select goods_id,goods_name,goods_units,goods_size,goods_sellPrice,goods_number from tb_good";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql);
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			Good g = new Good();
			for (Entry<String, Object> e : m.entrySet()) {
				// ��
				if (e.getKey().equals("goods_id")) {
					g.setGoods_id((int) e.getValue());
				} else if (e.getKey().equals("goods_name")) {
					g.setGoods_name(e.getValue().toString());
				} else if (e.getKey().equals("goods_units")) {
					g.setGoods_units(e.getValue().toString());
				} else if (e.getKey().equals("goods_size")) {
					g.setGoods_size(e.getValue().toString());
				} else if (e.getKey().equals("goods_sellPrice")) {
					g.setGoods_sellPrice(Double.parseDouble(e.getValue().toString()));
				} else if (e.getKey().equals("goods_number")) {
					g.setGoods_number(((int) e.getValue()));
				}
			}
			lst.add(g);
		}
		return lst;

	}

	/**
	 * ���Ҹ���ID���Ҷ���ȫ��״̬ Commodity_management
	 * 
	 * @param goods_id
	 * @return
	 */
	public List<RefundOrder> To_find_thex(int sell_id) {
		List<RefundOrder> lst = new ArrayList<RefundOrder>();
		// ��ѯsql���
		String sql = "select DISTINCT sell_id,sell_date,sell_profit,sDet_status,sell_mark from tb_sellOrder LEFT JOIN tb_sellDetail on sDet_sellId=sell_id where sell_id like ?";
		String str = "%" + sell_id + "%";
		// ��ѯ
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// �������ݼ���
		for (Map<String, Object> m : lmp) {
			// ����һ����Ʒ�嵥�����ڱ���map�е�����
			RefundOrder g = new RefundOrder();
			// "���۶������","��������","�ͻ�����","�տ���","��������","������","��ע"
			for (Entry<String, Object> e : m.entrySet()) {
				if (e.getKey().equals("sell_id")) {
					g.setSell_id((int) e.getValue());
				} else if (e.getKey().equals("sell_date")) {
					g.setSell_date((Date) e.getValue());
				} else if (e.getKey().equals("sell_profit")) {
					g.setSell_profit((double) e.getValue());
				} else if (e.getKey().equals("sell_mark")) {
					g.setSell_mark(e.getValue().toString());
				} else if (e.getKey().equals("sDet_status")) {
					g.setsDet_status((int) e.getValue());
				}
			}
			lst.add(g);
		}
		return lst;
	}

}
