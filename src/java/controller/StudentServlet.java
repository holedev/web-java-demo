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
import model.Student;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {

    private final StudentDAO dao = new StudentDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Đảm bảo JSP hiển thị tiếng Việt
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Student s = dao.findById(id);
            req.setAttribute("student", s);
            RequestDispatcher rd = req.getRequestDispatcher("student-form.jsp");
            rd.forward(req, resp);
            return;
        }

        int page = 1;
        int pageSize = 8;
        String keyword = req.getParameter("q");

        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        List<Student> students;
        int totalRecords;

        if (keyword != null && !keyword.trim().isEmpty()) {
            students = dao.searchByName(keyword.trim(), page, pageSize);
            totalRecords = dao.countByName(keyword.trim());
            req.setAttribute("keyword", keyword);
        } else {
            students = dao.findAll(page, pageSize);
            totalRecords = dao.countAll();
            req.setAttribute("keyword", "");
        }

        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        req.setAttribute("students", students);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        RequestDispatcher rd = req.getRequestDispatcher("student-list.jsp");
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
            double gpa = Double.parseDouble(req.getParameter("gpa"));
            dao.insert(new Student(0, name, gpa));
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            dao.delete(id);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            double gpa = Double.parseDouble(req.getParameter("gpa"));
            Student s = new Student(id, name, gpa);
            dao.update(s);
        }

        resp.sendRedirect("StudentServlet");
    }
}
