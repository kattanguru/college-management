package com.nisum.college.bean.bo;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.nisum.college.bean.BaseBean;
import org.springframework.data.mongodb.core.index.Indexed;

@JsonRootName("student")
public class StudentBO extends BaseBean {

    private static final long serialVersionUID = 10000003l;

    private String firstName;
    private String lastName;
    private String email;
    private String course;
    private String branch;
    @Indexed(unique = true)
    private String phoneNumber;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "StudentBO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", course='" + course + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
