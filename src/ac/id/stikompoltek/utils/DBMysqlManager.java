package ac.id.stikompoltek.utils;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBMysqlManager {
	
	private MysqlDataSource mysqlDataSource;
	
	public DBMysqlManager(String server, int port, String dbName, String username, String password) {
		this.mysqlDataSource = new MysqlDataSource();
		this.mysqlDataSource.setServerName(server);
		this.mysqlDataSource.setUser(username);
		this.mysqlDataSource.setDatabaseName(dbName);
		this.mysqlDataSource.setPortNumber(port);
		this.mysqlDataSource.setPassword(password);
	}
	
	public MysqlDataSource getMysqlDataSource() {
		return this.mysqlDataSource;
	}
	
	public Connection getMysqlConnection() {
		try {
			return (Connection) this.mysqlDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
