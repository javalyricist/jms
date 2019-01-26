package com.amit.message;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.MessageListenerContainer;

@SpringBootApplication
@EnableJms
public class MessageExampleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MessageExampleApplication.class, args);
		MessageListenerContainer listener = context.getBean(MessageListenerContainer.class);
		listener.start();
		ConnectionFactory factory = context.getBean(ConnectionFactory.class);
		JmsTemplate template = context.getBean(JmsTemplate.class);
		template.setConnectionFactory(factory);
		template.setDefaultDestinationName("xtra");
		template.convertAndSend(new String("Hello Dear I am testing from eclipse"));
		
	}

}
