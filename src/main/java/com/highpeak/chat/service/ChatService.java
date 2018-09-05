package com.highpeak.chat.service;


import com.highpeak.chat.beans.UserBean;
import com.highpeak.chat.exception.DataException;


public interface ChatService {

    String registerUser(UserBean userBean) throws DataException;



}
