import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Function {
    double eval(double x);
}

class FunctionHandler implements InvocationHandler {
    private final Function target;

    public FunctionHandler(Function target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, args);
        long endTime = System.nanoTime();
        System.out.println(method.getName() + " took " + (endTime - startTime) + " ns");
        return result;
    }
}

