/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbpack.dbconnection;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author user
 */
@WebServlet(name = "supplieraddproduct", urlPatterns = {"/supplieraddproduct"})
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class supplieraddproduct extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String sname = request.getParameter("pname");
            String desc = request.getParameter("desc");
            String price = request.getParameter("price");
            
            System.out.println(sname);
            System.out.println(desc);
            System.out.println(price);
            
            InputStream inputStream = null;
            Part filePart = request.getPart("photo");
            if (filePart != null) {

            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());


            inputStream = filePart.getInputStream();
            }
            
            HttpSession session=request.getSession();
            Connection con=new dbconnection().getconnection();
            
            PreparedStatement pst=con.prepareStatement("select * from product where supplier=? and pname=?");
            pst.setString(1,(String)session.getAttribute("uname"));
            pst.setString(2,sname);
            ResultSet rs=pst.executeQuery();
            
            if (rs.next())
            {
                response.sendRedirect("suppliermenu.jsp?msg=EXIST");
            }
            
            PreparedStatement pst2=con.prepareStatement("insert into product values(?,?,?,?,?)");
            pst2.setString(1,(String)session.getAttribute("uname"));
            pst2.setString(2,sname);
            pst2.setString(3,desc);
            pst2.setDouble(4,Double.parseDouble(price));
            
            File f=new File("C:\\defensesupplychain\\product");
                System.out.println(f.getPath());
                if (!f.exists())
                    f.mkdirs();
                
            byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                filePart.write(f.getPath()+"\\"+sname+".jpg");
                pst2.setString(5,f.getPath()+"\\"+sname+".jpg");
                
            pst2.executeUpdate();
            
            response.sendRedirect("suppliermenu.jsp?msg=SUCCESS");
        
        
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
