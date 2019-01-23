package com.nisum.college.repository.impl;

import com.nisum.college.bean.dto.CourseDO;
import com.nisum.college.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nisum.college.bean.CollegeConstants.ID;
import static com.nisum.college.bean.CollegeConstants.NAME;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private static Logger logger = LoggerFactory.getLogger(CourseRepositoryImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Cacheable(value = "college")
    public List<CourseDO> getCourses() {
        List<CourseDO> courses = mongoTemplate.findAll(CourseDO.class);
        logger.debug("Courses Details from Database : {}", courses);
        return courses;
    }

    @Cacheable(value = "college", key = "#name")
    public CourseDO getCourseByName(String name) {
        CourseDO course = mongoTemplate.findOne(new Query(Criteria.where(NAME).is(name)), CourseDO.class);
        logger.debug("Course Details by Course Name from Database : {}", course);
        return course;
    }

    public CourseDO addCourse(CourseDO course) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, ID)).limit(1);
        course.setId(mongoTemplate.findOne(query, CourseDO.class).getId() + 1);
        mongoTemplate.insert(course);
        logger.debug("Course Added Successfully to Database : {}", course);
        return course;
    }

    public void removeCourse(String name) {
        mongoTemplate.remove(new Query(Criteria.where(NAME).is(name)), CourseDO.class);
        logger.debug("Course Removed Successfully to Database : {}", name);
    }
}
