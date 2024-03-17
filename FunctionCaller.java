import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class FunctionCaller {
    // Метод для виклику методу за його ім'ям та параметрами на об'єкті
    public static void callMethod(Object obj, String methodName, Object... params) throws FunctionNotFoundException {
        Class<?>[] paramTypes = Arrays.stream(params).map(Object::getClass).toArray(Class[]::new);

        try {
            Method method = findMethod(obj.getClass(), methodName, paramTypes);
            Object result = method.invoke(obj, params);
            System.out.println("Результат виклику: " + result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new FunctionNotFoundException("Метод не знайдено або неможливо викликати з наданими параметрами.");
        }
    }

    // Метод для пошуку методу в класі або його суперкласах
    private static Method findMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) throws NoSuchMethodException {
        Method method = null;
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            try {
                method = currentClass.getDeclaredMethod(methodName, paramTypes);
                break;
            } catch (NoSuchMethodException e) {
                currentClass = currentClass.getSuperclass();
            }
        }
        if (method == null) {
            throw new NoSuchMethodException();
        }
        method.setAccessible(true); // Для доступу до приватних методів
        return method;
    }

    public static void main(String[] args) {
        TestClass obj = new TestClass(1.0);

        try {
            System.out.println(obj);
            callMethod(obj, "calculate", 1.0);
            callMethod(obj, "calculate", 1.0, 1);
        } catch (FunctionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
