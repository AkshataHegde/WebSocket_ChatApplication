package com.highpeak.chat.controller;

import com.highpeak.chat.Bean.ChatMessage;
import com.highpeak.chat.Repository.UserModelRepository;
import com.highpeak.chat.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserModelRepository userModelRepository;

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

        UserModel userModel=new UserModel();
        userModel.setUserName(chatMessage.getSender());
        userModel.setUserEmail(chatMessage.getEmailId());
        userModel.setIsSessionActive(true);
        userModel.setCreatedAt();
        userModel.setIsActive(true);
        userModel.setIsDeleted(false);

        userModelRepository.save(userModel);

        return chatMessage;
    }

    @MessageMapping("/chat.sendMessageToUser/{user}/message")
    public void sendMessageToUser(@Payload ChatMessage chatMessage, @DestinationVariable("user") String user) {

        System.out.println("Message " + chatMessage.getContent());
        System.out.println("UserName " + user);
        simpMessagingTemplate.convertAndSendToUser(user, "/message", chatMessage);

    }

}