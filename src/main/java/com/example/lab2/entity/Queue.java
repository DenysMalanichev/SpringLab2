package com.example.lab2.entity;

import jakarta.persistence.*;
import java.util.Stack;

@Entity
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isOpen;

    @OneToOne
    private User owner;

    @OneToMany(mappedBy = "queue")
    private Stack<User> users;

    public Queue(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.isOpen = true;
        this.users = new Stack<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Stack<User> getUsers() {
        return users;
    }

    public void setUsers(Stack<User> users) {
        this.users = users;
    }
}
