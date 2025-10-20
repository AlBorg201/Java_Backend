package Intro;

import java.util.Scanner;
import java.util.Arrays;

public class Task_1 {

    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    public static int findMin(int[] arr) {
        int min = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
        }
        return min;
    }

    public static double findAverage(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }

    public static double findMax(double[] arr) {
        double max = arr[0];
        for (double num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    public static double findMin(double[] arr) {
        double min = arr[0];
        for (double num : arr) {
            if (num < min) min = num;
        }
        return min;
    }

    public static double findAverage(double[] arr) {
        double sum = 0;
        for (double num : arr) {
            sum += num;
        }
        return sum / arr.length;
    }

    public static void sortAscendingOrder(int[] arr) {
        Arrays.sort(arr);
    }

    public static void sortDescendingOrder(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    public static void sortAscendingOrder(double[] arr) {
        Arrays.sort(arr);
    }

    public static void sortDescendingOrder(double[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length / 2; i++) {
            double temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    // MAIN
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nГенератор массива");

            int size = 0;

            while (true) {
                System.out.print("Введите размер массива: ");
                String sizeStr = scanner.nextLine().trim();

                if (sizeStr.isEmpty()) {
                    System.out.println("Ошибка: пустой ввод");
                    continue;
                }

                if (sizeStr.contains(".") || sizeStr.contains(",")) {
                    System.out.println("Ошибка: размер массива должен быть целым числом");
                    continue;
                }

                try {
                    size = Integer.parseInt(sizeStr);
                    if (size <= 0) {
                        System.out.println("Ошибка: размер массива должен быть больше 0");
                        continue;
                    }
                    if (size > 1_000_000) {
                        System.out.println("Ошибка: слишком большой массив (максимум 1 000 000 элементов)");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите целое число");
                }
            }

            String lowStr, upStr;
            while (true) {
                System.out.print("Введите нижнюю границу массива: ");
                lowStr = scanner.nextLine().trim();

                System.out.print("Введите верхнюю границу массива: ");
                upStr = scanner.nextLine().trim();

                if (lowStr.isEmpty() || upStr.isEmpty()) {
                    System.out.println("Ошибка: пустой ввод");
                    continue;
                }

                boolean isDouble = lowStr.contains(".") || upStr.contains(".");

                try {
                    if (isDouble) {
                        double lower = Double.parseDouble(lowStr);
                        double upper = Double.parseDouble(upStr);
                        if (lower >= upper) {
                            System.out.println("Ошибка: нижняя граница должна быть меньше верхней");
                            continue;
                        }

                        double[] arr = new double[size];
                        for (int i = 0; i < size; i++) {
                            arr[i] = Math.random() * (upper - lower) + lower;
                        }

                        System.out.print("Массив: [");
                        for (int i = 0; i < arr.length; i++) {
                            System.out.printf("%.3f", arr[i]);
                            if (i < arr.length - 1) System.out.print(", ");
                        }
                        System.out.println("]");

                        System.out.printf("Минимальное значение: %.3f%n", findMin(arr));
                        System.out.printf("Максимальное значение: %.3f%n", findMax(arr));
                        System.out.printf("Среднее значение: %.3f%n", findAverage(arr));

                        sortAscendingOrder(arr);
                        System.out.print("По возрастанию: [");
                        for (int i = 0; i < arr.length; i++) {
                            System.out.printf("%.3f", arr[i]);
                            if (i < arr.length - 1) System.out.print(", ");
                        }
                        System.out.println("]");

                        sortDescendingOrder(arr);
                        System.out.print("По убыванию: [");
                        for (int i = 0; i < arr.length; i++) {
                            System.out.printf("%.3f", arr[i]);
                            if (i < arr.length - 1) System.out.print(", ");
                        }
                        System.out.println("]");
                        break;

                    } else {
                        int lower = Integer.parseInt(lowStr);
                        int upper = Integer.parseInt(upStr);
                        if (lower >= upper) {
                            System.out.println("Ошибка: нижняя граница должна быть меньше верхней");
                            continue;
                        }

                        int[] arr = new int[size];
                        for (int i = 0; i < size; i++) {
                            arr[i] = (int) (Math.random() * (upper - lower + 1) + lower);
                        }

                        System.out.println("Массив: " + Arrays.toString(arr));
                        System.out.println("Минимальное значение: " + findMin(arr));
                        System.out.println("Максимальное значение: " + findMax(arr));
                        System.out.println("Среднее значение: " + findAverage(arr));

                        sortAscendingOrder(arr);
                        System.out.println("По возрастанию: " + Arrays.toString(arr));

                        sortDescendingOrder(arr);
                        System.out.println("По убыванию: " + Arrays.toString(arr));
                        break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: границы должны быть числами");
                }
            }

            System.out.print("\nХотите запустить программу снова? (да/нет): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            if (!answer.equals("да")) {
                System.out.println("Программа завершена");
                break;
            }
        }

        scanner.close();
    }
}
