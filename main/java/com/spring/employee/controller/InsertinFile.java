package com.spring.employee.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertinFile {

    public static void main(String[] args,Employee employee) {



   // public Employee insertResume(Employee employee) {

        String url = "jdbc:mysql://localhost:3306/springemployee";
        String user = "root";
        String password = "root";

        String filePath = "C:\\Users\\Admin\\Downloads";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO employee  values (?, ?, ?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,employee.getFirstname() );
            statement.setString(2, employee.getLastname());
            statement.setString(3,employee.getEmail());
            statement.setString(4,employee.getPhoneno());
            InputStream inputStream = new FileInputStream(new File(filePath));

            statement.setBlob(5, inputStream);

            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("A contact was inserted with photo image.");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


}