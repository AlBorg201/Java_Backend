package OOP;

// Класс для валидации выражений
public class Validator {
    // Проверяет, что массив содержит 3 элемента
    public static boolean validateExpression(String[] parts) {
        if (parts.length != 3) {
            return false;
        }
        try {
            Double.parseDouble(parts[0]);
            Double.parseDouble(parts[2]);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // Убирает пробелы слева и справа, а также вокруг операторов
    public static String[] withoutSpace(String input) {
        input = input.trim();
        input = input.replaceAll("\\s*([+\\-*/%^])\\s*", "$1");
        return input.split("(?<=[+\\-*/%^])|(?=[+\\-*/%^])");
    }
}
