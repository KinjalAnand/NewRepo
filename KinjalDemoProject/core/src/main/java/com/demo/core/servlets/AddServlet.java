package com.demo.core.servlets;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;


@Component(service = Servlet.class, immediate = true, property = {"sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=/bin/Add",
        "sling.servlet.extensions=html"})
public class AddServlet extends SlingSafeMethodsServlet {

    String[] marksArray;
    PrintWriter output;

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        this.output=response.getWriter();
        marksArray= request.getParameterMap().get("marks[]");
        if(marksArray!= null)
        {
            Integer sum=0;
            for(int i=0; i<marksArray.length; i++)
            {
                sum += Integer.parseInt(marksArray[i]);
            }
            output.println(sum);
        }
        else output.println("Nothing to Share!");
    }
}
