package com.highpeak.chat.datastore.models;

import javax.persistence.*;

@Entity
@Table(name = "chat_room_has_user")
public class ChatRoomHasUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cru_id")
    private Long id;

    @Column(name = "cru_cr_id")
    private int chatRoomId;

    @Column(name = "cru_cu_id")
    private int chatUserId;

    @Column(name = "cru_is_active")
    private boolean isActive;
}
