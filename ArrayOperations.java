import java.lang.reflect.Array;

public class ArrayOperations {
    // Метод для створення одновимірного масиву
    public static <T> T[] createArray(int length, Class<T> type) {
        T[] array = (T[]) Array.newInstance(type, length);
        // Додаємо значення за замовчуванням (null для посилань та 0 для примітивів)
        for (int i = 0; i < length; i++) {
            array[i] = getDefaultElement(type);
        }
        return array;
    }

    // Метод для створення матриці
    public static <T> T[][] createMatrix(int rows, int cols, Class<T> type) {
        T[][] matrix = (T[][]) Array.newInstance(type, rows, cols);
        // Додаємо значення за замовчуванням (null для посилань та 0 для примітивів)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = getDefaultElement(type);
            }
        }
        return matrix;
    }

    // Метод для зміни розміру одновимірного масиву зі збереженням вмісту
    public static <T> T[] resizeArray(T[] array, int newSize) {
        T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), newSize);
        System.arraycopy(array, 0, newArray, 0, Math.min(array.length, newSize));
        return newArray;
    }

    // Метод для зміни розміру матриці зі збереженням вмісту
    public static <T> T[][] resizeMatrix(T[][] matrix, int newRows, int newCols) {
        T[][] newMatrix = (T[][]) Array.newInstance(matrix.getClass().getComponentType().getComponentType(), newRows, newCols);
        for (int i = 0; i < Math.min(matrix.length, newRows); i++) {
            newMatrix[i] = resizeArray(matrix[i], newCols);
        }
        return newMatrix;
    }

    // Метод для перетворення одновимірного масиву в рядок
    public static <T> String arrayToString(T[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append(array.getClass().getComponentType().getSimpleName())
                .append("[").append(array.length).append("] = {");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(array[i]);
        }
        sb.append("}");
        return sb.toString();
    }

    // Метод для перетворення матриці в рядок
    public static <T> String matrixToString(T[][] matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append(matrix.getClass().getComponentType().getComponentType().getSimpleName())
                .append("[").append(matrix.length).append("][").append(matrix[0].length).append("] = {\n");
        for (int i = 0; i < matrix.length; i++) {
            if (i > 0) sb.append(",\n");
            sb.append(arrayToString(matrix[i]));
        }
        sb.append("\n}");
        return sb.toString();
    }

    // Метод для отримання значення за замовчуванням для типу даних
    private static <T> T getDefaultElement(Class<T> type) {
        if (type == boolean.class || type == Boolean.class) {
            return (T) Boolean.FALSE;
        } else if (type == byte.class || type == Byte.class) {
            return (T) Byte.valueOf((byte) 0);
        } else if (type == short.class || type == Short.class) {
            return (T) Short.valueOf((short) 0);
        } else if (type == int.class || type == Integer.class) {
            return (T) Integer.valueOf(0);
        } else if (type == long.class || type == Long.class) {
            return (T) Long.valueOf(0L);
        } else if (type == float.class || type == Float.class) {
            return (T) Float.valueOf(0.0f);
        } else if (type == double.class || type == Double.class) {
            return (T) Double.valueOf(0.0);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Integer[] array = createArray(2, Integer.class);
        System.out.println(arrayToString(array));

        String[][] matrix = createMatrix(3, 5, String.class);
        System.out.println(matrixToString(matrix));

        matrix = resizeMatrix(matrix, 4, 6);
        System.out.println(matrixToString(matrix));
    }
}
