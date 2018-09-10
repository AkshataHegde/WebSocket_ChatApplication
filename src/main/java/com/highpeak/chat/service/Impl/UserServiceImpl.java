package com.highpeak.chat.service.Impl;


import com.highpeak.chat.beans.SearchRequestBean;
import com.highpeak.chat.beans.SearchResponseBean;
import com.highpeak.chat.beans.UserBean;
import com.highpeak.chat.datastore.Repositories.UserRepository;
import com.highpeak.chat.exception.DataException;
import com.highpeak.chat.datastore.models.UserModel;
import com.highpeak.chat.service.UserService;
import com.highpeak.chat.util.NullEmptyUtils;
import com.highpeak.chat.util.StringConstantUtil;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER=Logger.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

   /* @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @Override
    public String registerUser(UserBean userBean) throws DataException {
        try {
            if (NullEmptyUtils.isNull(userBean)) {
                throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.INPUT_SHOULD_NOT_BE_NULL, HttpStatus.NOT_FOUND);
            }

            UserModel userModel = new UserModel();
            if(!NullEmptyUtils.isNullorEmpty(userBean.getName())) {
                userModel.setName(userBean.getName());
            }
            userModel.setUserName(userBean.getUserName());
            userModel.setEmail(userBean.getEmailId());

//          BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
            userModel.setPassword(userBean.getPassword());
            if(!NullEmptyUtils.isNullorEmpty(userBean.getPhoneNumber())){
                userModel.setPhoneNumber(userBean.getPhoneNumber());
            }
            userModel.setSessionActive(true);
            userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            userModel.setActive(true);

            userRepository.save(userModel);

            return StringConstantUtil.YOU_HAVE_SUCCESSFULLY_REGISTERED_AND_LOGGED_IN;
        } catch (DataException e) {
            LOGGER.error(StringConstantUtil.ERROR, e);
            throw e;

        } catch (Exception e) {
            LOGGER.error(e);
            throw new DataException(StringConstantUtil.CHAT_ERROR, StringConstantUtil.REGISTRATION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    /**
     * searching user
     *
     * @param searchRequestBean bean
     * @return list of users
     */
    @Override
    public SearchResponseBean searchUsers(SearchRequestBean searchRequestBean) {
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

        String searchVal = searchRequestBean.getSearchVal();

        //searching parameters are null so fetching all the users
        if (NullEmptyUtils.isNullorEmptyOrNullString(searchVal)) {
            searchResponseBean.setTotalRecords(userRepository.countAll());
            searchResponseBean.setEntityList(userRepository.findAll(offset, count));
        }

        //searching users by search parameters
        else if (!NullEmptyUtils.isNullorEmptyOrNullString(searchVal)) {
            searchResponseBean.setTotalRecords(userRepository.countBySearchValue(searchVal, searchVal, searchVal));
            searchResponseBean.setEntityList(userRepository.findBySearchValue(searchVal, searchVal, searchVal, offset, count));
        }

        return searchResponseBean;
    }
}
