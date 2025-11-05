/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBUtil;
import java.sql.*;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author phanl
 */
public class StatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int classId = Integer.parseInt(req.getParameter("id"));
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT COUNT(*) AS total, AVG(gpa) AS avgGpa FROM student WHERE class_id=?"
            );
            ps.setInt(1, classId);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                req.setAttribute("total", rs.getInt("total"));
                req.setAttribute("avg", rs.getDouble("avgGpa"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        
        RequestDispatcher rd = req.getRequestDispatcher("chart.jsp");
        rd.forward(req, resp);
    }
}
