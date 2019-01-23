package com.nisum.college.bean.dto;

import com.nisum.college.bean.BaseBean;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "branch")
public class BranchDO extends BaseBean {

    private static final long serialVersionUID = 20000003l;

    @Indexed(unique = true)
    private String name;
    private String course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "BranchDO{" +
                "name='" + name + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
