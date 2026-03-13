<%-- 
    Document   : logout
    Created on : Dec 18, 2023, 11:53:29 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            session.removeAttribute("user");
            session.removeAttribute("uname");
            session.invalidate();
            response.sendRedirect("index.html");
            %>
    </body>
</html>
