package ro.estore.ws.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
@ComponentScan("ro.estore.ws.soap")
public class WebContextConfig extends WsConfigurerAdapter {

	@Bean(name = "user")
	public DefaultWsdl11Definition userWsdl11Definition(XsdSchema userSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("UserPort");
		wsdl11Definition.setLocationUri("/ws/");
		wsdl11Definition.setTargetNamespace("http://estore.ro/ws/soap/user");
		wsdl11Definition.setSchema(userSchema);
		return wsdl11Definition;
	}

	@Bean(name = "store")
	public DefaultWsdl11Definition storeWsdl11Definition(XsdSchema storeSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("StorePort");
		wsdl11Definition.setLocationUri("/ws/");
		wsdl11Definition.setTargetNamespace("http://estore.ro/ws/soap/store");
		wsdl11Definition.setSchema(storeSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema userSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/user.xsd"));
	}

	@Bean
	public XsdSchema storeSchema() {
		return new SimpleXsdSchema(new ClassPathResource("xsd/store.xsd"));
	}
}
