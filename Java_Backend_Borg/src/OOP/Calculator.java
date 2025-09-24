package OOP;

// Класс калькулятора
public class Calculator {

    public Operation newOperation(String operator, double num_1, double num_2) {
        return switch (operator) {
            case "+" -> new Addition(num_1, num_2);
            case "-" -> new Subtraction(num_1, num_2);
            case "*" -> new Multiplication(num_1, num_2);
            case "/" -> new Division(num_1, num_2);
            case "^" -> new Exponentiation(num_1, num_2);
            case "%" -> new Remainder(num_1, num_2);
            default -> throw new IllegalArgumentException("Неизвестаня операция: " + operator);
        };
    }

    public double calculate(Operation operation) {
        return operation.getResult();
    }

    public String formatResult(double result) {
        if (result == (int) result) {
            return String.valueOf((int) result);
        } else {
            return String.format("%.3f", result);
        }
    }
}
