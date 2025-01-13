/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package context;

import java.util.List;
import model.Setting;
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
public class SettingDAOTest {

    private SettingDAO settingDAO;

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Starting tests for SettingDAO...");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("All tests completed for SettingDAO.");
    }

    @Before
    public void setUp() {
        settingDAO = new SettingDAO();
    }

    @After
    public void tearDown() {
        settingDAO = null;
    }
//
//    /**
//     * Test of getUserRolesList method, of class SettingDAO.
//     */
//    @Test
//    public void testGetUserRolesList() {
//        System.out.println("getUserRolesList");
//        SettingDAO instance = new SettingDAO();
//        List<Setting> expResult = null;
//        List<Setting> result = instance.getUserRolesList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getBizTermsList method, of class SettingDAO.
//     */
//    @Test
//    public void testGetBizTermsList() {
//        System.out.println("getBizTermsList");
//        SettingDAO instance = new SettingDAO();
//        List<Setting> expResult = null;
//        List<Setting> result = instance.getBizTermsList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getIssueTypeList method, of class SettingDAO.
//     */
//    @Test
//    public void testGetIssueTypeList() {
//        System.out.println("getIssueTypeList");
//        SettingDAO instance = new SettingDAO();
//        List<Setting> expResult = null;
//        List<Setting> result = instance.getIssueTypeList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of selectAllSettings method, of class SettingDAO.
     */
    @Test
    public void testSelectAllSettings() {
        System.out.println("selectAllSettings");
        String keyword = null;
        String type = null;
        Boolean status = null;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        assertNull("Keyword is null", keyword);
        assertNull("Type is null", type);
        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllSettings_WithInvalidKeyword() {
        System.out.println("selectAllSettings_WithInvalidKeyword");
        String keyword = "abc@123";
        String type = null;
        Boolean status = null;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
//        assertNotNull("Result is null", result);
        assertTrue("Return no setting", result.isEmpty());
        result.forEach(setting -> {
            assertFalse("Setting name or value do not match the keyword",
                    setting.getName().toLowerCase().contains(keyword.toLowerCase())
                    || setting.getValue().toLowerCase().contains(keyword.toLowerCase()));
        });
        assertNull("Type is null", type);
        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllSettings_WithValidKeyword1() {
        System.out.println("selectAllSettings_WithValidKeyword1");
        String keyword = "User Role";
        String type = null;
        Boolean status = null;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertTrue("Setting name or value match the keyword",
                    setting.getName().toLowerCase().contains(keyword.toLowerCase())
                    || setting.getValue().toLowerCase().contains(keyword.toLowerCase()));
        });
        assertNull("Type is null", type);
        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllSettings_WithValidKeyword2() {
        System.out.println("selectAllSettings_WithValidKeyword2");
        String keyword = "admin";
        String type = null;
        Boolean status = null;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertTrue("Setting name or value match the keyword",
                    setting.getName().toLowerCase().contains(keyword.toLowerCase())
                    || setting.getValue().toLowerCase().contains(keyword.toLowerCase()));
        });
        assertNull("Type is null", type);
        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllSettings_WithValidKeyword3() {
        System.out.println("selectAllSettings_WithValidKeyword3");
        String keyword = "2";
        String type = null;
        Boolean status = null;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertTrue("Setting name or value match the keyword",
                    setting.getName().toLowerCase().contains(keyword.toLowerCase())
                    || setting.getValue().toLowerCase().contains(keyword.toLowerCase()));
        });
        assertNull("Type is null", type);
        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllSettings_WithParentType() {
        System.out.println("selectAllSettings_WithParentType");
        String keyword = null;
        String type = "parent";
        Boolean status = null;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        assertTrue("Setting type match the Parent type", type.equals("parent"));
        assertNull("Keyword is null", keyword);
        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllSettings_WithValidTypeFilter() {
        System.out.println("selectAllSettings_WithValidTypeFilter");
        String keyword = null;
        String type = "User Role";
        Boolean status = null;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertEquals("Setting type match the type filter", type, setting.getType());
        });
        assertNull("Keyword is null", keyword);
        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllSettings_WithTrueStatus() {
        System.out.println("selectAllSettings_WithTrueStatus");
        String keyword = null;
        String type = null;
        Boolean status = true;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertEquals("Setting status match the status filter", status, setting.isStatus());
        });
        assertNull("Keyword is null", keyword);
        assertNull("Type is null", type);
    }

    @Test
    public void testSelectAllSettings_WithFalseStatus() {
        System.out.println("selectAllSettings_WithFalseStatus");
        String keyword = null;
        String type = null;
        Boolean status = false;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertEquals("Setting status match the status filter", status, setting.isStatus());
        });
        assertNull("Keyword is null", keyword);
        assertNull("Type is null", type);
    }

    @Test
    public void testSelectAllSettings_WithInvalidKeywordAndParentType() {
        System.out.println("selectAllSettings_WithInvalidKeywordAndParentType");
        String keyword = "abc@123";
        String type = "parent";
        Boolean status = null;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertTrue("Return no setting", result.isEmpty());
        result.forEach(setting -> {
            assertFalse("Setting name or value do not match the keyword",
                    setting.getName().toLowerCase().contains(keyword.toLowerCase())
                    || setting.getValue().toLowerCase().contains(keyword.toLowerCase()));
        });
        assertTrue("Setting type match the Parent type", type.equals("parent"));
        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllSettings_WithInvalidKeywordAndParentTypeAndTrueStatus() {
        System.out.println("selectAllSettings_WithInvalidKeywordAndParentTypeAndTrueStatus");
        String keyword = "abc@123";
        String type = "parent";
        Boolean status = true;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertTrue("Return no setting", result.isEmpty());
        result.forEach(setting -> {
            assertFalse("Setting name or value do not match the keyword",
                    setting.getName().toLowerCase().contains(keyword.toLowerCase())
                    || setting.getValue().toLowerCase().contains(keyword.toLowerCase()));
            assertEquals("Setting status match the status filter", status, setting.isStatus());
        });
        assertTrue("Setting type match the Parent type", type.equals("parent"));
    }

    @Test
    public void testSelectAllSettings_WithInvalidKeywordAndParentTypeAndFalseStatus() {
        System.out.println("selectAllSettings_WithInvalidKeywordAndParentTypeAndFalseStatus");
        String keyword = "abc@123";
        String type = "parent";
        Boolean status = false;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertTrue("Return no setting", result.isEmpty());
        result.forEach(setting -> {
            assertFalse("Setting name or value do not match the keyword",
                    setting.getName().toLowerCase().contains(keyword.toLowerCase())
                    || setting.getValue().toLowerCase().contains(keyword.toLowerCase()));
            assertEquals("Setting status match the status filter", status, setting.isStatus());
        });
        assertTrue("Setting type match the Parent type", type.equals("parent"));
    }

    @Test
    public void testSelectAllSettings_ValidInputButNoRecord() {
        System.out.println("selectAllSettings_ValidInputButNoRecord");
        String keyword = "User Role";
        String type = "User Role";
        Boolean status = null;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertTrue("Return no setting", result.isEmpty());
        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllSettings_WithValidKeywordAndValidType() {
        System.out.println("selectAllSettings_WithValidKeywordAndValidType");
        String keyword = "admin";
        String type = "User Role";
        Boolean status = null;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertTrue("Setting name or value match the keyword",
                    setting.getName().toLowerCase().contains(keyword.toLowerCase())
                    || setting.getValue().toLowerCase().contains(keyword.toLowerCase()));
            assertEquals("Setting type match the type filter", type, setting.getType());
        });
        assertNull("Status is null", status);
    }

    @Test
    public void testSelectAllSettings_WithValidKeywordAndValidTypeAndTrueStatus() {
        System.out.println("selectAllSettings_WithValidKeywordAndValidTypeAndTrueStatus");
        String keyword = "admin";
        String type = "User Role";
        Boolean status = true;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertTrue("Setting name or value match the keyword",
                    setting.getName().toLowerCase().contains(keyword.toLowerCase())
                    || setting.getValue().toLowerCase().contains(keyword.toLowerCase()));
            assertEquals("Setting type match the type filter", type, setting.getType());
            assertEquals("Setting status match the status filter", status, setting.isStatus());
        });
    }

    @Test
    public void testSelectAllSettings_WithValidKeywordAndValidTypeAndFalseStatus() {
        System.out.println("selectAllSettings_WithValidKeywordAndValidTypeAndFalseStatus");
        String keyword = "admin";
        String type = "User Role";
        Boolean status = false;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertTrue("Return no setting", result.isEmpty());
    }

    @Test
    public void testSelectAllSettings_WithParentTypeAndTrueStatus() {
        System.out.println("selectAllSettings_WithParentTypeAndTrueStatus");
        String keyword = null;
        String type = "parent";
        Boolean status = true;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertEquals("Setting status match the status filter", status, setting.isStatus());
        });
        assertNull("Keyword is null", keyword);
        assertTrue("Setting type match the Parent type", type.equals("parent"));
    }

    @Test
    public void testSelectAllSettings_WithParentTypeAndFalseStatus() {
        System.out.println("selectAllSettings_WithParentTypeAndFalseStatus");
        String keyword = null;
        String type = "parent";
        Boolean status = false;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertNull("Keyword is null", keyword);
        assertTrue("Setting type match the Parent type", type.equals("parent"));
    }

    @Test
    public void testSelectAllSettings_WithValidTypeAndTrueStatus() {
        System.out.println("selectAllSettings_WithValidTypeAndTrueStatus");
        String keyword = null;
        String type = "User Role";
        Boolean status = true;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertEquals("Setting type match the type filter", type, setting.getType());
            assertEquals("Setting status match the status filter", status, setting.isStatus());
        });
        assertNull("Keyword is null", keyword);
    }

    @Test
    public void testSelectAllSettings_WithValidTypeAndFalseStatus() {
        System.out.println("selectAllSettings_WithValidTypeAndFalseStatus");
        String keyword = null;
        String type = "User Role";
        Boolean status = false;

        List<Setting> result = settingDAO.selectAllSettings(keyword, type, status);
        assertNotNull("Result should not be null", result);
        assertTrue("Should return at least one setting", !result.isEmpty());
        result.forEach(setting -> {
            assertEquals("Setting type match the type filter", type, setting.getType());
            assertEquals("Setting status match the status filter", status, setting.isStatus());
        });
        assertNull("Keyword is null", keyword);
    }

    /**
     * Test of selectSettingByID method, of class SettingDAO.
     */
    @Test
    public void testSelectSettingByID_ValidId() {
        System.out.println("selectSettingByID_ValidId");
        int id = 1;
        Setting result = settingDAO.selectSettingByID(id);
        assertNotNull("Result should not be null", result);
        assertEquals("Setting ID match the requested ID", id, result.getId());
    }

    @Test
    public void testSelectSettingByID_InvalidId() {
        System.out.println("selectSettingByID_InvalidId");
        int id = 9999;
        Setting result = settingDAO.selectSettingByID(id);
        assertNull("Result is null", result);
    }

    /**
     * Test of insertSetting method, of class SettingDAO.
     */
    @Test
    public void testInsertSetting_ParentSetting() throws Exception {
        System.out.println("insertSetting_ParentSetting");
        String name = "Test insert";
        String type = null;
        String value = "Test insert";
        int priority = 1;
        boolean status = true;
        String description = "";

        Setting s = new Setting();
        s.setName(name);
        s.setType(type);
        s.setValue(value);
        s.setPriority(priority);
        s.setStatus(status);
        s.setDescription(description);

        int result = settingDAO.insertSetting(s);
        assertTrue("Insert parent setting successfully", result > 0);
    }

    @Test
    public void testInsertSetting_ValidSetting() throws Exception {
        System.out.println("insertSetting_ValidSetting");
        String name = "Test insert";
        String type = "User Role";
        String value = null;
        int priority = 1;
        boolean status = false;
        String description = "Test insert";

        Setting s = new Setting();
        s.setName(name);
        s.setType(type);
        s.setValue(value);
        s.setPriority(priority);
        s.setStatus(status);
        s.setDescription(description);

        int result = settingDAO.insertSetting(s);
        assertTrue("Insert setting successfully", result > 0);
    }

    /**
     * Test of updateSetting method, of class SettingDAO.
     */
    @Test
    public void testUpdateSetting_ParentSetting() throws Exception {
        System.out.println("updateSetting_ParentSetting");
        int id = 51;
        String name = "Test update";
        String type = null;
        String value = null;
        int priority = 1;
        boolean status = false;
        String description = "Test update";

        Setting s = new Setting();
        s.setId(id);
        s.setName(name);
        s.setType(type);
        s.setValue(value);
        s.setPriority(priority);
        s.setStatus(status);
        s.setDescription(description);
        boolean result = settingDAO.updateSetting(s);
        assertTrue("Update parent setting successfully", result);
    }

    @Test
    public void testUpdateSetting_ValidSetting() throws Exception {
        System.out.println("updateSetting_ValidSetting");
        int id = 53;
        String name = "Test update";
        String type = "User Role";
        String value = "Test update";
        int priority = 1;
        boolean status = true;
        String description = null;

        Setting s = new Setting();
        s.setId(id);
        s.setName(name);
        s.setType(type);
        s.setValue(value);
        s.setPriority(priority);
        s.setStatus(status);
        s.setDescription(description);
        boolean result = settingDAO.updateSetting(s);
        assertTrue("Update setting successfully", result);
    }

    /**
     * Test of changeStatusSetting method, of class SettingDAO.
     */
    @Test
    public void testChangeStatusSetting_ChangeToFalse() throws Exception {
        System.out.println("changeStatusSetting_ChangeToFalse");
        int id = 53;
        boolean status = true;
        boolean expStatus = false;

        Setting s = new Setting();
        s.setId(id);
        // If status is true, set to false; if false, set to true
        s.setStatus(!status);

        boolean result = settingDAO.changeStatusSetting(s);
        assertTrue("Changed the status of the setting from true to false successfully", result);
        assertEquals("Expected status matches the actual status", expStatus, s.isStatus());
    }

    @Test
    public void testChangeStatusSetting_ChangeToTrue() throws Exception {
        System.out.println("changeStatusSetting_ChangeToTrue");
        int id = 53;
        boolean status = false;
        boolean expStatus = true;

        Setting s = new Setting();
        s.setId(id);
        // If status is true, set to false; if false, set to true
        s.setStatus(!status);

        boolean result = settingDAO.changeStatusSetting(s);
        assertTrue("Changed the status of the setting from false to true successfully", result);
        assertEquals("Expected status matches the actual status", expStatus, s.isStatus());
    }
//
//    /**
//     * Test of getPriorityUserRolesList method, of class SettingDAO.
//     */
//    @Test
//    public void testGetPriorityUserRolesList() {
//        System.out.println("getPriorityUserRolesList");
//        SettingDAO instance = new SettingDAO();
//        List<Setting> expResult = null;
//        List<Setting> result = instance.getPriorityUserRolesList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getTypeList method, of class SettingDAO.
//     */
//    @Test
//    public void testGetTypeList() {
//        System.out.println("getTypeList");
//        SettingDAO instance = new SettingDAO();
//        List<Setting> expResult = null;
//        List<Setting> result = instance.getTypeList();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
