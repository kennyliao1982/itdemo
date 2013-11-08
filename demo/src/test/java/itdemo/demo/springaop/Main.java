package itdemo.demo.springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-beans.xml");
		BusinessService service = ctx.getBean(BusinessService.class);
		// service.processTransaction();

		// service.processTransactionWithReturnValue();

		 service.processTransactionWithException();
	}

}
