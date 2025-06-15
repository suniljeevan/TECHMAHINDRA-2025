package org.cms.SERVICES;

import java.util.List;

import org.cms.MODEL.Student;

//@Service - Stereotype annotation
//@Component - 
//@Repository
//@bean
 interface StudentService  {
	// java can upgrade
	public int insertStudent(Student s);
	public int updateStudent(Student s, String id);
	public int deleteStudent(String s);
	public List<Student> fetchAll();
	public Student fetchOneStudent(String s);

}
 
 
 
 
 
 
