package com.highpeak.chat.datastore.Repositories;

import com.highpeak.chat.datastore.models.ChatRoomHasUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomHasUserRepository extends JpaRepository<ChatRoomHasUser, Long> {
}
