package com.demo.core.services.impl;


import com.google.common.collect.Maps;
import com.demo.core.services.Q6Service;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import java.util.Map;

@Component(immediate = true, service = Q6Service.class)
@Designate(ocd = Q6ServiceImpl.clientInfoConfig.class)
public class Q6ServiceImpl implements Q6Service {

    String clientId;
    String clientKey;
    String path;

    @Reference
    ResourceResolverFactory resolverFactory;

    @Override
    public String getCId() {
        return this.clientId;
    }

    @Override
    public String getCKey() {
        return this.clientKey;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Activate
    @Modified
    protected void modified(clientInfoConfig config)
    {
        this.clientId= config.clientId();
        this.clientKey= config.clientKey();
        this.path= config.path();
        newNode();
    }

    @ObjectClassDefinition(name = "Client-Info-Configuration")
    public @interface clientInfoConfig
    {
        @AttributeDefinition(name= "Client Id")
        String clientId();
        @AttributeDefinition(name= "Client Key")
        String clientKey();
        @AttributeDefinition(name= "Path")
        String path();
    }

    protected void newNode()
    {
        try {
            String requestPath = this.path;
            Map<String, Object> properties = Maps.newHashMap();
            Map<String, Object> param = Maps.newHashMap();
            param.put(ResourceResolverFactory.SUBSERVICE,"Q6Service");
            ResourceResolver resourceResolver = resolverFactory.getServiceResourceResolver(param);
            Resource resource = ResourceUtil.getOrCreateResource(resourceResolver, requestPath, properties, "nt:unstructured", true);
            ModifiableValueMap modifiableValueMap= resource.adaptTo(ModifiableValueMap.class);
            modifiableValueMap.put("jcr:primaryType", "nt:unstructured");
            modifiableValueMap.put("clientId", this.clientId);
            modifiableValueMap.put("clientKey", this.clientKey);
            resourceResolver.commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
