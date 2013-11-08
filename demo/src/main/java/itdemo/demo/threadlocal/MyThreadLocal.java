package itdemo.demo.threadlocal;

public class MyThreadLocal {
	
	private static final ThreadLocal<Long> accessLog = new ThreadLocal<Long>();

	public static void setAccessTime(long time) {
		accessLog.set(time);
	}

	public static String getAccessTime() {
		return Thread.currentThread().getName() + " access time:" +accessLog.get();
	}
	
	public static void reset(){
		accessLog.remove();
	}
}
