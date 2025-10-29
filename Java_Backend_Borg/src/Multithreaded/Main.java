package Multithreaded;

/**
 * Главный класс для запуска многопоточной банковской системы.
 * Описание работы:
 *     Создаётся объект {@link Bank} с клиентами и начальными курсами валют
 *     Курсы валют автоматически обновляются каждые 3 секунды с помощью "ScheduledThreadPoolExecutor"
 *     Добавляются несколько потоков-кассиров, которые асинхронно обрабатывают транзакции из общей очереди
 *     Все события (транзакции, ошибки, обновления курсов) логгируются через {@link Logger}
 *
 * Таким образом, приложение демонстрирует:
 *     Работу с потоками (Thread, Executor)
 *     Асинхронную очередь транзакций
 *     Паттерн "Observer" для логгирования
 *     Автоматическое обновление валют
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Описание:");
        System.out.println("• Каждый кассир работает в отдельном потоке и обрабатывает транзакции из общей очереди.");
        System.out.println("• Курсы валют обновляются автоматически каждые 3 секунды.");
        System.out.println("• Все события логгируются через Observer-паттерн.");

        // Создание банка и логгер
        Bank bank = new Bank();
        Logger logger = new Logger();
        bank.addObserver(logger);

        // Добавление клиентов
        bank.addClient(new Client(1, 1000, "USD"));
        bank.addClient(new Client(2, 800, "EUR"));
        bank.addClient(new Client(3, 120000, "RUB"));

        // Создание и запуск кассиров
        Cashier cashier1 = new Cashier(1, bank);
        Cashier cashier2 = new Cashier(2, bank);
        Cashier cashier3 = new Cashier(3, bank);

        bank.addCashier(cashier1);
        bank.addCashier(cashier2);
        bank.addCashier(cashier3);

        cashier1.start();
        cashier2.start();
        cashier3.start();

        // Отправляем разные типы транзакций в очередь
        bank.submitTransaction(new Transaction(Transaction.Type.DEPOSIT, 1, 0, "USD", "USD", 200));
        bank.submitTransaction(new Transaction(Transaction.Type.WITHDRAW, 2, 0, "EUR", "EUR", 150));
        bank.submitTransaction(new Transaction(Transaction.Type.TRANSFER, 1, 2, "USD", "EUR", 300));
        bank.submitTransaction(new Transaction(Transaction.Type.EXCHANGE, 3, 0, "RUB", "USD", 50000));
        bank.submitTransaction(new Transaction(Transaction.Type.WITHDRAW, 3, 0, "RUB", "RUB", 200000)); // ошибка
        bank.submitTransaction(new Transaction(Transaction.Type.EXCHANGE, 1, 0, "USD", "JPY", 100)); // ошибка

        // Таймаут, чтобы система отработала
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignored) {
        }

        System.out.println("\nЗавершение работы");
        cashier1.shutdown();
        cashier2.shutdown();
        cashier3.shutdown();
        bank.shutdownScheduler();
    }
}
