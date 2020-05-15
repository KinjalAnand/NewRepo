package com.demo.core.models;

import java.util.Comparator;

public class Student implements Comparator<Student> {

    String name;
    int marks;

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compare(Student arg0, Student arg1) {
        if(arg0.getMarks()== arg1.getMarks())
        {
            return 0;
        }
        else if(arg0.getMarks()>arg1.getMarks())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
