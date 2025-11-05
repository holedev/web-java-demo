/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author baynv
 */
import dao.*;
import model.Class;
import model.Student;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ClassServlet extends HttpServlet {
    private final ClassDAO dao = new ClassDAOImpl();
    private final StudentDAO studentDao = new StudentDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // Đảm bảo JSP hiển thị tiếng Việt
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        
        String action = req.getParameter("action");
        
        if ("new".equals(action)) {
            List<Student> students = studentDao.findStudentsWithoutClass();
            req.setAttribute("students", students);
            
            RequestDispatcher rd = req.getRequestDispatcher("class-form.jsp");
            rd.forward(req, resp);
            return;
        }

        List<Class> classes = dao.findAll();
        req.setAttribute("classes", classes);
        RequestDispatcher rd = req.getRequestDispatcher("class-list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // đặt TRƯỚC khi đọc getParameter()
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            String name = req.getParameter("name");
            int classId = dao.insertAndReturnId(new Class(0, name));
            
            String[] studentIds = req.getParameterValues("studentIds");
            
            
            dao.assignStudentsToClass(classId, studentIds);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
        }

        resp.sendRedirect("ClassServlet");
    }
}