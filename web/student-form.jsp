<%-- 
    Document   : student-form.jsp
    Created on : Oct 29, 2025
    Author     : baynv
--%>

<%@page import="model.Student"%>
<%@include file="header.jsp" %>

<%
    Student s = (Student) request.getAttribute("student");
    boolean isEdit = (s != null);
%>

<div class="form-container container">
    <h2><%= isEdit ? "Edit Student" : "Add New Student"%></h2>
    <form action="StudentServlet" method="post">
        <input type="hidden" name="action" value="<%= isEdit ? "update" : "add"%>"/>
        <% if (isEdit) {%>
        <input type="hidden" name="id" value="<%= s.getId()%>"/>
        <% }%>

        <label class="form-label" for="name">Name:</label>
        <input class="form-control" type="text" id="name" name="name" required value="<%= isEdit ? s.getName() : "" %>"/>

        <label class="form-label" for="gpa">GPA:</label>
        <input class="form-control" type="text" id="gpa" name="gpa" required value="<%= isEdit ? s.getGpa() : "" %>"/>

        <input type="submit" value="<%= isEdit ? "Update" : "Save" %>" class="btn btn-primary mt-3"/>
    </form>

    <a href="StudentServlet" class="btn btn-secondary mt-2">Back to List</a>
</div>

<%@include file="footer.jsp" %>
