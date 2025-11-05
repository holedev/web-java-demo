/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author baynv
 */
import java.util.List;
import model.Student;

public interface StudentDAO {
    List<Student> findAll();
    Student findById(int id);
    void insert(Student s);
    void update(Student s);
    void delete(int id);
    
    public List<Student> findStudentsWithoutClass();
}