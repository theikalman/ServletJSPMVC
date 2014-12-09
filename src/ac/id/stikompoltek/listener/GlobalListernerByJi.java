package ac.id.stikompoltek.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ServletContextListener
 *
 */
@WebListener
public class GlobalListernerByJi implements javax.servlet.ServletContextListener {

    /**
     * Default constructor. 
     */
    public GlobalListernerByJi() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see GlobalListernerByJi#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    	System.out.println("Context destroyed");
    }

	/**
     * @see GlobalListernerByJi#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Context initialized");
    }
	
}
