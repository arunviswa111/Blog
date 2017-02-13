package com.niit.controller;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.niit.model.Message;
import com.niit.model.OutputMessage;

@Controller
public class ChatController {
	
	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public OutputMessage sendMessage(Message message){
		System.out.println("Inside chat contoller of back end...");
		return new OutputMessage(message,new Date());
	}
}




