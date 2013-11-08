package itdemo.demo.threadlocal;

import org.apache.log4j.Logger;

public class TestService {
	private static Logger log = Logger.getLogger(TestService.class);

	public void process() {
		log.info(MyThreadLocal.getAccessTime());
		log.info(Thread.currentThread().getName() + " processing.....");
		MyThreadLocal.reset();
	}
}
