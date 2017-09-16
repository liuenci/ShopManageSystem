package cn.purchase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import cn.dao.purchase.PurchaseOrderDao1;
import cn.model.common.PurchaseOrder;

public class Purchase extends JFrame {

	public Purchase() {
		// 初始化
		this.inist();
		// 添加面板方法
		this.addpanel();
		// 设置窗口可视化
		this.setVisible(true);
	}

	/**
	 * 初始化面板
	 */
	public void inist() {
		this.setSize(1370, 700);
		// 设置窗口显示位置
		this.setLocation(0, 0);
		// 设置标题
		this.setTitle("采购板块");
		// 设置关闭状态
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * 创建面板
	 */
	public void addpanel() {
		// 创建面板对象
		JPanel jp = new JPanel();
		// 面板布局设置为空
		jp.setLayout(null);
		// 面板长宽
		jp.setSize(1200, 600);
		// 创建按钮对象,商品添加按钮
		JButton btnPur = new JButton("采购进货");
		btnPur.setBounds(300, 200, 100, 40);
		// 触发点击事件
		btnPur.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳转到采购进货功能面板
				DefaultTableModel tm;// 创建表模型
				// 订单编号
				int pur_id = 20171001;
				// 供货商编号，外键
				int pur_supplyId = 1;
				// 采购日期
				String date = "2017-8-13";
				java.sql.Date pur_date = java.sql.Date.valueOf(date);
				// 支付总金额
				double pur_pay=0.0;
				// 员工编号，外键
				int pur_empId=1;
				// 是否审核（0：未审核1：已审核通过 2：审核未通过退回采购员）
				int pur_status=0;
				// 备注
				String pur_mark="";
				// 创建表格
				String[] str = { "采购订单号", "供货商编号", "采购日期", "支付总金额", "员工编号", "是否审核", "备注" };
				// 获取数据库数据
				PurchaseOrderDao1 purd = new PurchaseOrderDao1();
				PurchaseOrder pur = new PurchaseOrder(pur_supplyId,pur_date,pur_pay,pur_empId,pur_status,pur_mark);
				Object[][] rows = new Object[0][];
				pur.setPur_id(pur_id); 
				int afrow = 0;
				afrow = purd.addPurchaseOrder(pur);
				//String status = pur.getSup_status() == 0 ? "保持合作" : "解除合作";
				if (afrow > 0) {
					Object[] rowData = {afrow,pur_supplyId,pur_date,pur_pay,pur_empId,pur_status,pur_mark};
					// 在表格中新增一行数据
					//tm.addRow(rowData);
					// 关闭当前窗口
					new PurchaseOrderView();
				} 
			}
		});
		// 采购订单查询按钮
		JButton btnOrd = new JButton("采购订单");
		btnOrd.setBounds(300, 400, 100, 40);
		// 触发点击事件
		btnOrd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳转到采购订单查询面板
				new PurchaseSel();
			}
		});
		// 采购退货按钮
		JButton btnSta = new JButton("采购退货");
		btnSta.setBounds(700, 200, 100, 40);
		// 触发点击事件
		btnSta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 采购退货面板
				new PurchaseDel();
			}
		});
		// 供应商设置按钮
		JButton btnSup = new JButton("供应商设置");
		btnSup.setBounds(700, 400, 100, 40);
		// 触发点击事件
		btnSup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 供应商设置设置面板
				new PurchaseSupply();
			}
		});
		// 按钮添加到面板
		jp.add(btnPur);
		jp.add(btnOrd);
		jp.add(btnSta);
		jp.add(btnSup);
		// 面板添加到窗口
		this.add(jp);
	}

	public static void main(String[] args) {
		new Purchase();
	}
}
