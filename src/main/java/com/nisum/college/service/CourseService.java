package com.nisum.college.service;

import com.nisum.college.bean.bo.CourseBO;

import java.util.List;

public interface CourseService {

    List<CourseBO> getCourses(String expand);

    CourseBO getCourseByName(String name, String expand);

    CourseBO addCourse(CourseBO course);

    void removeCourse(String name);
}
