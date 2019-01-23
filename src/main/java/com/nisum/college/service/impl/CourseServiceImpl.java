package com.nisum.college.service.impl;

import com.nisum.college.bean.bo.BranchBO;
import com.nisum.college.bean.bo.CourseBO;
import com.nisum.college.bean.bo.StudentBO;
import com.nisum.college.bean.dto.CourseDO;
import com.nisum.college.repository.BranchRepository;
import com.nisum.college.repository.CourseRepository;
import com.nisum.college.repository.StudentRepository;
import com.nisum.college.service.CourseService;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nisum.college.bean.CollegeConstants.BRANCH_NAME;
import static com.nisum.college.bean.CollegeConstants.STUDENT_NAME;

@Service
public class CourseServiceImpl implements CourseService {

    private static Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MapperFacade mapperFacade;

    public List<CourseBO> getCourses(String expand) {
        logger.debug("START :: Courses Details : expand : {}", expand);
        List<CourseBO> courses = mapperFacade.mapAsList(courseRepository.getCourses(), CourseBO.class);
        if (CollectionUtils.isNotEmpty(courses) && StringUtils.isNotBlank(expand) && expand.contains(BRANCH_NAME)) {
            for (CourseBO course : courses) {
                List<BranchBO> branches = mapperFacade.mapAsList(branchRepository.getBranchesByCourse(course.getName()), BranchBO.class);
                if (expand.contains(STUDENT_NAME) && CollectionUtils.isNotEmpty(branches)) {
                    for (BranchBO branch : branches) {
                        List<StudentBO> students = mapperFacade.mapAsList(studentRepository.getStudentsByBranch(branch.getName()), StudentBO.class);
                        students.stream().forEach(student -> student.setCourse(branch.getCourse()));
                        branch.setStudents(students);
                    }
                }
                course.setBranches(branches);
            }
        }
        logger.debug("END :: Courses Details : {}", courses);
        return courses;
    }

    public CourseBO getCourseByName(String name, String expand) {
        logger.debug("START :: Course Details by Course Name: {} : expand : {}", name, expand);
        CourseBO course = mapperFacade.map(courseRepository.getCourseByName(name), CourseBO.class);
        if (null != course && StringUtils.isNotBlank(expand) && expand.contains(BRANCH_NAME)) {
            List<BranchBO> branches = mapperFacade.mapAsList(branchRepository.getBranchesByCourse(course.getName()), BranchBO.class);
            if (CollectionUtils.isNotEmpty(branches) && StringUtils.isNotBlank(expand) && expand.contains(STUDENT_NAME)) {
                for (BranchBO branch : branches) {
                    List<StudentBO> students = mapperFacade.mapAsList(studentRepository.getStudentsByBranch(branch.getName()), StudentBO.class);
                    students.stream().forEach(student -> student.setCourse(branch.getCourse()));
                    branch.setStudents(students);
                }
            }
            course.setBranches(branches);
        }
        logger.debug("END :: Course Details : {}", course);
        return course;
    }

    public CourseBO addCourse(CourseBO course) {
        logger.debug("Add Course Details : {}", course);
        branchRepository.clearCache();
        return mapperFacade.map(courseRepository.addCourse(mapperFacade.map(course, CourseDO.class)), CourseBO.class);
    }

    public void removeCourse(String name) {
        logger.debug("Remove Course Details : {}", name);
        branchRepository.clearCache();
        courseRepository.removeCourse(name);
    }
}
