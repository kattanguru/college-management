package com.nisum.college.repository.impl;

import com.nisum.college.bean.dto.StudentDO;
import com.nisum.college.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nisum.college.bean.CollegeConstants.*;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private static Logger logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<StudentDO> getAllStudentsData() {
        List<StudentDO> students = mongoTemplate.findAll(StudentDO.class);
        logger.debug("Students Details from Database : {}", students);
        return students;
    }

    public List<StudentDO> getStudentsByBranch(String branchName) {
        List<StudentDO> students = mongoTemplate.find(new Query(Criteria.where(BRANCH_NAME).is(branchName)), StudentDO.class);
        logger.debug("Students Details by Branch Name from Database : {}", students);
        return students;
    }

    public StudentDO getStudentByPhone(String phone) {
        StudentDO student = mongoTemplate.findOne(new Query(Criteria.where(PHONE).is(phone)), StudentDO.class);
        logger.debug("Student Details by PhoneNumber from Database : {}", student);
        return student;
    }

    public StudentDO getStudentData(Long Id) {
        StudentDO student = mongoTemplate.findById(Id, StudentDO.class);
        logger.debug("Student Details by ID from Database : {}", student);
        return student;
    }

    public StudentDO addStudentData(StudentDO student) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, ID)).limit(1);
        student.setId(mongoTemplate.findOne(query, StudentDO.class).getId() + 1);
        mongoTemplate.insert(student);
        logger.debug("Student Details Added Successfully to Database: {}", student);
        return student;
    }

    public StudentDO updateStudentData(StudentDO student) {
        mongoTemplate.save(student);
        logger.debug("Student Details Updated Successfully to Database: {}", student);
        return student;
    }

    public void removeStudent(Long Id) {
        mongoTemplate.remove(new Query(Criteria.where(ID).is(Id)), StudentDO.class);
        logger.debug("Student Details Removed Successfully to Database: {}", Id);
    }
}
