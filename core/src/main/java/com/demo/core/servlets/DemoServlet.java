package com.demo.core.servlets;

import com.demo.core.services.HelperService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.annotation.Nonnull;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, immediate = true, property = {"sling.servlet.methods=" + HttpConstants.METHOD_GET,
                        "sling.servlet.resourceTypes=" + "sling/servlet/default",
                        "sling.servlet.selectors=test",
                        "sling.servlet.extensions=abc"})
public class DemoServlet extends SlingSafeMethodsServlet {

    @Reference
    HelperService helperService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        int num= helperService.getHitNumber();
        num++;
        helperService.setHitNumber(num);
        response.getWriter().println("Hit Count is = "+num);
    }
}
