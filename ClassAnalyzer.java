import java.lang.reflect.*;
import java.util.Arrays;

public class ClassAnalyzer {

    public static String analyzeClass(Class<?> clazz) {
        StringBuilder result = new StringBuilder();

        // Package
        Package pkg = clazz.getPackage();
        if (pkg != null) {
            result.append("package ").append(pkg.getName()).append(";\n");
        }

        // Class declaration
        int modifiers = clazz.getModifiers();
        result.append(Modifier.toString(modifiers)).append(" ");

        if (clazz.isInterface()) {
            result.append("interface ");
        } else if (clazz.isEnum()) {
            result.append("enum ");
        } else if (clazz.isAnnotation()) {
            result.append("@interface ");
        } else {
            result.append("class ");
        }

        result.append(clazz.getSimpleName());

        // Extends
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null && !superclass.equals(Object.class)) {
            result.append(" extends ").append(superclass.getSimpleName());
        }

        // Implements
        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            result.append(" implements ");
            for (int i = 0; i < interfaces.length; i++) {
                if (i > 0) {
                    result.append(", ");
                }
                result.append(interfaces[i].getSimpleName());
            }
        }

        result.append(" {\n");

        // Fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            result.append("\t").append(Modifier.toString(field.getModifiers())).append(" ");
            result.append(field.getType().getSimpleName()).append(" ");
            result.append(field.getName()).append(";\n");
        }

        // Constructors
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            result.append("\t").append(Modifier.toString(constructor.getModifiers())).append(" ");
            result.append(clazz.getSimpleName()).append("(");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) {
                    result.append(", ");
                }
                result.append(parameterTypes[i].getSimpleName()).append(" param").append(i);
            }
            result.append(");\n");
        }

        // Methods
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            result.append("\t").append(Modifier.toString(method.getModifiers())).append(" ");
            result.append(method.getReturnType().getSimpleName()).append(" ");
            result.append(method.getName()).append("(");
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) {
                    result.append(", ");
                }
                result.append(parameterTypes[i].getSimpleName()).append(" param").append(i);
            }
            result.append(");\n");
        }

        result.append("}\n");

        return result.toString();
    }

    public static String analyzeClass(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        return analyzeClass(clazz);
    }
}
