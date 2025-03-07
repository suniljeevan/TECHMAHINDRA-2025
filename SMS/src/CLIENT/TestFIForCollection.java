package CLIENT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Student implements Comparable<Student>{
	private int id;
	private String name;
	private double cgpa;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public Student(int id, String name, double cgpa) {
		super();
		this.id = id;
		this.name = name;
		this.cgpa = cgpa;
	}

	public Student() {
		super();
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", cgpa=" + cgpa + "]";
	}
	public int compareTo(Student s) {
		return this.getName().compareTo(s.getName());
	}
	public static void print(Student s) {
		System.out.println(s.getName());
	}
	public static boolean check(Student s) {
		return s.getCgpa()>9.5;
	}

		@Override
	public int hashCode() {
		return Objects.hash(name);
	}

		@Override
		public boolean equals(Object obj) {
			
			Student other = (Student) obj;
			return 
				Objects.equals(name, other.name);
		}
	
	
}
public class TestFIForCollection {
	public static void main(String[] args) {
          List<Student> list=new ArrayList<Student>();
         list.add(new Student(101,"vijay",9.2));
         list.add(new Student(102,"vijay",9.2));
         list.add(new Student(103,"ajay",9.4));
         list.add(new Student(104,"sanjay",9.1));
         list.add(new Student(105,"vijay",9.8));
         Collections.sort(list);
         System.out.println(list);
         Consumer<Student> a=Student::print;
         for(Student s:list)
         a.accept(s);
	Predicate<Student> p=Student::check;
	list.stream().forEach(System.out::println);
	//list.stream().filter(null).collect(Collectors.groupingBy(null));
	//print the details of students whose cgpa>9.5
	List<Student> greatercgpa=
	list.stream().filter(p).collect(Collectors.toList());
	System.out.println(greatercgpa);
	
// distinct,reduce, Max, min, Collectors method
	// joining, grouping , partition
	// find distinct names
	Stream<Student> x=list.stream().distinct();
	List<Student> y=x.collect(Collectors.toList());
	System.out.println("distinct students");
	System.out.println(y);
	y.forEach(a);
}
}





