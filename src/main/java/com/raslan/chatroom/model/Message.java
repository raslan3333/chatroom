package com.raslan.chatroom.model;

import lombok.*;

import javax.persistence.*;
import java.io.*;
import java.util.*;

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

    public Message(Long id, String content, Long roomId) {
        this.id = id;
        this.content = content;
        this.roomId = roomId;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "room_id")
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
