package com.exam.examserver.controller;

import com.exam.examserver.model.Role;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    //Creating User
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        Set<UserRole> userRoleSet = new HashSet<>();
        Role role = new Role();
        role.setRoleName("NORMAL");
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRoleSet.add(userRole);
        return this.userService.createUser(user, userRoleSet);
    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable("userName") String userName){
        return this.userService.getUser(userName);
    }

    @PatchMapping("/{userId}/phone")
    public User updateUser(@PathVariable("userId") Long userId, @RequestBody String newPhone){
        return this.userService.updateUserPhone(userId, newPhone);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
    }



}
