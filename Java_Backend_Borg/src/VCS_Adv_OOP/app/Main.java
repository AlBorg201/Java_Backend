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

        int choice = 0;
        while (true) {
            System.out.println("Выберите тип транспорта: ");
            System.out.println("1 - Автомобиль, 2 - Самолет, 3 - Корабль, 4 - Велосипед");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice >= 1 && choice <= 4) {
                    break;
                } else {
                    System.out.println("Ошибка: выберите число от 1 до 4");
                }
            } else {
                System.out.println("Ошибка: введите число, а не текст");
                sc.nextLine();
            }
        }

        System.out.print("Введите модель транспорта: ");
        String model = sc.nextLine();

        System.out.print("Введите максимальную скорость транспорта, км/ч: ");
        int maxSpeed = sc.nextInt();

        Transport transport = null;

        switch (choice) {
            case 1, 2, 3 -> {
                System.out.print("Введите мощность двигателя, л.с.: ");
                double horsePower = sc.nextDouble();
                sc.nextLine();

                System.out.println("Выберите тип топлива: ");
                System.out.println("1 - Бензин, 2 - Дизель, 3 - Электричество, 4 - Керосин");

                int fuelChoice = sc.nextInt();
                sc.nextLine();

                Fuel fuelType;
                switch (fuelChoice) {
                    case 1 -> fuelType = Fuel.БЕНЗИН;
                    case 2 -> fuelType = Fuel.ДИЗЕЛЬ;
                    case 3 -> fuelType = Fuel.ЭЛЕКТРИЧЕСТВО;
                    case 4 -> fuelType = Fuel.КЕРОСИН;
                    default -> {
                        System.out.println("Неверный тип топлива, по умолчанию выбран бензин");
                        fuelType = Fuel.БЕНЗИН;
                    }
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

        while (true) {
            System.out.println("\nВыберите действие: ");
            System.out.println("1. Показать информацию");
            System.out.println("2. Завести транспорт");
            System.out.println("3. Начать движение");
            System.out.println("4. Остановить транспорт");
            System.out.println("5. Выход");

            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 1 -> transport.showInfo();
                case 2 -> transport.start();
                case 3 -> transport.move();
                case 4 -> transport.stop();
                case 5 -> {
                    System.out.println("Выход из программы");
                    sc.close();
                    return;
                }
                default -> System.out.println("Неверный выбор");
            }
        }
    }
}
