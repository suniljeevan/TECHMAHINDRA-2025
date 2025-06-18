package CLIENT;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

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
		//filter students from bengaluru
		List<Student> templist=
			studentlist.stream().filter(x
					->x.getAddress().equalsIgnoreCase("bengaluru")
					
					).collect(Collectors.toList())	;
		System.out.println(templist);
		//print all students fetched
		studentlist.stream().forEach(x->System.out.println(x));
		System.out.println("Method reference");
		studentlist.stream().forEach(System.out::println);
		//find the lowest id
		System.out.println("lowest id");
		Student student_lowestid=studentlist.stream()
				.min((x,y)->
		     x.getSid().compareTo(y.getSid())
				).get();
       System.out.println(student_lowestid);		
	boolean result=	studentlist.stream().allMatch(
				x->x.getAddress().equalsIgnoreCase("bengaluru")
				);
	System.out.println(result);
	//
	studentlist.stream().sorted().forEach(
			System.out::println
			);
	System.out.println("Sorted by address");
	Collections.sort(studentlist,
			(x,y)->{
				return x.getAddress().compareTo(y.getAddress());
			}
			);
	System.out.println(studentlist);	
		
	}

}
