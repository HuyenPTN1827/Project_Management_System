/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import context.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.User;

/**
 *
 * @author kelma
 */
public class UserService extends BaseServive {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    // Đăng ký người dùng mới
    public int registerUser(User user) throws ClassNotFoundException {
        // Có thể thêm logic kiểm tra trước khi gọi DAO
        return userDAO.registerUser(user);
    }

    // Xác thực đăng nhập
    public User loginValidate(User user) throws ClassNotFoundException {
        return userDAO.loginValidate(user);
    }

//    //lấy vai trò người dùng
//    public String getUserRole(User user) {
//        User foundUser = userDAO.selectUserByEmail(user.getEmail());
//        if (foundUser != null) {
//            return foundUser.getRole().getRole_name(); // Lấy role của user
//        }
//        return null; // Trả về null nếu không tìm thấy user
//    }
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

    // HuyenPTNHE160769
    // 26/09/2024
    // Admin get all users
    public List<User> getAllUsers(String keyword, Integer deptId, Integer roleId, Integer status) {
        return userDAO.selectAllUsers(keyword, deptId, roleId, status);
    }

    // HuyenPTNHE160769
    // 26/09/2024
    // Admin get an user information by id
    public User getUserById(int id) {
        return userDAO.selectUserByID(id);
    }

    // HuyenPTNHE160769
    // 27/09/2024
    // Admin add new user information
    public int insertUser(User user, Integer deptId, Integer roleId) throws SQLException {
        return userDAO.insertUser(user, deptId, roleId);
    }

    // HuyenPTNHE160769
    // 27/09/2024
    // Admin update an user information
    public boolean updateUser(User user, Integer deptId, Integer roleId) throws SQLException {
        return userDAO.updateUser(user, deptId, roleId);
    }

    // HuyenPTNHE160769
    // 26/09/2024
    // Admin change status of an user
    public boolean changeStatusUser(User user) throws SQLException {
        return userDAO.changeStatusUser(user);
    }

    // HuyenPTNHE160769
    // 28/09/2024
    // Validate user information
    public List<String> validateUser(User user) {
        List<String> errors = new ArrayList<>();

        // Validate email format
        if (!validateEmail(user.getEmail())) {
            errors.add("Invalid email format.");
        }
        // Validate Vietnamese phone number
        if (!validateMobile(user.getMobile())) {
            errors.add("Invalid mobile number format. Phone number must start with 03, 05, "
                    + "07, 08, or 09 and be 10 digits long.");
        }
        // Validate password 
        if (!validatePassword(user.getPassword())) {
            errors.add("Password must be at least 6 characters, including uppercase letters, "
                    + "lowercase letters, numbers and some special characters.");
        }
        return errors;
    }

    // HuyenPTNHE160769
    // 18/10/2024
    // Check for uniqueness in the database
    public boolean checkEmailExist(String email) throws SQLException {
        return userDAO.checkEmailExist(email);
    }

    public boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

// Lấy thông tin người dùng theo sessionId
   
     public User getUserBySessionId(int userId) {
        return userDAO.getUserBySessionId(userId);
    }

}
