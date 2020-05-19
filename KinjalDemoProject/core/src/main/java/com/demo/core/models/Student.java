package com.demo.core.models;

import java.util.Comparator;

public class Student implements Comparator<Student> {

    String name;
    Integer marks;
    Boolean pass;

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compare(Student arg0, Student arg1) {
       if(arg0.getMarks()>arg1.getMarks())
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
}
