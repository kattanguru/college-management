package com.nisum.college.bean.bo;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.nisum.college.bean.BaseBean;

import java.util.List;

@JsonRootName("branch")
public class BranchBO extends BaseBean {

    private static final long serialVersionUID = 10000005l;

    private String name;
    private String course;
    private List<StudentBO> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentBO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentBO> students) {
        this.students = students;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "BranchBO{" +
                "name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", students=" + students +
                '}';
    }
}
