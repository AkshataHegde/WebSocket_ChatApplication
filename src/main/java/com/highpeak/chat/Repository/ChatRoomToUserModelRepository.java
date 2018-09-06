package com.highpeak.chat.Repository;

import com.highpeak.chat.model.ChatRoomToUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomToUserModelRepository extends JpaRepository<ChatRoomToUserModel,Long> {
}
