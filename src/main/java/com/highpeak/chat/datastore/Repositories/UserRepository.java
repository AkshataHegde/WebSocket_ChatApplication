package com.highpeak.chat.datastore.Repositories;

import com.highpeak.chat.datastore.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
}
