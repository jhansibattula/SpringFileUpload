package com.spring.employee.controller;

//import org.springframework.


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private int idCount = 0;

    public void setTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Employee getEmpById(int id, Employee employee) {
        try {


            String sql = "select * from employee where id=?";
            jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Employee>(Employee.class));

        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new SecurityException("please Give A Proper Id");
        }

        if (employee.getFirstname() == null) {
            throw new SecurityException("Id Does Not Exist :" + id);
        }


        return employee;
    }

    public List<Employee> getEmployees() {
        return jdbcTemplate.query("select * from employee", new RowMapper<Employee>() {
            public Employee mapRow(ResultSet rs, int row) throws SQLException {
                Employee employee = new Employee();
                employee.setFirstname(rs.getString(1));
                employee.setLastname(rs.getString(2));
                employee.setEmail(rs.getString(3));
                employee.setPhoneno(rs.getString(4));
                employee.setId(rs.getInt(5));
                return employee;
            }
        });


    }

    public Employee createEmployee(Employee employee) throws SQLException {
        try {

            //String sql= " insert into employee(firstname, lastname, email, phoneno) values (?, ?, ?, ?)";
            String sql = "insert into employee(firstname,lastname,email,phoneno,id) values('" + employee.getFirstname() + "','" + employee.getLastname() + "','" + employee.getEmail() + "','" + employee.getPhoneno() + "'," + employee.getId() + ")";
            jdbcTemplate.update(sql);

            String s = Integer.toString(employee.getId());

            if (employee.getId() == 0) {
                System.out.println("success");
                employee.setId(++idCount);
            }

            if(employee.getFirstname().isEmpty()){
                throw new SecurityException("first name should not be empty");
            }
            if(employee.getLastname().isEmpty()){
                throw new SecurityException("last name should not be empty");
            }

            if(employee.getEmail().isEmpty()){
                throw new SecurityException("email  should not be empty");
            }

            if(employee.getPhoneno().isEmpty()){
                throw new SecurityException("phone number  should not be empty");
            }





            boolean status = EmailVerification.email_Valid(employee.getEmail());
            if (status) {
            } else {
                throw new SecurityException("Enter a Valid Email");

            }
             status = EmailVerification.phoneNumber(employee.getPhoneno());
            if (status) {
            } else {
                throw new SecurityException("Enter a Valid Phone Number");

            }



        } catch (DataAccessException e) {
            //e.printStackTrace();
            if(employee.getPhoneno().length()>10){
                throw new SecurityException("phone number  should be 10 digits ");
            }
            else {

                throw new SecurityException("duplicate data not allowed");
            }
        }

        return employee;
    }


    public Employee updateEmployee(Employee employee) {

        try {

            String sql = "update employee set firstname='" + employee.getFirstname() + "', lastname='" + employee.getLastname() + "',email='" + employee.getEmail() + "',phoneno='" + employee.getPhoneno() + "' where id=" + employee.getId() + "";
            jdbcTemplate.update(sql);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new SecurityException("duplicate data not allowed");
        }


        if (employee.getPhoneno().isEmpty()) {
            throw new SecurityException("Phone Number Should Not Be Empty");
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

    public String deleteEmployee(int id, Employee employee) {
        try {
            String sql = "delete from employee where id=" + id + "";
            jdbcTemplate.update(sql);

        } catch (DataAccessException e) {
            //e.printStackTrace();
            throw new SecurityException("please give a proper id");
        }
        if (employee.getId() != id) {
            throw new SecurityException("plese give a pproper id");
        }
        if (id == 100) {
            throw new SecurityException("id Does not exist");
        }

        return "data has been deleted";
    }

    public Employee updateResume(int id, Employee employee) {
        FileInputStream input=null;

        try {
            //InputStream inputStream = new FileInputStream(new File(filePath));
            Connection con = Dbconnection.getCon();
            PreparedStatement ps = con.prepareStatement("update employee set photo=? where id=?");
            File file = new File("C:\\Users\\Admin\\Downloads");
            input=new FileInputStream(file);
            ps.setBinaryStream(1,input);

            int n = ps.executeUpdate();
            if (n == 1) {
                System.out.println(" rent books data updataed");
            } else {
                System.out.println(" rent books data not updated");
            }

        } catch (Exception e) {
            System.out.println("database not connected");

        }
        return employee;
    }
}