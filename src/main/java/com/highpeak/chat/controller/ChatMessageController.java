package com.highpeak.chat.controller;

import com.highpeak.chat.beans.SearchRequestBean;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.service.ChatMessageService;
import com.highpeak.chat.util.MessageBundleResource;
import com.highpeak.chat.util.NullEmptyUtils;
import com.highpeak.chat.util.StringConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/chatMessages")
public class ChatMessageController extends AbstractRestService{

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private MessageBundleResource messageBundle;

    /**
     * getting history messages of individual or group chat
     *
     * @param searchRequestBean bean
     * @return history messages
     */
    @RequestMapping(value = "/getHistoryMsg", method = RequestMethod.POST)
    public ResponseEntity<?> getHistoryMessages(@RequestBody SearchRequestBean searchRequestBean){
        try {
            if (NullEmptyUtils.isNull(searchRequestBean)) {
                throw new DataException(StringConstantUtil.EXCEPTION, messageBundle.getMessage(StringConstantUtil.EMPTY_FIELD), HttpStatus.BAD_REQUEST);
            }

            return buildResponse(chatMessageService.getHistoryMessages(searchRequestBean));
        }catch(DataException e){
           return buildError(e);
        }
    }
}
