package CLIENT;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

//Consumer Interface builtin
//Use Consumer to check even or odd integers
// in a List
//accept(one)
public class BasicDemo3 {
	public static void main(String[] args) {
     Consumer<Integer> c=x->{
    	 if(x%2==0)
    	System.out.println("even");
    	 else
    		 System.out.println("odd");
     };
     //List<Integer> list=new ArrayList<Integer>();
     // list.add(10);list.add(20);
     List<Integer> list=Arrays.asList(10,21,31,40);
     for(int i=0;i<list.size();i++) {
    	 c.accept(list.get(i)); 
     }
     
	}

}
