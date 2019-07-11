package com.spring.employee.controller;


public class Employee  {
    //@NotNull(message = "first Name Should Not Null")
    //@Size(min=3,message ="Name Should Have atleast 3 Characters " )
    private  String firstname;
    //@Size(message = "Last Name Should Have Atleast 3 characters")
    private String lastname;
    //@Deprecated
    //@Email(message = " Email Should be Valid ")
    private  String email;
  // @Pattern(regexp="^(0|[1-9][0-9]*)$",message = "Phone Number Shold be Valid")
    //@Max(value = 10,message = "Phone Number should have  10 Numbers")
    //@Min(10)
    //@Size(min = 10,max = 10,message = "phone number should have 10 numbers")
    private  String phoneno;
    //@Pattern(regexp="^(0|[1-9][0-9]*)$",message = "Id Number Shuld Be Proper")
    private  int id;

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Employee(String firstname, String lastname, String email, String phoneno, int id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneno = phoneno;
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
