package com.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Demo1Model {
    @SlingObject
    Resource resource;

    @Inject
    String[] data;

    String[] name;

    @PostConstruct
    public void init()
    {
        int n= data.length;
        name= new String[n];

        for(int i=0;i<n;i++) {
            if(resource.getResourceResolver().getResource(data[i])!= null) {
                if (resource.getResourceResolver().getResource(data[i]).getChild("jcr:content") != null) {
                    name[i] = resource.getResourceResolver().getResource(data[i]).getChild("jcr:content").getValueMap().get("jcr:title", String.class);
                }
            }
        }
    }

    public String[] getData()
    {
       return data;
    }

    public String[] getName() {
        return name;
    }
}

