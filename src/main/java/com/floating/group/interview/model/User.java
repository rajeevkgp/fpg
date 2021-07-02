package com.floating.group.interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected User copy() {
        return new User(new String(this.name));
    }
}
