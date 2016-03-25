package ro.estore.model.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(RootJpaHibernateConfig.class)
@PropertySource("classpath:test.properties")
public class JpaHibernateTestConfig {
	
}