<%-- 
    Document   : deleteseed
    Created on : Dec 13, 2023, 12:40:07 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.sql.*"%>
<%@page import="dbpack.dbconnection"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            try
            {
                String sname=request.getParameter("sname");
                Connection con=new dbconnection().getconnection();
                PreparedStatement pst=con.prepareStatement("delete from product where supplier=? and pname=?");
                pst.setString(1,(String)session.getAttribute("uname"));
                pst.setString(2,sname);
                pst.executeUpdate();
                
                response.sendRedirect("supplierviewproducts.jsp?msg=DELETED");
            }
            catch(Exception e)
            {
                System.out.println(e);
                e.printStackTrace();
            }
        %>
    </body>
</html>
