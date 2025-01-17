/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import model.Project;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;
import java.util.List;
import model.Department;
import model.ProjectType;
import model.Setting;

import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class ProjectDAOTest {

    private ProjectDAO projectDAO;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting tests for ProjectDAO...");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("All tests completed for ProjectDAO.");
    }

    @Before
    public void setUp() {
        projectDAO = new ProjectDAO();
    }

    @After
    public void tearDown() {
        projectDAO = null;
    }

    /**
     * Test of getAllProjects method, of class ProjectDAO.
     */
    @Test
    public void testGetAllProjects() {
        System.out.println("getAllProjects");

        // Giả sử chúng ta đã có dữ liệu trong cơ sở dữ liệu cho test này.
        List<Project> result = projectDAO.getAllProjects();

        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one project", !result.isEmpty());
        System.out.println("The result contains at least one project");
        // Kiểm tra mỗi Project trong danh sách
        result.forEach(project -> {
            assertNotNull("Project id should not be null", project.getId());
            assertNotNull("Project name should not be null", project.getName());
            assertNotNull("Project code should not be null", project.getCode());
            assertNotNull("Project department name should not be null", project.getDepartmentName());
            assertNotNull("Project type name should not be null", project.getTypeName());
        });
    }

  @Test
public void testGetProjectById_ValidId() {
    System.out.println("getProjectById - Valid ID");

    int validId = 1; // ID hợp lệ, giả sử có trong cơ sở dữ liệu
    Project project = projectDAO.getProjectById(validId);
    // Log message sau khi thực hiện phương thức
    if (project != null) {
        System.out.println("Project fetched successfully. ID: " + project.getId());
    } else {
        System.out.println("No project found for ID: " + validId);
    }
    // Kiểm tra các thuộc tính không thể null
    assertNotNull("Project should not be null for valid ID", project);
    assertEquals("ID should match", validId, project.getId());
    assertNotNull("Project name should not be null", project.getName());
    assertNotNull("Project code should not be null", project.getCode());
    assertNotNull("Start date should not be null", project.getStartDate());
    assertNotNull("End date should not be null", project.getEndDate());
    assertNotNull("Last updated date should not be null", project.getLastUpdated());
    assertNotNull("Type code should not be null", project.getTypeCode());
    assertNotNull("Department code should not be null", project.getDepartmentCode());
    assertTrue("Estimated effort should be positive", project.getEstimatedEffort() > 0);

    // Kiểm tra riêng trường `details` (cho phép null)
    if (project.getDetails() != null) {
        assertFalse("Details should not be empty if present", project.getDetails().isEmpty());
    }
}

@Test
public void testGetProjectById_InvalidId() {
    System.out.println("getProjectById - Invalid ID");

    int invalidId = -1; // ID không hợp lệ (không có trong cơ sở dữ liệu)
    System.out.println("Testing with invalid project ID: " + invalidId);

    Project project = projectDAO.getProjectById(invalidId);

    // Log message sau khi thực hiện phương thức
    if (project == null) {
        System.out.println("No project found for ID: " + invalidId);
    } else {
        System.out.println("Project fetched unexpectedly for ID: " + invalidId);
    }

    // Kiểm tra nếu kết quả trả về là null (đúng như mong đợi)
    assertNull("Project should be null for invalid ID", project);
    System.out.println("Project is null, as expected for invalid ID: " + invalidId);
}


@Test
public void testUpdateProject_ValidData() {
    System.out.println("updateProject - Valid Data");

    // Tạo đối tượng Project hợp lệ
    Project project = new Project();
    project.setId(1); // ID đã tồn tại trong cơ sở dữ liệu
    project.setCode("P001");
    project.setName("New Project Name");
    project.setDetails("New project details");
    project.setStartDate(Date.valueOf("2025-01-01"));
    project.setEndDate(Date.valueOf("2025-12-31"));
    project.setStatus(1);
    project.setTypeId(1);
    project.setDepartmentId(2);
    project.setEstimatedEffort(200);

    ProjectDAO projectDAO = new ProjectDAO();
    boolean result = projectDAO.updateProject(project);

    // Kiểm tra kết quả trả về
    assertTrue("Project should be updated successfully", result);
}

