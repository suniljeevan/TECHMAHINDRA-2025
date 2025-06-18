package org.cms.SERVICES;

import java.util.List;
import org.cms.MODEL.Enrollment;

interface EnrollmentService {	
		public int insertEnrollment(Enrollment s);
		public int updateEnrollment(Enrollment s, String id,String cid);
		public int deleteEnrollment(String id, String cid);
		public List<Enrollment> fetchAll();
		public Enrollment fetchOneEnrollment(String id, String cid);
}
