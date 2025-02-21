package CLIENT;

import Controller.StudentController;
import DataSource.MySqlDBConnection;
import MODEL.Student;
import Repository.StudentRepository;
import SERVICE.StudentServiceImpl;
public class Test {
	public static void main(String[] args) throws Exception {
		Student s=new Student("2001","heaseebudeen","h@gmail.com","Bengaluru",2021);
		MySqlDBConnection ds=new MySqlDBConnection();
		StudentRepository dao=new StudentRepository(ds);
		StudentServiceImpl service=new StudentServiceImpl(dao);
		StudentController c=new StudentController(service);
		int r=c.insertStudent(s);
		System.out.println("inserted");
	}

}
