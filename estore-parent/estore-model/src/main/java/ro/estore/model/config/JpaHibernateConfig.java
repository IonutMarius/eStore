package ro.estore.model.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Import(RootJpaHibernateConfig.class)
public class JpaHibernateConfig {

}