package com.sms.controller;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        StudentDAO dao = new StudentDAO();
        Student student = dao.checkLogin(email, pass);

        if (student != null) {
            // LOGIN SUCCESS: Create a Session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", student); // Save user data in session
            
            response.sendRedirect("dashboard.jsp"); // Go to dashboard
        } else {
            // LOGIN FAILED
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}