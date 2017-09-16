package cn.view.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.PurchaseOrderDao1;
import cn.model.common.PurchaseOrder;
import cn.purchase.PurchaseDel;
import cn.purchase.PurchaseOrderView;
import cn.purchase.PurchaseSel;
import cn.purchase.PurchaseSupply;
/**
 * 日期：2017-08-05
 * 功能：提供采购进货功能
 * 	1.采购进货
 * 	2.采购退货
 * 	3.采购订单
 * 	4.供应商设置
 * @author LuckyJavaCi
 *
 */
public class PurchaseUI extends JPanel {
	JButton purBtn = new JButton("采购进货");//采购进货按钮
	JButton returnBtn = new JButton("采购退货");//采购退货按钮
	JButton purOrderBtn = new JButton("采购订单");//采购订单按钮

	public PurchaseUI() {
		this.setBackground(new Color(184,222,223));
		this.mainButton();
		this.elementListener();
		this.setVisible(true);
	}

	/*
	 *  添加主功能面板
	 */
	public void mainButton() {
		this.setLayout(null);
		purBtn.setBounds(120, 80, 120, 40);
		this.add(purBtn);
		returnBtn.setBounds(480, 80, 120, 40);
		this.add(returnBtn);
		purOrderBtn.setBounds(300, 300, 120, 40);
		this.add(purOrderBtn);
	}

	/*
	 *  给相关的组件设置时间监听器
	 */
	public void elementListener() {
		// 采购进货
		purBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int pur_id = 20171001;// 订单编号
				int pur_supplyId = 1;// 供货商编号，外键
				String date = "2017-8-26";// 采购日期
				Date pur_date = Date.valueOf(date);
				double pur_pay=0.0;// 支付总金额
				int pur_empId=154083005;// 员工编号，外键
				int pur_status=0;// 是否审核（0：未审核1：已审核通过 2：审核未通过退回采购员）
				String pur_mark="";// 备注
				// 创建表格
				// 获取数据库数据
				PurchaseOrderDao1 purd = new PurchaseOrderDao1();
				PurchaseOrder pur = new PurchaseOrder(pur_supplyId,pur_date,pur_pay,pur_empId,pur_status,pur_mark);
				pur.setPur_id(pur_id); 
				int afrow = 0;
				afrow = purd.addPurchaseOrder(pur);
				if (afrow > 0) {
					new PurchaseOrderView();
				} 
			}
		});
		// 采购订单
		returnBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PurchaseDel();
			}
		});
		// 采购退货
		purOrderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PurchaseSel();
			}
		});
	}

	public static void main(String[] args) {
		new PurchaseUI();
	}

}
