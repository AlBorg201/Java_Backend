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

            if (input.isEmpty()) {
                System.out.println("Ошибка: пустой ввод");
                continue;
            }

            if (!validateExpression(parts)) {
                System.out.println("Неверное выражение, введите еще раз:");
                continue;
            }

            String operator = parts[1];
            if (!operator.matches("[+\\-*/%^]")) {
                System.out.println("Ошибка: неизвестный оператор '" + operator + "'");
                continue;
            }

            double num_1 = Double.parseDouble(parts[0]);
            double num_2 = Double.parseDouble(parts[2]);

            try {
                num_1 = Double.parseDouble(parts[0]);
                num_2 = Double.parseDouble(parts[2]);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: некорректные числа");
                continue;
            }

            try {
                double result = 0;

                switch (operator) {
                    case "+": result = addition(num_1, num_2); break;
                    case "-": result = difference(num_1, num_2); break;
                    case "*": result = multiply(num_1, num_2); break;
                    case "/":
                        if (num_2 == 0) {
                            System.out.println("Ошибка: деление на ноль");
                            continue;
                        }
                        result = division(num_1, num_2);
                        break;
                    case "%":
                        if (num_2 == 0) {
                            System.out.println("Ошибка: деление на ноль");
                            continue;
                        }
                        result = remainder(num_1, num_2);
                        break;
                    case "^":
                        if (num_1 == 0 && num_2 < 0) {
                            System.out.println("Ошибка: ноль в отрицательной степени не определён");
                            continue;
                        }
                        result = exponentiation(num_1, num_2);
                        break;
                }

                if (Double.isNaN(result) || Double.isInfinite(result)) {
                    System.out.println("Ошибка: результат не определён или слишком велик");
                } else {
                    System.out.println("Результат: " + formatResult(result));
                }

            } catch (ArithmeticException e) {
                System.out.println("Ошибка вычисления: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
