package com.spring.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Component
public class EmployeeService {

    @Autowired
    private static Connection con = Dbconnection.getCon();


    public Employee createEmployee(Employee employee) throws UserNotFoundExeption {
        try {
            String query = " insert into employee(firstname, lastname, email, phoneno) values (?, ?, ?, ?)";
            // if (con != null) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getPhoneno());
            int n = ps.executeUpdate();
            if (n == 1) {
                System.out.println("record inserted succesfully");

            } else {
                System.out.println("record not inserted sucessfully");

            }


        } catch (Exception e) {
            //System.out.println();
            throw new SecurityException("duplicate data not allowed");

        }
        if (employee.getFirstname() == null) {
            //throw new
        }

        return employee;
    }



    public List<Employee> getEmployee() {
        List<Employee> employeelist = new ArrayList<>();

        try {
            //Connection con = Dbconnection.getCon();
            PreparedStatement ps = con.prepareStatement("select * from employee");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee(rs.getString(1), rs.getString(2), rs.getNString(3), rs.getString(4), rs.getInt(5));
                // System.out.println(" Getting employee values");
                employeelist.add(employee);
            }
        } catch (Exception e) {
            System.out.println("can not connected to database...");
        }
        return employeelist;

    }

   /* @GetMapping("/employee/{id}")
    public Employee getempbyid(@PathVariable int id, Employee employee) {
*/
   public Employee getempbyId(int id,Employee employee) throws UserNotFoundExeption {

        try {

            Connection con = Dbconnection.getCon();
            PreparedStatement ps = con.prepareStatement("select * from employee where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employee = new Employee(rs.getString(1), rs.getString(2), rs.getNString(3), rs.getString(4), rs.getInt(5));

            }

        } catch (Exception e) {
            //System.out.println("can not connected to database...");
            throw new SecurityException("Enter a Proper Id Number " + id);
        }
        //return employee;

        if (employee.getFirstname() == null) {
            throw new SecurityException("Does Not Exist The Id" + id);
        }
        return employee;
    }




    public Employee upDateEmployee( int id ,Employee employee) throws UserNotFoundExeption
     {
        try {
            //Connection con = Dbconnection.getCon();
            PreparedStatement ps = con.prepareStatement("update employee set firstname= ?,lastname=?,email=?,phoneno=? where id =?");
            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getPhoneno());
            ps.setInt(5, id);
            int n = ps.executeUpdate();
            if (n == 1) {
                System.out.println(" data updataed");
            } else {
                // throw new UserNotFoundExeption("duplicate data not allowed" +id);
                System.out.println(" data not updated");
            }
        } catch (Exception e) {
            throw new SecurityException("duplicate data not allowed" + id);
            //System.out.println(" data not updated");
            // System.out.println(e.getMessage());
        }

        if (employee.getPhoneno().isEmpty()) {
            throw new SecurityException("Phone Number Should Not Be Empty" + id);
        }
        if (employee.getEmail().isEmpty()) {
            throw new SecurityException("Email Should Not Be Empty");
        }
        if (employee.getFirstname().isEmpty()) {
            throw new SecurityException("Firstname Should Not Be Empty");

            }

        if (employee.getLastname().isEmpty()) {
            throw new SecurityException("Lastname Should Not Be Empty");
        }

        return employee;
    }

    public String deleteEmployee(int id){
        try {
            // Connection con = Dbconnection.getCon();
            PreparedStatement ps = con.prepareStatement("delete from employee where id =?");
            ps.setInt(1,id );
            ResultSet rs = ps.executeQuery();
            int n = ps.executeUpdate();
            if (n == 1) {
                System.out.println("record deleted succesfully");

            } else {
                System.out.println("record not not sucessfully");
            }
        } catch (Exception e) {
           // throw new UserNotFoundExeption("please give a valid id number" + id);
            System.out.println("can not connected to database...");
        }
        return "deleted successfully";
    }

}