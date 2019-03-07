package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import oracle.jdbc.driver.OracleDriver;

public class ConnectionFactory {
	private static Logger log = Logger.getLogger(ConnectionFactory.class);
	private static ConnectionFactory cf = new ConnectionFactory();
	
	private ConnectionFactory() {
		super();
	}
	
	public static ConnectionFactory getInstance() {
		return cf;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		System.out.println("in connection factory getConnection");
		Properties prop = new Properties();
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			
//			prop.load(new FileReader("C:\\Users\\maraz\\Documents\\Revature\\Zachary-Jaitee-Project-1\\Project 1 STS Workspace\\Zachary-Jaitee-Project1\\src\\main\\resources\\application.properties"));
			prop.load(new FileReader("C:\\Users\\Jaitee\\Rrepos\\Zachary-Jaitee-Project-1\\Project 1 STS Workspace\\Zachary-Jaitee-Project1\\src\\main\\resources\\application.properties"));
			
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pw"));
		} catch (SQLException sqle) {
			log.error(sqle.getMessage());
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			log.error(ioe.getMessage());
		}
		if(conn == null) {
			System.out.println("conn is null");
		}
		return conn;
	}
}
