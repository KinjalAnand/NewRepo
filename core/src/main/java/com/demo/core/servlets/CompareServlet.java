package com.demo.core.servlets;

import com.demo.core.services.CompareService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Component(service = Servlet.class, immediate = true, property = {"sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=/bin/Compare",
        "sling.servlet.extensions=html"})
public class CompareServlet extends SlingSafeMethodsServlet {

    @Reference
    CompareService compareService;

    PrintWriter output;
    private Map<Integer,String> Students;

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        this.output=response.getWriter();
        int i=0;
        output.println("Top 3 Students");
        Students = compareService.getValue();
        if(Students!= null)
        {
            int n= Students.size();
            for (Map.Entry<Integer, String> en : Students.entrySet()) {
                if(i<3)
                {
                    output.print("Name = " + en.getValue() +
                            ", Marks = " + en.getKey());
                    switch(i)
                    {
                        case 0: output.println(" First");
                                break;
                        case 1: output.println(" Second");
                                break;
                        case 2: output.println(" Third");
                                break;
                        default: output.println(" Default");
                    }
                    i++;
                }
                else break;
            }
        }
        else output.println("Nothing to Share!");
    }
}
