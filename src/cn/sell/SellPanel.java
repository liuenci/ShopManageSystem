package cn.sell;
/**
 * 主面板
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.dao.sell.SalesDao2;
import cn.model.common.SellOrder;

public class SellPanel extends JFrame {
	// 主面板
	private JPanel mainPanel;
	// 商品退货按钮
	private JButton mercharetu;
	// 订单管理按钮
	private JButton ordManag;
	// 当前库存查询按钮
	private JButton currInvenQuery;
	// 销售订单编号
	int saleOrderNumber;

	Format gs=new Format();
	// 初始化
	public SellPanel() {
		this.init();
		addsell();
		clickEvent();
		// 设置窗口可见
		this.setVisible(true);
	}

	public void init() {
		// 设置窗口标题
		this.setTitle("销售管理");
		// 设置窗口大小
		this.setSize(1366, 768);
		// 设置显示窗口的位置
		this.setResizable(false);
		;
		// 不许修改窗口的大小
		this.setLocationRelativeTo(getOwner());
		// 设置关闭状态
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 主窗口 子功能有：跳转 （销售订单 订单退货 库存查询）
	 */
	public void addsell() {
		// 创建面板
		mainPanel = gs.createPanel(5, 5, 775, 450);
		ordManag = gs.createButton("销售订单", 500, 300, 130, 30, "宋体", 24);
		mercharetu = gs.createButton("订单退货", 500, 410, 130, 30, "宋体", 24);
		currInvenQuery = gs.createButton("库存查询", 850, 345, 130, 30, "宋体", 24);
		mainPanel.add(ordManag);
		mainPanel.add(mercharetu);
		mainPanel.add(currInvenQuery);
		this.add(mainPanel);
	}

	/**
	 * 点击事件
	 */
	public void clickEvent() {
		/**
		 * 跳转商品退货窗口
		 */
		mercharetu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 商品退货
				new Merchandise_returns();

			}
		});

		/**
		 * 跳转订单管理窗口
		 */
		ordManag.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 暂时时间
				String date = "2017-8-13";
				Date sell_date = Date.valueOf(date);
				// 暂时员工表编号外键
				int sell_empId = 2;
				// 暂时支付总金额
				double sell_profit = 0.0;
				// 暂时备注
				String sell_mark = "无备注";
				// 调用dao层新增销售单信息
				SalesDao2 sell = new SalesDao2();
				int status = 0;
				SellOrder seoed = new SellOrder(sell_empId, sell_date, sell_profit,status,sell_mark);
				int sel = sell.addSellOrder(seoed);
				// 销售订单
				new Sales_order();
			}
		});
		/**
		 * 跳转库存查询窗口
		 */
		currInvenQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Inventory_query();

			}
		});
	}

	public static void main(String[] args) {
		new SellPanel();
	}
}
