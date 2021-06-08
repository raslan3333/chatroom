package com.raslan.chatroom.model;

import lombok.*;

import javax.persistence.*;
import java.io.*;

@Data
@Entity
@Table(name = "message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }

    public Message(Long id, String content, Long roomId, Long userId) {
        this.id = id;
        this.content = content;
        this.roomId = roomId;
        this.userId = userId;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "user_id")
    private Long userId;

}
