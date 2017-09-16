
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

	// 通过职位查询员工
	public List<Employee> query(int posi_id) {
		// 创建员工结果集合
		List<Employee> result = new ArrayList<Employee>();
		String sql = "select emp_name from tb_employee where emp_position_id = ?";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(posi_id));
		for (Map<String, Object> m : lmp) {
			// 创建员工对象
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
	 * 查询最新的id 值
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
		// 查询sql
		String sql = "select *from tb_good";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
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
		// 查询sql语句
		String sql = "select * from tb_good where goods_id  like ?";
		String str = "%" + goods_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));

		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
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
		// 查询sql语句
		String sql = "select * from tb_good where goods_stoId  = ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(goods_stoId));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建供应商对象保存数据
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
	 * 1.添加销售订单表
	 * 
	 * @param bo
	 * @return 影响的行数
	 */
	public int addtb_sellDetail(SellDetail sellDetail) {
		String sql = "insert into tb_sellDetail(sDet_sellId ,sDet_goodId ,sDet_number "
				+ ",sDet_goodPrice ,sDet_mark,sDet_status) values (?,?,?,?,?,?)";
		// 调用增加方法
		int row = updateExecuted(sql,
				new ParamSet(sellDetail.getsDet_sellId(), sellDetail.getsDet_goodId(), sellDetail.getsDet_number(),
						sellDetail.getsDet_goodPrice(), sellDetail.getsDet_mark(), sellDetail.getsDet_status()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;

	}

	/**
	 * 新增销售单信息
	 * 
	 * @param sell
	 * @return 返回最大id
	 */
	public int addSellOrder(SellOrder sell) {
		// sql语句
		String sql = "insert into tb_sellOrder (sell_empId,sell_date,sell_profit,sell_status,sell_mark) VALUES(?,?,?,0,?) ";
		// 调用sql方法
		int row = updateExecuted(sql,
				new ParamSet(sell.getSell_empId(), sell.getSell_date(), sell.getSell_profit(), sell.getSell_mark()));
		if (row > 0) {
			row = getMaxId();
		}
		return row;
	}

	/**
	 * 跟新订单表
	 * 
	 * @param sell
	 * @return 返回最大id
	 */
	public int reviseSales(SellOrder sell) {
		// 员工编号 时间 总金额 备注
		String sql = "update tb_sellOrder set sell_empId =?,sell_date =?,sell_profit =?,sell_mark =? where sell_id = ?";
		int row = updateExecuted(sql, new ParamSet(sell.getSell_empId(), sell.getSell_date(), sell.getSell_profit(),
				sell.getSell_mark(), sell.getSell_id()));

		return row;
	}

	/**
	 * 通过id查询数量
	 * 
	 * @param goods_number
	 * @return
	 */
	public List<Good> goodsnum(int goods_number) {
		// 创建员工结果集合
		List<Good> result = new ArrayList<Good>();
		String sql = "select goods_number from tb_good where goods_id = ?";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(goods_number));
		for (Map<String, Object> m : lmp) {
			// 创建数量对象
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
	 * 通过商品编号查询库存商品数量
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

	// 通过姓名查找员工Id
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
	 * 跟新商品数量 状态goods_number0是销售出去 状态goods_number1是销售了退回来了
	 * 
	 * @param sell
	 * @return 返回最大id
	 */
	public int update(int goodnumber,int sDetgoodId,int sellid) {
		// 员工编号 时间 总金额 备注
		String sql = "update tb_sellDetail,tb_good,tb_sellOrder set sDet_status=1,sell_status=1,goods_number =goods_number + ? where tb_sellOrder.sell_id=tb_sellDetail.sDet_sellId and tb_sellDetail.sDet_goodId=tb_good.goods_id AND sDet_goodId= ? and sell_id= ?";
		int row = updateExecuted(sql, new ParamSet(goodnumber,sDetgoodId,sellid));
		return row;
	}

	public int updateGoodNum(int goods_id, int pDet_num) {
		// 员工编号 时间 总金额 备注
		String sql = "update tb_good set goods_number=goods_number- ? where goods_id= ?";
		int row = updateExecuted(sql, new ParamSet(pDet_num, goods_id));
		return row;
	}

	/**
	 * 查看销售订单状态为全部 不重复
	 * 
	 * @return
	 */

	public List<RefundOrder> SeeRefundOrderx() {
		List<RefundOrder> lst = new ArrayList<RefundOrder>();
		// 查询sql
		String sql = "select distinct sell_id,sell_date,sell_profit,sell_mark from tb_sellOrder LEFT JOIN tb_sellDetail on sDet_sellId=sell_id";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefundOrder g = new RefundOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
	 * 查看销售订单状态为0 不重复
	 * 
	 * @return
	 */

	public List<RefunddetailsOrder> SeeRefundOrder() {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// 查询sql
		String sql = "select distinct sell_id,sell_date,sell_profit,sell_mark,emp_name from tb_sellOrder LEFT JOIN tb_employee on emp_id=sell_empId WHERE sell_status=0";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","经办人","备注"
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
	 * 查看销售订单状态为0 不重复
	 * 
	 * @return
	 */

	public List<RefunddetailsOrder> viewSalesOrderStatus() {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// 查询sql
		String sql = "SELECT DISTINCT tb_sellorder.sell_id,tb_sellorder.sell_date,tb_sellorder.sell_mark,tb_employee.emp_name,tb_sellorder.sell_profit,tb_selldetail.sDet_status FROM tb_sellorder ,tb_selldetail ,tb_employee WHERE tb_employee.emp_id = tb_sellorder.sell_empId AND tb_selldetail.sDet_status = 1 AND tb_selldetail.sDet_sellId = tb_sellorder.sell_id";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","经办人","备注"
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
	 * 查看销售订单状态为1 不重复
	 * 
	 * @return
	 */

	public List<SellOrder> SeeRefundOrders() {
		List<SellOrder> lst = new ArrayList<SellOrder>();
		// 查询sql
		String sql = "select * from tb_sellOrder";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			SellOrder g = new SellOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
		// 查询sql
		String sql = "select * from tb_sellOrder where sell_id like ? and ";
		// 查询
		List<Map<String, Object>> lmp = queryList("%" + sql + "%");
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			SellOrder g = new SellOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
	 * 根据 订单号查询 已销售的订单详情
	 * 
	 * @return
	 */
	public List<RefunddetailsOrder> SeeRefunddetailsOrder(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// 查询sql
		String sql = "select sDet_goodId,goods_name,goods_units,goods_size,goods_sellPrice,sDet_number,sDet_goodPrice,sDet_status from tb_sellDetail LEFT JOIN tb_good on sDet_goodId=goods_id LEFT JOIN tb_sellOrder ON sDet_sellId=sell_id where sDet_status= 0 AND sell_id= ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sell_id));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefunddetailsOrder g = new RefunddetailsOrder();
			// 商品编号 商品名称 单位 单价 数量 总金额 规格大小
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
	 * 根据 订单号查询 已销售的退货订单详情
	 * 
	 * @return
	 */
	public List<RefunddetailsOrder> ordernumberfordetailsof(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// 查询sql
		String sql = "select sDet_goodId,goods_name,goods_units,goods_size,sDet_status,goods_sellPrice,sDet_number,sDet_goodPrice from tb_sellDetail LEFT JOIN tb_good on sDet_goodId=goods_id LEFT JOIN tb_sellOrder ON sDet_sellId=sell_id where sDet_status= 1 AND sell_id= ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sell_id));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefunddetailsOrder g = new RefunddetailsOrder();
			// 商品编号 商品名称 单位 单价 数量 总金额 规格大小
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
	 * 根据 订单号查询 已销售的订单详情 加状态显示
	 * 
	 * @return
	 */
	public List<RefunddetailsOrder> SeeRefunddetailsOrderx(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// 查询sql
		String sql = "select sDet_goodId,goods_name,goods_units,sDet_status,goods_size,goods_sellPrice,sDet_number,sDet_goodPrice from tb_sellDetail LEFT JOIN tb_good on sDet_goodId=goods_id LEFT JOIN tb_sellOrder ON sDet_sellId=sell_id where sell_id= ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sell_id));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefunddetailsOrder g = new RefunddetailsOrder();
			// 商品编号 商品名称 单位 单价 数量 总金额 规格大小
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
	 * 根据 订单号查询 已销售的订单详情 zt1
	 * 
	 * @return
	 */
	public List<RefunddetailsOrder> SeeRefunddetailsOrders(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// 查询sql
		String sql = "select sDet_goodId,goods_name,goods_units,goods_size,goods_sellPrice,sDet_number,sDet_goodPrice from tb_sellDetail LEFT JOIN tb_good on sDet_goodId=goods_id LEFT JOIN tb_sellOrder ON sDet_sellId=sell_id where sDet_status= 1 AND sell_id= ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sell_id));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefunddetailsOrder g = new RefunddetailsOrder();
			// 商品编号 商品名称 单位 单价 数量 总金额 规格大小
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
	 * 查找根据ID查找订单
	 * 
	 * @param goods_id
	 * @return
	 */
	public List<RefunddetailsOrder> To_find_the(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// 查询sql语句
		String sql = "SELECT DISTINCT tb_employee.emp_name,tb_sellorder.sell_id,tb_sellorder.sell_date,tb_sellorder.sell_profit,tb_sellorder.sell_mark FROM tb_employee ,tb_sellorder,tb_selldetail WHERE tb_employee.emp_id = tb_sellorder.sell_empId AND tb_sellorder.sell_id = tb_selldetail.sDet_sellId AND tb_sellorder.sell_status = 0 and sell_id like ?";
		String str = "%" + sell_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
	 * 查找根据ID查找订单zt1
	 * 
	 * @param goods_id
	 * @return
	 */
	public List<RefunddetailsOrder> findorderaccordingtoID(int sell_id) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		// 查询sql语句
		String sql = "SELECT DISTINCT tb_employee.emp_name,tb_sellorder.sell_id,tb_sellorder.sell_date,tb_sellorder.sell_profit,tb_sellorder.sell_mark FROM tb_employee ,tb_sellorder,tb_selldetail WHERE tb_employee.emp_id = tb_sellorder.sell_empId AND tb_sellorder.sell_id = tb_selldetail.sDet_sellId AND sDet_status= 1 and sell_id like ?";
		String str = "%" + sell_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
	 * 查找根据ID查找订单 zt1
	 * 
	 * @param goods_id
	 * @return
	 */
	public List<RefundOrder> To_find_thes(int sell_id) {
		List<RefundOrder> lst = new ArrayList<RefundOrder>();
		// 查询sql语句
		String sql = "select * from tb_sellOrder LEFT JOIN tb_sellDetail ON sDet_sellId=sell_id where sDet_status= 1 OR sell_date like ?";
		String str = "%" + sell_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefundOrder g = new RefundOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
	 * 部分退 根据订单号 商品编号 修改状态并修改数量
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
	 * 全部退货 根据订单号修改状态
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
	 * 根据时间查询单据 ztquanb
	 */
	public List<RefundOrder> stafftimex(Date sell_date) {
		List<RefundOrder> lst = new ArrayList<RefundOrder>();
		String sql = "select distinct sell_id,sell_date,sell_profit,sell_mark from tb_sellOrder LEFT JOIN tb_sellDetail ON sDet_sellId=sell_id where sell_date like ?";
		String str = "%" + sell_date + "%";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefundOrder g = new RefundOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
	 * 根据时间查询单据 zt0
	 */
	public List<RefunddetailsOrder> stafftime(Date sell_date) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		String sql = "SELECT DISTINCT tb_employee.emp_name,tb_sellorder.sell_id,tb_sellorder.sell_date,tb_sellorder.sell_profit,tb_sellorder.sell_mark FROM tb_employee ,tb_sellorder,tb_selldetail WHERE tb_employee.emp_id = tb_sellorder.sell_empId AND tb_sellorder.sell_id = tb_selldetail.sDet_sellId AND tb_sellorder.sell_status = 0 and sell_date like ?";
		String str = "%" + sell_date + "%";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
	 * 根据时间查询单据 zt0
	 */
	public List<RefunddetailsOrder> checkthedocumentsaccordingtothetime(Date sell_date) {
		List<RefunddetailsOrder> lst = new ArrayList<RefunddetailsOrder>();
		String sql = "SELECT DISTINCT tb_employee.emp_name,tb_sellorder.sell_id,tb_sellorder.sell_date,tb_sellorder.sell_profit,tb_sellorder.sell_mark FROM tb_employee ,tb_sellorder,tb_selldetail WHERE tb_employee.emp_id = tb_sellorder.sell_empId AND tb_sellorder.sell_id = tb_selldetail.sDet_sellId AND sDet_status= 1 and sell_date like ?";
		String str = "%" + sell_date + "%";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefunddetailsOrder g = new RefunddetailsOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
	 * 根据时间查询单据 zt 1
	 * 
	 * @param sell_date
	 * @return
	 */
	public List<RefundOrder> querySellOrderByDate(Date sell_date) {
		List<RefundOrder> lst = new ArrayList<RefundOrder>();
		String sql = "select * from tb_sellOrder where sell_date = ?";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(sell_date));
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefundOrder g = new RefundOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
	 * 删除根据订单编号
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
	 * 根据姓名找ID
	 * 
	 * @param emp_name
	 * @return
	 */
	public List<Employee> addEmployee(String emp_name) {
		List<Employee> lst = new ArrayList<Employee>();
		// 查询sql
		String sql = "select emp_position_id from tb_employee where emp_name = ?";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(emp_name));
		// 遍历数据集合
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
	 * 根据订单详情号查询订单信息 //
	 */
	// public List<PurchaseOrderEmpSup> queryOrdId(int pur_id) {
	// // 创建PurchaseOrderDetailGood集合保存信息
	// List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
	// // 查询sql语句
	// String sql = "select pur_id,(select sup_name from tb_supply where sup_id
	// = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from
	// tb_employee where emp_id = pur_empId) as emp_name,pur_mark from
	// tb_purchaseorder where pur_id like ? ";
	// String str = "%" + pur_id + "%";
	// // 查询
	// List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
	// // 遍历数据集合
	// for (Map<String, Object> m : lmp) {
	// // 创建对象保存数据
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
	 * 全部查询
	 */
	public List<PurchaseOrderEmpSup> queryOrdTime() {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder ";

		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
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
	 * 根据ID
	 * 
	 * @param pur_id
	 * @return
	 */
	public List<PurchaseOrderEmpSup> queryOrdTime(int pur_id) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_status,pur_mark from tb_purchaseorder WHERE pur_id like ?";
		String str = "%" + pur_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
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
	 * 根据详单id，查询
	 */
	public List<PurchaseOrderDetailGood> queryTime(int pur_id) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderDetailGood> lst = new ArrayList<PurchaseOrderDetailGood>();
		// 查询sql语句
		String sql = "select goods_id ,goods_name,goods_units,goods_size,goods_purPrice,pDet_number,goods_sellPrice,goods_stoId,goods_keepDays,goods_minNumber,pDet_status from tb_purDetail LEFT JOIN   tb_good on tb_good.goods_id = tb_purDetail.pDet_goodId LEFT JOIN  tb_purchaseOrder on tb_purDetail.pDet_purId = tb_purchaseOrder.pur_id WHERE pur_id like ?";
		String str = "%" + pur_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
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
	 * 根据时间查询单据
	 */
	public List<PurchaseOrderEmpSup> queryOrdTime(Date pur_date) {
		// 创建PurchaseOrderDetailGood集合保存信息
		List<PurchaseOrderEmpSup> lst = new ArrayList<PurchaseOrderEmpSup>();
		// 查询sql语句
		String sql = "select pur_id,(select sup_name from tb_supply where sup_id = pur_supplyId) as sup_name,pur_date,pur_pay,(select emp_name from tb_employee where emp_id = pur_empId) as emp_name,pur_mark from tb_purchaseorder where  pur_date = ? ";
		// String str = "%" + pur_date + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(pur_date));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建对象保存数据
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
	 * 获取仓库姓名Inventory_query
	 * 
	 * @return
	 */
	public List<Storage> Obtain_Library_information() {

		// 创建studentBean集合用于保存仓库名
		List<Storage> lst = new ArrayList<Storage>();
		// 查询sql
		String sql = "select sto_name FROM tb_storage";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
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
	 * 按商品编号查询信息
	 */
	public List<Good> queryGoods(int goods_id) {
		// 创建studentBean集合用于保存商品清单的信息
		List<Good> lst = new ArrayList<Good>();
		// 查询sql
		String sql = "select goods_id,goods_name,goods_units,goods_size,goods_sellPrice,goods_number from tb_good WHERE goods_id like ?";
		// 查询
		String str = "%" + goods_id + "%";
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			Good g = new Good();
			for (Entry<String, Object> e : m.entrySet()) {
				// 给
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
	 * 查询商品清单 Choose_goods
	 * 
	 * @return
	 */
	public List<Good> queryGood() {
		// 创建studentBean集合用于保存商品清单的信息
		List<Good> lst = new ArrayList<Good>();
		// 查询sql
		String sql = "select goods_id,goods_name,goods_units,goods_size,goods_sellPrice,goods_number from tb_good";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql);
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			Good g = new Good();
			for (Entry<String, Object> e : m.entrySet()) {
				// 给
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
	 * 查找根据ID查找订单全部状态 Commodity_management
	 * 
	 * @param goods_id
	 * @return
	 */
	public List<RefundOrder> To_find_thex(int sell_id) {
		List<RefundOrder> lst = new ArrayList<RefundOrder>();
		// 查询sql语句
		String sql = "select DISTINCT sell_id,sell_date,sell_profit,sDet_status,sell_mark from tb_sellOrder LEFT JOIN tb_sellDetail on sDet_sellId=sell_id where sell_id like ?";
		String str = "%" + sell_id + "%";
		// 查询
		List<Map<String, Object>> lmp = queryList(sql, new ParamSet(str));
		// 遍历数据集合
		for (Map<String, Object> m : lmp) {
			// 创建一个商品清单，用于保存map中的数据
			RefundOrder g = new RefundOrder();
			// "销售订单编号","销售日期","客户名称","收款金额","单据类型","经办人","备注"
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
