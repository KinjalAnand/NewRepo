package com.demo.core.services.impl;

import com.demo.core.services.CompareService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

@Component(immediate=true, service= CompareService.class)
public class CompareServiceImpl implements CompareService {
    private TreeMap<Integer, String> Students;
    Integer marks;

    @Activate
    public void init()
    {
        Students = new TreeMap<Integer, String>();
    }


    @Override
    public Map<Integer, String> getValue() {
        NavigableMap nmap = Students.descendingMap();
        return nmap;
    }

    @Override
    public void setValue(String name, String marks) {
        this.marks = Integer.parseInt(marks);
        Students.put(this.marks,name);
    }
}
