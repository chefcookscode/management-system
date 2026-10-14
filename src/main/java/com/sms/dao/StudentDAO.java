package com.sms.dao;

import com.sms.model.Student;
import com.sms.util.DBConnection;
import java.sql.*;

public class StudentDAO {

    // 1. REGISTER USER
    public boolean registerStudent(Student student) {
        boolean status = false;
        try {
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO student(name, email, password, department) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPassword());
            ps.setString(4, student.getDepartment());

            int result = ps.executeUpdate();
            if(result > 0) status = true;
            
        } catch (Exception e) { e.printStackTrace(); }
        return status;
    }

    // 2. CHECK LOGIN
    public Student checkLogin(String email, String password) {
        Student student = null;
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM student WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setName(rs.getString("name"));
                student.setEmail(rs.getString("email"));
                student.setDepartment(rs.getString("department"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return student;
    }
}