package training.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.Self;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables=Resource.class)


public class PageModel {
	
 @Inject @Named("jcr:title") @Required
   private String title;

    public String getTitle() {
	return title;
}
    @Inject  @Named("subtitle") @Required
	private String subtitle;
    
	
    public String getSubtitle() {
		return subtitle;
	}
	
	private String formattedsstitle;

    @PostConstruct
    protected void initSubtitle() {
         this.formattedsstitle=this.title+"Hello";
    }
	public String getFormattedsstitle()
		{
		System.out.println("Hello PageModel");
			return formattedsstitle;


		}
}
