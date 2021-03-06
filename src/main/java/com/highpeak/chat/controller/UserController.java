package com.highpeak.chat.controller;

import com.highpeak.chat.beans.SearchRequestBean;
import com.highpeak.chat.beans.UserBean;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.service.UserService;
import com.highpeak.chat.util.MessageBundleResource;
import com.highpeak.chat.util.NullEmptyUtils;
import com.highpeak.chat.util.StringConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/users")
public class UserController extends AbstractRestService {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageBundleResource messageBundle;

    @PostMapping("/registration")
    public String registerUser(@RequestBody UserBean userBean) throws DataException {
        try
        {
            return userService.registerUser(userBean);
        }
        catch(DataException e)
        {
            throw e;
        }
    }

    /**
     * searching user
     *
     * @param searchRequestBean bean
     * @return list of users
     */
    @RequestMapping(value = "/searchUser", method = RequestMethod.POST)
    public ResponseEntity<?> searchUser(@RequestBody SearchRequestBean searchRequestBean) {
        try {
            if (NullEmptyUtils.isNull(searchRequestBean)) {
                throw new DataException(StringConstantUtil.EXCEPTION, messageBundle.getMessage(StringConstantUtil.EMPTY_FIELD), HttpStatus.BAD_REQUEST);
            }

            return buildResponse(userService.searchUsers(searchRequestBean));
        }catch(DataException e){
            return buildError(e);
        }
    }

}
