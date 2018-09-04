package com.highpeak.chat.Repository;

import com.highpeak.chat.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageModelRepository extends JpaRepository<MessageModel,Long> {
}
