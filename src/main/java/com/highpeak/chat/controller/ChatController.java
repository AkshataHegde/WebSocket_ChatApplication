package com.highpeak.chat.controller;

import com.highpeak.chat.beans.ChatMessage;
import com.highpeak.chat.datastore.Repositories.UserRepository;
import com.highpeak.chat.datastore.models.UserModel;
import com.highpeak.chat.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket spring.jpa.hibernate.ddl-auto=createsession
        System.out.println("ChatMessage Object  "+chatMessage);
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());


        //storing user details into database
        UserModel userModel=new UserModel();
        userModel.setName(chatMessage.getSender());
        userModel.setEmail(chatMessage.getEmailId());
        userModel.setSessionActive(true);
        userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        userModel.setActive(true);

        userRepository.save(userModel);

        return chatMessage;
    }

    @MessageMapping("/chat.sendMessageToUser/{user}/message")
    public void sendMessageToUser(@Payload ChatMessage chatMessage, @DestinationVariable("user") String user) {

        System.out.println("Message " + chatMessage.getContent());
        System.out.println("UserName " + user);
        simpMessagingTemplate.convertAndSendToUser(user, "/message", chatMessage);

    }

}