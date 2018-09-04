package com.highpeak.chat.model;

import javax.persistence.*;

@Entity
@Table(name = "chat_message")
public class ChatMessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cm_id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "cm_sender",referencedColumnName = "cu_id")
    private ChatModel sender;

    @Column(name = "cm_to")
    private Long to;

    @Column(name = "cm_message")
    private String message;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public ChatModel getSender() {
        return sender;
    }

    public void setSender(ChatModel sender) {
        this.sender = sender;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
