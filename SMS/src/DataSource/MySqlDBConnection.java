package DataSource;
import java.sql.*;
public class MySqlDBConnection {
	private Connection connection;
	public MySqlDBConnection() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/techtest","root","root");
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	

}
