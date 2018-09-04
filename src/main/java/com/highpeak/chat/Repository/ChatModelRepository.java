package com.highpeak.chat.Repository;

import com.highpeak.chat.model.ChatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatModelRepository extends JpaRepository<ChatModel,Long> {
}
