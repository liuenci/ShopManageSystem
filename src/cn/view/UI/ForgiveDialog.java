package cn.view.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * ����:2017-08-02
 * ���ܣ���ʾ���������
 * @author LuckyJavaCi
 *
 */

public class ForgiveDialog extends JDialog{
	private JLabel desc = null;
	private JPanel panel = null;
	private JButton btn = null;
	
	public ForgiveDialog(Frame frame, String title, boolean modal) {
		super(frame,title,modal);
		init();
	}
	public void init() {
		desc = new JLabel();
		desc.setText("<html>���������ˣ�<br/><br/><br/>����ϵ���ܵĹ���Ա�ɡ�</html>");
		desc.setFont(new Font("����",Font.PLAIN,20));
		desc.setHorizontalAlignment(JLabel.CENTER);
		panel = new JPanel();
		btn = new JButton("�õ�");
		btn.setPreferredSize(new Dimension(100, 30));
		panel.add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ForgiveDialog.this.dispose();
			}
		});
		//Ĭ�Ϸ��ڲ��ֹ��������м�
		this.add(desc);
		this.add(panel,BorderLayout.SOUTH);
		this.setSize(450,370);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		int windowWidth = this.getWidth();
		int windowHeight = this.getHeight();
		int x = (screenWidth - windowWidth) / 2;
		int y = (screenHeight - windowHeight) / 2;
		this.setLocation(x, y);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
