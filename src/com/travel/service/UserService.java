package com.travel.service;

import com.travel.model.UserModel;
import com.travel.repository.UserRepository;

public class UserService {
    UserRepository userRepo = null;

    public UserService() throws Exception {
        userRepo = new UserRepository();
    }

    public void CreateUser(UserModel user) {
        try {
            userRepo.CreateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserModel GetUserByUserName(String userName){
        UserModel user = null;
        try {
            user = userRepo.GetUserByUserName(userName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
