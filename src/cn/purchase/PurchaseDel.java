package cn.purchase;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import cn.method.CommondMethods;

/**
 * 1.����2017-8-18
 * 2.����
 *  a.�����˻�ѡ����л������˻����˻���ѯ
 * @author �ܳ���
 *
 */
public class PurchaseDel extends JFrame{
	
	JSplitPane splittopDel;// �������·ָ����
	JSplitPane splittopQuery;// �������·ָ����
	CommondMethods comMenth = null;
	
	public PurchaseDel(){
		comMenth = new CommondMethods();
    	this.inist();
    	this.addpanel();
    	//���ڿ��ӻ�
    	this.setVisible(true);
    	splittopDel.setDividerLocation(0.52);
    	splittopQuery.setDividerLocation(0.52);
    }
	/**
     * ��ʼ��ҳ��
     */
    public void inist(){
    	//���ô��ڴ�С
    	this.setSize(815, 530);
 	   //���ô�����ʾλ��
    	this.setLocation(280,85);
    	// �����޸Ĵ��ڵĴ�С
        this.setResizable(false);
    	this.setTitle("�ɹ��˻�");
    }
    /**
     * �������                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
     */
    public void addpanel(){
    	// �����ָ�������
    	splittopDel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	JPanel jp = comMenth.createPanel(0, 0, 815, 530);
    	//�޲�������
    	jp.setLayout(new BorderLayout());
    	jp.add(new PurchaseDelGood(splittopDel));


    	//���������
    	// �����ָ�������
    	splittopQuery = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	JPanel jp2 = new JPanel();
    	jp2.setLayout(new BorderLayout());
    	jp2.add(new PurchaseDelGoodQuery(splittopQuery));
        
        JTabbedPane tp = new JTabbedPane();
        tp.add(jp);
        tp.add(jp2);
     // ����tab�ı���
        tp.setTitleAt(0, "�ɹ��˻�");
        tp.setTitleAt(1, "�˻���ѯ");
        ImageIcon i = new ImageIcon("images\\refresh.png");
        tp.setIconAt(0,i );
        tp.setIconAt(1,i );
        this.setContentPane(tp);
    	
    }
    public static void main(String[] args) {
		new PurchaseDel();
	}
}
