package com.floating.group.interview.dto;

import com.floating.group.interview.model.User;

public class UserDto extends User {

    public User toUser() {
        return super.copy();
    }
}
