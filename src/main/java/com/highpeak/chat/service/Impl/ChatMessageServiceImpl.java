package com.highpeak.chat.service.Impl;

import com.highpeak.chat.beans.SearchRequestBean;
import com.highpeak.chat.beans.SearchResponseBean;
import com.highpeak.chat.datastore.Repositories.ChatMessageRepository;
import com.highpeak.chat.datastore.Repositories.ChatRoomHasUserRepository;
import com.highpeak.chat.datastore.Repositories.ChatRoomRepository;
import com.highpeak.chat.datastore.models.ChatRoomModel;
import com.highpeak.chat.datastore.models.ChatRoomType;
import com.highpeak.chat.service.ChatMessageService;
import com.highpeak.chat.util.NullEmptyUtils;
import com.highpeak.chat.util.StringConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatRoomHasUserRepository chatRoomHasUserRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    /**
     * getting history messages of individual or group chat
     *
     * @param searchRequestBean bean
     * @return history messages
     */
    @Override
    public SearchResponseBean getHistoryMessages(SearchRequestBean searchRequestBean) {

        //setting default offset and count value
        int offset = 0;
        int count = 10;

        //offset
        if (!NullEmptyUtils.isNullorEmpty(searchRequestBean.getOffset())) {
            offset = searchRequestBean.getOffset();
        }
        //count
        if (!NullEmptyUtils.isNullorEmpty(searchRequestBean.getCount())) {
            count = searchRequestBean.getCount();
        }

        SearchResponseBean searchResponseBean = new SearchResponseBean();

        //Individual chat messages
        if(!NullEmptyUtils.isNullorEmpty(searchRequestBean.getView()) && searchRequestBean.getView().equalsIgnoreCase(ChatRoomType.INDIVIDUAL.toString()) && !NullEmptyUtils.isNullorEmpty(searchRequestBean.getId())){

            List<BigInteger> listOfChatRoomIds= chatRoomHasUserRepository.findByChatUserIdAndIsActiveTrue(searchRequestBean.getId());

            if(!NullEmptyUtils.isNullorEmpty(listOfChatRoomIds)){
                List<ChatRoomModel> chatRoomModels = chatRoomRepository.findByTypeAndIdInIsActiveTrue( StringConstantUtil.INDIVIDUAL, listOfChatRoomIds, offset, count);
                if(!NullEmptyUtils.isNull(chatRoomModels)) {
                    searchResponseBean.setTotalRecords(chatMessageRepository.countByChatRoomId(chatRoomModels.get(0).getId()));
                    searchResponseBean.setEntityList(chatMessageRepository.findByChatRoomId(chatRoomModels.get(0).getId(), offset, count));
                }
            }

        }
        //Group chat messages
        else if(!NullEmptyUtils.isNullorEmpty(searchRequestBean.getView()) && searchRequestBean.getView().equalsIgnoreCase(ChatRoomType.GROUP.toString()) && !NullEmptyUtils.isNullorEmpty(searchRequestBean.getId())){

            searchResponseBean.setTotalRecords(chatMessageRepository.countByChatRoomId(searchRequestBean.getId()));
            searchResponseBean.setEntityList(chatMessageRepository.findByChatRoomId(searchRequestBean.getId(),offset,count));
        }

        return searchResponseBean;
    }
}