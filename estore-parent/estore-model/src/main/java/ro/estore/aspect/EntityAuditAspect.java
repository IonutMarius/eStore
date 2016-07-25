package ro.estore.aspect;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ro.estore.model.entitiy.AbstractModelEntity;

@Component
@Aspect
public class EntityAuditAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(EntityAuditAspect.class);

	@Autowired
	private Environment env;

	/*
	 * Method solely used for selecting the methods with the signature which is
	 * specified in the @Pointcut annotation
	 */
	@Pointcut("execution (public * create(..))")
	private final void selectRepositoryCreateMethod() {
		// Empty because there is no actual code/logic which should be involved.
		// Only needs annotated
	}

	/*
	 * Method solely used for selecting the methods with the signature which is
	 * specified in the @Pointcut annotation
	 */
	@Pointcut("execution (public * update(..))")
	private final void selectRepositoryUpdateMethod() {
		// Empty because there is no actual code/logic which should be involved.
		// Only needs annotated
	}

	/*
	 * Method solely used for selecting the classes found in the package which
	 * is specified in the @Pointcut annotation
	 */
	@Pointcut("within(ro.estore.model.repository.impl..*)")
	private final void selectRepositories() {
		// Empty because there is no actual code/logic which should be involved.
		// Only needs annotated
	}

	@Around("selectRepositoryCreateMethod() && selectRepositories()")
	public Object addAuditColumnsForCreate(ProceedingJoinPoint joinPoint) {
		AbstractModelEntity entity = (AbstractModelEntity) joinPoint.getArgs()[0];
		LOGGER.trace("Adding create audit values to: \n\t" + entity.getClass().getCanonicalName());

		Object res = null;
		addCreated(entity);
		addModified(entity);
		Object[] args = { entity };
		try {
			res = joinPoint.proceed(args);
			LOGGER.trace("Proceeding with calling: \n\t" + joinPoint.getStaticPart().getSignature().toString());
		} catch (Exception e) {
			LOGGER.error("Exception encountered: ", e);
		} catch (Throwable e) {
			LOGGER.error("Error encountered: ", e);
		}

		return res;
	}

	@Around("selectRepositoryUpdateMethod() && selectRepositories()")
	public Object addAuditColumnsForUpdate(ProceedingJoinPoint joinPoint) {
		AbstractModelEntity entity = (AbstractModelEntity) joinPoint.getArgs()[0];
		LOGGER.trace("Adding update audit values to: \n\t" + entity.getClass().getCanonicalName());

		Object res = null;
		addModified(entity);
		Object[] args = { entity };
		try {
			res = joinPoint.proceed(args);
			LOGGER.trace("Proceeding with calling: \n\t" + joinPoint.getStaticPart().getSignature().toString());
		} catch (Exception e) {
			LOGGER.error("Exception encountered: ", e);
		} catch (Throwable e) {
			LOGGER.error("Error encountered: ", e);
		}

		return res;
	}

	private void addCreated(AbstractModelEntity entity) {
		entity.setCreatedDate(LocalDateTime.now());
		entity.setCreatedBy(env.getProperty("db.username"));
		addModified(entity);
	}

	private void addModified(AbstractModelEntity entity) {
		entity.setModifiedDate(LocalDateTime.now());
		entity.setModifiedBy(env.getProperty("db.username"));
	}
}
