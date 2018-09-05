package com.highpeak.chat.datastore.models;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "chat_message")
public class ChatMessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cm_id")
    private Long id;

    @Column(name = "cm_sender")
    private Long sender;

    @Column(name = "cm_content")
    private String content;

    @Column(name = "cm_type")
    private String type;

    @Column(name = "cm_cr_id")
    private Long chatRoomId;

    @Column(name = "cm_created_date")
    private Timestamp createdDate;

    @Column(name = "cm_is_deleted")
    private Boolean isDeleted;
}
