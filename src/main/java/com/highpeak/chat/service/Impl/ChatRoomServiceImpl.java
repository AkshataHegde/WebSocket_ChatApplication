package com.highpeak.chat.service.Impl;

import com.highpeak.chat.beans.ChatRoomBean;
import com.highpeak.chat.beans.SearchRequestBean;
import com.highpeak.chat.beans.SearchResponseBean;
import com.highpeak.chat.datastore.Repositories.ChatRoomHasUserRepository;
import com.highpeak.chat.datastore.Repositories.ChatRoomRepository;
import com.highpeak.chat.datastore.models.ChatRoomHasUser;
import com.highpeak.chat.datastore.models.ChatRoomModel;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.service.ChatRoomService;
import com.highpeak.chat.util.NullEmptyUtils;
import com.highpeak.chat.util.StringConstantUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import static com.highpeak.chat.util.StringConstantUtil.ERROR;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private static final Logger LOGGER = Logger.getLogger(ChatRoomServiceImpl.class);
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatRoomHasUserRepository chatRoomHasUserRepository;

    /**
     * Searching chat rooms
     *
     * @param searchRequestBean bean
     * @return list of chat rooms
     */
    @Override
    public SearchResponseBean searchChatRooms(SearchRequestBean searchRequestBean) {

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

        SearchResponseBean searchResponseBean=new SearchResponseBean();

        List<BigInteger> listOfChatRoomIds= chatRoomHasUserRepository.findByChatUserIdAndIsActiveTrue(searchRequestBean.getId());

        if(!NullEmptyUtils.isNullorEmpty(listOfChatRoomIds) && NullEmptyUtils.isNullorEmpty(searchRequestBean.getSearchVal())){
            searchResponseBean.setTotalRecords(chatRoomRepository.countByTypeAndIdInIsActiveTrue(StringConstantUtil.GROUP, listOfChatRoomIds));
            searchResponseBean.setEntityList(chatRoomRepository.findByTypeAndIdInIsActiveTrue(StringConstantUtil.GROUP, listOfChatRoomIds, offset, count));
        }

        else if(!NullEmptyUtils.isNullorEmpty(listOfChatRoomIds) && !NullEmptyUtils.isNullorEmpty(searchRequestBean.getSearchVal())){
            searchResponseBean.setTotalRecords(chatRoomRepository.countByTypeAndNameAndIdInIsActiveTrue(StringConstantUtil.GROUP, searchRequestBean.getSearchVal(), listOfChatRoomIds));
            searchResponseBean.setEntityList(chatRoomRepository.findByTypeAndNameAndIdInIsActiveTrue(StringConstantUtil.GROUP, searchRequestBean.getSearchVal(), listOfChatRoomIds, offset, count));
        }

        return searchResponseBean;
    }

    @Override
    public String createChatRoom(ChatRoomBean chatRoomBean) throws DataException {
        try {
            if (NullEmptyUtils.isNull(chatRoomBean)) {
                throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.INPUT_SHOULD_NOT_BE_NULL, HttpStatus.NOT_FOUND);
            }

            ChatRoomModel chatRoomModel = new ChatRoomModel();
            chatRoomModel.setName(chatRoomBean.getName());
            chatRoomModel.setType(chatRoomBean.getType());
            chatRoomModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            chatRoomModel.setActive(true);

            ChatRoomModel chatRoom = chatRoomRepository.save(chatRoomModel);

            for (BigInteger participantId : chatRoomBean.getParticipantIdList()) {
                ChatRoomHasUser chatRoomHasUser = new ChatRoomHasUser();
                chatRoomHasUser.setChatRoomId(chatRoom.getId());
                chatRoomHasUser.setChatUserId(participantId);
                chatRoomHasUser.setActive(true);
                chatRoomHasUserRepository.save(chatRoomHasUser);
            }
            return StringConstantUtil.GROUP_HAS_BEEN_CREATED_SUCCESSFULLY;
        } catch (DataException e) {
            LOGGER.error(ERROR, e);
            throw e;

        } catch (Exception e) {
            LOGGER.error(e);
            throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.SOMETHING_WENT_WRONG_WHILE_CREATING_CHAT_ROOM, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @Override
    public String addToChatRoom(BigInteger chatRoomId, List<BigInteger> userIdList) throws DataException {
        try
        {
            if(NullEmptyUtils.isNullorEmpty(chatRoomId))
            {
                throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.GROUP_ID_SHOULD_NOT_BE_NULL_OR_EMPTY,HttpStatus.NOT_FOUND);
            }

            if(NullEmptyUtils.isNullorEmpty(userIdList))
            {
                throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.USER_ID_SHOULD_NOT_BE_NULL_OR_EMPTY,HttpStatus.NOT_FOUND);
            }

            for (BigInteger userId : userIdList) {
                ChatRoomHasUser chatRoomHasUser = new ChatRoomHasUser();
                chatRoomHasUser.setChatRoomId(chatRoomId);
                chatRoomHasUser.setChatUserId(userId);
                chatRoomHasUser.setActive(true);
                chatRoomHasUserRepository.save(chatRoomHasUser);
            }
            return StringConstantUtil.USER_HAS_BEEN_ADDED_SUCCESSFULLY;
        }
        catch(DataException e)
        {
            LOGGER.error(ERROR,e);
            throw e;
        }
        catch (Exception e)
        {
            LOGGER.error(ERROR,e);
            throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.SOMETHING_WENT_WRONG_WHILE_ADDING_TO_GROUP,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @Override
    public String leaveChatRoom(BigInteger userId,BigInteger chatRoomId) throws DataException {
        try
        {
            if(NullEmptyUtils.isNullorEmpty(userId))
            {
                throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.USER_ID_SHOULD_NOT_BE_NULL_OR_EMPTY,HttpStatus.NOT_FOUND);
            }
            if(NullEmptyUtils.isNullorEmpty(chatRoomId))
            {
                throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.GROUP_ID_SHOULD_NOT_BE_NULL_OR_EMPTY,HttpStatus.NOT_FOUND);
            }

            if(chatRoomHasUserRepository.leaveGroup(chatRoomId,userId)<1)
            {
                throw new DataException(StringConstantUtil.CHAT_ERROR,StringConstantUtil.SOMETHING_WENT_WRONG_WHILE_LEAVING_GROUP,HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return StringConstantUtil.USER_SUCCESSFULLY_LEFT_THE_CHAT_ROOM;
        }
        catch (DataException e)
        {
            LOGGER.error(ERROR,e);
            throw e;
        }
        catch (Exception e)
        {
            LOGGER.error(ERROR,e);
            throw new DataException(StringConstantUtil.CHAT_ERROR,StringConstantUtil.SOMETHING_WENT_WRONG_WHILE_LEAVING_GROUP,HttpStatus.NOT_FOUND);
        }
    }
}
