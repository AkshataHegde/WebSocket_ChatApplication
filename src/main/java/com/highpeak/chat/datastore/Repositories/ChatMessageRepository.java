package com.highpeak.chat.datastore.Repositories;

import com.highpeak.chat.datastore.models.ChatMessageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessageModel, Long> {
}
