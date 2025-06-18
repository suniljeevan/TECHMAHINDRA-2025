package Unittest;
import org.junit.platform.console.ConsoleLauncher;

public class TestRunner {
    public static void main(String[] args) {
        ConsoleLauncher.main(new String[]{"--select-class", "Unittest.StudentControllerTest3"});
    }
}