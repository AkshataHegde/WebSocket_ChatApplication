package com.highpeak.chat.datastore.models;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "chat_room")
public class ChatRoomModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cr_id")
    private Long id;

    @Column(name = "cr_name")
    private String name;

    @Column(name = "cr_type")
    private String type;

    @Column(name = "cr_created_date")
    private Timestamp createdDate;

    @Column(name = "cr_is_active")
    private boolean isActive;

}
