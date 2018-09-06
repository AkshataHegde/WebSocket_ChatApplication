package com.highpeak.chat.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;

@Entity
@Data
@Table(name="chat_room_to_user_model")
public class ChatRoomToUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cr_u_id")
    private Long chatRoomToUserId;


    @Column(name = "cr_u_cr_id")
    private Long cuChatRoomId;

    @Column(name = "cr_u_u_id")
    private Long cuUserId;
}
