package com.bridgeit.todo.service;

import java.util.HashMap;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

public class ProducerImpl implements Producer {

	

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

  
    public void send(HashMap map) {
        this.template.convertAndSend(queue.getName(), map);
        System.out.println(" [x] Sent '" + map + "'");
    }


}
	
}
