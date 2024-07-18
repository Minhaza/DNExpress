/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Impl.Dinh.DriverImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Driver;

/**
 *
 * @author dangq
 */
public class Svl_EditProfile_Driver extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Svl_EditProfile_Driver</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Svl_EditProfile_Driver at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        DriverImplement infoDAO = new DriverImplement();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");
        int id = account.getAccount_id(); //Nhập AccountID ở chỗ này
        Driver info = infoDAO.viewDriverInfo(id);
        request.setAttribute("info", info);
        
         Date dob = info.getAccount().getDob(); // Get the date from your data source

        if (dob != null) {
            // Format the date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateFormatted = dateFormat.format(dob);
            request.setAttribute("dobFormatted", dateFormatted);
        }

        request.getRequestDispatcher("Scr_EditProfile_EditProfileDriver.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("loginSession");

        int id = account.getAccount_id(); // Lấy Account_ID từ session hoặc từ request

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");

        String genderStr = request.getParameter("select-gen");
        char gender = ' ';
        if ("Female".equals(genderStr)) {
            gender = 'F';
        } else if ("Male".equals(genderStr)) {
            gender = 'M';
        } else {
            gender = 'O';
        }
        String dobStr = request.getParameter("dob");
        Date dob = null;
        try {
            dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String cccdNumber = request.getParameter("idNumber");
        String lisence = request.getParameter("licsNumber");

        DriverImplement infoDAO = new DriverImplement();

        account.setFirstName(fname);
        account.setLastName(lname);
        account.setEmail(email);
        account.setPhone_numb(phone);
        account.setDob(dob);
        account.setCccd_numb(cccdNumber);
        account.setGender(gender);
        Driver driver = new Driver(account, lisence);

        String msg = "";
        if (infoDAO.updateDriverInfo(driver)) {
            msg = "Update success!";
        } else {
            msg = "Update fail!";
        }

        session.setAttribute("msg", msg);
        response.sendRedirect("Svl_Profile_Driver");
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