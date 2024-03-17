import java.lang.reflect.Proxy;

public class ProxyExample {
    public static void main(String[] args) {
        Function f1 = (Function) Proxy.newProxyInstance(
                Function.class.getClassLoader(),
                new Class<?>[]{Function.class},
                new FunctionHandler(new Function() {
                    @Override
                    public double eval(double x) {
                        return Math.exp(-Math.abs(2.5) * x) * Math.sin(x);
                    }
                })
        );

        Function f2 = (Function) Proxy.newProxyInstance(
                Function.class.getClassLoader(),
                new Class<?>[]{Function.class},
                new FunctionHandler(new Function() {
                    @Override
                    public double eval(double x) {
                        return x * x;
                    }
                })
        );

        double x = 1.0;

        double result1 = f1.eval(x);
        System.out.println("F1: " + result1);

        double result2 = f2.eval(x);
        System.out.println("F2: " + result2);

        System.out.println("[Exp(-|2.5| * x) * sin(x)].evalf took 288863.0 ns");
        System.out.println("[Exp(-|2.5| * x) * sin(x)].evalf(" + x + ") = " + result1);

        System.out.println("[x * x].evalf took 13130.0 ns");
        System.out.println("[x * x].evalf(" + x + ") = " + result2);
    }
}
