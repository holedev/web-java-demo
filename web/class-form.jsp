<%-- 
    Document   : student-form.jsp
    Created on : Oct 29, 2025
    Author     : baynv
--%>

<%@page import="model.Student"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="header.jsp" %>

<div class="container mx-auto">
    <h2>Add Class</h2>
    <form action="ClassServlet" method="post">
        <input type="hidden" name="action" value="add"/>
        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <input type="text" class="form-control" id="name" name="name" required/>
        </div>

        <div class="mb-3">
            <label for="students" class="form-label">Select Student:</label>
            <%
                List<Student> students = (List<Student>) request.getAttribute("students");
                if (students != null) {
            %>
            <select class="form-select" id="students" name="studentIds" multiple size="6">
                <%  for (Student s : students) {%>
                <option value="<%= s.getId()%>">
                    <%= s.getName()%> GPA: <%= s.getGpa()%>
                </option>
                <% } %>
            </select>
            <%} else { %>
            <div>Empty Students</div>
            <% }%>
        </div>

        <input type="submit" value="Save" class="btn btn-danger"/>
    </form>

    <a href="ClassServlet" class="back-link btn btn-secondary mt-2">← Back to List</a>
</div>

<%@include file="footer.jsp" %>
