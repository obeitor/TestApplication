package com.gracefield.finalapplication;

/**
 * Created by Abdulgafar Obeitor on 8/6/2018.
 * THis is a Java Class
 * It is a template or structure defined to use in creating an object
 * It defines the properties (variables) and the behaviours (methods)
 * This particular class is for Users.
 */
public class UserClass {
    /**
     * This defines the structure of a user, a typical user would have an id, firstname, lastname,
     * gender, department, state of origin
     * private is written in front of the variables so that only the variable is only accessible to this clas
     * protected is written so as to make it available to anyone in the same package
     * com.gracefield.finalapplication
     * with UserClass
     * public is written so as to make it available to everyone.
     *
     * the variables would use private, then getter and setter methods would be created with public so the
     * external world can get the users id or set it accordingly,
     * a getter is a method that returns the variable, while a setter sets the variable, an example would be shown
     * if there is a variable you dont want the public to be able to set, you can make its setter method private
     * else it would be public
     */
    private int id;
    private String firstname;
    private String lastname;
    private String gender;
    private String department;
    private String state;

    /**
     * This below is called a constructor,
     * it is a creator of the class, so anything defined inside is what happens whenever u are creating a new member of the class
     * This particular one does not have anything inside of the brackets, this is called a default constructor
     */
    public UserClass(){

    }
    /**
     * This constructor has variables inside the bracket, this variables are passed into it
     * while creating the new member,
     * therefore if you call the constructor : new UserClass("Abdul", "Gafar","male","IT","Kogi")
     * the new member created would have his name, gender, dept etc set accordingly from creation
     */
    public UserClass(String fname, String lname, String gndr, String dept, String st){
        this.firstname = fname;
        this.lastname = lname;
        this.gender = gndr;
        this.department = dept;
        this.state = st;
    }

    /**
     * This is a getter method for the ID,
     * when you call it outside, it gives you that particular users ID
     * @return
     */
    public int getId() {
        return id;
    }


    /**
     * This is the setter method for the id
     * you pass the ID into it and it is being assigned to the user accordingly
     * Same thing for the getter and setter methods of all other properties
     * @param id
     */
    public void setId(int id) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
