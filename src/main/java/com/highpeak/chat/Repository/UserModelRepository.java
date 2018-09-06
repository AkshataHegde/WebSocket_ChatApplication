package com.highpeak.chat.Repository;

import com.highpeak.chat.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel,Long> {

    List<UserModel> findByUserIdInAndIsActiveTrueAndIsDeletedFalse(List<Long> userIdList);
}
