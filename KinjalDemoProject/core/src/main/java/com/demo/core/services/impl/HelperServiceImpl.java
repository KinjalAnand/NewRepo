package com.demo.core.services.impl;

import com.demo.core.services.HelperService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(immediate=true, service=HelperService.class)
public class HelperServiceImpl implements HelperService {

    int number;

    @Activate
    protected void activate()
    {
        this.number=0;
    }

    @Override
    public int getHitNumber()
    {
        return this.number;
    }

    @Override
    public void setHitNumber(int Number) {
        this.number=Number;
    }
}
