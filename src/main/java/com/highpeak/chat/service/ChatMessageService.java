package com.highpeak.chat.service;

import com.highpeak.chat.beans.SearchRequestBean;
import com.highpeak.chat.beans.SearchResponseBean;

public interface ChatMessageService {

    /**
     * getting history messages of individual or group chat
     *
     * @param searchRequestBean bean
     * @return history messages
     */
    SearchResponseBean getHistoryMessages(SearchRequestBean searchRequestBean);

}
