package cn.purchase;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
/**
 * 1.日期2017-8-16
 * 2.内容
 *  a.订单审核选项卡，切换订单审核和订单查询
 * @author 熊晨晨
 *
 */
public class PurchaseSel extends JFrame{

	JSplitPane splittopSelGood;// 创建上下分割面板
	JSplitPane splittopSelQuery;// 创建上下分割面板
	
	public PurchaseSel(){
    	this.inist();
    	this.addpanel();
    	//窗口可视化
    	this.setVisible(true);
    	splittopSelGood.setDividerLocation(0.52);
    	splittopSelQuery.setDividerLocation(0.52);
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
    	this.setTitle("审核采购查询");
    }
    /**
     * 创建面板                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
     */
    public void addpanel(){
    	// 创建分割面板对象
    	splittopSelGood = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	JPanel jp = new JPanel();
    	//无布局设置
    	jp.setLayout(new BorderLayout());
    	jp.add(new PurchaseSelGood(splittopSelGood));


    	//添加面板对象
    	// 创建分割面板对象
    	splittopSelQuery = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	JPanel jp2 = new JPanel();
    	jp2.setLayout(new BorderLayout());
    	jp2.add(new PurchaseSelQuery(splittopSelQuery));
        
        JTabbedPane tp = new JTabbedPane();
        tp.add(jp);
        tp.add(jp2);
     // 设置tab的标题
        tp.setTitleAt(0, "审核订单");
        tp.setTitleAt(1, "审核查询");
        ImageIcon i = new ImageIcon("images\\refresh.png");
        tp.setIconAt(0,i );
        tp.setIconAt(1,i );
        
        this.setContentPane(tp);
    	
    }
    public static void main(String[] args) {
		new PurchaseSel();
	}

}
