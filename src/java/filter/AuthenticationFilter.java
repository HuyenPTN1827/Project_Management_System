/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import model.Setting;
import model.User;

/**
 *
 * @author Admin
 */
public class AuthenticationFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Khởi tạo filter, nếu cần
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//
//        // Kiểm tra người dùng đã đăng nhập hay chưa
//        Object userObject = httpRequest.getSession().getAttribute("user");
//        if (userObject == null) {
//            httpRequest.getRequestDispatcher("/member/login.jsp").forward(request, response);
//            return;
//        }
//
//        // Đã đăng nhập, kiểm tra quyền hạn
//        User user = (User) userObject; // Ép kiểu đối tượng user từ session
//        String userRole = (String) httpRequest.getSession().getAttribute("userRole"); // Lấy vai trò từ session
//
//        // Lấy URL hiện tại để kiểm tra quyền truy cập
//        String currentURL = httpRequest.getRequestURI();
//
//        System.out.println("Current URL: " + currentURL);
//        System.out.println("User Role: " + userRole);
//        // Kiểm tra quyền truy cập dựa trên vai trò
//        if (currentURL.contains("/user-management")) {
//            if ("admin".equalsIgnoreCase(userRole)) {
//                // Nếu là admin, cho phép truy cập
//                chain.doFilter(request, response);
//            } else {
//                // Nếu không phải admin, chuyển hướng đến trang lỗi
//                httpRequest.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
//            }
//        } else if (currentURL.contains("/todo-list")) {
//            if ("member".equalsIgnoreCase(userRole) || "admin".equalsIgnoreCase(userRole)) {
//                // Nếu là user hoặc admin, cho phép truy cập
//                chain.doFilter(request, response);
//            } else {
//                // Nếu không có quyền, chuyển hướng đến trang lỗi
//                httpRequest.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
//            }
//        } else {
//            // Nếu không thuộc các đường dẫn cần kiểm tra quyền, cho phép tiếp tục
//            chain.doFilter(request, response);
//        }
//    }

  @Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    // Kiểm tra người dùng đã đăng nhập hay chưa
    Object userObject = httpRequest.getSession().getAttribute("user");
    if (userObject == null) {
        httpRequest.getRequestDispatcher("/member/login.jsp").forward(request, response);
        return;
    }

    // Đã đăng nhập, kiểm tra quyền hạn
    Setting userRoleSetting = (Setting) httpRequest.getSession().getAttribute("userRoleSetting"); // Lấy vai trò từ session
    String currentURL = httpRequest.getRequestURI();

    // Ghi log thông tin
    System.out.println("Current URL: " + currentURL);
    System.out.println("User Role: " + (userRoleSetting != null ? userRoleSetting.getName() : "No role"));

    // Kiểm tra quyền truy cập dựa trên vai trò
    if (currentURL.contains("/user-management")) {
        if (userRoleSetting != null && userRoleSetting.getPriority() == 1) {
            chain.doFilter(request, response); // Cho phép truy cập
        } else {
            // Ghi log và chuyển hướng đến trang lỗi
            System.out.println("Unauthorized access attempt by user with role: " + (userRoleSetting != null ? userRoleSetting.getName() : "No role"));
            httpRequest.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
        }
    } else if (currentURL.contains("/member-dashboard")) {
        if (userRoleSetting != null && ( userRoleSetting.getPriority() == 2)) {
            chain.doFilter(request, response); // Cho phép truy cập
        } else {
            // Ghi log và chuyển hướng đến trang lỗi
            System.out.println("Unauthorized access attempt by user with role: " + (userRoleSetting != null ? userRoleSetting.getName() : "No role"));
            httpRequest.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
        }
    } else {
        chain.doFilter(request, response); // Cho phép tiếp tục
    }
}


    @Override
    public void destroy() {
        // Giải phóng tài nguyên, nếu cần
    }
}
