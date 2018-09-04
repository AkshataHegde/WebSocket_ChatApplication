package com.highpeak.chat.Repository;

import com.highpeak.chat.model.ChatRoomModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomModelRepository extends JpaRepository<ChatRoomModel,Long> {
}
