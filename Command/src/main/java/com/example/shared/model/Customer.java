package com.example.shared.model;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Customer implements Serializable {
    public static final long serialVersionUID = 123;
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Customer() {}

    public Customer(String firstName, String lastName,String email, Integer age, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.gender = gender;
    }

    public Customer(String id, String firstName, String lastName, String email,int age, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return id+" "+firstName+" "+ lastName+" "+ email+" "+ age+" "+gender;
    }

}