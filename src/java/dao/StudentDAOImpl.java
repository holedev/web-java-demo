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
import model.Student;
import util.DBUtil;

public class StudentDAOImpl implements StudentDAO {
    private Connection conn = DBUtil.getConnection();

    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM student")) {
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getDouble("gpa"), rs.getInt("class_id")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public void insert(Student s) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO student(name,gpa) VALUES(?,?)")) {
            ps.setString(1, s.getName());
            ps.setDouble(2, s.getGpa());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void update(Student s) {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE student SET name=?, gpa=? WHERE id=?")) {
            ps.setString(1, s.getName());
            ps.setDouble(2, s.getGpa());
            ps.setInt(3, s.getId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM student WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public Student findById(int id) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new Student(rs.getInt("id"), rs.getString("name"), rs.getDouble("gpa"));
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Student> findStudentsWithoutClass() {
        List<Student> ls = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE class_id IS NULL")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ls.add(
                    new Student(rs.getInt("id"), rs.getString("name"), rs.getDouble("gpa"), 0)
                );
            }
        } catch (Exception e) { e.printStackTrace(); }
        
        return ls;
    }
    
    
}