package Multithreaded;

import java.util.*;
import java.util.concurrent.*;

/**
 * Основной класс системы
 * Хранит клиентов, курсы валют, очередь транзакций, кассиров и наблюдателей
 * А также содержит планировщик, автоматически обновляющий курсы валют
 */
public class Bank {

    private final ConcurrentMap<Integer, Client> clients = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Double> exchangeRates = new ConcurrentHashMap<>();
    private final BlockingQueue<Transaction> transactionQueue = new LinkedBlockingQueue<>();
    private final List<Observer> observers = new CopyOnWriteArrayList<>();
    private final List<Cashier> cashiers = new ArrayList<>();
    private final ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1);

    /**
     * Создаёт новый объект банка.
     * Инициализирует базовые курсы валют и запускает планировщик их обновления.
     */
    public Bank() {
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.9);
        exchangeRates.put("RUB", 78.0);

        scheduler.scheduleAtFixedRate(this::updateExchangeRates, 0, 3, TimeUnit.SECONDS);
        notifyObservers("Инициализирован банк. Курсы валют будут обновляться каждые 3 секунды.");
    }

    /**
     * Метод, симулирующий изменение курсов валют.
     * Для демонстрации используется случайное изменение в пределах ±5%.
     */
    private void updateExchangeRates() {
        Random random = new Random();
        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            double oldRate = entry.getValue();
            double delta = (random.nextDouble() * 0.1) - 0.05; // от -5% до +5%
            double newRate = Math.max(0.01, oldRate * (1 + delta));
            exchangeRates.put(entry.getKey(), newRate);
            notifyObservers(String.format("Курс %s обновлён: %.4f → %.4f", entry.getKey(), oldRate, newRate));
        }
    }

    /**
     * Добавляет клиента в систему.
     *
     * @param client клиент для добавления
     */
    public void addClient(Client client) {
        clients.put(client.getId(), client);
        notifyObservers("Добавлен новый клиент: " + client);
    }

    /**
     * Возвращает клиента по ID.
     *
     * @param id идентификатор клиента
     * @return объект клиента или null, если не найден
     */
    public Client getClient(int id) {
        return clients.get(id);
    }

    /**
     * Регистрирует наблюдателя.
     *
     * @param observer объект, реализующий интерфейс
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Уведомляет всех наблюдателей о событии.
     *
     * @param message текст уведомления
     */
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    /**
     * Добавляет транзакцию в очередь для асинхронной обработки.
     *
     * @param transaction объект транзакции
     */
    public void submitTransaction(Transaction transaction) {
        transactionQueue.add(transaction);
        notifyObservers("Транзакция добавлена в очередь: " + transaction);
    }

    /**
     * @return потокобезопасная очередь транзакций
     */
    public BlockingQueue<Transaction> getTransactionQueue() {
        return transactionQueue;
    }

    /**
     * @return актуальные курсы валют
     */
    public ConcurrentMap<String, Double> getExchangeRates() {
        return exchangeRates;
    }

    /**
     * Добавляет кассира в систему.
     *
     * @param cashier объект кассира
     */
    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
    }

    /**
     * @return список всех активных кассиров
     */
    public List<Cashier> getCashiers() {
        return cashiers;
    }

    /**
     * Останавливает планировщик обновления валют.
     */
    public void shutdownScheduler() {
        scheduler.shutdownNow();
        notifyObservers("Планировщик курсов валют остановлен.");
    }
}
