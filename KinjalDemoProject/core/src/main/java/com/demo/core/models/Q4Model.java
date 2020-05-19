package com.demo.core.models;


import com.demo.core.services.CompareService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Q4Model {

    @SlingObject
    Resource resource;

    String name;
    String marks;

    SortedSet<Student> studentSortedSet = new TreeSet<>((o1, o2) -> o1.compare(o1, o2));

    @PostConstruct
    public void init() {
        if(resource.getChild("multi")!= null)
        {
            Iterator<Resource> iterator = resource.getChild("multi").listChildren();
            while (iterator.hasNext()) {
                Student student = new Student();
                Resource res = iterator.next();
                name = res.getValueMap().get("SName", String.class);
                student.setName(name);
                marks = res.getValueMap().get("Marks", String.class);
                if (marks != null) {
                    student.setMarks(Integer.parseInt(marks));
                }
//                if (name == null)
//                    student.setName("Others");
                if (marks == null)
                    student.setMarks(0);
                studentSortedSet.add(student);
            }
        }
    }

    public SortedSet<Student> getData() {
        return studentSortedSet;
    }
}
