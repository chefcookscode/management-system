package com.sms.controller;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register") // URL: localhost:8080/ProjectName/register
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // 1. Fetch data from HTML form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String dept = request.getParameter("department");

        // 2. Set data to Model object
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setPassword(pass);
        student.setDepartment(dept);

        // 3. Call DAO to save
        StudentDAO dao = new StudentDAO();
        boolean isSaved = dao.registerStudent(student);

        // 4. Redirect based on result
        if (isSaved) {
            response.sendRedirect("login.jsp?msg=registered");
        } else {
            response.sendRedirect("register.jsp?error=failed");
        }
    }
}