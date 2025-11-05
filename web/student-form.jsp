<%-- 
    Document   : student-form.jsp
    Created on : Oct 29, 2025
    Author     : baynv
--%>

<%@include file="header.jsp" %>

<div class="form-container container">
    <h2>Add Student</h2>
    <form action="StudentServlet" method="post">
        <input type="hidden" name="action" value="add"/>

        <label class="form-label" for="name">Name:</label>
        <input class="form-control" type="text" id="name" name="name" required/>

        <label class="form-label" for="gpa">GPA:</label>
        <input class="form-control" type="text" id="gpa" name="gpa" required/>

        <input type="submit" value="Save" class="btn btn-primary mt-3"/>
    </form>

    <a href="StudentServlet" class="btn btn-secondary mt-2">? Back to List</a>
</div>

<%@include file="footer.jsp" %>
