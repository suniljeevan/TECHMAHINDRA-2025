package CLIENT;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TESTFunctionalInterfaces {
   
	public static void main(String[] args) {
       Consumer<Integer> a=x->{
    	   System.out.println(x);
       };
       a.accept(10);
       Supplier<Double> b=()-> {
    	  double y=10.5;
       return y;
       };
      System.out.println( b.get());
       Predicate<Integer> c=x->{
    	   return x%2==0;
    	};
     System.out.println(  c.test(99));
	}

}
