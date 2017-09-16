package cn.purchase;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import cn.method.CommondMethods;

/**
 * 1.日期2017-8-18
 * 2.内容
 *  a.订单退货选项卡，切换订单退货和退货查询
 * @author 熊晨晨
 *
 */
public class PurchaseDel extends JFrame{
	
	JSplitPane splittopDel;// 创建上下分割面板
	JSplitPane splittopQuery;// 创建上下分割面板
	CommondMethods comMenth = null;
	
	public PurchaseDel(){
		comMenth = new CommondMethods();
    	this.inist();
    	this.addpanel();
    	//窗口可视化
    	this.setVisible(true);
    	splittopDel.setDividerLocation(0.52);
    	splittopQuery.setDividerLocation(0.52);
    }
	/**
     * 初始化页面
     */
    public void inist(){
    	//设置窗口大小
    	this.setSize(815, 530);
 	   //设置窗口显示位置
    	this.setLocation(280,85);
    	// 不许修改窗口的大小
        this.setResizable(false);
    	this.setTitle("采购退货");
    }
    /**
     * 创建面板                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
     */
    public void addpanel(){
    	// 创建分割面板对象
    	splittopDel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	JPanel jp = comMenth.createPanel(0, 0, 815, 530);
    	//无布局设置
    	jp.setLayout(new BorderLayout());
    	jp.add(new PurchaseDelGood(splittopDel));


    	//添加面板对象
    	// 创建分割面板对象
    	splittopQuery = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	JPanel jp2 = new JPanel();
    	jp2.setLayout(new BorderLayout());
    	jp2.add(new PurchaseDelGoodQuery(splittopQuery));
        
        JTabbedPane tp = new JTabbedPane();
        tp.add(jp);
        tp.add(jp2);
     // 设置tab的标题
        tp.setTitleAt(0, "采购退货");
        tp.setTitleAt(1, "退货查询");
        ImageIcon i = new ImageIcon("images\\refresh.png");
        tp.setIconAt(0,i );
        tp.setIconAt(1,i );
        this.setContentPane(tp);
    	
    }
    public static void main(String[] args) {
		new PurchaseDel();
	}
}
