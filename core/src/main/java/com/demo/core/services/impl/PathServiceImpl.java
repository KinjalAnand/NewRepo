package com.demo.core.services.impl;

import com.demo.core.services.PathService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.HashMap;
import java.util.Map;

@Component(immediate=true, service= PathService.class)
public class PathServiceImpl implements PathService {

    private Map<String, Integer> pathMap;
    String path;

    @Activate
    public void init()
    {
        pathMap = new HashMap<>();
    }

    @Override
    public Integer getPathCount(String path) {
        if(pathMap.get(path)== null)
        {
            return 0;
        }
        else
        {
           return pathMap.get(path);
        }
    }

    @Override
    public void SetPathCount(String path) {
        if(pathMap.get(path)== null)
        {
            pathMap.put(path,1);
        }
        else
        {
            pathMap.put(path,pathMap.get(path)+1);
        }
    }
}
