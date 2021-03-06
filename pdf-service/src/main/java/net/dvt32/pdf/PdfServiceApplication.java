package net.dvt32.pdf;

import javax.jms.ConnectionFactory;

import org.apache.activemq.broker.BrokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJms
public class PdfServiceApplication {
	
	@Bean
    public JmsListenerContainerFactory<?> myFactory(
    	ConnectionFactory connectionFactory,
        DefaultJmsListenerContainerFactoryConfigurer configurer) 
	{
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

	@Bean
    public BrokerService broker() 
    	throws Exception 
    {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        return broker;
    }
	
    @Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(PdfServiceApplication.class, args);
	}
	
}