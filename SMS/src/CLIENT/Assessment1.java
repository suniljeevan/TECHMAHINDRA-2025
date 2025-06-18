package CLIENT;
abstract class Shape {
	abstract public double area(int a,int b);
}
class Triangle extends Shape {
	public double area(int b,int h) {
		return 0.5*b*h;
	}
}
public class Assessment1 {
	public static void main(String[] args) {
		Shape s=new Triangle();//
		double Area=s.area(4, 6);
		System.out.println("area is "+Area);
	}

}
