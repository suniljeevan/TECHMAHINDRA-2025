package CLIENT;

import java.util.List;
import java.util.ListIterator;

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
		//int res=controller.insertStudent(s);
		//System.out.println(res+" record inserted");
		List<Student> studentlist=controller.fetchALlStudent();
		System.out.println(studentlist);
		for(Student i:studentlist) {
			System.out.println(i);
		}
		//which collection for course, student->List,Set	
		//which collection for Enrollment - Map
		System.out.println("Using Iterator");
		ListIterator itr=studentlist.listIterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		
		
	}

}
