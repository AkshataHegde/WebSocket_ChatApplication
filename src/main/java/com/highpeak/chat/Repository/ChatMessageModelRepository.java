package com.highpeak.chat.Repository;

import com.highpeak.chat.model.ChatMessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageModelRepository extends JpaRepository<ChatMessageModel,Long> {
}
