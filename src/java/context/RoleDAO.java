/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Role;

/**
 *
 * @author kelma
 */
public class RoleDAO {

    private static final String SELECT_ALL_ROLES_SQL = "SELECT * FROM pms.role;";

    public List<Role> selectAllRoles() {
        List<Role> role = new ArrayList<>();

        try (Connection cnt = BaseDAO.getConnection(); PreparedStatement stm = cnt.prepareStatement(SELECT_ALL_ROLES_SQL);) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("id"));
                r.setRole_name(rs.getString("role_name"));
                r.setDescription(rs.getString("description"));
                role.add(r);
            }
        } catch (SQLException e) {
            BaseDAO.printSQLException(e);
        }
        return role;
    }
}
