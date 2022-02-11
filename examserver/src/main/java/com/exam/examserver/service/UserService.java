package com.exam.examserver.service;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface UserService {

    //creating User
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get User by userName
    public User getUser(String userName);

    public void deleteUser(Long userId);

    public User updateUserPhone(Long userId, String newPhone);

    public default User updateEmail(Long userId, String newEmail){
        return null;
    }

}
