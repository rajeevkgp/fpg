package com.floating.group.interview.service;

import com.floating.group.interview.exception.UserNotFoundException;
import com.floating.group.interview.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    User findById(Long userId) throws UserNotFoundException;

    User save(User user);

    User delete(Long userId);

    List<User> deleteAll(List<Long> userIds);
}
