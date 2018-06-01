import org.apache.wicket.protocol.http.WebApplication;

import grails.util.*
import org.apache.wicket.Application
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.wicket.RuntimeConfigurationType
import org.apache.wicket.request.mapper.parameter.UrlPathPageParametersEncoder

import org.wicketstuff.rest.Index
import org.apache.wicket.request.resource.ResourceReference
import org.wicketstuff.rest.resource.PersonsRestResource
import org.apache.wicket.request.resource.IResource


public class WicketApplication extends WebApplication {
    
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    Class getHomePage() { Index.class }
    //Class getHomePage() { HomePage.class }

    /**
     * Configures Grails' application context to be used for @SpringBean injection
     */
    protected void init() {
        super.init()

        mountResource("/personsmanager", new ResourceReference("restReference") {
            PersonsRestResource resource = new PersonsRestResource();
            @Override
            public IResource getResource() {
                return resource;
            }

        });

        //addComponentInstantiationListener(new SpringComponentInjector(this, ApplicationHolder.getApplication().getMainContext(), false));
        getComponentInstantiationListeners().add(new SpringComponentInjector(this)); //replaces above Spring injection in wicket 1.4

        mountPage("/home2",HomePage.class)

    }

    /**
     * If we're running in Grails development environment use Wicket development environment
     */
    //replaces getConfigurationType() commented out below in wicket 1.4
    public RuntimeConfigurationType getConfigurationType() {
        if(GrailsUtil.isDevelopmentEnv()) {
            return RuntimeConfigurationType.DEVELOPMENT
        }
        return RuntimeConfigurationType.DEPLOYMENT
    }

    /*public String getConfigurationType() {
        if (GrailsUtil.isDevelopmentEnv()) {
            return Application.DEVELOPMENT
        }
        return Application.DEPLOYMENT
    } */


}