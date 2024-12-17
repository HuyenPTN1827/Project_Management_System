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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import model.Setting;

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
//BachHD

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Kiểm tra session
        HttpSession session = httpRequest.getSession(false); // Không tạo session mới
        if (session == null || session.getAttribute("user") == null) {
            System.out.println("Session expired or user not logged in. Redirecting to login.");
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/logout");
            return;
        }

        // Kiểm tra vai trò người dùng
        Setting userRoleSetting = (Setting) session.getAttribute("userRoleSetting");
        String currentURL = httpRequest.getRequestURI();

        // Kiểm tra xem userRoleSetting có null không
        if (userRoleSetting == null) {
            System.out.println("userRoleSetting is null.");
        } else {
            System.out.println("userRoleSetting is not null.");
            System.out.println("User role name: " + userRoleSetting.getName());
            System.out.println("User role ID: " + userRoleSetting.getId());
        }

        System.out.println("Current URL: " + currentURL);

        // Phân quyền
        if (currentURL.contains("/user-management")
                || currentURL.contains("/add-user")
                || currentURL.contains("/insert-user")
                || currentURL.contains("/edit-user")
                || currentURL.contains("/update-user")
                || currentURL.contains("/change-status-user")) {
            if (userRoleSetting != null && userRoleSetting.getId() == 2) {
                chain.doFilter(request, response);
            } else {
                System.out.println("Unauthorized access attempt to /user-management");
                httpRequest.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
            }
        } else if (currentURL.contains("/setting-management")
                || currentURL.contains("/add-setting")
                || currentURL.contains("/insert-setting")
                || currentURL.contains("/edit-setting")
                || currentURL.contains("/update-setting")
                || currentURL.contains("/change-status-setting")) { // Thêm kiểm tra cho /setting-management
            if (userRoleSetting != null && userRoleSetting.getId() == 2) {
                chain.doFilter(request, response);
            } else {
                System.out.println("Unauthorized access attempt to /setting-management");
                httpRequest.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
            }
        } else if (currentURL.contains("/project-type-management")
                || currentURL.contains("/add-project-type")
                || currentURL.contains("/insert-project-type")
                || currentURL.contains("/edit-project-type")
                || currentURL.contains("/update-project-type")
                || currentURL.contains("/change-status-project-type")
                || currentURL.contains("/project-type-config")
                || currentURL.contains("/add-project-type-setting")
                || currentURL.contains("/insert-project-type-setting")
                || currentURL.contains("/edit-project-type-setting")
                || currentURL.contains("/update-project-type-setting")
                || currentURL.contains("/change-status-project-type-setting")
                || currentURL.contains("/add-project-phase")
                || currentURL.contains("/insert-project-phase")
                || currentURL.contains("/edit-project-phase")
                || currentURL.contains("/update-project-phase")
                || currentURL.contains("/change-status-project-phase")
                || currentURL.contains("/add-department")
                || currentURL.contains("/insert-department")
                || currentURL.contains("/update-department")
                || currentURL.contains("/change-status-department")
                || currentURL.contains("/insert-department-user")
                || currentURL.contains("/change-status-department-user")) {
            if (userRoleSetting != null && userRoleSetting.getId() == 2) {
                chain.doFilter(request, response);
            } else {
                System.out.println("Unauthorized access attempt to /project-type-management");
                httpRequest.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
            }
        } else if (currentURL.contains("/department-management")
                || currentURL.contains("/department-config")
                || currentURL.contains("/add-project")
                || currentURL.contains("/insert-project")
                || currentURL.contains("/update-project")
                || currentURL.contains("/check-project-code")
                || currentURL.contains("/add-allocation")
                || currentURL.contains("/insert-allocation")
                || currentURL.contains("/update-allocation")
                || currentURL.contains("/change-status-allocation")
                ) {
            if (userRoleSetting != null && userRoleSetting.getId() >= 2 && userRoleSetting.getId() <= 3) {
                chain.doFilter(request, response);
            } else {
                System.out.println("Unauthorized access attempt to /department-management");
                httpRequest.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
            }
        } else if (currentURL.contains("/member-dashboard")
                || currentURL.contains("/projectlist")
                || currentURL.contains("/projectconfig")
                || currentURL.contains("/issue-management")
                || currentURL.contains("/edit-milestone")
                ) {
            if (userRoleSetting != null && userRoleSetting.getId() >= 2) {
                chain.doFilter(request, response);
            } else {
                System.out.println("Unauthorized access attempt to /member-dashboard");
                httpRequest.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
            }
        } else if (currentURL.contains("/edit-allocation")
                || currentURL.contains("/add-milestone")
                || currentURL.contains("/insert-milestone")
                || currentURL.contains("/update-milestone")) {
            if (userRoleSetting != null && userRoleSetting.getId() >= 2 && userRoleSetting.getId() != 5) {
                chain.doFilter(request, response);
            } else {
                httpRequest.getRequestDispatcher("/WEB-INF/member/unauthorized.jsp").forward(request, response);
            }
        } else {
            // Các URL khác cho phép truy cập
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Giải phóng tài nguyên, nếu cần
    }
}
