package com.highpeak.chat.controller;

import com.highpeak.chat.beans.UserBean;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/chat")
public class UserController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/registration")
    public String registerUser(@RequestBody UserBean userBean) throws DataException {
        try
        {
           return chatService.registerUser(userBean);
        }
        catch(DataException e)
        {
            throw e;
        }
    }

//    @PostMapping("/group")
//    public String createGroup(@RequestBody List<>)




}
