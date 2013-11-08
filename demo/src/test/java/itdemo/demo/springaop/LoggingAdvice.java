package itdemo.demo.springaop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {
	private Logger log = Logger.getLogger(LoggingAdvice.class);

	//@Before("execution(* itdemo.demo.springaop.*Service.*(..))")
	private void loggingBefore() {
		log.info("@Before執行前寫log...");
	}

	//@After("execution(* itdemo.demo.springaop.*Service.*(..))")
	private void loggingAfter() {
		log.info("@After執行後寫log...");
	}

	//@Around("execution(* itdemo.demo.springaop.*Service.*(..))")
	private Object loggingAround(ProceedingJoinPoint pjp) throws Throwable {
		log.info("@Around執行前寫log...");
		Object retVal = pjp.proceed();
		log.info("@Around執行後寫log...");
		return retVal;
	}

	//@AfterReturning(pointcut = "execution(* itdemo.demo.springaop.BusinessService.processTransactionWithReturnValue())", returning = "retVal")
	private void loggingAfterReturn(Object retVal) {
		log.info("@AfterReturning回傳值為:" + retVal);
	}

	@AfterThrowing(pointcut = "execution(* itdemo.demo.springaop.BusinessService.processTransactionWithException())", throwing = "ex")
	private void loggingAfterReturn(Throwable ex) {
		log.info("@AfterThrowing錯誤訊息為:" + ex.getMessage());
	}
}