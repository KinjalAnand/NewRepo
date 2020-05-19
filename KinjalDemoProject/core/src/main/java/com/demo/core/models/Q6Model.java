package com.demo.core.models;

import com.demo.core.services.Q6Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Q6Model {

    String key;
    String id;
    String path;

    @OSGiService
    Q6Service q6Service;

    @SlingObject
    Resource resource;

    @PostConstruct
    public void init()
    {
        path= q6Service.getPath();
        Resource res= resource.getResourceResolver().getResource(path);
        if(res!=null)
        {
            if(res.getValueMap().get("clientId",String.class)!= null)
            {
                id= res.getValueMap().get("clientId",String.class);
            }
            else
            {if(q6Service.getCId() == null)
                id= "No Id";
            else
                id= q6Service.getCId();}

            if(res.getValueMap().get("clientKey",String.class)!= null)
            {
                key= res.getValueMap().get("clientKey",String.class);
            }
            else
            {if(q6Service.getCId() == null)
                id= "No Id";
            else
            {if(q6Service.getCKey()== null)
                key= "No Key";
            else
                key= q6Service.getCKey();}
            }
        }
        else
        {
            if(q6Service.getCKey()== null)
                key= "No Key";
            else
                key= q6Service.getCKey();
            if(q6Service.getCId() == null)
                id= "No Id";
            else
                id= q6Service.getCId();
        }
    }

    public String getKey()
    {
        return key;
    }

    public String getId()
    {
        return id;
    }


}
