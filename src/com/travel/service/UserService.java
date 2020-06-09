package com.travel.service;

import com.travel.model.UserModel;
import com.travel.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    UserRepository userRepo = null;

    public UserService() throws Exception {
        userRepo = new UserRepository();
    }

    public void CreateUser(UserModel user, int type) {
        try {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepo.CreateUser(user, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserModel GetUserByUserName(String userName) {
        UserModel user = null;
        try {
            user = userRepo.GetUserByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean Login(String userName, String password) {
        UserModel user = userRepo.GetUserByUserName(userName);
        if (BCrypt.checkpw(password, user.getPassword())) {
            return true;
        }
        return false;
    }
}
