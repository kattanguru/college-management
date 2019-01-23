package com.nisum.college.bean.bo;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.List;

@JsonRootName("branches")
public class BranchesBO implements Serializable {

    private static final long serialVersionUID = 10000007l;

    private List<BranchBO> branches;

    public List<BranchBO> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchBO> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "BranchesBO{" +
                "branches=" + branches +
                '}';
    }
}
