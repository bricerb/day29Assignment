package com.tiy;

import javax.persistence.*;

/**
 * Created by Brice on 9/15/16.
 */

@Entity
@Table
public class ToDo {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    public ToDo() {
    }

    public ToDo(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
