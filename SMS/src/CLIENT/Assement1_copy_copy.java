package CLIENT;

import java.util.function.Function;

public class Assement1_copy_copy {
	public static void main(String[] args) {
     Function<Double,Double> f=r-> {
    	return 3.14*r*r; 
     };
    System.out.println("Area of circle "+ f.apply(5.5));
	}

}
