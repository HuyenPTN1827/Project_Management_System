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
/**
 *
 * @author Admin
 */
public class AuthenticationFilter implements Filter{
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo filter, nếu cần
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Kiểm tra xem người dùng đã đăng nhập hay chưa
        // Ví dụ: nếu không có session user, chuyển hướng tới login
        if (httpRequest.getSession().getAttribute("user") == null) {
            httpRequest.getRequestDispatcher("/member/login.jsp").forward(request, response);
            return;
        }

        // Nếu đã đăng nhập, cho phép tiếp tục đến servlet tiếp theo
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Giải phóng tài nguyên, nếu cần
    }
}