package com.demo.core.models;


import com.demo.core.services.CompareService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.util.Iterator;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DataModel {

    @SlingObject
    Resource resource;

    String SName;
    String Marks;

    @OSGiService
    CompareService compareService;

    @PostConstruct
    public void init()
    {
        Resource res= resource.getChild("multi");
        Iterator it = res.listChildren();
        while(it.hasNext())
        {
            Resource item = (Resource) it.next();

            if(item.getValueMap().get("SName") == null)
            {
                SName = "John Doe";
            }
            else
            {
                SName= (String) item.getValueMap().get("SName");
            }
            if(item.getValueMap().get("Marks") == null)
            {
                Marks="0";
            }
            else
            {
                Marks = (String) item.getValueMap().get("Marks");
            }

            compareService.setValue(SName,Marks);
        }

    }

    public String getName()
    {
        return SName;
    }

    public String getMarks()
    {
        return Marks;
    }

}
