package com.highpeak.chat.service;


import com.highpeak.chat.Bean.UserBean;
import com.highpeak.chat.exception.DataException;
import org.springframework.stereotype.Service;


public interface ChatService {

    String registerUser(UserBean userBean) throws DataException;



}
