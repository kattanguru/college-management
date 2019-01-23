package com.nisum.college.bean.bo;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

@JsonRootName("students")
public class StudentsBO implements Serializable {

    private static final long serialVersionUID = 10000002l;

    private List<StudentBO> students;

    public List<StudentBO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentBO> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "StudentsBO{" +
                "students=" + students +
                '}';
    }
}
