package com.travel.service;

import com.travel.bean.UserBean;
import com.travel.dao.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

public class UserService {
    UserDAO userDAO = null;

    public UserService() throws Exception {
        userDAO = new UserDAO();
    }

    public boolean CreateUser(UserBean user, int type) {
        try {
            UserBean loadUser = userDAO.GetUserByUserName(user.getUserName());
            if (loadUser != null) {
                System.out.println("User name already exists");
                return false;
            }
            if (user == null) {
                return false;
            }
            if (user.getUserName() == null || user.getPassword() == null || user.getPhone() == null) {
                return false;
            }
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userDAO.CreateUser(user, type);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public UserBean GetUserByUserName(String userName) {
        UserBean user = null;
        try {
            user = userDAO.GetUserByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean Login(String userName, String password) {
        UserBean user = userDAO.GetUserByUserName(userName);
        if (BCrypt.checkpw(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    public ArrayList<UserBean> GetAllUsers(int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        ArrayList<UserBean> listUsers = userDAO.GetAllUsers(page, perPage);
        return listUsers;
    }

    public int UpdateUserByUserName(UserBean user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        int count = userDAO.UpdateUser(user);
        return count;
    }

    public boolean DeactivateUser(int userID) {
        if (userID <= 0) {
            return false;
        }
        return userDAO.DeactivateUser(userID);
    }
}
