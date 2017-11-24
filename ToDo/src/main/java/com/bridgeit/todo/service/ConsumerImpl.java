/*package com.bridgeit.todo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.SerializationUtils;



public class ConsumerImpl implements MessageListener{
	 
	@Autowired
	private MailServiceImpl mailSender;


	public void onMessage(Message messsage) {
		
		byte body[]= messsage.getBody();
		System.out.println("in consumer");
		Map map = (HashMap)SerializationUtils.deserialize(body);
		mailSender.sendMail(map.get("to")+"" , "", map.get("message")+"" , "");
	}

}
*/