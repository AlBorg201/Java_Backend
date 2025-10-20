package VCS_Adv_OOP.app;

import VCS_Adv_OOP.properties.Engine;
import VCS_Adv_OOP.properties.Fuel;
import VCS_Adv_OOP.transportType.Transport;
import VCS_Adv_OOP.transports.Airplane;
import VCS_Adv_OOP.transports.Bicycle;
import VCS_Adv_OOP.transports.Car;
import VCS_Adv_OOP.transports.Ship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Transport transport = null;

        try {
            int choice = 0;
            while (true) {
                System.out.println("Выберите тип транспорта: ");
                System.out.println("1 - Автомобиль, 2 - Самолет, 3 - Корабль, 4 - Велосипед");
                System.out.print("> ");

                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Ошибка: пустой ввод");
                    continue;
                }

                try {
                    choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= 4) {
                        break;
                    } else {
                        System.out.println("Ошибка: выберите число от 1 до 4");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите число, а не текст");
                }
            }

            System.out.print("Введите модель транспорта: ");
            String model = sc.nextLine().trim();
            if (model.isEmpty()) model = "Без названия";

            int maxSpeed;
            while (true) {
                System.out.print("Введите максимальную скорость транспорта, км/ч: ");
                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Ошибка: пустой ввод");
                    continue;
                }

                try {
                    maxSpeed = Integer.parseInt(input);
                    if (maxSpeed > 0) break;
                    else System.out.println("Ошибка: скорость должна быть положительной");
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите корректное число");
                }
            }

            switch (choice) {
                case 1, 2, 3 -> {
                    double horsePower = 0;
                    while (true) {
                        System.out.print("Введите мощность двигателя, л.с.: ");
                        String input = sc.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("Ошибка: пустой ввод");
                            continue;
                        }

                        try {
                            horsePower = Double.parseDouble(input);
                            if (horsePower > 0) break;
                            else System.out.println("Ошибка: мощность должна быть положительной");
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: введите корректное число");
                        }
                    }

                    int fuelChoice = 0;
                    while (true) {
                        System.out.println("Выберите тип топлива:");
                        System.out.println("1 - Бензин, 2 - Дизель, 3 - Электричество, 4 - Керосин");
                        System.out.print("> ");
                        String input = sc.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("Ошибка: пустой ввод");
                            continue;
                        }

                        try {
                            fuelChoice = Integer.parseInt(input);
                            if (fuelChoice >= 1 && fuelChoice <= 4) break;
                            else System.out.println("Ошибка: выберите число от 1 до 4");
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: введите число, а не текст");
                        }
                    }

                    Fuel fuelType = switch (fuelChoice) {
                        case 1 -> Fuel.БЕНЗИН;
                        case 2 -> Fuel.ДИЗЕЛЬ;
                        case 3 -> Fuel.ЭЛЕКТРИЧЕСТВО;
                        case 4 -> Fuel.КЕРОСИН;
                        default -> Fuel.БЕНЗИН;
                    };

                    Engine engine = new Engine(horsePower, fuelType);

                    transport = switch (choice) {
                        case 1 -> new Car(model, maxSpeed, engine);
                        case 2 -> new Airplane(model, maxSpeed, engine);
                        case 3 -> new Ship(model, maxSpeed, engine);
                        default -> null;
                    };
                }
                case 4 -> transport = new Bicycle(model, maxSpeed);
            }

            if (transport == null) {
                System.out.println("Ошибка: транспорт не создан");
                return;
            }

            while (true) {
                System.out.println("\nВыберите действие: ");
                System.out.println("1. Показать информацию");
                System.out.println("2. Завести транспорт");
                System.out.println("3. Начать движение");
                System.out.println("4. Остановить транспорт");
                System.out.println("5. Выход");

                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Ошибка: пустой ввод");
                    continue;
                }

                int action;
                try {
                    action = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите число");
                    continue;
                }

                switch (action) {
                    case 1 -> transport.showInfo();
                    case 2 -> transport.start();
                    case 3 -> transport.move();
                    case 4 -> transport.stop();
                    case 5 -> {
                        System.out.println("Выход из программы");
                        return;
                    }
                    default -> System.out.println("Неверный выбор");
                }
            }

        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
