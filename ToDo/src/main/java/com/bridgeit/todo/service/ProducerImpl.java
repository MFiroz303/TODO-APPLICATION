/*package com.bridgeit.todo.service;

import java.util.HashMap;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class ProducerImpl implements Producer {

	

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

  
    public void send(HashMap map) {
        this.template.convertAndSend( queue.getName(), map);
        System.out.println(" [x] Sent '" + map + "'");
    }
}
*/