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

	JProgressBar pb; // 创建一个进度对象

	public InitPurFrame() {
		init();
		setContent();
		// 设置窗口显示
		this.setVisible(true);
	}

	/**
	 * 初始化窗口
	 */
	public void init() {
		// 设置窗口大小
		this.setSize(400, 300);
		// 去掉窗口边框
		this.setUndecorated(true);
		// 设置窗口位置
		// 获取屏幕尺寸
		// Dimension ds = this.getToolkit().getScreenSize();
		// this.setLocation((ds.width-this.getWidth())/2,
		// (ds.height-this.getHeight())/2);
		// 设置窗口居中
		this.setLocationRelativeTo(getOwner());
		this.setResizable(false);
	}

	/**
	 * 初始化窗口内容
	 */
	public void setContent() {
		// 给窗口添加图片
		JLabel lb = new JLabel();
		ImageIcon logoIcon = new ImageIcon("images/Content.gif");
		Image logoImage = logoIcon.getImage();
		int logoWidth = 400;
		int logoHeight = 300;
		logoImage = logoImage.getScaledInstance(logoWidth, logoHeight, Image.SCALE_DEFAULT);
		logoIcon.setImage(logoImage);
		lb.setIcon(logoIcon);
		this.add(lb);

		// 添加进度条
		pb = new JProgressBar(0, 400);
		// 设置进度条文字
		pb.setStringPainted(true);
		pb.setBackground(Color.white);

		this.add(pb, BorderLayout.SOUTH);
	}
	/**
	 * 用于执行进度条滚动的操作
	 */
	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				//每个50毫秒设置一次
				Thread.sleep(50);
				//设置进度条的值
				pb.setValue(4*i);
				pb.setString("程序加载中..."+i+"%");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//关闭当前窗口
		this.dispose();
		//打开主界面
		new ContentUI();
		StorageAlarmInit storageAlarmInit = new StorageAlarmInit();
		storageAlarmInit.start();
	}
	
	/**
	 * 开启线程
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

