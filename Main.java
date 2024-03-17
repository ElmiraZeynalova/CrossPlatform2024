import java.lang.reflect.*;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            String className = args[0];
            try {
                String result = ClassAnalyzer.analyzeClass(className);
                System.out.println(result);
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found: " + className);
            }
        } else {
            System.err.println("Usage: java Test <class_name>");
        }
    }
}
