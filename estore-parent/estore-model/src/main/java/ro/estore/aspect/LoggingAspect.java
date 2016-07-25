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

	/*
	 * Method solely used for selecting the classes found in the package which
	 * is specified in the @Pointcut annotation
	 */
	@Pointcut("within(ro.estore..*)")
	private final void selectAll() {
		// Empty because there is no actual code/logic which should be involved.
		// Only needs annotated
	}

	@Before("selectAll()")
	public void logMethodCall(JoinPoint joinPoint) {
		LOGGER.trace("Calling method: " + joinPoint.getStaticPart().getSignature().toString());
	}

	@Around("selectAll()")
	public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object res = null;

		try {
			res = joinPoint.proceed();
			stopWatch.stop();
			LOGGER.trace("Method: " + joinPoint.getStaticPart().getSignature().toString() + " took "
					+ stopWatch.getTime() + "ms to execute.");
		} catch (Exception e) {
			LOGGER.error("Exception encountered: ", e);
		} catch (Throwable e) {
			LOGGER.error("Error encountered: ", e);
		}

		return res;
	}

}
