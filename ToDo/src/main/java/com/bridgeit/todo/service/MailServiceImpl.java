package com.bridgeit.todo.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


public class MailServiceImpl implements MailService{

	//@Autowired
    private MailSender mailSender;
   
   public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	} 
   
	@Override
	public void sendMail(String to) {
		
		 
		 SimpleMailMessage message = new SimpleMailMessage();
		
		
		 message.setFrom("mdfirozahmad222@gmail.com");
		 message.setTo(to);
		 message.setSubject("hello...");
		 message.setText("Successfully send....");
			mailSender.send(message);
		 
	}

}