package cn.purchase;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
/**
 * 1.����2017-8-16
 * 2.����
 *  a.�������ѡ����л�������˺Ͷ�����ѯ
 * @author �ܳ���
 *
 */
public class PurchaseSel extends JFrame{

	JSplitPane splittopSelGood;// �������·ָ����
	JSplitPane splittopSelQuery;// �������·ָ����
	
	public PurchaseSel(){
    	this.inist();
    	this.addpanel();
    	//���ڿ��ӻ�
    	this.setVisible(true);
    	splittopSelGood.setDividerLocation(0.52);
    	splittopSelQuery.setDividerLocation(0.52);
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
    	this.setTitle("��˲ɹ���ѯ");
    }
    /**
     * �������                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
     */
    public void addpanel(){
    	// �����ָ�������
    	splittopSelGood = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	JPanel jp = new JPanel();
    	//�޲�������
    	jp.setLayout(new BorderLayout());
    	jp.add(new PurchaseSelGood(splittopSelGood));


    	//���������
    	// �����ָ�������
    	splittopSelQuery = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    	JPanel jp2 = new JPanel();
    	jp2.setLayout(new BorderLayout());
    	jp2.add(new PurchaseSelQuery(splittopSelQuery));
        
        JTabbedPane tp = new JTabbedPane();
        tp.add(jp);
        tp.add(jp2);
     // ����tab�ı���
        tp.setTitleAt(0, "��˶���");
        tp.setTitleAt(1, "��˲�ѯ");
        ImageIcon i = new ImageIcon("images\\refresh.png");
        tp.setIconAt(0,i );
        tp.setIconAt(1,i );
        
        this.setContentPane(tp);
    	
    }
    public static void main(String[] args) {
		new PurchaseSel();
	}

}
