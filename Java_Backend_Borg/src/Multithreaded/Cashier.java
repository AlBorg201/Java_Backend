package Multithreaded;

import java.util.concurrent.BlockingQueue;

/**
 * Класс кассира, работающего в отдельном потоке.
 * Кассир постоянно извлекает транзакции из очереди банка и обрабатывает их.
 * Поддерживаются операции: депозит, снятие, перевод и обмен валют.
 */
public class Cashier extends Thread {
    private final int id;
    private final Bank bank;
    private volatile boolean active = true;

    /**
     * Создаёт новый поток-кассир.
     *
     * @param id   уникальный идентификатор кассира
     * @param bank ссылка на объект банка
     */
    public Cashier(int id, Bank bank) {
        this.id = id;
        this.bank = bank;
        setName("Cashier-" + id);
    }

    // Главный цикл кассира — обрабатывает транзакции из очереди
    @Override
    public void run() {
        BlockingQueue<Transaction> queue = bank.getTransactionQueue();

        while (active) {
            try {
                Transaction transaction = queue.take();
                processTransaction(transaction);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                bank.notifyObservers(getName() + " — ошибка при обработке транзакции: " + e.getMessage());
            }
        }
    }

    /**
     * Обрабатывает транзакцию в зависимости от её типа.
     *
     * @param transaction объект транзакции
     */
    private void processTransaction(Transaction transaction) {
        switch (transaction.getType()) {
            case DEPOSIT -> handleDeposit(transaction);
            case WITHDRAW -> handleWithdraw(transaction);
            case TRANSFER -> handleTransfer(transaction);
            case EXCHANGE -> handleExchange(transaction);
            default -> bank.notifyObservers(getName() + " — неизвестный тип транзакции: " + transaction);
        }
    }

    // Обработка депозита (пополнение счёта)
    private void handleDeposit(Transaction transaction) {
        Client client = bank.getClient(transaction.getClientId());
        if (client == null) {
            bank.notifyObservers(getName() + " — ошибка: клиент #" + transaction.getClientId() + " не найден.");
            return;
        }

        double newBalance = client.getBalance() + transaction.getAmount();
        client.setBalance(newBalance);
        bank.notifyObservers(getName() + " — депозит выполнен: клиент #" + client.getId() +
                " пополнил счёт на " + transaction.getAmount() +
                " " + client.getCurrency() + ". Новый баланс: " + newBalance);
    }

    // Обработка снятия средств со счёта
    private void handleWithdraw(Transaction transaction) {
        Client client = bank.getClient(transaction.getClientId());
        if (client == null) {
            bank.notifyObservers(getName() + " — ошибка: клиент #" + transaction.getClientId() + " не найден.");
            return;
        }

        double currentBalance = client.getBalance();
        double amount = transaction.getAmount();

        if (currentBalance < amount) {
            bank.notifyObservers(getName() + " — ошибка: недостаточно средств у клиента #" + client.getId() +
                    " (баланс: " + currentBalance + ", попытка снять: " + amount + ")");
            return;
        }

        client.setBalance(currentBalance - amount);
        bank.notifyObservers(getName() + " — снятие выполнено: клиент #" + client.getId() +
                " снял " + amount + " " + client.getCurrency() +
                ". Новый баланс: " + client.getBalance());
    }

    // Обработка перевода между клиентами
    private void handleTransfer(Transaction transaction) {
        Client sender = bank.getClient(transaction.getClientId());
        Client receiver = bank.getClient(transaction.getTargetClientId());

        if (sender == null || receiver == null) {
            bank.notifyObservers(getName() + " — ошибка: клиент-отправитель или получатель не найден.");
            return;
        }

        double amount = transaction.getAmount();
        if (sender.getBalance() < amount) {
            bank.notifyObservers(getName() + " — ошибка: у клиента #" + sender.getId() +
                    " недостаточно средств для перевода (" + amount + ")");
            return;
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        bank.notifyObservers(getName() + " — перевод выполнен: клиент #" + sender.getId() +
                " → клиент #" + receiver.getId() +
                " сумма: " + amount + " " + sender.getCurrency());
    }

    // Обработка обмена валют
    private void handleExchange(Transaction transaction) {
        Client client = bank.getClient(transaction.getClientId());
        if (client == null) {
            bank.notifyObservers(getName() + " — ошибка: клиент #" + transaction.getClientId() + " не найден.");
            return;
        }

        String from = transaction.getFromCurrency();
        String to = transaction.getToCurrency();
        double amount = transaction.getAmount();

        Double fromRate = bank.getExchangeRates().get(from);
        Double toRate = bank.getExchangeRates().get(to);

        if (fromRate == null || toRate == null) {
            bank.notifyObservers(getName() + " — ошибка: неизвестная валюта (" + from + " или " + to + ")");
            return;
        }

        if (!client.getCurrency().equals(from)) {
            bank.notifyObservers(getName() + " — ошибка: клиент #" + client.getId() +
                    " не имеет счёта в валюте " + from);
            return;
        }

        if (client.getBalance() < amount) {
            bank.notifyObservers(getName() + " — ошибка: недостаточно средств у клиента #" + client.getId());
            return;
        }

        double usdValue = amount / fromRate;
        double converted = usdValue * toRate;

        client.setBalance(converted);
        client.setCurrency(to);

        bank.notifyObservers(getName() + " — обмен валют выполнен: " +
                amount + " " + from + " → " + converted + " " + to +
                " (клиент #" + client.getId() + ")");
    }

    // Завершает работу кассира
    public void shutdown() {
        active = false;
        interrupt();
    }
}
