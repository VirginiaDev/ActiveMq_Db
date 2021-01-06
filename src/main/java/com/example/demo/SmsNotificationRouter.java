package com.example.demo;

import com.example.demo.dto.Message;
import com.example.demo.processor.MessageProcessor;
import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Predicate;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.smpp.SmppException;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

		rest("/message")
				.post("/add").consumes(MediaType.APPLICATION_JSON_VALUE).type(Message.class).outType(Message.class)
				.to("seda:newMessage");


//		from("seda:newMessage?concurrentConsumers=20").routeId("smpp-sender").process(messageProcessor)
//				.setHeader("CamelSmppDestAddr",simple("91${in.body.sender}"))
//				.setBody(simple("${in.body.messageBody}"))
//				.to("smpp://{{smpp.tr.systemid}}@{{smpp.tr.host}}:{{smpp.tr.port}}?password={{smpp.tr.password}}&enquireLinkTimer=3000&transactionTimer=5000&sourceAddrTon={{smpp.source.addr.ton}}&sourceAddrNpi={{smpp.source.addr.npi}}&destAddrTon={{smpp.dest.addr.ton}}&destAddrNpi={{smpp.dest.addr.npi}}&sourceAddr={{smpp.source.address}}");

		
		/**
		 * This Camel route handles messages going out to the SMS world
		 */
		
	}
	
	public static void main(String args[]) {
		try {
			new SmsNotificationRouter().configure();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
