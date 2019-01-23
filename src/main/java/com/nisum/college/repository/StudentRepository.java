package com.nisum.college.repository;

import com.nisum.college.bean.dto.StudentDO;

import java.util.List;

public interface StudentRepository {

    List<StudentDO> getAllStudentsData();

    List<StudentDO> getStudentsByBranch(String branchName);

    StudentDO getStudentData(Long Id);

    StudentDO getStudentByPhone(String phone);

    StudentDO addStudentData(StudentDO student);

    StudentDO updateStudentData(StudentDO student);

    void removeStudent(Long Id);
}
