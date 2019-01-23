package com.nisum.college.service.impl;

import com.nisum.college.bean.bo.BranchBO;
import com.nisum.college.bean.bo.StudentBO;
import com.nisum.college.bean.dto.StudentDO;
import com.nisum.college.repository.BranchRepository;
import com.nisum.college.repository.StudentRepository;
import com.nisum.college.service.StudentService;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private MapperFacade mapperFacade;

    public List<StudentBO> getStudents() {
        logger.debug("START :: Retrieve Students Details");
        List<StudentBO> students = mapperFacade.mapAsList(studentRepository.getAllStudentsData(), StudentBO.class);
        if (CollectionUtils.isNotEmpty(students)) {
            for (StudentBO student : students) {
                student.setCourse(mapperFacade.map(branchRepository.getBranchByName(student.getBranch()), BranchBO.class).getCourse());
            }
        }
        logger.debug("END :: Retrieve Students Details :{}", students);
        return students;
    }

    public List<StudentBO> getStudentsByBranch(String branchName) {
        logger.debug("START :: Retrieve Students Details By Branch Name : {}", branchName);
        List<StudentBO> students = mapperFacade.mapAsList(studentRepository.getStudentsByBranch(branchName), StudentBO.class);
        BranchBO branch = mapperFacade.map(branchRepository.getBranchByName(branchName), BranchBO.class);
        students.stream().forEach(student -> student.setCourse(branch.getCourse()));
        logger.debug("END :: Retrieve Students Details By Branch Name : {}", students);
        return students;
    }

    public StudentBO getStudentByPhone(String phone) {
        logger.debug("START :: Retrieve Student Details By Phone Number : {}", phone);
        StudentBO student = mapperFacade.map(studentRepository.getStudentByPhone(phone), StudentBO.class);
        BranchBO branch = mapperFacade.map(branchRepository.getBranchByName(student.getBranch()), BranchBO.class);
        student.setCourse(branch.getCourse());
        logger.debug("END :: Retrieve Student Details By Phone Number : {}", student);
        return student;
    }

    public StudentBO getStudent(Long Id) {
        logger.debug("Retrieve Student Details By ID : {}", Id);
        return mapperFacade.map(studentRepository.getStudentData(Id), StudentBO.class);
    }

    public StudentBO addStudent(StudentBO student) {
        logger.debug("Add Student Details : {}", student);
        return mapperFacade.map(studentRepository.addStudentData(mapperFacade.map(student, StudentDO.class)), StudentBO.class);
    }

    public StudentBO updateStudentDetails(StudentBO student) {
        logger.debug("Update Student Details : {}", student);
        return mapperFacade.map(studentRepository.updateStudentData(mapperFacade.map(student, StudentDO.class)), StudentBO.class);
    }

    public void removeStudentDetails(Long Id) {
        logger.debug("Remove Student Details : {}", Id);
        studentRepository.removeStudent(Id);
    }
}
