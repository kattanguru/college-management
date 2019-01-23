package com.nisum.college.repository.impl;

import com.nisum.college.bean.dto.BranchDO;
import com.nisum.college.repository.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nisum.college.bean.CollegeConstants.*;

@Repository
public class BranchRepositoryImpl implements BranchRepository {

    private static Logger logger = LoggerFactory.getLogger(BranchRepositoryImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Cacheable(value = "college")
    public List<BranchDO> getBranches() {
        List<BranchDO> branches = mongoTemplate.findAll(BranchDO.class);
        logger.debug("Branches Details from Database : {}", branches);
        return branches;
    }

    @Cacheable(value = "college", key = "#name")
    public BranchDO getBranchByName(String name) {
        BranchDO branch = mongoTemplate.findOne(new Query(Criteria.where(NAME).is(name)), BranchDO.class);
        logger.debug("Branch Details by Branch Name from Database : {}", branch);
        return branch;
    }

    @Cacheable(value = "college", key = "#name")
    public List<BranchDO> getBranchesByCourse(String name) {
        List<BranchDO> branches = mongoTemplate.find(new Query(Criteria.where(COURSE_NAME).is(name)), BranchDO.class);
        logger.debug("Branches Details by Course Name from Database : {}", branches);
        return branches;
    }

    public BranchDO addBranch(BranchDO branch) {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, ID)).limit(1);
        branch.setId(mongoTemplate.findOne(query, BranchDO.class).getId() + 1);
        mongoTemplate.insert(branch);
        logger.debug("Branch Added Successfully to Database : {}", branch);
        return branch;
    }

    public BranchDO updateBranch(BranchDO branch) {
        mongoTemplate.save(branch);
        logger.debug("Branch Updated Successfully to Database : {}", branch);
        return branch;
    }

    public void removeBranch(String name) {
        mongoTemplate.remove(new Query(Criteria.where(NAME).is(name)), BranchDO.class);
        logger.debug("Branch Removed Successfully to Database : {}", name);
    }

    @CacheEvict(value = "college", allEntries = true)
    public void clearCache() {
        logger.debug("Cache (college) Cleared");
    }
}
