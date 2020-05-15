package com.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Q3Model {

    @SlingObject
    Resource resource;

    String name;
    String marks;
    Student student = new Student();
    SortedSet<Student> ss = new TreeSet<>((o1, o2) -> o1.compare(o1,o2));

    @PostConstruct
    public void init()
    {
        Iterator iterator;
        iterator=resource.getChild("multi").listChildren();
        do
        {
           Resource res= (Resource) iterator;
           name= res.getValueMap().get("SName",String.class);
            student.name= name;
           marks= res.getValueMap().get("Marks",String.class);
            student.marks= Integer.parseInt(marks);
           if(name== null)
               student.name= "John Doe";
           if(marks== null)
               student.marks= 0;
           ss.add(student);
           iterator.next();

        }while(iterator.hasNext());
    }



}


