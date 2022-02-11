package com.exam.examserver.service;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.repo.RoleRepository;
import com.exam.examserver.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    //Creating User
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local=this.userRepository.findByUserName(user.getUserName());
        if(local != null){
            System.out.println("User already exists");
            throw new Exception("User already present");
        } else{
            //user Create
            for(UserRole userRole: userRoles){
                roleRepository.save(userRole.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
            return local;
        }
    }

    @Override
    public User getUser(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User updateUserPhone(Long userId, String newPhone) {
        User local = this.userRepository.getById(userId);
        local.setPhone(newPhone);
        local = this.userRepository.save(local);
        return local;
    }


}
