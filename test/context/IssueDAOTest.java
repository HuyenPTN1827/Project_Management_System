/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package context;

import java.time.LocalDate;
import java.util.List;
import model.Issue;
import model.Milestone;
import model.Project;
import model.Setting;
import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kelma
 */
public class IssueDAOTest {

    private IssueDAO issueDAO;
    private ProjectDAO projectDAO;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting tests for IssueDAO...");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("All tests completed for IssueDAO.");
    }

    @Before
    public void setUp() {
        issueDAO = new IssueDAO();
        projectDAO = new ProjectDAO();
    }

    @After
    public void tearDown() {
        issueDAO = null;
    }

//    /**
//     * Test of getMemberListByProjectId method, of class IssueDAO.
//     */
//    @Test
//    public void testGetMemberListByProjectId() {
//        System.out.println("getMemberListByProjectId");
//        Integer projectId = null;
//        IssueDAO instance = new IssueDAO();
//        List<User> expResult = null;
//        List<User> result = instance.getMemberListByProjectId(projectId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of selectAllIssues method, of class IssueDAO.
     */
    @Test
    public void testSelectAllIssues() {
        System.out.println("selectAllIssues");
        String keyword = null;
        Integer project = null;
        Integer type = null;
        Integer milestone = null;
        Integer assigner = null;
        Integer assignee = null;
        Integer status = null;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one issue", !result.isEmpty());
//        assertNull("Keyword is null", keyword);
//        assertNull("Type is null", type);
//        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllIssues_WithValidKeyword() {
        System.out.println("selectAllIssues_WithValidKeyword");
        String keyword = "Shortage of human resources";
        Integer project = null;
        Integer type = null;
        Integer milestone = null;
        Integer assigner = null;
        Integer assignee = null;
        Integer status = null;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one issue", !result.isEmpty());
    }

    @Test
    public void testSelectAllIssues_WithInvalidKeyword() {
        System.out.println("selectAllIssues_WithInvalidKeyword");
        String keyword = "Issue Test";
        Integer project = null;
        Integer type = null;
        Integer milestone = null;
        Integer assigner = null;
        Integer assignee = null;
        Integer status = null;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertTrue("Return no issue", result.isEmpty());
    }

    @Test
    public void testSelectAllIssues_WithValidProject() {
        System.out.println("selectAllIssues_WithValidProject");
        String keyword = null;
        Integer project = 13;
        Integer type = null;
        Integer milestone = null;
        Integer assigner = null;
        Integer assignee = null;
        Integer status = null;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one issue", !result.isEmpty());
    }

    @Test
    public void testSelectAllIssues_WithValidType() {
        System.out.println("selectAllIssues_WithValidType");
        String keyword = null;
        Integer project = null;
        Integer type = 16;
        Integer milestone = null;
        Integer assigner = null;
        Integer assignee = null;
        Integer status = null;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one issue", !result.isEmpty());
    }

    @Test
    public void testSelectAllIssues_WithValidMilestone() {
        System.out.println("selectAllIssues_WithValidMilestone");
        String keyword = null;
        Integer project = null;
        Integer type = null;
        Integer milestone = 28;
        Integer assigner = null;
        Integer assignee = null;
        Integer status = null;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one issue", !result.isEmpty());
    }

    @Test
    public void testSelectAllIssues_WithValidAssigner() {
        System.out.println("selectAllIssues_WithValidAssigner");
        String keyword = null;
        Integer project = null;
        Integer type = null;
        Integer milestone = null;
        Integer assigner = 2;
        Integer assignee = null;
        Integer status = null;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one issue", !result.isEmpty());
    }

    @Test
    public void testSelectAllIssues_WithValidAssignee() {
        System.out.println("selectAllIssues_WithValidAssignee");
        String keyword = null;
        Integer project = null;
        Integer type = null;
        Integer milestone = null;
        Integer assigner = null;
        Integer assignee = 75;
        Integer status = null;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one issue", !result.isEmpty());
    }

    @Test
    public void testSelectAllIssues_WithValidStatus() {
        System.out.println("selectAllIssues_WithValidStatus");
        String keyword = null;
        Integer project = null;
        Integer type = null;
        Integer milestone = null;
        Integer assigner = null;
        Integer assignee = null;
        Integer status = 1;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one issue", !result.isEmpty());
    }

    @Test
    public void testSelectAllIssues_WithInvalidInput() {
        System.out.println("selectAllIssues_WithInvalidStatus");
        String keyword = "Shortage of human resources";
        Integer project = 13;
        Integer type = 16;
        Integer milestone = 28;
        Integer assigner = 2;
        Integer assignee = 75;
        Integer status = 1;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertTrue("Return no issue", result.isEmpty());
    }

    @Test
    public void testSelectAllIssues_WithValidInput() {
        System.out.println("selectAllIssues_WithValidStatus");
        String keyword = "Shortage of human resources";
        Integer project = 13;
        Integer type = 14;
        Integer milestone = 28;
        Integer assigner = 2;
        Integer assignee = 74;
        Integer status = 2;

        List<Issue> result = issueDAO.selectAllIssues(keyword, project, type, milestone, assigner, assignee, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one issue", !result.isEmpty());
    }

//    /**
//     * Test of select10LastestIssues method, of class IssueDAO.
//     */
//    @Test
//    public void testSelect10LastestIssues() {
//        System.out.println("select10LastestIssues");
//        int userId = 0;
//        IssueDAO instance = new IssueDAO();
//        List<Issue> expResult = null;
//        List<Issue> result = instance.select10LastestIssues(userId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of selectIssueByID method, of class IssueDAO.
     */
    @Test
    public void testSelectIssueByID_ValidId() {
        System.out.println("selectIssueByID_ValidId");
        int id = 20;
        Issue result = issueDAO.selectIssueByID(id);
        assertNotNull("Result should not be null", result);
        assertEquals("Issue ID match the requested ID", id, result.getId());
    }

    @Test
    public void testSelectIssueByID_InvalidId() {
        System.out.println("selectIssueByID_InvalidId");
        int id = 9999;
        Issue result = issueDAO.selectIssueByID(id);
        assertNull("Result is null", result);
    }

    /**
     * Test of insertIssue method, of class IssueDAO.
     */
    @Test
    public void testInsertIssue_ValidDeadline1() throws Exception {
        System.out.println("insertIssue_ValidDeadline1");
        String name = "Test insert";
        int type = 12;
        int project = 13;
        int milestone = 27;
        int assigner = 1;
        int assignee = 76;
        LocalDate deadline = LocalDate.parse("2024-12-18");
        int status = 0;
        String description = null;

        Issue i = new Issue();
        i.setName(name);
        i.setDeadline(deadline);
        i.setStatus(status);
        i.setDetails(description);

        Setting s = new Setting();
        s.setId(type);
        i.setType(s);

        Project p = new Project();
        p.setId(project);
        i.setProject(p);

        Milestone m = new Milestone();
        m.setId(milestone);
        i.setMilestone(m);

        User u1 = new User();
        u1.setId(assigner);
        i.setCreated_by(u1);

        User u2 = new User();
        u2.setId(assignee);
        i.setAssignee(u2);

        // Lấy thông tin dự án từ database
        Project prj = projectDAO.getProjectById(project);

        // Kiểm tra tính hợp lệ của deadline
        int result = 0;
        if (deadline.isBefore(LocalDate.parse(prj.getStartDate().toString()))
                || deadline.isAfter(LocalDate.parse(prj.getEndDate().toString()))) {
            // Deadline không hợp lệ, không chèn issue
            result = 0;
        } else {
            // Deadline hợp lệ, thử chèn issue
            result = issueDAO.insertIssue(i);
        }
        assertTrue("Insert issue successfully", result > 0);
    }

    @Test
    public void testInsertIssue_ValidDeadline2() throws Exception {
        System.out.println("insertIssue_ValidDeadline2");
        String name = "Test insert";
        int type = 12;
        int project = 13;
        int milestone = 27;
        int assigner = 1;
        int assignee = 76;
        LocalDate deadline = LocalDate.parse("2024-12-31");
        int status = 1;
        String description = "Test insert";

        Issue i = new Issue();
        i.setName(name);
        i.setDeadline(deadline);
        i.setStatus(status);
        i.setDetails(description);

        Setting s = new Setting();
        s.setId(type);
        i.setType(s);

        Project p = new Project();
        p.setId(project);
        i.setProject(p);

        Milestone m = new Milestone();
        m.setId(milestone);
        i.setMilestone(m);

        User u1 = new User();
        u1.setId(assigner);
        i.setCreated_by(u1);

        User u2 = new User();
        u2.setId(assignee);
        i.setAssignee(u2);

        // Lấy thông tin dự án từ database
        Project prj = projectDAO.getProjectById(project);

        // Kiểm tra tính hợp lệ của deadline
        int result = 0;
        if (deadline.isBefore(LocalDate.parse(prj.getStartDate().toString()))
                || deadline.isAfter(LocalDate.parse(prj.getEndDate().toString()))) {
            // Deadline không hợp lệ, không chèn issue
            result = 0;
        } else {
            // Deadline hợp lệ, thử chèn issue
            result = issueDAO.insertIssue(i);
        }
        assertTrue("Insert issue successfully", result > 0);
    }

    @Test
    public void testInsertIssue_InvalidDeadline1() throws Exception {
        System.out.println("insertIssue_InvalidDeadline1");
        String name = "Test insert";
        int type = 12;
        int project = 13;
        int milestone = 27;
        int assigner = 1;
        int assignee = 76;
        LocalDate deadline = LocalDate.parse("2024-12-17");
        int status = 2;
        String description = null;

        // Tạo đối tượng Issue và thiết lập thông tin
        Issue i = new Issue();
        i.setName(name);
        i.setDeadline(deadline);
        i.setStatus(status);
        i.setDetails(description);

        Setting s = new Setting();
        s.setId(type);
        i.setType(s);

        Project p = new Project();
        p.setId(project);
        i.setProject(p);

        Milestone m = new Milestone();
        m.setId(milestone);
        i.setMilestone(m);

        User u1 = new User();
        u1.setId(assigner);
        i.setCreated_by(u1);

        User u2 = new User();
        u2.setId(assignee);
        i.setAssignee(u2);

        // Lấy thông tin dự án từ database
        Project prj = projectDAO.getProjectById(project);

        // Kiểm tra tính hợp lệ của deadline
        int result = 0;
        if (deadline.isBefore(LocalDate.parse(prj.getStartDate().toString()))
                || deadline.isAfter(LocalDate.parse(prj.getEndDate().toString()))) {
            // Deadline không hợp lệ, không chèn issue
            result = 0;
        } else {
            // Deadline hợp lệ, thử chèn issue
            result = issueDAO.insertIssue(i);
        }

        // Kiểm tra kết quả: Không được chèn issue vì deadline không hợp lệ
        assertFalse("Insert issue fail due to invalid deadline", result > 0);
    }

    @Test
    public void testInsertIssue_InvalidDeadline2() throws Exception {
        System.out.println("insertIssue_InvalidDeadline2");
        String name = "Test insert";
        int type = 12;
        int project = 13;
        int milestone = 27;
        int assigner = 1;
        int assignee = 76;
        LocalDate deadline = LocalDate.parse("2025-01-01");
        int status = 3;
        String description = "Test insert";

        // Tạo đối tượng Issue và thiết lập thông tin
        Issue i = new Issue();
        i.setName(name);
        i.setDeadline(deadline);
        i.setStatus(status);
        i.setDetails(description);

        Setting s = new Setting();
        s.setId(type);
        i.setType(s);

        Project p = new Project();
        p.setId(project);
        i.setProject(p);

        Milestone m = new Milestone();
        m.setId(milestone);
        i.setMilestone(m);

        User u1 = new User();
        u1.setId(assigner);
        i.setCreated_by(u1);

        User u2 = new User();
        u2.setId(assignee);
        i.setAssignee(u2);

        // Lấy thông tin dự án từ database
        Project prj = projectDAO.getProjectById(project);

        // Kiểm tra tính hợp lệ của deadline
        int result = 0;
        if (deadline.isBefore(LocalDate.parse(prj.getStartDate().toString()))
                || deadline.isAfter(LocalDate.parse(prj.getEndDate().toString()))) {
            // Deadline không hợp lệ, không chèn issue
            result = 0;
        } else {
            // Deadline hợp lệ, thử chèn issue
            result = issueDAO.insertIssue(i);
        }

        // Kiểm tra kết quả: Không được chèn issue vì deadline không hợp lệ
        assertFalse("Insert issue fail due to invalid deadline", result > 0);
    }

    /**
     * Test of updateIssue method, of class IssueDAO.
     */
    @Test
    public void testUpdateIssue_ValidDeadline1() throws Exception {
        System.out.println("updateIssue_ValidDeadline1");
        int id = 31;
        String name = "Test update";
        int type = 16;
        int project = 13;
        int milestone = 28;
        int assigner = 1;
        int assignee = 75;
        LocalDate deadline = LocalDate.parse("2024-12-18");
        int status = 0;
        String description = "Test update";

        Issue i = new Issue();
        i.setId(id);
        i.setName(name);
        i.setDeadline(deadline);
        i.setStatus(status);
        i.setDetails(description);

        Setting s = new Setting();
        s.setId(type);
        i.setType(s);

        Project p = new Project();
        p.setId(project);
        i.setProject(p);

        Milestone m = new Milestone();
        m.setId(milestone);
        i.setMilestone(m);

        User u1 = new User();
        u1.setId(assigner);
        i.setCreated_by(u1);

        User u2 = new User();
        u2.setId(assignee);
        i.setAssignee(u2);

        // Lấy thông tin dự án từ database
        Project prj = projectDAO.getProjectById(project);

        // Kiểm tra tính hợp lệ của deadline
        boolean result = false;
        if (deadline.isBefore(LocalDate.parse(prj.getStartDate().toString()))
                || deadline.isAfter(LocalDate.parse(prj.getEndDate().toString()))) {
            // Deadline không hợp lệ, không chèn issue
            result = false;
        } else {
            // Deadline hợp lệ, thử chèn issue
            result = issueDAO.updateIssue(i);
        }
        assertTrue("Update issue successfully", result);
    }

    @Test
    public void testUpdateIssue_ValidDeadline2() throws Exception {
        System.out.println("updateIssue_ValidDeadline2");
        int id = 27;
        String name = "Test update";
        int type = 16;
        int project = 13;
        int milestone = 28;
        int assigner = 1;
        int assignee = 75;
        LocalDate deadline = LocalDate.parse("2024-12-31");
        int status = 1;
        String description = null;

        Issue i = new Issue();
        i.setId(id);
        i.setName(name);
        i.setDeadline(deadline);
        i.setStatus(status);
        i.setDetails(description);

        Setting s = new Setting();
        s.setId(type);
        i.setType(s);

        Project p = new Project();
        p.setId(project);
        i.setProject(p);

        Milestone m = new Milestone();
        m.setId(milestone);
        i.setMilestone(m);

        User u1 = new User();
        u1.setId(assigner);
        i.setCreated_by(u1);

        User u2 = new User();
        u2.setId(assignee);
        i.setAssignee(u2);

        // Lấy thông tin dự án từ database
        Project prj = projectDAO.getProjectById(project);

        // Kiểm tra tính hợp lệ của deadline
        boolean result = false;
        if (deadline.isBefore(LocalDate.parse(prj.getStartDate().toString()))
                || deadline.isAfter(LocalDate.parse(prj.getEndDate().toString()))) {
            // Deadline không hợp lệ, không chèn issue
            result = false;
        } else {
            // Deadline hợp lệ, thử chèn issue
            result = issueDAO.updateIssue(i);
        }
        assertTrue("Update issue successfully", result);
    }

    @Test
    public void testUpdateIssue_InvalidDeadline1() throws Exception {
        System.out.println("updateIssue_InvalidDeadline1");
        int id = 31;
        String name = "Test update";
        int type = 12;
        int project = 13;
        int milestone = 27;
        int assigner = 1;
        int assignee = 76;
        LocalDate deadline = LocalDate.parse("2024-12-17");
        int status = 2;
        String description = null;

        Issue i = new Issue();
        i.setId(id);
        i.setName(name);
        i.setDeadline(deadline);
        i.setStatus(status);
        i.setDetails(description);

        Setting s = new Setting();
        s.setId(type);
        i.setType(s);

        Project p = new Project();
        p.setId(project);
        i.setProject(p);

        Milestone m = new Milestone();
        m.setId(milestone);
        i.setMilestone(m);

        User u1 = new User();
        u1.setId(assigner);
        i.setCreated_by(u1);

        User u2 = new User();
        u2.setId(assignee);
        i.setAssignee(u2);

        // Lấy thông tin dự án từ database
        Project prj = projectDAO.getProjectById(project);

        // Kiểm tra tính hợp lệ của deadline
        boolean result = false;
        if (deadline.isBefore(LocalDate.parse(prj.getStartDate().toString()))
                || deadline.isAfter(LocalDate.parse(prj.getEndDate().toString()))) {
            // Deadline không hợp lệ, không chèn issue
            result = false;
        } else {
            // Deadline hợp lệ, thử chèn issue
            result = issueDAO.updateIssue(i);
        }
        assertFalse("Update issue fail due to invalid deadline", result);
    }

    @Test
    public void testUpdateIssue_InvalidDeadline2() throws Exception {
        System.out.println("updateIssue_InvalidDeadline2");
        int id = 27;
        String name = "Test update";
        int type = 12;
        int project = 13;
        int milestone = 27;
        int assigner = 1;
        int assignee = 76;
        LocalDate deadline = LocalDate.parse("2025-01-01");
        int status = 3;
        String description = "Test update";

        Issue i = new Issue();
        i.setId(id);
        i.setName(name);
        i.setDeadline(deadline);
        i.setStatus(status);
        i.setDetails(description);

        Setting s = new Setting();
        s.setId(type);
        i.setType(s);

        Project p = new Project();
        p.setId(project);
        i.setProject(p);

        Milestone m = new Milestone();
        m.setId(milestone);
        i.setMilestone(m);

        User u1 = new User();
        u1.setId(assigner);
        i.setCreated_by(u1);

        User u2 = new User();
        u2.setId(assignee);
        i.setAssignee(u2);

        // Lấy thông tin dự án từ database
        Project prj = projectDAO.getProjectById(project);

        // Kiểm tra tính hợp lệ của deadline
        boolean result = false;
        if (deadline.isBefore(LocalDate.parse(prj.getStartDate().toString()))
                || deadline.isAfter(LocalDate.parse(prj.getEndDate().toString()))) {
            // Deadline không hợp lệ, không chèn issue
            result = false;
        } else {
            // Deadline hợp lệ, thử chèn issue
            result = issueDAO.updateIssue(i);
        }
        assertFalse("Update issue fail due to invalid deadline", result);
    }
//    /**
//     * Test of countIssues method, of class IssueDAO.
//     */
//    @Test
//    public void testCountIssues() {
//        System.out.println("countIssues");
//        Integer deptId = null;
//        Integer bizTerm = null;
//        IssueDAO instance = new IssueDAO();
//        List<Issue> expResult = null;
//        List<Issue> result = instance.countIssues(deptId, bizTerm);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
