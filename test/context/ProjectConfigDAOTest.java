/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import model.Milestone;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Date;




/**
 *
 * @author Admin
 */
public class ProjectConfigDAOTest {
    
      private ProjectConfigDAO projectConfigDAO;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting ProjectConfigDAO tests...");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("All ProjectConfigDAO tests completed.");
    }

    @Before
    public void setUp() {
        projectConfigDAO = new ProjectConfigDAO();
    }

    @After
    public void tearDown() {
        projectConfigDAO = null;
    }

//    @Test
//public void testGetMilestonesByProjectId_Success() {
//    System.out.println("getMilestonesByProjectId - Success");
//
//    ProjectConfigDAO projectConfigDAO = new ProjectConfigDAO();
//    int projectId = 1; // Giả sử projectId 1 có tồn tại và có các milestones
//
//    List<Milestone> result = projectConfigDAO.getMilestonesByProjectId(projectId);
//
//    // Kiểm tra kết quả trả về
//    assertNotNull("Result should not be null", result);
//    assertTrue("Milestones list should not be empty", result.size() > 0);
//    assertTrue("Each milestone should have a project ID matching the input", result.stream().allMatch(milestone -> milestone.getProjectId() == projectId));
//}

//@Test
//public void testGetMilestonesByProjectId_InvalidProjectId() {
//    System.out.println("getMilestonesByProjectId - Invalid Project ID");
//
//    ProjectConfigDAO projectConfigDAO = new ProjectConfigDAO();
//    int projectId = 999; // Giả sử projectId 999 không tồn tại trong cơ sở dữ liệu
//
//    List<Milestone> result = projectConfigDAO.getMilestonesByProjectId(projectId);
//
//    // Kiểm tra kết quả trả về
//    assertNotNull("Result should not be null", result);
//    assertTrue("Milestones list should be empty", result.isEmpty());
//}

  
//
//@Test
//public void testUpdateMilestone_ValidData() {
//    System.out.println("updateMilestone - Valid Data");
//
//    // Create a valid Milestone object
//    Milestone milestone = new Milestone();
//    milestone.setId(1);
//    milestone.setCreatedBy(2);
//    milestone.setLastUpdated("2025-01-12");
//    milestone.setName("Updated Milestone");
//    milestone.setParentMilestone(null); // Parent milestone can be null
//    milestone.setPriority(2);
//    milestone.setTargetDate(Date.valueOf("2025-02-15")); // Ensure java.sql.Date is used
//    milestone.setStatus(1);
//    milestone.setActualDate(Date.valueOf("2025-02-10"));
//    milestone.setDetails("Updated details");
//    milestone.setProjectId(3);
//
//    ProjectConfigDAO projectConfigDAO = new ProjectConfigDAO();
//
//    // Call the update method
//    boolean result = projectConfigDAO.updateMilestone(milestone);
//
//    // Assert the result is true (indicating successful update)
//    assertTrue("Milestone should be successfully updated", result);
//}
//
//@Test
//public void testUpdateMilestone_InvalidData() {
//    System.out.println("updateMilestone - Invalid Data");
//
//    // Invalid milestone (e.g., missing ID)
//    Milestone milestone = new Milestone();
//    milestone.setId(0); // Invalid ID
//    milestone.setCreatedBy(1);
//    milestone.setLastUpdated("2025-01-12");
//    milestone.setName("Invalid Milestone");
//    milestone.setParentMilestone(2);
//    milestone.setPriority(1);
//    milestone.setTargetDate(Date.valueOf("2025-06-01"));
//    milestone.setStatus(1);
//    milestone.setActualDate(Date.valueOf("2025-06-05"));
//    milestone.setDetails("Invalid milestone details");
//    milestone.setProjectId(1);
//
//    ProjectConfigDAO projectConfigDAO = new ProjectConfigDAO();
//    boolean result = projectConfigDAO.updateMilestone(milestone);
//
//    // Assert that the update fails due to invalid data
//    assertFalse("Milestone update should fail due to invalid data", result);
//}
//
//@Test
//public void testUpdateMilestone_ParentMilestoneNull() {
//    System.out.println("updateMilestone - Parent Milestone Null");
//
//    // Milestone with null parent milestone
//    Milestone milestone = new Milestone();
//    milestone.setId(1); // Existing milestone ID
//    milestone.setCreatedBy(1);
//    milestone.setLastUpdated("2025-01-12");
//    milestone.setName("Updated Milestone Without Parent");
//    milestone.setParentMilestone(null); // No parent milestone
//    milestone.setPriority(1);
//    milestone.setTargetDate(Date.valueOf("2025-06-01"));
//    milestone.setStatus(1);
//    milestone.setActualDate(Date.valueOf("2025-06-05"));
//    milestone.setDetails("Updated milestone without parent");
//    milestone.setProjectId(1); // Existing project ID
//
//    ProjectConfigDAO projectConfigDAO = new ProjectConfigDAO();
//    boolean result = projectConfigDAO.updateMilestone(milestone);
//
//    // Assert that the milestone was successfully updated even without a parent milestone
//    assertTrue("Milestone should be updated successfully even without a parent milestone", result);
//}


//@Test
//public void testInsertMilestone_ValidData() {
//    System.out.println("insertMilestone - Valid Data");
//
//    Milestone milestone = new Milestone();
//    milestone.setProjectId(1);
//    milestone.setName("New Milestone");
//    milestone.setStatus(1);
//    milestone.setCreatedBy(1);
//    milestone.setLastUpdated("2025-01-13");
//    milestone.setParentMilestone(0); // No parent
//    milestone.setPriority(1);
//    milestone.setTargetDate(Date.valueOf("2025-01-20"));
//    milestone.setActualDate(null); // Null actual date
//    milestone.setDetails("Details about the milestone");
//
//    ProjectConfigDAO projectConfigDAO = new ProjectConfigDAO();
//    boolean result = projectConfigDAO.insertMilestone(milestone);
//
//    assertTrue("Milestone should be successfully inserted", result);
//}

//@Test
//public void testInsertMilestone_MissingRequiredFields() {
//    System.out.println("insertMilestone - Missing Required Fields");
//
//    Milestone milestone = new Milestone();
//    milestone.setProjectId(0); // Invalid project ID
//    milestone.setName(null);  // Missing name
//    milestone.setStatus(1);
//    milestone.setCreatedBy(1);
//    milestone.setLastUpdated("2025-01-13");
//    milestone.setParentMilestone(0);
//    milestone.setPriority(1);
//
//    ProjectConfigDAO projectConfigDAO = new ProjectConfigDAO();
//    boolean result = projectConfigDAO.insertMilestone(milestone);
//
//    assertFalse("Milestone insertion should fail due to missing required fields", result);
//}

    
   @Test
public void testInsertMilestone_OptionalFieldsNull() {
    System.out.println("insertMilestone - Optional Fields Null");

    Milestone milestone = new Milestone();
    milestone.setProjectId(1);
    milestone.setName("Milestone with Null Fields");
    milestone.setStatus(1);
    milestone.setCreatedBy(1);
    milestone.setLastUpdated("2025-01-13");
    milestone.setParentMilestone(0); // No parent milestone
    milestone.setPriority(1);
    milestone.setTargetDate(new java.util.Date()); // Set a valid target date
    milestone.setActualDate(null); // Null actual date
    milestone.setDetails(null); // Null details

    ProjectConfigDAO projectConfigDAO = new ProjectConfigDAO();
    boolean result = projectConfigDAO.insertMilestone(milestone);

    assertTrue("Milestone should be successfully inserted even with null optional fields", result);
}

}
