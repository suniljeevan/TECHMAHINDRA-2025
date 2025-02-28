package CLIENT;

import java.util.Random;

@FunctionalInterface 
interface X <T extends Number>{
	public abstract void compare(T x,T y);
	
}

public class BasicDemo2 {
	public static void main(String[] args) {
       X r1=()-> {
    	   Random R=new Random();
    	   System.out.println(R.nextInt()%100);
       };
       r.compare();
    X r2=()-> {
    	Random R=new Random();
 	   System.out.println(Math.ceil(R.nextFloat()*100));
    };
    r2.compare();
    X r3=() -> {
    	
    	
    };
       
	}

}
