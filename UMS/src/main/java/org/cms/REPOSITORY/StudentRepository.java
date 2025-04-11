package org.cms.REPOSITORY;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.*;
import org.cms.DATASOURCES.MySqlDBSource;
import org.cms.MODEL.Student;

// Datasource   --  Configuration
// JDBC calls 

// this violates SOLID principle
public class StudentRepository {
	// get the datasourse object
	//Injecting/wiring an object 
	// @autowire
	private MySqlDBSource ds;
	//alt shift s

	public StudentRepository(MySqlDBSource ds) {
		super();
		this.ds = ds;
	}

	public MySqlDBSource getDs() {
		return ds;
	}
	public void setDs(MySqlDBSource ds) {
		this.ds = ds;
	}
	public int insertStudent(Student s) {
		int r=0;
		try {
		String sql="insert into student values(?,?,?,?,?)";
		
		PreparedStatement ps=ds.getConnection().prepareStatement(sql);
		ps.setString(1, s.getSid());
		ps.setString(2, s.getSname());
		ps.setString(3,s.getEmail());
		ps.setString(4, s.getAddress());
		ps.setInt(5, s.getYear());
		 r=ps.executeUpdate();
		
		}catch(Exception e) {
			System.out.println(e);
		}
		return r;
	}
	public List<Student> fetchAllStudents() {
		List<Student> list=new ArrayList<Student>();
		Student s;
		try {
		String sql="select * from student";	
		Statement st=ds.getConnection().createStatement();
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
		PreparedStatement ps=ds.getConnection().prepareStatement(sql);
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
			PreparedStatement ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1,id);
			r=ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(e);
		}
		return r;
	}
	public Student fetchOneStudent(String id) {
		Student s=new Student();
		try {
			String sql="select * from student where sid=?";	
			PreparedStatement ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1,id);
			ResultSet r=ps.executeQuery();
			while(r.next()) {
			s.setSid(r.getString(1));
			s.setSname(r.getString(2));
			s.setEmail(r.getString(3));
			s.setAddress(r.getString(4));
			s.setYear(r.getInt(5));
			}
			System.out.print(s);
		}catch(Exception e) {
			System.out.println(e);
		}
		return s;
	}

}




