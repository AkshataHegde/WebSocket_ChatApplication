package com.highpeak.chat.service;


import com.highpeak.chat.beans.SearchRequestBean;
import com.highpeak.chat.beans.SearchResponseBean;
import com.highpeak.chat.beans.UserBean;
import com.highpeak.chat.exception.DataException;


public interface UserService {

    String registerUser(UserBean userBean) throws DataException;

    /**
     * searching user
     *
     * @param searchRequestBean bean
     * @return list of users
     */
    SearchResponseBean searchUsers(SearchRequestBean searchRequestBean);
}
