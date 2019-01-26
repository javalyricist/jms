package com.amit.message.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

@Configuration
@ComponentScan
public class AppConfig {
	public static final String QUEUE_NAME = "xtra";

	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		return connectionFactory;
	}

	@Bean
	public MessageListenerContainer listenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setDestinationName(QUEUE_NAME);
		container.setMessageListener(new MyJmsListener());
		return container;
	}
}