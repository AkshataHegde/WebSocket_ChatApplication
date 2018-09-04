package com.highpeak.chat.Repository;

import com.highpeak.chat.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserModelRepository extends JpaRepository<UserModel,Long> {
}
