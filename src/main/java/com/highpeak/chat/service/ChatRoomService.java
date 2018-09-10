package com.highpeak.chat.service;

import com.highpeak.chat.beans.ChatRoomBean;
import com.highpeak.chat.beans.SearchRequestBean;
import com.highpeak.chat.beans.SearchResponseBean;
import com.highpeak.chat.exception.DataException;

import java.math.BigInteger;
import java.util.List;

public interface ChatRoomService {

    /**
     * Searching chat rooms
     *
     * @param searchRequestBean bean
     * @return list of chat rooms
     */
    SearchResponseBean searchChatRooms(SearchRequestBean searchRequestBean);

    String createChatRoom(ChatRoomBean chatRoomBean) throws DataException;

    String addToChatRoom(BigInteger chatRoomId, List<BigInteger> userIdList) throws DataException;

    String leaveChatRoom(BigInteger userId,BigInteger groupId) throws DataException;
}
