package CLIENT;

import Controller.StudentController;
import DataSource.MySqlDBConnection;
import MODEL.Student;
import Repository.StudentRepository;
import SERVICE.StudentServiceImpl;

//JVM will work for client and for server
//Spring
public class Test {
	public static void main(String[] args) throws Exception{
		MySqlDBConnection connection=new MySqlDBConnection();
		Student s=new Student("1009","amith","Amith@gmail.com","Hyderabad",2021);
		StudentRepository repository=new StudentRepository(connection);
		StudentServiceImpl service=new StudentServiceImpl(repository);
		StudentController controller=new StudentController(service);
		int res=controller.insertStudent(s);
		System.out.println(res+" record inserted");
	}

}
