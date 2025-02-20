package Repository;

import java.util.Objects;

import DataSource.MySqlDBConnection;
import MODEL.Student;

// Datasource   --  Configuration
// JDBC calls 

// this violates SOLID principle
public class StudentRepository {
	// get the datasourse object
	//Injecting/wiring an object 
	// @autowire
	private MySqlDBConnection connection;
	//alt shift s

	public StudentRepository(MySqlDBConnection connection) {
		super();
		this.connection = connection;
	}

	public MySqlDBConnection getConnection() {
		return connection;
	}
	public void setConnection(MySqlDBConnection connection) {
		this.connection = connection;
	}
	public int insertStudent(Student s) {
		
		return 0;
	}
	

}




