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

    SortedSet<Student> ss = new TreeSet<>((o1, o2) -> o1.compare(o1,o2));

    @PostConstruct
    public void init()
    {
        Iterator<Resource> iterator = resource.getChild("multi").listChildren();
        while(iterator.hasNext())
        {
            Student student = new Student();
           Resource res= iterator.next();
           name= res.getValueMap().get("SName",String.class);
            student.setName(name);
           marks= res.getValueMap().get("Marks",String.class);
            student.setMarks(Integer.parseInt(marks));
           if(name== null)
               student.setName("Others");
           if(marks== null)
               student.setMarks(0);
           if(student.getMarks()>40)
               student.setPass(true);
           else
               student.setPass(false);
           ss.add(student);
        }
    }

    public SortedSet<Student> getData()
    {
        return ss;
    }

}


