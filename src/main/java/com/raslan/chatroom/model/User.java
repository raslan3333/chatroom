package com.raslan.chatroom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private int id;
    @Column
    private String username;
    private String email;

}
