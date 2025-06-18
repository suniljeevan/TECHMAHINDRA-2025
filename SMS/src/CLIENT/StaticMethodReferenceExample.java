package CLIENT;
import java.util.function.Function;

public class StaticMethodReferenceExample {
    public static int square(int number) {
        return number * number;
    }

    public static void main(String[] args) {
        // Lambda Expression
        Function<Integer, Integer> lambdaSquare = number -> StaticMethodReferenceExample.square(number);
        
        // Method Reference
        Function<Integer, Integer> methodRefSquare = StaticMethodReferenceExample::square;

        System.out.println("Square using Lambda: " + lambdaSquare.apply(5));
        System.out.println("Square using Method Reference: " + methodRefSquare.apply(5));
    }
}
