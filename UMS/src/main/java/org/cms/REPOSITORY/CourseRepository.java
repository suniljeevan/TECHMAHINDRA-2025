package org.cms.REPOSITORY;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cms.DATASOURCES.MySqlDBSource;
import org.cms.MODEL.Course;
import org.cms.MODEL.Student;

public class CourseRepository {
	// get the datasourse object
		//Injecting/wiring an object 
		// @autowire
		private MySqlDBSource ds;
		//alt shift s

		public CourseRepository(MySqlDBSource ds) {
			super();
			this.ds = ds;
		}

		public MySqlDBSource getDs() {
			return ds;
		}
		public void setDs(MySqlDBSource ds) {
			this.ds = ds;
		}
		public int insertCourse(Course s) {
			int r=0;
			try {
			String sql="insert into Course values(?,?,?,?)";
			
			PreparedStatement ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1, s.getCourseid());
			ps.setString(2, s.getCoursename());
			ps.setInt(3,s.getCredit());
			ps.setString(4, s.getCtype());
			 r=ps.executeUpdate();
			
			}catch(Exception e) {}
			return r;
		}
		public List<Course> fetchAllCourses() {
			List<Course> list=new ArrayList<Course>();
			Course s;
			try {
			String sql="select * from Course";	
			Statement st=ds.getConnection().createStatement();
			ResultSet set=st.executeQuery(sql);
			while(set.next()) {
				String id=set.getString(1);
				String name=set.getString(2);
				int credit=set.getInt(3);
				String type=set.getString(4);
				
				s=new Course(id,name,credit,type);
				list.add(s);
			}
			}catch(Exception e) {}
			return list;
		}
		public int updateCourse(Course s, String id) {
			int r=0;
			try {
			String sql="update Course set coursename=?, credit=?, ctype=? where sid=?";	
			PreparedStatement ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1, s.getCoursename());
			ps.setInt(2, s.getCredit());
			ps.setString(3, s.getCtype());
			ps.setString(4, s.getCourseid());
			r=ps.executeUpdate();
			}catch(Exception e) {}
			return r;
		}
		public int deleteCourse(String id) {
			int r=0;
			try {
				String sql="delete from Course where courseid=?";	
				PreparedStatement ps=ds.getConnection().prepareStatement(sql);
				ps.setString(1,id);
				r=ps.executeUpdate();
			}catch(Exception e) {}
			return r;
		}

	}

