package com.spring.employee.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconnection {


     private  static Connection con;
    // private Dbconnection(){}
    static {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/springemployee","root","root");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    static Connection getCon(){
        return con;
    }

}
