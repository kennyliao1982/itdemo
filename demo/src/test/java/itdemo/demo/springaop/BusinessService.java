package itdemo.demo.springaop;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
	private Logger log = Logger.getLogger(BusinessService.class);

	public void processTransaction() {
		log.info("processTransaction....");
	}

	public String processTransactionWithReturnValue() {
		log.info("processTransactionWithReturnValue....");
		return "ok";
	}

	public void processTransactionWithException() {
		log.info("processTransactionWithException....");
		throw new RuntimeException("error occurs !!");
	}
}
