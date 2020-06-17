package com.travel.service;

import com.travel.bean.UserBean;
import com.travel.dao.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

public class UserService {
    UserDAO userRepo = null;

    public UserService() throws Exception {
        userRepo = new UserDAO();
    }

    public void CreateUser(UserBean user, int type) {
        try {
            user = new UserBean("admin", "123456", "Hoang An", "antang@gmail.com", "0977765121", 12);
            UserBean loadUser = userRepo.GetUserByUserName(user.getUserName());
            if (loadUser != null) {
                System.out.println("User name already exists");
                return;
            }
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userRepo.CreateUser(user, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserBean GetUserByUserName(String userName) {
        UserBean user = null;
        try {
            user = userRepo.GetUserByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean Login(String userName, String password) {
        UserBean user = userRepo.GetUserByUserName(userName);
        if (BCrypt.checkpw(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    public ArrayList<UserBean> GetAllUsers(int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        ArrayList<UserBean> listUsers = userRepo.GetAllUsers(page, perPage);
        return listUsers;
    }

    public int UpdateUserByUserName(UserBean user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        int count = userRepo.UpdateUser(user);
        return count;
    }
}
