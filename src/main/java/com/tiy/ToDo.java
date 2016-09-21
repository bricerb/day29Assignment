package com.tiy;

import javax.persistence.*;

/**
 * Created by Brice on 9/15/16.
 */

@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    @Column
    boolean isDone = false;

    @ManyToOne
    User user;

    public ToDo() {
    }

    public ToDo(String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
