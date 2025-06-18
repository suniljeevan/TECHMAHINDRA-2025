package org.cms.DATASOURCES;
import java.sql.*;
public class MySqlDBSource {
	private Connection connection;
	public MySqlDBSource() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver Loaded");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/techtest","root","root");
		System.out.println("Connection Established");
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	

}
