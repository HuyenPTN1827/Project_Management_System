/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kelma
 */
public class BaseDAO {

    private static final String jdbcURL = "jdbc:mysql://localhost:3306/pms";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "182769";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public class MyDateUtil {

        public static LocalDate getUtilDate(java.sql.Date sqlDate) {
            return sqlDate != null ? sqlDate.toLocalDate() : null;
        }

        public static Date getSQLDate(LocalDate date) {
            return date != null ? java.sql.Date.valueOf(date) : null;
        }

        public static LocalDate getLocalDate(java.util.Date utilDate) {
            return utilDate != null
                    ? utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    : null;
        }
    }
}
