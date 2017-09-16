package cn.init.frame;

import cn.storage.StorageAlarm;

public class StorageAlarmInit implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			new StorageAlarm();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void start(){
		Thread t = new Thread(this);
		t.start();		
	}

}
