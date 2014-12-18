package ac.id.stikompoltek.listener;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ac.id.stikompoltek.utils.DBMysqlManager;

/**
 * Application Lifecycle Listener implementation class ServContListener
 *
 */
@WebListener
public class ServContListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent contextEvent) {
    	
    	// Remove mysqlConnection from ServletContext scope when application is destroyed
    	contextEvent.getServletContext().removeAttribute("mysqlConnection");
    	
    	// For debugging purpose only
    	System.out.println("=================================================");
        System.out.println("Servlet context destroyed");
        System.out.println("=================================================");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent contextEvent) {
    	try {
    		// Open and get configuration file
			FileReader dbConfigPropertiesFile = new FileReader("/home/aji/Git/javaprojects/ServletJSPMVC/src/ac/id/stikompoltek/resources/dbconf.properties");
			Properties dbConfigProperties = new Properties();
			dbConfigProperties.load(dbConfigPropertiesFile);
			
			String dbServer, dbName, dbUsername, dbPassword;
			int dbPort;
			
			dbServer = dbConfigProperties.getProperty("database_host");
			dbPort = Integer.parseInt(dbConfigProperties.getProperty("database_port"));
			dbName = dbConfigProperties.getProperty("database_name");
			dbUsername = dbConfigProperties.getProperty("database_username");
			dbPassword = dbConfigProperties.getProperty("database_password");
			
			// Make a connection
			DBMysqlManager mySqlManager = new DBMysqlManager(dbServer, dbPort, dbName, dbUsername, dbPassword);
			
			// Store connection to ServletContext scope
			contextEvent.getServletContext().setAttribute("mysqlConnection", mySqlManager.getMysqlConnection());
			
			// Close configuration file
			dbConfigPropertiesFile.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	// for debugging purpose only
    	System.out.println("=================================================");
    	System.out.println("Servlet context listener instantiated");
    	System.out.println("=================================================");
    }
	
}
