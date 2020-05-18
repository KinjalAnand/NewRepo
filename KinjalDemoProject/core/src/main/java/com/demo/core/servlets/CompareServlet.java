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
import java.util.Set;

@Component(service = Servlet.class, immediate = true, property = {"sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=/bin/Compare",
        "sling.servlet.extensions=html"})
public class CompareServlet extends SlingSafeMethodsServlet {

    PrintWriter output;
    private String[] studentNames;
    private String[] studentMarks;

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        this.output=response.getWriter();
        studentNames= request.getParameterMap().get("studentName[]");
        studentMarks= request.getParameterMap().get("studentMarks[]");
        int i=0;
        if(studentMarks!= null && studentNames!=null)
        {
            //int n= Students.size();
            for (int j=0; j<studentNames.length; j++) {
                if(i<3)
                {
                    output.print("Name = " + studentNames[j] +
                            ", Marks = " + studentMarks[j]);
                    switch(i)
                    {
                        case 0: output.print(", Position = First\n");
                                break;
                        case 1: output.print(", Position = Second\n");
                                break;
                        case 2: output.print(", Position = Third\n");
                                break;
                        default: output.print(" Default\n");
                    }
                    i++;
                }
                else break;
            }
        }
        else output.println("Nothing to Share!");
    }
}
