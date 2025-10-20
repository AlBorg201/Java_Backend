package OOP;

import java.util.Scanner;

// MAIN
public class Task_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        System.out.println("Типы операций: +, -, *, /, ^, %");
        System.out.println("Введите 'exit' для выхода.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход...");
                break;
            }

            String[] parts = Validator.withoutSpace(input);

            if (input.isEmpty()) {
                System.out.println("Ошибка: пустой ввод");
                continue;
            }

            if (!Validator.validateExpression(parts)) {
                System.out.println("Неверное выражение, введите еще раз:");
                continue;
            }

            double num_1, num_2;

            try {
                num_1 = Double.parseDouble(parts[0]);
                num_2 = Double.parseDouble(parts[2]);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: некорректные числа");
                continue;
            }

            String operator = parts[1];

            try {
                Operation operation = calculator.newOperation(operator, num_1, num_2);
                double result = calculator.calculate(operation);

                if (Double.isNaN(result) || Double.isInfinite(result)) {
                    System.out.println("Ошибка: результат не определён или слишком велик");
                } else {
                    System.out.println("Результат: " + calculator.formatResult(result));
                }

            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Непредвиденная ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
