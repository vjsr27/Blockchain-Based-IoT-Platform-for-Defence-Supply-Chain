/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbpack.dbconnection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
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
@WebServlet(name = "partnerpayment", urlPatterns = {"/partnerpayment"})
public class partnerpayment extends HttpServlet {

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
            String seller=request.getParameter("supplier");
            String pname=request.getParameter("pname");
            String q=request.getParameter("quantity");
            String total=request.getParameter("total");
            String pmode=request.getParameter("pmode");
            String cno=request.getParameter("cno");
            
            int tno=1000+new Random().nextInt(10000);
            
            HttpSession s=request.getSession();
            String partner=(String)s.getAttribute("uname");
            
            //save inDB
            /*
            Connection con=new dbconnection().getconnection();
            PreparedStatement pst=con.prepareStatement("insert into seedsales values(?,?,?,?,?,?,?)");
            pst.setInt(1,tno);
            pst.setString(2,seller);
            pst.setString(3,seed);
            pst.setString(4,q);
            pst.setString(5,total);
            pst.setString(6,farmer);
            pst.setString(7,new java.util.Date().toString());
            pst.executeUpdate();
            */
            
            //insert inn blockchain
            Socket soc=new Socket("localhost",3000);
            System.out.println("socket conneted");
            ObjectOutputStream oos=new ObjectOutputStream(soc.getOutputStream());
            ObjectInputStream oin=new ObjectInputStream(soc.getInputStream());
            System.out.println("streams created");
            
            oos.writeObject("ADDBLOCK");
            oos.writeObject(Integer.toString(tno));
            oos.writeObject(partner);
            oos.writeObject(seller);            
            oos.writeObject(pname);   
            oos.writeObject(q);  
            oos.writeObject(total);  
            oos.writeObject(pmode);
            oos.writeObject(cno);
            oos.writeObject(new java.util.Date().toString());
            oos.writeObject("PENDING");
                       
            String reply=(String)oin.readObject();
            
            if (reply.equals("SUCCESS"))
            {
                response.sendRedirect("partnerorders.jsp?msg=PENDING");
            }
            
            
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
