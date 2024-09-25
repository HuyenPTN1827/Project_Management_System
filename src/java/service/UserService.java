/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.UserDAO;
import java.util.List;
import model.User;

/**
 *
 * @author kelma
 */
public class UserService extends BaseServive{
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // Đăng ký người dùng mới
    public int registerUser(User user) throws ClassNotFoundException {
        // Có thể thêm logic kiểm tra trước khi gọi DAO
        return userDAO.registerUser(user);
    }

    // Xác thực đăng nhập
    public boolean loginValidate(User user) throws ClassNotFoundException {
        // Có thể thêm logic kiểm tra trước khi gọi DAO
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
}
