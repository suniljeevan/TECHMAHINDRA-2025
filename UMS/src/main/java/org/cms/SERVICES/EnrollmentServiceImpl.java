package org.cms.SERVICES;

import java.util.List;

import org.cms.MODEL.Course;
import org.cms.MODEL.Enrollment;
import org.cms.REPOSITORY.CourseRepository;
import org.cms.REPOSITORY.EnrollmentRepository;

public class EnrollmentServiceImpl {
	//Explicit Wiring/ Inject
			private EnrollmentRepository repository;
			
			public EnrollmentServiceImpl() {
				super();
			}
			public EnrollmentServiceImpl(EnrollmentRepository repository) {
				this.repository = repository;
			}
		
		
	public int insertEnrollment(Enrollment s) {
		return repository.insertEnrollment(s);
	}
	public int updateEnrollment(Enrollment s, String id,String cid) {
		return repository.updateEnrollment(s, id);
	}
	public int deleteEnrollment(String id, String cid) {
		return repository.deleteEnrollment(id);
	}
	public List<Enrollment> fetchAll(){
		return repository.fetchAllEnrollments();
	}
	public Enrollment fetchOneEnrollment(String id, String cid) {
		return null;
	}
}
