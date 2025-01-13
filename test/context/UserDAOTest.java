/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class UserDAOTest {

    private UserDAO userDAO;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting UserDAO tests...");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("All UserDAO tests completed.");
    }

    @Before
    public void setUp() {
        userDAO = new UserDAO();
    }

    @After
    public void tearDown() {
        userDAO = null;
    }

    /**
     * Test of updateMember method with valid data.
     */
    @Test
    public void testUpdateMember_ValidData() {
        System.out.println("testUpdateMember_ValidData");

        // Arrange
        User user = new User();
        user.setId(1);
        user.setFull_name("Updated Name");
        user.setEmail("updatedemail@example.com"); // Valid email
        user.setMobile("1234567890");
        user.setUsername("updatedusername");

        // Act
        boolean result = userDAO.updateMember(user);

        // Debugging: Log result to check the outcome
        System.out.println("Result of updateMember: " + result);

        // Assert
        assertTrue("updateMember should return true for valid data", result);
    }

}
