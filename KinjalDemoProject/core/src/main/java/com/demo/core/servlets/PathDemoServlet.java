package com.demo.core.servlets;

import com.demo.core.services.HelperService;
import com.demo.core.services.PathService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = Servlet.class, immediate = true, property = {"sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=/bin/HitNumber",
        "sling.servlet.selectors=count", "sling.servlet.selectors=show",
        "sling.servlet.extensions=html"})
public class PathDemoServlet extends SlingSafeMethodsServlet {

    @Reference
    PathService pathService;

    PrintWriter output;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String path = request.getParameter("path");
        this.output=response.getWriter();
        String selector= request.getRequestPathInfo().getSelectorString();
        if(selector != null)
        {
            switch (selector)
            {
                case "count" : {
                    pathService.SetPathCount(path);
                    response.sendRedirect(path);
                    break;
                }

                case "show" : {
                    Integer count;
                    count= pathService.getPathCount(path);
                    output.println("Hit Count for path= "+path+" is "+count);
                    break;
                }

                default: output.println("Please put the right selector (count/show)!"); break;
            }
        }
        else
        {
            output.println("Please put a (count/show) selector!");
        }

    }

}
