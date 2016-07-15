package ro.estore.aspect;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ro.estore.model.repository.impl.ProductRepositoryJpaImpl;

@Component
@Aspect
public class LoggingAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryJpaImpl.class);
	
	@Pointcut("within(ro.estore..*)")
	private void selectAll() {
	}

	@Before("selectAll()")
	public void logMethodCall(JoinPoint joinPoint) {
		LOGGER.info("\n\t=====================================================================\n\t" + 
				"Calling method: " + joinPoint.getStaticPart().getSignature().toString()
				+ "\n\t=====================================================================");
	}
	
	@Around("selectAll()")
	public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object res = null;
		
		try {
			res = joinPoint.proceed();
			stopWatch.stop();
			LOGGER.info("\n\t=====================================================================\n\t" + 
					"Method: " + joinPoint.getStaticPart().getSignature().toString() + " took " + stopWatch.getTime() + "ms to execute."
					+ "\n\t=====================================================================");
		} catch (Throwable e) {
			LOGGER.error("Error encountered: ", e);
		}	
		
		return res;
	}

}
