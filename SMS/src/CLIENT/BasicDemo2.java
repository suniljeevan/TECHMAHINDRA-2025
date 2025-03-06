package CLIENT;

import java.util.Random;

@FunctionalInterface 
interface X <T extends Number>{
	public abstract void compare(T x,T y);
	
}

public class BasicDemo2 {
	public static void main(String[] args) {
       X r1=(x,y)-> {
    	  if(x.intValue()>y.intValue()) 
    		  System.out.println("first is greater");
    	  else 
    		  System.out.println("second is greater");
    	    };
       r1.compare(10,20);
    X r2=(x,y)-> {
    	if(x.doubleValue()>y.doubleValue()) 
  		  System.out.println("first is greater");
  	  else 
  		  System.out.println("second is greater");	
 	  
    };
    r2.compare(10.5,9.5);
          
	}

}
