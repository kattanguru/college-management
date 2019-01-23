package com.nisum.college.bean.bo;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.nisum.college.bean.BaseBean;

import java.util.List;

@JsonRootName("course")
public class CourseBO extends BaseBean {

    private static final long serialVersionUID = 10000004l;

    private String name;
    private List<BranchBO> branches;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BranchBO> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchBO> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "CourseBO{" +
                "name='" + name + '\'' +
                ", branches=" + branches +
                '}';
    }
}
