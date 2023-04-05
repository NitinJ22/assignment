package com.latentview.assignment.service;

import com.latentview.assignment.entity.UserData;
import com.latentview.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserData getUser(UserData user) throws IllegalArgumentException{
        UserData userData = userRepo.findByName(user.getName());
        if(Objects.nonNull(userData)){
            if(userData.getPassword().equals(user.getPassword())) {
                return userData;
            }
        }
        return null;
    }

    public UserData userAuthenticate(UserData user) throws IllegalArgumentException{
        UserData userData = userRepo.findUserByUserNameAndPassword(user.getName(), user.getPassword());
        if(Objects.nonNull(userData)){
            return userData;
        }
        return null;
    }

    public UserData saveUser(UserData userData){
        UserData user = userRepo.findUserByUserNameAndPasswordAndEmail(userData.getName(), userData.getPassword(), userData.getEmail());
        if(Objects.isNull(user)){
            user = userRepo.save(userData);
            return user;
        }else{
            return null;
        }
    }
}
