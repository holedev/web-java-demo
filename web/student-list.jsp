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

    <div class="d-flex justify-content-between">
        <div class="d-flex gap-2 align-items-center">
            <h2>Student List</h2>
            <a href="student-form.jsp" class="btn btn-primary h-full">+ Add New Student</a>
        </div>

        <%
            int currentPage = (int) request.getAttribute("currentPage");
            int totalPages = (int) request.getAttribute("totalPages");
            String keyword = (String) request.getAttribute("keyword");
        %>
        <div class="d-flex align-items-center gap-4">
            <nav aria-label="Page navigation example">
                <ul class="pagination mb-0">
                    <% if (currentPage > 1) {%>
                    <li class="page-item"><a class="page-link" href="StudentServlet?page=<%=currentPage - 1%>&q=<%=keyword%>">Previous</a></li>
                        <% } %>

                    <% for (int i = 1; i <= totalPages; i++) {%>
                    <li class="page-item <%= (i == currentPage) ? "active" : ""%>">
                        <a class="page-link" href="StudentServlet?page=<%=i%>&q=<%=keyword%>"><%=i%></a>
                    </li>
                    <% } %>

                    <% if (currentPage < totalPages) {%>
                    <li class="page-item"><a class="page-link" href="StudentServlet?page=<%=currentPage + 1%>&q=<%=keyword%>">Next</a></li>
                        <% }%>
                </ul>
            </nav>

            <form class="d-flex" role="search">
                <input class="form-control me-2" value="<%=keyword != null ? keyword : ""%>" type="search" placeholder="Tên sinh viên" aria-label="Search" name="q"/>
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>


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
                <form class="d-inline" action="StudentServlet" method="post" style="margin:0;" onsubmit="return confirm('Are you sure you want to delete this student?');">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="<%= s.getId()%>"/>
                    <input type="submit" value="Delete" class="btn btn-danger"/>
                </form>
                <a href="StudentServlet?action=edit&id=<%=s.getId()%>"
                   class="btn btn-info">Edit</a>
            </td>
        </tr>
        <% } %>
    </table>
    <% }%>
</div>

<%@include file="footer.jsp" %>
