package com.nisum.college.bean.dto;

import com.nisum.college.bean.BaseBean;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "course")
public class CourseDO extends BaseBean {

    private static final long serialVersionUID = 20000002l;

    @Indexed(unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CourseDO{" +
                "name='" + name + '\'' +
                '}';
    }
}
