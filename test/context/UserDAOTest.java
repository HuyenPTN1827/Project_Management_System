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
    
@Test
public void testLoginValidate_ValidData() {
    System.out.println("testLoginValidate_ValidData");

    // Arrange
    User testUser = new User();
    testUser.setEmail("haduybachbn@gmail.com");
    testUser.setPassword("Bach123!");

    // Act
    User result = null;
    try {
        result = userDAO.loginValidate(testUser);
    } catch (ClassNotFoundException e) {
        fail("ClassNotFoundException was thrown: " + e.getMessage());
    }

    // Assert
    assertTrue("User should be found with valid credentials", result != null);
}


/**
 * Test of loginValidate method with invalid data.
 */

@Test
public void testLoginValidate_InvalidData() {
    System.out.println("testLoginValidate_InvalidData");

    // Arrange
    User testUser = new User();
    testUser.setEmail("invaliduser@example.com"); // Invalid email
    testUser.setPassword("wrongpassword"); // Invalid password

    // Act
    User result = null;
    try {
        result = userDAO.loginValidate(testUser);
    } catch (ClassNotFoundException e) {
        fail("ClassNotFoundException was thrown: " + e.getMessage());
    }

    // Assert
    assertNull("User should not be found with invalid credentials", result);
}





/**
 * Test of loginValidate method with null email or password.
 */

@Test
public void testLoginValidate_NullFields() {
    System.out.println("testLoginValidate_NullFields");

    // Arrange with null email
    User testUser = new User();
    testUser.setEmail(null); // Null email
    testUser.setPassword("password123");

    // Act
    User result = null;
    try {
        result = userDAO.loginValidate(testUser);
    } catch (ClassNotFoundException e) {
        fail("ClassNotFoundException was thrown: " + e.getMessage());
    }

    // Assert
    assertNull("User should not be found when email is null", result);

    // Arrange with null password
    testUser.setEmail("testuser@example.com");
    testUser.setPassword(null);

    // Act
    try {
        result = userDAO.loginValidate(testUser);
    } catch (ClassNotFoundException e) {
        fail("ClassNotFoundException was thrown: " + e.getMessage());
    }

    // Assert
    assertNull("User should not be found when password is null", result);
}


@Test
    public void testUpdatePassword_Valid() {
        System.out.println("testUpdatePassword_Valid");

        // Arrange
        int validUserId = 1; // Giả định userId này tồn tại
        String newPassword = "newPassword123";

        // Act
        boolean result = userDAO.updatePassword(validUserId, newPassword);

        // Assert
        assertTrue("Password should be updated successfully for valid userId", result);
    }
    
    
    /**
     * Test updatePassword with non-existing userId.
     */
    @Test
    public void testUpdatePassword_NonExistingUserId() {
        System.out.println("testUpdatePassword_NonExistingUserId");

        // Arrange
        int nonExistingUserId = 9999; // Giả định userId này không tồn tại
        String newPassword = "newPassword123";

        // Act
        boolean result = userDAO.updatePassword(nonExistingUserId, newPassword);

        // Assert
        assertFalse("Password update should fail for non-existing userId", result);
    }
    

}