@Test
public void testUpdateProject_MissingName() {
    System.out.println("updateProject - Missing Name");

    // Tạo đối tượng Project với tên null
    Project project = new Project();
    project.setId(1); // Giả sử ID 2 tồn tại trong cơ sở dữ liệu
    project.setCode("P002");
    project.setName(null); // Tên null
    project.setDetails("Some details");
    project.setStartDate(Date.valueOf("2025-01-01"));
    project.setEndDate(Date.valueOf("2025-12-31"));
    project.setStatus(1);
    project.setTypeId(1);
    project.setDepartmentId(2);
    project.setEstimatedEffort(1000);

    ProjectDAO projectDAO = new ProjectDAO();
    boolean result = projectDAO.updateProject(project);

    // Kiểm tra kết quả trả về
    assertFalse("Project update should fail for missing name", result);
}

@Test
public void testUpdateProject_NonExistentID() {
    System.out.println("updateProject - Non-Existent ID");

    // Tạo đối tượng Project với ID không tồn tại
    Project project = new Project();
    project.setId(9999); // Giả sử ID 9999 không tồn tại
    project.setCode("P999");
    project.setName("Non-Existent Project");
    project.setDetails("No details");
    project.setStartDate(Date.valueOf("2025-01-01"));
    project.setEndDate(Date.valueOf("2025-12-31"));
    project.setStatus(1);
    project.setTypeId(1);
    project.setDepartmentId(2);
    project.setEstimatedEffort(1000);

    ProjectDAO projectDAO = new ProjectDAO();
    boolean result = projectDAO.updateProject(project);

    // Kiểm tra kết quả trả về
    assertFalse("Project update should fail for non-existent ID", result);
}
 
@Test
public void testUpdateProject_NullDetails() {
    System.out.println("updateProject - Null Details");

    // Tạo đối tượng Project với chi tiết null
    Project project = new Project();
    project.setId(1); // Giả sử ID 1 tồn tại
    project.setCode("P001");
    project.setName("Valid Project Name");
    project.setDetails(null); // Chi tiết null
    project.setStartDate(Date.valueOf("2025-01-01"));
    project.setEndDate(Date.valueOf("2025-12-31"));
    project.setStatus(1);
    project.setTypeId(1);
    project.setDepartmentId(2);
    project.setEstimatedEffort(200);

    ProjectDAO projectDAO = new ProjectDAO();
    boolean result = projectDAO.updateProject(project);

    // Kiểm tra kết quả trả về
    assertTrue("Project update should succeed even with null details", result);
}

    
    @Test
public void testGetAllProjectTypes_ValidData() {
    System.out.println("getAllProjectTypes - Valid Data");

    // Tạo đối tượng ProjectDAO
    ProjectDAO projectDAO = new ProjectDAO();
    
    // Lấy tất cả các ProjectType từ cơ sở dữ liệu
    List<ProjectType> result = projectDAO.getAllProjectTypes();

    // Kiểm tra kết quả trả về
    assertNotNull("The list of project types should not be null", result);
    assertTrue("There should be at least one project type in the list", result.size() > 0);
}

  
   @Test
public void testGetAllDepartments_ValidData() {
    System.out.println("getAllDepartments - Valid Data");

    // Tạo đối tượng ProjectDAO
    ProjectDAO projectDAO = new ProjectDAO();

    // Lấy tất cả các Department từ cơ sở dữ liệu
    List<Department> result = projectDAO.getAllDepartments();

    // Kiểm tra kết quả trả về
    assertNotNull("The list of departments should not be null", result);
    assertTrue("There should be at least one department in the list", result.size() > 0);
}
   
@Test
public void testGetAllBizTerms_Success() {
    System.out.println("getAllBizTerms - Success");

    // Giả sử dữ liệu tồn tại trong bảng
    ProjectDAO projectDAO = new ProjectDAO();
    List<Setting> result = projectDAO.getAllBizTerms();

    // Kiểm tra kết quả trả về
    assertNotNull("The result should not be null", result);
    assertTrue("The result should contain business terms", result.size() > 0);
}
//ko có bizterm nao
    @Test
public void testGetAllBizTerms_EmptyResult() {
    System.out.println("getAllBizTerms - Empty Result Set");

    // Giả sử không có bản ghi nào thỏa mãn điều kiện
    ProjectDAO projectDAO = new ProjectDAO();
    List<Setting> result = projectDAO.getAllBizTerms();

    // Kiểm tra kết quả trả về
    assertNotNull("The result should not be null", result);
    assertTrue("The result should be empty", result.isEmpty());
}





}
