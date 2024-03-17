import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

// Власний клас виключення
class FunctionNotFoundException extends Exception {
    public FunctionNotFoundException(String message) {
        super(message);
    }
}

// Приклад класу з методом, який ми хочемо викликати
class TestClass {
    private double a;

    public TestClass(double a) {
        this.a = a;
    }

    public double calculate(double x) {
        return Math.exp(-Math.abs(a) * x) * Math.sin(x);
    }

    public double calculate(double x, int y) {
        return Math.exp(-Math.abs(a) * x) * Math.sin(x);
    }

    @Override
    public String toString() {
        return "TestClass [a=" + a + ", exp(-abs(a)*x)*sin(x)]";
    }
}
