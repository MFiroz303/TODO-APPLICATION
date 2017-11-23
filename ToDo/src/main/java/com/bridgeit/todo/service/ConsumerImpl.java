/*package com.bridgeit.todo.service;

import org.springframework.beans.factory.annotation.Autowired;

public class ConsumerImpl implements Consumer{
	
	 
	@Autowired
	Mailer mailer;


	public void onMessage(Message messsage) {
		
		byte body[]=messsage.getBody();
		Map map = (HashMap)SerializationUtils.deserialize(body);
		mailer.send(map.get("to")+"", map.get("message")+"");
	}

}

}
*/