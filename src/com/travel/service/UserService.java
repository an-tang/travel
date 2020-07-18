package com.travel.service;

import com.travel.bean.UserBean;
import com.travel.dao.UserDAO;
import com.travel.enumerize.Role;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

public class UserService {
    UserDAO userDAO = null;

    public UserService() throws Exception {
        userDAO = new UserDAO();
    }

    public String CreateUser(UserBean user, int role) {
        try {
            UserBean existingUser = userDAO.GetUserByUserName(user.getUserName());
            if (existingUser != null) {
                return "Tài khoản với tên đăng nhập này đã tồn tại";
            }
            if (user.getUserName() == null || user.getPassword() == null || user.getPhone() == null) {
                return "Tên đăng nhập, mật khẩu hoặc số điện thoại không được để trống.\nXác nhận mật khẩu phải trùng khớp.";
            }
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            userDAO.CreateUser(user, role);
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception thrown";
        }

        return "success";
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
        if (user != null) {
            String userPwd = user.getPassword();
            return BCrypt.checkpw(password, userPwd);
        }
        return false;
    }

    public ArrayList<UserBean> GetAllUsers(int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        ArrayList<UserBean> listUsers = userDAO.GetAllUsers(page, perPage);
        return listUsers;
    }

    public String UpdateUserByUserName(UserBean user) {
        UserBean existingUser = userDAO.GetUserByUserName(user.getUserName());
        if (existingUser == null) {
            return "Không tìm thấy tài khoản trong hệ thống";
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

        int count = userDAO.UpdateUser(user);
        if (count == 0) {
            return "Cập nhật thông tin thất bại";
        }
        return "Success";
    }

    public int UpdateUserByUserNameWithoutPassword(UserBean user) {
        user.setPassword(user.getPassword());
        int count = userDAO.UpdateUser(user);
        return count;
    }

    public boolean DeactivateUser(int userID) {
        if (userID <= 0) {
            return false;
        }
        return userDAO.DeactivateUser(userID);
    }

    public boolean IsAdmin(String username) {
        int role = userDAO.GetUserRole(username);
        if (role == Role.ADMIN.getValue()) {
            return true;
        }

        return false;
    }

    public UserBean GetUserByID(int id) {
        if (id <= 0) {
            return null;
        }

        return userDAO.GetUserByID(id);
    }
}
