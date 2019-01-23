package com.nisum.college.service;

import com.nisum.college.bean.bo.BranchBO;

import java.util.List;

public interface BranchService {

    List<BranchBO> getBranches(String expand);

    BranchBO getBranchByName(String name, String expand);

    List<BranchBO> getBranchByCourse(String name, String expand);

    BranchBO addBranch(BranchBO branch);

    BranchBO updateBranch(BranchBO branch);

    void removeBranch(String name);
}
