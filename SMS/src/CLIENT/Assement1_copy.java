package CLIENT;
@FunctionalInterface
interface myShape {
	double area(int a,int b);
}

public class Assement1_copy {
	public static void main(String[] args) {
		//Circle is anonymous
      myShape s=(x,y)-> {
    	  return 3.141*x*x;
      };
      double area=s.area(2, 3);
      System.out.println("Area of Circle is "+area);
	}

}
