package DataSource;
import java.sql.*;
public class MySqlDBConnection {
	private Connection connection;
	public MySqlDBConnection() throws Exception {
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
