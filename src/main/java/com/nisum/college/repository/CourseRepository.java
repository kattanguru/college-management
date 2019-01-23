package com.nisum.college.repository;

import com.nisum.college.bean.dto.CourseDO;

import java.util.List;

public interface CourseRepository {

    List<CourseDO> getCourses();

    CourseDO getCourseByName(String name);

    CourseDO addCourse(CourseDO course);

    void removeCourse(String name);
}
