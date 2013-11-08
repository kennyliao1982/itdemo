package itdemo.demo.threadlocal;

import org.apache.log4j.Logger;

public class Main extends Thread {
	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Main();
		t1.start();
		
		Thread.sleep((long)(Math.random() * 2000));
		
		Thread t2 = new Main();
		t2.start();
	}

	public void run() {
		long now = System.currentTimeMillis();
		log.info(Thread.currentThread().getName() + " now:" + now);
		MyThreadLocal.setAccessTime(now);

		new TestService().process();
	}
}
