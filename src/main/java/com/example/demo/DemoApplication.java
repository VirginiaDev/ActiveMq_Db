package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.example.demo.dto.Clients;

@SpringBootApplication
public class DemoApplication {
	public void getUser(String name) {
		System.out.println("Name..........."+name);
	}
	public static void main(String[] args) throws JMSException {
		
		ApplicationContext ctx =  SpringApplication.run(DemoApplication.class, args);
		
		JmsTemplate jms = ctx.getBean(JmsTemplate.class);
		String message="hello";
		
		Clients c = new Clients();
		c.setSender_details(Constants.TEST_QUEUE);
		c.setMessage(message);
		
		new ClientManager().saveIntoDb(c);
		
	    jms.convertAndSend(Constants.TEST_QUEUE, message);
		
		}
        
 
//		ApplicationContext ctx =  SpringApplication.run(DemoApplication.class, args);
//		
//		JmsTemplate jms = ctx.getBean(JmsTemplate.class);
//		
//		HashMap<String, Object> allMessages =   new GetAllMessages().getMessages();
//		
//		
//		for ( Map.Entry<String, Object> entry : allMessages.entrySet()) {
//		    String key = entry.getKey();
//		    Object message = entry.getValue();
//		    
//		    jms.convertAndSend(key, message);
//		
//		}
//		
		
//		jms.convertAndSend("James", allMessages.get("James"));
//		jms.convertAndSend("New Year", allMessages.get("New Year"));
//		jms.convertAndSend("Singh ji", allMessages.get("Singh ji"));
//		jms.convertAndSend("Joseph", allMessages.get("Joseph"));
//		jms.convertAndSend("Katrina", allMessages.get("Katrina"));
//		jms.convertAndSend("Jorden", allMessages.get("Jorden"));
//		jms.convertAndSend("Gagan", allMessages.get("Gagan"));
//		jms.convertAndSend("Kamal", allMessages.get("Kamal"));
//		jms.convertAndSend("Suresh", allMessages.get("Suresh"));
//		jms.convertAndSend("kajal", allMessages.get("kajal"));
//		jms.convertAndSend("Farhan", allMessages.get("Farhan"));
//		jms.convertAndSend("Google", allMessages.get("Google"));
//		jms.convertAndSend("Facebook", allMessages.get("Facebook"));
//		jms.convertAndSend("Havana", allMessages.get("Havana"));
//		
		

}
