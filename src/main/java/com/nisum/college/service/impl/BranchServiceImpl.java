package com.nisum.college.service.impl;

import com.nisum.college.bean.bo.BranchBO;
import com.nisum.college.bean.bo.StudentBO;
import com.nisum.college.bean.dto.BranchDO;
import com.nisum.college.repository.BranchRepository;
import com.nisum.college.repository.StudentRepository;
import com.nisum.college.service.BranchService;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nisum.college.bean.CollegeConstants.STUDENT_NAME;

@Service
public class BranchServiceImpl implements BranchService {

    private static Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MapperFacade mapperFacade;

    public List<BranchBO> getBranches(String expand) {
        logger.debug("START :: Branches Details : expand {}", expand);
        List<BranchBO> branches = mapperFacade.mapAsList(branchRepository.getBranches(), BranchBO.class);
        if (CollectionUtils.isNotEmpty(branches) && StringUtils.isNotBlank(expand) && expand.contains(STUDENT_NAME)) {
            for (BranchBO branch : branches) {
                List<StudentBO> students = mapperFacade.mapAsList(studentRepository.getStudentsByBranch(branch.getName()), StudentBO.class);
                students.stream().forEach(student -> student.setCourse(branch.getCourse()));
                branch.setStudents(students);
            }
        }
        logger.debug("END :: Branches Details : {}", branches);
        return branches;
    }

    public BranchBO getBranchByName(String name, String expand) {
        logger.debug("START :: Branch Details by Branch Name: {} : expand {}", name, expand);
        BranchBO branch = mapperFacade.map(branchRepository.getBranchByName(name), BranchBO.class);
        if (null != branch && StringUtils.isNotBlank(expand) && expand.contains(STUDENT_NAME)) {
            List<StudentBO> students = mapperFacade.mapAsList(studentRepository.getStudentsByBranch(name), StudentBO.class);
            students.stream().forEach(student -> student.setCourse(branch.getCourse()));
            branch.setStudents(students);
        }
        logger.debug("END :: Branch Details by Branch Name: {}", branch);
        return branch;
    }

    public List<BranchBO> getBranchByCourse(String name, String expand) {
        logger.debug("START :: Branches Details by Course Name : {} : expand {}", name, expand);
        List<BranchBO> branches = mapperFacade.mapAsList(branchRepository.getBranchesByCourse(name), BranchBO.class);
        if (CollectionUtils.isNotEmpty(branches) && StringUtils.isNotBlank(expand) && expand.contains(STUDENT_NAME)) {
            for (BranchBO branch : branches) {
                List<StudentBO> students = mapperFacade.mapAsList(studentRepository.getStudentsByBranch(branch.getName()), StudentBO.class);
                students.stream().forEach(student -> student.setCourse(branch.getCourse()));
                branch.setStudents(students);
            }
        }
        logger.debug("END :: Branches Details by Course Name: {}", branches);
        return branches;
    }

    public BranchBO updateBranch(BranchBO branch) {
        logger.debug("Update Branch Details : {}", branch);
        branchRepository.clearCache();
        return mapperFacade.map(branchRepository.updateBranch(mapperFacade.map(branch, BranchDO.class)), BranchBO.class);
    }

    public BranchBO addBranch(BranchBO branch) {
        logger.debug("Add Branch Details : {}", branch);
        branchRepository.clearCache();
        return mapperFacade.map(branchRepository.addBranch(mapperFacade.map(branch, BranchDO.class)), BranchBO.class);
    }

    public void removeBranch(String name) {
        logger.debug("Remove Branch Details : {}", name);
        branchRepository.clearCache();
        branchRepository.removeBranch(name);
    }
}
