<%-- 
    Document   : student-list.jsp
    Created on : Oct 29, 2025
    Author     : baynv
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="model.Class"%>

<%@include file="header.jsp" %>

<div class="container">
    <h2>Class List</h2>
    <a href="ClassServlet?action=new" class="add-btn">
        <button type="button" class="btn btn-primary">
            + Add new class
        </button>
    </a>

    <%
        List<Class> list = (List<Class>) request.getAttribute("classes");
        if (list == null || list.isEmpty()) {
    %>
    <p class="no-data">No class found.</p>
    <%
    } else {
    %>
    <table class="table">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Name</th>
            <th scope="col">Action</th>
        </tr>
        <% for (Class s : list) {%>
        <tr>
            <td scope="row"><%= s.getId()%></td>
            <td><%= s.getName()%></td>
            <td>
                <form class="d-inline" action="ClassServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this class?');">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="<%= s.getId()%>"/>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
                <a href="StatsServlet?id=<%= s.getId()%>" class="btn btn-secondary inline">Stats</a>
            </td>
        </tr>
        <% } %>
    </table>
    <% }%>
</div>

<%@include file="footer.jsp" %>
