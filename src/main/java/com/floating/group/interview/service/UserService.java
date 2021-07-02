package com.floating.group.interview.service;

import com.floating.group.interview.exception.UserNotFoundException;
import com.floating.group.interview.model.User;
import com.floating.group.interview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    public UserService(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User findById(Long userId) throws UserNotFoundException {
        return repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User delete(Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        repository.delete(user);
        return user;
    }

    @Override
    public List<User> deleteAll(List<Long> userIds) {
        List<User> users = repository.findAllById(userIds);
        repository.deleteAll(users);
        return users;
    }
}
