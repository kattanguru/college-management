package com.nisum.college.service;

import com.nisum.college.bean.bo.StudentBO;

import java.util.List;

public interface StudentService {

    List<StudentBO> getStudents();

    List<StudentBO> getStudentsByBranch(String branchName);

    StudentBO getStudent(Long Id);

    StudentBO getStudentByPhone(String phone);

    StudentBO addStudent(StudentBO student);

    StudentBO updateStudentDetails(StudentBO student);

    void removeStudentDetails(Long Id);
}
