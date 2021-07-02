package com.floating.group.interview.controller;

import com.floating.group.interview.dto.UserDto;
import com.floating.group.interview.exception.UserNotFoundException;
import com.floating.group.interview.model.User;
import com.floating.group.interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    /**
     * curl localhost:8090/fpg/user/all
     * @return list of all users available
     */
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * curl localhost:8090/fpg/user/1
     * @param userId
     * @return user found for the given id
     * @throws UserNotFoundException
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable("id") final Long userId) throws UserNotFoundException {
        return userService.findById(userId);
    }

    /**
     * curl localhost:8090/fpg/user -H'content-type: application/json' -d'{"name":"rajeev"}'
     * @param userDto : user body to be created
     * @return created user body
     */
    @PostMapping
    public User createUser(@RequestBody final UserDto userDto) {
        return userService.save(userDto.toUser());
    }

    /**
     * curl -XDELETE localhost:8090/fpg/user/1
     * @param userId : user id to be deleted
     * @return deleted user id
     * @throws UserNotFoundException
     */
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") final Long userId) throws UserNotFoundException {
        return userService.delete(userId);
    }

    /**
     * curl -XDELETE localhost:8090/fpg/user -H'content-type: application/json' -d'[3,4]'
     * @param userIds : list of user ids to be deleted
     * @return deleted list of user ids
     */
    @DeleteMapping
    public List<User> deleteUser(@RequestBody final List<Long> userIds) {
        return userService.deleteAll(userIds);
    }
}
