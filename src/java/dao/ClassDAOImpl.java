/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author baynv
 */
import java.sql.*;
import java.util.*;
import model.Class;
import util.DBUtil;

public class ClassDAOImpl implements ClassDAO {

    private Connection conn = DBUtil.getConnection();

    @Override
    public List<Class> findAll() {
        List<Class> list = new ArrayList<>();
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM class")) {
            while (rs.next()) {
                list.add(new Class(rs.getInt("id"), rs.getString("name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insert(Class s) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO class(name) VALUES(?)")) {
            ps.setString(1, s.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Class s) {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE class SET name=? WHERE id=?")) {
            ps.setString(1, s.getName());
            ps.setInt(2, s.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM class WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class findById(int id) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM class WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Class(rs.getInt("id"), rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void assignStudentsToClass(int class_id, String[] studentIds) {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE student SET class_id = ? WHERE id = ?")) {
            for (String id : studentIds) {
                ps.setInt(1, class_id);
                ps.setInt(2, Integer.parseInt(id));
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insertAndReturnId(Class c) {
        int classId = 0;
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO class(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getName());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                classId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classId;
    }

}
