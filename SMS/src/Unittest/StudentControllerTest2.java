package Unittest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import Controller.StudentController;
import DataSource.MySqlDBConnection;
import MODEL.Student;
import Repository.StudentRepository;
import SERVICE.StudentServiceImpl;

public class StudentControllerTest2 {

    private MySqlDBConnection connection;
	private StudentRepository repository;
    private StudentServiceImpl studentService;
    private StudentController studentController;

    @BeforeEach
    public void setup() throws Exception {
    	connection=new MySqlDBConnection();
        repository = new StudentRepository(connection);  // Make sure this constructor doesn't have dependencies
        studentService = new StudentServiceImpl(repository);
        studentController = new StudentController(studentService);
    }

    @Test
    public void testInsertStudent() {
    	Student s=new Student("3000","amith","Amith@gmail.com","Hyderabad",2021);
    	assertNotNull(s);  // Modify assertion as needed
        int result=studentController.insertStudent(s);
        assertEquals(1, result, "Student insertion success!");
        // Assert that student was inserted
        
    }
}