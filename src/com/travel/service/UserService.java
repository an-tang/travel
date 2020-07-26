package com.travel.service;

import com.travel.bean.UserBean;
import com.travel.dao.UserDAO;
import com.travel.enumerize.Role;
import com.travel.enumerize.Status;
import com.travel.viewmodel.UserReport;
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

        int count = userDAO.UpdateUser(user);
        if (count == 0) {
            return "Cập nhật thông tin thất bại";
        }
        return "success";
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
        return userDAO.UpdateUserStatus(userID, Status.DEACTIVE);
    }

    public boolean ActivateUser(int userID) {
        if (userID <= 0) {
            return false;
        }
        return userDAO.UpdateUserStatus(userID, Status.ACTIVE);
    }

    // Sample: GetAllUsersHaveSorting("name", "ASC", 1, 0, 5)
    public ArrayList<UserBean> GetAllUsersHaveSorting(String fieldName, String sortType, int status, int page, int perPage){
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        fieldName = ((fieldName == null) || (fieldName == "")) ? "user_name" : fieldName;
        sortType = ((sortType == null) || (sortType == "")) ? "ASC" : sortType;
        status = status < 0 ? 2 : status;

        String params = status == 2 ? "" : " AND status = " + status;
        params = params + " ORDER BY " + fieldName + " " + sortType;

        return userDAO.GetAllUsersHaveSorting(params, page, perPage);
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

    public String UpdatePassword(String userName, String password){
        UserBean existingUser = userDAO.GetUserByUserName(userName);
        if (existingUser != null) {
            return "Tài khoản không tồn tại";
        }
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        if (userDAO.UpdatePassword(userName, hashPassword)){
            return "success";
        }

        return "Cập nhật mật khẩu thất bại";
    }

    public ArrayList<UserReport> TopUsersByOrder(int limit){
        limit = Math.max(limit, 10);

        return userDAO.TopUsersByOrder(limit);
    }

    public ArrayList<UserBean> GetUserInAdminPageByKeyword(String keyword, int page, int perPage){
        keyword = keyword.replace(" ", "&");
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;

        return userDAO.GetUserInAdminPageByKeyword(keyword, page, perPage);
    }
}
