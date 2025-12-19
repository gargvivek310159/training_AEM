package training.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet to get Content Fragments based on tags authored.
 * Call the servlet using below url
 * localhost:4502/bin/querybuilder?queryparam=/content/we-retail/ca/en/experience
 */
@Component(service = Servlet.class, property = { "sling.servlet.methods=" + "GET",
		"sling.servlet.paths=" + "/bin/querybuilder", "sling.servlet.extensions=" + "json" })
public class Querybuilder extends SlingAllMethodsServlet {

	/** The generated Serial Version UID */
	private static final long serialVersionUID = 1740240983848618567L;

	/** The Logger */
	private static final Logger LOG = LoggerFactory.getLogger(Querybuilder.class);

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			String queryparam=request.getParameter("queryparam");
			// Create the query
			String query = createQuery(queryparam, response);
			if (query == null) {
				return;
			}
		
			ResourceResolver resolver = request.getResourceResolver();
			Iterator<Resource> results = resolver.findResources(query, "JCR-SQL2");
			PrintWriter out = response.getWriter();
			// Process the results and add them to the Json Array
			while (results.hasNext()) {
				Resource result = results.next();
				response.setContentType("json");
				
				response.setHeader("Cache-Control", "max-age=0");
				// Removing the ending Newline character and carriage return characters
				out.println(result.getPath().toString());
				
				}
			out.flush();
			out.close();
		
		} catch (Exception e) {
			LOG.error("Exception in AppCFServlet " + e.getMessage());
		}
	}

	
	private String createQuery(String param, SlingHttpServletResponse response) throws IOException {
			
		StringBuilder query = new StringBuilder(
				"SELECT * FROM [dam:Asset] WHERE ISDESCENDANTNODE (["+param+"])");
		         
		
	
		LOG.info("Query: " + query.toString());
		return query.toString();
	}

	
}
