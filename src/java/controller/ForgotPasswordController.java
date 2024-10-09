/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import model.User;
import service.UserService;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
//5/10
//BachHD
@WebServlet(name = "ForgotPasswordController", urlPatterns = {"/forgotpassword"})
public class ForgotPasswordController extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/forgotpassword.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        // Kiểm tra xem email đã được đăng ký hay chưa
        User user = userService.selectUserByEmail(email);
        if (user == null) {
            // Xử lý nếu email không được tìm thấy
            request.setAttribute("errorMessage", "Email không tồn tại trong hệ thống.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/forgotpassword.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Tạo OTP và gửi đến email
        String otp = generateOtp();
        sendOtpToEmail(email, otp);

        // Lưu OTP vào session để xác thực
        request.getSession().setAttribute("otp", otp);
        request.getSession().setAttribute("userId", user.getId());

        // Chuyển hướng đến trang nhập mật khẩu mới
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/resetpassword.jsp");
        request.setAttribute("success", true); // Thêm thuộc tính success để hiển thị form nhập OTP
        dispatcher.forward(request, response);
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private void sendOtpToEmail(String email, String otp) {
        // Tham số cấu hình email có thể được lấy từ file cấu hình
        String host = "smtp.gmail.com";
        final String user = "haduybachbn@gmail.com";
        final String password = "iifq izgj dupx ivyy";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);
            Transport.send(message);
            System.out.println("OTP đã được gửi đến email: " + email);
        } catch (MessagingException e) {
            System.out.println("Gửi email thất bại. Lỗi: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
