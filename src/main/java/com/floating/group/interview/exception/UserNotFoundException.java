package com.floating.group.interview.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {}

    public UserNotFoundException(Long userId) {
        super("User not found for id : " + userId);
    }
}
