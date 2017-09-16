package cn.init.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import cn.view.UI.ContentUI;

public class InitPurFrame extends JFrame implements Runnable {

	JProgressBar pb; // ����һ�����ȶ���

	public InitPurFrame() {
		init();
		setContent();
		// ���ô�����ʾ
		this.setVisible(true);
	}

	/**
	 * ��ʼ������
	 */
	public void init() {
		// ���ô��ڴ�С
		this.setSize(400, 300);
		// ȥ�����ڱ߿�
		this.setUndecorated(true);
		// ���ô���λ��
		// ��ȡ��Ļ�ߴ�
		// Dimension ds = this.getToolkit().getScreenSize();
		// this.setLocation((ds.width-this.getWidth())/2,
		// (ds.height-this.getHeight())/2);
		// ���ô��ھ���
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);
	}

	/**
	 * ��ʼ����������
	 */
	public void setContent() {
		// ���������ͼƬ
		JLabel lb = new JLabel();
		ImageIcon logoIcon = new ImageIcon("images/Content.gif");
		Image logoImage = logoIcon.getImage();
		int logoWidth = 400;
		int logoHeight = 300;
		logoImage = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_DEFAULT);
		logoIcon.setImage(logoImage);
		lb.setIcon(logoIcon);
		this.add(lb);

		// ��ӽ�����
		pb = new JProgressBar(0, 400);
		// ���ý���������
		pb.setStringPainted(true);
		pb.setBackground(Color.white);

		this.add(pb, BorderLayout.SOUTH);
	}
	/**
	 * ����ִ�н����������Ĳ���
	 */
	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				//ÿ��50��������һ��
				Thread.sleep(50);
				//���ý�������ֵ
				pb.setValue(4*i);
				pb.setString("���������..."+i+"%");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//�رյ�ǰ����
		this.dispose();
		//��������
		new ContentUI();
		StorageAlarmInit storageAlarmInit = new StorageAlarmInit();
		storageAlarmInit.start();
	}
	
	/**
	 * �����߳�
	 */
	public void start(){
		Thread t = new Thread(this);
		t.start();		
	}

	public static void main(String[] args) {
		InitPurFrame inf=new InitPurFrame();
		inf.start();
	}

}

