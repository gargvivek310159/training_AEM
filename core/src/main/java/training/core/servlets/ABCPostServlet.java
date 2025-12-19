package training.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

import com.adobe.granite.crypto.CryptoException;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths="+ "/bin/formdata"
       
})
public class ABCPostServlet extends SlingAllMethodsServlet{ 

	private static final long serialVersionUid = 1L;
@Override
protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {  
    java.util.logging.Logger.getLogger(getServletName()+"ABCServelet");        

    String fname;
    String lname;
    String osgiService="";
    System.out.println("Inside the servlet");

    try {
    	System.out.println("Inside the try");
    	fname=request.getParameter("firstName");
    	
    	lname=request.getParameter("lastName");
    	JSONObject obj=new JSONObject();
       // obj.put("id",id);
        obj.put("firstname",fname);
        obj.put("lastname",lname);
    	System.out.println(fname+"&&&&"+lname);
    	 String jsonData = obj.toString();
    	 response.setCharacterEncoding("UTF-8");
    	 response.setContentType("application/json");

         
         //Return the JSON formatted data
    	 response.getWriter().write(jsonData);
    	    } catch (Exception e) {
        e.printStackTrace();
    } finally {
       //TODO
    }         
}
}

/** Wrapper class to always return GET for AEM to process the request/response as GET. 
*/
