package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.*;
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
		int r=0;
		try {
		String sql="insert into student values(?,?,?,?,?)";
		
		PreparedStatement ps=connection.getConnection().prepareStatement(sql);
		ps.setString(1, s.getSid());
		ps.setString(2, s.getSname());
		ps.setString(3,s.getEmail());
		ps.setString(4, s.getAddress());
		ps.setInt(5, s.getYear());
		 r=ps.executeUpdate();
		
		}catch(Exception e) {}
		return r;
	}
	public List<Student> fetchAllStudents() {
		List<Student> list=new ArrayList<Student>();
		Student s;
		try {
		String sql="select * from student";	
		Statement st=connection.getConnection().createStatement();
		ResultSet set=st.executeQuery(sql);
		while(set.next()) {
			String id=set.getString(1);
			String name=set.getString(2);
			String email=set.getString(3);
			String address=set.getString(4);
			int year=set.getInt(5);
			s=new Student(id,name,email,address,year);
			list.add(s);
		}
		}catch(Exception e) {}
		return list;
	}
	public int updateStudent(Student s, String id) {
		int r=0;
		try {
		String sql="update student set address=?, admission_year=? where sid=?";	
		PreparedStatement ps=connection.getConnection().prepareStatement(sql);
		ps.setString(1, s.getAddress());
		ps.setInt(2, s.getYear());
		ps.setString(3, id);
		r=ps.executeUpdate();
		}catch(Exception e) {}
		return r;
	}
	public int deleteStudent(String id) {
		int r=0;
		try {
			String sql="delete from student where sid=?";	
			PreparedStatement ps=connection.getConnection().prepareStatement(sql);
			ps.setString(1,id);
			r=ps.executeUpdate();
		}catch(Exception e) {}
		return r;
	}

}




