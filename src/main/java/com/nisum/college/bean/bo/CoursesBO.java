package com.nisum.college.bean.bo;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

@JsonRootName("courses")
public class CoursesBO implements Serializable {

    private static final long serialVersionUID = 10000006l;

    private List<CourseBO> courses;

    public List<CourseBO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseBO> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "CoursesBO{" +
                "courses=" + courses +
                '}';
    }
}
