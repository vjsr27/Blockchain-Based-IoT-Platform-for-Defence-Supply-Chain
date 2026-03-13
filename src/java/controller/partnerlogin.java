/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbpack.dbconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "partnerlogin", urlPatterns = {"/partnerlogin"})
public class partnerlogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String uname=request.getParameter("uname");
            String pass=request.getParameter("pwd");
            
            Connection con=new dbconnection().getconnection();
            
            PreparedStatement pst=con.prepareStatement("select * from partner where uname=? and pass=?");
            pst.setString(1,uname);
            pst.setString(2,pass);
            ResultSet rs=pst.executeQuery();
            
            if (rs.next())
            {
                PreparedStatement pst2=con.prepareStatement("select * from partner where uname=? and status=?");
                pst2.setString(1,uname);
                pst2.setString(2,"ACTIVE");
                ResultSet rs2=pst2.executeQuery();
                
                if (rs2.next())
                {
                    HttpSession s=request.getSession();
                    s.setAttribute("user","partner");
                    s.setAttribute("uname",uname);
                
                    response.sendRedirect("partnermenu.jsp");
                }
                else
                {
                    response.sendRedirect("partnerlogin.jsp?msg=DEACTIVE");
                }
            }
            else
                response.sendRedirect("partnerlogin.jsp?msg=FAILED");
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
