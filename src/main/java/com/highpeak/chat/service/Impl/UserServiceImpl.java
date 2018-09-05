package com.highpeak.chat.service.Impl;


import com.highpeak.chat.Bean.UserBean;
import com.highpeak.chat.Repository.UserModelRepository;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.model.UserModel;
import com.highpeak.chat.service.ChatService;
import com.highpeak.chat.util.DateUtil;
import com.highpeak.chat.util.NullEmptyUtils;
import com.highpeak.chat.util.StringConstantUtil;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.highpeak.chat.util.StringConstantUtil.ERROR;
import static com.highpeak.chat.util.StringConstantUtil.REGISTRATION_FAILED;
import static com.highpeak.chat.util.StringConstantUtil.YOU_HAVE_SUCCESSFULLY_REGISTERED_AND_LOGGED_IN;

@Service
public class UserServiceImpl implements ChatService {

    private static final Logger LOGGER=Logger.getLogger(UserServiceImpl.class);


    @Autowired
    private UserModelRepository userModelRepository;

   /* @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @Override
    public String registerUser(UserBean userBean) throws DataException {
        try
        {
            if(NullEmptyUtils.isNull(userBean))
            {
                throw new DataException(StringConstantUtil.CHAT_ERROR,StringConstantUtil.INPUT_SHOULD_NOT_BE_NULL,HttpStatus.NOT_FOUND);
            }

            UserModel userModel=new UserModel();
            userModel.setUserName(userBean.getUserName());
            userModel.setUserEmail(userBean.getEmailId());

//          BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            userModel.setPassword(userBean.getPassword());
            userModel.setIsSessionActive(true);
            userModel.setCreatedAt(DateUtil.getUTCCalenderInstance(System.currentTimeMillis()));
            userModel.setIsActive(true);
            userModel.setIsDeleted(false);

            userModelRepository.save(userModel);

            return YOU_HAVE_SUCCESSFULLY_REGISTERED_AND_LOGGED_IN;
        }
        catch(DataException e)
        {
            LOGGER.error(ERROR,e);
            throw e;

        }
        catch(Exception e)
        {
            LOGGER.error(e);
            throw new DataException(StringConstantUtil.CHAT_ERROR,REGISTRATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
