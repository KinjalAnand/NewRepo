package com.demo.core.servlets;

import com.demo.core.models.Q4Model;
import com.demo.core.models.Student;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.SortedSet;

@Component(service = Servlet.class, immediate = true,
property = {
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.resourceTypes=" + "KinjalDemoProject/components/content/Test4",
        "sling.servlet.selectors=testq4",
        "sling.servlet.extensions=html"
})
public class CompareResourceServlet extends SlingAllMethodsServlet {

    PrintWriter output;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Q4Model q4Model= request.getResource().adaptTo(Q4Model.class);
        output= response.getWriter();
        //output.println("Testing 1...2...3");
        if(q4Model != null)
        {
            SortedSet<Student> studentSortedSet = q4Model.getData();
            int i=0;
            int n= studentSortedSet.size();
            if(n==1)
                output.print("Top 1 Student\n");
            else if(n<3)
                output.print("Top "+n+" Students\n");
            else
                output.print("Top 3 Students\n");
            Iterator value = studentSortedSet.iterator();
            while (value.hasNext()) {
                Student s = (Student) value.next();
                if(i<3)
                {
                    output.print("Name = " + s.getName() + ", Marks = " + s.getMarks() );
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
    }
}
