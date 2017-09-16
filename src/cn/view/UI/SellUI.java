package cn.view.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JPanel;

import cn.dao.sell.SalesDao2;
import cn.model.common.SellOrder;
import cn.sell.Merchandise_returns;
import cn.sell.Sales_order;

public class SellUI extends JPanel {

	// 创建按钮
	JButton sellOrderBtn = new JButton("销售订单");
	JButton sellReturnBtn = new JButton("销售退货");

	public SellUI() {
		this.setBackground(new Color(184, 222, 223));
		this.mainButton();
		this.elementListener();
		this.setVisible(true);
	}

	// 添加主功能面板
	public void mainButton() {
		// 设置布局为空
		this.setLayout(null);
		// this.setOpaque(false);
		sellOrderBtn.setBounds(120, 200, 120, 40);
		this.add(sellOrderBtn);
		sellReturnBtn.setBounds(480, 200, 120, 40);
		this.add(sellReturnBtn);

	}

	// 给相关的组件设置时间监听器
	public void elementListener() {
		// 采购计划
		sellOrderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 暂时时间
				String date = "2017-8-13";
				Date sell_date = Date.valueOf(date);
				// 暂时员工表编号外键
				int sell_empId = 154083006;
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
		// 库存盘点
		sellReturnBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Merchandise_returns();
				
			}
		});
	}

	public static void main(String[] args) {
		new SellUI();
	}

}