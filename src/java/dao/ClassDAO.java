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
import model.Class;

public interface ClassDAO {
    List<Class> findAll();
    Class findById(int id);
    void insert(Class c);
    void update(Class c);
    void delete(int id);
    
    int insertAndReturnId(Class c);
    void assignStudentsToClass(int class_id, String[] studentIds);
}