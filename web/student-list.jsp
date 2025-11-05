<%-- 
    Document   : student-list.jsp
    Created on : Oct 29, 2025
    Author     : baynv
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="model.Student"%>

<%@include file="header.jsp" %>

<div class="container">
    <h2>Student List</h2>
    <a href="student-form.jsp" class="add-btn btn btn-primary">+ Add New Student</a>

    <%
        List<Student> list = (List<Student>) request.getAttribute("students");
        if (list == null || list.isEmpty()) {
    %>
    <p class="no-data">No student found.</p>
    <%
    } else {
    %>
    <table class="table">
        <tr>
            <th>ID</th><th>Name</th><th>GPA</th><th>Class ID</th><th>Action</th>
        </tr>
        <% for (Student s : list) {%>
        <tr>
            <td><%= s.getId()%></td>
            <td><%= s.getName()%></td>
            <td><%= s.getGpa()%></td>
            <td><%= s.getClass_id()%></td>
            <td>
                <form action="StudentServlet" method="post" style="margin:0;" onsubmit="return confirm('Are you sure you want to delete this student?');">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="<%= s.getId()%>"/>
                    <input type="submit" value="Delete" class="btn btn-danger"/>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <% }%>
</div>

<%@include file="footer.jsp" %>
