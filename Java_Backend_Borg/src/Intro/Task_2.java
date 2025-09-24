package Intro;

import java.util.Scanner;

public class Task_2 {

    public static double addition(double num_1, double num_2) {
        return num_1 + num_2;
    }

    public static double difference(double num_1, double num_2) {
        return num_1 - num_2;
    }

    public static double multiply(double num_1, double num_2) {
        return num_1 * num_2;
    }

    public static double division(double num_1, double num_2) {
        return num_1 / num_2;
    }

    public static double remainder(double num_1, double num_2) {
        return num_1 % num_2;
    }

    public static double exponentiation(double num_1, double num_2) {
        return Math.pow(num_1, num_2);
    }

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

     // Приводит число к типу int, либо округляет до трех знаков после запятой
    public static String formatResult(double result) {
        if (result == (int) result) {
            return String.valueOf((int) result);
        } else {
            return String.format("%.3f", result);
        }
    }

    // MAIN
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Типы операций: +, -, *, /, ^, %");
        System.out.println("Введите 'exit' для выхода.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход...");
                break;
            }

            String[] parts = withoutSpace(input);

            if (!validateExpression(parts)) {
                System.out.println("Неверное выражение, введите еще раз:");
                continue;
            }

            double num_1 = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double num_2 = Double.parseDouble(parts[2]);

            try {
                switch (operator) {
                    case "+":
                        System.out.println("Результат: " + formatResult(addition(num_1, num_2)));
                        break;
                    case "-":
                        System.out.println("Результат: " + formatResult(difference(num_1, num_2)));
                        break;
                    case "*":
                        System.out.println("Результат: " + formatResult(multiply(num_1, num_2)));
                        break;
                    case "/":
                        if (num_2 == 0) {
                            System.out.println("Ошибка: деление на ноль");
                        } else {
                            System.out.println("Результат: " + formatResult(division(num_1, num_2)));
                        }
                        break;
                    case "^":
                        System.out.println("Результат: " + formatResult(exponentiation(num_1, num_2)));
                        break;
                    case "%":
                        if (num_2 == 0) {
                            System.out.println("Ошибка: деление на ноль");
                        } else {
                            System.out.println("Результат: " + formatResult(remainder(num_1, num_2)));
                        }
                        break;
                    default:
                        System.out.println("Ошибка: неизвестная операция");
                }
            } catch (ArithmeticException e) {
                System.out.println("Ошибка вычисления: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
