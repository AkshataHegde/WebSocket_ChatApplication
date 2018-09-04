package com.highpeak.chat.model;

import javax.persistence.*;


@Entity
@Table(name = "chat_users")
public class ChatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cu_id")
    private Long chatUserId;

    @Column(name = "cu_name")
    private String chatUserName;

    @Column(name = "cu_is_session_active")
    private Boolean isSessionActive;

    public Long getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(Long chatUserId) {
        this.chatUserId = chatUserId;
    }

    public String getChatUserName() {
        return chatUserName;
    }

    public void setChatUserName(String chatUserName) {
        this.chatUserName = chatUserName;
    }

    public Boolean getSessionActive() {
        return isSessionActive;
    }

    public void setSessionActive(Boolean sessionActive) {
        isSessionActive = sessionActive;
    }
}
