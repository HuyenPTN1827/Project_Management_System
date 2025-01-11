/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kelma
 */
public class BaseServive {

    // HuyenPTNHE160769
    // 28/09/2024
    // Validate email format
    protected boolean validateEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // HuyenPTNHE160769
    // 28/09/2024
    // Validate Vietnamese phone number (format starts with 03, 05, 07, 08, 09 and has 10 digits)
    protected boolean validateMobile(String mobile) {
        if (mobile == null || mobile.isEmpty()) {
            return true; // Accept empty phone number
        }
        String mobileRegex = "^(03|05|07|08|09)\\d{8}$";
        Pattern pattern = Pattern.compile(mobileRegex);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    // HuyenPTNHE160769
    // 28/09/2024
    // Validate password (must include at least 6 characters, contain uppercase, 
    // lowercase, numbers and special characters)
    protected boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{6,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    //Start date cannot be before today
    public boolean validateStartDates(LocalDate startDate) {
        LocalDate today = LocalDate.now();
        return (startDate != null && !startDate.isBefore(today));
    }
    
    //End date cannot be before the project start date and after the project end date.
    public boolean validateEndDates(LocalDate startDate, LocalDate endDate) {
        if (endDate == null) {
            return true; // Accept empty endDate
        }
        return (startDate != null && !endDate.isBefore(startDate));
    }
}
