package com.nisum.college.repository;

import com.nisum.college.bean.dto.BranchDO;

import java.util.List;

public interface BranchRepository {

    List<BranchDO> getBranches();

    BranchDO getBranchByName(String name);

    List<BranchDO> getBranchesByCourse(String name);

    BranchDO addBranch(BranchDO branch);

    BranchDO updateBranch(BranchDO branch);

    void removeBranch(String name);

    void clearCache();
}
