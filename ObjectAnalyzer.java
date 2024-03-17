import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ObjectAnalyzer {

    public static void inspectObject(Object obj) {
        Class<?> clazz = obj.getClass();

        System.out.println("Реальний тип об'єкту: " + clazz.getName());

        // Вивід полів та їх значень
        System.out.println("Стан об'єкту:");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                System.out.println(field.getType().getName() + " " + field.getName() + " = " + value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        // Вивід відкритих методів
        System.out.println("Список відкритих методів:");
        Method[] methods = clazz.getMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println((i + 1) + "). " + methods[i]);
        }

        // Виклик методу
        System.out.print("Введіть порядковий номер методу [1, " + methods.length + "]: ");
        Scanner scanner = new Scanner(System.in);
        int methodIndex = scanner.nextInt();
        if (methodIndex >= 1 && methodIndex <= methods.length) {
            try {
                Object result = methods[methodIndex - 1].invoke(obj);
                System.out.println("Результат виклику методу: " + result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Невірний номер методу.");
        }
    }
}
