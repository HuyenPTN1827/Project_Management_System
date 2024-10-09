/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.SQLException;

import context.UserDAO;
import java.util.List;
import model.User;

/**
 *
 * @author kelma
 */
public class UserService extends BaseServive {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // Đăng ký người dùng mới
    public int registerUser(User user) throws ClassNotFoundException {
        // Có thể thêm logic kiểm tra trước khi gọi DAO
        return userDAO.registerUser(user);
    }

//    // Xác thực đăng nhập
//    public boolean loginValidate(User user) throws ClassNotFoundException {
//        // Có thể thêm logic kiểm tra trước khi gọi DAO
//        return userDAO.loginValidate(user);
//    }
    public User loginValidate(User user) throws ClassNotFoundException {
        return userDAO.loginValidate(user);
    }

    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userDAO.selectAllUsers();
    }
//
//    // Lấy thông tin người dùng qua ID
//    public User getUserById(int id) {
//        return userDAO.selectUserByID(id);
//    }
//
//    // Admin thêm thông tin người dùng mới
//    public int insertUser(User user) throws SQLException {
//        return userDAO.insertUser(user);
//    }
//
//    // Cập nhật thông tin người dùng
//    public boolean updateUser(User user) throws SQLException {
//        return userDAO.updateUser(user);
//    }
//
//    // Xóa người dùng
//    public boolean deleteUser(int id) throws SQLException {
//        return userDAO.deleteUser(id);
//    }
//
    //lấy vai trò người dùng

    public String getUserRole(User user) {
        User foundUser = userDAO.selectUserByEmail(user.getEmail());
        if (foundUser != null) {
            return foundUser.getRole().getRole_name(); // Lấy role của user
        }
        return null; // Trả về null nếu không tìm thấy user
    }

    public User selectUserByEmail(String email) {
        return userDAO.selectUserByEmail(email);
    }

    //BachHD
    //28/9
    //updateMember
    public boolean updateMember(User user) {
        return userDAO.updateMember(user);
    }

    //    BachHD
    //    28/09/2024        
    //     xử lý yêu cầu đổi mật khẩu của người dùng
    public boolean changePassword(User user, String oldPassword, String newPassword) {
        // Kiểm tra nếu user là null
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        String currentPassword = user.getPassword();
        System.out.println("Current Password: " + currentPassword);

        // Kiểm tra nếu currentPassword là null
        if (currentPassword == null) {
            throw new IllegalStateException("Current password is not set for user");
        }

        // So sánh mật khẩu
        if (!currentPassword.equals(oldPassword)) {
            return false; // Mật khẩu cũ không khớp
        }

        // Cập nhật mật khẩu mới vào cơ sở dữ liệu
        return userDAO.updatePassword(user.getId(), newPassword); // Gọi phương thức updatePassword
    }

    public boolean updatePassword(int userId, String newPassword) {
        return userDAO.updatePassword(userId, newPassword);
    }

}
