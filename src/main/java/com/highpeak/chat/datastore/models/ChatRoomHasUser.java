package com.highpeak.chat.datastore.models;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "chat_room_has_user")
public class ChatRoomHasUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cru_id")
    private BigInteger id;

    @Column(name = "cru_cr_id")
    private BigInteger chatRoomId;

    @Column(name = "cru_cu_id")
    private BigInteger chatUserId;

    @Column(name = "cru_is_active")
    private boolean isActive;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(BigInteger chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public BigInteger getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(BigInteger chatUserId) {
        this.chatUserId = chatUserId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
