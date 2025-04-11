package org.cms.REPOSITORY;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cms.DATASOURCES.MySqlDBSource;
import org.cms.MODEL.Enrollment;
import org.cms.MODEL.Student;

public class EnrollmentRepository {
	// get the datasourse object
		//Injecting/wiring an object 
		// @autowire
		private MySqlDBSource ds;
		//alt shift s

		public EnrollmentRepository(MySqlDBSource ds) {
			super();
			this.ds = ds;
		}

		public MySqlDBSource getDs() {
			return ds;
		}
		public void setDs(MySqlDBSource ds) {
			this.ds = ds;
		}
		public int insertEnrollment(Enrollment s) {
			int r=0;
			try {
			String sql="insert into Enrollment values(?,?)";
			
			PreparedStatement ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1, s.getStudid());
			ps.setString(2, s.getCourseid());
			r=ps.executeUpdate();
			}catch(Exception e) {}
			return r;
		}
		public List<Enrollment> fetchAllEnrollments() {
			List<Enrollment> list=new ArrayList<Enrollment>();
			Enrollment s;
			try {
			String sql="select * from Enrollment";	
			Statement st=ds.getConnection().createStatement();
			ResultSet set=st.executeQuery(sql);
			while(set.next()) {
				String sid=set.getString(1);
				String cid=set.getString(2);
				s=new Enrollment(sid,cid);
				list.add(s);
			}
			}catch(Exception e) {}
			return list;
		}
		public int updateEnrollment(Enrollment s, String id) {
			int r=0;
			try {
			String sql="update Enrollment set courseid=?,  where studid=?";	
			PreparedStatement ps=ds.getConnection().prepareStatement(sql);
			ps.setString(1, s.getCourseid());
			ps.setString(2, s.getStudid());
			r=ps.executeUpdate();
			}catch(Exception e) {}
			return r;
		}
		public int deleteEnrollment(String id) {
			int r=0;
			try {
				String sql="delete from Enrollment where studid=?";	
				PreparedStatement ps=ds.getConnection().prepareStatement(sql);
				ps.setString(1,id);
				r=ps.executeUpdate();
			}catch(Exception e) {}
			return r;
		}

	}