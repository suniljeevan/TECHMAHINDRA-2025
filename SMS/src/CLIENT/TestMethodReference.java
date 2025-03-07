package CLIENT;
import java.util.function.Supplier;
class M {
	static int a=10;
	public static int meth1() {
		return(a);
		}
}
public class TestMethodReference {

	public static void main(String[] args) {
		Supplier<Integer> s=() ->{
			return M.meth1();
		};
		
     System.out.println(s.get());
     
     
	}

}
