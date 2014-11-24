package ac.id.stikompoltek.listener;

import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ac.id.stikompoltek.utils.DBMysqlManager;

public class ServContList implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.removeAttribute("mysqlConnection");
		// Debugging
		System.out.println("Koneksi mysql dihapus dari ServletContext.");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		
		String server, dbName, username, password;
		int serverPort;
		ServletContext servletContext = servletContextEvent.getServletContext();
		
		server = servletContext.getInitParameter("DBSERVER");
		serverPort = Integer.parseInt(servletContext.getInitParameter("DBSERVERPORT"));
		dbName = servletContext.getInitParameter("DBNAME");
		username = servletContext.getInitParameter("DBUSERNAME");
		password = servletContext.getInitParameter("DBPASSWORD");
		
		DBMysqlManager dbMysqlManager = new DBMysqlManager(server, serverPort, dbName, username, password);
		
		servletContext.setAttribute("mysqlConnection", dbMysqlManager.getMysqlConnection());
		
		// Debugging
		Connection connection = (Connection) servletContext.getAttribute("mysqlConnection");
		System.out.println("mysqlConnection : ServletContext = " + connection.toString());
		System.out.println("Koneksi mysql distore di ServletContext.");
		
	}

}
