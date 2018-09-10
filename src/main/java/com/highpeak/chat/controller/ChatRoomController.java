package com.highpeak.chat.controller;

import com.highpeak.chat.beans.ChatRoomBean;
import com.highpeak.chat.beans.SearchRequestBean;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.service.ChatRoomService;
import com.highpeak.chat.util.MessageBundleResource;
import com.highpeak.chat.util.NullEmptyUtils;
import com.highpeak.chat.util.StringConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/rest/chatRooms")
public class ChatRoomController extends AbstractRestService {


    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MessageBundleResource messageBundle;

    /**
     * Searching chat rooms
     *
     * @param searchRequestBean bean
     * @return list of chat rooms
     */
    @RequestMapping(value="/searchChatRooms", method = RequestMethod.POST)
    public ResponseEntity<?> searchChatRoom(@RequestBody SearchRequestBean searchRequestBean){

        try {
            if (NullEmptyUtils.isNull(searchRequestBean) || NullEmptyUtils.isNull(searchRequestBean.getId())) {
                throw new DataException(StringConstantUtil.EXCEPTION, messageBundle.getMessage(StringConstantUtil.EMPTY_FIELD), HttpStatus.BAD_REQUEST);
            }

            return buildResponse(chatRoomService.searchChatRooms(searchRequestBean));
        }catch(DataException e){
            return buildError(e);
        }
    }

    @PostMapping("/group")
    public String createGroup(@RequestBody ChatRoomBean chatRoomBean) throws DataException {
        try
        {
            return chatRoomService.createChatRoom(chatRoomBean);
        }
        catch (DataException e)
        {
            throw e;
        }
    }

    @PostMapping("/subscription/{chatRoomId}")
    public String addToChatRoom(@RequestBody List<BigInteger> userIdList, @PathVariable("chatRoomId") BigInteger chatRoomId) throws DataException {
        try
        {
            return chatRoomService.addToChatRoom(chatRoomId,userIdList);
        }
        catch (DataException e)
        {
            throw e;
        }
    }

    @PostMapping("/unSubscription/{chatRoomId}/{userId}")
    public String leaveChatRoom(@PathVariable("userId") BigInteger userId, @PathVariable("chatRoomId") BigInteger chatRoomId) throws DataException {
        try
        {
            return chatRoomService.leaveChatRoom(userId,chatRoomId);
        }
        catch (DataException e)
        {
            throw e;
        }
    }
}
