package com.nisum.college.bean;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class BaseBean implements Serializable {

    private static final long serialVersionUID = 10000001l;

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "id='" + id + '\'' +
                '}';
    }
}
