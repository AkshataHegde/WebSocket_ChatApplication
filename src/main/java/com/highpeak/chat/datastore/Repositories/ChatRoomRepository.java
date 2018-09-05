package com.highpeak.chat.datastore.Repositories;

import com.highpeak.chat.datastore.models.ChatRoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomModel, Long> {
}
